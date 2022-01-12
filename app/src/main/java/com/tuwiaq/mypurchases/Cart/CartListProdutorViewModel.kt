package com.tuwiaq.mypurchases.Cart

import androidx.lifecycle.ViewModel
import com.tuwiaq.mypurchases.Database.RepsitoryMyPurch

class CartListProdutorViewModel : ViewModel() {
    private val repsitorymypurch= RepsitoryMyPurch.get()

//    fun cartProductor( cart:Cart){
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