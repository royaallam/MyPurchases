package com.tuwiaq.mypurchases.Supermarket

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.tuwiaq.mypurchases.LoginFragment.LoginFragmentDirections
import com.tuwiaq.mypurchases.R
import com.tuwiaq.mypurchases.RegisterFragment.RegisterFragmentDirections
import kotlinx.coroutines.launch


private const val TAG = "SuperMarketViewFragment"
class SuperMarketViewFragment : Fragment() {

    private lateinit var super_market_list: RecyclerView
//private    lateinit var supermartList: ArrayList<SuperMarkt>
//    lateinit var supermarketAdapter: SupermarketAdapter
   var firestore= Firebase.firestore

    val superMarktViewModel by lazy { ViewModelProvider(
        this).get(SuperMarketViewViewModel::class.java) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_supermarket_view, container, false)
        Init(view)



        super_market_list.layoutManager = LinearLayoutManager(context)
        super_market_list.hasFixedSize()
      //  supermartList = ArrayList()
      //  supermarketAdapter = SupermarketAdapter(supermartList)
//        EnentChangeListener()

//        ListaddSupermp.EnentChangeListener()


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {

            superMarktViewModel.EnentChangeListener().observe(viewLifecycleOwner,
            Observer {
                updateUI(it)
            })

        }
    }


    private fun Init(view: View) {
        super_market_list = view.findViewById(R.id.supermarket_recycler)


    }
    private fun updateUI(supeRms: List<SuperMarkt>) {
        val SupermarketAdapter = SupermarketAdapter(supeRms as ArrayList<SuperMarkt>)
        super_market_list.adapter=SupermarketAdapter
    }


    inner class SupermarketHodler(view: View) : RecyclerView.ViewHolder(view) {
        val superMarketTextView: TextView = itemView.findViewById(R.id.titilesupermarketTv)
        val supermarketButtom: Button = itemView.findViewById(R.id.view_supermarketBTN)
        private lateinit var titleSM: SuperMarkt
        fun bind(supermart: SuperMarkt) {
            titleSM = supermart

           // superMarketTextView.text
            supermarketButtom.setOnClickListener {
                val navCon = findNavController()
                val action = SuperMarketViewFragmentDirections.actionSuperMarketViewFragmentToUserProdutorFragment2()
                navCon.navigate(action)
            }
        }

    }

    inner class SupermarketAdapter(private val supermartList: ArrayList<SuperMarkt>) :
        RecyclerView.Adapter<SupermarketHodler>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SupermarketHodler {
            val view = layoutInflater.inflate(R.layout.list_supermarket_item, parent, false)
            return SupermarketHodler(view)
        }

        override fun onBindViewHolder(holder: SupermarketHodler, position: Int) {
            val superMk = supermartList[position]
            holder.superMarketTextView.text = superMk.userName
            holder.bind(superMk)
        }

        override fun getItemCount(): Int = supermartList.size


    }



//    fun EnentChangeListener(){
//        firestore.collection("users").
//        whereEqualTo("Type","Supermarket")
//            . addSnapshotListener(object : EventListener<QuerySnapshot> {
//                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
//                    if (error!=null){
//                        Log.e("FireStore_error","${error.message.toString()},errors in fireStore of supermarket list")
//                        return
//                    }
////                    val supermartList =
////                        value?.toObjects(SuperMarkt::class.java) as ArrayList<SuperMarkt>
////                    super_market_list.adapter = SupermarketAdapter(supermartList)
////                    Log.d(TAG, "onEvent: $supermartList")
//                }
//
//            })
//
//
//    }






}