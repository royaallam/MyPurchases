package com.tuwiaq.mypurchases.LoginFragment

import androidx.lifecycle.ViewModel
import com.tuwiaq.mypurchases.Database.RepsitoryMyPurch

class sigoutviewModel:ViewModel() {
    private val repsitorymypurch= RepsitoryMyPurch.get()

//    suspend  fun  typelogin(uid:String):String{
//        return  repsitorymypurch.typelogin(uid)
//
//    }
}