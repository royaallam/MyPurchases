package com.tuwiaq.mypurchases.Productorslow

import androidx.lifecycle.ViewModel
import com.tuwiaq.mypurchases.Cart.Cart
import com.tuwiaq.mypurchases.Database.RepsitoryMyPurch

class ProductorDeatiFragmentViewmodle :ViewModel(){


    private val repsitorymypurch= RepsitoryMyPurch.get()

//    fun cartProductor( cart: Cart){
//        repsitorymypurch.cartProductor(cart)
//    }
//    fun cart(docId:String,id:String){
//
//        repsitorymypurch.cart(docId,id)
//    }
fun cart(id:String){
     repsitorymypurch.cart(id)
}
}