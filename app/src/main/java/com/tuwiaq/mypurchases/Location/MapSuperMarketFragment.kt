package com.tuwiaq.mypurchases.Location

import ScanerBarFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

import com.tuwiaq.mypurchases.R

class MapSuperMarketFragment : Fragment() {

    private lateinit var locationBtm:Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.map_supermarket, container, false)
      locationBtm=view.findViewById(R.id.location_btm)
        return view
    }

    override fun onStart() {
        super.onStart()
           locationBtm.setOnClickListener {
               val fragment= ScanerBarFragment()
               activity?.let {
                   it.supportFragmentManager
                       .beginTransaction()
                       .replace(R.id.fragment_container,fragment)
                       .addToBackStack(null)
                       .commit()
               }
           }

    }
}