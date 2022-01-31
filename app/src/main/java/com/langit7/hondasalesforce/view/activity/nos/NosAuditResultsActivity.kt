package com.langit7.hondasalesforce.view.activity.nos

import NosAudit
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ShareCompat
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.common.Constants
import com.langit7.hondasalesforce.common.ExcelUtils
import com.langit7.hondasalesforce.common.FileShareUtils
import com.langit7.hondasalesforce.view.activity.BaseActivity

class NosAuditResultsActivity : BaseActivity() {

    var index = ""
    var judul = ""
    var dataTemp = ArrayList<NosAudit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nos_audit_results)

        judul = intent.getStringExtra("title").toString()
       // tactionbartitle.text = judul


        index = intent.getStringExtra("index").toString()

      //  dataTemp    = intent.getParcelableExtra<NosAudit>("data") as ArrayList<NosAudit>


        Log.d("", dataTemp.toString());



    }



    fun initiateExport(dataList: List<NosAudit>, title : String,
                       option1 : String,
                       option2 : String,
                       option3 : String,
                       option4 : String,) {
        Log.d(
            "",
            "initiateExport: "
        )

        // Initially setting Status as 'LOADING' and set/post value to generateExcelMLD
        val isExcelGenerated: Boolean = ExcelUtils.exportDataIntoWorkbook(
            application,
            Constants.EXCEL_FILE_NAME, dataList, title, option1, option2,option3,option4
        )
        if (isExcelGenerated) {
            Log.d("Succes", "Succes")
            onShareButtonClicked()
        } else {
            Log.d("Failed", "Failed")

        }
    }

    private fun launchShareFileIntent(uri: Uri) {
        val intent = ShareCompat.IntentBuilder.from(this)
            .setType("application/vnd.ms-excel")
            .setStream(uri)
            .setChooserTitle("Select application to share file")
            .createChooserIntent()
            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(intent)
    }

    fun onShareButtonClicked() {
        Log.e("TAG", "onShareButtonClicked: ")
        val fileUri = initiateSharing()
        if (fileUri == null) {
            //displaySnackBar("Generate Excel before sharing")
        } else {
            launchShareFileIntent(fileUri)
        }
    }

    fun initiateSharing(): Uri? {
        Log.e(
            "",
            "initiateSharing: "
        )
        return FileShareUtils.accessFile(application, Constants.EXCEL_FILE_NAME)
    }
}