package com.tuwiaq.mypurchases.RegisterFragment


import androidx.lifecycle.ViewModel
import com.tuwiaq.mypurchases.Database.RepsitoryMyPurch



class RegisterViewModel : ViewModel() {
    private val repsitorymypurch= RepsitoryMyPurch.get()

    fun registerUser(userName: String, emailEText: String, passWord: String, type: String){
        repsitorymypurch.registerUser(userName, emailEText, passWord, type)
    }


}