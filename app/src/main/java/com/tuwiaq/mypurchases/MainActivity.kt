package com.tuwiaq.mypurchases

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.work.*
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tuwiaq.mypurchases.Cart.CartListProdutorFragment
import com.tuwiaq.mypurchases.LoginFragment.LoginFragmentDirections
import com.tuwiaq.mypurchases.LoginFragment.MyWorker
//import com.tuwiaq.mypurchases.LoginFragment.MyWorker
import com.tuwiaq.mypurchases.LoginFragment.SigoutFragment

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

private const val TAG = "MainActivity"
private const val SUPERMARKET="Supermarket"
private const val USER="user"
class MainActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var bottomappbar: BottomAppBar
    private lateinit var floatingactionbutton: FloatingActionButton


    var type = ""
    private val viewModel by lazy { ViewModelProvider(this)[MainActivityViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.fragment_container)
        auth = FirebaseAuth.getInstance()
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomappbar = findViewById(R.id.bottomAppBar)
        floatingactionbutton = findViewById(R.id.floatingActionButton)

        bottomNavigationView.background = null
        val appBarConfiguration =
           // AppBarConfiguration(setOf(R.id.mapSMFragment, R.id.sigoutFragment))
       // setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)
//        bottomNavigationView.menu.getItem(0).isEnabled=false
        val navController = Navigation.findNavController(this, R.id.fragment_container)
        if (!auth.currentUser?.uid.isNullOrBlank()) {
            lifecycleScope.launch {
                type = viewModel.typelogin(auth.currentUser?.uid!!)
            }.invokeOnCompletion {

                Log.d(TAG, "onCreate: $type")
                if (type == SUPERMARKET) {
                    navController.navigate(R.id.action_loginFragment_to_barCodeScannerFragment)
//                            .navigate(R.id.action_loginFragment_to_barCodeScannerFragment)
                } else {
                    navController.navigate(R.id.action_loginFragment_to_mapSuperMarketFragment2)

                }

            }
        }
        navController()
        flaotNav()
//        simplework()
        myWorkManger()

    }

    private fun navController() {
        navController.addOnDestinationChangedListener { _, destnation, _ ->
            when (destnation.id) {
                R.id.loginFragment -> {
                    bottomNavigationView.visibility = View.GONE
                    floatingactionbutton.visibility = View.GONE
                    bottomappbar.visibility = View.GONE

                }
                R.id.registerFragment -> {
                    bottomNavigationView.visibility = View.GONE
                    floatingactionbutton.visibility = View.GONE
                    bottomappbar.visibility = View.GONE
                }
                R.id.barCodeScannerFragment -> {
                    bottomNavigationView.visibility = View.GONE
                    floatingactionbutton.visibility = View.GONE
                    bottomappbar.visibility = View.GONE
                }
                R.id.listAddSuperFragment -> {
                    bottomNavigationView.visibility = View.GONE
                    floatingactionbutton.visibility = View.GONE
                    bottomappbar.visibility = View.GONE
                }
                R.id.mapSMFragment -> {
                    bottomNavigationView.visibility = View.VISIBLE
                    floatingactionbutton.visibility = View.VISIBLE
                    bottomappbar.visibility = View.VISIBLE
                }
                R.id.superMarketViewFragment -> {
                    bottomNavigationView.visibility = View.VISIBLE
                    floatingactionbutton.visibility = View.VISIBLE
                    bottomappbar.visibility = View.VISIBLE
                }
                R.id.userProdutorFragment2 -> {
                    bottomNavigationView.visibility = View.VISIBLE
                    floatingactionbutton.visibility = View.VISIBLE
                    bottomappbar.visibility = View.VISIBLE
                }
                R.id.productorDeatiFragment -> {
                    bottomNavigationView.visibility = View.VISIBLE
                    floatingactionbutton.visibility = View.VISIBLE
                    bottomappbar.visibility = View.VISIBLE
                }
                R.id.cartListProdutorFragment -> {
                    bottomNavigationView.visibility = View.VISIBLE
                    floatingactionbutton.visibility = View.VISIBLE
                    bottomappbar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun flaotNav(){
        floatingactionbutton.setOnClickListener {
            navController.navigate(R.id.cartListProdutorFragment)
        }
    }
//    private fun simplework(){
//        val mRequest: WorkRequest = OneTimeWorkRequestBuilder<MyWorker>()
//            .build()
//        WorkManager.getInstance(this)
//            .enqueue(mRequest)
//    }
    private fun myWorkManger(){
        val constraints=Constraints.Builder()
            .setRequiresCharging(false)
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .setRequiresCharging(false)
            .setRequiresBatteryNotLow(true)
            .build()

    val myRequest= PeriodicWorkRequest .Builder(
        MyWorker::class.java,15,TimeUnit.MINUTES
    ).setConstraints(constraints)
        .build()
    WorkManager.getInstance(this)
        .enqueueUniquePeriodicWork(
            "my_id",
            ExistingPeriodicWorkPolicy.KEEP,
            myRequest

        )
    }

}

