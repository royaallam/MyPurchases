package com.tuwiaq.mypurchases.Cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.tuwiaq.mypurchases.Productorslow.ProductorDeatiFragmentArgs
import com.tuwiaq.mypurchases.UserProductor.Prodctor

//    var id:String?=null,
//    var descipation:String?=null,
//    var prines:String?=null,
//    var quantiiy:Int=0,
//    var totalProd:Int=0
class Cart:Fragment() {
    private lateinit var prodctor: Prodctor
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auto: FirebaseAuth
    private var productId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prodctor= Prodctor()



    }

    override fun onStart() {
        super.onStart()
        firestore.collection("users").document(auto.currentUser!!.uid)
            .update("cart", FieldValue.arrayUnion(productId))
        productId.forEach {
            prodctor.decpation
            prodctor.price

        }
    }
}

