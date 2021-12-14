package com.tuwiaq.mypurchases

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tuwiaq.mypurchases.LoginFragment.LoginFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val currentFragment=supportFragmentManager
//            .findFragmentById(R.id.fragment_container)
//        if(currentFragment==null){
//            val fragment=LoginFragment()
//            supportFragmentManager
//                .beginTransaction()
//                .add(R.id.fragment_container,fragment)
//                .commit()
//        }
//        val nav=findNavController(R.id.fragment_container)
//        val bottomNavigationView:BottomNavigationView=findViewById(R.id.)
    }
}