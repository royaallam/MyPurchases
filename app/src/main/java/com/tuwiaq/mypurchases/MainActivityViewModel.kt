package com.tuwiaq.mypurchases

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.tuwiaq.mypurchases.Database.RepsitoryMyPurch

class MainActivityViewModel: ViewModel() {

    private val repsitorymypurch= RepsitoryMyPurch.get()
    suspend fun typelogin(uid: String): String{
        return repsitorymypurch.typelogin(uid)
    }


}