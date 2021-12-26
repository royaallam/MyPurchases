package com.tuwiaq.mypurchases.RegisterFragment


import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

import com.tuwiaq.mypurchases.LoginFragment.LoginFragment
import com.tuwiaq.mypurchases.LoginFragment.LoginFragmentDirections
import com.tuwiaq.mypurchases.R


private const val TAG = "RegisterFragment"
class RegisterFragment : Fragment() {

    private lateinit var usernameET: EditText
    private lateinit var emailET: EditText
    private lateinit var passwordET: EditText
    private lateinit var repasswordET: EditText
    private lateinit var resisterBTN: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore:FirebaseFirestore


    private lateinit var viewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        firestore= FirebaseFirestore.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        Init(view)
        return view


    }

    fun Init(view: View) {
        usernameET = view.findViewById(R.id.username_edittext)
        emailET = view.findViewById(R.id.edittext_email)
        passwordET = view.findViewById(R.id.passeord_edtxt)
        repasswordET = view.findViewById(R.id.re_password_edtext)
        resisterBTN = view.findViewById(R.id.resis_btn)
    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        if (currentUser != null){
            Log.d(TAG , "hi ${currentUser.displayName}")
        }

        resisterBTN.setOnClickListener {
            val userName: String = usernameET.text.toString()
            val emaiEText: String = emailET.text.toString()
            val passWord: String = passwordET.text.toString()
            val passwordRE: String = repasswordET.text.toString()

            when {
                userName.isEmpty() -> showToast("Please Enter a userName? ")
                emaiEText.isEmpty() -> showToast("Please Enter a Email?")
                passWord.isEmpty() -> showToast("Please Enter a Password?")
                passWord != passwordRE -> showToast(" Enter a Re-password is correct")
                else -> {
                    registerUser(userName, emaiEText, passWord)
//                    val fragment=LoginFragment()
//                    activity?.let {
//                        it.supportFragmentManager
//                            .beginTransaction()
//                            .replace(R.id.fragment_container,fragment)
//                            .addToBackStack(null)
//                            .commit()
//                    }
                    val navCon = findNavController()
                    val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment3()
                    navCon.navigate(action)
                }
            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()

    }

    private fun registerUser(userName: String, emailEText: String, passWord: String) {

        auth.createUserWithEmailAndPassword(emailEText, passWord)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    showToast("good job")
                    // Create a new user with a first and last name
                    val user = hashMapOf(
                        "username" to userName,
                        "email" to emailEText,
                        "password" to passWord
                    )
                    firestore.collection("users").document(auth.currentUser?.uid!!)
                        .set(user)
                        .addOnSuccessListener { documentReference ->
                            Log.d(TAG, "DocumentSnapshot added succssfully")
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error adding document", e)
                        }

                } else {
                    Log.e(TAG, "there was something wrong", task.exception)
                }
            }
                val updateProfile = userProfileChangeRequest {
                    displayName = userName
                }

                auth.currentUser?.updateProfile(updateProfile)

            }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =ViewModelProvider(this).get(RegisterViewModel::class.java)
        auth= FirebaseAuth.getInstance()
    }





}


