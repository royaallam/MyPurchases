package com.tuwiaq.mypurchases.LoginFragment

import android.app.Activity
import android.app.AlertDialog
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tuwiaq.mypurchases.R
import com.tuwiaq.mypurchases.RegisterFragment.RegisterFragmentDirections
import java.util.*


class SigoutFragment : Fragment() {

    private lateinit var singout:TextView
    private  val viewModelSinout: sigoutviewModel by lazy { ViewModelProvider(this).get(viewModelSinout::class.java) }
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_sigout,container,false)
        Init(view)
        return view
    }
    fun Init(view: View){
        singout=view.findViewById(R.id.logout_tV)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadLocate()
    }

    override fun onStart() {
        super.onStart()
     showChangerLang()
        singout.setOnClickListener {
            auth.signOut()
//            when(sigoutviewModel.(uid = Firebase.auth.currentUser?.uid!!)){
//                "Supermarket" -> {
//                    val navCon = findNavController()
//                    val action = R.id.action_listAddSuperFragment_to_sigoutFragment
//                    navCon.navigate(action)
//
//                }
//                "user" -> {
//                    val navCon = findNavController()
//                    val action = R.id.action_cartListProdutorFragment_to_sigoutFragment
//                    navCon.navigate(action)
//                }
//
//            }
        }
    }
    private  fun showChangerLang(){
        val listItmes= arrayOf("English","عربي","French")
        val mBuilder=AlertDialog.Builder(requireContext())
        mBuilder.setSingleChoiceItems(listItmes,-1) { dialog, which ->
            if (which == 0) {
                setLocate("en")
                resources
            } else if (which == 1) {
                setLocate("ar")
                resources
            } else if (which == 2) {
                setLocate("fr")
                resources
            }
            dialog.dismiss()
        }
        val rDialog=mBuilder.create()
       rDialog.show()
        }


    private fun setLocate(Lang:String){
    val locale=Locale(Lang)
        Locale.setDefault(locale)
       val  config=Configuration()
        config.locale=locale


        context?.resources?.updateConfiguration(config,context?.resources?.displayMetrics)
        getDefaultSharedPreferences(context).edit()
        .putString("PREF_CHANGE_LANG_KEY",Lang)
        .apply()
     }
    private fun loadLocate(){
        val shar=getDefaultSharedPreferences(context)
        val language=shar.getString("PREF_CHANGE_LANG_KEY","")!!
        setLocate(language)
    }


}