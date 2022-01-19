package com.tuwiaq.mypurchases.Product

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tuwiaq.mypurchases.ProductSuper.ListAddsupermarketProduViewModel
import com.tuwiaq.mypurchases.R
import com.tuwiaq.mypurchases.UserProductor.Prodctor

private const val REQEST_CODE_IMAGE_PICK=0

private const val TAG = "ListAddSuperFragment"
class ListAddSuperFragment : Fragment() {
    private lateinit var ProdutorIma: ImageView
    private lateinit var UploadProIma: Button
    private lateinit var CodebarTv: TextView
    private lateinit var DecpationEd: EditText
    private lateinit var PriceEd: EditText
    private lateinit var AddBtn: Button
    private lateinit var selectImage: ImageView
    val addProductorsuperViewModel by lazy { ViewModelProvider(
        this).get(ListAddsupermarketProduViewModel::class.java) }
    private lateinit var auto: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var prodctor: Prodctor
    var filepath: Uri? = null
    private val args: ListAddSuperFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auto = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
        prodctor = Prodctor()

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
        ProdutorIma = view.findViewById(R.id.iamge_pro)
        UploadProIma = view.findViewById(R.id.uplaed_image)
        selectImage = view.findViewById(R.id.select_image)
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
        selectImage.setOnClickListener {


            selectImage()
        }



        AddBtn.setOnClickListener {
            Toast.makeText(requireContext(),"add prodtor",Toast.LENGTH_LONG).show()
            val codebar = CodebarTv.text.toString()
            val decpation = DecpationEd.text.toString()
            val price = PriceEd.text.toString()


            when {
                codebar.isEmpty() -> showToast("Please Enter RQ ")
                decpation.isEmpty() -> showToast("Please Enter a decpation")
                price.isEmpty() -> showToast("Please Enter a Pa")
                else -> {
                    val product = Prodctor(codebar, decpation, price)


                    //Add a new document with a generated ID

                    val a = firestore.collection("product").document()
                    product.id = a.id



                    a.set(product)

                    filepath?.let {
                        addProductorsuperViewModel.uploadImageToStorage(it,a.id)
                    }
                }
            }
        }
        CodebarTv.text = args.codeId


    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, REQEST_CODE_IMAGE_PICK)
    }
    private fun showToast(msg: String) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "onActivityResult111: $filepath")
        if (requestCode == REQEST_CODE_IMAGE_PICK ) {
            filepath = data?.data ?: return
             ProdutorIma.load(filepath)
            Log.d(TAG, "onActivityResult: $filepath")
        }
            }
    }