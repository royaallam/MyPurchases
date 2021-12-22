package com.tuwiaq.mypurchases.LoginFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tuwiaq.mypurchases.Location.MapSuperMarketFragment
import com.tuwiaq.mypurchases.R
import com.tuwiaq.mypurchases.RegisterFragment.RegisterFragment


private const val TAG = "LoginFragment"
class LoginFragment : Fragment() {

    private lateinit var emailET:EditText
    private lateinit var passwordET:EditText
    private lateinit var signinBTN:Button
    private lateinit var resisterBTN:Button
    private lateinit var viewModel:LoginViewModel
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_login,container,false)
        init(view)
        return view
    }


    private fun init(view: View){
        emailET=view.findViewById(R.id.email_edittext)
        passwordET=view.findViewById(R.id.pass_edittext)
        signinBTN=view.findViewById(R.id.signin_btn)
        resisterBTN=view.findViewById(R.id.resister_btn)
    }
    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()

    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =ViewModelProvider(this).get(LoginViewModel::class.java)
        auth= FirebaseAuth.getInstance()
        signinBTN.setOnClickListener {
            val emaiETexts: String = emailET.text.toString()
            val passWords: String = passwordET.text.toString()
            if (TextUtils.isEmpty(emaiETexts)||TextUtils.isEmpty(passWords)){
                showToast("Enter email and password")
            }else{
                auth.signInWithEmailAndPassword(emaiETexts,passWords)
                    .addOnCompleteListener { task->if (task.isSuccessful){
                        showToast("good job")
                        val fragment=MapSuperMarketFragment()
                        activity?.let {
                            it.supportFragmentManager
                                .beginTransaction()
                                .replace(R.id.fragment_container,fragment)
                                .addToBackStack(null)
                                .commit()
//                        val navCon = findNavController()
//                        val action = LoginFragmentDirections.
//                        navCon.navigate(action)


                        }
                    }else{
                        showToast("email or password is wrong")
                        Log.e(TAG,"there was something wrong",task.exception)
                    }
                    }


            }
        }
        resisterBTN.setOnClickListener {
            val fragment=RegisterFragment()
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