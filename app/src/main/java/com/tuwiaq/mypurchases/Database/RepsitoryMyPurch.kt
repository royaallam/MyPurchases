package com.tuwiaq.mypurchases.Database

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.lifecycle.LiveData
import com.google.api.LogDescriptor
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.*
import com.google.firestore.v1.Value
import com.tuwiaq.mypurchases.Cart.Cart
import com.tuwiaq.mypurchases.Location.MapSuperMarketFragment
import com.tuwiaq.mypurchases.R
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

    //////////////////-----------//////////////////////
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
        var Tpye = ""

        firestore.collection("users").document(uid).get().addOnSuccessListener {

            Tpye = it.getString("Type").toString()
            Log.d(TAG, "onActivityCreated: $Tpye")

        }.await()

        return Tpye
    }


    ////////////------------///////////////////////

    fun registerUser(userName: String, emailEText: String, passWord: String, type: String) {

        auth.createUserWithEmailAndPassword(emailEText, passWord)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.w(TAG, "registerUser: Succcessful ")
                    // Create a new user with a first and last name

//                    val cart= listOf<String>()
                    val user = hashMapOf(
                        "username" to userName,
                        "email" to emailEText,
                        "password" to passWord,
                        "Type" to type
//                       "cart" to cart

                    )
                    firestore.collection("users").document(auth.currentUser?.uid!!)
                        .set(user)
                        .addOnSuccessListener { documentReference ->
                            Log.d(TAG, "DocumentSnapshot added succssfully")
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error adding document", e)
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


//   fun type(){
//       firestore.collection("users").get().addOnSuccessListener {
//           it.forEach{
//             val  Tpye = it.getString("Type").toString()
//               Log.d(com.tuwiaq.mypurchases.LoginFragment.TAG, "onActivityCreated: $Tpye")
//
//           }
//       }
//       if (Tpye == "Supermarket") {
//           navCon.navigate(R.id.action_loginFragment_to_barCodeScannerFragment)
//           Log.d(com.tuwiaq.mypurchases.LoginFragment.TAG, "onActivityCreated: up")
//       } else if(Tpye == "user"){
//           navCon.navigate(R.id.action_loginFragment_to_mapSuperMarketFragment2)
//       }
//   }


    fun cartProductor( cart:Cart){

        val ref =firestore.collection("Cart").document()
        cart.id = ref.id
        ref.set(cart)

    }
    fun cart(docId:String,id:String){
        firestore.collection("users").document(docId).update("cart",FieldValue.arrayUnion(id))
    }


}
