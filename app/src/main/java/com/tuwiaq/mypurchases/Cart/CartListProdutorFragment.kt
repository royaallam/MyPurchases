package com.tuwiaq.mypurchases.Cart


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.tuwiaq.mypurchases.LoginFragment.LoginViewModel
import com.tuwiaq.mypurchases.R
import com.tuwiaq.mypurchases.RegisterFragment.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


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
    inner class CartHolder(view: View):RecyclerView.ViewHolder(view){
    //   val  nameProdutor:TextView=itemView.findViewById(R.id.name_productoe)
   //   val   princeProdutor:TextView=itemView.findViewById(R.id.prince_productor)
    }

    inner class CartAdapter(private val cartList:ArrayList<Cart>):
        RecyclerView.Adapter<CartHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder {
           val view=layoutInflater.inflate(R.layout.cart_list_produtor_fragment,parent,false)
            return CartHolder(view)
        }

        override fun onBindViewHolder(holder: CartHolder, position: Int) {
            val cartU = cartList[position]

//            holder.nameProdutor.text=cartU.productor
//            holder.princeProdutor.text=
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
