package com.tuwiaq.mypurchases.UserProductor

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tuwiaq.mypurchases.Database.RepsitoryMyPurch
import com.tuwiaq.mypurchases.Supermarket.SuperMarkt

class UserProdutorViewModel : ViewModel() {
    private val repsitorymypurch= RepsitoryMyPurch.get()

    suspend fun EnentChangeListenerPro() : LiveData<List<Prodctor>> {
        return  repsitorymypurch.EnentChangeListenerPro()
    }
}