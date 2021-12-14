package com.tuwiaq.mypurchases.Listitem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.tuwiaq.mypurchases.R


class ListAddSuperFragment : Fragment() {

    private lateinit var CodebarEd:EditText
    private lateinit var DecpationEd:EditText
    private lateinit var PriceEd:EditText
    private lateinit var AddBtn:Button
    val Firebase
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_list_add_super,container,false)
       Init(view)
        return  view
    }

    private fun Init(view: View){
     CodebarEd=view.findViewById(R.id.codebarEt)
        DecpationEd=view.findViewById(R.id.decpationEt)
        PriceEd=view.findViewById(R.id.priceRY)
        AddBtn=view.findViewById(R.id.add_btn)
    }


}