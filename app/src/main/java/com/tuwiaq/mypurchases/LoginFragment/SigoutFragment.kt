package com.tuwiaq.mypurchases.LoginFragment

import android.app.AlertDialog
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.google.firebase.auth.FirebaseAuth
import com.tuwiaq.mypurchases.MainActivity
import com.tuwiaq.mypurchases.R
import com.tuwiaq.mypurchases.RegisterFragment.User
import kotlinx.coroutines.launch
import java.util.*

private const val TAG = "SigoutFragment"
class SigoutFragment : Fragment() {

    private lateinit var singout:TextView
    private lateinit var auth: FirebaseAuth
    private lateinit var user: User
    private lateinit var usertV:TextView
    private lateinit var emailtv:TextView
    private lateinit var choseLang:Button
    val viewModel:sigoutviewModel by lazy { ViewModelProvider(this).get(sigoutviewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         auth = FirebaseAuth.getInstance()
        user= User()
        loadLocate()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_sigout,container,false)
        Init(view)
        lifecycleScope.launch {

            viewModel.Profile().observe(
                viewLifecycleOwner
            ) {
                it?.let { user ->
                    usertV.text = user.userName
                    emailtv.text= user.emailEText
                    Log.d(TAG, "onCreateView: ${user.userName}")
                }
            }

        }
        singout.setOnClickListener {
           auth.signOut()
            findNavController().popBackStack()
        }


  //      loadLocate()
        return view
    }
    fun Init(view: View){
        singout=view.findViewById(R.id.logout_btm)
        usertV=view.findViewById(R.id.user_name)
        emailtv=view.findViewById(R.id.email_tv)
        choseLang=view.findViewById(R.id.chose_lan)


    }

    override fun onStart() {
        super.onStart()
        choseLang.setOnClickListener {
            showChangerLang()
        }


    }
    private  fun showChangerLang(){
        val listItmes= arrayOf("English","عربي","French")
        val mBuilder=AlertDialog.Builder(requireContext())
        mBuilder.setSingleChoiceItems(listItmes,-1) { dialog, which ->
            if (which == 0) {
                setLocate("en")
                resources
                startActivity(Intent(requireContext(),MainActivity::class.java))
           activity?.finish()
            } else if (which == 1) {
                setLocate("ar")
                resources
                startActivity(Intent(requireContext(),MainActivity::class.java))
            activity?.finish()
            } else if (which == 2) {
                setLocate("fr")
                resources
                startActivity(Intent(requireContext(),MainActivity::class.java))
            activity?.finish()
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