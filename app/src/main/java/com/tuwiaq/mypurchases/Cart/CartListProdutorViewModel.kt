package com.tuwiaq.mypurchases.Cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.google.firebase.firestore.auth.User
import com.tuwiaq.mypurchases.Database.RepsitoryMyPurch
import com.tuwiaq.mypurchases.UserProductor.Prodctor
import kotlinx.coroutines.tasks.await

class CartListProdutorViewModel : ViewModel() {
    private val repsitorymypurch= RepsitoryMyPurch.get()

//    fun cartProductor( cart:Cart){
//        repsitorymypurch.cartProductor(cart)
//    }
//    fun cart(docId:String,id:String){
//
//        repsitorymypurch.cart(docId,id)
//    }
//fun cart(id:String){
//    repsitorymypurch.cart(prodctor = Prodctor(id))
//}
//fun cartPro(cart:Cart): LiveData<List<User>> {
//    return repsitorymypurch.cartPro(cart)
//}

}