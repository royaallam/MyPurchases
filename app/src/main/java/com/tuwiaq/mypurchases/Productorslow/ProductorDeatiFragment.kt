package com.tuwiaq.mypurchases.Productorslow


import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.tuwiaq.mypurchases.R
import com.tuwiaq.mypurchases.RegisterFragment.RegisterFragmentDirections


class ProductorDeatiFragment : Fragment() {


    private lateinit var imagePro:ImageView
    private lateinit var barCodePro:TextView
    private lateinit var decrpationPrp:TextView
    private lateinit var numPro:EditText
    private lateinit var addPro:ImageView
    private lateinit var subPro:ImageView
    private lateinit var addCart:Button
    private lateinit var auto: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.descrpation_product,container,false)
        Init(view)
        return view
    }
   private fun Init(view: View){
        imagePro=view.findViewById(R.id.image_produtor)
        barCodePro=view.findViewById(R.id.barcode_pro)
        decrpationPrp=view.findViewById(R.id.decripa_pro)
        numPro=view.findViewById(R.id.qutity_prodctor)
//       addPro=view.findViewById(R.id.add_pro)
//       subPro=view.findViewById(R.id.sub_prod)
      addCart=view.findViewById(R.id.add_cart)
    }

    override fun onStart() {
        super.onStart()

//        firestore.collection("product").document(uid).get()
//        firestore.collection("users").document(auth.currentUser?.uid!!)
//                        .set(user)
//                        .addOnSuccessListener { documentReference ->
//                            Log.d(TAG, "DocumentSnapshot added succssfully")
//                        }
//                        .addOnFailureListener { e ->
//                            Log.w(TAG, "Error adding document", e)




        addCart.setOnClickListener {
            val navCon = findNavController()
            val action = ProductorDeatiFragmentDirections.actionProductorDeatiFragmentToCartListProdutorFragment()
            navCon.navigate(action)
        }
    }

}