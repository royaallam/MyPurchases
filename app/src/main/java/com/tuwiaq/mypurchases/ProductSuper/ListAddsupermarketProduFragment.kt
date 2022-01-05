package com.tuwiaq.mypurchases.Product

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.zxing.integration.android.IntentIntegrator.REQUEST_CODE
import com.tuwiaq.mypurchases.R
import com.tuwiaq.mypurchases.RegisterFragment.RegisterFragmentDirections
import com.tuwiaq.mypurchases.UserProductor.Prodctor
import java.util.*

private const val REQEST_CODE_IMAGE_PICK=0

private const val TAG = "ListAddSuperFragment"
class ListAddSuperFragment : Fragment() {
    private lateinit var ProdutorIma: ImageView
    private lateinit var UploadProIma:Button
    private lateinit var CodebarTv: TextView
    private lateinit var DecpationEd: EditText
    private lateinit var PriceEd: EditText
    private lateinit var AddBtn: Button
    private lateinit var btab: Button
    private lateinit var auto: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var prodctor: Prodctor
    var imageURL=""
    var filepath: Uri?=null
    private val iamgestorage = FirebaseStorage.getInstance()
        var storageRef = iamgestorage.reference
    private val args:ListAddSuperFragmentArgs by navArgs()

    private val openGallery = registerForActivityResult(
        ActivityResultContracts.GetContent()){


   }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auto = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        prodctor= Prodctor()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_addsupermarket_produ_fragment, container, false)
        Init(view)
        return view
    }

    private fun Init(view: View) {
        ProdutorIma=view.findViewById(R.id.iamge_pro)
        UploadProIma=view.findViewById(R.id.upload_imag)
        btab=view.findViewById(R.id.bta)
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
                UploadProIma.setOnClickListener{
            openGallery.launch("image/*")
            Intent(Intent.ACTION_GET_CONTENT).also {
                it.type="image/*"
               startActivityForResult(it,REQEST_CODE_IMAGE_PICK)
            }
//                    uploadImageToStorage()
        }
        btab.setOnClickListener {
            uploadImageToStorage()
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
                    val product = Prodctor(codebar, decpation, price)
//                    val user = hashMapOf(
//                        "codebar" to codebar,
//                        "decpation" to decpation,
//                        "price" to price,
//                        imageURL to ""
//                    )

// Add a new document with a generated ID
                  val a =  firestore.collection("product").document()
                          product.id = a.id

                                  a.set(product)
//                        .add(product)
//                        .addOnSuccessListener { documentReference ->
//                            Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//                        }
//                        .addOnFailureListener { e ->
//                            Log.w(TAG, "Error adding document", e)
//                        }
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

         fun  uploadImageToStorage(){
         val date = Date()
         val ref = storageRef.child("images/image_$date.jpg")
        val uploadTask = ref.putFile(filepath!!)

         uploadTask.continueWithTask { task ->
             if (!task.isSuccessful) {
                 task.exception?.let {
                     throw it
                 }
             }
             ref.downloadUrl
         }.addOnCompleteListener { task ->
             if (task.isSuccessful) {

                 val downloadUri = task.result
                 imageURL = downloadUri.toString()

                 firestore.collection("product").document()
                     .update("imageURL",downloadUri)
             } else {
                 // Handle failures
                 // ...
             }
         }
    }
    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()

    }
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
             data?.data?.let {
                filepath= it
                 ProdutorIma.setImageURI(it)
             }

        }
    }


}
