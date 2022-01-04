package com.tuwiaq.mypurchases.Database

import android.app.Application


class MyPurchases:Application() {
    override fun onCreate() {
        super.onCreate()
     RepsitoryMyPurch.initialize(this)
    }
}
