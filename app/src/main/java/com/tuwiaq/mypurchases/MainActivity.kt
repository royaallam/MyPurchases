package com.tuwiaq.mypurchases

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "MainActivity"
private const val SUPERMARKET="Supermarket"
private const val USER="user"
class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    var type = ""
    private val viewModel by lazy { ViewModelProvider(this)[MainActivityViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val navController = Navigation.findNavController(this, R.id.fragment_container)
        if(!auth.currentUser?.uid.isNullOrBlank()){
            lifecycleScope.launch{
                type = viewModel.typelogin(auth.currentUser?.uid!!)
            }.invokeOnCompletion {

                    Log.d(TAG, "onCreate: $type")
                    if (type == SUPERMARKET){
                        navController.navigate(R.id.action_loginFragment_to_barCodeScannerFragment)
//                            .navigate(R.id.action_loginFragment_to_barCodeScannerFragment)
                    }else{
                        navController.navigate(R.id.action_loginFragment_to_mapSuperMarketFragment2)

                }
            }



        }

    }
}