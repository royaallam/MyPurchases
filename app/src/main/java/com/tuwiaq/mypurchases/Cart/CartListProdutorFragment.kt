package com.tuwiaq.mypurchases.Cart


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.tuwiaq.mypurchases.LoginFragment.LoginFragmentDirections
import com.tuwiaq.mypurchases.R
import com.tuwiaq.mypurchases.RegisterFragment.User
import com.tuwiaq.mypurchases.UserProductor.Prodctor
import com.tuwiaq.mypurchases.UserProductor.UserProdutorFragmentDirections
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

private const val TAG = "CartListProdutorFragmen"
class CartListProdutorFragment : Fragment() {
    private lateinit var cart_list_reclyec:RecyclerView
    private  val cartViewModel: CartListProdutorViewModel by lazy { ViewModelProvider(this).get(CartListProdutorViewModel::class.java) }
    private lateinit var firestore: FirebaseFirestore

    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.cart_list_produtor_fragment,container,false)
        Init(view)

        cart_list_reclyec.layoutManager=LinearLayoutManager(context)
        cart_list_reclyec.hasFixedSize()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userId = Firebase.auth.currentUser!!.uid
        lifecycleScope.launch {
            cartViewModel.productDetails(userId).observe(viewLifecycleOwner,

                Observer { list ->

                        updateUI(list)


                    Log.e(TAG,"the cart: $list")
                })
        }
    }
    fun Init(view: View) {
        cart_list_reclyec = view.findViewById(R.id.cart_user_recycler)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firestore= FirebaseFirestore.getInstance()

    }

    override fun onStart() {
        super.onStart()
    }
    private fun updateUI(cartRms: List<Cart>) {
        val CartAdapter = CartAdapter(cartRms)
        cart_list_reclyec.adapter=CartAdapter
    }
    inner class CartHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var iamgeProdutor: ImageView = itemView.findViewById(R.id.image_prod)
        var decrProdutor: TextView = itemView.findViewById(R.id.name_productoe)
        var prince: TextView = itemView.findViewById(R.id.prince_pro)
        val quantity: TextView = itemView.findViewById(R.id.upprince_imag)
        val deleProdctor:ImageView=itemView.findViewById(R.id.delet_image)
        val idpro:TextView=itemView.findViewById(R.id.id_pro)
        val editPro:ImageView=itemView.findViewById(R.id.efit_pro)
        private lateinit var titlca: Cart
        fun bind(cart: Cart) {
            titlca = cart
            iamgeProdutor?.load(cart.product.imageURL)

            decrProdutor.text = cart.product.decpation
            idpro.text=cart.product.codebar
            prince.text=cart.product.price
            quantity.text = (cart.product.price.toDouble() * cart.count).toString()
            editPro.setOnClickListener {
                findNavController().popBackStack()
            }
            deleProdctor.setOnClickListener {
                lifecycleScope.launch {
                    val user =
                        firestore.collection("users").document(auth.currentUser!!.uid).get().await()
                            .toObject(User::class.java)
                        val newCart = user?.cart?.filter {
                            it.product.id != titlca.product.id
                        }

                       firestore.collection("users").document(auth.currentUser!!.uid)
                            .update("cart", newCart)
                    cart_list_reclyec.adapter = CartAdapter(newCart ?: emptyList())

                }
            }
        }


    }

    inner class CartAdapter(
        private val cartList:List<Cart>):
        RecyclerView.Adapter<CartHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder {
           val view=layoutInflater.inflate(R.layout.cart_productor_item,parent,false)
            return CartHolder(view)
        }

        override fun onBindViewHolder(holder: CartHolder, position: Int) {
            val cartU = cartList[position]

            holder.bind(cartU)
        }


        override fun getItemCount(): Int =cartList.size
    }


    }
