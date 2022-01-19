package com.langit7.hondasalesforce.view.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowManager
import android.widget.Toast
import com.budiyev.android.codescanner.*
import com.langit7.hondasalesforce.API.api
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.apparel
import com.langit7.hondasalesforce.presenter.logic.ApparelPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface

class ScanActivity : AppCompatActivity() {

    var pausescan=false



    lateinit var ctx:Context
    val APIServices by lazy {
        api.create(this)
    }


    private lateinit var codeScanner: CodeScanner


    lateinit var presenter: ApparelPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_scan)


        ctx=this


        presenter= ApparelPresenter(ctx as Activity,APIServices)




        val scannerView = findViewById<CodeScannerView>(R.id.scanner_view)

        codeScanner = CodeScanner(this, scannerView)

        // Parameters (default values)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ALL_FORMATS // list of producttype BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {


                presenter.getApparelbyUrl(it.text, object : ObjectResponseInterface<apparel>{
                    override fun onSuccess(res: apparel) {
                        var ii = Intent(ctx, ApparelDetailActivity::class.java)
                        ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        ii.putExtra("apr", res)
                        startActivity(ii)
                        finish()
                    }

                    override fun onFailed(msg: String) {
                        Toast.makeText(ctx,"Apparel tidak ditemukan!", Toast.LENGTH_LONG).show()
                        finish()
                    }


                })








            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            runOnUiThread {
                Toast.makeText(this, "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG).show()
            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
}