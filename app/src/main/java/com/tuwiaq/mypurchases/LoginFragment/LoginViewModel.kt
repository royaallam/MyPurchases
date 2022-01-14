package com.tuwiaq.mypurchases.LoginFragment

import android.content.Context
import androidx.lifecycle.ViewModel
import com.tuwiaq.mypurchases.Database.RepsitoryMyPurch

class LoginViewModel : ViewModel() {
    private val repsitorymypurch= RepsitoryMyPurch.get()


    suspend fun loginUser(emaiETexts:String, passWords:String ):Boolean{
        return repsitorymypurch.loginUser(emaiETexts, passWords)
    }

    suspend  fun  typelogin(uid:String):String{
        return  repsitorymypurch.typelogin(uid)

    }


}