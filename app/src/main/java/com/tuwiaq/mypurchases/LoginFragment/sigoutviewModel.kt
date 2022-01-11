package com.tuwiaq.mypurchases.LoginFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tuwiaq.mypurchases.Database.RepsitoryMyPurch
import com.tuwiaq.mypurchases.RegisterFragment.User

class sigoutviewModel:ViewModel() {
    private val repsitorymypurch= RepsitoryMyPurch.get()
    suspend fun Profile(): LiveData<User> {
        return repsitorymypurch.Profile()
    }
}