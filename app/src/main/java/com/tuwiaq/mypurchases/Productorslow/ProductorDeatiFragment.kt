package com.tuwiaq.mypurchases.Productorslow



import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.tuwiaq.mypurchases.Cart.Cart
import com.tuwiaq.mypurchases.R
import com.tuwiaq.mypurchases.RegisterFragment.User
import com.tuwiaq.mypurchases.UserProductor.Prodctor
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


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
    private var prodctor: Prodctor? = null
    private lateinit var user: User
    var filepath: Uri? = null

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

    }

    override fun onStart() {
        super.onStart()


        firestore.collection("users")
                        .get().addOnSuccessListener { q ->
                q.documents.forEach {
                               prodId = it.getString("id").toString()
                    Log.d(TAG, "id: $prodId")

            }
            }


        firestore.collection("product").document(productId)
            .get().addOnSuccessListener {
                prodctor = it.toObject(Prodctor::class.java)
                barCodePro.text = it.getString("codebar")
                decrpationPrp.text=it.getString("decpation")
                val url = it.getString("imageURL")
                imagePro.load(url)







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

        addCart.setOnClickListener {
            if(count != 0){
            Toast.makeText(requireContext(),"addcart", Toast.LENGTH_LONG).show()
//            prodctor.id= user.cart.toString()
//    firestore.collection("product").document()
            val cart = Cart(product = prodctor!!, count = count)
            lifecycleScope.launch {
                val user =
                    firestore.collection("users").document(auth.currentUser!!.uid).get().await()
                        .toObject(User::class.java)
                var contain = false

                user?.cart?.forEach {
                    if (it.product.id == cart.product.id) {
                        cart.count += it.count
                        contain = true
                    }
                }
                if (contain) {
                    val newCart = user?.cart?.filter {
                        it.product.id != cart.product.id
                    }?.toMutableList()
                    newCart?.add(cart)
                    firestore.collection("users").document(auth.currentUser!!.uid)
                        .update("cart", newCart)
                } else {
                    firestore.collection("users").document(auth.currentUser!!.uid)
                        .update("cart", FieldValue.arrayUnion(cart))
                }
            }
                val navCon = findNavController()
                val action = ProductorDeatiFragmentDirections.actionProductorDeatiFragmentToCartListProdutorFragment()
                navCon.navigate(action)
            }
            }

        }


    }