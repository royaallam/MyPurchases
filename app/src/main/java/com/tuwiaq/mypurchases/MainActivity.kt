package com.tuwiaq.mypurchases

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tuwiaq.mypurchases.LoginFragment.LoginFragment
import com.tuwiaq.mypurchases.RegisterFragment.RegisterFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val currentFragment=supportFragmentManager
            .findFragmentById(R.id.fragment_container)
        if(currentFragment==null){
            val fragment=LoginFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container,fragment)
                .commit()
        }
    }
}