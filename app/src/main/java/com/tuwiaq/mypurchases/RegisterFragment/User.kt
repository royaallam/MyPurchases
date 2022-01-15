package com.tuwiaq.mypurchases.RegisterFragment

import com.tuwiaq.mypurchases.Cart.Cart

class User(
    val userName:String="",
    val emailEText:String="",
    val type:String="",
    val cart:List<Cart> = listOf(),
    var id:String="")

