package com.tuwiaq.mypurchases.Productorslow



import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.tuwiaq.mypurchases.Cart.Cart
import com.tuwiaq.mypurchases.Cart.CartListProdutorViewModel
import com.tuwiaq.mypurchases.R
import com.tuwiaq.mypurchases.RegisterFragment.RegisterFragmentDirections
import com.tuwiaq.mypurchases.RegisterFragment.User
import com.tuwiaq.mypurchases.UserProductor.Prodctor


private const val TAG = "ProductorDeatiFragment"
class ProductorDeatiFragment : Fragment() {


    private val args: ProductorDeatiFragmentArgs by navArgs()
    private lateinit var imagePro:ImageView
    private lateinit var barCodePro:TextView
    private lateinit var decrpationPrp:TextView
    private lateinit var numPro:TextView
    private lateinit var addPro:ImageView
    private lateinit var subPro:ImageView
    private lateinit var addCart:Button
    private lateinit var auto: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var preBut: Button
    private lateinit var prodctor: Prodctor
    private lateinit var user: User

    private val proDecViewmodel:ProductorDeatiFragmentViewmodle by lazy { ViewModelProvider(this).get(ProductorDeatiFragmentViewmodle::class.java) }

     var count=0
     private var productId = ""
    var prodId = ""
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.descrpation_product,container,false)
        Init(view)
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         firestore= FirebaseFirestore.getInstance()
        prodctor= Prodctor()
        user= User()
        productId = args.id

    }
   private fun Init(view: View){
        imagePro=view.findViewById(R.id.image_produtor)
        barCodePro=view.findViewById(R.id.barcode_pro)
        decrpationPrp=view.findViewById(R.id.decripa_pro)
        numPro=view.findViewById(R.id.qutity_prodctor)
       addPro=view.findViewById(R.id.add_pro)
       subPro=view.findViewById(R.id.sub_prod)
      addCart=view.findViewById(R.id.add_cart)
       preBut=view.findViewById(R.id.profel)
    }

    override fun onStart() {
        super.onStart()

//        firestore.collection("product").document(uid).get()
        firestore.collection("users")
                        .get().addOnSuccessListener { q ->
                q.documents.forEach {
                               prodId = it.getString("id").toString()
                    Log.d(TAG, "id: $prodId")

            }
            }
//                        .addOnSuccessListener { documentReference ->
//                            Log.d(TAG, "DocumentSnapshot added succssfully")
//                        }
//                        .addOnFailureListener { e ->
//                            Log.w(TAG, "Error adding document", e)

        firestore.collection("product").document(productId)
            .get().addOnSuccessListener {
                barCodePro.text = it.getString("codebar")
                decrpationPrp.text=it.getString("decpation")
            }
        numPro.text= count.toString()
        addPro.setOnClickListener {
            count++
            numPro.text= count.toString()

        }

        subPro.setOnClickListener {
            count--
            numPro.text= count.toString()

        }
        preBut.setOnClickListener {
            val navCon = findNavController()
            val action = ProductorDeatiFragmentDirections.actionProductorDeatiFragmentToSigoutFragment()
            navCon.navigate(action)
        }
        addCart.setOnClickListener {
            Toast.makeText(requireContext(),"addcart", Toast.LENGTH_LONG).show()
//            prodctor.id= user.cart.toString()
    firestore.collection("product").document()
    firestore.collection("users").document(auth.currentUser!!.uid)
        .update("cart", FieldValue.arrayUnion(id))
        .addOnSuccessListener {

        }



    val navCon = findNavController()
            val action = ProductorDeatiFragmentDirections.actionProductorDeatiFragmentToCartListProdutorFragment()
            navCon.navigate(action)
        }

    }

}