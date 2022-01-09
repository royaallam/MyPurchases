package com.tuwiaq.mypurchases.Supermarket

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.*
import com.google.firebase.firestore.model.Values
import com.tuwiaq.mypurchases.Database.RepsitoryMyPurch


class SuperMarketViewViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val repsitorymypurch= RepsitoryMyPurch.get()



    suspend fun EnentChangeListener() : LiveData<List<SuperMarkt>> {
      return  repsitorymypurch.EnentChangeListener()
}
}