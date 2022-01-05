package com.tuwiaq.mypurchases.UserProductor


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tuwiaq.mypurchases.R


class UserProdutorFragment : Fragment() {
    private lateinit var list_user_add: RecyclerView
    var firestore= Firebase.firestore
    val ListaddUser by lazy { ViewModelProvider(this).get(UserProdutorViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_producta_user, container, false)
        Init(view)
        list_user_add.layoutManager = LinearLayoutManager(context)
        list_user_add.hasFixedSize()

        EnentChangeListener()

        return view
    }

    fun Init(view: View) {
        list_user_add = view.findViewById(R.id.cart_user_recycler)

    }

    inner class UserprodutorHolder(view: View) : RecyclerView.ViewHolder(view) {
     //   val iamgePro: ImageView = itemView.findViewById(R.id.pro_item_iamg)
        val decripation: TextView = itemView.findViewById(R.id.decrpation_item)
        val  slowProductpe:Button=itemView.findViewById(R.id.button)

        private lateinit var titleProUser: Prodctor
        fun bind(prodctor: Prodctor) {
            titleProUser = prodctor
            slowProductpe.setOnClickListener {
                val navCon = findNavController()
                val action = UserProdutorFragmentDirections.actionUserProdutorFragment2ToProductorDeatiFragment(prodctor.id)
                navCon.navigate(action)
            }

        }
    }

        inner class UserprodutorAdapter(private val prodctorList: ArrayList<Prodctor>) :
            RecyclerView.Adapter<UserprodutorHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserprodutorHolder {
                val view = layoutInflater.inflate(R.layout.list_prodctor_item, parent, false)
                return UserprodutorHolder(view)
            }


            override fun onBindViewHolder(holder: UserprodutorHolder, position: Int) {
                val prodctoorUR = prodctorList[position]
                holder.decripation.text = prodctoorUR.decpation
                holder.bind(prodctoorUR)
            }

            override fun getItemCount(): Int = prodctorList.size


        }
    fun EnentChangeListener(){
        firestore.collection("product")
            . addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error!=null){
                        Log.e("FireStore_error","${error.message.toString()},errors in fireStore of supermarket list")
                        return
                    }
                    val prodctorList =
                        value?.toObjects(Prodctor::class.java) as ArrayList<Prodctor>
                   list_user_add .adapter = UserprodutorAdapter(prodctorList)

                }

            })


    }


    }