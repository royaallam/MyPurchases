package com.tuwiaq.mypurchases.Productorslow

import androidx.lifecycle.ViewModel
import com.tuwiaq.mypurchases.Database.RepsitoryMyPurch

class ProductorDeatiFragmentViewmodle :ViewModel(){


    private val repsitorymypurch= RepsitoryMyPurch.get()


fun cart(id:String){
     repsitorymypurch.cart(id)
}
}