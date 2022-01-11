package com.tuwiaq.mypurchases.Cart


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.tuwiaq.mypurchases.R


class CartListProdutorFragment : Fragment() {
    private lateinit var cart_list_reclyec:RecyclerView
    private  val cartViewModel: CartListProdutorViewModel by lazy { ViewModelProvider(this).get(CartListProdutorViewModel::class.java) }
    private lateinit var firestore: FirebaseFirestore
    private lateinit var cart:Cart



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.cart_list_produtor_fragment,container,false)
        Init(view)
        return view
    }
    fun Init(view: View){
        cart_list_reclyec=view.findViewById(R.id.cart_user_recycler)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firestore= FirebaseFirestore.getInstance()
    }

    override fun onStart() {
        super.onStart()
//cartviewModel.cartProductor(cart)
    }
    inner class CartHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var iamgeProdutor: ImageView?= null
        var decrProdutor: TextView?=null
        var  addProductor:ImageView?=null
        var   subProductor:ImageView?=null
        var prince:TextView?=null
        var quantity:TextView?=null


      init {
          iamgeProdutor=itemView.findViewById(R.id.image_prod) as ImageView
        decrProdutor= itemView.findViewById(R.id.name_productoe)as TextView
        addProductor=itemView.findViewById(R.id.add_imag) as ImageView
          subProductor=itemView.findViewById(R.id.sub_imag)as ImageView
          prince=itemView.findViewById(R.id.prince_imag) as TextView
          quantity=itemView.findViewById(R.id.qutit_image) as TextView
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
            holder.decrProdutor!!.text=StringBuilder().append(cartU)
            holder.quantity!!.text=StringBuilder().append(cartU)
            holder.prince!!.text=StringBuilder().append(cartU)
        }

        override fun getItemCount(): Int =cartList.size
    }

//    private  fun CartHolder.sumProductor() {
//        lifecycleScope.launch(Dispatchers.IO){
//            val productorList:MutableList<String> =
//            (firestore.collection("users").document(Firebase.auth.currentUser?.uid!!)
//                .get()
//                .await()
//                .toObject(User::class.java)
//                ?.cart?: emptyList() as MutableList<String>
//            productorList += productor.id
//                    firestore.collection("users.").document(Firebase.auth.currentUser?.uid!!)
//                    updata("cart",productorList)
//        }
    }
