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

class mapSuperMarketFragment : Fragment() {

    private lateinit var locationBtm: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.map_supermarket, container, false)
        locationBtm = view.findViewById(R.id.location_btm)
        return view
    }

    override fun onStart() {
        super.onStart()
        locationBtm.setOnClickListener {
               val navCon = findNavController()
               val action = mapSuperMarketFragmentDirections.actionMapSMFragmentToSuperMarketViewFragment()
               navCon.navigate(action)
        }

        }



}