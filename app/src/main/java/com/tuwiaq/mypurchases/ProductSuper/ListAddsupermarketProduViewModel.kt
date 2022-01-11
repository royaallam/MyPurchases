package com.tuwiaq.mypurchases.ProductSuper

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tuwiaq.mypurchases.Database.RepsitoryMyPurch
import kotlinx.coroutines.launch

class ListAddsupermarketProduViewModel : ViewModel() {


    private val repsitorymypurch= RepsitoryMyPurch.get()

//       fun productorId(id:String){
//           repsitorymypurch.productorId(id)
//       }

     fun  uploadImageToStorage(filepath:Uri,id:String){
        viewModelScope.launch {

            repsitorymypurch.uploadImageToStorage(filepath,id)

        }
    }
}