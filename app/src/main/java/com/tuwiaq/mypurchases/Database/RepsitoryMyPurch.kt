package com.tuwiaq.mypurchases.Database

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.google.api.LogDescriptor
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.*
import com.google.firestore.v1.Value
import com.tuwiaq.mypurchases.Cart.Cart
import com.tuwiaq.mypurchases.Location.MapSuperMarketFragment
import com.tuwiaq.mypurchases.R
import com.tuwiaq.mypurchases.RegisterFragment.User
import com.tuwiaq.mypurchases.Supermarket.SuperMarkt
import kotlinx.coroutines.tasks.await
import java.lang.Exception

import java.lang.IllegalStateException

private const val TAG = "RepsitoryMyPurch"

class RepsitoryMyPurch private constructor(context: Context) {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()

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
                    Log.w(TAG, "registerUser: Succcessful ")
                    // Create a new user with a first and last name
//                    val id=firestore.collection("user").document().id
//                    val cart= listOf<String>()
//                    val user = hashMapOf(
//                        "id" to id,
//                        "username" to userName,
//                        "email" to emailEText,
//                        "password" to passWord,
//                        "Type" to type,
//                       "cart" to cart
//
//                    )
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




    fun cartProductor( cart:Cart){

        val ref =firestore.collection("Cart").document()
        cart.id = ref.id
        ref.set(cart)

    }
    fun cart(docId:String,id:String){
        val a = hashMapOf<String, String>(
            "prodId" to id
        )
        firestore.collection("users").document(docId).update("cart",FieldValue.arrayUnion(id))
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


}
