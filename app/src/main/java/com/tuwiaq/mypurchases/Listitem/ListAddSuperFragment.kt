package com.tuwiaq.mypurchases.Listitem

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tuwiaq.mypurchases.R


private const val TAG = "ListAddSuperFragment"
class ListAddSuperFragment : Fragment() {

    private lateinit var CodebarTv: TextView
    private lateinit var DecpationEd: EditText
    private lateinit var PriceEd: EditText
    private lateinit var AddBtn: Button
    private lateinit var auto: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private val args:ListAddSuperFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auto = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list_add_super, container, false)
        Init(view)
        return view
    }

    private fun Init(view: View) {
        CodebarTv = view.findViewById(R.id.codebarEt)
        DecpationEd = view.findViewById(R.id.decpationEt)
        PriceEd = view.findViewById(R.id.priceRY)
        AddBtn = view.findViewById(R.id.add_btn)
    }

    override fun onStart() {
        super.onStart()

        val currentUser = auto.currentUser
        if (currentUser != null) {
            Log.d(TAG, "hi ${currentUser.displayName}")
        }

        AddBtn.setOnClickListener {
            val codebar=CodebarTv.text.toString()
            val decpation=DecpationEd.text.toString()
            val price=PriceEd.text.toString()


            when {
                codebar.isEmpty() -> showToast("Please Enter RQ ")
                decpation.isEmpty() -> showToast("Please Enter a decpation")
                price.isEmpty() -> showToast("Please Enter a Pa")
                else -> {
                    val user = hashMapOf(
                        "codebar" to codebar,
                        "decpation" to decpation,
                        "price" to price
                    )

// Add a new document with a generated ID
                    firestore.collection("product")
                        .add(user)
                        .addOnSuccessListener { documentReference ->
                            Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error adding document", e)
                        }
                    //  val fragment=
//                 activity?.let {
//                     it.supportFragmentManager
//                         .beginTransaction()
//                         .replace(R.id.fragment_container,fragment)
//                         .addToBackStack(null)
//                         .commit()
//                 }
                }
            }
        }
        CodebarTv.text=args.codeId

    }
    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()

    }


}