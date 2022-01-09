package com.tuwiaq.mypurchases.Location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.tuwiaq.mypurchases.Barcode.barCodeScannerFragment
import com.tuwiaq.mypurchases.LoginFragment.LoginFragmentDirections


import com.tuwiaq.mypurchases.R

class MapSuperMarketFragment : Fragment() {

    private lateinit var locationBtm: Button
  //  private lateinit var navation:BottomNavigationMenuView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.map_supermarket, container, false)
        locationBtm = view.findViewById(R.id.location_btm)
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
    }
}