package com.tuwiaq.mypurchases.Barcode

import android.content.pm.LauncherActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import com.tuwiaq.mypurchases.R


private const val TAG = "barCodeScannerFragment"
class barCodeScannerFragment : Fragment() {
    private lateinit var codebarBtn: ImageView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bar_code_scanner, container, false)
        Init(view)
        return view
    }

    private fun Init(view: View) {
        codebarBtn = view.findViewById(R.id.scaner_bar)
    }


    override fun onStart() {
        super.onStart()

        codebarBtn.setOnClickListener {
           barcodeLauncher.launch(ScanOptions().setBeepEnabled(true))


        }

    }

    val barcodeLauncher = registerForActivityResult(ScanContract()){ result ->
        Log.d(TAG, ": ${result.contents}")

        val navController=findNavController()
        val action=barCodeScannerFragmentDirections.actionBarCodeScannerFragmentToListAddSuperFragment(result.contents)
        navController.navigate(action)

    }

}