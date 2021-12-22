//package com.tuwiaq.mypurchases.Barcode
//
//import android.app.Activity
//import android.content.Intent
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Button
import androidx.fragment.app.Fragment
//import com.google.zxing.integration.android.IntentIntegrator
//import com.google.zxing.integration.android.IntentResult
//import com.tuwiaq.mypurchases.R
//
//
class ScanerBarFragment : Fragment() {
//    private lateinit var barcodeBtn: Button
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view=inflater.inflate(R.layout.fragment_scaner_bar,container,false)
//        Init(view)
//        return view
//    }
//    private fun Init(view: View){
//        barcodeBtn=view.findViewById(R.id.scanner_Btn)
//    }
//
//    override fun onStart() {
//        super.onStart()
//        barcodeBtn.setOnClickListener {
//            val scanner = IntentIntegrator(requireActivity())
//            scanner.let {
//                it.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
//                it.setBeepEnabled(false)
//                it.initiateScan()
//            }
//        }
//    }
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (requestCode == Activity.RESULT_OK) {
//            val result: IntentResult =
//                IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
//            if (result.getContents() == null) {
//
//                // Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
//            } else {
//                // Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
//            }
//        }else{
//            super.onActivityResult(requestCode, resultCode, data)
//        }
//    }



}