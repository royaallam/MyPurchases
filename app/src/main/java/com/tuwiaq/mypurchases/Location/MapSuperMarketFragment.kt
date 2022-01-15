package com.tuwiaq.mypurchases.Location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.tuwiaq.mypurchases.Barcode.barCodeScannerFragment
import com.tuwiaq.mypurchases.LoginFragment.LoginFragmentDirections


import com.tuwiaq.mypurchases.R

class MapSuperMarketFragment : Fragment() {

    private lateinit var locationBtm: Button
  //  private lateinit var navation:BottomNavigationMenuView
//  private lateinit var bottomNavigationView: BottomNavigationView
//    private lateinit var navController: NavController
//    private lateinit var bottomappbar: BottomAppBar
//    private lateinit var floatingactionbutton: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.map_supermarket, container, false)
        locationBtm = view.findViewById(R.id.location_btm)
//        bottomNavigationView=view.findViewById(R.id.bottomNavigationView)
//        bottomappbar=view.findViewById(R.id.bottomAppBar)
//        floatingactionbutton=view.findViewById(R.id.floatingActionButton)
//        navation=view.findViewById(R.id.bottomNavigationView)
        return view
    }

    override fun onStart() {
        super.onStart()
        locationBtm.setOnClickListener {
//               val fragment= barCodeScannerFragment()
//               activity?.let {
//                   it.supportFragmentManager
//                       .beginTransaction()
//                       .replace(R.id.fragment_container,fragment)
//                       .addToBackStack(null)
//                       .commit()
//               }
               val navCon = findNavController()
               val action = MapSuperMarketFragmentDirections.actionMapSMFragmentToSuperMarketViewFragment()
               navCon.navigate(action)
//           }

        }
//        navation.setOnMenuItemClickListener { menuItem ->
//            when (menuItem.itemId) {
//                R.id.search -> {
//                    // Handle search icon press
//                    true
//                }
//                R.id.more -> {
//                    // Handle more item (inside overflow menu) press
//                    true
//                }
//                else -> false
//            }
//        }

//navController()
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        navController=NavController()
//    }
//        private  fun navController(){
//
//      navController.addOnDestinationChangedListener{_,destnation,_  ->
//          when(destnation.id){
////              R.id.loginFragment ->{
////                  bottomNavigationView.visibility=View.GONE
////                  floatingactionbutton.visibility=View.GONE
////                  bottomappbar.visibility=View.GONE
//
////              }
////              R.id.registerFragment ->{
////                  bottomNavigationView.visibility=View.GONE
////                  floatingactionbutton.visibility=View.GONE
////                  bottomappbar.visibility=View.GONE
////              }
//              R.id.barCodeScannerFragment ->{
//                  bottomNavigationView.visibility=View.VISIBLE
//                  floatingactionbutton.visibility=View.VISIBLE
//                  bottomappbar.visibility=View.VISIBLE
//              }
//              R.id.listAddSuperFragment ->{
//                  bottomNavigationView.visibility=View.VISIBLE
//                  floatingactionbutton.visibility=View.VISIBLE
//                  bottomappbar.visibility=View.VISIBLE
//              }
//              R.id.mapSMFragment ->{
//                  bottomNavigationView.visibility=View.VISIBLE
//                  floatingactionbutton.visibility=View.VISIBLE
//                  bottomappbar.visibility=View.VISIBLE
//              }
//              R.id.superMarketViewFragment ->{
//                  bottomNavigationView.visibility=View.VISIBLE
//                  floatingactionbutton.visibility=View.VISIBLE
//                  bottomappbar.visibility=View.VISIBLE
//              }
//              R.id.userProdutorFragment2 ->{
//                  bottomNavigationView.visibility=View.VISIBLE
//                  floatingactionbutton.visibility=View.VISIBLE
//                  bottomappbar.visibility=View.VISIBLE
//              }
//              R.id.productorDeatiFragment ->{
//                  bottomNavigationView.visibility=View.VISIBLE
//                  floatingactionbutton.visibility=View.VISIBLE
//                  bottomappbar.visibility=View.VISIBLE
//              }
//              R.id.cartListProdutorFragment ->{
//                  bottomNavigationView.visibility=View.VISIBLE
//                  floatingactionbutton.visibility=View.VISIBLE
//                  bottomappbar.visibility=View.VISIBLE
//              }
//          }
//      }
//    }


}