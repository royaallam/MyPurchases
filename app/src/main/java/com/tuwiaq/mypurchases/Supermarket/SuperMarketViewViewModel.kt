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

//    private var valueLiveData =MutableLiveData<String>()
//    var userInfo:LiveData<ArrayList<SuperMarkt>> =
//        repsitorymypurch.EnentChangeListener()
//    }


//     fun EnentChangeListener() {
//        repsitorymypurch.EnentChangeListener()
//}
}