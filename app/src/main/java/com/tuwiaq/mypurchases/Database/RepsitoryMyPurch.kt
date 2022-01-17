package com.tuwiaq.mypurchases.Database

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.tuwiaq.mypurchases.Cart.Cart
import com.tuwiaq.mypurchases.RegisterFragment.User
import com.tuwiaq.mypurchases.Supermarket.SuperMarkt
import com.tuwiaq.mypurchases.UserProductor.Prodctor
import kotlinx.coroutines.tasks.await
import java.lang.Exception

import java.lang.IllegalStateException
import java.util.*

private const val TAG = "RepsitoryMyPurch"

class RepsitoryMyPurch private constructor(context: Context) {
    //-----firebases---------//
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val iamgestorage = FirebaseStorage.getInstance()
    var storageRef = iamgestorage.reference
//------comp---///
    companion object {

        var INSTANCE: RepsitoryMyPurch? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {

                INSTANCE = RepsitoryMyPurch(context)
            }
        }

        fun get(): RepsitoryMyPurch {
            return INSTANCE
                ?: throw IllegalStateException("My purchases Repositor must be initialized")
        }
    }
//    private fun showToast(msg: String) {
//        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
//
//    }

    //////////////////------log in -----//////////////////////

    suspend fun loginUser(emaiETexts: String, passWords: String) : Boolean {
        var x: AuthResult?  = null
        try {
            x = auth.signInWithEmailAndPassword(emaiETexts, passWords)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.e(TAG, "loginUser: good job ")
                    } else {
                        Log.e(TAG, "email or password is wrong")
                    }
                }.await()
        }catch (e:Exception){
            Log.e(TAG, "loginUser: error ",e )
        }

        return x?.user!= null
    }

    suspend fun typelogin(uid: String): String {
        var Type = ""

        firestore.collection("users").document(uid).get().addOnSuccessListener {

            Type = it.getString("type").toString()
            Log.d(TAG, "onActivityCreated: $Type")

        }.await()

        return Type
    }


    ////////////-------register-----///////////////////////

    fun registerUser(userName: String, emailEText: String, passWord: String,user: User) {

        auth.createUserWithEmailAndPassword(emailEText, passWord)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                 //   Toast.makeText("context","job",Toast.LENGTH_LONG)
                    Log.w(TAG, "registerUser: Succcessful ")
                    val firebaseUser = task.result?.user
                    firebaseUser?.let {
                        val ref =  firestore.collection("users").document(it.uid)
                        user.id = it.uid
                        ref.set(user)
                            .addOnSuccessListener { documentReference ->
                                Log.d(TAG, "DocumentSnapshot added succssfully")
                            }
                            .addOnFailureListener { e ->
                                Log.w(TAG, "Error adding document", e)
                            }
                    }


                } else {
                    Log.e(TAG, "there was something wrong", task.exception)
                }
            }
        val updateProfile = userProfileChangeRequest {
            displayName = userName
        }

        auth.currentUser?.updateProfile(updateProfile)

    }
    //--------image-------////////
   fun  uploadImageToStorage(filepath: Uri,id:String) {
        val product = Prodctor()
//        val pro = Prodctor()
        filepath.let {
            val date = Date()
            val ref = storageRef.child("images/image_$date.jpg")
            val uploadTask = ref.putFile(it)

            uploadTask.continueWithTask { task ->
                if (!task.isSuccessful) {
                    task.exception?.let {
                        throw it
                    }
                }
                ref.downloadUrl
            }.addOnSuccessListener {
                val imageURL = it.toString()
                product.imageURL = imageURL
                Log.d(TAG, "uploadImageToStorage: $imageURL")
                firestore.collection("product").document(id)
                    .update("imageURL", imageURL)

            }



        }
    }

    //----------sinout ---------///
    suspend fun Profile():LiveData<User> {
        return liveData {
            val userInfo=  firestore.collection("users")
                .document(Firebase.auth.currentUser?.uid!!)
              .get()
                .await()
                .toObject(User::class.java)
          //  Log.d(TAG, "Profile: $userInfo")

            if (userInfo!=null){
                emit(userInfo)
            }

        }
    }

//----------cart---------///////
//    fun cartPro():LiveData<List<Cart>>{
//        return LiveData{
//            val listdata=firestore.collection("users").whereEqualTo("caet",)
//            .get()
//                .await().toObjects(Cart::class.java)
//            emptyList(listdata)
//
//
//        }
//    }
    fun cartPro(userId:String):LiveData<List<Cart>>{
        return liveData {
            val listdata = firestore.collection("users").whereEqualTo("id",userId)
            .get()
                .await()
                .toObjects(User::class.java)
            val user = listdata.first()
            val cart = user.cart
            emit(cart)
        }
    }

    fun productDetails(userId:String):LiveData<List<Cart>>{
       return liveData {
           val listdata = firestore.collection("users").whereEqualTo("id",userId)
               .get()
               .await()
               .toObjects(User::class.java)
           val user = listdata.first()
           val cart = user.cart
           val productList = mutableListOf<Cart>()
//           cart.forEach {
//               val product = firestore.collection("product").document(it.product.id).get().await()
//                   .toObject(Cart::class.java)
//               product.let { product ->
//                   productList.add(product!!)
//               }
//
//           }
           Log.d(TAG, "productDetails: ${user.cart}")
           emit(user.cart)
       }
    }
//    fun cartProductor( cart:Cart){
//
//        val ref =firestore.collection("Cart").document()
//        cart.id = ref.id
//        ref.set(cart)
//
//    }
//    fun cart(docId:String,id:String){
//        val a = hashMapOf<String, String>(
//            "prodId" to id
//        )
//        firestore.collection("users").document(docId).update("cart",FieldValue.arrayUnion(id))
//    }

    fun cart(id:String):String {
    val a= firestore.collection("product").document().id
        firestore.collection("users").document(auth.currentUser!!.uid)
            .update("cart", FieldValue.arrayUnion(a))
            .addOnSuccessListener {
                Log.d(TAG, "dddd{$id}")
                setOf(id)
            }
        return id
    }


        //---------Rec Supermarket-----------//


    suspend fun EnentChangeListener():LiveData<List<SuperMarkt>>{
       return liveData {
           val datalist = firestore.collection("users").whereEqualTo("type", "Supermarket")

                .get()
                .await().toObjects(SuperMarkt::class.java)

           Log.d(TAG, "EnentChangeListener: $datalist")
            emit(datalist)
        }

    }
    //---------recyle Productor------------//
    suspend fun EnentChangeListenerPro():LiveData<List<Prodctor>>{
        return liveData {
            val datalist = firestore.collection("product")

                .get()
                .await().toObjects(Prodctor::class.java)

            Log.d(TAG, "EnentChangeListener: $datalist")
            emit(datalist)
        }

    }



    ////----------cart----------///
//    private  fun sumProductor() {
//        val produvtorList:MutableList <String> = (firestore.collection("users").document(f))
//    }
    ////////prductor
//    suspend fun EnentChangeListeneriAM(id: String):Prodctor? {
//
//    return    firestore.collection("product").document(id)
//
//            .get()
//            .await()
//            .toObject(Prodctor::class.java)
//
//    }


    }



