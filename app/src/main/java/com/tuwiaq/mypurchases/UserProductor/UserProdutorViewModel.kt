package com.tuwiaq.mypurchases.UserProductor

import androidx.lifecycle.ViewModel
import com.tuwiaq.mypurchases.Database.RepsitoryMyPurch

class UserProdutorViewModel : ViewModel() {
    private val repsitorymypurch= RepsitoryMyPurch.get()
//    suspend fun EnentChangeListeneriAM(id: String):Prodctor?{
//      return  repsitorymypurch.EnentChangeListeneriAM(id)
//    }
}