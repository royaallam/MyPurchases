package com.tuwiaq.mypurchases.Cart

import com.tuwiaq.mypurchases.UserProductor.Prodctor
data class Cart(
    var product: Prodctor = Prodctor(),
    var count: Int = 0
)




