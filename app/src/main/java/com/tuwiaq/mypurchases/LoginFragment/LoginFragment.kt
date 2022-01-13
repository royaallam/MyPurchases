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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tuwiaq.mypurchases.Location.MapSuperMarketFragment
import com.tuwiaq.mypurchases.R
import com.tuwiaq.mypurchases.RegisterFragment.RegisterFragment
import com.tuwiaq.mypurchases.RegisterFragment.RegisterFragmentDirections
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlin.reflect.typeOf


private const val TAG = "LoginFragment"

class LoginFragment : Fragment() {

    private lateinit var emailET: EditText
    private lateinit var passwordET: EditText
    private lateinit var signinBTN: Button
    private lateinit var resisterBTN: Button
    private  val viewModel: LoginViewModel by lazy { ViewModelProvider(this).get(LoginViewModel::class.java) }
    var type = ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        init(view)
        return view
    }


    private fun init(view: View) {
        emailET = view.findViewById(R.id.email_edittext)
        passwordET = view.findViewById(R.id.pass_edittext)
        signinBTN = view.findViewById(R.id.signin_btn)
        resisterBTN = view.findViewById(R.id.resister_btn)
    }

    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        signinBTN.setOnClickListener {
            val emaiETexts: String = emailET.text.toString()
            val passWords: String = passwordET.text.toString()
            if (TextUtils.isEmpty(emaiETexts) || TextUtils.isEmpty(passWords)) {
                showToast("Enter email and password")
            } else {
                lifecycleScope.launch {
                    if (viewModel.loginUser(emaiETexts, passWords)){
                        Log.d(TAG, "onActivityCreated: scuuell")
                        when(viewModel.typelogin(uid = Firebase.auth.currentUser?.uid!!)){
                            "Supermarket" -> {
                                val navCon = findNavController()
                                val action = LoginFragmentDirections.actionLoginFragmentToBarCodeScannerFragment()
                                navCon.navigate(action)
                                Log.d(TAG, "onActivityCreated: scuuell3")

                            }
                            "user" -> {
                                val navCon = findNavController()
                                val action = LoginFragmentDirections.actionLoginFragmentToMapSuperMarketFragment2()
                                navCon.navigate(action)
                                Log.d(TAG, "onActivityCreated: scuuell2")
                            }
                            else -> {
                                Log.d(
                                    TAG,
                                    "onActivityCreated: ${viewModel.typelogin(uid = Firebase.auth.currentUser?.uid!!)} ")}

                        }

                    }



                }


            }
        }




        resisterBTN.setOnClickListener {

            val navCon = findNavController()
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment3()
            navCon.navigate(action)

        }

    }
//  private  fun simpleWork(){
//        val mRequest:WorkRequest= OneTimeWorkRequestBuilder<MyWorker>()
//            .build()
//               WorkManager.getInstance(requireContext())
//                   .enqueue(mRequest)
//    }
}

