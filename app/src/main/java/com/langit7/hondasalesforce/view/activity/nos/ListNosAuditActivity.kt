package com.langit7.hondasalesforce.view.activity.nos

import NosAudit
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ShareCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.common.Constants
import com.langit7.hondasalesforce.common.ExcelUtils
import com.langit7.hondasalesforce.common.FileShareUtils
import com.langit7.hondasalesforce.databinding.ActivityListNosAuditBinding
import com.langit7.hondasalesforce.model.baseresponse
import com.langit7.hondasalesforce.model.teamreport.FrequentUser
import com.langit7.hondasalesforce.model.teamreport.PartisipantDetail
import com.langit7.hondasalesforce.model.teamreport.PartisipantQuiz
import com.langit7.hondasalesforce.presenter.adapter.teamreport.AdapterParticipantKuis
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.activity.BaseActivity




class ListNosAuditActivity : BaseActivity() {

    private lateinit var binding: ActivityListNosAuditBinding
    private lateinit var AdapterListNosAudit : AdapterListNosAudit
    var dataTemp = ArrayList<NosAudit>()
    var option1 = 0
    var option2 = 0
    var option3 = 0
    var option4 = 0
    var total = 0

    var index = ""
    var judul = ""
    var mandatoryItemValue = ""



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_nos_audit)

        binding = ActivityListNosAuditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AdapterListNosAudit = AdapterListNosAudit()



        judul = intent.getStringExtra("title").toString()

        binding.bar.tactionbartitle       .setText(judul)

        binding.bar.imgback    .setOnClickListener {
            onBackPressed()
        }

        index = intent.getStringExtra("index").toString()

        // submit data, and get results
        binding.submit   .setOnClickListener {

            // showAlertDialog()

            Log.d("index", index.toString())

            // reset answer
             option1 = 0
             option2 = 0
             option3 = 0
             option4 = 0
             total = 0

            var data : ArrayList<NosAudit> =  AdapterListNosAudit.getData()

                            data.forEachIndexed {
                                    index, e ->

                                    total++

                                Log.d(total.toString(), e.answer.toString())


                                if(e.answer == 1){
                                    option1++
                                }
                                if(e.answer == 2){
                                    option2++
                                }
                                if(e.answer == 3){
                                    option3++
                                }

                                if(e.answer == 4){
                                    option4++
                                }
                            }


            var totalAnswer = option1
            val totalResult = ( totalAnswer.toDouble() / total ) * 100

            Log.d("totalAnswer", totalAnswer.toString())
            Log.d("total", total.toString())
            Log.d("totalResult", totalResult.toString())



            //
            binding.rv.visibility = View.GONE
            binding.submit.visibility = View.GONE
            binding.score.visibility = View.VISIBLE


            // h1 premises
//            if(index=="1") {
//
//                Log.d("h1 premises", index.toString())
//
//                //  binding.img.setBackgroundResource(R.drawable.silver)
//                if( ( data[6-2].answer ==1 || data[6-2].answer ==4 ) &&
//                    ( data[7-2].answer ==1 || data[7-2].answer ==4 ) &&
//                    data[40-2].answer ==1  &&
//                    data[41-2].answer ==1  &&
//                    data[42-2].answer ==1  &&
//                    data[43-2].answer ==1  &&
//                    data[44-2].answer ==1  &&
//                    data[45-2].answer ==1  &&
//                    ( data[48-2].answer ==1 || data[48-2].answer ==4  ) &&
//                    (  data[49-2].answer ==1 || data[49-2].answer ==4 ) &&
//                    data[52-2].answer ==1 &&
//                    ( data[57-2].answer ==1 || data[57-2].answer ==4 ) &&
//                    ( data[65-2].answer ==1 || data[65-2].answer ==4 )  &&
//                    data[103-2].answer ==1
//                        ){
//
//                      binding.img.setBackgroundResource(R.drawable.platinum)
//                    mandatoryItemValue = "Platinum"
//                    Log.d("", "Platinum")
//                }
//                else if (
//
//                    ( data[6-2].answer ==1 || data[6-2].answer ==4 ) &&
//                    ( data[7-2].answer ==1 || data[7-2].answer ==4 ) &&
//                    data[40-2].answer ==1  &&
//                    data[41-2].answer ==1  &&
//                    data[42-2].answer ==1  &&
//                    data[43-2].answer ==1  &&
//                    data[44-2].answer ==1  &&
//                    data[45-2].answer ==1  &&
//                    ( data[48-2].answer ==1 || data[48-2].answer ==4 ) &&
//                    ( data[49-2].answer ==1 || data[49-2].answer ==4 ) &&
//                    data[52-2].answer ==1 &&
//                    data[103-2].answer ==1)
//                {
//
//                    binding.img.setBackgroundResource(R.drawable.gold)
//                    mandatoryItemValue = "Gold"
//
//                    Log.d("", "Gold")
//
//                }
//                else if (
//                    data[40-2].answer ==1  &&
//                    data[41-2].answer ==1  &&
//                    data[42-2].answer ==1  &&
//                    data[43-2].answer ==1  &&
//                    data[52-2].answer ==1 &&
//
//                    data[103-2].answer ==1)
//                {
//                    binding.img.setBackgroundResource(R.drawable.silver)
//                    mandatoryItemValue = "Silver"
//
//                    Log.d("", "Silver")
//
//                }
//                else
//                {
//                    binding.img.setBackgroundResource(R.drawable.bronze)
//                    mandatoryItemValue = "Bronze"
//
//                    Log.d("", "Bronze")
//
//                }
//            }

            // h1 people
//            if(index=="2") {
//                Log.d("h1 people", index.toString())
//
//                if(
//                    data[2-2].answer ==1 &&
//                    ( data[11-2].answer ==1 || data[11-2].answer ==4 )  &&
//                    (  data[20-2].answer ==1 || data[11-2].answer ==4 ) &&
//                    data[100-2].answer ==1   &&
//                    data[128-2].answer ==1
//                ){
//                    binding.img.setBackgroundResource(R.drawable.platinum)
//                    mandatoryItemValue = "Platinum"
//
//                    Log.d("", "Platinum")
//                }
//                else if (
//                    data[2-2].answer ==1 &&
//                    (  data[20-2].answer ==1 || data[11-2].answer ==4 )  &&
//                    data[100-2].answer ==1   &&
//                    data[128-2].answer ==1
//
//                )
//
//                {
//
//                    binding.img.setBackgroundResource(R.drawable.gold)
//                    mandatoryItemValue = "Gold"
//
//                    Log.d("", "Gold")
//
//                }
//                else if (
//                    data[2-2].answer ==1 &&
//
//                    data[128-2].answer ==1)
//                {
//                    binding.img.setBackgroundResource(R.drawable.silver)
//                    Log.d("", "Silver")
//                    mandatoryItemValue = "Silver"
//
//
//                }
//                else
//                {
//                    binding.img.setBackgroundResource(R.drawable.bronze)
//                    Log.d("", "Bronze")
//                    mandatoryItemValue = "Bronze"
//
//
//                }
//            }



            // h1 process
//            if(index=="3") {
//
//                Log.d("h1 process", index.toString())
//
//
//                if(
//                    data[44-2].answer ==1 &&
//                    data[54-2].answer ==1 &&
//                    data[55-2].answer ==1 &&
//
//                    data[57-2].answer ==1 &&
//                    data[62-2].answer ==1 &&
//                    data[63-2].answer ==1 &&
//                    data[79-2].answer ==1 &&
//                    data[80-2].answer ==1
//
//                ){
//                    binding.img.setBackgroundResource(R.drawable.platinum)
//                    Log.d("", "Platinum")
//                    mandatoryItemValue = "Platinum"
//
//                }
//                else if (
//                    data[54-2].answer ==1 &&
//                    data[55-2].answer ==1 &&
//
//                    data[57-2].answer ==1 &&
//                    data[62-2].answer ==1 &&
//                    data[63-2].answer ==1
//
//
//                )
//
//                {
//
//                    binding.img.setBackgroundResource(R.drawable.gold)
//                    Log.d("", "Gold")
//                    mandatoryItemValue = "Gold"
//
//
//                }
//                else if (
//                    data[54-2].answer ==1 &&
//                    data[55-2].answer ==1 &&
//
//                    data[57-2].answer ==1
//                )
//                {
//                    binding.img.setBackgroundResource(R.drawable.silver)
//                    Log.d("", "Silver")
//                    mandatoryItemValue = "Silver"
//
//
//                }
//                else
//                {
//                    binding.img.setBackgroundResource(R.drawable.bronze)
//                    Log.d("", "Bronze")
//                    mandatoryItemValue = "Bronze"
//
//
//                }
//            }


            // h23 premesis
//            if(index=="4") {
//
//                Log.d("h23 premesis", index.toString())
//
//
//                if(
//                    data[8-2].answer ==1 &&
//                    data[10-2].answer ==1 &&
//                    data[20-2].answer ==1 &&
//                    data[21-2].answer ==1 &&
//                    data[34-2].answer ==1 &&
//                    data[36-2].answer ==1 &&
//                    data[37-2].answer ==1 &&
//                    data[41-2].answer ==1 &&
//                    data[43-2].answer ==1 &&
//                    data[52-2].answer ==1 &&
//                    data[56-2].answer ==1 &&
//                    data[62-2].answer ==1 &&
//                    data[73-2].answer ==1 &&
//                    data[74-2].answer ==1 &&
//                    data[77-2].answer ==1 &&
//                    data[80-2].answer ==1 &&
//                    data[81-2].answer ==1 &&
//                    data[82-2].answer ==1 &&
//                    data[84-2].answer ==1 &&
//                    data[85-2].answer ==1 &&
//                    data[86-2].answer ==1 &&
//                    data[87-2].answer ==1
//                        ){
//                    binding.img.setBackgroundResource(R.drawable.platinum)
//                    Log.d("", "Platinum")
//                    mandatoryItemValue = "Platinum"
//
//                }
//                else if (
//                    data[8-2].answer ==1 &&
//                    data[10-2].answer ==1 &&
//                    data[20-2].answer ==1 &&
//                    data[21-2].answer ==1 &&
//                    data[34-2].answer ==1 &&
//                    data[36-2].answer ==1 &&
//                    data[37-2].answer ==1 &&
//                    data[41-2].answer ==1 &&
//                    data[43-2].answer ==1 &&
//                    data[52-2].answer ==1 &&
//                    data[56-2].answer ==1 &&
//                    data[62-2].answer ==1 &&
//                    data[73-2].answer ==1 &&
//                    data[74-2].answer ==1 &&
//                    data[77-2].answer ==1 &&
//                    data[80-2].answer ==1 &&
//                    data[81-2].answer ==1 &&
//                    data[85-2].answer ==1 &&
//                    data[86-2].answer ==1 &&
//                    data[87-2].answer ==1
//                        )
//                {
//                    binding.img.setBackgroundResource(R.drawable.gold)
//                    Log.d("", "Gold")
//                    mandatoryItemValue = "Gold"
//
//                }
//                else if (
//                    data[8-2].answer ==1 &&
//                    data[10-2].answer ==1 &&
//                    data[20-2].answer ==1 &&
//                    data[21-2].answer ==1 &&
//                    data[34-2].answer ==1 &&
//                    data[37-2].answer ==1 &&
//                    data[43-2].answer ==1 &&
//                    data[52-2].answer ==1 &&
//                    data[56-2].answer ==1 &&
//                    data[73-2].answer ==1 &&
//                    data[74-2].answer ==1
//                        ){
//                    binding.img.setBackgroundResource(R.drawable.silver)
//                    Log.d("", "silver")
//                    mandatoryItemValue = "Silver"
//
//
//                }
//                else
//                {
//                    binding.img.setBackgroundResource(R.drawable.bronze)
//                    Log.d("", "Bronze")
//                    mandatoryItemValue = "Bronze"
//                    mandatoryItemValue = "Bronze"
//
//
//
//                }
//            }


            // h23 people
//            if(index=="5") {
//
//                Log.d("h23 people", index.toString())
//
//
//                if(
//                    data[2-2].answer ==1 &&
//                    data[22-2].answer ==1 &&
//                    data[33-2].answer ==1 &&
//                    data[34-2].answer ==1 &&
//                    data[45-2].answer ==1 &&
//                    data[52-2].answer ==1 &&
//                    data[54-2].answer ==1 &&
//                    data[59-2].answer ==1
//
//
//                        ){
//                    binding.img.setBackgroundResource(R.drawable.platinum)
//                    Log.d("", "Platinum")
//                    mandatoryItemValue = "Platinum"
//
//                }
//                else if (
//                    data[2-2].answer ==1 &&
//                    data[33-2].answer ==1 &&
//                    data[34-2].answer ==1 &&
//                    data[54-2].answer ==1 &&
//                    data[59-2].answer ==1
//                )
//                {
//                    binding.img.setBackgroundResource(R.drawable.gold)
//                    Log.d("", "Gold")
//                    mandatoryItemValue = "Gold"
//
//                }
//                else if (
//                    data[2-2].answer ==1 &&
//
//                    data[54-2].answer ==1
//                )
//                {
//                    binding.img.setBackgroundResource(R.drawable.gold)
//                    Log.d("", "Gold")
//                    mandatoryItemValue = "Gold"
//
//                }
//                else
//                {
//                    binding.img.setBackgroundResource(R.drawable.bronze)
//                    Log.d("", "Bronze")
//                    mandatoryItemValue = "Bronze"
//
//                }
//            }

            // h23 process
//            if(index=="6") {
//
//                Log.d("h23 process", index.toString())
//
//
//                if(
//                    data[9-2].answer ==1 &&
//                    data[10-2].answer ==1 &&
//                    data[11-2].answer ==1 &&
//                    data[12-2].answer ==1 &&
//                    data[13-2].answer ==1 &&
//                    data[25-2].answer ==1 &&
//                    data[33-2].answer ==1 &&
//                    data[34-2].answer ==1 &&
//                    data[39-2].answer ==1 &&
//                    data[40-2].answer ==1 &&
//                    data[48-2].answer ==1 &&
//                    data[49-2].answer ==1 &&
//                    data[50-2].answer ==1 &&
//                    data[61-2].answer ==1 &&
//                    data[65-2].answer ==1 &&
//                    data[73-2].answer ==1
//                        ){
//                    binding.img.setBackgroundResource(R.drawable.platinum)
//                    Log.d("", "Platinum")
//                }
//                else if (
//                    data[9-2].answer ==1 &&
//                    data[10-2].answer ==1 &&
//                    data[11-2].answer ==1 &&
//                    data[12-2].answer ==1 &&
//                    data[13-2].answer ==1 &&
//                    data[25-2].answer ==1 &&
//                    data[33-2].answer ==1 &&
//                    data[34-2].answer ==1 &&
//                    data[39-2].answer ==1 &&
//                    data[40-2].answer ==1 &&
//                    data[48-2].answer ==1 &&
//                    data[49-2].answer ==1 &&
//                    data[50-2].answer ==1 &&
//                    data[61-2].answer ==1 &&
//                    data[65-2].answer ==1 &&
//                    data[73-2].answer ==1
//
//                )
//                {
//                    binding.img.setBackgroundResource(R.drawable.gold)
//                    mandatoryItemValue = "Gold"
//
//                    Log.d("", "Gold")
//                }
//                else if (
//                    data[25-2].answer ==1 &&
//                    data[33-2].answer ==1 &&
//                    data[34-2].answer ==1 &&
//                    data[39-2].answer ==1 &&
//                    data[40-2].answer ==1 &&
//                    data[48-2].answer ==1 &&
//                    data[49-2].answer ==1 &&
//                    data[50-2].answer ==1 &&
//                    data[73-2].answer ==1
//
//                        )
//                {
//                    binding.img.setBackgroundResource(R.drawable.silver)
//                    Log.d("", "silver")
//                    mandatoryItemValue = "Silver"
//
//                }
//                else
//                {
//                    binding.img.setBackgroundResource(R.drawable.bronze)
//                    Log.d("", "Bronze")
//                    mandatoryItemValue = "Bronze"
//
//
//                }
//            }


            if(totalResult.toInt() >= 90){
                binding.img.setBackgroundResource(R.drawable.platinum)
                mandatoryItemValue = "Platinum"
                Log.d("", "Platinum")
            }
            else if (totalResult.toInt() >= 70) {
                binding.img.setBackgroundResource(R.drawable.gold)
                mandatoryItemValue = "Gold"
                Log.d("", "Gold")
            }

            else if (totalResult.toInt() >= 50) {
                binding.img.setBackgroundResource(R.drawable.silver)
                mandatoryItemValue = "Silver"
                Log.d("", "Silver")

            }
            else{
                binding.img.setBackgroundResource(R.drawable.bronze)
                mandatoryItemValue = "Bronze"
                Log.d("", "Bronze")

            }
//                if(totalResult.toInt() >= 80){
//                    binding.img.setBackgroundResource(R.drawable.platinum)
//                    mandatoryItemValue = "Gold"
//                    Log.d("", "Platinum")
//                }
//
//            }else{
//        }


            // export data to excel
            if(index=="1")   initiateExport(data, "H1 Premises", "Exist, Good", "Exist, Not Good", "Not Exist", "N/A" , totalResult.toString(), mandatoryItemValue)
            if(index=="2")   initiateExport(data, "H1 People", "Exist, Done", "Exist, Not Done", "Not Exist", "N/A" , totalResult.toString(), mandatoryItemValue)
            if(index=="3")   initiateExport(data, "H1 Process", "Exist, Done", "Exist, Not Done", "Not Exist", "N/A", totalResult.toString() , mandatoryItemValue )
            if(index=="4")   initiateExport(data, "H23 Premises",  "Exist, Good", "Exist, Not Good", "Not Exist", "N/A" , totalResult.toString(), mandatoryItemValue)
            if(index=="5")   initiateExport(data, "H23 People", "Exist, Done", "Exist, Not Done", "Not Exist", "N/A", totalResult.toString(), mandatoryItemValue )
            if(index=="6")   initiateExport(data, "H23 Process", "Exist, Done", "Exist, Not Done", "Not Exist", "N/A", totalResult.toString() , mandatoryItemValue )
            if(index=="7")   initiateExport(data, "Audit All Nos", "Exist, Good", "Exist, Not Good", "Not Exist", "N/A" , totalResult.toString(), mandatoryItemValue)

        }

        binding.exit   .setOnClickListener {

            binding.rv.visibility = View.VISIBLE
            binding.submit.visibility = View.VISIBLE
            binding.score.visibility = View.GONE

        }

        binding.download.setOnClickListener {
            onShareButtonClicked()
        }

    }

//    fun goToPeserta(index: String, title : String, data : ArrayList<NosAudit>
//    ) {
//
//        var ii= Intent (this, NosAuditResultsActivity::class.java)
//        ii.putExtra("index", index)
//        ii.putExtra("title", title)
//        ii.putExtra("data", data )
//
//        this.startActivity(ii)
//    }

    fun initiateExport(dataList: List<NosAudit>, title : String,
                       option1 : String,
                       option2 : String,
                       option3 : String,
                       option4 : String,
                       resultPercent : String,
                       mandatoryItem : String

                       ) {
        Log.d(
            "",
            "initiateExport: "
        )


        // Initially setting Status as 'LOADING' and set/post value to generateExcelMLD
        val isExcelGenerated: Boolean = ExcelUtils.exportDataIntoWorkbook(
            application,
            Constants.EXCEL_FILE_NAME, dataList, title, option1, option2,option3,option4,resultPercent, mandatoryItem
        )
        if (isExcelGenerated) {
            Log.d("Succes", "Succes")
          //  onShareButtonClicked()
        } else {
            Log.d("Failed", "Failed")

        }
    }

    private fun showAlertDialog() {
        val alertDialog: AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialog.setTitle("AlertDialog")
        alertDialog.setMessage("Do you wanna close this Dialog?")
        alertDialog.setPositiveButton(
            "yes"
        ) { _, _ ->

        }
        alertDialog.setNegativeButton(
            "No"
        ) { _, _ -> }
        val alert: AlertDialog = alertDialog.create()
        alert.setCanceledOnTouchOutside(false)
        alert.show()
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


    override fun onResume() {
        super.onResume()
        getData()
    }

    fun getData(){

        if(index=="1")   dataTemp = generateDataH1Premises() as ArrayList<NosAudit>
        if(index=="2")   dataTemp = generateDataH1People() as ArrayList<NosAudit>
        if(index=="3")   dataTemp = generateDataH1Process() as ArrayList<NosAudit>
        if(index=="4")   dataTemp = generateDataH23Premises() as ArrayList<NosAudit>
        if(index=="5")   dataTemp = generateDataH23People() as ArrayList<NosAudit>
        if(index=="6")   dataTemp = generateDataH23Process() as ArrayList<NosAudit>




        if(index=="7") {
            val list: MutableList<NosAudit> = ArrayList()
            list.addAll(generateDataH1Premises())
            list.addAll(generateDataH1People())
            list.addAll(generateDataH1Process())
            list.addAll(generateDataH23Premises())
            list.addAll(generateDataH23People())
            list.addAll(generateDataH23Process())

            dataTemp = list as ArrayList<NosAudit>
        }


        AdapterListNosAudit.setData(dataTemp)
        AdapterListNosAudit.notifyDataSetChanged()


        with(binding.rv) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = AdapterListNosAudit
        }
    }

    fun generateDataH1Premises(): List<NosAudit> {

        val nosAudit = ArrayList<NosAudit>()

        nosAudit.add(NosAudit("Kebersihan Dealer",
            "Exterior", "Exterior dalam keadaan: Bersih (Dibersihkan 1 tahun sekali - bukti ditunjukan) , Rapih & Tidak Rusak.\n" +
                    "Exterior terdiri dari :\n" +
                    "- Fascia\n" +
                    "- Pylon / Projecting Sign\n" +
                    "- Window Display\n" +
                    "- Louver\n" +
                    "- Kanopi\n" +
                    "\n" +
                    "Dinding Exterior (Pengecatan 2 tahun sekali - bukti ditunjukan) \n" +
                    "\n" +
                    "*Bila ditemukan kondisi kotor yang bukan akibat dari proses waktu (kebocoran , sarang binatang, lumpur, jejak sepatu maka harus segera dibersihkan tanpa menunggu jadwal rutin pembersihan)\n" +
                    "*Bukti pembersihan: \n" +
                    " - Bila menggunakan Vendor maka  Bukti Pembersihan berupa Kwitansi dan Dokumentasi dengan tanggal\n" +
                    " - Bila dilakukan oleh Dealer sendiri maka Bukti Pembersihan berupa Dokumentasi dengan tanggal", 0))
        nosAudit.add(NosAudit("Kebersihan Dealer",
            "Interior", "Kebersihan Interior :\n" +
                    "- Dinding Interior\n" +
                    "      - Pengecatan 2 tahun sekali - bukti ditunjukan\n" +
                    "      - Bersih dari sarang binatang \n" +
                    "*Bila ditemukan kondisi kotor yang bukan akibat dari proses waktu (kebocoran , lumpur, jejak sepatu maka harus segera dibersihkan tanpa menunggu jadwal rutin pembersihan)\n" +
                    "*Bukti pembersihan: \n" +
                    "       - Bila menggunakan Vendor maka  Bukti Pembersihan berupa Kwitansi dan Dokumentasi dengan tanggal\n" +
                    "        - Bila dilakukan oleh Dealer sendiri maka Bukti Pembersihan berupa Dokumentasi dengan tanggal\n" +
                    "\n" +
                    "- Lantai \n" +
                    "      - Dibersihkan minimal sehari 2 kali - bukti ditunjukan dengan Checklist Harian\n" +
                    "      - Dalam keadaan rapih dan tidak rusak \n" +
                    "- Toilet \n" +
                    "      - Dibersihkan minimal sehari 3 kali - bukti ditunjukan dengan Checklist Harian\n" +
                    "      - Dalam keadaan rapih dan tidak rusak \n" +
                    "- Furniture Standard Item: \n" +
                    "      - Dibersihkan minimal sehari 2 kali - bukti ditunjukan dengan Checklist Harian\n" +
                    "      - Dalam keadaan rapih dan tidak rusak \n" +
                    "*Bila ditemukan kondisi kotor (perubahan warna) maka harus segera dibersihkan tanpa menunggu jadwal rutin pembersihan atau diganti bila sudah tidak bisa dibersihkan\n" +
                    "\n" +
                    "- Item Furniture Recommended Item \n" +
                    "      - Dibersihkan minimal sehari 2 kali - bukti ditunjukan dengan Checklist Harian\n" +
                    "      - Dalam keadaan rapih dan tidak rusak \n" +
                    "*Bila ditemukan kondisi kotor (perubahan warna) maka harus segera dibersihkan tanpa menunggu jadwal rutin pembersihan atau diganti bila sudah tidak bisa dibersihkan\n" +
                    "\n" +
                    "- Display Motor (Dibersihkan minimal sehari 2 kali - bukti ditunjukan dengan Checklist Harian)",0))


        nosAudit.add(NosAudit("Kebersihan Dealer", "Checklist Kebersihan Interior"
            , "1. Terdapat checklist kebersihan untuk area showroom \n" +
                "2. Terdapat kolom checklist kebersihan minimal pada jam 07.30, 12.00, 15.00",0 ))

        nosAudit.add(NosAudit("Kebersihan Dealer", "Checklist Kebersihan Atribut",
            "1. Terdapat checklist kebersihan untuk unit display, HRT, accessories, dan apparel\n" +
                    "2. Terdapat kolom checklist kebersihan minimal pada jam 07.30, 12.00, 15.00" ,0))


        nosAudit.add(NosAudit("Approval Layout New VinCi",
            "Eksterior",
            "1. Sesuai dengan gambar tampak depan yang sudah diapprove bersama\n" +
                    "2. Gambar eksterior yang sudah diapproved, dicetak, ditempel di ruang Kacab", 0))
        nosAudit.add(NosAudit("Approval Layout New VinCi",
            "Interior",
            "1. Area / Zoning Showroom sesuai dengan gambar yang sudah diapprove bersama : \n" +
                    "Item Interior yang ada di gambar approval berada didalam showroom dan digunakan sesuai fungsinya, khususnya:\n" +
                    "    - Sales & Finance Front Desk \n" +
                    "    - Negotiation Table  minimal sejumlah yang ada didalam gambar approval\n" +
                    "    - Khusus Wing Dealer maka Posisi Wing Corner harus sesuai dengan gambar approval dan Item Interior yang ada didalam Wing Corner harus sesuai dan tidak boleh dipindahkan , terkecuali meja negosiasi bila harus digantikan oleh unit motor\n" +
                    "\n" +
                    "2. Area / Zoning HUB / PENGHUBUNG sesuai dengan gambar yang sudah diapprove bersama : \n" +
                    "a. Ruang Tunggu \n" +
                    "     - Posisi dan Fungsinya sesuai dengan gambar interior yang sudah diapprove bersama\n" +
                    "b. Kasir\n" +
                    "    - Posisi dan Fungsinya sesuai dengan gambar interior yang sudah diapprove bersama\n" +
                    "\n" +
                    "3.Area / Zoning  H3 sesuai dengan gambar yang sudah diapprove bersama (4 Ruko)\n" +
                    "\n" +
                    "4. Area / Zoning  H2 sesuai dengan gambar yang sudah diapprove bersama\n" +
                    "\n" +
                    "5. Gambar interior yang sudah diapproved, dicetak, ditempel di ruang Kacab (untuk Wing Dealer, gambar yang dipajang adalah gambar yang sudah terdapat wing corner)", 0))
        nosAudit.add(NosAudit("Eksterior",
            "Fascia",
            "1. Dalam keadaan rapih\n" +
                    "2. Nama Dealer terbaca jelas\n" +
                    "3. Lampu menyala pada pukul 18:00 - 22:00 ( for New VinCi only )\n" +
                    "4. Dalam Keadaan Bersih\n" +
                    "5. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "6. Untuk new VinCi sesuai dengan gambar tampak depan yang sudah di approve bersama\n" +
                    "\n" +
                    "Hanya untuk temporary Fascia : \n" +
                    "1. Mengikuti Juklak AHM, ukuran mengikuti Standard yang ada di Manual Book AHM\n" +
                    "2. Warna tidak pudar (merah tidak memudar, sesuai standard warna Merah Honda) ", 0))
        nosAudit.add(NosAudit("Eksterior",
            "Pylon Atau Projecting Sign",
            "1. Dalam keadaan rapih\n" +
                    "2. Untuk logo Honda,detail tulisan penjualan,pemeliharaan,suku cadang tidak terhalangi dan dapat dilihat dan dibaca oleh pengguna jalan dari dua sisi\n" +
                    "3. Lampu menyala pada pukul 18:00 - 22:00 ( for New VinCi only )\n" +
                    "4. Dalam Keadaan Bersih\n" +
                    "5. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "6. Peletakan sesuai dengan denah yang sudah disepakati bersama", 0))
        nosAudit.add(NosAudit("Eksterior",
            "Canopy",
            "1. Dalam keadaan bersih (bukan debu) \n" +
                    "2. Dalam Keadaan Bersih\n" +
                    "3. Dalam Keadaan Rapih dan Tidak Rusak", 0))

        nosAudit.add(NosAudit("Eksterior",
            "Window Display",
            "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "3.Display motor terlihat jelas \n" +
                    "4. Lampu menyala pada pukul 18:00 - 22:00 \n" +
                    "5. 1 Ruko = 4m diisi 1 motor dan 1 roller banner \n" +
                    "\n" +
                    "Dapat memilih N/A dengan melampirkan surat tertulis penundaan investasi dari MD ke AHM", 0))

        nosAudit.add(NosAudit("Eksterior",
            "Kisi-Kisi Alumunium / Louvre ",
            "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "\n" +
                    "Dapat memilih N/A dengan melampirkan surat tertulis penundaan investasi dari MD ke AHM", 0))

        nosAudit.add(NosAudit("Eksterior",
            "Operational hour",
            "1. Dapat terlihat jelas oleh konsumen pada saat datang / sampai di halaman /pagar  Dealer\n" +
                    "2. Berinformasikan : Hari & Jam operational Showroom dan AHASS\n" +
                    "3. Dapat berupa sticker di kaca,banner,running text,spanduk", 0))

        nosAudit.add(NosAudit("Eksterior",
            "Cadangan Masker",
            "Tersedia masker yang masih disegel, terletak di area security/pintu masuk, dan diberikan kepada konsumen yang tidak membawa masker saat memasuki area dealer", 0))

        nosAudit.add(NosAudit("Eksterior",
            "X-Banner Protab Kebersihan",
            "1. Terdapat X-Banner yang berisi edukasi protab kebersihan  di area security (pintu masuk)", 0))


        nosAudit.add(NosAudit("Eksterior",
            "Thermal Gun",
            "1. Spesifikasi: infrared dan digital\n" +
                    "2. Terdapat pada pintu masuk atau di area security / pintu masuk (dedicated untuk showroom)", 0))

        nosAudit.add(NosAudit("Eksterior",
            "Tempat Cuci Tangan",
            "1. Spesifikasi: infrared dan digital\n" +
                    "2. Terdapat pada pintu masuk atau di area security / pintu masuk (dedicated untuk showroom)", 0))

        nosAudit.add(NosAudit("Eksterior",
            "Tempat Cuci Tangan",
            "1. Tersedia tempat cuci tangan dan sabun pada area pintu masuk dealer\n" +
                    "2. Terdapat sabun cair \n" +
                    "3. Terdapat tissue kering\n" +
                    "*untuk dealer H123 minimal terdapat 1 tempat cuci tangan pada area pintu masuk dealer\n" +
                    "\n" +
                    "Dapat menyesuaikan rekomendasi juklak 164/AHM/MPA/VII/2020", 0))

        nosAudit.add(NosAudit("Eksterior",
            "Area Tunggu Sementara",
            "1.Terdapat area tunggu sementara diluar showroom / AHASS yang dapat digunakan konsumen jika kondisi showroom / AHASS sedang ramai\n" +
                    "2. Nice to have jika berupa tenda", 0))

        nosAudit.add(NosAudit("Eksterior",
            "Garis Pembatas",
            "1. Terdapat garis pembatas 1m pada area parkir yang dapat digunakan sebagai titik berdiri konsumen untuk memasuki showroom, jika kondisi sedang ramai", 0))

        nosAudit.add(NosAudit("Material Promosi Eksterior",
            "Product Banner/ Spanduk",
            "1. Memasang spanduk mengacu pada Line Up Bulanan (Notes : Juklak no 376/AHM/MPA/XI/2018  tanggal 28 November 2018)\n" +
                    "2. Ditempatkan di Pagar Dealer / Dinding Halaman Dealer / Dibawah Kanopi Dealer (Janggutan)\n" +
                    "3. Spanduk dalam keadaan :  \n" +
                    "- bersih dari kotoran/debu\n" +
                    "- tidak terlipat\n" +
                    "- tidak bertumpukan dengan material promosi lain\n" +
                    "- tidak melendut\n" +
                    "- tidak berkibar (sisi kanan dan kiri harus terikat)\n" +
                    "4. Design dan Periode pemasangan Sesuai dengan Line Up Bulanan", 0))

        nosAudit.add(NosAudit("Material Promosi Eksterior",
            "HGP atau AHM Oil Banner dan Vertical Banner",
            "1.Banner ditempatkan  di pagar dealer/dinding halaman dealer dibawah kanopi halaman dealer dan dalam keadaan bersih dari kotoran/debu, tidak terlipat, tidak bertumpukan  dengan material promosi lain , tidak berkibar (sisi kanan dan kiri harus terikat)i dan ter-update sesuai ketentuan PT AHM.\n" +
                    "2. Vertical banner ditempatkan dihalaman dealer/pagar dealer dan dalam keadaan bersih dari kotoran/debu, tidak terlipat, tidak bertumpukan  dengan material promosi lain dan ter-update sesuai ketentuan PT AHM.\n" +
                    "3. Periode pemasangan sesuai dengan ketentuan PT AHM\n", 0))
        nosAudit.add(NosAudit("Material Promosi Eksterior",
            "HGP atau AHM Oil Poster",
            "1. Poster dalam keadaan bersih dari kotoran/debu, tidak terlipat, tidak bertumpukan  dengan material promosi lain dan ter-update sesuai ketentuan PT AHM.\n" +
                    "2. Terdapat di AHASS / ruang tunggu AHASS\n" +
                    "\n" +
                    "*Periode pemasangan sesuai dengan ketentuan PT AHM.", 0))

        nosAudit.add(NosAudit("Material Promosi Eksterior",
            "Product Giant Banner (Old VinCi)",
            "1. Memasang giant banner di glass protector.\n" +
                    "2. Giant Banner dalam keadaan bersih dari kotoran/debu, tidak terlipat, tidak bertumpukan  dengan material promosi lain dan ter-update sesuai ketentuan PT AHM.\n" +
                    "\n" +
                    "*Periode pemasangan sesuai dengan ketentuan PT AHM", 0))

        nosAudit.add(NosAudit("Parking Area",
            "Parking Area",
            "1. Area parkir khusus digunakan untuk parkir customer.\n" +
                    "2. Memiliki marka parkir (warna putih/kuning)\n" +
                    "3. Bentuk Marka parkir mengikuti juklak\n" +
                    "4. Bersih dan tertata rapi (kendaraan tertata di dalam marka)\n" +
                    "\n" +
                    "*) Nilai N/A : bagi dealer yang tidak memiliki lahan untuk parkir (misal langsng trotoar/jalan).", 0))

        nosAudit.add(NosAudit("Parking Area",
            "Direction Signage (Showroom, Service, Spareparts)",
            "1. Terlihat jelas oleh customer\n" +
                    "2. Tanda penunjuk arah pada signage mengarahkan dan sesuai dengan lokasi Parkir/Showroom/AHASS\n" +
                    "\n" +
                    "*Mengikuti juklak yang ada\n" +
                    "(di detailkan peruntukannya)", 0))
        nosAudit.add(NosAudit("Interior | Atribut Interior",
            "Poster Reminder ",
            "Terdapat informasi untuk menggunakan masker dan  hand sanitizer pada pintu masuk showroom\n" +
                    "\n" +
                    "Dapat diletakan bersebelahan dengan hand santizer atau ditempel pada botol hand sanitizer", 0))

        nosAudit.add(NosAudit("Interior | Atribut Interior",
            "Hand Sanitizer",
            "Terletak di pintu masuk showroom", 0))

        nosAudit.add(NosAudit("Interior | Atribut Interior",
            "Dealing Table ",
            "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "3. Tertata rapi\n" +
                    "4. Dilengkapi permen, vas bunga (maks. 20 cm), buku tamu, AMDK (maks. 3 pcs), tisu basah kemasan botol, sanitizer, price list, dan kalendar product\n" +
                    "5.  Meja bewarna putih", 0))

        nosAudit.add(NosAudit("Interior | Atribut Interior",
            "Corporate Identity Logo\n" +
                    "(New VinCi)",
            "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "3. Perletakan sesuai dengan denah dealer yang sudah diapprove AHM", 0))
        nosAudit.add(NosAudit("Interior | Atribut Interior",
            "Direction Sign \n" +
                    "(Papan Gantung /  Menempel di Dinding)",
            "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "3. Terbaca dan Terlihat Jelas\n" +
                    "4. Terdapat minimal : penjualan,pembiayaan, bengkel,suku cadang,ruang tunggu,toilet,smoking,no smoking,female toilet/male toilet/toilet", 0))

        nosAudit.add(NosAudit("Interior | Atribut Interior",
            "Sales Front Desk & Finance Front Desk\n" +
                    "(New VinCi)",
            "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "3. Tertata rapi \n" +
                    "4. Jumlah dan Perletakan sesuai dengan denah dealer yang sudah diapprove AHM\n" +
                    "5. Terdapat hand sanitizer", 0))

        nosAudit.add(NosAudit("Interior | Atribut Interior",
            "Staff chair\n" +
                    "(New VinCi)",
            "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "3. Tertata rapi\n" +
                    "4. Material setara : alas hitam,sandaran putih,tanpa sandaran tangan,kaki dengan roda,tuas untuk mengatur ketinggian posisi\n" +
                    "Ket : \n" +
                    "Untuk all front desk ", 0))

        nosAudit.add(NosAudit("Interior | Atribut Interior",
            "Customer chair \n" +
                    "(New VinCi)",
            "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "3. Tertata rapi\n" +
                    "4. Material setara : warna hitam dan merah,tanpa sandaran tangan dan kaki 4 tanpa roda\n" +
                    "Ket : \n" +
                    "Terletak di depan front desk ", 0))

        nosAudit.add(NosAudit("Interior | Atribut Interior",
            "Safety Shield\n" +
                    "Nice to have",
            "1. terletak pada area meja sales counter dan meja fincoy\n" +
                    "2. sesuai dengan rekomendasi juklak 154/AHM/MPA/VI/2020", 0))

        nosAudit.add(NosAudit("Interior | Atribut Interior",
            " Modification Rack Acc or Jacket Hanger (Old VinCi)",
            "1. Terpasang / terdisplay secara rapi dan bersih \n" +
                    "2. Standard display apparel tidak menggunakan plastik pembungkus, kecuali jika posisi dealer terbuka (tidak ada kaca) dan bila karakteristik konsumen menginginkan display menggunakan plastik, maka boleh menggunakan plastik (mika)\n" +
                    "\n" +
                    "* Untuk old VinCi dan Non boleh menggunakan rak modifikasi\n" +
                    "* Item arrangement mengikuti juklak yang dikirim oleh AHM setiap bulannya", 0))


        nosAudit.add(NosAudit("Interior | Atribut Interior",
            "Wall system display (New VinCi)",
            "1. Pengaturan rak mengikuti juklak yang dikirim oleh AHM setiap bulannya\n" +
                    "2. Pengaturan rapi\n" +
                    "3. Product yang di display dalam keadaan bersih\n" +
                    "4. Semua apparel menggunakan hanger dalam keadaan rapih, bersih & bebas debu, dengan spesifikasi sbb :\n" +
                    "Specification : Hook Material : Stainless (4.00 mm) Shoulder Material : Wood  Shoulder Colour : Wood Colour Size : Width : 420 mm Note : Ukuran tidak harus identik sama\n" +
                    "5. Standard display apparel tidak menggunakan plastik pembungkus, kecuali jika posisi dealer terbuka (tidak ada kaca) dan bila karakteristik konsumen menginginkan display menggunakan plastik, maka boleh menggunakan plastik (mika).\n" +
                    "6. Penempatan dan Jumlah Wall System Display sesuai dengan Layout yang sudah disetujui\n" +
                    "\n" +
                    "Wall System / Furniture\n" +
                    "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak", 0))

        nosAudit.add(NosAudit("Interior | Atribut Interior",
            "Spec Stand \n" +
                    "(New VinCi)",
            "1. Ditempatkan didalam area H1 dan bersebelahan dengan unit display.\n" +
                    "2. Terdapat sticker logo Honda\n" +
                    "3. Dalam Keadaan Bersih\n" +
                    "4. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "5. Type sesuai dengan unit yang didisplay\n" +
                    "6. Matprom yang digunakan sesuai dengan standard AHM", 0))

        nosAudit.add(NosAudit("Interior | Atribut Interior",
            "Glass Showcase\n" +
                    "(New VinCi)",
            "1. Jika glass showcase ada di Premium Corner, maka yang harus di-display adalah Aksesoris Premium 2. Apparel dapat di-display dalam Glass Showcase, kecuali Jacket, T-Shirt, dan Helmet 3.HGA dan Apparel yang di-display dalam Glass Showcase dikeluarkan dari kemasan atau packaging-nya (kelengkapan baut, manual, dan lainnya tidak di-display di dalam Glass Showcase).\n" +
                    "4. Menggunakan acrylic display untuk :\n" +
                    "a. Aksesoris dan Apparel kecil seperti sarung tangan, Tank Pad, Garnish, dll.\n" +
                    "b. Aksesoris dan apparel besar seperti Seat Cowl, Fender Eliminator, dll. Specification : Material : Acrylic, Size : 90mm x 60mm Note : Ukuran tidak harus identik sama\n" +
                    "\n" +
                    "Glass Showcase / Furniture\n" +
                    "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak", 0))

        nosAudit.add(NosAudit("Material Promosi Interior",
            "X-banner Product",
            "1. Terpasang di samping atau di belakang sesuai unitnya. Minimal terdapat 3 X-Banner\n" +
                    "2. X-banner dalam keadaan bersih dari kotoran/debu, tidak terlipat dan ter-update sesuai ketentuan PT AHM.\n" +
                    "3. Dalam keadaan bersih dari kotoran / debu dan kondisi baik \n" +
                    "\n" +
                    "*Periode pemasangan harus sesuai dengan ketentuan line up bulanan", 0))


        nosAudit.add(NosAudit("Material Promosi Interior",
            "Wobbler",
            "1.  Terpasang sesuai di unit motor (di setiap series produk) minimal dipasang di 3 tipe yang berbeda\n" +
                    "2. Terdapat QR Code untuk scan aksesoris (unit baru)\n" +
                    "3. Terbaca oleh konsumen", 0))

        nosAudit.add(NosAudit("Material Promosi Interior",
            "Brochure Rack ",
            "1. Ditempatkan didalam area H1\n" +
                    "2. Tersedia minimum 1 di showroom\n" +
                    "3. Produk terbaru diletakan paling atas\n" +
                    "4. Diletakan dekat dengan ruang tunggu konsumen\n" +
                    "5. Terdapat sticker logo Honda\n" +
                    "6. Pemilihan Tipe Self Standing atau Wall Mounted disesuaikan dengan ruang yang tersedia didalam showroom.\n" +
                    "7. Dalam Keadaan Bersih\n" +
                    "8. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "\n" +
                    "*Disusun berdasarkan juklak bulanan yang dikirimkan oleh PT. AHM", 0))

        nosAudit.add(NosAudit("Material Promosi Interior",
            "Honda Contact Center Banner",
            "1. Ditempatkan di area yang terlihat oleh konsumen dan area ruang tunggu / showroom \n" +
                    "2.Design sesuai juklak AHM terupdate\n" +
                    "3. Berbentuk X-Banner\n" +
                    "4. Nomor telepon yang tertera adalah nomor telepon call center Honda dan Hotline Main Dealer", 0))


        nosAudit.add(NosAudit("Material Promosi Interior",
            "LCD TV",
            "1. Ditempatkan di dalam showroom \n" +
                    "2. Minimal berukuran 32 inchi dan dinyalakan selama jam buka showroom\n" +
                    "3. Terdapat looping TVC. \n" +
                    "4. Untuk Wing Dealer, akan disertakan material racing video, regular product, dan PDSA video.\n" +
                    "5. Dalam keadaan berfungsi (menyala)\n" +
                    "6. Bersih / bebas dari debu", 0))

        nosAudit.add(NosAudit("Material Promosi Interior",
            "Racing Video + PDSA Video",
            "1. Di tampilkan liputan sesuai dengan ketentuan dan juklak dari PT AHM", 0))

        nosAudit.add(NosAudit("Material Promosi Interior",
            "Box Duratrans (Old VinCi)",
            "1. Terpasang di dinding interior \n" +
                    "2. Lengkap dan terisi \n" +
                    "3. Dalam Keadaan Bersih\n" +
                    "4. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "5. Poster One Heart\n" +
                    "\n" +
                    "* sesuai ketentuan PT AHM", 0))

        nosAudit.add(NosAudit("Material Promosi Interior",
            "Wall Sticker One Heart (New VinCi)",
            "1. Terpasang di dinding interior \n" +
                    "2. Dalam keadaan bersih dari kotoran/debu, tidak terlipat\n" +
                    "3. Sticker One Heart\n" +
                    "\n" +
                    "* sesuai ketentuan PT AHM", 0))


        nosAudit.add(NosAudit("Material Promosi Interior",
            "9 Panel Poster Display \n" +
                    "(New VinCi)\n" +
                    "* > 3 ruko ",
            "1. Terpasang di 9 panel poster display.\n" +
                    "2. Pemasangan  panel tidak diperbolehkan terhalangi objek lain.\n" +
                    "3. Dalam Keadaan Bersih\n" +
                    "4. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "5. Periode pemasangan dan material poster yang didisplay sesuai ketentuan PT AHM\n" +
                    "6. Peletakan berada di area showroom\n", 0))


        nosAudit.add(NosAudit("Material Promosi Interior",
            "Poster Display \n" +
                    "(New VinCi)",
            "1. Ditempatkan didalam area H1 dan dekat dengan ruang tunggu (dekat sofa ruang tunggu)\n" +
                    "2. Dalam Keadaan Bersih\n" +
                    "3. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "4. Berisikan promosi dan layanan yang ada di dealer (contoh : sales champaign di dealer)\n" +
                    "5. Peletakan berada di area showroom", 0))

        nosAudit.add(NosAudit("Unit Display",
            "Unit Display Arrangement",
            "1. Mengelompokkan base on juklak bulanan yang dikirim oleh PT. AHM\n" +
                    "2. Mengatur jarak antar unit minimum 60-70 cm \n" +
                    "3. Unit bersih (diperbolehkan menggunakan plastik wrapping jok dengan kondisi yang bersih dan rapi)\n" +
                    "4. Melengkapi spion\n" +
                    "5. Meletakkan di lokasi yang strategis (mudah dilihat konsumen)\n" +
                    "6. Memberi penerangan yang cukup (Semua lampu dipastikan dalam kondisi menyala) \n" +
                    "7. Unit display dilengkapi dengan tag PDI\n" +
                    "\n" +
                    "* sesuai ketentuan PT AHM\n", 0))

        nosAudit.add(NosAudit("Unit Display",
            "Unit Display di Stage (Old VinCi)",
            "1. Memasang produk sesuai juklak bulanan yang dikirimkan oleh PT. AHM\n" +
                    "2. Meletakkan di area strategis, mudah dilihat.\n" +
                    "3. Memberi penerangan yang cukup\n" +
                    "\n" +
                    "* sesuai ketentuan PT AHM", 0))

        nosAudit.add(NosAudit("Unit Display",
            "Unit display with accessories",
            "1. Semua motor harus terpasang aksesoris \n" +
                    "2. Jika ada varian motor yang sama namun berbeda warna atau tipe, cukup salah satu saja yang dipasang aksesoris (contoh : Di dealer ada 1 Vario 125 dan 1 Vario 150, maka cukup salah satu motor saja yang dipasang aksesoris)\n" +
                    "\n" +
                    "*Sesuai dengan juklak yang dikirimkan bulanan oleh PT. AHM", 0))


        nosAudit.add(NosAudit("Unit Display",
            "Clear File Holder untuk Informasi HGA, Apparel, dan Customized Parts",
            "1. Untuk penyimpanan media informasi berupa : \n" +
                    "a. Brosur acc & apparel\n" +
                    "b. Price List HGA, Apparels,Customized Parts per tipe motor (bukan brosur unit),\n" +
                    "c. Brosur bengkel modifikasi, dengan ketentuan sbb :\n" +
                    "- Format Brosur Bengkel Modifikasi disesuaikan dengan format masing-masing Main Dealer\n" +
                    "- Brosur Bengkel Modifikasi untuk CBR250RR dan CRF150L (Jika bukan Wing Dealer, maka hanya akan ada brosur Bengkel Modifikasi untuk CRF150L)\n" +
                    "d. Brosur customized parts, dengan ketentuan sbb :\n" +
                    " *Ter-update sesuai dengan ketentuan PT. AHM\n" +
                    "e. Sales talk HGA & Apparel\n" +
                    "Jumlah Sales Talk Aksesoris per tipe motor harus ada sejumlah  (Contoh : jika di Juklak ada 10 model motor yang terpasang aksesoris, maka harus ada 10 sales talk aksesoris per tipe motor).\n" +
                    "2. Ukuran Clear File Holder : Folio\n", 0))

        nosAudit.add(NosAudit("Unit Display",
            "Banner Community",
            "1. Berupa X-Banner ", 0))

        nosAudit.add(NosAudit("Unit Display",
            "Hero Stage\n" +
                    "(New VinCi)\n" +
                    "* > 3 ruko ",
            "1. Memasang produk volume seller atau best seller\n" +
                    "2. Meletakkan di area strategis, mudah dilihat.\n" +
                    "3. Memberi penerangan yang cukup (Semua lampu dipastikan dalam kondisi menyala)\n" +
                    "4. Perletakan sesuai dengan denah dealer yang sudah diapprove AHM\n" +
                    "\n" +
                    "Hero Stage / Furniture\n" +
                    "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak", 0))


        nosAudit.add(NosAudit("Unit Display",
            "Isolate Stage\n",
            "1. Memasang produk baru dalam periode 3 bulan setelah launching atau selama periode yang ditentukan AHM\n" +
                    "2. Meletakkan di area strategis, mudah dilihat.\n" +
                    "3. Memberi penerangan yang cukup (Semua lampu dipastikan dalam kondisi menyala)\n" +
                    "Keterangan : \n" +
                    "Untuk area di luar wing corner:\n" +
                    "- 2 ruko : 0 \n" +
                    "-  3 - 4 ruko : 1 unit\n" +
                    "4. Perletakan sesuai dengan denah dealer yang sudah diapprove AHM\n" +
                    "\n" +
                    "Isolate Stage / Furniture\n" +
                    "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak", 0))

        nosAudit.add(NosAudit("Unit Display",
            "Mannequin\n",
            "1. Warna Putih\n" +
                    "2. Seluruh badan lengkap dengan kepala dan tidak boleh ada berwajah (faceless)\n" +
                    "3. Dikarenakan mannequin yang digunakan adalah full body, maka harap dilengkapi juga dengan celana dan sepatu\n" +
                    "4. Apparel yang di-display pada mannequin harus senada dan satu tema antara Jacket, T-Shirt, Gloves, Helmet, atau Cap (misal tema Honda Racing Red, maka dari Helmet/Cap, Jacket, Gloves, dan Shirt dapat menggunakan line up Honda Racing Red)\n" +
                    "5. Peletakan berada di area showroom\n" +
                    "6. Jika ada sales program Direct Gift Apparel, maka mannequin boleh dipergunakan untuk mendukung program tersebut selama periode program", 0))

        nosAudit.add(NosAudit("Unit Display",
            "Glass Showcase\n" +
                    "(New VinCi)\n" +
                    "*>4 ruko",
            "1. Dapat diisi dengan Aksesoris dan Apparel Regular (jika posisi Glass Showcase diluar Premium Corner)\n" +
                    "2. Apparel dapat di-display dalam Glass Showcase, kecuali Jacket, T-Shirt, dan Helmet\n" +
                    "3.HGA dan Apparel yang di-display dalam Glass Showcase dikeluarkan dari kemasan atau packaging-nya (kelengkapan baut, manual, dan lainnya tidak di-display di dalam Glass Showcase)\n" +
                    "4. Peletakan berada di area showroom\n" +
                    "\n" +
                    "Glass Showcase / Furniture\n" +
                    "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak", 0))

        nosAudit.add(NosAudit("Unit Display",
            "Riding Test Unit ",
            "1. Riding Test Unit wajib tersedia di setiap dealer. Tipe dan jumlah mengacu pada juklak bulanan PT AHM.\n" +
                    "\n" +
                    "2. Unit Riding Test wajib selalu dalam keadaan bersih & layak digunakan. \n" +
                    "\n" +
                    "3. Riding Test Unit wajib di PDI sebelum digunakan oleh konsumen. Bukti PDI harap disimpan.\n" +
                    "\n" +
                    "4. STNK sepeda motor unit test ride wajib atas nama Dealer/Owner/Kepala Cabang.\n" +
                    "\n" +
                    "5. Jarak tempuh sepeda motor (dilihat pada speedometer) berada pada angka yang wajar sesuai penggunaan.\n" +
                    "\n" +
                    "6. Penempatan unit Riding Test adalah di Riding Test Parking Area. \n" +
                    "\n" +
                    "7. Berikut contoh utilisasi unit riding test di Dealer :\n" +
                    " a. Riding Test Prospek Konsumen\n" +
                    " b. Exhibition, Roadshow & Sales Event\n" +
                    " c. Wing SF Weekend Riding\n" +
                    " d. Leisure Touring\n" +
                    " e. Fun Race Track Day", 0))


        nosAudit.add(NosAudit("Unit Display",
            "Riding test guest book",
            "1. Terdapat kolom hari/tanggal, nama, alamat, no telpon, tipe motor yang disukai (tertarik), warna dan saran/kritik.\n" +
                    "2. Tertata rapi dalam folder.\n" +

                    "3. Form riding test guest book dipegang oleh Wing Sales People dan Sales People Regular.", 0))


        nosAudit.add(NosAudit("Unit Display",
            "Riding test parking area",
            "1. Terdapat marka khusus untuk riding test unit parking area. Desain tidak diberikan dari AHM, masing-masing dealer bebas berkreasi untuk desain riding test unit parking area.\n" +
                    "\n" +
                    "2. Pada riding test unit parking area terdapat tulisan “Test Ride Zone” atau “Riding Test Area” yang terlihat jelas oleh konsumen.\n" +
                    "\n" +
                    "3. Riding Test Unit Parking Area terletak di dekat pintu masuk showroom.\n" +
                    "\n" +
                    "4. Riding Test Unit Parking Area harus dapat memuat minimal 2 unit riding test (latest product).\n" +
                    "\n" +
                    "5. Riding Test Unit Parking Area disediakan oleh dealer.\n" +
                    "\n" +
                    "6. Pengecualian untuk tidak memiliki Riding Test Unit Parking Area diberikan bagi dealer yang tidak memiliki area parkir/halaman depan.\n", 0))

        nosAudit.add(NosAudit("Unit Display",
            "Riding Test Signage",
            "1. Terlihat jelas oleh customer \n" +
                    "2. Dalam keadaan bersih \n" +
                    "3. Terpasang di plat motor bertuliskan \"test ride\" jika motor sedang tidak digunakan.", 0))

        nosAudit.add(NosAudit("Unit Display",
            "Cairan Pembersih dan Wiping Tools Riding Test",
            "1. berisi campuran air bersih dengan sabun (dilarang menggunakan cairan yang mengandung alkohol)\n" +
                    "2. wiping tools microfiber / plas chamois \n" +
                    "3. Terletak di sekitar area riding test", 0))

        nosAudit.add(NosAudit("Unit Display",
            "HRT Banner (New VinCi)\n" +
                    "(* > 3 ruko)",
            "1. Diletakkan dekat dengan HRT\n" +
                    "2. Terlihat jelas oleh customer \n" +
                    "3. Dalam Keadaan Bersih\n" +
                    "4. Dalam Keadaan Rapih dan Tidak Rusak", 0))


        nosAudit.add(NosAudit("Unit Display",
            "HRT\n" +
                    "(* > 3 ruko)",
            "1. HRT harus berfungsi dengan baik\n" +
                    "2. Terdapat buku tamu HRT \n" +
                    "3. Dalam keadaan bersih", 0))

        nosAudit.add(NosAudit("Riding Test",
            "Riding Test Unit ",
            "1. Riding Test Unit wajib tersedia di setiap dealer. Tipe dan jumlah mengacu pada juklak bulanan PT AHM.\n" +
                    "\n" +
                    "2. Unit Riding Test wajib selalu dalam keadaan bersih & layak digunakan. \n" +
                    "\n" +
                    "3. Riding Test Unit wajib di PDI sebelum digunakan oleh konsumen. Bukti PDI harap disimpan.\n" +
                    "\n" +
                    "4. STNK sepeda motor unit test ride wajib atas nama Dealer/Owner/Kepala Cabang.\n" +
                    "\n" +
                    "5. Jarak tempuh sepeda motor (dilihat pada speedometer) berada pada angka yang wajar sesuai penggunaan.\n" +
                    "\n" +
                    "6. Penempatan unit Riding Test adalah di Riding Test Parking Area. \n" +
                    "\n" +
                    "7. Berikut contoh utilisasi unit riding test di Dealer :\n" +
                    " a. Riding Test Prospek Konsumen\n" +
                    " b. Exhibition, Roadshow & Sales Event\n" +
                    " c. Wing SF Weekend Riding\n" +
                    " d. Leisure Touring\n" +
                    " e. Fun Race Track Day", 0))

        nosAudit.add(NosAudit("Riding Test",
            "Riding test guest book",
            "1. Terdapat kolom hari/tanggal, nama, alamat, no telpon, tipe motor yang disukai (tertarik), warna dan saran/kritik.\n" +
                    "2. Tertata rapi dalam folder.\n" +

                    "3. Form riding test guest book dipegang oleh Wing Sales People dan Sales People Regular.", 0))


        nosAudit.add(NosAudit("Riding Test",
            "Riding test parking area",
            "1. Terlihat jelas oleh customer \n" +
                    "2. Dalam keadaan bersih \n" +
                    "3. Terpasang di plat motor bertuliskan \"test ride\" jika motor sedang tidak digunakan.", 0))

        nosAudit.add(NosAudit("Riding Test",
            "Cairan Pembersih dan Wiping Tools Riding Test",
            "1. berisi campuran air bersih dengan sabun (dilarang menggunakan cairan yang mengandung alkohol)\n" +
                    "2. wiping tools microfiber / plas chamois \n" +
                    "3. Terletak di sekitar area riding test", 0))

        nosAudit.add(NosAudit("Riding Test ",
            "HRT Banner (New VinCi)\n" +
                    "(* > 3 ruko)",
            "1. Diletakkan dekat dengan HRT\n" +
                    "2. Terlihat jelas oleh customer \n" +
                    "3. Dalam Keadaan Bersih\n" +
                    "4. Dalam Keadaan Rapih dan Tidak Rusak", 0))

        nosAudit.add(NosAudit("Riding Test",
            "HRT",
            "1. HRT harus berfungsi dengan baik\n" +
                    "2. Terdapat buku tamu HRT \n" +
                    "3. Dalam keadaan bersih", 0))

        nosAudit.add(NosAudit("Indent",
            "Information Board for indent management",
            "1. Indent Information Board dibagi ke dalam 4 bagian, yaitu:\n" +
                    "• Daftar Indent: isi dari form indent dipindahkan ke bagian ini.\n" +
                    "• Feedback: berisi informasi estimasi unit diterima konsumen\n" +
                    "• Form Monitoring: berisi informasi nama sales people, Honda ID, Tanggal Follow-Up 1-3.\n" +
                    "• Update Status: berisi informasi status (Open/Close/Cancel), tanggal pembelian, nomor mesin dan keterangan untuk status indent (mengapa masih open, mengapa cancel).\n" +
                    "\n" +
                    "2. Terdapat 3 pilihan Indent Information Board, yaitu:\n" +
                    "• Menggunakan televisi berukuran minimal 32 inch.\n" +
                    "• Menggunakan printed whiteboard dengan ukuran 120 x 180 cm.\n" +
                    "• Menggunakan kertas print-out yang ditempelkan pada whiteboard. Kertas berukuran minimal A4.\n" +
                    "\n" +
                    "3. Indent Information Board ditempatkan di ruang briefing sales people.", 0))

        nosAudit.add(NosAudit("Indent",
            "Form Indent",
            "1. Available \n" +
                    "2. Berisi keterangan : tanggal, tipe, warna, nama customer, alamat, no. telpon, tanda jadi\n" +
                    "3. Ukuran minimal A4, harus di print\n" +
                    "4. Tersimpan rapi dalam folder\n" +
                    "5. Mengikuti standard dari AHM terupdate", 0))

        nosAudit.add(NosAudit("Negotiation and Dealing",
            "Daftar Perjanjian\n" +
                    "(contohnya)",
            "1. Berisi keterangan :Nama Customer, contact number, Tanggal dan waktu perjanjian, Salespeople yang membuat janji, dokumen yang dibutuhkan, Kebutuhan datang ke Dealer\n" +
                    "2. Semua konten terisi\n" +
                    "3. Di update setiap hari", 0))

        nosAudit.add(NosAudit("Negotiation and Dealing",
            "Album Katalog Brosur ",
            "1. Brosur lengkap dan update untuk semua type di dalam Clear File Holder\n" +
                    "2. Dalam keadaan rapi \n" +
                    "\n", 0))


        nosAudit.add(NosAudit("Negotiation and Dealing",
            "Surat Pesanan Kendaraan",
            "1. Minimal Berisi kolom yang distandardkan AHM\n" +
                    "2. Terdapat nama dan alamat Dealer, logo dealer opsional\n" +
                    "3. Form harus di print dan atau menggunakan sistem (aplikasi)\n" +
                    "4. Ditanda tangan oleh konsumen", 0))

        nosAudit.add(NosAudit("Negotiation and Dealing",
            "Lampiran estimasi Delivery time,STNK / BPKB, dan penjelasan refund,indent  policy",
            "1. Berisikan : estimasi delivery time ke konsumen, estimasi STNK / BPKB, dan kolom checklist bahwa konsumen sudah dijelaskan terkait refund dan indent policy \n" +
                    "2. Ditanda tangan oleh salespeople dan konsumen \n" +
                    "3. Dilampirkan di SPK", 0))

        nosAudit.add(NosAudit("Negotiation and Dealing",
            "Form Pengajuan STNK / Customer Database ",
            "1. Available berupa : apps / hard copy \n" +
                    "2. Terdapat di CS dan salesman\n" +
                    "3. Konten minimal sesuai dari AHM\n" +
                    "4. Tambahan konten dari form CDB lama :   \n" +
                    "    a. Tempat Lahir Konsumen\n" +
                    "    b. Hobi\n" +
                    "    c. Pilihan Pegawai Swasta dan Wiraswasta / Pedagang, bidang / sektor : \n" +
                    "        Agricultural, Industri, Konstruksi, Mining, Jasa, Retail\n" +
                    "    d. Nama Instansi / Usaha\n" +
                    "    e. Alamat, kecamatan, kota, propinsi Instansi / Usaha\n" +
                    "    f. Aktivitas Penjualan (Gathering / Showroom Event, Walk In, Canvassing, Ruang Tunggu AHASS, Pameran Besar, Pameran Menengah / Kecil, Roadshow, SMS/WA/Call (FU CDB), Visit (FU CDB), Social Media, Flyering, Website, Mobile App MD/Dealer, Referral, Contact Center, External Parties (Leasing/Insurance), Virtual Event, Others)\n" +
                    "5. Surat persetujuan database konsumen yang dilengkapi tanda tangan konsumen\n", 0))


        nosAudit.add(NosAudit("Negotiation and Dealing",
            "Database Kartu Keluarga",
            "1. Terdapat di sistem Dealer\n" +
                    "2 Konten minimal sesuai dari AHM, berisi : \n" +
                    "     a. Nomor Kartu keluarga\n" +
                    "     b. NIK\n" +
                    "     c. Nama Lengkap\n" +
                    "     d. Jeni Kelamin\n" +
                    "     e. Tempat lahir\n" +
                    "     f. Tanggal lahir\n" +
                    "     g. Agama\n" +
                    "     h. Pendidikan\n" +
                    "     i. Jenis Pekerjaan\n" +
                    "     j. Status pernikahan\n" +
                    "     k. Status Hubungan dalam keluarga\n" +
                    "     l. Kewarganegaraan", 0))

        nosAudit.add(NosAudit("Negotiation and Dealing",
            "Price List",
            "Ketentuan Pricelist Produk Premium :\n" +
                    "1. Pricelist menggunakan bahan Art Paper, 120 gram dan 4/4 full color (sesuai dengan arahan dari MPA AHM).\n" +
                    "\n" +
                    "2. Pricelist memuat Harga OTR yang sesuai.\n" +
                    "\n" +
                    "3. Penempatan Pricelist adalah di dealing table dalam area Wing Corner.\n" +
                    "\n" +
                    "4. Pricelist wajib untuk dicetak & dipersiapkan.\n" +
                    "\n" +
                    "5. Jumlah Pricelist yang tersedia di dealer mencukupi untuk diberikan ke konsumen.\n" +
                    "\n" +
                    "Ketentuan Pricelist Produk Regular :\n" +
                    "1. Pricelist memuat informasi Nama Dealer, Alamat Dealer, No. Telpon Dealer, Nama Sales People dan No. Kontak Sales People.\n" +
                    "\n" +
                    "2. Pricelist memuat Harga OTR yang sesuai dengan ketentuan AHM dan MD.\n" +
                    "\n" +
                    "3. Pricelist wajib untuk dicetak & dipersiapkan.\n" +
                    "\n" +
                    "4. Pricelist wajib ada di setiap meja dealing.\n" +
                    "\n" +
                    "5. Jumlah Pricelist yang tersedia di dealer mencukupi untuk diberikan ke konsumen.", 0))

        nosAudit.add(NosAudit("Negotiation and Dealing",
            "Service Talk Flyer",
            "1. Desain sesuai standard AHM\n" +
                    "2. Terdapat pada Map Kerja Sales People\n" +
                    "3. Available \n", 0))

        nosAudit.add(NosAudit("Negotiation and Dealing",
            "Guest Book",
            "1. Available berupa : apps / hard copy (minimal berukuran A4)\n" +
                    "2. Terdapat di CS dan salesman\n" +
                    "3. Konten minimal sesuai dari AHM, berisi :\n" +
                    "    a. tanggal\n" +
                    "    b. nama\n" +
                    "    c. alamat email\n" +
                    "    d. sosmed & ID sosial media\n" +
                    "    e. no telp/HP\n" +
                    "    f. alamat lengkap\n" +
                    "    g. varian motor dan warna\n" +
                    "    h. status Hot/Medium/Low\n" +
                    "    i. New Cust/RO\n" +
                    "    j. Cash/credit\n" +
                    "    k. follow up 1-3\n" +
                    "    l. keterangan.\n" +
                    "4. Tidak berserakan,tertata rapi\n" +
                    "5. Dimiliki setiap Salespeople\n" +
                    "6. Dilaporkan ke PIC Dealer setiap hari dan diinput ke sistem/excel setiap hari sebagai data prospek (data yang di dalam buku sesuai dengan data yang di excel / sistem)", 0))


        nosAudit.add(NosAudit("Customer Facilities",
            "Ashtray",
            "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "3. Diletakkan di area smoking", 0))

        nosAudit.add(NosAudit("Customer Facilities",
            "Charging area\n" +
                    "(Nice to have)",
            "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak / Berfungsi\n" +
                    "3. Terdapat sticker informasi untuk charging area\n" +
                    "4. Design direkomendasikan mengikuti juklak AHM\n", 0))

        nosAudit.add(NosAudit("Customer Facilities",
            "Jaringan Internet Wi-Fi & Design Wi-Fi Sticker \n",
            "1. Terdapat sticker informasi free wifi sesuai dengan desain di samping\n" +
                    "     a. New ViCi : boleh menggunakan desain No. 1\n" +
                    "     b. Old ViCi : wajib menggunakan desain No. 2\n" +
                    "2. Informasi terbaca jelas oleh konsumen (tanpa penghalang)\n" +
                    "3. Terdapat informasi Username dan Password wifi untuk konsumen\n" +
                    "4. Jaringan wifi berfungsi dengan baik", 0))

        nosAudit.add(NosAudit("Customer Facilities",
            "Meja Observasi \n" +
                    "(New VinCi)\n",
            "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "3. Material dan design mengikuti rekomendasi dari AHM : warna putih,dilengkapi laci untuk meletakkan tas\n" +
                    "4. Peletakan sesuai dengan denah yang sudah disepakati bersama\n" +
                    "5. Terdapat hand sanitizer\n", 0))

        nosAudit.add(NosAudit("Customer Facilities",
            "Kursi Observasi\n" +
                    "(New VinCi)",
            "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "3. Jumlah kursi (include sofa)  = 2 x jumlah pit \n" +
                    "4. Material dan design mengikuti rekomendasi dari AHM : bewarna hitam,merah dalam 1 meja ( selang seling ), dengan sandaran tangan,tanpa roda\n" +
                    "5. Peletakan sesuai dengan denah yang sudah disepakati bersama\n" +
                    "6. diberi jarak antar kursi sesuai juklak No 136/AHM/MPA/IV/2020", 0))


        nosAudit.add(NosAudit("Customer Facilities",
            "Snack Corner\n" +
                    "(New VinCi)\n" +
                    "(nice to have)",
            "1. Tersedia snack dan minuman\n" +
                    "2. Dalam Keadaan Bersih\n" +
                    "3. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "4. Material dan design dianjurkan  mengikuti rekomendasi dari AHM \n", 0))

        nosAudit.add(NosAudit("Customer Facilities",
            "Toilet",
            "1. Dalam Keadaan Bersih\n" +
                    "2. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "3. Memilki Item minimal :\n" +
                    "  - Kloset Duduk / Jongkok\n" +
                    "  - Terdapat Bak / Penampung air / Hand Shower\n" +
                    "  - Terdapat Tissue\n" +
                    "  - Terdapat Wastafel (didalam / diluar dinding toilet)\n" +
                    "  - Terdapat Cermin (seukuran minimal 1 muka orang dewasa)\n" +
                    "  - Terdapat Floor Drain dan lubangnya tertutup rapih\n" +
                    "  - Terdapat Tong Sampah\n" +
                    "  - Terdapat Rak Hambalan / Hook Pengantung\n" +
                    "4. Terdapat checklist kebersihan minimal pada jam 07.30, 12.00, 15.00", 0))

        nosAudit.add(NosAudit("Payment",
            "Bukti Pembayaran ",
            "1. Bukti pembayaran harus mencantumkan nama, alamat dealer dan No. Telp Dealer (logo dealer opsional).\n" +
                    "\n" +
                    "2. Bukti pembayaran harus di-print dengan ukuran A5 berbahan kertas karbon dan terdapat cap/stempel resmi dealer.\n" +
                    "\n" +
                    "3. Bukti pembayaran terletak di area kasir.", 0))


        nosAudit.add(NosAudit("Payment",
            "Pre Delivery Safety Advice (PDSA) Flyer",
            "1. Flyer wajib dicetak dengan ukuran A5 (print 2 sisi bolak-balik)\n" +
                    "2. Dimasukan di dalam acryic mini display \n" +
                    "3. Diletakan di loket kasir yang mudah terbaca untuk konsumen dan kasir", 0))

        nosAudit.add(NosAudit("Payment",
            "Direction Sign\n" +
                    "(New VinCi)",
            "1. Terlihat jelas oleh customer\n" +
                    "2. Dalam Keadaan Bersih\n" +
                    "3. Dalam Keadaan Rapih dan Tidak Rusak\n" +
                    "\n" +
                    "Ket : Menempel pada dinding / pada langit - langit Dealer", 0))

        nosAudit.add(NosAudit("",
            "Name Card",
            "Name card 2 sisi :\n" +
                    "- Bagian depan : informasi Nama & Alamat Dealer, Nama dan No. Telp. sales people, Nama dan No. Telp. Service Advisor, Logo Wing Dealer, Nama Kepala Cabang (No Hp optional), Foto (optional)\n" +
                    "\n" +
                    "- Bagian Belakang : Logo Wing, No Telp Penjualan dan Pemeliharaan / Bengkel, sosial media dealer (optional)\n", 0))

        nosAudit.add(NosAudit("",
            "Data Follow-Up RO unit Sepeda Motor Honda",
            "1. Data RO penjualan unit SMH yang dibagi oleh PIC CRM \n" +
                    "2. Available berupa apps / file ms. Excel / hard copy\n" +
                    "3. Terdapat di CS / sales people / telemarketing / admin CRM H1\n" +
                    "4. Konten minimal berisi : \n" +
                    "     a. nama\n" +
                    "     b. alamat\n" +
                    "     c. no hp\n" +
                    "     d. pekerjaan\n" +
                    "     e. tipe & tahun motor sebelumnya\n" +
                    "     f. hasil follow up 1, 2, 3\n" +
                    "     g. keterangan", 0))


        nosAudit.add(NosAudit("",
            "Form Follow-Up RO unit Sepeda Motor Honda dari ruang tunggu AHASS",
            "1. Data follow up ruang tunggu AHASS\n" +
                    "2. Available berupa apps / file ms. Excel / hard copy\n" +
                    "3. Terdapat di CS \n" +
                    "4. Konten minimal berisi : \n" +
                    "     a. nama\n" +
                    "     b. no hp\n" +
                    "     c. tipe & tahun motor sebelumnya\n" +
                    "     d. dealer pembelian SMH sebelumnya (dealer sendiri / dealer lain)\n" +
                    "     e. tanggal follow up\n" +
                    "     f. hasil follow up (hot/medium/low)\n" +
                    "     g. varian motor dan warna (khusus konsumen yang tertarik)\n" +
                    "     h. keterangan ", 0))


        nosAudit.add(NosAudit("Warehouse",
            "FIFO ",
            "1. Menjalankan FIFO: Dijalankan berdasarkan nomor aging per bulan saat unit motor diterima Dealer\n" +
                    "2. Unit motor defect/ Non Ready for Sales dipisahkan\n" +
                    "3. Penyusunan unit mempertimbangkan jarak antar motor (tidak bersentuhan antara unit motor)\n" +
                    "4. Penyusunan unit per tipe per warna \n" +
                    "5. Menggunakan alat bantu visual Tag FIFO (digantung di motor dan berdasarkan Urutan Huruf) atau jika dealer sudah mempunyai sistem, dapat dibuktikan pada saat audit)", 0))

        nosAudit.add(NosAudit("Warehouse",
            "Border Line",
            "1. Jelas dan terlihat ", 0))

        nosAudit.add(NosAudit("Warehouse",
            "Kondisi Warehouse",
            "1. Sesuai dengan kebutuhan kapasitas gudang unit = Dealer Stock days target MD * Target Daily Sales Dealer (untuk dealer stock days nya apakah mau dibuat sheet baru untuk informasi dealer stock days per MD nya)\n" +
                    "2. Sesuai dengan kebutuhan luas gudang (m2)= Kapasitas gudang (unit)* 2.2 m2\n" +
                    "3. Dalam keadaan rapi dan bersih", 0))

        nosAudit.add(NosAudit("Warehouse",
            "Kondisi Warehouse",
            "1. Sesuai dengan kebutuhan kapasitas gudang unit = Dealer Stock days target MD * Target Daily Sales Dealer (untuk dealer stock days nya apakah mau dibuat sheet baru untuk informasi dealer stock days per MD nya)\n" +
                    "2. Sesuai dengan kebutuhan luas gudang (m2)= Kapasitas gudang (unit)* 2.2 m2\n" +
                    "3. Dalam keadaan rapi dan bersih", 0))


        nosAudit.add(NosAudit("Warehouse",
            "Premium Zone ",
            "1. Premium zone sebagai tempat meletakkan unit premium\n" +
                    "2. Premium zone dibatasi oleh border line berwarna kuning cerah\n" +
                    "3. Terdapat SIGNING “Premium Zone” yang ditempel di dinding atau digantung di area tersebut", 0))

        nosAudit.add(NosAudit("Delivery",
            "Kelengkapan Standard Unit",
            "1. Pengiriman unit  dilengkapi dengan KSU (buku pedoman pemilik, helm, tool set dan buku servis)", 0))

        nosAudit.add(NosAudit("Delivery",
            "Appreciation card ",
            "1. Berisi : nomor kontak service booking, email dealer,nomor telepon,nomor kontak showroom,nomor kontak AHASS, nomor hotline Dealer / Main Dealer \n" +
                    "2. Tercetak \n" +
                    "3. Ukuran sebesar KTP / SIM (setelah dilipat)", 0))


        nosAudit.add(NosAudit("Delivery",
            "Delivery Car",
            "1. Minimal Jumlah delivery car = Round up (Target jualan per bulan /200)\n" +
                    "2. Kondisi mobil dalam keadaan bersih\n" +
                    "3. Terdapat refreshing tissue untuk delivery man\n" +
                    "*apabila menggunakan pihak ketiga, maka design tetap mengikuti standard ", 0))

        nosAudit.add(NosAudit("Delivery",
            "Cairan Pembersih dan Wiping Tools Delivery ",
            "1. berisi campuran air bersih dengan sabun (dilarang menggunakan cairan yang mengandung alkohol)\n" +
                    "2. wiping tools microfiber / plas chamois \n" +
                    "3. Terletak di dalam mobil delivery", 0))

        nosAudit.add(NosAudit("Follow Up First Call ",
            "Telepon/Handphone",
            "1. Berfungsi dengan baik\n" +
                    "2. Dedicated untuk kegiatan follow up konsumen", 0))

        nosAudit.add(NosAudit("Follow Up First Call ",
            "",
            "1. Available berupa apps / file ms. Excel\n" +
                    "2. Terdapat di admin CRM H1\n" +
                    "3. Minimal berisi keterangan : \n" +
                    "     a. no frame\n" +
                    "     b. nama konsumen\n" +
                    "     c. tanggal lahir\n" +
                    "     d. no hp/telp\n" +
                    "     e. alamat\n" +
                    "     f. agama\n" +
                    "     g. email\n" +
                    "     h. terima kasih\n" +
                    "     i. verifikasi data\n" +
                    "     j. reminder service (KPB 1)\n" +
                    "     k. no tlp dealer\n" +
                    "     l. tanggal follow up\n" +
                    "     m. status\n", 0))


        nosAudit.add(NosAudit("Follow Up Customer Database",
            "WA Broadcast dan SMS Broadcast software\n",
            "1. Berfungsi dengan baik\n" +
                    "2. cek simulasi pengiriman dan histori pengiriman terupdate minimal 1 hari yang lalu (di hari kerja)\n" +
                    "3. SMS hanya digunakan untuk konsumen tidak punya WA", 0))

        nosAudit.add(NosAudit("Follow Up Customer Database",
            "Komputer + Modem",
            "1. Komputer dedicated untuk kegiatan CRM\n" +
                    "2. Berfungsi dengan baik ", 0))



        nosAudit.add(NosAudit("Follow Up Customer Database",
            "Data Follow-Up Reminder Servis by SMS / WA (rutinCRM/exclude booking service)\n",
            "1. Data reminder SMS / WA untuk servis KPB 1 dari data penjualan\n" +
                    "2. Available berupa apps / file ms. Excel / hard copy\n" +
                    "3. Konten minimal berisi : \n" +
                    "     a. nama konsumen \n" +
                    "     b. no hp\n" +
                    "     c. pekerjaan\n" +
                    "     d. tipe & tahun motor sebelumnya\n" +
                    "     f. tanggal follow up SMS / WA \n" +
                    "     g. hasil follow up SMS / WA (misal terkirim/ tidak terkirim / booking servis/ servis kunjung / dll)\n" +
                    "     h. keterangan\n" +
                    "\n" +
                    "Note : proses reminder selalu update, minimal reminder WA / SMS dilakukan 1 hari yang lalu (di hari kerja) ", 0))



        nosAudit.add(NosAudit("Follow Up Customer Database",
            "Form monitoring / rekapan PIC CRM dari hasil follow up konsumen per SC / salesman",
            "1. Available berupa apps / file ms. Excel\n" +
                    "2. terdapat di PIC CRM Dealer\n" +
                    "3. Minimal berisi keterangan : \n" +
                    "     a. nama salespeople\n" +
                    "     b. jumlah data yang dibagikan ke salespeople\n" +
                    "     c. jumlah konsumen yang dapat dihubungi\n" +
                    "     d. jumlah konsumen yang RO\n" +
                    "     e. jumlah konsumen yang gagal\n" +
                    "     f. jumlah konsumen yang menunggu / belum ada keputusan\n" +
                    "     g. keterangan", 0))



        nosAudit.add(NosAudit("Follow Up Customer Database",
            "Report Niguri ke MD (full cycle)",
            "Tersedia report niguri secara lengkap di dealer (dealer information, niguri H1 to H2, niguri H2 to H1, detil konsumen RO)/ bulan (cek email)", 0))



        nosAudit.add(NosAudit("Follow Up Customer Database",
            "Form Rekap Data Pending\n",
            "1. Tersedia list konsumen yang pending dari hasil prospect  konsumen dan selalu update \n" +
                    "2. Terdapat di PIC CRM Dealer\n" +
                    "3. Konten minimal berisi : \n" +
                    "    a. nama\n" +
                    "    b. no hp\n" +
                    "    c. alamat\n" +
                    "    d. pekerjaan konsumen\n" +
                    "    e. progress hasil follow up\n" +
                    "    f. waktu rencana pembelian SMH (hot / medium / low)\n" +
                    "    g. keterangan", 0))



        nosAudit.add(NosAudit("Follow Up Customer Database",
            "SOP (Workflow) CRM Dealer",
            "1. Dealer memiliki SOP CRM (H1 ke H2 dan H2 ke H1) yang ditempel di ruangan kacab atau ruangan CRM\n" +
                    "2. SOP CRM Dealer dilengkapi dengan nama-nama PIC, admin CRM, atau jabatan yang bertanggung jawab terhadap proses CRM\n", 0))


        nosAudit.add(NosAudit("Follow Up Customer Database",
            "Panduan Follow Up ",
            "1. Script Follow Up (Call/ SMS / WA) : \n" +
                    "    a. Penawaran Pembelian Unit SMH\n" +
                    "    b. Thank you call\n" +
                    "    c. Informasi STNK / BPKB jadi\n" +
                    "    d. Reminder Service \n" +
                    "    e. Reminder booking service\n" +
                    "    f. Follow Up After Service\n" +
                    "2. Panduan Waktu Telepon (Golden time)\n" +
                    "3. Dapat berupa hard copy atau soft copy ", 0))



        nosAudit.add(NosAudit("Ruang Briefing Sales People",
            "Ruang Briefing Sales People",
            "1. Minimal luas ruangan 9m2 untuk 1 team terdiri dari 1 koordinator dan 8 salesman\n" +
                    "2. Kursi dan meja menyesuaikan jumlah sales people dalam  1 team\n" +
                    "3. Sebaiknya lokasi berada di lantai 2, tetapi diperbolehkan berlokasi di lantai 1 dengan syarat tidak terlihat dan tidak terdengar oleh konsumen\n" +
                    "4. Terdapat Sales Wallboard (berisi Sales per Sales People daily, weekly, monthly, CoE, Info sales program, info insentif dan unit indent))\n" +
                    "5. Terdapat Poster Product Comparisson (Start Semester 2- 2021)\n" +
                    "6. Terdapat Sales Talk Product Knowledge untuk refreshment\n" +
                    "7. Jika ruangan tidak satu atap dengan dealer, jarak paling jauh adalah 10 menit jalan kaki\n" +
                    "8. Jika jumlah tim >3 tim, briefing dapat dilakukan bergantian\n" +
                    "9. Terdapat jeda pembatas 1m antar kursi atau antar sales people\n" +
                    "10. Terdapat hand sanitizer \n", 0))



        nosAudit.add(NosAudit("Ruang Briefing Sales People",
            "TV LED ",
            "1. Ukuran min. 32 inch\n" +
                    "2. Dapat memutar Video NOS & Video Product Knowledge\n" +
                    "3. Dapat digunakan menampilkan Sales Wallboard", 0))


        nosAudit.add(NosAudit("Sales Tools",
            "My Hero Application",
            "1. Terdapat pada setiap smartphone sales force dan sudah berhasil login\n" +
                    "2. Digunakan dalam mempelajari product knowledge dan informasi lain terkait sepeda motor Honda serta sebagai alat bantu dalam menjelaskan produk Honda ke konsumen.", 0))


        nosAudit.add(NosAudit("Sales Tools",
            "Map Kerja Sales People",
            "1. Dimiliki setiap sales people (1 Sales People memiliki 1 set utuh)\n" +
                    "2. Berisi : Service Talk Flyer, Community Flyer, Flyer Acc & App, Flyer Customizing Part, Flyer Rekomendasi Bengkel Modifikasi, Pricelist, Sales Program List, Alat tulis", 0))


        nosAudit.add(NosAudit("Sales Tools",
            "Kalender Product",
            "a. Wajib dimiliki setiap Sales Counter dan digunakan ketika menjelaskan product ke konsumen\n" +
                    "b. Diletakkan di meja dealing table\n" +
                    "c. Desain Standard dari AHM (baik map maupun isinya)\n" +
                    "d. Untuk tipe - tipe baru, yang belum ada design dari AHM, Dealer menggunakan print out dari sales talk product knowledge", 0))


        nosAudit.add(NosAudit("NOS New Normal",
            "Rekap Harian Suhu Tubuh Karyawan",
            "1. Terletak di Ruang Kepala Cabang\n" +
                    "2. Diupdate setiap hari untuk seluruh karyawan dealer (Showroom + AHASS)", 0))


        nosAudit.add(NosAudit("NOS New Normal",
            "Daftar Suhu Tubuh Harian Sales People\n",
            "Terletak pada sekitar area pintu masuk showroom dan terlihat oleh konsumen", 0))



        return nosAudit
    }


    fun generateDataH1People(): List<NosAudit> {

        val nosAudit = ArrayList<NosAudit>()

        nosAudit.add(NosAudit("Kepala Cabang",
            " Jumlah ",
            "Dealer wajib memiliki 1 Kepala Cabang ", 0))

        nosAudit.add(NosAudit("Kepala Cabang",
            "Training",
            "Sudah melakukan training (dengan masa kerja) :\n" +
                    "a. Wing Sales People Training (Wajib dilakukan pada usia kerja 0 - 1 bulan)\n" +
                    "\n" +
                    "* Exist, Done : Sudah lulus training (Nilai >= 80)\n" +
                    "* Not Exist : Belum lulus Training", 0))
        nosAudit.add(NosAudit("Kepala Cabang",
            "Training",
            "b. SFT - Product Knowledge (All Product Launched)\n" +
                    "\n" +
                    "* Exist, Done :   Sudah lulus training (Nilai >= 70)\n" +
                    "* Not Exist :   Belum lulus Training", 0))

        nosAudit.add(NosAudit("Kepala Cabang",
            "Training",
            "c. Customer Service for leader 1 - Complaint Handling (Wajib dilakukan pada usia kerja > 3 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Kepala Cabang",
            "Training",
            "d. Customer Service for leader 2 (Wajib dilakukan pada usia kerja > 6 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Kepala Cabang",
            "Training",
            "e. TFT Basic Orientation Traning - BOT (Wajib dilakukan pada usia kerja > 1 bulan) sebelum training NOS\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))
        nosAudit.add(NosAudit("Kepala Cabang",
            "Training",
            "g. Parts Management Knowledge (Wajib dilakukan pada usia kerja 3 bulan atau lebih)\n" +
                    "\n" +
                    "* Exist, Done :   Sudah ikut training\n" +
                    "* Not Exist :   Belum ikut training\n" +
                    "* N/A : Apabila masa kerja belum memenuhi", 0))
        nosAudit.add(NosAudit("Kepala Cabang",
            "Sales Tools",
            "Kepala Cabang memiliki sales tools berupa :\n" +
                    "Aplikasi MyHero (berhasil Login)\n" +
                    "\n" +
                    "Ketika menjelaskan konsumen diutamakan menggunakan Aplikasi MyHero (digital)", 0))

        nosAudit.add(NosAudit("Kepala Cabang",
            "Product Knowledge Test",
            "Dapat menjelaskan konsep produk, design produk, performance dan min. 5 - 7 fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge dengan menggunakan metode SPWA\n" +
                    "\n" +
                    "Alternatif Tipe Produk : Premium Product\n" +
                    "\n" +
                    "* Exist, Done : Dapat menjelaskan min. 5 - 7 fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge dengan menggunakan metode SPWA & C-F-A\n" +
                    "\n" +
                    "* Not Exist  : Tidak Dapat menjelaskan min. 5 - 7 fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge dengan menggunakan metode SPWA & C-F-A", 0))
        nosAudit.add(NosAudit("Supervisor",
            " Jumlah ",
            "1 Spv membawahi = 5 Coord SM\n" +
                    "Target  Jmlah Coord SM (A)\n" +
                    "Target  Jml Spv = A / 5\n" +
                    "\n" +
                    "* Exist, Done :  Jml Spv ≥ Target  Jml Spv \n" +
                    "* Exist, Not Done : Target  Jml Spv > 0 dan  Jml Spv < Target  Jml Spv\n" +
                    "* Not Done : Target  Jml Spv > 0 dan  Jml Spv = 0\n" +
                    "* N/A : Target  Jml Spv = 0 dan  Jml Spv = 0", 0))

        nosAudit.add(NosAudit("Supervisor",
            "Training",
            "Sudah melakukan training (dengan masa kerja) :\n" +
                    "a. Wing Sales People Training (Wajib dilakukan pada usia kerja 0 - 1 bulan)\n" +
                    "\n" +
                    "* Exist, Done : Sudah lulus training (Nilai >= 80)\n" +
                    "* Not Exist : Belum lulus Training", 0))

        nosAudit.add(NosAudit("Supervisor",
            "Training",
            "b. SFT - Product Knowledge (All Product Launched)\n" +
                    "\n" +
                    "* Exist, Done :   Sudah lulus training (Nilai >= 70)\n" +
                    "* Not Exist :   Belum lulus Training\n", 0))

        nosAudit.add(NosAudit("Supervisor",
            "Training",
            "c. Customer Service for leader 1 - Complaint Handling (Wajib dilakukan pada usia kerja > 3 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))
        nosAudit.add(NosAudit("Supervisor",
            "Training",
            "d. Customer Service for leader 2 (Wajib dilakukan pada usia kerja > 6 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Supervisor",
            "Training",
            "e. TFT Basic Orientation Traning - BOT (Wajib dilakukan pada usia kerja > 1 bulan) sebelum training NOS\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Supervisor",
            "Training",
            "f. Training Network Operational Standard (Wajib dilakukan pada usia kerja > 1 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))
        nosAudit.add(NosAudit("Supervisor",
            "Training",
            "Notes :\n" +
                    "* Apabila Target  Jml Spv = 0 dan  Jmlah Spv = 0, maka untuk penilaian diisii N/A\n" +
                    "* Apabila Target  Jml Spv > 0 dan  Jmlah Spv = 0, maka penilaian tetap dilakukan", 0))

        nosAudit.add(NosAudit("Supervisor",
            "Product Knowledge Test",
            "Dapat menjelaskan konsep produk, design produk, performance dan min. 5 - 7 fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge.\n" +
                    "\n" +
                    "Alternatif Tipe Produk : Premium Product\n" +
                    "\n" +
                    "* Exist, Done : Dapat menjelaskan min. 5 - 7 fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge dengan menggunakan metode SPWA & C-F-A\n" +
                    "\n" +
                    "* Not Exist  : Tidak Dapat menjelaskan min. 5 - 7 fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge dengan menggunakan metode SPWA & C-F-A", 0))

        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "  Jumlah ",
            "1 Coordinator SC membawahi = 4 SC\n" +
                    "Target  Jmlah SC (A)\n" +
                    "Target  Jml Coordinator SC = A / 4\n" +
                    "* Jabatan ini adalah fungsi yang dapat dirangkap oleh Sales Counter\n" +
                    "\n" +
                    "* Exist, Done : Actual Jml Coord SC ≥ Target  Jml Coord SC\n" +
                    "* Exist, Not Done : Target Jml Coord SC > 0 dan Actual Jml Coord SC < Target Jml Coord SC \n" +
                    "* Not Done : Target Jml Coord SC > 0 dan Actual Jml Coord SC = 0\n" +
                    "* N/A : Target Jml Coord SC = 0 dan Actual Jml Coord SC = 0", 0))

        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Training",
            "a. SFT - Product Knowledge (All Product Launched)\n" +
                    "\n" +
                    "* Exist, Done :   Sudah lulus training (Nilai >= 70)\n" +
                    "* Not Exist :   Belum lulus Training\n", 0))
        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Training",
            "b. Customer Service For FLP Training (Wajib dilakukan pada usia kerja > 3 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Training",
            "c. Basic Orientation Traning - BOT (Wajib dilakukan pada usia kerja > 1 bulan) sebelum training NOS\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Training",
            "d. Salesmanship Training  (Wajib dilakukan pada usia kerja > 6 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))
        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Training",
            "e. Training Network Operational Standard (Wajib dilakukan pada usia kerja > 1 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit(" Sales Counter",
            "Standard Penampilan",
            "1.Wajah : \n" +
                    "Pria : ,wajah terlihat segar/tidak berminyak/tidak kusam, kumis/jenggot rapi (tdk dipanjangkan)\n" +
                    "\n" +
                    "Wanita :  Rias Wajah menggunakan make up (bedak,alis,eye shadow,blush on,lipstik sesuai standar)\n" +
                    "\n" +
                    "2.Rambut : \n" +
                    "Pria : Rambut Pendek Rapi, tidak menutupi mata dan tidak menyentuh kerah baju dan telinga\n" +
                    "\n" +
                    "Wanita :  Rambut rapi / tidak menutupi pandangan (panjang rambut lebih dari sebahu wajib dikuncir ponytail)\n" +
                    "\n" +
                    "3.Wewangian dan kebersihan:\n" +
                    "Aroma tubuh tidak mengganggu, tangan bersih, kuku tangan dan kuku kaki bersih. Untuk pria kuku pendek rapi.\n", 0))

        nosAudit.add(NosAudit(" Sales Counter",
            "Standard Penampilan",
            "4. Seragam : \n" +
                    "\n" +
                    "Ketentuan :\n" +
                    " Senin & Rabu : Seragam Honda berkerah merah,Celana / rok hitam \n" +
                    " Selasa & Kamis : Seragam Honda berkerah putih,Celana / rok hitam  \n" +
                    "Jumat : baju khas daerah / batik (batik Dealer), Celana / Rok Hitam\n" +
                    "Sabtu & Minggu : casual (berkerah) atau sesuai kebijakan masing2 dealer\n" +
                    "(kondisi Baju : Rapi, Bersih, Warna tidak kusam, tidak robek, dan logo Honda terlihat)\n" +
                    "* Sesuai dengan aturan seragam dari HC3 AHM\n" +
                    "\n" +
                    "5.Atribut  : \n" +
                    "- Senin - Jumat : Wanita menggunakan sepatu pantofel warna hitam heel minimum 5 cm kecuali wanita hamil\n" +
                    "- Senin - Jumat : Pria menggunakan sepatu pantofel warna hitam\n" +
                    "- Sabtu - Minggu atau saat berada di lapangan : Sepatu Kets  warna Netral (Putih, Hitam, Abu-abu, Merah Maroon, Biru Tua, Putih)\n" +
                    "- ID Card dengan Kalung Honda Merah dan ID Card Holder WSP dari AHM\n" +
                    "- Pin One Heart di dada kiri\n" +
                    "- Jam Tangan Kulit / Karet / Canvas Warna Hitam / Coklat Tua / Gelap\n" +
                    "\n" +
                    "6. Aksesories :\n" +
                    "Pria : aksesoris tidak berlebihan (tidak lebih dari 5 titik)\n" +
                    "Wanita : aksesoris tidak berlebihan (tidak lebih dari 7 titik)\n" +
                    "\n" +
                    "Menggunakan Masker polos atau tidak bermotif.\n" +
                    "-Masker kesehatan (warna bebas)\n" +
                    "-Masker kain disesuaikan dengan warna unsur seragam, yaitu abu-abu/putih/merah/hitam* *Kecuali Jum'at-Sabtu-Minggu\n" +
                    "Menggunakan face shield (jika berinteraksi dengan konsumen)", 0))

        nosAudit.add(NosAudit(" Sales Counter",
            "Sales Tools",
            "Memiliki sales tools berupa :\n" +
                    "1. Aplikasi MyHero (berhasil Login)\n" +
                    "2. Map Kerja Sales People yang berisi Service Talk Flyer, Community Flyer, Flyer Acc & App, Flyer Customizing Part, Flyer Rekomendasi Bengkel Modifikasi, Pricelist, Sales Program List, Alat tulis. \n" +
                    "3. Kalendar Produk\n" +
                    "\n" +
                    "Ketika menjelaskan ke konsumen di utamakan menggunakan aplikasi MyHero (digital) ", 0))
        nosAudit.add(NosAudit(" Sales Counter",
            "Product Knowledge Test",
            "Alternatif Tipe Produk : Regular Product\n" +
                    "\n" +
                    "* Exist, Done : Dapat menjelaskan min. 5 – 7  fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge dengan menggunakan metode SPWA & C-F-A\n" +
                    "\n" +
                    "* Not Exist  : Tidak Dapat menjelaskan min. 5 – 7  fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge dengan menggunakan metode SPWA & C-F-A\n", 0))

        nosAudit.add(NosAudit("Coordinator Sales Man",
            "Jumlah ",
            "1 Coordinator SM membawahi < = 10 SM\n" +
                    "Target  Jmlah SM (A)\n" +
                    "Target  Jml Coordinator SM = A / 10\n" +
                    "\n" +
                    "* Exist, Done :  Jml Coord SM ≥ Target  Jml Coord SM\n" +
                    "* Exist, Not Done : Target  Jml Coord SM > 0 dan  Jml Coord SM < Target  Jml Coord SM \n" +
                    "* Not Done : Target  Jml Coord SM > 0 dan  Jml Coord SM = 0\n" +
                    "* N/A : Target  Jml Coord SM = 0 dan  Jml Coord SM = 0", 0))

        nosAudit.add(NosAudit("Coordinator Sales Man",
            "Training",
            "a. SFT - Product Knowledge (All Product Launched)\n" +
                    "\n" +
                    "* Exist, Done :   Sudah lulus training (Nilai >= 70)\n" +
                    "* Not Exist :   Belum lulus Training\n", 0))
        nosAudit.add(NosAudit("Coordinator Sales Man",
            "Training",
            "b. Customer Service For FLP Training (Wajib dilakukan pada usia kerja > 3 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Coordinator Sales Man",
            "Training",
            "c. Basic Orientation Traning - BOT (Wajib dilakukan pada usia kerja > 1 bulan) sebelum training NOS\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Coordinator Sales Man",
            "Training",
            "d. Salesmanship Training  (Wajib dilakukan pada usia kerja > 6 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Coordinator Sales Man",
            "Training",
            "e. Training Network Operational Standard (Wajib dilakukan pada usia kerja > 1 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))


        nosAudit.add(NosAudit("Coordinator Sales Man",
            "Standard Penampilan",
            "1.Wajah : \n" +
                    "Pria : ,wajah terlihat segar/tidak berminyak/tidak kusam, kumis/jenggot rapi (tdk dipanjangkan)\n" +
                    "\n" +
                    "Wanita :  Rias Wajah menggunakan make up (bedak,alis,eye shadow,blush on,lipstik sesuai standar)\n" +
                    "\n" +
                    "2.Rambut : \n" +
                    "Pria : Rambut Pendek Rapi, tidak menutupi mata dan tidak menyentuh kerah baju dan telinga\n" +
                    "\n" +
                    "Wanita :  Rambut rapi / tidak menutupi pandangan (panjang rambut lebih dari sebahu wajib dikuncir ponytail )\n" +
                    "\n" +
                    "3.Wewangian dan kebersihan:\n" +
                    "Aroma tubuh tidak mengganggu, tangan bersih, kuku tangan dan kuku kaki bersih. Untuk pria kuku pendek rapi.\n", 0))

        nosAudit.add(NosAudit("Coordinator Sales Man",
            "Sales Tools",
            "Memiliki sales tools berupa :\n" +
                    "1. Aplikasi MyHero (berhasil Login)\n" +
                    "2. Map Kerja Sales People yang berisi Service Talk Flyer, Community Flyer, Flyer Acc & App, Flyer Customizing Part, Flyer Rekomendasi Bengkel Modifikasi, Pricelist, Sales Program List, Alat tulis. \n" +
                    "\n" +
                    "Ketika menjelaskan ke konsumen di utamakan menggunakan aplikasi MyHero (digital) ", 0))

        nosAudit.add(NosAudit("Coordinator Sales Man",
            "Product Knowledge Test",
            "Dapat menjelaskan konsep produk, design produk, performance dan min. 5 - 7 fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge \n" +
                    "\n" +
                    "Alternatif Tipe Produk : Product Terbaru (antara 3 yang terakhir launching)\n" +
                    "\n" +
                    "* Exist, Done : Dapat menjelaskan min. 5 - 7 fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge dengan menggunakan metode SPWA & C-F-A\n" +
                    "\n" +
                    "* Not Exist  : Tidak Dapat menjelaskan min. 5 - 7 fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge dengan menggunakan metode SPWA & C-F-A", 0))
        nosAudit.add(NosAudit("Sales Man",
            "  Jumlah ",
            "Target Sales Dealer (A)\n" +
                    "Target Kontribusi Sales by SM (B)\n" +
                    "Target Productivity SM (C)\n" +
                    "(Range Target Productivity SM = 5-12 unit (disesuaikan dengan target masing-masing MD)\n" +
                    "\n" +
                    "Target  Jml SM = (A * B) / C\n" +
                    "\n" +
                    "* Exist, Done :  Jml SM ≥ Target  Jml SM\n" +
                    "* Exist, Not Done : Target  Jml SM > 0 dan  Jml SM < Target  Jml SM \n" +
                    "* Not Done : Target  Jml SM > 0 dan  Jml SM = 0", 0))

        nosAudit.add(NosAudit("Sales Man",
            "Training",
            "a. SFT - Product Knowledge (All Product Launched)\n" +
                    "\n" +
                    "* Exist, Done :   Sudah lulus training (Nilai >= 70)\n" +
                    "* Not Exist :   Belum lulus Training\n", 0))

        nosAudit.add(NosAudit("Sales Man",
            "Training",
            "b. Customer Service For FLP Training (Wajib dilakukan pada usia kerja > 3 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Sales Man",
            "Training",
            "c. Basic Orientation Traning - BOT (Wajib dilakukan pada usia kerja > 1 bulan) sebelum training NOS\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))
        nosAudit.add(NosAudit("Sales Man",
            "Training",
            "d. Salesmanship Training  (Wajib dilakukan pada usia kerja > 6 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Sales Man",
            "Training",
            "e. Training Network Operational Standard (Wajib dilakukan pada usia kerja > 1 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Sales Man",
            "Standard Penampilan",
            "1.Wajah : \n" +
                    "Pria : wajah terlihat segar/tidak berminyak/tidak kusam, kumis/jenggot rapi (tdk dipanjangkan)\n" +
                    "\n" +
                    "Wanita :  Rias Wajah menggunakan make up (bedak,alis,eye shadow,blush on,lipstik sesuai standar)\n" +
                    "\n" +
                    "2. Rambut : \n" +
                    "Pria : Rambut Pendek Rapi, tidak menutupi mata dan tidak menyentuh kerah baju dan telinga\n" +
                    "\n" +
                    "Wanita :  Rambut rapi / tidak menutupi pandangan (panjang rambut lebih dari sebahu wajib dikuncir ponytail)\n" +
                    "\n" +
                    "3.Wewangian dan kebersihan:\n" +
                    "Aroma tubuh tidak mengganggu, tangan bersih, kuku tangan dan kuku kaki bersih. Untuk pria kuku pendek rapi.", 0))
        nosAudit.add(NosAudit("Sales Man",
            "Standard Penampilan",
            "4. Seragam : \n" +
                    "\n" +
                    "Ketentuan :\n" +
                    " Senin & Rabu : Seragam Honda berkerah merah,Celana / rok hitam \n" +
                    " Selasa & Kamis : Seragam Honda berkerah putih,Celana / rok hitam  \n" +
                    "Jumat : baju khas daerah / batik (batik Dealer), Celana / Rok Hitam\n" +
                    "Sabtu & Minggu : casual (berkerah) atau sesuai kebijakan masing2 dealer\n" +
                    "(kondisi Baju : Rapi, Bersih, Warna tidak kusam, tidak robek, dan logo Honda terlihat)\n" +
                    "* Sesuai dengan aturan seragam dari HC3 AHM\n" +
                    "\n" +
                    "5.Atribut  : \n" +
                    "- Senin - Jumat : Wanita menggunakan sepatu pantofel warna hitam heel minimum 5 cm kecuali wanita hamil\n" +
                    "- Senin - Jumat : Pria menggunakan sepatu pantofel warna hitam\n" +
                    "- Sabtu - Minggu atau saat berada di lapangan : Sepatu Kets  warna Netral (Putih, Hitam, Abu-abu, Merah Maroon, Biru Tua, Putih)\n" +
                    "- ID Card dengan Kalung Honda Merah dan ID Card Holder WSP dari AHM\n" +
                    "- Pin One Heart di dada kiri\n" +
                    "- Jam Tangan Kulit / Karet / Canvas Warna Hitam / Coklat Tua / Gelap\n" +
                    "\n" +
                    "6. Aksesories :\n" +
                    "Pria : aksesoris tidak berlebihan (tidak lebih dari 5 titik)\n" +
                    "Wanita : aksesoris tidak berlebihan (tidak lebih dari 7 titik)\n" +
                    "\n" +
                    "Menggunakan Masker polos atau tidak bermotif.\n" +
                    "-Masker kesehatan (warna bebas)\n" +
                    "-Masker kain disesuaikan dengan warna unsur seragam, yaitu abu-abu/putih/merah/hitam* *Kecuali Jum'at-Sabtu-Minggu\n" +
                    "Menggunakan face shield (jika berinteraksi dengan konsumen)", 0))

        nosAudit.add(NosAudit("Sales Man",
            "Sales Tools",
            "Memiliki sales tools berupa :\n" +
                    "1. Aplikasi MyHero (berhasil Login)\n" +
                    "2. Map Kerja Sales People yang berisi Service Talk Flyer, Community Flyer, Flyer Acc & App, Flyer Customizing Part, Flyer Rekomendasi Bengkel Modifikasi, Pricelist, Sales Program List, Alat tulis. \n" +
                    "\n" +
                    "Ketika menjelaskan ke konsumen di utamakan menggunakan aplikasi MyHero (digital) ", 0))

        nosAudit.add(NosAudit("Sales Man",
            "Product Knowledge Test",
            "Alternatif Tipe Produk : Regular Product\n" +
                    "\n" +
                    "* Exist, Done : Dapat menjelaskan min. 5 – 7  fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge dengan menggunakan metode SPWA & C-F-A\n" +
                    "\n" +
                    "* Not Exist  : Tidak Dapat menjelaskan min. 5 – 7  fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge dengan menggunakan metode SPWA & C-F-A\n", 0))

        nosAudit.add(NosAudit("Supervisor",
            " Jumlah ",
            "1 Spv membawahi = 5 Coord SM\n" +
                    "Target  Jmlah Coord SM (A)\n" +
                    "Target  Jml Spv = A / 5\n" +
                    "\n" +
                    "* Exist, Done :  Jml Spv ≥ Target  Jml Spv \n" +
                    "* Exist, Not Done : Target  Jml Spv > 0 dan  Jml Spv < Target  Jml Spv\n" +
                    "* Not Done : Target  Jml Spv > 0 dan  Jml Spv = 0\n" +
                    "* N/A : Target  Jml Spv = 0 dan  Jml Spv = 0", 0))
        nosAudit.add(NosAudit("Supervisor",
            "Training",
            "Sudah melakukan training (dengan masa kerja) :\n" +
                    "a. Wing Sales People Training (Wajib dilakukan pada usia kerja 0 - 1 bulan)\n" +
                    "\n" +
                    "* Exist, Done : Sudah lulus training (Nilai >= 80)\n" +
                    "* Not Exist : Belum lulus Training", 0))

        nosAudit.add(NosAudit("Supervisor",
            "Training",
            "b. SFT - Product Knowledge (All Product Launched)\n" +
                    "\n" +
                    "* Exist, Done :   Sudah lulus training (Nilai >= 70)\n" +
                    "* Not Exist :   Belum lulus Training\n", 0))


        nosAudit.add(NosAudit("Supervisor",
            "Training",
            "c. Customer Service for leader 1 - Complaint Handling (Wajib dilakukan pada usia kerja > 3 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Supervisor",
            "Training",
            "d. Customer Service for leader 2 (Wajib dilakukan pada usia kerja > 6 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Supervisor",
            "Training",
            "e. TFT Basic Orientation Traning - BOT (Wajib dilakukan pada usia kerja > 1 bulan) sebelum training NOS\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Supervisor",
            "Training",
            "f. Training Network Operational Standard (Wajib dilakukan pada usia kerja > 1 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Supervisor",
            "Training",
            "Notes :\n" +
                    "* Apabila Target  Jml Spv = 0 dan  Jmlah Spv = 0, maka untuk penilaian diisii N/A\n" +
                    "* Apabila Target  Jml Spv > 0 dan  Jmlah Spv = 0, maka penilaian tetap dilakukan", 0))
        nosAudit.add(NosAudit("Supervisor",
            "Sales Tools",
            "Supervisor memiliki sales tools berupa :\n" +
                    "Aplikasi MyHero (berhasil Login)\n" +
                    "\n" +
                    "Ketika menjelaskan konsumen diutamakan menggunakan Aplikasi MyHero (digital)", 0))

        nosAudit.add(NosAudit("Supervisor",
            "Product Knowledge Test",
            "Dapat menjelaskan konsep produk, design produk, performance dan min. 5 - 7 fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge.\n" +
                    "\n" +
                    "Alternatif Tipe Produk : Premium Product\n" +
                    "\n" +
                    "* Exist, Done : Dapat menjelaskan min. 5 - 7 fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge dengan menggunakan metode SPWA & C-F-A\n" +
                    "\n" +
                    "* Not Exist  : Tidak Dapat menjelaskan min. 5 - 7 fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge dengan menggunakan metode SPWA & C-F-A", 0))

        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "  Jumlah ",
            "1 Coordinator SC membawahi = 4 SC\n" +
                    "Target  Jmlah SC (A)\n" +
                    "Target  Jml Coordinator SC = A / 4\n" +
                    "* Jabatan ini adalah fungsi yang dapat dirangkap oleh Sales Counter\n" +
                    "\n" +
                    "* Exist, Done : Actual Jml Coord SC ≥ Target  Jml Coord SC\n" +
                    "* Exist, Not Done : Target Jml Coord SC > 0 dan Actual Jml Coord SC < Target Jml Coord SC \n" +
                    "* Not Done : Target Jml Coord SC > 0 dan Actual Jml Coord SC = 0\n" +
                    "* N/A : Target Jml Coord SC = 0 dan Actual Jml Coord SC = 0", 0))
        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Training",
            "a. SFT - Product Knowledge (All Product Launched)\n" +
                    "\n" +
                    "* Exist, Done :   Sudah lulus training (Nilai >= 70)\n" +
                    "* Not Exist :   Belum lulus Training\n", 0))

        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Training",
            "b. Customer Service For FLP Training (Wajib dilakukan pada usia kerja > 3 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Training",
            "c. Basic Orientation Traning - BOT (Wajib dilakukan pada usia kerja > 1 bulan) sebelum training NOS\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Training",
            "d. Salesmanship Training  (Wajib dilakukan pada usia kerja > 6 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))
        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Training",
            "e. Training Network Operational Standard (Wajib dilakukan pada usia kerja > 1 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Standard Penampilan",
            "1.Wajah : \n" +
                    "Pria : ,wajah terlihat segar/tidak berminyak/tidak kusam, kumis/jenggot rapi (tdk dipanjangkan)\n" +
                    "\n" +
                    "Wanita :  Rias Wajah menggunakan make up (bedak,alis,eye shadow,blush on,lipstik sesuai standar)\n" +
                    "\n" +
                    "2.Rambut : \n" +
                    "Pria : Rambut Pendek Rapi, tidak menutupi mata dan tidak menyentuh kerah baju dan telinga\n" +
                    "\n" +
                    "Wanita :  Rambut rapi / tidak menutupi pandangan (panjang rambut lebih dari sebahu wajib dikuncir ponytail )\n" +
                    "\n" +
                    "3.Wewangian dan kebersihan:\n" +
                    "Aroma tubuh tidak mengganggu, tangan bersih, kuku tangan dan kuku kaki bersih. Untuk pria kuku pendek rapi.\n", 0))

        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Standard Penampilan",
            "4. Seragam : \n" +
                    "\n" +
                    "Ketentuan :\n" +
                    " Senin & Rabu : Seragam Honda berkerah merah,Celana / rok hitam \n" +
                    " Selasa & Kamis : Seragam Honda berkerah putih,Celana / rok hitam  \n" +
                    "Jumat : baju khas daerah / batik (batik Dealer), Celana / Rok Hitam\n" +
                    "Sabtu & Minggu : casual (berkerah) atau sesuai kebijakan masing2 dealer\n" +
                    "(kondisi Baju : Rapi, Bersih, Warna tidak kusam, tidak robek, dan logo Honda terlihat)\n" +
                    "* Sesuai dengan aturan seragam dari HC3 AHM\n" +
                    "\n" +
                    "5.Atribut  : \n" +
                    "- Senin - Jumat : Wanita menggunakan sepatu pantofel warna hitam heel minimum 5 cm kecuali wanita hamil\n" +
                    "- Senin - Jumat : Pria menggunakan sepatu pantofel warna hitam\n" +
                    "- Sabtu - Minggu atau saat berada di lapangan : Sepatu Kets  warna Netral (Putih, Hitam, Abu-abu, Merah Maroon, Biru Tua, Putih)\n" +
                    "- ID Card dengan Kalung Honda Merah dan ID Card Holder WSP dari AHM\n" +
                    "- Pin One Heart di dada kiri\n" +
                    "- Jam Tangan Kulit / Karet / Canvas Warna Hitam / Coklat Tua / Gelap\n" +
                    "\n" +
                    "6. Aksesories :\n" +
                    "Pria : aksesoris tidak berlebihan (tidak lebih dari 5 titik)\n" +
                    "Wanita : aksesoris tidak berlebihan (tidak lebih dari 7 titik)\n" +
                    "\n" +
                    "Menggunakan Masker polos atau tidak bermotif.\n" +
                    "-Masker kesehatan (warna bebas)\n" +
                    "-Masker kain disesuaikan dengan warna unsur seragam, yaitu abu-abu/putih/merah/hitam* *Kecuali Jum'at-Sabtu-Minggu\n" +
                    "Menggunakan face shield (jika berinteraksi dengan konsumen)", 0))
        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Standard Penampilan",
            "Notes : \n" +
                    "* Apabila Target  Jml Coord SC = 0 dan  Jmlah Coord SC = 0, maka untuk penilaian diisii N/A\n" +
                    "* Apabila Target  Jml Coord SC > 0 dan  Jmlah Coord SC = 0, maka penilaian tetap dilakukan", 0))

        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Sales Tools",
            "Memiliki sales tools berupa :\n" +
                    "1. Aplikasi MyHero (berhasil Login)\n" +
                    "2. Map Kerja Sales People yang berisi Service Talk Flyer, Community Flyer, Flyer Acc & App, Flyer Customizing Part, Flyer Rekomendasi Bengkel Modifikasi, Pricelist, Sales Program List, Alat tulis. \n" +
                    "3. Kalendar Produk\n" +
                    "\n" +
                    "Ketika menjelaskan ke konsumen di utamakan menggunakan aplikasi MyHero (digital) ", 0))

        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Product Knowledge Test",
            "Dapat menjelaskan konsep produk, design produk, performance dan min. 5 - 7 fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge.\n" +
                    "\n" +
                    "Alternatif Tipe Produk : Product Terbaru (antara 3 yang terakhir launching)\n" +
                    "\n" +
                    "* Exist, Done : Dapat menjelaskan min. 5 - 7 fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge dengan menggunakan metode SPWA & C-F-A\n" +
                    "\n" +
                    "* Not Exist  : Tidak Dapat menjelaskan min. 5 - 7 fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge dengan menggunakan metode SPWA & C-F-A", 0))

        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "  Jumlah ",
            "Target Sales Dealer (A)\n" +
                    "Target Kontribusi Sales by SC (B)\n" +
                    "Target Productivity SC (C)\n" +
                    "(Range Target Productivity SC = 20-45 unit (disesuaikan dengan target masing-masing MD)\n" +
                    "\n" +
                    "Target  Jml SC = (A * B) / C\n" +
                    "\n" +
                    "* Exist, Done :  Jml SC ≥ Target  Jml SC\n" +
                    "* Exist, Not Done : Target  Jml SC > 0 dan  Jml SC < Target  Jml SC \n" +
                    "* Not Done : Target  Jml SC > 0 dan  Jml SC = 0", 0))
        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Training",
            "a. SFT - Product Knowledge (All Product Launched)\n" +
                    "\n" +
                    "* Exist, Done :   Sudah lulus training (Nilai >= 70)\n" +
                    "* Not Exist :   Belum lulus Training\n", 0))

        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Training",
            "b. Customer Service For FLP Training (Wajib dilakukan pada usia kerja > 3 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Training",
            "c. Basic Orientation Traning - BOT (Wajib dilakukan pada usia kerja > 1 bulan) sebelum training NOS\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))
        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Training",
            "d. Salesmanship Training  (Wajib dilakukan pada usia kerja > 6 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Training",
            "e. Training Network Operational Standard (Wajib dilakukan pada usia kerja > 1 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Standard Penampilan",
            "1.Wajah : \n" +
                    "Pria : ,wajah terlihat segar/tidak berminyak/tidak kusam, kumis/jenggot rapi (tdk dipanjangkan)\n" +
                    "\n" +
                    "Wanita :  Rias Wajah menggunakan make up (bedak,alis,eye shadow,blush on,lipstik sesuai standar)\n" +
                    "\n" +
                    "2.Rambut : \n" +
                    "Pria : Rambut Pendek Rapi, tidak menutupi mata dan tidak menyentuh kerah baju dan telinga\n" +
                    "\n" +
                    "Wanita :  Rambut rapi / tidak menutupi pandangan (panjang rambut lebih dari sebahu wajib dikuncir ponytail)\n" +
                    "\n" +
                    "3.Wewangian dan kebersihan:\n" +
                    "Aroma tubuh tidak mengganggu, tangan bersih, kuku tangan dan kuku kaki bersih. Untuk pria kuku pendek rapi.\n", 0))

        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Standard Penampilan",
            "4. Seragam : \n" +
                    "\n" +
                    "Ketentuan :\n" +
                    " Senin & Rabu : Seragam Honda berkerah merah,Celana / rok hitam \n" +
                    " Selasa & Kamis : Seragam Honda berkerah putih,Celana / rok hitam  \n" +
                    "Jumat : baju khas daerah / batik (batik Dealer), Celana / Rok Hitam\n" +
                    "Sabtu & Minggu : casual (berkerah) atau sesuai kebijakan masing2 dealer\n" +
                    "(kondisi Baju : Rapi, Bersih, Warna tidak kusam, tidak robek, dan logo Honda terlihat)\n" +
                    "* Sesuai dengan aturan seragam dari HC3 AHM\n" +
                    "\n" +
                    "5.Atribut  : \n" +
                    "- Senin - Jumat : Wanita menggunakan sepatu pantofel warna hitam heel minimum 5 cm kecuali wanita hamil\n" +
                    "- Senin - Jumat : Pria menggunakan sepatu pantofel warna hitam\n" +
                    "- Sabtu - Minggu atau saat berada di lapangan : Sepatu Kets  warna Netral (Putih, Hitam, Abu-abu, Merah Maroon, Biru Tua, Putih)\n" +
                    "- ID Card dengan Kalung Honda Merah dan ID Card Holder WSP dari AHM\n" +
                    "- Pin One Heart di dada kiri\n" +
                    "- Jam Tangan Kulit / Karet / Canvas Warna Hitam / Coklat Tua / Gelap\n" +
                    "\n" +
                    "6. Aksesories :\n" +
                    "Pria : aksesoris tidak berlebihan (tidak lebih dari 5 titik)\n" +
                    "Wanita : aksesoris tidak berlebihan (tidak lebih dari 7 titik)\n" +
                    "\n" +
                    "Menggunakan Masker polos atau tidak bermotif.\n" +
                    "-Masker kesehatan (warna bebas)\n" +
                    "-Masker kain disesuaikan dengan warna unsur seragam, yaitu abu-abu/putih/merah/hitam* *Kecuali Jum'at-Sabtu-Minggu\n" +
                    "Menggunakan face shield (jika berinteraksi dengan konsumen)", 0))
        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Sales Tools",
            "Memiliki sales tools berupa :\n" +
                    "1. Aplikasi MyHero (berhasil Login)\n" +
                    "2. Map Kerja Sales People yang berisi Service Talk Flyer, Community Flyer, Flyer Acc & App, Flyer Customizing Part, Flyer Rekomendasi Bengkel Modifikasi, Pricelist, Sales Program List, Alat tulis. \n" +
                    "3. Kalendar Produk\n" +
                    "\n" +
                    "Ketika menjelaskan ke konsumen di utamakan menggunakan aplikasi MyHero (digital) ", 0))

        nosAudit.add(NosAudit("Coordinator Sales Counter",
            "Product Knowledge Test",
            "Alternatif Tipe Produk : Regular Product\n" +
                    "\n" +
                    "* Exist, Done : Dapat menjelaskan min. 5 – 7  fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge dengan menggunakan metode SPWA & C-F-A\n" +
                    "\n" +
                    "* Not Exist  : Tidak Dapat menjelaskan min. 5 – 7  fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge dengan menggunakan metode SPWA & C-F-A\n", 0))

        nosAudit.add(NosAudit("Coordinator Sales Man",
            "Jumlah",
            "1 Coordinator SM membawahi < = 10 SM\n" +
                    "Target  Jmlah SM (A)\n" +
                    "Target  Jml Coordinator SM = A / 10\n" +
                    "\n" +
                    "* Exist, Done :  Jml Coord SM ≥ Target  Jml Coord SM\n" +
                    "* Exist, Not Done : Target  Jml Coord SM > 0 dan  Jml Coord SM < Target  Jml Coord SM \n" +
                    "* Not Done : Target  Jml Coord SM > 0 dan  Jml Coord SM = 0\n" +
                    "* N/A : Target  Jml Coord SM = 0 dan  Jml Coord SM = 0", 0))
        nosAudit.add(NosAudit("Coordinator Sales Man",
            "Training",
            "a. SFT - Product Knowledge (All Product Launched)\n" +
                    "\n" +
                    "* Exist, Done :   Sudah lulus training (Nilai >= 70)\n" +
                    "* Not Exist :   Belum lulus Training\n", 0))

        nosAudit.add(NosAudit("Coordinator Sales Man",
            "Training",
            "b. Customer Service For FLP Training (Wajib dilakukan pada usia kerja > 3 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Coordinator Sales Man",
            "Training",
            "c. Basic Orientation Traning - BOT (Wajib dilakukan pada usia kerja > 1 bulan) sebelum training NOS\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Coordinator Sales Man",
            "Training",
            "d. Salesmanship Training  (Wajib dilakukan pada usia kerja > 6 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))
        nosAudit.add(NosAudit("Coordinator Sales Man",
            "Training",
            "e. Training Network Operational Standard (Wajib dilakukan pada usia kerja > 1 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Coordinator Sales Man",
            "Standard Penampilan",
            "1.Wajah : \n" +
                    "Pria : ,wajah terlihat segar/tidak berminyak/tidak kusam, kumis/jenggot rapi (tdk dipanjangkan)\n" +
                    "\n" +
                    "Wanita :  Rias Wajah menggunakan make up (bedak,alis,eye shadow,blush on,lipstik sesuai standar)\n" +
                    "\n" +
                    "2.Rambut : \n" +
                    "Pria : Rambut Pendek Rapi, tidak menutupi mata dan tidak menyentuh kerah baju dan telinga\n" +
                    "\n" +
                    "Wanita :  Rambut rapi / tidak menutupi pandangan (panjang rambut lebih dari sebahu wajib dikuncir ponytail )\n" +
                    "\n" +
                    "3.Wewangian dan kebersihan:\n" +
                    "Aroma tubuh tidak mengganggu, tangan bersih, kuku tangan dan kuku kaki bersih. Untuk pria kuku pendek rapi.\n", 0))

        nosAudit.add(NosAudit("Coordinator Sales Man",
            "Standard Penampilan",
            "4. Seragam : \n" +
                    "\n" +
                    "Ketentuan :\n" +
                    " Senin & Rabu : Seragam Honda berkerah merah,Celana / rok hitam \n" +
                    " Selasa & Kamis : Seragam Honda berkerah putih,Celana / rok hitam  \n" +
                    "Jumat : baju khas daerah / batik (batik Dealer), Celana / Rok Hitam\n" +
                    "Sabtu & Minggu : casual (berkerah) atau sesuai kebijakan masing2 dealer\n" +
                    "(kondisi Baju : Rapi, Bersih, Warna tidak kusam, tidak robek, dan logo Honda terlihat)\n" +
                    "* Sesuai dengan aturan seragam dari HC3 AHM\n" +
                    "\n" +
                    "5.Atribut  : \n" +
                    "- Senin - Jumat : Wanita menggunakan sepatu pantofel warna hitam heel minimum 5 cm kecuali wanita hamil\n" +
                    "- Senin - Jumat : Pria menggunakan sepatu pantofel warna hitam\n" +
                    "- Sabtu - Minggu atau saat berada di lapangan : Sepatu Kets  warna Netral (Putih, Hitam, Abu-abu, Merah Maroon, Biru Tua, Putih)\n" +
                    "- ID Card dengan Kalung Honda Merah\n" +
                    "- Pin One Heart di dada kiri\n" +
                    "- Jam Tangan Kulit / Karet / Canvas Warna Hitam / Coklat Tua / Gelap\n" +
                    "\n" +
                    "6. Aksesories :\n" +
                    "Pria : aksesoris tidak berlebihan (tidak lebih dari 5 titik)\n" +
                    " Wanita : aksesoris tidak berlebihan (tidak lebih dari 7 titik)\n" +
                    "\n" +
                    "Menggunakan Masker  polos atau tidak bermotif (disesuaikan dengan warna unsur seragam, yaitu abu-abu/putih/merah/hitam)* *Kecuali Jum'at-Sabtu-Minggu\n" +
                    "Menggunakan face shield (jika berinteraksi dengan konsumen)", 0))
        nosAudit.add(NosAudit("Coordinator Sales Man",
            "Standard Penampilan",
            "Notes : \n" +
                    "* Apabila Target  Jml Coord SM = 0 dan  Jmlah Coord SM = 0, maka untuk penilaian diisii N/A\n" +
                    "* Apabila Target  Jml Coord SM > 0 dan  Jmlah Coord SM = 0, maka penilaian tetap dilakukan", 0))

        nosAudit.add(NosAudit("Coordinator Sales Man",
            "Sales Tools",
            "Memiliki sales tools berupa :\n" +
                    "1. Aplikasi MyHero (berhasil Login)\n" +
                    "2. Map Kerja Sales People yang berisi Service Talk Flyer, Community Flyer, Flyer Acc & App, Flyer Customizing Part, Flyer Rekomendasi Bengkel Modifikasi, Pricelist, Sales Program List, Alat tulis. \n" +
                    "\n" +
                    "Ketika menjelaskan ke konsumen di utamakan menggunakan aplikasi MyHero (digital) ", 0))

        nosAudit.add(NosAudit("Coordinator Sales Man",
            "Product Knowledge Test",
            "Dapat menjelaskan konsep produk, design produk, performance dan min. 5 - 7 fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge \n" +
                    "\n" +
                    "Alternatif Tipe Produk : Product Terbaru (antara 3 yang terakhir launching)\n" +
                    "\n" +
                    "* Exist, Done : Dapat menjelaskan min. 5 - 7 fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge dengan menggunakan metode SPWA & C-F-A\n" +
                    "\n" +
                    "* Not Exist  : Tidak Dapat menjelaskan min. 5 - 7 fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge dengan menggunakan metode SPWA & C-F-A", 0))

        nosAudit.add(NosAudit("Sales Man",
            "  Jumlah ",
            "Target Sales Dealer (A)\n" +
                    "Target Kontribusi Sales by SM (B)\n" +
                    "Target Productivity SM (C)\n" +
                    "(Range Target Productivity SM = 5-12 unit (disesuaikan dengan target masing-masing MD)\n" +
                    "\n" +
                    "Target  Jml SM = (A * B) / C\n" +
                    "\n" +
                    "* Exist, Done :  Jml SM ≥ Target  Jml SM\n" +
                    "* Exist, Not Done : Target  Jml SM > 0 dan  Jml SM < Target  Jml SM \n" +
                    "* Not Done : Target  Jml SM > 0 dan  Jml SM = 0", 0))
        nosAudit.add(NosAudit("Sales Man",
            "Training",
            "a. SFT - Product Knowledge (All Product Launched)\n" +
                    "\n" +
                    "* Exist, Done :   Sudah lulus training (Nilai >= 70)\n" +
                    "* Not Exist :   Belum lulus Training\n", 0))

        nosAudit.add(NosAudit("Sales Man",
            "Training",
            "b. Customer Service For FLP Training (Wajib dilakukan pada usia kerja > 3 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Sales Man",
            "Training",
            "c. Basic Orientation Traning - BOT (Wajib dilakukan pada usia kerja > 1 bulan) sebelum training NOS\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))
        nosAudit.add(NosAudit("Sales Man",
            "Training",
            "d. Salesmanship Training  (Wajib dilakukan pada usia kerja > 6 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Sales Man",
            "Training",
            "e. Training Network Operational Standard (Wajib dilakukan pada usia kerja > 1 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Sales Man",
            "Standard Penampilan",
            "1.Wajah : \n" +
                    "Pria : wajah terlihat segar/tidak berminyak/tidak kusam, kumis/jenggot rapi (tdk dipanjangkan)\n" +
                    "\n" +
                    "Wanita :  Rias Wajah menggunakan make up (bedak,alis,eye shadow,blush on,lipstik sesuai standar)\n" +
                    "\n" +
                    "2. Rambut : \n" +
                    "Pria : Rambut Pendek Rapi, tidak menutupi mata dan tidak menyentuh kerah baju dan telinga\n" +
                    "\n" +
                    "Wanita :  Rambut rapi / tidak menutupi pandangan (panjang rambut lebih dari sebahu wajib dikuncir ponytail)\n" +
                    "\n" +
                    "3.Wewangian dan kebersihan:\n" +
                    "Aroma tubuh tidak mengganggu, tangan bersih, kuku tangan dan kuku kaki bersih. Untuk pria kuku pendek rapi.", 0))

        nosAudit.add(NosAudit("Sales Man",
            "Sales Tools",
            "Memiliki sales tools berupa :\n" +
                    "1. Aplikasi MyHero (berhasil Login)\n" +
                    "2. Map Kerja Sales People yang berisi Service Talk Flyer, Community Flyer, Flyer Acc & App, Flyer Customizing Part, Flyer Rekomendasi Bengkel Modifikasi, Pricelist, Sales Program List, Alat tulis. \n" +
                    "\n" +
                    "Ketika menjelaskan ke konsumen di utamakan menggunakan aplikasi MyHero (digital) ", 0))
        nosAudit.add(NosAudit("Sales Man",
            "Product Knowledge Test",
            "Alternatif Tipe Produk : Regular Product\n" +
                    "\n" +
                    "* Exist, Done : Dapat menjelaskan min. 5 – 7  fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge dengan menggunakan metode SPWA & C-F-A\n" +
                    "\n" +
                    "* Not Exist  : Tidak Dapat menjelaskan min. 5 – 7  fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge dengan menggunakan metode SPWA & C-F-A\n", 0))

        nosAudit.add(NosAudit("PIC CRM ",
            "  Jumlah ",
            "Dealer wajib memiliki 1 PIC CRM dedicated (tidak boleh merangkap dengan fungsi apapun)\n", 0))

        nosAudit.add(NosAudit("PIC CRM ",
            "Training",
            "Ketentuan :\n" +
                    "Bisa mengoperasikan Tools CRM & Software pendukung (seperti Ms. Excel)", 0))
        nosAudit.add(NosAudit("PIC CRM ",
            "Training",
            "Training Network Operational Standard (Wajib dilakukan pada usia kerja > 1 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("PIC CRM ",
            "Seragam",
            "- Menggunakan Masker Polos (disesuaikan dengan warna unsur seragam yaitu abu-abu/putih/merah/hitam)\n", 0))

        nosAudit.add(NosAudit("Admin CRM H1",
            "  Jumlah ",
            "Dealer memiliki 1 Admin CRM H1 (secara fungsi boleh dirangkap oleh Admin H1/STNK)", 0))

        nosAudit.add(NosAudit("Admin CRM H1",
            "Training",
            "a. Basic Orientation Traning - BOT (Wajib dilakukan pada usia kerja > 1 bulan) sebelum training NOS\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))
        nosAudit.add(NosAudit("Admin CRM H1",
            "Standard Penampilan",
            "1.Wajah : \n" +
                    "Pria : ,wajah terlihat segar/tidak berminyak/tidak kusam, kumis/jenggot rapi (tdk dipanjangkan)\n" +
                    "\n" +
                    "Wanita :  Rias Wajah menggunakan make up (bedak,alis,eye shadow,blush on,lipstik sesuai standar)\n" +
                    "\n" +
                    "2.Rambut : \n" +
                    "Pria : Rambut Pendek Rapi, tidak menutupi mata dan tidak menyentuh kerah baju dan telinga\n" +
                    "\n" +
                    "Wanita :  Rambut rapi / tidak menutupi pandangan (panjang rambut lebih dari sebahu wajib dikuncir ponytail )\n" +
                    "\n" +
                    "3.Wewangian dan kebersihan:\n" +
                    "Aroma tubuh tidak mengganggu, tangan bersih, kuku tangan dan kuku kaki bersih. Untuk pria kuku pendek rapi.\n", 0))

        nosAudit.add(NosAudit("Admin CRM H1",
            "Standard Penampilan",
            "4. Seragam : \n" +
                    "\n" +
                    "Ketentuan :\n" +
                    " Senin & Rabu : Seragam Honda berkerah merah,Celana / rok hitam \n" +
                    " Selasa & Kamis : Seragam Honda berkerah putih,Celana / rok hitam  \n" +
                    "Jumat : baju khas daerah / batik (batik Dealer), Celana / Rok Hitam\n" +
                    "Sabtu & Minggu : casual (berkerah) atau sesuai kebijakan masing2 dealer\n" +
                    "(kondisi Baju : Rapi, Bersih, Warna tidak kusam, tidak robek, dan logo Honda terlihat)\n" +
                    "* Sesuai dengan aturan seragam dari HC3 AHM\n" +
                    "\n" +
                    "5.Atribut  : \n" +
                    "- Senin - Jumat : Wanita menggunakan sepatu pantofel warna hitam heel minimum 5 cm kecuali wanita hamil\n" +
                    "- Senin - Jumat : Pria menggunakan sepatu pantofel warna hitam\n" +
                    "- Sabtu - Minggu atau saat berada di lapangan : Sepatu Kets  warna Netral (Putih, Hitam, Abu-abu, Merah Maroon, Biru Tua, Putih)\n" +
                    "- ID Card dengan Kalung Honda Merah dan ID Card Holder WSP dari AHM\n" +
                    "- Pin One Heart di dada kiri\n" +
                    "- Jam Tangan Kulit / Karet / Canvas Warna Hitam / Coklat Tua / Gelap\n" +
                    "\n" +
                    "6. Aksesories :\n" +
                    "Pria : aksesoris tidak berlebihan (tidak lebih dari 5 titik)\n" +
                    "Wanita : aksesoris tidak berlebihan (tidak lebih dari 7 titik)\n" +
                    "\n" +
                    "Menggunakan Masker polos atau tidak bermotif.\n" +
                    "-Masker kesehatan (warna bebas)\n" +
                    "-Masker kain disesuaikan dengan warna unsur seragam, yaitu abu-abu/putih/merah/hitam* *Kecuali Jum'at-Sabtu-Minggu\n" +
                    "Menggunakan face shield (jika berinteraksi dengan konsumen)", 0))

        nosAudit.add(NosAudit("Admin H1",
            "  Jumlah ",
            "Dealer memiliki 1 Admin CRM H1 (secara fungsi boleh dirangkap oleh Admin H1/STNK)\n" +
                    "maks. 1 orang memegang 2 jabatan ", 0))
        nosAudit.add(NosAudit("Admin H1",
            "Training",
            "a. Basic Orientation Traning - BOT (Wajib dilakukan pada usia kerja > 1 bulan) sebelum training NOS\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))
        nosAudit.add(NosAudit("Admin H1",
            "Training",
            "b. Customer Service For FLP Training (Wajib dilakukan pada usia kerja > 3 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Admin H1",
            "Training",
            "c. Training Network Operational Standard (Wajib dilakukan pada usia kerja > 1 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Admin H1",
            "Standard Penampilan",
            "1.Wajah : \n" +
                    "Pria : ,wajah terlihat segar/tidak berminyak/tidak kusam, kumis/jenggot rapi (tdk dipanjangkan)\n" +
                    "\n" +
                    "Wanita :  Rias Wajah menggunakan make up (bedak,alis,eye shadow,blush on,lipstik sesuai standar)\n" +
                    "\n" +
                    "2.Rambut : \n" +
                    "Pria : Rambut Pendek Rapi, tidak menutupi mata dan tidak menyentuh kerah baju dan telinga\n" +
                    "\n" +
                    "Wanita :  Rambut rapi / tidak menutupi pandangan (panjang rambut lebih dari sebahu wajib dikuncir ponytail )\n" +
                    "\n" +
                    "3.Wewangian dan kebersihan:\n" +
                    "Aroma tubuh tidak mengganggu, tangan bersih, kuku tangan dan kuku kaki bersih. Untuk pria kuku pendek rapi.\n", 0))
        nosAudit.add(NosAudit("Admin H1",
            "Standard Penampilan",
            "4. Seragam : \n" +
                    "\n" +
                    "Ketentuan :\n" +
                    " Senin & Rabu : Seragam Honda berkerah merah,Celana / rok hitam \n" +
                    " Selasa & Kamis : Seragam Honda berkerah putih,Celana / rok hitam  \n" +
                    "Jumat : baju khas daerah / batik (batik Dealer), Celana / Rok Hitam\n" +
                    "Sabtu & Minggu : casual (berkerah) atau sesuai kebijakan masing2 dealer\n" +
                    "(kondisi Baju : Rapi, Bersih, Warna tidak kusam, tidak robek, dan logo Honda terlihat)\n" +
                    "* Sesuai dengan aturan seragam dari HC3 AHM\n" +
                    "\n" +
                    "5.Atribut  : \n" +
                    "- Senin - Jumat : Wanita menggunakan sepatu pantofel warna hitam heel minimum 5 cm kecuali wanita hamil\n" +
                    "- Senin - Jumat : Pria menggunakan sepatu pantofel warna hitam\n" +
                    "- Sabtu - Minggu atau saat berada di lapangan : Sepatu Kets  warna Netral (Putih, Hitam, Abu-abu, Merah Maroon, Biru Tua, Putih)\n" +
                    "- ID Card dengan Kalung Honda Merah dan ID Card Holder WSP dari AHM\n" +
                    "- Pin One Heart di dada kiri\n" +
                    "- Jam Tangan Kulit / Karet / Canvas Warna Hitam / Coklat Tua / Gelap\n" +
                    "\n" +
                    "6. Aksesories :\n" +
                    "Pria : aksesoris tidak berlebihan (tidak lebih dari 5 titik)\n" +
                    "Wanita : aksesoris tidak berlebihan (tidak lebih dari 7 titik)\n" +
                    "\n" +
                    "Menggunakan Masker polos atau tidak bermotif.\n" +
                    "-Masker kesehatan (warna bebas)\n" +
                    "-Masker kain disesuaikan dengan warna unsur seragam, yaitu abu-abu/putih/merah/hitam* *Kecuali Jum'at-Sabtu-Minggu\n" +
                    "Menggunakan face shield (jika berinteraksi dengan konsumen)", 0))

        nosAudit.add(NosAudit("Cashier",
            "  Jumlah ",
            "Untuk Dealer yang memiliki average Sales  : \n" +
                    "a. ≥ 450 unit = minimal 2 Cashier\n" +
                    "b. < 450 unit = minimal 1 Cashier", 0))

        nosAudit.add(NosAudit("Cashier",
            "Training",
            "b. Customer Service For FLP Training (Wajib dilakukan pada usia kerja > 3 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))
        nosAudit.add(NosAudit("Cashier",
            "Training",
            "c. Training Network Operational Standard (Wajib dilakukan pada usia kerja > 1 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Cashier",
            "Standard Penampilan",
            "1.Wajah : \n" +
                    "Pria : wajah terlihat segar/tidak berminyak/tidak kusam, kumis/jenggot rapi (tdk dipanjangkan)\n" +
                    "\n" +
                    "Wanita :  Rias Wajah menggunakan make up (bedak,alis,eye shadow,blush on,lipstik sesuai standar)\n" +
                    "\n" +
                    "2.Rambut : \n" +
                    "Pria : Rambut Pendek Rapi, tidak menutupi mata dan tidak menyentuh kerah baju dan telinga\n" +
                    "\n" +
                    "Wanita :  Rambut rapi / tidak menutupi pandangan (panjang rambut lebih dari sebahu wajib dikuncir ponytail )\n" +
                    "\n" +
                    "3.Wewangian dan kebersihan:\n" +
                    "Aroma tubuh tidak mengganggu, tangan bersih, kuku tangan dan kuku kaki bersih. Untuk pria kuku pendek rapi.\n", 0))

        nosAudit.add(NosAudit("Cashier",
            "Standard Penampilan",
            "4. Seragam : \n" +
                    "\n" +
                    "Ketentuan :\n" +
                    " Senin & Rabu : Seragam Honda berkerah merah,Celana / rok hitam \n" +
                    " Selasa & Kamis : Seragam Honda berkerah putih,Celana / rok hitam  \n" +
                    "Jumat : baju khas daerah / batik (batik Dealer), Celana / Rok Hitam\n" +
                    "Sabtu & Minggu : casual (berkerah) atau sesuai kebijakan masing2 dealer\n" +
                    "(kondisi Baju : Rapi, Bersih, Warna tidak kusam, tidak robek, dan logo Honda terlihat)\n" +
                    "* Sesuai dengan aturan seragam dari HC3 AHM\n" +
                    "\n" +
                    "5.Atribut  : \n" +
                    "- Senin - Jumat : Wanita menggunakan sepatu pantofel warna hitam heel minimum 5 cm kecuali wanita hamil\n" +
                    "- Senin - Jumat : Pria menggunakan sepatu pantofel warna hitam\n" +
                    "- Sabtu - Minggu atau saat berada di lapangan : Sepatu Kets  warna Netral (Putih, Hitam, Abu-abu, Merah Maroon, Biru Tua, Putih)\n" +
                    "- ID Card dengan Kalung Honda Merah dan ID Card Holder WSP dari AHM\n" +
                    "- Pin One Heart di dada kiri\n" +
                    "- Jam Tangan Kulit / Karet / Canvas Warna Hitam / Coklat Tua / Gelap\n" +
                    "\n" +
                    "6. Aksesories :\n" +
                    "Pria : aksesoris tidak berlebihan (tidak lebih dari 5 titik)\n" +
                    "Wanita : aksesoris tidak berlebihan (tidak lebih dari 7 titik)\n" +
                    "\n" +
                    "Menggunakan Masker polos atau tidak bermotif.\n" +
                    "-Masker kesehatan (warna bebas)\n" +
                    "-Masker kain disesuaikan dengan warna unsur seragam, yaitu abu-abu/putih/merah/hitam* *Kecuali Jum'at-Sabtu-Minggu\n" +
                    "Menggunakan face shield (jika berinteraksi dengan konsumen)", 0))

        nosAudit.add(NosAudit("Security",
            "  Jumlah ",
            "Untuk Dealer yang memiliki average Sales  : \n" +
                    "a. ≥ 450 unit = 1 security dedicated ", 0))
        nosAudit.add(NosAudit("Security",
            "Training",
            "a. Basic Orientation Traning - BOT (Wajib dilakukan pada usia kerja > 1 bulan) sebelum training NOS\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)\n" +
                    "\n" +
                    "Untuk Security outsorce : \n" +
                    "*Exist,Done : Security outsorce sudah di sosialisasikan\n" +
                    "*Not Exist : Security outsorce belum di sosialisasikan\n" +
                    "* N/A : Apabila masa kerja belum memenuhi\n" +
                    "\n" +
                    "Notes :\n" +
                    "Apabila security outsorce,maka tidak diwajibkan untuk training,tetapi di sosialisasikan saja", 0))
        nosAudit.add(NosAudit("Security",
            "Training",
            "b. Training Network Operational Standard (Wajib dilakukan pada usia kerja > 1 bulan) \n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)\n" +
                    "\n" +
                    "Untuk Security outsorce : \n" +
                    "*Exist,Done : Security outsorce sudah di sosialisasikan\n" +
                    "*Not Exist : Security outsorce belum di sosialisasikan\n" +
                    "* N/A : Apabila masa kerja belum memenuhi\n" +
                    "\n" +
                    "Notes :\n" +
                    "Apabila security outsorce,maka tidak diwajibkan untuk training,tetapi di sosialisasikan terkait standard interaksi NOS ", 0))

        nosAudit.add(NosAudit("Security",
            "Standard Penampilan",
            "1.Wajah : \n" +
                    "Pria : ,wajah terlihat segar/tidak berminyak/tidak kusam, kumis/jenggot rapi (tdk dipanjangkan)\n" +
                    "\n" +
                    "Wanita :  Rias Wajah menggunakan make up (bedak,alis,eye shadow,blush on,lipstik sesuai standar)\n" +
                    "\n" +
                    "2.Rambut : \n" +
                    "Pria : Rambut Pendek Rapi, tidak menutupi mata dan tidak menyentuh kerah baju dan telinga\n" +
                    "\n" +
                    "Wanita :  Rambut rapi / tidak menutupi pandangan (panjang rambut lebih dari sebahu wajib dikuncir ponytail )\n" +
                    "\n" +
                    "3.Wewangian dan kebersihan:\n" +
                    "Aroma tubuh tidak mengganggu, tangan bersih, kuku tangan dan kuku kaki bersih. Untuk pria kuku pendek rapi.\n", 0))
        nosAudit.add(NosAudit("Security",
            "Standard Penampilan",
            "4. Seragam : \n" +
                    "\n" +
                    "Ketentuan :\n" +
                    " Senin & Rabu : Seragam Honda berkerah merah,Celana / rok hitam \n" +
                    " Selasa & Kamis : Seragam Honda berkerah putih,Celana / rok hitam  \n" +
                    "Jumat : baju khas daerah / batik (batik Dealer), Celana / Rok Hitam\n" +
                    "Sabtu & Minggu : casual (berkerah) atau sesuai kebijakan masing2 dealer\n" +
                    "(kondisi Baju : Rapi, Bersih, Warna tidak kusam, tidak robek, dan logo Honda terlihat)\n" +
                    "* Sesuai dengan aturan seragam dari HC3 AHM\n" +
                    "\n" +
                    "5.Atribut  : \n" +
                    "- Senin - Jumat : Wanita menggunakan sepatu pantofel warna hitam heel minimum 5 cm kecuali wanita hamil\n" +
                    "- Senin - Jumat : Pria menggunakan sepatu pantofel warna hitam\n" +
                    "- Sabtu - Minggu atau saat berada di lapangan : Sepatu Kets  warna Netral (Putih, Hitam, Abu-abu, Merah Maroon, Biru Tua, Putih)\n" +
                    "- ID Card dengan Kalung Honda Merah dan ID Card Holder WSP dari AHM\n" +
                    "- Pin One Heart di dada kiri\n" +
                    "- Jam Tangan Kulit / Karet / Canvas Warna Hitam / Coklat Tua / Gelap\n" +
                    "\n" +
                    "6. Aksesories :\n" +
                    "Pria : aksesoris tidak berlebihan (tidak lebih dari 5 titik)\n" +
                    "Wanita : aksesoris tidak berlebihan (tidak lebih dari 7 titik)\n" +
                    "\n" +
                    "Menggunakan Masker polos atau tidak bermotif.\n" +
                    "-Masker kesehatan (warna bebas)\n" +
                    "-Masker kain disesuaikan dengan warna unsur seragam, yaitu abu-abu/putih/merah/hitam* *Kecuali Jum'at-Sabtu-Minggu\n" +
                    "Menggunakan face shield (jika berinteraksi dengan konsumen)", 0))

        nosAudit.add(NosAudit("Greeter",
            "  Jumlah ",
            "Dealer wajib memiliki 1 greeter (fungsi) untuk menyambut konsumen (fase welcoming)", 0))

        nosAudit.add(NosAudit("Greeter",
            "Training",
            "a. Basic Orientation Traning - BOT (Wajib dilakukan pada usia kerja > 1 bulan) sebelum training NOS\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Greeter",
            "Standard Penampilan",
            "1.Wajah : \n" +
                    "Pria : ,wajah terlihat segar/tidak berminyak/tidak kusam, kumis/jenggot rapi (tdk dipanjangkan)\n" +
                    "\n" +
                    "Wanita :  Rias Wajah menggunakan make up (bedak,alis,eye shadow,blush on,lipstik sesuai standar)\n" +
                    "\n" +
                    "2.Rambut : \n" +
                    "Pria : Rambut Pendek Rapi, tidak menutupi mata dan tidak menyentuh kerah baju dan telinga\n" +
                    "\n" +
                    "Wanita :  Rambut rapi / tidak menutupi pandangan (panjang rambut lebih dari sebahu wajib dikuncir ponytail )\n" +
                    "\n" +
                    "3.Wewangian dan kebersihan:\n" +
                    "Aroma tubuh tidak mengganggu, tangan bersih, kuku tangan dan kuku kaki bersih. Untuk pria kuku pendek rapi.\n", 0))
        nosAudit.add(NosAudit("Greeter",
            "Standard Penampilan",
            "4. Seragam : \n" +
                    "\n" +
                    "Ketentuan :\n" +
                    " Senin & Rabu : Seragam Honda berkerah merah,Celana / rok hitam \n" +
                    " Selasa & Kamis : Seragam Honda berkerah putih,Celana / rok hitam  \n" +
                    "Jumat : baju khas daerah / batik (batik Dealer), Celana / Rok Hitam\n" +
                    "Sabtu & Minggu : casual (berkerah) atau sesuai kebijakan masing2 dealer\n" +
                    "(kondisi Baju : Rapi, Bersih, Warna tidak kusam, tidak robek, dan logo Honda terlihat)\n" +
                    "* Sesuai dengan aturan seragam dari HC3 AHM\n" +
                    "\n" +
                    "5.Atribut  : \n" +
                    "- Senin - Jumat : Wanita menggunakan sepatu pantofel warna hitam heel minimum 5 cm kecuali wanita hamil\n" +
                    "- Senin - Jumat : Pria menggunakan sepatu pantofel warna hitam\n" +
                    "- Sabtu - Minggu atau saat berada di lapangan : Sepatu Kets  warna Netral (Putih, Hitam, Abu-abu, Merah Maroon, Biru Tua, Putih)\n" +
                    "- ID Card dengan Kalung Honda Merah dan ID Card Holder WSP dari AHM\n" +
                    "- Pin One Heart di dada kiri\n" +
                    "- Jam Tangan Kulit / Karet / Canvas Warna Hitam / Coklat Tua / Gelap\n" +
                    "\n" +
                    "6. Aksesories :\n" +
                    "Pria : aksesoris tidak berlebihan (tidak lebih dari 5 titik)\n" +
                    "Wanita : aksesoris tidak berlebihan (tidak lebih dari 7 titik)\n" +
                    "\n" +
                    "Menggunakan Masker polos atau tidak bermotif.\n" +
                    "-Masker kesehatan (warna bebas)\n" +
                    "-Masker kain disesuaikan dengan warna unsur seragam, yaitu abu-abu/putih/merah/hitam* *Kecuali Jum'at-Sabtu-Minggu\n" +
                    "Menggunakan face shield (jika berinteraksi dengan konsumen)", 0))

        nosAudit.add(NosAudit("Delivery Man",
            " Jumlah ",
            "Minimal  Jmlah Delivery Man = Round Up (Target Sales Dealer / 200).\n"+
                    "Penilaian  Jmlah Actual vs Target :.\n",0))

        nosAudit.add(NosAudit("Delivery Man",
            " Training ",
            "a. Basic Orientation Traning - BOT (Wajib dilakukan pada usia kerja > 1 bulan) sebelum training NOS\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))
        nosAudit.add(NosAudit("Delivery Man",
            " Training ",
            "b. Customer Service For FLP Training (Wajib dilakukan pada usia kerja > 3 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Delivery Man",
            " Training ",
            "c. Training Network Operational Standard (Wajib dilakukan pada usia kerja > 1 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))

        nosAudit.add(NosAudit("Delivery Man",
            " Training ",
            "d. Materi Technical (Instruksi kerja proses ekspedisi pick up)\n" +
                    "\n" +
                    "* Exist, Done : All Delivery Man sudah training\n" +
                    "* Not Exist : minimal 1 Delivery Man belum training\n" +
                    "* N/A : Apabila masa kerja belum memenuhi", 0))

        nosAudit.add(NosAudit("Delivery Man",
            " Training ",
            "e. Training Sales Talk Product Knowledge (All Product Launched)\n" +
                    "\n" +
                    "* Exist, Done : All Delivery Man sudah training\n" +
                    "* Not Exist : minimal 1 Delivery Man belum training", 0))

        nosAudit.add(NosAudit("Delivery Man",
            "Standard Penampilan",
            "1.Wajah : \n" +
                    "Pria : ,wajah terlihat segar/tidak berminyak/tidak kusam, kumis/jenggot rapi (tdk dipanjangkan)\n" +
                    "\n" +
                    "Wanita :  Rias Wajah menggunakan make up (bedak,alis,eye shadow,blush on,lipstik sesuai standar)\n" +
                    "\n" +
                    "2.Rambut : \n" +
                    "Pria : Rambut Pendek Rapi, tidak menutupi mata dan tidak menyentuh kerah baju dan telinga\n" +
                    "\n" +
                    "Wanita :  Rambut rapi / tidak menutupi pandangan (panjang rambut lebih dari sebahu wajib dikuncir ponytail )\n" +
                    "\n" +
                    "3.Wewangian dan kebersihan:\n" +
                    "Aroma tubuh tidak mengganggu, tangan bersih, kuku tangan dan kuku kaki bersih. Untuk pria kuku pendek rapi.\n", 0))
        nosAudit.add(NosAudit("Delivery Man",
            "Standard Penampilan",
            "4. Seragam : \n" +
                    "Ketentuan :\n" +
                    "Mengenakan Polo Shirt warna merah sesuai standar AHM, dengan celana hitam.\n" +
                    "\n" +
                    "5.Atribut  : \n" +
                    "- Menggunakan sepatu warna hitam / Gelap (boleh sepatu kets)\n" +
                    "- ID Card (dengan kalung Honda Merah)\n" +
                    "- Pin One Heart di dada kiri\n" +
                    "- Jam Tangan Kulit / Karet / Canvas Warna Hitam / Coklat Tua / Gelap\n" +
                    "\n" +
                    "\n" +
                    "6. Aksesories :\n" +
                    "Pria : aksesoris tidak berlebihan (tidak lebih dari 5 titik)\n" +
                    "\n" +
                    "Menggunakan Masker polos atau tidak bermotif.\n" +
                    "-Masker kesehatan (warna bebas)\n" +
                    "-Masker kain disesuaikan dengan warna unsur seragam, yaitu abu-abu/putih/merah/hitam* *Kecuali Jum'at-Sabtu-Minggu\n" +
                    "Menggunakan face shield (jika berinteraksi dengan konsumen)\n" +
                    "\n" +
                    "Menggunakan name tag yang menunjukkan suhu tubuh dan tanggal pengecekan (wajib di update setiap hari) ", 0))

        nosAudit.add(NosAudit("PDI Man",
            "  Jumlah ",
            "Untuk Dealer yang memiliki average Sales per bulan : \n" +
                    "a. ≤275 unit = 1\n" +
                    "b. >275 unit = Target Sales per bulan / 275 ", 0))
        nosAudit.add(NosAudit("PDI Man",
            "Training",
            "Sudah melakukan training (dengan masa kerja) :\n" +
                    "a. Jika PDI Man 1 orang, maka minimal TTL2    \n" +
                    "b. JIka 1 PDI Man > 1 orang, maka 1 orang minimal TTL 2", 0))

        nosAudit.add(NosAudit("PDI Man",
            "Seragam",
            "Standar Mekanik\n", 0))

        nosAudit.add(NosAudit("Admin Warehouse",
            "  Jumlah ",
            "Dealer wajib memiliki 1 Admin Warehouse (fungsi)", 0))

        nosAudit.add(NosAudit("Admin Warehouse",
            "Training",
            "Ketentuan :\n" +
                    "a. Bisa mengoperasikan Microsoft Office \n" +
                    "b. Bisa mengoperasikan sistem dealer (check & update stock) apabila dealer memiliki sistem.", 0))

        nosAudit.add(NosAudit("Admin Warehouse",
            "Training",
            "f. Training Network Operational Standard (Wajib dilakukan pada usia kerja > 1 bulan)\n" +
                    "\n" +
                    "* Exist, Done :   jika status lulus training di Dealer 100%\n" +
                    "* Exist, not good : jika status lulus training di Dealer 50% - 99%\n" +
                    "* Not Exist : jika status lulus training di Dealer 0% - 49%\n" +
                    "\n" +
                    "Note : Sudah lulus training (Nilai >= 80)", 0))
        nosAudit.add(NosAudit("Admin Warehouse",
            "Seragam",
            "Menggunakan Masker polos atau tidak bermotif.\n", 0))

        return nosAudit
    }


    fun generateDataH1Process(): List<NosAudit> {

        val nosAudit = ArrayList<NosAudit>()

        nosAudit.add(NosAudit("Security (Wing)/Juru Parkir",
            "Ketika sampai di showroom",
            "-Menyambut Konsumen ( Salam Satu Hati dan Salam Cuaca )\n" +
                    "- Menanyakan kebutuhan konsumen datang ke dealer (showroom/AHASS)\n" +
                    "- Mengarahkan ke area parkir", 0))

        nosAudit.add(NosAudit("Security (Wing)/Juru Parkir",
            "Memeriksa suhu tubuh konsumen",
            "-Melakukan pengecekan suhu tubuh konsumen yang memasuki area dealer\n" +
                    "- Mengkomunikasikan dengan sopan apabila suhu tubuh konsumen diatas 37.5, maka tidak diperbolehkan memasuki area dealer", 0))
        nosAudit.add(NosAudit("Security (Wing)/Juru Parkir",
            "Memastikan jarak 1m",
            "ketika ada konsumen / karyawan dealer mengalami kondisi emergency (tidak sadarkan diri, tergeletak, dan lain-lain), langsung menghubungi RS Rujukan, RS atau fasilitas kesehatan terdekat. Tetap menjaga kondusifitas, serta melakukan protokol kesehatan yang diperlukan seperti sterilisasi ruangan atau area, disinfektan ruangan atau langkah preventif lainnya", 0))

        nosAudit.add(NosAudit("Security (Wing)/Juru Parkir",
            "Meninggalkan showroom",
            "-mengingatkan konsumen memakai helm dengan benar / mengingatkan konsumen untuk \"Cari Aman\" dalam berkendara \n" +
                    "'- melepas konsumen dan mengucapkan terima kasih dan Salam Satu Hati ", 0))

        nosAudit.add(NosAudit("Greeter",
            "Ketika masuk ke showroom",
            "-Menyambut Konsumen ( Salam Satu Hati dan Salam Cuaca )\n" +
                    "-Menyakan kebutuhan konsumen (membeli motor, ambil STNK dll)\n" +
                    "-Menanyakan nama\n" +
                    "-Mengarahkan/mengenalkan konsumen dengan wing sales people/SC\n" +
                    "-Memberitahukan M/C yang dicari konsumen ke wing sales people/SC\n" +
                    "\n" +
                    "(Jika konsumen sudah membuat janji)\n" +
                    "Mengarahkan ke sales people yang bersangkutan\n", 0))

        nosAudit.add(NosAudit("Greeter",
            "Meninggalkan showroom",
            "-Melepas konsumen dan mengucapkan terima kasih dan Salam Satu Hati", 0))


        nosAudit.add(NosAudit("Sales Counter",

            "Membuat perjanjian",
            "-Salam Satu Hati\n" +
                    "-Menanyakan nama \n" +
                    "-Menanyakan keperluan konsumen dan tanggal kedatangan konsumen\n" +
                    "-No tlp konsumen\n" +
                    "-Mencatat di buku perjanjian", 0))


        nosAudit.add(NosAudit("Sales Counter",
            "Membuat perjanjian",

            "Mengkonfirmasi dokumen pendukung\n" +
                    "-Menginformasikan dokumen pendukung yang harus dibawa ke showroom (Jika konsumen ingin mengambil STNK, BPKB, syarat-syarat pembelian, dll)", 0))

        nosAudit.add(NosAudit("Sales Counter",
            "Menuju dealing table",
            "Mengkonfirmasi dokumen pendukung\n" +
                    "-Menginformasikan dokumen pendukung yang harus dibawa ke showroom (Jika konsumen ingin mengambil STNK, BPKB, syarat-syarat pembelian, dll)", 0))

        nosAudit.add(NosAudit("Sales Counter",
            "Menuju display area",
            "-Menanyakan pengalaman membeli motor di dealer tsb\n" +
                    "-Ucapan terima kasih telah membeli Honda\n" +
                    "-Informasi program RO (jika ada)\n" +
                    "\n", 0))

        nosAudit.add(NosAudit("Sales Counter",
            "Menuju display area",
            "-Menawarkan dan menemani konsumen untuk melihat produk yang didisplay (bila unit tersedia)", 0))

        nosAudit.add(NosAudit("Sales Counter",
            "Menuju display area",
            "-Memberikan brosur produk/ penggunaan tab/ HP/ Sales Talk bila unit tidak tersedia", 0))

        nosAudit.add(NosAudit("Sales Counter",
            "Menuju display area",
            "membersihkan unit display (jok, handgrip, speedometer, spion, serta touch point konsumen lainnya), accessories, dan apparel setelah kontak langsung dengan konsumen", 0))

        nosAudit.add(NosAudit("Sales Counter",
            "Menanyakan tentang unit dan after sales services",
            "-Sales Counter/Salesman roleplay menjelaskan pengetahuan dan informasi terkait product. Alternatif Tipe Produk : Product Terbaru (antara 3 yang terakhir launching)\n" +
                    "- Sales Counter/Salesman dapat menjelaskan konsep produk, design produk, performance dan min. 5 - 7 fitur produk dan kegunaannya berdasarkan Sales Talk Product Knowledge dengan menggunakan metode SPWA & C-F-A\n" +
                    "dibantu dengan My Hero Apps", 0))
        nosAudit.add(NosAudit("Sales Counter",
            "Menanyakan tentang unit dan after sales services",
            "-Menjelaskan fasilitas booking service, service kunjung, dan fasilitas AHASS lainnya", 0))
        nosAudit.add(NosAudit("Sales Counter",
            "Menanyakan tentang unit dan after sales services",
            "-Menginformasikan mengenai kegiatan-kegiatan  komunitas (untuk fokus produk)", 0))

        nosAudit.add(NosAudit("Sales Counter",
            "Menanyakan tentang aksesoris dan apparel",
            "-Menawarkan aksesoris tambahan (jika ada)\n" +
                    "- Menawarkan apparel sesuai kebutuhan konsumen\n" +
                    "- Mengarahkan konsumen untuk scan barcode di wobbler (hanya untuk tipe-tipe terbaru dan tertentu)", 0))
        nosAudit.add(NosAudit("Sales Counter",
            "Menanyakan tentang aksesoris dan apparel",
            "-Menjelaskan hilangnya garansi jika melakukan modifikasi atau penggantian part custom", 0))

        nosAudit.add(NosAudit("Sales Counter",
            "Mencoba riding test",
            "-Menawarkan riding test sesuai unit yang diminati (sesuai dengan juklak dari sales div)\n" +
                    "-Non sport : Riding position & start engine, stop & go; Sport : Additionaly riding experience in public road\n" +
                    "\n" +
                    "Riding test dihaarpkan dilakukan di safety riding centre (jika safety riding centre berada >100m dari dealer maka dapat dilakukan di tempat lain yang memungkinkan)", 0))

        nosAudit.add(NosAudit("Sales Counter",
            "Mencoba riding test",
            "- Jika unit sedang dipakai (pameran) maka sales people wajib untuk menawarkan untuk melakukan riding test di lain hari", 0))

        nosAudit.add(NosAudit("Sales Counter",
            "Mencoba riding test",
            "- Meminta konsumen memakai riding gear miliknya sendiri\n" +
                    "- Apabila konsumen tidak membawa riding gear, dapat melakukan penjadwalan ulang untuk riding test\n" +
                    "- menunjukkan SIM", 0))
        nosAudit.add(NosAudit("Sales Counter",
            "Mencoba riding test",
            "Membersihkan jok, hand grip, speedometer, spion, serta touch point konsumen lainnya pada riding test sebelum dipakai konsumen (dilakukan di depan konsumen)", 0))
        nosAudit.add(NosAudit("Sales Counter",
            "Mencoba riding test",
            "mengarahkan konsumen untuk memakai hand sanitizer yang terletak di area pintu masuk showroom, setelah melakukan riding test", 0))

        nosAudit.add(NosAudit("Sales Counter",
            "Mencoba riding test",
            "- Meminta konsumen untuk mengisi buku riding test", 0))
        nosAudit.add(NosAudit("Sales Counter",
            "Melakukan negosiasi",
            "- Menjelaskan ketersediaan unit (Termasuk indent, apabila unit tidak tersededia\n" +
             "- Menjelaskan harga\n" +
            "- Menjelaskan sales \n" +
            "- Menjelaskan proses pembayaran (syarat yang harus dilengkapi)Melakukan negosiasi",
             0))

        nosAudit.add(NosAudit("Sales Counter",
            "Melakukan negosiasi",
            "-Melakukan pengajuan (tambahan) diskon melalui sistem DMS kepada Kacab", 0))

        nosAudit.add(NosAudit("Sales Counter",
            "Melakukan negosiasi",
            "(Jika konsumen tidak jadi membeli)\n" +
                    "-Meminta konsumen untuk mengisi buku tamu/ diisi oleh SC \n" +
            "-Validasi data konsumen dengan call\n" +
            "- Memberikan no. kontak Sales Counter"
            , 0))
        nosAudit.add(NosAudit("Sales Counter",
            "Memutuskan pembelian",
            "- Mengisi SPK dan mengisi data konsumen (form permohonan STNK dan CDB)\n" +
                    "- Validasi data konsumen dengan call\n" +
                    "-Pengisian dibantu oleh  SC\n" +
                    "-Menjelaskan isi SPK dan data konsumen sekaligus \n" +
                    "-Mengkonfirmasi isi SPK sudah lengkap dan benar\n" +
                    "-Penandatanganan SPK oleh konsumen\n" +
                    "- Meminta konsumen untuk mengisi buku tamu / diisi SC\n" +
                    "- Meminta konsumen untuk menyerahkan foto copy kartu keluarga", 0))

        nosAudit.add(NosAudit("Sales Counter",
            "Memutuskan pembelian",
            "-melakukan pengecekan stock unit di DMS\n" +
                    "\n" +
                    "Bila customer batal melakukan pembelian:\n" +
                    "- Entry reason cancellation (SPK) di DMS", 0))
        nosAudit.add(NosAudit("Sales Counter",
            "Memutuskan pembelian",
            "-Menjelaskan proses indent, booking fee, dan prosedur refund\n" +
                    "-Mengisi form indent", 0))

        nosAudit.add(NosAudit("Sales Counter",
            "Memutuskan pembelian",
            "-Entry Indent di DMS.  Incl. mengajukan prioritas Indent ke BM (Bila ada request khusus dari konsumen)", 0))

        nosAudit.add(NosAudit("Sales Counter",
            "Memutuskan pembelian",
            "-Memberikan informasi kedatangan unit indent \n" +
                    "-Follow up call konsumen mengenai update kedatangan unit\n", 0))

        nosAudit.add(NosAudit("Sales Counter",
            "Memutuskan pembelian",
            "-Mempersiapkan kelengkapan administrasi dan mengantarkan ke kasir\n", 0))
        nosAudit.add(NosAudit("Sales Counter",
            "Memutuskan pembelian",
            "- Mereferensikan fincoy yang bisa dpilih konsumen\n" +
                    "- Memberitahukan dokumen yang perlu disiapkan untuk pengajuan kredit sesuai kebutuhan fincoy", 0))

        nosAudit.add(NosAudit("Sales Counter",
            "Memutuskan pembelian",
            "- Menginformasikan ke konsumen jika kredit sudah disetujui (tlp/SMS)", 0))
        nosAudit.add(NosAudit("Sales Counter",
            "Memutuskan pembelian",
            "-Info status PO Leasing (approve/reject) berdasarkan status pada DMS", 0))

        nosAudit.add(NosAudit("Sales Counter",
            "Memutuskan pembelian",
            "-Memberikan estimasi waktu STNK dan BPKB jadi\n" +
                    "-Memberikan informasi jika ada keterlambatan waktu jadi STNK dan bpkb", 0))
        nosAudit.add(NosAudit("Sales Counter",
            "Memutuskan pembelian",
            "- Info status document handling (STNK & BPKB) berdasarkan status pada DMS", 0))

        nosAudit.add(NosAudit("Sales Counter",
            "Memutuskan pembelian",
            "-Menanyakan dan menjelaskan mengenai waktu pengiriman", 0))


        nosAudit.add(NosAudit("Sales Counter",
            "Penjelasan AHASS terdekat",
            "-Menginformasikan lokasi AHASS terdekat\n" +
                    "-Menyerahkan kartu nama", 0))
        nosAudit.add(NosAudit("Sales Counter",
            "Meninggalkan showroom",
            "-Mengucapkan terima kasih\n" +
                    "-Salam Satu Hati\n" +
                    "-Mengingatkan cari Aman dalam berkendara", 0))
        nosAudit.add(NosAudit("Sales Counter",
            "*Diabaikan jika penjualan di BTL",
            "-Mengantarkan konsumen ke pintu keluar", 0))

        nosAudit.add(NosAudit("Sales Counter",
            "Follow up ",
            "-Melakukan follow up dan prospek dengan cara:\n" +
                    "   •sms / call dari database dan guestbook\n" +
                    "   •home visit dari database dan guestbook\n" +
                    "- Mengundang konsumen untuk datang ke showroom", 0))
        nosAudit.add(NosAudit("Sales Counter",
            "Follow up ",
            "- Melakukan activity follow up untuk outstanding prospect berdasarkan informasi (notifikasi) dari DMS", 0))

        nosAudit.add(NosAudit("Cashier",
            "Melakukan pembayaran",
            "- Salam Satu Hati\n" +
                    "- Salam Cuaca", 0))
        nosAudit.add(NosAudit("Cashier",
            "Melakukan pembayaran",
            "- Mengonfirmasi ke konsumen (detail informasi pembayaran: mis untuk pembayaran DP unit CBR 250 RR) diiringi dengan informasi  total uang yang harus dibayar konsumen\n" +
                    "- Menawarkan pembayaran ke konsumen dengan cash atau non tunai\n" +
                    "*Jika pembayaran dengan cash, menghitung uang didepan konsumen (menerima dan menyerahkan uang dengan kedua tangan) dan menyebut jumlah uang yang diterima\n" +
                    "- Melakukan proses administrasi pembayaran", 0))


        nosAudit.add(NosAudit("Cashier",
            "Melakukan pembayaran",

            "- Mencetak bukti pembayaran\n" +
                    "'- Konfirmasi bukti pembayaran ke konsumen", 0))
        nosAudit.add(NosAudit("Cashier",
            "Melakukan pembayaran",
            "- Membacakan konten PDSA (isi 5P, isi Catatan) kepada Konsumen", 0))

        nosAudit.add(NosAudit("Cashier",
            "Melakukan pembayaran",
            "- Menawarkan apa ada yang bisa dibantu lagi\n" +
                    "- Mengucapkan terima kasih\n" +
                    "- Salam Satu Hati\n" +
                    "- Salam cuaca", 0))

        nosAudit.add(NosAudit("Deliveryman",
            "Mendapat konfirmasi pengantaran unit",
            "Deliveryman memastikan 1 Delivery Car maksimal membawa 2 unit premium", 0))
        nosAudit.add(NosAudit("Cashier",
            "Mendapat konfirmasi pengantaran unit",
            "-Memeriksa nama konsumen \n" +
                    "-Memeriksa no. kontak konsumen", 0))

        nosAudit.add(NosAudit("Deliveryman",
            "Mendapat konfirmasi pengantaran unit",
            "-Memeriksa kelengkapan surat dan administrasi\n" +
                    "-Memastikan motor sudah lolos PDI (cek tag PDI)\n" +
                    "-Mengecek nomor rangka & nomor mesin\n" +
                    "- mengecek pengikatan motor dan memasang rubber", 0))
        nosAudit.add(NosAudit("Deliveryman",
            "Mendapat konfirmasi pengantaran unit",
            "-Mengucapkan salam ( Salam Satu Hati dan Salam Cuaca )\n" +
                    "-Konfirmasi unit yang akan dikirim\n" +
                    "-Konfirmasi nama konsumen\n" +
                    "-Konfirmasi siapa yang akan menerima motor\n" +
                    "-Konfirmasi jadwal pengiriman\n" +
                    "-Konfirmasi alamat pengiriman\n" +
                    "-Menelpon konsumen jika terlambat datang", 0))

        nosAudit.add(NosAudit("Deliveryman",
            "Menerima dan mengecek unit",
            "-Mengucapkan salam  ( Salam Satu Hati dan Salam Cuaca )\n" +
                    "-Memeriksa dokumen terkait (kwitansi, dll)\n" +
                    "-Mengecek fisik unit\n" +
                    "-Menjelaskan fitur utama (demonstrasi)\n" +
                    "-Menjelaskan fungsi fitur\n" +
                    "\n" +
                    "-Bersama konsumen mengecek kesesuaian no. mesin & rangka\n" +
                    "\n", 0))

        nosAudit.add(NosAudit("Deliveryman",
            "Menerima dan mengecek unit",
            "membersihkan jok dan hand grip motor (dilakukan di depan konsumen)", 0))


        nosAudit.add(NosAudit("Deliveryman",
            "Menerima dan mengecek unit",
            "- Garansi rangka dan listrik 1 tahun \n" +
                    "- Garansi mesin 3 tahun\n" +
                    "- Garansi PGMFI 5 tahun\n" +
                    "\n" +
                    "-KPB service\n" +
                    "\n" +
                    "-Mengajak konsumen untuk service di AHASS sendiri (H123)/terdekat (H1)\n" +
                    "\n" +
                    "-Menginformasikan fasilitas AHASS (Booking Service, Service kunjung, dan fasilitas AHASS lainnya)", 0))



        nosAudit.add(NosAudit("Deliveryman",
            "Menerima dan mengecek unit",
            "-Menyerahkan KSU (Kelengkapan Standard Unit)", 0))



        nosAudit.add(NosAudit("Deliveryman",
            "Menerima dan mengecek unit",
            "- Pengisian hari, tanggal, dan konsumen  tanda tangan  BAST yang ada di dalam buku servis\n" +
                    "-Memberikan kartu apresiasi yang ditanda tangani oleh PIC Dealer\n" +
                    "-Mengingatkan safety riding", 0))


        nosAudit.add(NosAudit("Deliveryman",
            "Menerima dan mengecek unit",
            "Mengkonfirmasi dan mengupdate data konsumen seperti alamat, nomer HP alternatif, email dan media sosial", 0))



        nosAudit.add(NosAudit("Admin H1",
            "Memutuskan pembelian",
            "- Melakukan input form CDB ke dalam sistem komputerisasi dealer\n" +
                    "- Memastikan seluruh field terisi sesuai dengan informasi DATA konsumen pada form CDB & kartu keluarga", 0))

        nosAudit.add(NosAudit("Mengambil STNK & BPKB",
            "Mengambil STNK & BPKB",
            "Memberikan informasi kepada konsumen (SMS/WA/Telpon) terkait waktu, tempat, dan syarat pengambilan STNK & BPKB", 0))



        nosAudit.add(NosAudit("Admin H1",
            "Mengambil STNK & BPKB",
            "melakukan follow up (Paling lambat H-1 dari tanggal yang dijanjikan) jika :\n" +
                    "-STNK mengalami keterlambatan ( lebih dari 14 Hari Kerja atau yang telah dijanjikan )\n" +
                    "-BPKB mengalami keterlambatan ( lebih dari 3 bulan atau yang telah dijanjikan )\n", 0))




        nosAudit.add(NosAudit("Admin H1",
            "Mengambil STNK & BPKB",
            "- Mengucapkan salam  ( Salam Satu Hati dan Salam Cuaca )\n" +
                    "- Menanyakan kebutuhan konsumen", 0))




        nosAudit.add(NosAudit("Admin H1",
            "Mengambil STNK & BPKB",
            "-Meminta konsumen untuk memeriksa kesesuaian STNK\n" +
                    "Mengonfirmasi data konsumen:\n" +
                    "-nama\n" +
                    "-alamat\n" +
                    "-nomor mesin\n" +
                    "-nomor rangka", 0))


        nosAudit.add(NosAudit("Admin H1",
            "Mengambil STNK & BPKB",
            "-Mengucapkan terima kasih \n" +
                    "-Salam Satu Hati", 0))


        nosAudit.add(NosAudit("PIC CRM ",
            "Analisa Data konsumen ",
            "Analisa Data konsumen \n" +
                    "-Melakukan analisa konsumen untuk repeat order (koordinasi dengan kacab)\n" +
                    "-Melakukan analisa konsumen yang akan di undang service (koordinasi dengan kabeng)\n" +
                    "- Melakukan analisa alasan konsumen mengapa tidak jadi beli (koordinasi dengan kacab)\n" +
                    "-Melakukan analisa alasan konsumen mengapa tidak kembali service  (koordinasi dengan kabeng) ", 0))
        nosAudit.add(NosAudit("PIC CRM ",
            "Analisa Data konsumen ",
            "Menganalisa data konsumen untuk dilakukan follow up RO yang berada di 1 lokasi dengan BTL activity regular seperti canvassing, pameran, gathering ", 0))

        nosAudit.add(NosAudit("PIC CRM ",
            "Distribusi Data ",
            "-Melakukan distribusi data konsumen untuk di Follow up salesforce minimal seminggu sekali\n" +
                    "- Melakukan distribusi data konsumen kepada sales force yang akan melakukan BTL activity regular di lokasi yang sama dengan domisili konsumen agar dapat dilakukan follow up\n" +
                    "\n ", 0))

        nosAudit.add(NosAudit("PIC CRM ",
            "Distribusi Data ",
            "-Melakukan distribusi data konsumen untuk di Follow up salesforce minimal seminggu sekali\n" +
                    "- Melakukan distribusi data konsumen kepada sales force yang akan melakukan BTL activity regular di lokasi yang sama dengan domisili konsumen agar dapat dilakukan follow up\n" +
                    "\n ", 0))

        nosAudit.add(NosAudit("PIC CRM ",
            "Distribusi Data ",
            "-Menggunakan data konsumen H1 untuk diberikan ke H2 ", 0))

        nosAudit.add(NosAudit("PIC CRM ",
            "Distribusi Data ",
            " ", 0))
        nosAudit.add(NosAudit("PIC CRM ",
            "Distribusi Data ",
            "-Melakukan distribusi data konsumen untuk di follow up service oleh Admin CRM ", 0))
        nosAudit.add(NosAudit("PIC CRM ",
            "Distribusi Data ",
            "-Melakukan distribusi data konsumen untuk di follow up service oleh Admin CRM ", 0))

        nosAudit.add(NosAudit("PIC CRM ",
            "Result & Monitoring ",
            "-Melakukan monitoring terhadap hasil follow up sales force setiap hari\n ", 0))

        nosAudit.add(NosAudit("PIC CRM ",
            "Result & Monitoring ",
            "-Melakukan monitoring terhadap hasil follow up sales force setiap hari\n ", 0))

        nosAudit.add(NosAudit("PIC CRM ",
            "Result & Monitoring ",
            "-Mendata hasil follow up sales force ", 0))

        nosAudit.add(NosAudit("PIC CRM ",
            "Result & Monitoring ",
            "-Melakukan review minimal seminggu sekali kepada sales force untuk status follow up * ", 0))
        nosAudit.add(NosAudit("Admin CRM H1 ",
            "Follow up  ",
            "Validasi data\n" +
                    "(maksimal 5 hari kerja setelah pengantaran)\n" +
                    "-Mengucapkan terima kasih atas pembelian motor by call\n" +
                    "-Menanyakan kritik dan saran (feedback layanan Dealer) ", 0))
        nosAudit.add(NosAudit("Admin CRM H1 ",
            "Follow up  ",
            "Verifikasi data konsumen dan no tlp\n" +
                    "-Mengingatkan untuk service KPB 1\n" +
                    "- Menginformasikan fasilitas AHASS( booking servis/ servis kunjung)\n" +
                    "-Mengundang ke dealer\n" +
                    "\n" +
                    "*khusus untuk unit premium dan pembelian pertama, follow up dilakukan oleh PIC Dealer ", 0))

        nosAudit.add(NosAudit("Kacab Dealer ",
            "Ketika masuk ke showroom ",
            "-Memberikan informasi mengenai list konsumen yang akan datang ke showroom\n" +
                    "-Memberikan informasi terkait indent informasi (type,colour,ETA) ", 0))

        nosAudit.add(NosAudit("Kacab Dealer ",
            "Memutuskan indent ",
            "-Memonitor proses indent secara update (open,close,cancel)\n" +
                    "-Menginformasi feedback dari MD terkait indent kepada salespeople untuk ETA (Estimation time arrived) ", 0))

        nosAudit.add(NosAudit("Kacab Dealer ",
            "Result & Monitoring CRM  ",
            "-Melakukan monitoring terhadap hasil follow up sales force setiap hari\n" +
                    "-Melakukan monitoring terhadap hasil follow up data RO konsumen yang berada di 1 lokasi dengan BTL Activity Regular\n" +
                    "\n" +
                    "\n ", 0))

        nosAudit.add(NosAudit("Kacab Dealer ",
            "Result & Monitoring CRM  ",
            "-Mendata hasil follow up sales force dan servis ", 0))
        nosAudit.add(NosAudit("Kacab Dealer ",
            "Result & Monitoring CRM  ",
            "-Melakukan review minimal seminggu sekali kepada sales force untuk status follow up * ", 0))

        nosAudit.add(NosAudit("Admin Warehouse ",
            "Menerima dan memeriksa unit dari Main Dealer ",
            "-Memeriksa kesesuaian unit dengan surat jalan dari MD\n" +
                    "-Memeriksa kesesuaian KSU (tool kit, buku service) ", 0))

        nosAudit.add(NosAudit("Admin Warehouse ",
            "Pengiriman unit ke Konsumen ",
            "Instruksi berdasarkan SPK yang sah  ", 0))

        nosAudit.add(NosAudit("Admin Warehouse ",
            "Pengiriman unit ke Konsumen ",
            "PDI Unit sudah OK dan pastikan KSU sudah sesuai dengan unit motornya ", 0))

        nosAudit.add(NosAudit("Admin Warehouse ",
            "Pengiriman unit ke Konsumen ",
            "Menginformasikan nama, alamat dan nomor kontak konsumen ", 0))

        nosAudit.add(NosAudit("Admin Warehouse ",
            "Update Data Stock Dealer ",
            "Dilakukan setiap hari berdasarkan data transaksi penerimaan dan pengiriman unit ", 0))

        nosAudit.add(NosAudit("Admin Warehouse ",
            "Update Data Stock Dealer ",
            "- Tanda Serah terima unit dari Main Dealer\n" +
                    "- Tanda Serah terima unit dengan Konsumen ", 0))
        nosAudit.add(NosAudit("Kepala Cabang ",
            " ",
            "bekerja sama dengan security, greeter, dan SA untuk menjaga dan mengontrol implementasi protokol kesehatan dealer ", 0))
        nosAudit.add(NosAudit("Kepala Cabang ",
            " ",
            "mengetahui RS rujukan terdekat dan menginformasikan kepada Security / Sales Counter/Service Advisor ", 0))

        return nosAudit
    }


    fun generateDataH23People(): List<NosAudit> {

        val nosAudit = ArrayList<NosAudit>()

        nosAudit.add(NosAudit(
            "Kepala Bengkel   ",
            "Jumlah ",
            "Untuk AHASS yang memiliki Unit entry : \n" +
                    "- UE > 1.500 WAJIB memiliki 1 kepala bengkel Independen (tidak boleh merangkap apapun)\n" +
                    "- UE < 1.500 boleh dirangkap oleh kepala cabang dealer yang telah memenuhi syarat training dan kompetensi\n" +
                    "*H123 wajib punya Kabeng independen, H23 boleh membawahi 2 outlet selama gabungan UE kedua outlet < 1500.  ",
            0))

        nosAudit.add(NosAudit(
            "Kepala Bengkel   ",
            "Training ",
            "Sudah melakukan training  :\n" +
                    "1. Chief Workshop Training\n" +
                    "\n" +
                    "Nilai 2 : Kabeng sudah training Kabeng versi AHM\n" +
                    "Nilai 0 : Kabeng belum training  ",
            0))
        nosAudit.add(NosAudit(
            "Kepala Bengkel   ",
            "Training ",
            "Sudah melakukan training :\n" +
                    "Customer Service for leader 1 - Complaint Handling (Wajib dilakukan pada usia kerja 0 - 3 bulan)\n" +
                    "\n" +
                    "Nilai 2 : Kabeng sudah training \n" +
                    "Nilai 0 : Kabeng belum training   ",
            0))

        nosAudit.add(NosAudit(
            "Kepala Bengkel   ",
            "Training ",
            "Sudah melakukan training :\n" +
                    "Customer Service for leader 2 (Wajib dilakukan pada usia kerja 0 - 6 bulan)\n" +
                    "\n" +
                    "Nilai 2 : Kabeng sudah training \n" +
                    "Nilai 0 : Kabeng belum training   ",
            0))
        nosAudit.add(NosAudit(
            "Kepala Bengkel   ",
            "Training ",
            "Sudah melakukan training  :\n" +
                    "Parts Management Knowledge\n" +
                    "\n" +
                    "Nilai 2 : Kabeng sudah training\n" +
                    "Nilai 0 : Kabeng belum training   ",
            0))
        nosAudit.add(NosAudit(
            "Kepala Bengkel   ",
            "Training ",
            "Sudah melakukan training :\n" +
                    "Training Technical Product Knowledge untuk Kabeng yang bukan berasal dari mekanik\n" +
                    "\n" +
                    "Nilai 2 : Kabeng sudah training\n" +
                    "Nilai 0 : Kabeng belum training \n" +
                    "\n" +
                    "Apabila Kabeng berasal dari mekanik, nilai NA  ",
            0))
        nosAudit.add(NosAudit(
            "Kepala Bengkel   ",
            "Training ",
            "Sudah melakukan training :\n" +
                    "TFT Basic Orientation Traning - BOT (Wajib dilakukan pada usia kerja 0 - 1 bulan)\n" +
                    "\n" +
                    "Nilai 2 : Kabeng sudah training \n" +
                    "Nilai 0 : Kabeng belum training   ",
            0))
        nosAudit.add(NosAudit(
            "Kepala Bengkel   ",
            "Training ",
            "Sudah melakukan training :\n" +
                    "-Training Network Operational Standard (Wajib dilakukan pada usia kerja 0 - 1 bulan)\n" +
                    "\n" +
                    "Nilai 2 : Kabeng sudah training\n" +
                    "Nilai 0 : Kabeng belum training   ",
            0))

        nosAudit.add(NosAudit(
            "Kepala Mekanik  ",
            "Jumlah ",
            "1 Kepala mekanik independen membawahi maksimal 10 mekanik pit\n" +
                    "\n" +
                    "Note : Independen tidak boleh merangkap jabatan lain kecuali claim processor atau final inspector sesuai ketentuan\n" +
                    "Nilai 2 : Ada dan sesuai dengan Ratio\n" +
                    "Nilai 1 : Ada namun tidak sesuai dengan Ratio\n" +
                    "Nilai 0 : Tidak ada  ",
            0))

        nosAudit.add(NosAudit(
            "Kepala Mekanik  ",
            "Training\n" +
                    "\n" +
                    "Pembagi dari jumlah aktual kepala mekanik independent\n" +
                    "\n" +
                    "Contoh :\n" +
                    "Mekanik 15, kepala mekanik harusnya 2 orang, hanya ada 1 orang Kamek. Yang bersangkutan sudah training TTL 3 dan Kamek.\n" +
                    "\n" +
                    "Untuk training Kamek berarti nilainya : 1/1 atau 100% ",
            "Sudah melakukan training :\n" +
                    "- TTL 3- Diagnosis training  (6 bulan atau lebih)\n" +
                    "\n" +
                    "Nilai 2 : 100% Kamek sudah training \n" +
                    "Nilai 1 :50%-99% Kamek mengikuti Training  \n" +
                    "Nilai 0 : < 50% Kamek mengikuti training\n" +
                    "\n" +
                    "Note: yang diperhitungkan adalah Kamek yang menjabat 6 bulan atau lebih. Apabila masa kerja sbg Kamek < 6 bulan, maka tidak diperhitungkan dalam penilaian.  ",
            0))
        nosAudit.add(NosAudit(
            "Kepala Mekanik  ",
            "Training\n" +
                    "\n" +
                    "Pembagi dari jumlah aktual kepala mekanik independent\n" +
                    "\n" +
                    "Contoh :\n" +
                    "Mekanik 15, kepala mekanik harusnya 2 orang, hanya ada 1 orang Kamek. Yang bersangkutan sudah training TTL 3 dan Kamek.\n" +
                    "\n" +
                    "Untuk training Kamek berarti nilainya : 1/1 atau 100% ",
            "  ",
            0))
        nosAudit.add(NosAudit(
            "Kepala Mekanik  ",
            "Training\n" +
                    "\n" +
                    "Pembagi dari jumlah aktual kepala mekanik independent\n" +
                    "\n" +
                    "Contoh :\n" +
                    "Mekanik 15, kepala mekanik harusnya 2 orang, hanya ada 1 orang Kamek. Yang bersangkutan sudah training TTL 3 dan Kamek.\n" +
                    "\n" +
                    "Untuk training Kamek berarti nilainya : 1/1 atau 100% ",
            "Sudah melakukan training :\n" +
                    "-Training Network Operational Standard (Wajib dilakukan pada usia kerja 0 - 1 bulan)\n" +
                    "\n" +
                    "Nilai 2 : 100% Kamek sudah training \n" +
                    "Nilai 1 :50%-99% Kamek mengikuti Training  \n" +
                    "Nilai 0 : < 50% Kamek mengikuti training\n  ",
            0))
        nosAudit.add(NosAudit(
            "Kepala Mekanik  ",
            "Seragam ",
            "Sama dengan seragam mekanik tanpa topi, masker, apron\n" +
                    "\n" +
                    "Seragam kerja diberikan gratis oleh AHASS\n" +
                    "2 set/tahun/kepala mekanik\n" +
                    "Menggunakan Masker Polos (disesuaikan dengan warna unsur seragam, yaitu abu-abu/putih/merah/hitam)\n" +
                    "Menggunakan face shield (jika berinteraksi dengan konsumen)  ",
            0))
        nosAudit.add(NosAudit(
            "Kepala Mekanik  ",
            "Alat Perlindungan Diri (APD) ",
            "1. Menggunakan safety shoes\n" +
                    "\n" +
                    "Nilai 2 : 100% kepala mekanik menggunakan APD\n" +
                    "Nilai 0 : < 100% kepala mekanik menggunakan APD\n" +
                    "\n" +
                    "Sepatu safety diberikan gratis oleh pemilik AHASS\n" +
                    "1 set/ 2 tahun  ",
            0))
        nosAudit.add(NosAudit(
            "Final Inspector  ",
            "Jumlah ",
            "1 Final inspector independen = max 1.500 Unit Entry\n" +
                    "\n" +
                    "Independen tidak boleh merangkap jabatan lain kecuali claim processor atau Kepala Mekanik sesuai ketentuan\n" +
                    "\n" +
                    "Untuk unit entry  1 s.d 1.000, final inspector masih boleh dirangkap dengan kepala mekanik  ",
            0))
        nosAudit.add(NosAudit(
            "  ",
            "Training\n" +
                    "\n" +
                    "Pembagi dari jumlah aktual final inspector independent\n" +
                    "\n" +
                    "Contoh :\n" +
                    "Unit Entry 5.300, harusnya final inspector ada 4. Aktual hanya ada 3 orang. Yang bersangkutan sudah TTL 2\n" +
                    "\n" +
                    "Untuk training TTL 2 berarti nilainya :3/3 atau 100% ",
            "Sudah melakukan training (dengan masa kerja) :\n" +
                    "-  TTL 2\n" +
                    "\n" +
                    "Nilai 2 : 100% FI sudah training \n" +
                    "Nilai 1 : 50%-99% FI mengikuti Training  \n" +
                    "Nilai 0 : < 50% FI mengikuti training\n" +
                    "\n" +
                    "Note: yang diperhitungkan adalah FI yang menjabat 6 bulan atau lebih. Apabila masa kerja sbg FI < 6 bulan, maka tidak diperhitungkan dalam penilaian.  ",
            0))
        nosAudit.add(NosAudit(
            "Final Inspector  ",
            "Training\n" +
                    "\n" +
                    "Pembagi dari jumlah aktual final inspector independent\n" +
                    "\n" +
                    "Contoh :\n" +
                    "Unit Entry 5.300, harusnya final inspector ada 4. Aktual hanya ada 3 orang. Yang bersangkutan sudah TTL 2\n" +
                    "\n" +
                    "Untuk training TTL 2 berarti nilainya :3/3 atau 100% ",
            " Sudah melakukan training  :\n" +
                    "-Training Network Operational Standard (Wajib dilakukan pada usia kerja 0 - 1 bulan)\n" +
                    "\n" +
                    "Nilai 2 : 100% FI sudah training \n" +
                    "Nilai 1 : 50%-99% FI mengikuti Training  \n" +
                    "Nilai 0 : < 50% FI mengikuti training\n  ",
            0))
        nosAudit.add(NosAudit(
            "Final Inspector  ",
            " Training\n" +
                    "\n" +
                    "Pembagi dari jumlah aktual final inspector independent\n" +
                    "\n" +
                    "Contoh :\n" +
                    "Unit Entry 5.300, harusnya final inspector ada 4. Aktual hanya ada 3 orang. Yang bersangkutan sudah TTL 2\n" +
                    "\n" +
                    "Untuk training TTL 2 berarti nilainya :3/3 atau 100%",
            " Sudah melakukan training  :\n" +
                    "-Training Network Operational Standard (Wajib dilakukan pada usia kerja 0 - 1 bulan)\n" +
                    "\n" +
                    "Nilai 2 : 100% FI sudah training \n" +
                    "Nilai 1 : 50%-99% FI mengikuti Training  \n" +
                    "Nilai 0 : < 50% FI mengikuti training\n  ",
            0))
        nosAudit.add(NosAudit(
            "Final Inspector  ",
            "Seragam ",
            "Sama dengan seragam mekanik tanpa topi, masker, apron\n" +
                    "\n" +
                    "Seragam kerja diberikan gratis oleh AHASS\n" +
                    "2 set/tahun/final inspector\n" +
                    "Menggunakan Masker Polos (disesuaikan dengan warna unsur seragam, yaitu abu-abu/putih/merah/hitam)\n" +
                    "Menggunakan face shield (jika berinteraksi dengan konsumen)  ",
            0))
        nosAudit.add(NosAudit(
            "Final Inspector  ",
            "Alat Perlindungan Diri ",
            "1. Menggunakan safety shoes\n" +
                    "\n" +
                    "Nilai 2 : 100% final inspector menggunakan APD\n" +
                    "Nilai 0 : < 100% final inspector menggunakan APD\n" +
                    "\n" +
                    "Sepatu safety diberikan gratis oleh pemilik AHASS\n" +
                    "1 set/ 2 tahun  ",
            0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Jumlah ",
            "1 Service advisor independen = max 1.000 Unit Entry\n" +
                    "H123 & H23 diperbolehkan untuk dirangkap selama UE < 1000.\n" +
                    "\n" +
                    "Independen tidak boleh merangkap jabatan lain  ",
            0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Training\n" +
                    "\n" +
                    "Pembagi dari jumlah aktual service advisor independent\n" +
                    "\n" +
                    "Contoh :\n" +
                    "Unit Entry 5.300, harusnya service advisor ada 6. Aktual hanya ada 3 orang. Yang sudah training SA kurikulum 2015 = 2\n" +
                    "\n" +
                    "Untuk training SA berarti nilainya :2/3 atau 66,67% ",
            " Sudah melakukan training (dengan masa kerja) :\n" +
                    "-  TTL 2\n" +
                    "\n" +
                    "Nilai 2 : 100% SA sudah training \n" +
                    "Nilai 1 : 50%-99% SA mengikuti Training  \n" +
                    "Nilai 0 : < 50% SA mengikuti training\n" +
                    "\n" +
                    "Note: yang diperhitungkan adalah SA yang menjabat 6 bulan atau lebih. Apabila masa kerja sbg SA < 6 bulan, maka tidak diperhitungkan dalam penilaian.  ",
            0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Training\n" +
                    "\n" +
                    "Pembagi dari jumlah aktual service advisor independent\n" +
                    "\n" +
                    "Contoh :\n" +
                    "Unit Entry 5.300, harusnya service advisor ada 6. Aktual hanya ada 3 orang. Yang sudah training SA kurikulum 2015 = 2\n" +
                    "\n" +
                    "Untuk training SA berarti nilainya :2/3 atau 66,67% ",
            "Sudah melakukan training  :\n" +
                    "- Training SA kurikulum AHM 2015\n" +
                    "\n" +
                    "Nilai 2 : 100% SA sudah training \n" +
                    "Nilai 1 : 50%-99% SA mengikuti Training  \n" +
                    "Nilai 0 : < 50% SA mengikuti training  ",
            0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Training\n" +
                    "\n" +
                    "Pembagi dari jumlah aktual service advisor independent\n" +
                    "\n" +
                    "Contoh :\n" +
                    "Unit Entry 5.300, harusnya service advisor ada 6. Aktual hanya ada 3 orang. Yang sudah training SA kurikulum 2015 = 2\n" +
                    "\n" +
                    "Untuk training SA berarti nilainya :2/3 atau 66,67% ",
            "\n" +
                    "Sudah melakukan training :\n" +
                    "- Basic Orientation Traning - BOT (Wajib dilakukan pada usia kerja 0 - 1 bulan oleh Kacab / Kabeng) sebelum training NOS\n" +
                    "\n" +
                    "Nilai 2 : 100% SA sudah training \n" +
                    "Nilai 1 : 50%-99% SA mengikuti Training  \n" +
                    "Nilai 0 : < 50% SA mengikuti training  ",
            0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Training\n" +
                    "\n" +
                    "Pembagi dari jumlah aktual service advisor independent\n" +
                    "\n" +
                    "Contoh :\n" +
                    "Unit Entry 5.300, harusnya service advisor ada 6. Aktual hanya ada 3 orang. Yang sudah training SA kurikulum 2015 = 2\n" +
                    "\n" +
                    "Untuk training SA berarti nilainya :2/3 atau 66,67% ",
            "Sudah melakukan training  :\n" +
                    "-Training Network Operational Standard (Wajib dilakukan pada usia kerja 0 - 1 bulan)\n" +
                    "\n" +
                    "Nilai 2 : 100% SA sudah training \n" +
                    "Nilai 1 : 50%-99% SA mengikuti Training  \n" +
                    "Nilai 0 : < 50% SA mengikuti training  ",
            0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Training\n" +
                    "\n" +
                    "Pembagi dari jumlah aktual service advisor independent\n" +
                    "\n" +
                    "Contoh :\n" +
                    "Unit Entry 5.300, harusnya service advisor ada 6. Aktual hanya ada 3 orang. Yang sudah training SA kurikulum 2015 = 2\n" +
                    "\n" +
                    "Untuk training SA berarti nilainya :2/3 atau 66,67% ",
            "Sudah melakukan training  :\n" +
                    "- Customer Service for FLP Training (Wajib dilakukan pada usia kerja 0 - 3 bulan)\n" +
                    "\n" +
                    "Nilai 2 : 100% SA sudah training \n" +
                    "Nilai 1 : 50%-99% SA mengikuti Training  \n" +
                    "Nilai 0 : < 50% SA mengikuti training\n  ",
            0))


        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Standard Penampilan",
            "1.Wajah : \n" +
                    "Pria : ,wajah terlihat segar/tidak berminyak/tidak kusam, kumis/jenggot rapi (tdk dipanjangkan)\n" +
                    "\n" +
                    "Wanita :  Rias Wajah menggunakan make up (bedak,alis,eye shadow,blush on,lipstik sesuai standar)\n" +
                    "\n" +
                    "2.Rambut : \n" +
                    "Pria : Rambut Pendek Rapi, tidak menutupi mata dan tidak menyentuh kerah baju dan telinga\n" +
                    "\n" +
                    "Wanita :  Rambut rapi / tidak menutupi pandangan (panjang rambut lebih dari sebahu wajib dikuncir ponytail )\n" +
                    "\n" +
                    "3.Wewangian dan kebersihan:\n" +
                    "Aroma tubuh tidak mengganggu, tangan bersih, kuku tangan dan kuku kaki bersih. Untuk pria kuku pendek rapi.\n",
            0))

        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Standard Penampilan",
            "4. Seragam : \n" +
                    "\n" +
                    "Ketentuan :\n" +
                    " Senin & Rabu : Seragam Honda berkerah merah,Celana / rok hitam \n" +
                    " Selasa & Kamis : Seragam Honda berkerah putih,Celana / rok hitam  \n" +
                    "Jumat : baju khas daerah / batik (batik Dealer), Celana / Rok Hitam\n" +
                    "Sabtu & Minggu : casual (berkerah) atau sesuai kebijakan masing2 dealer\n" +
                    "(kondisi Baju : Rapi, Bersih, Warna tidak kusam, tidak robek, dan logo Honda terlihat)\n" +
                    "* Sesuai dengan aturan seragam dari HC3 AHM\n" +
                    "\n" +
                    "5.Atribut  : \n" +
                    "- Senin - Jumat : Wanita menggunakan sepatu pantofel warna hitam heel minimum 5 cm kecuali wanita hamil\n" +
                    "- Senin - Jumat : Pria menggunakan sepatu pantofel warna hitam\n" +
                    "- Sabtu - Minggu atau saat berada di lapangan : Sepatu Kets  warna Netral (Putih, Hitam, Abu-abu, Merah Maroon, Biru Tua, Putih)\n" +
                    "- ID Card dengan Kalung Honda Merah\n" +
                    "- Pin One Heart di dada kiri\n" +
                    "- Jam Tangan Kulit / Karet / Canvas Warna Hitam / Coklat Tua / Gelap\n" +
                    "\n" +
                    "6. Aksesories :\n" +
                    "Pria : aksesoris tidak berlebihan (tidak lebih dari 5 titik)\n" +
                    " Wanita : aksesoris tidak berlebihan (tidak lebih dari 7 titik)\n" +
                    "\n" +
                    "Menggunakan Masker polos atau tidak bermotif.\n" +
                    "-Masker kesehatan (warna bebas)\n" +
                    "-Masker kain disesuaikan dengan warna unsur seragam, yaitu abu-abu/putih/merah/hitam* *Kecuali Jum'at-Sabtu-Minggu\n" +
                    "Menggunakan face shield (jika berinteraksi dengan konsumen)",
            0))

        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Alat Perlindungan Diri (APD)",
            "1. Menggunakan safety shoes\n" +
                    "\n" +
                    "Nilai 2 : 100% service advisor menggunakan APD\n" +
                    "Nilai 0 : < 100% service advisor menggunakan APD\n" +
                    "\n" +
                    "Sepatu safety diberikan gratis oleh pemilik AHASS\n" +
                    "1 set/ 2 tahun",
            0))



        nosAudit.add(NosAudit(
            "Mechanic  ",
            "Produktivitas  ",
            " Nilai 2 jika produktivitas mekanik pit 7 s.d 9 (7 =< x =< 9)\n" +
                    "Nilai 1 jika produktivitas mekanik pit  <7 atau >9\n" +
                    "\n" +
                    "\n" +
                    "Perhitungan produktivitas : Unit entry/jumlah hari kerja mekanik dalam 1 bulan/jumlah mekanik\n" +
                    "\n" +
                    "Mekanik yang dihitung adalah semua mekanik yang bekerja di dalam pit (termasuk yang untrained) kecuali mekanik magang\n" +
                    "\n" +
                    "Hari kerja : 25 hari kerja ",
            0))

        nosAudit.add(NosAudit(
            "Mechanic  ",
            "Training  ",
            "100% sudah training TTL 1\n" +
                    "(Diperbolehkan maksimum 1 orang Untrain)\n" +
                    "\n" +
                    "Nilai 2 : 100% syarat terpenuhi\n" +
                    "Nilai 0 : syarat tidak terpenuhi\n  ",
            0))

        nosAudit.add(NosAudit(
            "Mechanic  ",
            "Training  ",
            "Minimal ada 1 orang mekanik TTL 3\n" +
                    "Diperbolehkan Kabeng/Kamek/FI/SA\n" +
                    "\n" +
                    "Nilai 2 : terpenuhi\n" +
                    "Nilai 0 : tidak terpenuhi  ",
            0))

        nosAudit.add(NosAudit(
            "Mechanic  ",
            "Training  ",
            "Untuk mekanik pit s.d 5, minimal 2 orang mekanik pit sudah training TTL 2/3\n" +
                    "Untuk mekanik pit > 5, minimal 50% mekanik pit sudah training TTL 2/3\n" +
                    "\n" +
                    "Nilai 2 : 100% syarat terpenuhi\n" +
                    "Nilai 0 : syarat tidak terpenuhi  ",
            0))
        nosAudit.add(NosAudit(
            "Mechanic  ",
            "Training  ",
            "Sudah melakukan training  :\n" +
                    "-Training Network Operational Standard (Wajib dilakukan pada usia kerja 0 - 1 bulan)\n" +
                    "\n" +
                    "Nilai 2 : 100% Mechanic sudah training \n" +
                    "Nilai 1 : 50%-99% Mechanic mengikuti Training  \n" +
                    "Nilai 0 : < 50% Mechanic mengikuti training\n" +
                    "\n" +
                    "*Khusus mekanik Big  wing  ",
            0))

        nosAudit.add(NosAudit(
            "Mechanic  ",
            "Seragam kerja\n" +
                    "Mekanik TTL 1 dan TTL 2  ",
            "1. Menggunakan seragam kerja\n" +
                    "\n" +
                    "Nilai 2 : 100% Mekanik TTL 1/2 menggunakan seragam kerja\n" +
                    "Nilai 0 : < 100% Mekanik TTL 1/2 menggunakan seragam kerja\n" +
                    "\n" +
                    "Seragam kerja diberikan gratis oleh AHASS\n" +
                    "2 set/tahun/mekanik\n" +
                    "\n" +
                    "Menggunakan Masker Polos (disesuaikan dengan warna unsur seragam, yaitu abu-abu/putih/merah/hitam)\n" +
                    "Menggunakan face shield (jika berinteraksi dengan konsumen)  ",
            0))

        nosAudit.add(NosAudit(
            "Mechanic  ",
            "Seragam kerja\n" +
                    "Mekanik TTL3  ",
            "1. Menggunakan seragam kerja \n" +
                    "Untuk wing dealer, mekanik menggunakan badge \"wing dealer\" di seragamnya\n" +
                    "\n" +
                    "Nilai 2 : 100% Mekanik TTL 3 menggunakan seragam kerja\n" +
                    "Nilai 0 : < 100% Mekanik  TTL 3 menggunakan seragam kerja\n" +
                    "\n" +
                    "Seragam kerja diberikan gratis oleh AHASS\n" +
                    "2 set/tahun/mekanik\n" +
                    "\n" +
                    "Menggunakan Masker Polos (disesuaikan dengan warna unsur seragam, yaitu abu-abu/putih/merah/hitam)\n" +
                    "Menggunakan face shield (jika berinteraksi dengan konsumen)  ",
            0))
        nosAudit.add(NosAudit(
            "Mechanic  ",
            "Alat Perlindungan Diri (APD)  ",
            "1. Menggunakan safety shoes, apron, masker, topi\n" +
                    "\n" +
                    "Nilai 2 : 100% Mekanik pit non Heavy Repair menggunakan 100% APD\n" +
                    "Nilai 0 : < 100% Mekanik pit non Heavy Repair menggunakan 100% APD\n" +
                    "\n" +
                    "Semua APD disediakan gratis oleh AHASS\n" +
                    "Sepatu safety 1 set/2 tahun/ mekanik  ",
            0))

        nosAudit.add(NosAudit(
            "Mechanic  ",
            "Alat Perlindungan Diri (APD) Heavy Repair  ",
            "1. Menggunakan safety shoes\n" +
                    "2. Tersedia sarung tangan, ear plug, masker\n" +
                    "\n" +
                    "Nilai 2 : 100% Mekanik heavy repair menggunakan safety shoes dan 100% tersedia sarung tangan, ear plug, masker\n" +
                    "Nilai 0 : < 100% Mekanik heavy repair menggunakan safety shoes dan 100% tersedia sarung tangan, ear plug, masker\n" +
                    "\n" +
                    "Semua APD disediakan gratis oleh AHASS  ",
            0))

        nosAudit.add(NosAudit(
            "Frontdesk Officer\n" +
                    "(FDO)  ",
            "Jumlah  ",
            "1 Front Desk Officer independen = max 1.500 Unit Entry\n" +
                    "\n" +
                    "Independen = tidak boleh merangkap jabatan apapun  ",
            0))

        nosAudit.add(NosAudit(
            "Frontdesk Officer\n" +
                    "(FDO)  ",
            "Jumlah  ",
            "Sudah melakukan training :\n" +
                    "- Basic Orientation Traning - BOT (Wajib dilakukan pada usia kerja 0 - 1 bulan oleh Kacab / Kabeng) sebelum training NOS\n" +
                    "\n" +
                    "Nilai 2 : 100% FDO sudah training \n" +
                    "Nilai 1 : 50%-99% FDO mengikuti Training  \n" +
                    "Nilai 0 : < 50% FDO mengikuti training\n" +
                    "\n" +
                    "*Untuk FDO apabila  merangkap pekerjaan yang berkomunikasi dengan konsumen\n  ",
            0))


        nosAudit.add(NosAudit(
            "Frontdesk Officer\n" +
                    "(FDO)  ",
            "Jumlah  ",
            "Sudah melakukan training  :\n" +
                    "-Training Network Operational Standard (Wajib dilakukan pada usia kerja 0 - 1 bulan)\n" +
                    "\n" +
                    "Nilai 2 : 100% FDO sudah training \n" +
                    "Nilai 1 : 50%-99% FDO mengikuti Training  \n" +
                    "Nilai 0 : < 50% FDO mengikuti training  ",
            0))


        nosAudit.add(NosAudit(
            "Frontdesk Officer\n" +
                    "(FDO)  ",
            "Standard Penampilan  ",
            "1.Wajah : \n" +
                    "Pria : ,wajah terlihat segar/tidak berminyak/tidak kusam, kumis/jenggot rapi (tdk dipanjangkan)\n" +
                    "\n" +
                    "Wanita :  Rias Wajah menggunakan make up (bedak,alis,eye shadow,blush on,lipstik sesuai standar)\n" +
                    "\n" +
                    "2.Rambut : \n" +
                    "Pria : Rambut Pendek Rapi, tidak menutupi mata dan tidak menyentuh kerah baju dan telinga\n" +
                    "\n" +
                    "Wanita :  Rambut rapi / tidak menutupi pandangan (panjang rambut lebih dari sebahu wajib dikuncir ponytail )\n" +
                    "\n" +
                    "3.Wewangian dan kebersihan:\n" +
                    "Aroma tubuh tidak mengganggu, tangan bersih, kuku tangan dan kuku kaki bersih. Untuk pria kuku pendek rapi.\n  ",
            0))


        nosAudit.add(NosAudit(
            "Frontdesk Officer\n" +
                    "(FDO)  ",
            "Standard Penampilan  ",
            "4. Seragam : \n" +
                    "Ketentuan :\n" +
                    "Mengenakan Polo Shirt warna merah sesuai standar AHM, dengan celana hitam.\n" +
                    "\n" +
                    "5.Atribut  : \n" +
                    "- Menggunakan sepatu warna hitam / Gelap (boleh sepatu kets)\n" +
                    "- ID Card (dengan kalung Honda Merah)\n" +
                    "- Pin One Heart di dada kiri\n" +
                    "- Jam Tangan Kulit / Karet / Canvas Warna Hitam / Coklat Tua / Gelap\n" +
                    "\n" +
                    "\n" +
                    "6. Aksesories :\n" +
                    "Pria : aksesoris tidak berlebihan (tidak lebih dari 5 titik)\n" +
                    "\n" +
                    "Menggunakan Masker polos atau tidak bermotif.\n" +
                    "-Masker kesehatan (warna bebas)\n" +
                    "-Masker kain disesuaikan dengan warna unsur seragam, yaitu abu-abu/putih/merah/hitam* *Kecuali Jum'at-Sabtu-Minggu\n" +
                    "Menggunakan face shield (jika berinteraksi dengan konsumen)  ",
            0))


        nosAudit.add(NosAudit(
            "Admin CRM H23\n  ",
            "Jumlah  ",
            "1 Admin CRM H23 independen = max 1.500 Unit Entry (Untuk Wing Dealer).\n" +
                    "\n" +
                    "Untuk Regular Dealer Admin CRM H23 adalah fungsi (boleh merangkap jabatan apapun) jika UE < 1500. Apabila UE ≥ 1500 wajib mempunyai 1 Admin CRM H23 Dedicated\n" +
                    "\n" +
                    "Independen = tidak boleh merangkap jabatan apapun  ",
            0))

        nosAudit.add(NosAudit(
            "Admin CRM H23\n  ",
            "Training  ",
            "\n" +
                    "Sudah melakukan training :\n" +
                    "- Basic Orientation Traning - BOT (Wajib dilakukan pada usia kerja 0 - 1 bulan oleh Kacab / Kabeng) sebelum training NOS\n" +
                    "\n" +
                    "Nilai 2 : 100% Admin CRM sudah training \n" +
                    "Nilai 1 : 50%-99% Admin CRM mengikuti Training  \n" +
                    "Nilai 0 : < 50% Admin CRM mengikuti training  ",
            0))

        nosAudit.add(NosAudit(
            "Admin CRM H23\n  ",
            "Training  ",
            "Sudah melakukan training (dengan masa kerja) :\n" +
                    "-Training Network Operational Standard (Wajib dilakukan pada usia kerja 0 - 1 bulan)\n" +
                    "\n" +
                    "Nilai 2 : 100% Admin CRM sudah training \n" +
                    "Nilai 1 : 50%-99% Admin CRM mengikuti training  \n" +
                    "Nilai 0 : < 50% Admin CRM mengikuti training  ",
            0))

        nosAudit.add(NosAudit(
            "Admin CRM H23\n  ",
            "Standard Penampilan  ",
            "1.Wajah : \n" +
                    "Pria : ,wajah terlihat segar/tidak berminyak/tidak kusam, kumis/jenggot rapi (tdk dipanjangkan)\n" +
                    "\n" +
                    "Wanita :  Rias Wajah menggunakan make up (bedak,alis,eye shadow,blush on,lipstik sesuai standar)\n" +
                    "\n" +
                    "2.Rambut : \n" +
                    "Pria : Rambut Pendek Rapi, tidak menutupi mata dan tidak menyentuh kerah baju dan telinga\n" +
                    "\n" +
                    "Wanita :  Rambut rapi / tidak menutupi pandangan (panjang rambut lebih dari sebahu wajib dikuncir ponytail )\n" +
                    "\n" +
                    "3.Wewangian dan kebersihan:\n" +
                    "Aroma tubuh tidak mengganggu, tangan bersih, kuku tangan dan kuku kaki bersih. Untuk pria kuku pendek rapi.\n  ",
            0))

        nosAudit.add(NosAudit(
            "Admin CRM H23\n  ",
            "Standard Penampilan  ",
            "4. Seragam : \n" +
                    "\n" +
                    "Ketentuan :\n" +
                    " Senin & Rabu : Seragam Honda berkerah merah,Celana / rok hitam \n" +
                    " Selasa & Kamis : Seragam Honda berkerah putih,Celana / rok hitam  \n" +
                    "Jumat : baju khas daerah / batik (batik Dealer), Celana / Rok Hitam\n" +
                    "Sabtu & Minggu : casual (berkerah) atau sesuai kebijakan masing2 dealer\n" +
                    "(kondisi Baju : Rapi, Bersih, Warna tidak kusam, tidak robek, dan logo Honda terlihat)\n" +
                    "* Sesuai dengan aturan seragam dari HC3 AHM\n" +
                    "\n" +
                    "5.Atribut  : \n" +
                    "- Senin - Jumat : Wanita menggunakan sepatu pantofel warna hitam heel minimum 5 cm kecuali wanita hamil\n" +
                    "- Senin - Jumat : Pria menggunakan sepatu pantofel warna hitam\n" +
                    "- Sabtu - Minggu atau saat berada di lapangan : Sepatu Kets  warna Netral (Putih, Hitam, Abu-abu, Merah Maroon, Biru Tua, Putih)\n" +
                    "- ID Card dengan Kalung Honda Merah\n" +
                    "- Pin One Heart di dada kiri\n" +
                    "- Jam Tangan Kulit / Karet / Canvas Warna Hitam / Coklat Tua / Gelap\n" +
                    "\n" +
                    "6. Aksesories :\n" +
                    "Pria : aksesoris tidak berlebihan (tidak lebih dari 5 titik)\n" +
                    " Wanita : aksesoris tidak berlebihan (tidak lebih dari 7 titik)\n" +
                    "\n" +
                    "Menggunakan Masker polos atau tidak bermotif.\n" +
                    "-Masker kesehatan (warna bebas)\n" +
                    "-Masker kain disesuaikan dengan warna unsur seragam, yaitu abu-abu/putih/merah/hitam* *Kecuali Jum'at-Sabtu-Minggu\n" +
                    "Menggunakan face shield (jika berinteraksi dengan konsumen)  ",
            0))



        nosAudit.add(NosAudit(
            "Claim Processor  ",
            "Jumlah  ",
            "AHASS memiliki minimal 1 orang Claim Processor yang sudah tersertifikasi --> minimum TTL 2\n  ",
            0))

        nosAudit.add(NosAudit(
            "Claim Processor  ",
            "Training  ",
            "Sudah melakukan training:\n" +
                    "TTL 2  ",
            0))



        nosAudit.add(NosAudit(
            "PIC Parts  ",
            " Jumlah ",
            "Untuk Wing Dealer , wajib memiliki PIC PARTS  dan  petugas  gudang parts yang  khusus. Tidak  boleh dirangkap oleh frontdesk , dengan jumlah sbb:\n" +
                    "1. PIC parts khusus ( minimum 1  : PIC parts  merangkap  gudang ) untuk unit entry s/d 1500  per bulan \n" +
                    "2. PIC parts khusus ( minimum 1 PIC parts + 1 petugas  gudang ) untuk unit entry   1500 - 5000 per bulan\n" +
                    "3. PIC parts khusus ( minimum 1 PIC parts + 2 petugas  gudang ) untuk unit entry di atas  5000 per bulan\n  ",
            0))


        nosAudit.add(NosAudit(
            "PIC Parts  ",
            " Training (PIC Parts) ",
            "Untuk PIC Parts  , sudah melakukan training  :\n" +
                    "-Customer Service For FLP Training (Wajib dilakukan pada usia kerja 0 - 3 bulan)\n" +
                    "Nilai 2 : 100%  (PIC Parts)  sudah training \n" +
                    "Nilai 1 : Belum 100%  ( PIC Parts) mengikuti Training  \n" +
                    "Nilai 0 : Belum ada  ( PIC Parts) mengikuti training  ",
            0))


        nosAudit.add(NosAudit(
            "PIC Parts  ",
            " Training (PIC Parts) ",
            "Untuk PIC Parts  , sudah melakukan training  :\n" +
                    "- Parts Management Knowledge \n" +
                    "Nilai 2 : 100%  (PIC Parts)  sudah training \n" +
                    "Nilai 1 : Belum 100%  ( PIC Parts) mengikuti Training  \n" +
                    "Nilai 0 : Belum ada  ( PIC Parts) mengikuti training  ",
            0))

        nosAudit.add(NosAudit(
            "PIC Parts  ",
            " Training (PIC Parts) ",
            "\n" +
                    "Sudah melakukan training :\n" +
                    "- Basic Orientation Traning - BOT (Wajib dilakukan pada usia kerja 0 - 1 bulan oleh Kacab / Kabeng) sebelum training NOS\n" +
                    "\n" +
                    "Nilai 2 : 100% PIC Part sudah training \n" +
                    "Nilai 1 : 50%-99% PIC Part mengikuti Training  \n" +
                    "Nilai 0 : < 50% PIC Part mengikuti training  ",
            0))


        nosAudit.add(NosAudit(
            "PIC Parts  ",
            " Training (PIC Parts) ",
            "Sudah melakukan training (dengan masa kerja) :\n" +
                    "-Training Network Operational Standard (Wajib dilakukan pada usia kerja 0 - 1 bulan)\n" +
                    "\n" +
                    "Nilai 2 : 100%  (PIC Parts)  sudah training \n" +
                    "Nilai 1 : Belum 100%  ( PIC Parts) mengikuti Training  \n" +
                    "Nilai 0 : Belum ada  ( PIC Parts) mengikuti training  ",
            0))


        nosAudit.add(NosAudit(
            "PIC Parts  ",
            "Standard Penampilan  ",
            "1.Wajah : \n" +
                    "Pria : ,wajah terlihat segar/tidak berminyak/tidak kusam, kumis/jenggot rapi (tdk dipanjangkan)\n" +
                    "\n" +
                    "Wanita :  Rias Wajah menggunakan make up (bedak,alis,eye shadow,blush on,lipstik sesuai standar)\n" +
                    "\n" +
                    "2.Rambut : \n" +
                    "Pria : Rambut Pendek Rapi, tidak menutupi mata dan tidak menyentuh kerah baju dan telinga\n" +
                    "\n" +
                    "Wanita :  Rambut rapi / tidak menutupi pandangan (panjang rambut lebih dari sebahu wajib dikuncir ponytail )\n" +
                    "\n" +
                    "3.Wewangian dan kebersihan:\n" +
                    "Aroma tubuh tidak mengganggu, tangan bersih, kuku tangan dan kuku kaki bersih. Untuk pria kuku pendek rapi.\n  ",
            0))


        nosAudit.add(NosAudit(
            "PIC Parts  ",
            "Standard Penampilan  ",
            "4. Seragam : \n" +
                    "\n" +
                    "Ketentuan :\n" +
                    " Senin & Rabu : Seragam Honda berkerah merah,Celana / rok hitam \n" +
                    " Selasa & Kamis : Seragam Honda berkerah putih,Celana / rok hitam  \n" +
                    "Jumat : baju khas daerah / batik (batik Dealer), Celana / Rok Hitam\n" +
                    "Sabtu & Minggu : casual (berkerah) atau sesuai kebijakan masing2 dealer\n" +
                    "(kondisi Baju : Rapi, Bersih, Warna tidak kusam, tidak robek, dan logo Honda terlihat)\n" +
                    "* Sesuai dengan aturan seragam dari HC3 AHM\n" +
                    "\n" +
                    "5.Atribut  : \n" +
                    "- Senin - Jumat : Wanita menggunakan sepatu pantofel warna hitam heel minimum 5 cm kecuali wanita hamil\n" +
                    "- Senin - Jumat : Pria menggunakan sepatu pantofel warna hitam\n" +
                    "- Sabtu - Minggu atau saat berada di lapangan : Sepatu Kets  warna Netral (Putih, Hitam, Abu-abu, Merah Maroon, Biru Tua, Putih)\n" +
                    "- ID Card dengan Kalung Honda Merah\n" +
                    "- Pin One Heart di dada kiri\n" +
                    "- Jam Tangan Kulit / Karet / Canvas Warna Hitam / Coklat Tua / Gelap\n" +
                    "\n" +
                    "6. Aksesories :\n" +
                    "Pria : aksesoris tidak berlebihan (tidak lebih dari 5 titik)\n" +
                    " Wanita : aksesoris tidak berlebihan (tidak lebih dari 7 titik)\n" +
                    "\n" +
                    "Menggunakan Masker polos atau tidak bermotif.\n" +
                    "-Masker kesehatan (warna bebas)\n" +
                    "-Masker kain disesuaikan dengan warna unsur seragam, yaitu abu-abu/putih/merah/hitam* *Kecuali Jum'at-Sabtu-Minggu\n" +
                    "Menggunakan face shield (jika berinteraksi dengan konsumen)  ",
            0))


        nosAudit.add(NosAudit(
            "PIC HGA & Apparel  ",
            "Jumlah   ",
            "Minimal ada 1 orang PIC HGA & Apparel atau bisa dirangkap oleh PIC Parts ",
            0))
        nosAudit.add(NosAudit(
            "PIC HGA & Apparel  ",
            " Training (PIC Parts)  ",
            "Sudah melakukan training (dengan masa kerja) :\n" +
                    "- Customer Service For FLP Training (Wajib dilakukan pada usia kerja 0 - 3 bulan jika dirangkap oleh PIC Parts)\n" +
                    "\n" +
                    "Nilai 2 : 100%  (PIC HGA & Apparel)  sudah training \n" +
                    "Nilai 1 : Belum 100%  ( PIC HGA & Apparel) mengikuti Training  \n" +
                    "Nilai 0 : Belum ada  ( PIC HGA & Apparel) mengikuti training ",
            0))
        nosAudit.add(NosAudit(
            "PIC HGA & Apparel  ",
            "Training (PIC Parts)   ",
            "Sudah melakukan training (dengan masa kerja) :\n" +
                    "- Parts Management Knowledge \n" +
                    "Nilai 2 : 100%  (PIC HGA & Apparel)  sudah training \n" +
                    "Nilai 1 : Belum 100%  ( PIC HGA & Apparel) mengikuti Training  \n" +
                    "Nilai 0 : Belum ada  ( PIC HGA & Apparel) mengikuti training ",
            0))
        nosAudit.add(NosAudit(
            "PIC HGA & Apparel  ",
            "Training (PIC Parts)   ",
            "\n" +
                    "Sudah melakukan training :\n" +
                    "- Basic Orientation Traning - BOT (Wajib dilakukan pada usia kerja 0 - 1 bulan oleh Kacab / Kabeng) sebelum training NOS\n" +
                    "\n" +
                    "Nilai 2 : 100% (PIC HGA & Apparel) sudah training \n" +
                    "Nilai 1 : 50%-99% (PIC HGA & Apparel) mengikuti Training  \n" +
                    "Nilai 0 : < 50% (PIC HGA & Apparel) mengikuti training ",
            0))
        nosAudit.add(NosAudit(
            "PIC HGA & Apparel  ",
            "Training (PIC Parts)   ",
            "Sudah melakukan training (dengan masa kerja) :\n" +
                    "-Training Network Operational Standard (Wajib dilakukan pada usia kerja 0 - 1 bulan)\n" +
                    "\n" +
                    "Nilai 2 : 100%  (PIC HGA & Apparel)  sudah training \n" +
                    "Nilai 1 : Belum 100%  ( PIC HGA & Apparel) mengikuti Training  \n" +
                    "Nilai 0 : Belum ada  ( PIC HGA & Apparel) mengikuti training ",
            0))
        nosAudit.add(NosAudit(
            "PIC HGA & Apparel  ",
            "Standard Penampilan   ",
            "1.Wajah : \n" +
                    "Pria : ,wajah terlihat segar/tidak berminyak/tidak kusam, kumis/jenggot rapi (tdk dipanjangkan)\n" +
                    "\n" +
                    "Wanita :  Rias Wajah menggunakan make up (bedak,alis,eye shadow,blush on,lipstik sesuai standar)\n" +
                    "\n" +
                    "2.Rambut : \n" +
                    "Pria : Rambut Pendek Rapi, tidak menutupi mata dan tidak menyentuh kerah baju dan telinga\n" +
                    "\n" +
                    "Wanita :  Rambut rapi / tidak menutupi pandangan (panjang rambut lebih dari sebahu wajib dikuncir ponytail )\n" +
                    "\n" +
                    "3.Wewangian dan kebersihan:\n" +
                    "Aroma tubuh tidak mengganggu, tangan bersih, kuku tangan dan kuku kaki bersih. Untuk pria kuku pendek rapi.\n ",
            0))
        nosAudit.add(NosAudit(
            "PIC HGA & Apparel  ",
            "Standard Penampilan   ",
            "4. Seragam : \n" +
                    "\n" +
                    "Ketentuan :\n" +
                    " Senin & Rabu : Seragam Honda berkerah merah,Celana / rok hitam \n" +
                    " Selasa & Kamis : Seragam Honda berkerah putih,Celana / rok hitam  \n" +
                    "Jumat : baju khas daerah / batik (batik Dealer), Celana / Rok Hitam\n" +
                    "Sabtu & Minggu : casual (berkerah) atau sesuai kebijakan masing2 dealer\n" +
                    "(kondisi Baju : Rapi, Bersih, Warna tidak kusam, tidak robek, dan logo Honda terlihat)\n" +
                    "* Sesuai dengan aturan seragam dari HC3 AHM\n" +
                    "\n" +
                    "5.Atribut  : \n" +
                    "- Senin - Jumat : Wanita menggunakan sepatu pantofel warna hitam heel minimum 5 cm kecuali wanita hamil\n" +
                    "- Senin - Jumat : Pria menggunakan sepatu pantofel warna hitam\n" +
                    "- Sabtu - Minggu atau saat berada di lapangan : Sepatu Kets  warna Netral (Putih, Hitam, Abu-abu, Merah Maroon, Biru Tua, Putih)\n" +
                    "- ID Card dengan Kalung Honda Merah\n" +
                    "- Pin One Heart di dada kiri\n" +
                    "- Jam Tangan Kulit / Karet / Canvas Warna Hitam / Coklat Tua / Gelap\n" +
                    "\n" +
                    "6. Aksesories :\n" +
                    "Pria : aksesoris tidak berlebihan (tidak lebih dari 5 titik)\n" +
                    " Wanita : aksesoris tidak berlebihan (tidak lebih dari 7 titik)\n" +
                    "\n" +
                    "Menggunakan Masker polos atau tidak bermotif.\n" +
                    "-Masker kesehatan (warna bebas)\n" +
                    "-Masker kain disesuaikan dengan warna unsur seragam, yaitu abu-abu/putih/merah/hitam* *Kecuali Jum'at-Sabtu-Minggu\n" +
                    "Menggunakan face shield (jika berinteraksi dengan konsumen) ",
            0))



        return nosAudit
    }

    fun generateDataH23Process(): List<NosAudit> {

        val nosAudit = ArrayList<NosAudit>()

        nosAudit.add(NosAudit(
            "Front Desk Officer  ",
            "Layanan booking servis (Wing Dealer) |  Data booking servis ",
            "Kebersihan Interior :\n" +
                    "-Lantai dan Dinding Bengkel\n" +
                    "      - Bebas dari debu dan minyak pada saat tidak digunakan\n" +
                    "      - Bersih dari sarang binatang \n" +
                    "*Dibersihkan dengan sapu pada saat Jam istirahat mekanik dari debu/ kotoran sisan motor dari motor yang di servis\n" +
                    "*Dibersihkan setiap hari pada saat bengkel selesai beroperasi dengan menggunakan deterjen dan kain pel \n" +
                    "*Menchecklist Form kebersihan dan di dokumentasi (sebagai tools monitoring dan controllong)\n" +
                    "- Piping System \n" +
                    "      - Instalasi pipa harus selalu dalam kondisi bersih  dan  tidak ada sarang binatang\n" +
                    "- Meja Sa dan Front Desk \n" +
                    "    - bersih dan tidak terkelupas \n" +
                    "-Ruang Tunggu \n" +
                    "    -Bebas dari sampah AMDK dan piring bekas Snack konsumen \n" +
                    "*Dibersihkan apabila ada bekas AMDK yang tidak di buang oleh konsumen \n" +
                    "-Toilet\n" +
                    "    -Dalam keadaan bersih dan nyaman digunakan oleh Konsumen \n" +
                    "*Dibersihkan setiap hari dan diulang minimum pada pagi, siang, dan sore hari "
            , 0))


        nosAudit.add(NosAudit(
            "Front Desk Officer  ",
            "Layanan booking servis (Wing Dealer) |  Data booking servis ",
            "- Mengkonfirmasi kembali data konsumen "
            , 0))


        nosAudit.add(NosAudit(
            "Front Desk Officer  ",
            "Layanan booking servis (Wing Dealer) |  Data booking servis ",
            "- Mencatat data konsumen ke form booking service "
            , 0))


        nosAudit.add(NosAudit(
            "Front Desk Officer  ",
            "Layanan booking servis (Wing Dealer) |  Data booking servis ",
            "Mencatat data booking service ke sistem DMS "
            , 0))


        nosAudit.add(NosAudit(
            "Front Desk Officer  ",
            "Input Data Konsumen dan motor di sistem   ",
            "- Memastikan seluruh field terisi sesuai dengan informasi data konsumen, data motor, dan data pekerjaan pada form SA\n" +
                    "- Melakukan input semua informasi dari form SA ke dalam sistem komputerisasi AHASS "
            , 0))

        nosAudit.add(NosAudit(
            "Front Desk Officer  ",
            "Input Data Konsumen dan motor di sistem   ",
            "Melakukan update PKB di sistem  bila ada pekerjaan tambahan "
            , 0))


        nosAudit.add(NosAudit(
            "Front Desk Officer  ",
            "Untuk konsumen premium | Perubahan data  ",
            "Menyusun data rekap perubahan data customer tracking motor premium "
            , 0))


        nosAudit.add(NosAudit(
            "Admin CRM H23  ",
            "Layanan booking servis | Reminder booking servis ",
            "- Melakukan telepon Reminder H-1\n" +
                    "-Mengingatkan konsumen untuk memakai masker saat mengunjungi AHASS\n" +
                    "*Jika telepon tidak diangkat oleh konsumen, Admin CRM H23 wajib melakukan SMS/WA "
            , 0))

        nosAudit.add(NosAudit(
            "Admin CRM H23  ",
            "Layanan booking servis | Reminder booking servis ",
            "- Melakukan SMS / WA reminder di pagi Hari H\n" +
                    "- -Mengingatkan konsumen untuk memakai masker saat mengunjungi AHASS "
            , 0))


        nosAudit.add(NosAudit(
            "Admin CRM H23  ",
            "Konsumen menerima service reminder | Reminder servis ",
            "SMS / WA reminder (KPB 2,3,4 + Reguar Service )\n" +
                    "- SMS dikirimkan H-30 sebelum servis dan menginfokan booking service (waktu sms dapat disesuaikan)\n" +
                    "- List sms yang dikirimkan "
            , 0))


        nosAudit.add(NosAudit(
            "Admin CRM H23  ",
            "Konsumen menerima service reminder | Reminder servis ",
            "Call / WA reminder H-14 ((KPB 2,3,4 + Reguar Service  ) sebelum jatuh tempo untuk konsumen yang belum datang servis setelah dilakukan sms reminder\n" +
                    "- Menyesuaikan waktu follow up dengan pekerjaan konsumen (golden time),\n" +
                    "- Menawarkan fasilitas AHASS (seperti booking servis, servis kunjung, dll),\n" +
                    "- Adanya list daftar konsumen yang difollow up sebagai evidence\n "
            , 0))



        nosAudit.add(NosAudit(
            "Admin CRM H23  ",
            "Konsumen menerima follow up setelah servis | List konsumen yang di-follow up  ",
            "Menyiapkan data konsumen yg ingin di follow up,  paling lambat H+7 usai servis \n" +
                    "Menginformasikan fasilitas booking servis dan servis kunjung (jika tersedia armada service kunjung)\n" +
                    "\n" +
                    "Konsumen premium : semua tipe pekerjaan\n" +
                    "Konsumen reguler : HR, Claim, JR, LR "
            , 0))


        nosAudit.add(NosAudit(
            "Security / SA  ",
            "Ketika sampai di AHASS",
            "Menyambut konsumen, Salam Satu Hati "
            , 0))

        nosAudit.add(NosAudit(
            "Security / SA  ",
            "Ketika sampai di AHASS  ",
            "Menanyakan kebutuhan konsumen datang ke dealer (ke showroom/AHASS) "
            , 0))


        nosAudit.add(NosAudit(
            "Security / SA  ",
            "Ketika sampai di AHASS | Mengarahkan ke area parkir showroom atau AHASS",
            "Jika konsumen hanya akan membeli spare part, Security mengarahkan ke part counter "
            , 0))


        nosAudit.add(NosAudit(
            "Security / SA  ",
            "Ketika sampai di AHASS | Mengarahkan ke area parkir showroom atau AHASS",
            "Bila konsumen akan service maka\n" +
                    "- Memberikan nomor antrian AHASS \n" +
                    "- Mengarahkan ke parkir in "
            , 0))


        nosAudit.add(NosAudit(
            "Security / SA  ",
            "Ketika sampai di AHASS | Mengarahkan ke area parkir showroom atau AHASS   ",
            "-Menunjukkan pintu masuk AHASS/ SA desk "
            , 0))


        nosAudit.add(NosAudit(
            "Security / SA  ",
            "Ketika sampai di AHASS | Memeriksa suhu tubuh konsumen ",
            "-Melakukan pengecekan suhu tubuh konsumen yang memasuki area dealer\n" +
                    "- Mengkomunikasikan dengan sopan apabila suhu tubuh konsumen diatas 37.5, maka tidak diperbolehkan memasuki area dealer "
            , 0))


        nosAudit.add(NosAudit(
            "Security / SA  ",
            "Ketika sampai di AHASS | Memeriksa suhu tubuh konsumen ",
            "Apabila suhu tubuh konsumen diatas 37.5\n" +
                    "-Meminta konsumen untuk menunggu 5 menit, kemudian melakukan pemeriksaan suhu kembali\n" +
                    "- Apabila setelah diukur masih diatas 37.5, mengucapkan kalimat \"Mohon maaf Bapak/Ibu, dikarenakan suhu tubuh Bapak/Ibu diatas 37,5 maka tidak diperkenankan untuk memasuki area dealer. SIlahkan Bapak/Ibu dapat mengunjungi fasilitas kesehatan terdekat. Jangan khawatir, kebutuhan Bapak/Ibu Whataspp (untuk pembelian) atau Service Kunjung (untuk service)\"\n" +
                    "- Menanyakan nama dan nomor telepon konsumen "
            , 0))


        nosAudit.add(NosAudit(
            "Security / SA  ",
            "Ketika sampai di AHASS  | Memastikan jarak 1m",
            "Memastikan konsumen berdiri pada garis-garis yang sudah ditentukan, untuk membatasi jarak 1m antar konsumen "
            , 0))


        nosAudit.add(NosAudit(
            "Security / SA  ",
            "Ketika sampai di AHASS | Preventif ",
            "ketika ada konsumen / karyawan dealer mengalami kondisi emergency (tidak sadarkan diri, tergeletak, dan lain-lain), langsung menghubungi RS Rujukan, RS atau fasilitas kesehatan terdekat. Tetap menjaga kondusifitas, serta melakukan protokol kesehatan yang diperlukan seperti sterilisasi ruangan atau area, disinfektan ruangan atau langkah preventif lainnya "
            , 0))

        nosAudit.add(NosAudit(
            "Security / SA  ",
            "Meninggalkan AHASS | Mengingatkan konsumen memakai helm dengan benar ",
            "Mengingatkan untuk memakai helm/memastikan helm sudah \"klik !\" /safety riding "
            , 0))

        nosAudit.add(NosAudit(
            "Security / SA  ",
            "Meninggalkan AHASS | Memberi salam dan berterimakasih kepada konsumen   ",
            "-Mengucapkan terima kasih\n" +
                    "-Salam Satu Hati\n" +
                    "-Mengingatkan cari Aman dalam berkendara "
            , 0))


        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konsumen bertemu dengan Service Advisor | Penerimaan konsumen  ",
            " 1. Mengucapkan salam 1 hati\n" +
                    "2. Meminta no antrian servis yang diperoleh konsumen dari security\n" +
                    "3. Menawarkan bantuan kepada konsumen\n" +
                    "4. Bila SA sibuk maka dapat digantikan oleh FI atau Kamek"
            , 0))

        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konsumen bertemu dengan Service Advisor | Penerimaan booking servis  ",
            "Mengkonfirmasi konsumen booking servis atau tidak, jika konsumen booking servis mendapat antrian khusus "
            , 0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konsumen bertemu dengan Service Advisor | Penerimaan booking servis  ",
            "memastikan ketersediaan ruang tunggu di AHASS. Apabila kondisi ruang tunggu sudah padat , maka SA dapat menyarankan menggunakan booking service / motor ditinggal\n "
            , 0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konsumen bertemu dengan Service Advisor | Pencatatan data konsumen dan motor ",
            "Mengkonfirmasi apakah konsumen berasal dari Dealer yang sama dengan AHASS (Own Dealer) "
            , 0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konsumen bertemu dengan Service Advisor | Pencatatan data konsumen dan motor ",
            "Jika konsumen selected model, menanyakan informasi apakah ada perubahan data pemilik "
            , 0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konsumen bertemu dengan Service Advisor | Pencatatan data konsumen dan motor   ",
            "Melakukan update ke sistem DMS jika ada perubahan data pemilik  "
            , 0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konsumen bertemu dengan Service Advisor | Pencatatan data konsumen dan motor  ",
            "Meminjam buku servis konsumen dan mengkonfirmasi servis sebelumnya "
            , 0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konsumen bertemu dengan Service Advisor | Pencatatan data konsumen dan motor  ",
            "Mencatat data konsumen dan motor\n" +
                    "Melakukan validasi nomor telepon dengan cara miscall (Jika antrian penuh proses validasi dapat dibantu oleh admin CRM H2/Front Desk/kasir) "
            , 0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konsumen bertemu dengan Service Advisor | Pencatatan data konsumen dan motor  ",
            "Mencatat keluhan konsumen dan mengkonfirmasi keluhan konsumen "
            , 0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konsumen bertemu dengan Service Advisor | Pencatatan data konsumen dan motor  ",
            "Memeriksa fisik motor (body, cover, safety item : lampu, ban, rem, rantai) "
            , 0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konsumen bertemu dengan Service Advisor | Pencatatan data konsumen dan motor  ",
            "Menjelaskan kondisi sepeda motor dengan menginformasikan service yg dibutuhkan dan merekomendasikan part yang harus diganti, serta menginformasikan garansi unit (jika memenuhi syarat dan ketentuan claim yang berlaku). "
            , 0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konsumen bertemu dengan Service Advisor | Pencatatan data konsumen dan motor  ",
            "Memeriksa apakah sepeda motor konsumen termasuk market treatment "
            , 0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konsumen bertemu dengan Service Advisor | Pencatatan data konsumen dan motor  ",
            "Menawarkan aksesoris motor "
            , 0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konsumen bertemu dengan Service Advisor | Pencatatan data konsumen dan motor  ",
            "Mengkonfirmasi kepada konsumen (kecuali battery lithium) :\n" +
                    "1. Part bekas adalah hak konsumen\n" +
                    "2. Apakah part bekas mau dibawa konsumen atau ditinggal di AHASS "
            , 0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konsumen bertemu dengan Service Advisor | Pencatatan data konsumen dan motor  ",
            "Menuliskan estimasi waktu  sepeda motor mulai dikerjakan perawatan/perbaikan di form SA dan memberitahukan secara lisan "
            , 0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konsumen bertemu dengan Service Advisor | Pencatatan data konsumen dan motor  ",
            "Menuliskan estimasi waktu  sepeda motor selesai dikerjakan perawatan/perbaikan di form SA dan memberitahukan secara lisan "
            , 0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konsumen bertemu dengan Service Advisor | Pencatatan data konsumen dan motor  ",
            "Menulis kontak konsumen yang bisa dihubungi untuk mengkonfirmasi tambahan pekerjaan/pergantian spare part "
            , 0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konsumen bertemu dengan Service Advisor | (Jika membutuhkan part tambahan) ",
            "-Mengecek ketersediaan part di part counter\n" +
                    "-Membuat part order terkait part tambahan "
            , 0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konsumen bertemu dengan Service Advisor | (Jika membutuhkan part tambahan) ",
            "mengecek ketersediaan part di sistem DMS "
            , 0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konsumen bertemu dengan Service Advisor | Menawarkan kepada konsumen premium  ",
            "Mengarahkan konsumen jika akan melakukan pergantian battery lib,smart key assy, atau key set  "
            , 0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konfirmasi terakhir oleh SA |  Penjelasan setelah pekerjaan\n" +
                    "1. Dilakukan di area parkir out,\n" +
                    "apabila tidak dilakukan di parkir out nilai = 0\n" +
                    "2. Dilakukan sebelum konsumen membayar, apabila dilakukan setelah konsumen membayar ke kasir nilai = 0\n" +
                    "3. Dilakukan oleh SA atau SA Back Up, apabila dilakukan oleh yang lain maka nilai = 0  ",
            "Mengambil form PKB, buku servis, kunci motor yang telah selesai dilakukan final inspection di meja front desk "
            , 0))

        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konfirmasi terakhir oleh SA |  Penjelasan setelah pekerjaan\n" +
                    "1. Dilakukan di area parkir out,\n" +
                    "apabila tidak dilakukan di parkir out nilai = 0\n" +
                    "2. Dilakukan sebelum konsumen membayar, apabila dilakukan setelah konsumen membayar ke kasir nilai = 0\n" +
                    "3. Dilakukan oleh SA atau SA Back Up, apabila dilakukan oleh yang lain maka nilai = 0  ",
            "Memeriksa buku servis apakah semua pekerjaan servis sudahdiberi tanda cek (v) oleh mekanik "
            , 0))

        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konfirmasi terakhir oleh SA |  Penjelasan setelah pekerjaan\n" +
                    "1. Dilakukan di area parkir out,\n" +
                    "apabila tidak dilakukan di parkir out nilai = 0\n" +
                    "2. Dilakukan sebelum konsumen membayar, apabila dilakukan setelah konsumen membayar ke kasir nilai = 0\n" +
                    "3. Dilakukan oleh SA atau SA Back Up, apabila dilakukan oleh yang lain maka nilai = 0  ",
            "Memangggil konsumen yang sedang menunggu di ruang tunggu, mengajak konsumen ke sepeda motor konsumen di parking out "
            , 0))

        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konfirmasi terakhir oleh SA |  Penjelasan setelah pekerjaan\n" +
                    "1. Dilakukan di area parkir out,\n" +
                    "apabila tidak dilakukan di parkir out nilai = 0\n" +
                    "2. Dilakukan sebelum konsumen membayar, apabila dilakukan setelah konsumen membayar ke kasir nilai = 0\n" +
                    "3. Dilakukan oleh SA atau SA Back Up, apabila dilakukan oleh yang lain maka nilai = 0  ",
            "Menjelaskan pekerjaan service yang sudah dilakukan dan part yang diganti, bila perlu diperagakan  "
            , 0))

        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konfirmasi terakhir oleh SA |  Penjelasan setelah pekerjaan\n" +
                    "1. Dilakukan di area parkir out,\n" +
                    "apabila tidak dilakukan di parkir out nilai = 0\n" +
                    "2. Dilakukan sebelum konsumen membayar, apabila dilakukan setelah konsumen membayar ke kasir nilai = 0\n" +
                    "3. Dilakukan oleh SA atau SA Back Up, apabila dilakukan oleh yang lain maka nilai = 0  ",
            "Memberitahukan garansi service "
            , 0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konfirmasi terakhir oleh SA |  Penjelasan setelah pekerjaan\n" +
                    "1. Dilakukan di area parkir out,\n" +
                    "apabila tidak dilakukan di parkir out nilai = 0\n" +
                    "2. Dilakukan sebelum konsumen membayar, apabila dilakukan setelah konsumen membayar ke kasir nilai = 0\n" +
                    "3. Dilakukan oleh SA atau SA Back Up, apabila dilakukan oleh yang lain maka nilai = 0  ",
            "Memberitahukan jadwal service berikutnya  "
            , 0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konfirmasi terakhir oleh SA |  Penjelasan setelah pekerjaan\n" +
                    "1. Dilakukan di area parkir out,\n" +
                    "apabila tidak dilakukan di parkir out nilai = 0\n" +
                    "2. Dilakukan sebelum konsumen membayar, apabila dilakukan setelah konsumen membayar ke kasir nilai = 0\n" +
                    "3. Dilakukan oleh SA atau SA Back Up, apabila dilakukan oleh yang lain maka nilai = 0  ",
            "Menginformasikan service atau pergantian part yang pending untuk segera dilakukan "
            , 0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konfirmasi terakhir oleh SA |  Penjelasan setelah pekerjaan\n" +
                    "1. Dilakukan di area parkir out,\n" +
                    "apabila tidak dilakukan di parkir out nilai = 0\n" +
                    "2. Dilakukan sebelum konsumen membayar, apabila dilakukan setelah konsumen membayar ke kasir nilai = 0\n" +
                    "3. Dilakukan oleh SA atau SA Back Up, apabila dilakukan oleh yang lain maka nilai = 0  ",
            "Menjelaskan garansi tambahan untuk kasus tertentu (jika ada) "
            , 0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konfirmasi terakhir oleh SA |  Penjelasan setelah pekerjaan\n" +
                    "1. Dilakukan di area parkir out,\n" +
                    "apabila tidak dilakukan di parkir out nilai = 0\n" +
                    "2. Dilakukan sebelum konsumen membayar, apabila dilakukan setelah konsumen membayar ke kasir nilai = 0\n" +
                    "3. Dilakukan oleh SA atau SA Back Up, apabila dilakukan oleh yang lain maka nilai = 0  ",
            "Menunjukkan dan menyerahkan part yang telah diganti kepada konsumen atau dengan persetujuan konsumen untuk membuang parts tersebut (kecuali battery EV) "
            , 0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konfirmasi terakhir oleh SA |  Penjelasan setelah pekerjaan\n" +
                    "1. Dilakukan di area parkir out,\n" +
                    "apabila tidak dilakukan di parkir out nilai = 0\n" +
                    "2. Dilakukan sebelum konsumen membayar, apabila dilakukan setelah konsumen membayar ke kasir nilai = 0\n" +
                    "3. Dilakukan oleh SA atau SA Back Up, apabila dilakukan oleh yang lain maka nilai = 0  ",
            "Menginformasikan kepada konsumen pentingnya perawatan berkala di AHASS dan garansi unit SMH (selama unit masih berada di rentang waktu garansi) "
            , 0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konfirmasi terakhir oleh SA |  Penjelasan setelah pekerjaan\n" +
                    "1. Dilakukan di area parkir out,\n" +
                    "apabila tidak dilakukan di parkir out nilai = 0\n" +
                    "2. Dilakukan sebelum konsumen membayar, apabila dilakukan setelah konsumen membayar ke kasir nilai = 0\n" +
                    "3. Dilakukan oleh SA atau SA Back Up, apabila dilakukan oleh yang lain maka nilai = 0  ",
            "Menginformasikan kepada konsumen Contact Person yang dapat dihubungi jika ada hal yang ingin ditanyakan "
            , 0))
        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konfirmasi terakhir oleh SA |  Penjelasan setelah pekerjaan\n" +
                    "1. Dilakukan di area parkir out,\n" +
                    "apabila tidak dilakukan di parkir out nilai = 0\n" +
                    "2. Dilakukan sebelum konsumen membayar, apabila dilakukan setelah konsumen membayar ke kasir nilai = 0\n" +
                    "3. Dilakukan oleh SA atau SA Back Up, apabila dilakukan oleh yang lain maka nilai = 0  ",
            "Meminta persetujuan konsumen dengan bukti tanda tangan di form SA atau PKB (jika ada tambahan) "
            , 0))

        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konfirmasi terakhir oleh SA |  Penjelasan setelah pekerjaan\n" +
                    "1. Dilakukan di area parkir out,\n" +
                    "apabila tidak dilakukan di parkir out nilai = 0\n" +
                    "2. Dilakukan sebelum konsumen membayar, apabila dilakukan setelah konsumen membayar ke kasir nilai = 0\n" +
                    "3. Dilakukan oleh SA atau SA Back Up, apabila dilakukan oleh yang lain maka nilai = 0  ",
            "Mengucapkan terima kasih dan mengarahkan konsumen ke kasir.  Memastikan buku servis sudah disimpan di kompartemen dokumen motor. "
            , 0))

        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konfirmasi terakhir oleh SA |  Penjelasan setelah pekerjaan\n" +
                    "1. Dilakukan di area parkir out,\n" +
                    "apabila tidak dilakukan di parkir out nilai = 0\n" +
                    "2. Dilakukan sebelum konsumen membayar, apabila dilakukan setelah konsumen membayar ke kasir nilai = 0\n" +
                    "3. Dilakukan oleh SA atau SA Back Up, apabila dilakukan oleh yang lain maka nilai = 0  ",
            "membersihkan hand grip, jok, spion motor di depan konsumen pada saat proses penyerahan\n "
            , 0))

        nosAudit.add(NosAudit(
            "Service Advisor  ",
            "Konsumen melakukan pembayaran | Mengkonfirmasi gratis semprot helm kepada konsumen premium ",
            "Mengkonfirmasi lagi penyemprotan helmet deodorizer/anti bacterial/ pembersih kaca helm secara gratis untuk customer premium "
            , 0))

        nosAudit.add(NosAudit(
            "Mechanic  ",
            "Konsumen menunggu pekerjaan | Melakukan pekerjaan sesuai perintah kerja  ",
            "Melakukan pekerjaan sesuai perintah kerja "
            , 0))

        nosAudit.add(NosAudit(
            "Mechanic  ",
            "Konsumen menunggu pekerjaan | Melakukan pekerjaan sesuai perintah kerja  ",
            "Memasang cover jok dan grip "
            , 0))

        nosAudit.add(NosAudit(
            "Mechanic  ",
            "Konsumen menunggu pekerjaan | Melakukan pekerjaan sesuai perintah kerja  ",
            "Memasang papan PKB di plat nomer belakang sepeda motor yang diservis/diperbaiki "
            , 0))

        nosAudit.add(NosAudit(
            "Mechanic  ",
            "Konsumen menunggu pekerjaan | Melakukan pekerjaan sesuai perintah kerja  ",
            "Membuat tanda cek (v) di pekerjaan yang telah dilakukan di buku servis konsumen terutama untuk pekerjaan KPB 1 - 4  "
            , 0))
        nosAudit.add(NosAudit(
            "Mechanic  ",
            "Konsumen menunggu pekerjaan | Melakukan pekerjaan sesuai perintah kerja  ",
            "Melakukan prosedur kebersihan diri, area kerja, sepeda motor sebelum dan sesudah melakukan pekerjaan perawatan sepeda motor "
            , 0))
        nosAudit.add(NosAudit(
            "Mechanic  ",
            "Konsumen menunggu pekerjaan | Melakukan pekerjaan sesuai perintah kerja  ",
            "Apabila tidak ada fasilitas cuci motor,mechanic wajib melakukan wiping terhadap sepeda motor (kecuali kondisi sepeda motor tidak memungkinkan) "
            , 0))

        nosAudit.add(NosAudit(
            "Mechanic  ",
            "Konsumen menunggu pekerjaan | Melakukan pekerjaan sesuai perintah kerja  ",
            "Untuk pekerjaan perawatan sepeda motor premium mengikuti juklak yang telah ditentukan oleh PT Astra Honda Motor "
            , 0))

        nosAudit.add(NosAudit(
            "Mechanic  ",
            "Konsumen menunggu pekerjaan | Konfirmasi tambahan pekerjaan atau pergantian spare part  ",
            "Menginformasikan SA jika ada tambahan pekerjaan atau pergantian spare part "
            , 0))

        nosAudit.add(NosAudit(
            "Mechanic  ",
            "Konsumen menunggu pekerjaan | Setelah motor selesai dirawat di pit kerja  ",
            "Mekanik membawa sepeda motor, form pkb, buku servis, dan spare part bekas  (yang telah diberi wadah pembungkus, jika memang konsumen minta part bekas) ke area pit final inspection "
            , 0))


        nosAudit.add(NosAudit(
            "Chief of Mechanic  ",
            "Konsumen menunggu pekerjaan | Monitor pekerjaan mekanik ",
            "- Membantu mekanik menyelesaikan masalah teknis\n" +
                    "- Menjaga produktivitas dengan melakukan update papan kontrol mekanik\n" +
                    "\n" +
                    "Cek apakah kepala mekanik keliling pit/bantu mekanik di pit/papan kontrol mekanik terupdate, jika ya nilai = 2, jika tidak nilai = 0 "
            , 0))

        nosAudit.add(NosAudit(
            "Chief of Mechanic  ",
            "Konsumen menunggu pekerjaan | Monitor pekerjaan mekanik ",
            "- Mengontrol kebersihan area kerja mekanik\n" +
                    "- Menjaga produktivitas bisa dicek dengan kelengkapan tools\n" +
                    "- Checklist kebersihan dan kelengkapan tools update\n" +
                    "\n" +
                    "Cek apakah area kerja mekanik bersih, tool lengkap, checklist terupdate. Jika ya nilai = 2, jika tidak, nilai = 0 "
            , 0))

        nosAudit.add(NosAudit(
            "Chief of Mechanic  ",
            "Konsumen menunggu pekerjaan | Monitor area ruang tunggu konsumen ",
            "- Mengontrol kebersihan area ruang tunggu konsumen\n" +
                    "- Mengontrol berfungsinya semua fasilitas yang ada di ruang tunggu konsumen (TV, WIFI, Charger HP, HRT, sirkulasi udara)\n" +
                    "- Mengontrol ketersediaan AMDK, Kopi tea, kursi ruang tunggu, koran, dan tabloid bertema otomotif "
            , 0))

        nosAudit.add(NosAudit(
            "Claim processor  ",
            "Konsumen menunggu pekerjaan | Melakukan prosedur klaim jika ada dalam 2x24 jam  ",
            "- Melakukan prosedur klaim jika ada klaim terkait produk dalam 2x24 jam (Selama parts claim tersedia di AHASS)\n" +
                    "\n" +
                    "Dicrosscheck dengan form Claim yang sudah dilaksanakan "
            , 0))

        nosAudit.add(NosAudit(
            "Final Inspector  ",
            "Konsumen menunggu pekerjaan | Pemeriksaan akhir  ",
            "Melakukan final inspection sesuai form work order dan keluhan konsumen sesuai IK Final inspection\n" +
                    "\n" +
                    "Apabila tidak sesuai, final inspector akan mengembalikan sepeda motor ke mekanik yang melakukan perawatan\n" +
                    "Apabila sesuai, final inspector akan meletakkan sepeda motor yang sudah di final inspection ke area parking out "
            , 0))

        nosAudit.add(NosAudit(
            "Final Inspector  ",
            "Konsumen menunggu pekerjaan | Pemeriksaan akhir  ",
            "Meletakkan tag servis, form PKB, dan kunci motor di meja front desk "
            , 0))

        nosAudit.add(NosAudit(
            "Final Inspector  ",
            "Konsumen menunggu pekerjaan | Pemeriksaan akhir  ",
            "Menginformasikan motor yang telah selesai ke service advisor "
            , 0))

        nosAudit.add(NosAudit(
            "Cashier  ",
            "Konsumen melakukan pembayaran | Menyambut konsumen  ",
            "- Salam satu Hati\n" +
                    "- Salam Cuaca "
            , 0))

        nosAudit.add(NosAudit(
            "Cashier  ",
            "Konsumen melakukan pembayaran | Validasi sepeda motor  ",
            "Menanyakan form SA atau STNK untuk validasi sepeda motor "
            , 0))

        nosAudit.add(NosAudit(
            "Cashier  ",
            "Konsumen melakukan pembayaran | Validasi sepeda motor  ",
            "Menjelaskan benefit (Voucher senilai Rp 250ribu subsidi dari AHM) perubahan data customer untuk konsumen selected model "
            , 0))

        nosAudit.add(NosAudit(
            "Cashier  ",
            "Konsumen melakukan pembayaran | Konfirmasi pembayaran  ",
            "-Mengonfirmasi ke konsumen jenis biaya servis dan penggantian part (jika ada) disertai informasi rincian dan  total uang yang akan dibayar\n" +
                    "- Menawarkan pembayaran ke konsumen dengan cash atau kartu\n" +
                    "- Melakukan proses administrasi pembayaran\n" +
                    "\n" +
                    "*Menjelaskan kepada konsumen dalam kondisi berdiri, bukan dari tempat duduk kasir "
            , 0))

        nosAudit.add(NosAudit(
            "Cashier  ",
            "Konsumen melakukan pembayaran | Melakukan Pembayaan  ",

            "- Mencetak bukti pembayaran\n" +
                    "- Konfirmasi bukti pembayaran ke konsumen\n" +
                    "- Memberikan stempel di buku servis "
            , 0))

        nosAudit.add(NosAudit(
            "Cashier  ",
            "Konsumen melakukan pembayaran | Melakukan Pembayaan  ",

            "Menyerahkan:\n" +
                    "a. Nota Jasa Bengkel\n" +
                    "b. Nota Suku Cadang\n" +
                    "c. Buku service KPB\n" +
                    "d. Kunci sepeda motor konsumen\n" +
                    "\n" +
                    "Menyerahkan kepada konsumen dalam kondisi berdiri, bukan dari tempat duduk kasir "
            , 0))


        nosAudit.add(NosAudit(
            "Cashier  ",
            "Konsumen melakukan pembayaran | Penjelasan PDSA  ",
            "- Membacakan konten PDSA (isi 5P, isi Catatan) kepada Konsumen "
            , 0))

        nosAudit.add(NosAudit(
            "Cashier  ",
            " Extra | Selesai pembayaran  ",
            "- Menawarkan apa ada yang bisa dibantu lagi\n" +
                    "- Mengucapkan terima kasih\n" +
                    "- Salam Satu Hati\n" +
                    "- Salam cuaca "
            , 0))

        nosAudit.add(NosAudit(
            "Cashier  ",
            " Extra | Selesai pembayaran  ",
            "Konfirmasi no. polisi sepeda motor konsumen, menerima dan menyerahkan uang dengan kedua tangan, menyebut nama konsumen, memanggil SA jika ada pertanyaan lebih lanjut dari konsumen "
            , 0))



        nosAudit.add(NosAudit(
            "Chief of Mechanic  ",
            "Pengiriman laporan kualitas Honda  ",
            "a. Nilai 1 jika setiap bulan jumlah LKH >= jumlah pit aktif\n" +
                    "b. Nilai 0 jika tidak sesuai\n "
            , 0))

        nosAudit.add(NosAudit(
            "Chief of Mechanic  ",
            "Pengiriman laporan kualitas Honda | AHASS mengirimkan LKH dengan kecepatan sesuai ketentuan AHM (Rank A <= 5 jam)  ",
            "a. Nilai 0 jika  tidak sesuai\n" +
                    "b. Nilai 1 jika sesuai\n" +
                    "\n" +
                    "Jika tidak ada LKH rank A, nilai = 1\n" +
                    "Data LKH selama 1 periode penilaian\n "
            , 0))

        nosAudit.add(NosAudit(
            "Chief of Mechanic  ",
            "Pengiriman laporan kualitas Honda  ",
            "AHASS mengirimkan LKH dengan kecepatan sesuai ketentuan AHM (Rank B & C <= 3 hari) "
            , 0))

        nosAudit.add(NosAudit(
            "Chief of Mechanic  ",
            "Pengiriman laporan kualitas Honda  ",
            "a. Nilai 0 jika  tidak sesuai\n" +
                    "b. Nilai 1 jika sesuai\n" +
                    "\n" +
                    "Data LKH selama 1 periode penilaian\n "
            , 0))

        nosAudit.add(NosAudit(
            "Parts Counter  ",
            "Menuju part counter | Menyambut konsumen ",
            "salam satu hati\n "
            , 0))

        nosAudit.add(NosAudit(
            "Parts Counter  ",
            " (pembelian langsung tanpa servis) | Menanyakan/menggali kebutuhan konsumen  ",
            "Menanyakan kebutuhan konsumen, \"ada yang bisa dibantu?“ dan menggali Parts (HGP),  AHM OIL dan HGA Accesories (HGA) atau apparel yang dicari  "
            , 0))

        nosAudit.add(NosAudit(
            "Parts Counter  ",
            "(pembelian langsung tanpa servis maupun permintaan saat servis oleh SA / mekanik) | Memeriksa ketersediaan stok  dan informasi HET kepada konsumen\n",
            "Memeriksa ketersediaan stok dan menginformasikan harga  (maksimum HET) beserta promo yang berlaku (jika tersedia)\n" +
                    "Untuk pembelian Accessories konsumen ditawarkan melakukan pemasangan di AHASS\n "
            , 0))

        nosAudit.add(NosAudit(
            "Parts Counter  ",
            "(pembelian langsung tanpa servis maupun permintaan saat servis oleh SA / mekanik)  | Menginstruksikan pengambilan parts dan mengupdate stock \n ",
            "Menginstruksikan pengambilan barang ke petugas gudang parts (jika terdapat parts picker) dan mengupdate stock di sistem sesuai transaksi\n "
            , 0))

        nosAudit.add(NosAudit(
            "Parts Counter  ",
            "(pembelian langsung tanpa servis maupun permintaan saat servis oleh SA / mekanik)  | Menginstruksikan pengambilan parts dan mengupdate stock \n ",
            "Menerbitkan nota suku cadang dan menginformasikan serta mengarahkan konsumen untuk membayar di kasir\n "
            , 0))

        nosAudit.add(NosAudit(
            "Parts Counter  ",
            "Pembelian HGA dan konsumen ingin dipasang langsung dimotor konsumen \n  ",
            "Memeriksa kuitansi pembayaran dan menyerahkan  Accesories ke SA agar dipasangkan pada motor konsumen dan meminta kesediaan konsumen untuk menunggu sesuai antrian yang ada\n "
            , 0))

        nosAudit.add(NosAudit(
            "Parts Counter  ",
            "Pembelian HGA dan konsumen ingin dipasang langsung dimotor konsumen \n  ",
            "Memeriksa ketersediaan stock di dealer sekitar dan MD\n" +
                    " Jika tersedia: menginformasikan konsumen bahwa parts tersedia di dealer lain/ MD dan menanyakan kesediaan konsumen/ SA  untuk menunggu sesuai dengan waktu proses pengiriman parts.\n" +
                    "\n" +
                    "Jika tidak tersedia:\n" +
                    "Melakukan penawaran untuk  pemesanan parts melalui Hotline Order "
            , 0))

        nosAudit.add(NosAudit(
            "Parts Counter  ",
            "Pembelian HGA dan konsumen ingin dipasang langsung dimotor konsumen \n  ",
            "JIka konsumen batal membeli:\n" +
                    "mencatat record part demand di sistem DMS "
            , 0))

        nosAudit.add(NosAudit(
            "Parts Counter  ",
            "Mempersiapkan keperluan pemesanan parts melalui Hotline Order \n | Menawarkan pemesanan parts melalui Hotline Order bila parts tidak tersedia saat itu ",
            "menginformasikan mengenai prosedur untuk pemesanan parts melalui Hotline Order "
            , 0))

        nosAudit.add(NosAudit(
            "Parts Counter  ",
            "Mempersiapkan keperluan pemesanan parts melalui Hotline Order \n | Menawarkan pemesanan parts melalui Hotline Order bila parts tidak tersedia saat itu ",
            "mengisikan form HLO (jika konsumen menyetujui untuk memesan part melalui HLO)  "
            , 0))

        nosAudit.add(NosAudit(
            "Parts Counter  ",
            "Mempersiapkan keperluan pemesanan parts melalui Hotline Order \n | Menawarkan pemesanan parts melalui Hotline Order bila parts tidak tersedia saat itu ",
            "Mengisikan HLO ke sistem DMS  "
            , 0))


        nosAudit.add(NosAudit(
            "Parts Counter  ",
            "Mempersiapkan keperluan pemesanan parts melalui Hotline Order \n | Menawarkan pemesanan parts melalui Hotline Order bila parts tidak tersedia saat itu ",
            "menginformasikan  harga  ( harga yang berlaku adalah Harga Eceran Tertinggi) dan estimasi kedatangan  "
            , 0))

        nosAudit.add(NosAudit(
            "Parts Counter  ",
            "Mempersiapkan keperluan pemesanan parts melalui Hotline Order \n | Menawarkan pemesanan parts melalui Hotline Order bila parts tidak tersedia saat itu ",
            "Mencetak nota penjualan melalui DMS  "
            , 0))

        nosAudit.add(NosAudit(
            "Parts Counter  ",
            "Mempersiapkan keperluan pemesanan parts melalui Hotline Order \n | Menawarkan pemesanan parts melalui Hotline Order bila parts tidak tersedia saat itu ",
            "- Mengisikan form HLO untuk smart key,battery LIB, key set  "
            , 0))


        nosAudit.add(NosAudit(
            "Parts Counter  ",
            "Mempersiapkan keperluan pemesanan parts melalui Hotline Order \n | Follow up pemenuhan parts via Hotline Order \n",
            "Melakukan follow up by call kepada konsumen mengenai informasi kedatangan parts nya di dealer tersebut sesuai dengan estimasi waktu yang dijanjikan, serta update estimasi waktu terbaru jika parts belum tersedia (Tanggal Followup dicatat di form dan jika parts sudah datang, PIC Parts wajib menanyakan estimasi kedatangan konsumen) "
            , 0))

        nosAudit.add(NosAudit(
            "Parts Counter  ",
            "Mempersiapkan keperluan pemesanan parts melalui Hotline Order  ",
            "Meremind kembali konsumen untuk mengambil part hotline di pagi hari pada hari-H kedatangan konsumen "
            , 0))

        nosAudit.add(NosAudit(
            "PIC HGA & Apparel  ",
            "Penjualan HGA & Apparel\n" +
                    "(melalui Sales People) | Mengambil Stock  HGA / Apparel dan melakukan pencatatan di Sistem Stock  ",
"Menerbitkan nota penjualan, melakukan update stock sesuai dengan transaksi konsumen dan menyerahkan HGA/Apparel dan nota kepada Sales People\n"
            , 0))

        nosAudit.add(NosAudit(
            "PIC HGA & Apparel  ",
            "Penjualan HGA & Apparel \n" +
                    "(Order Indent) | Kondisi : Jika Stock HGA & Apparel tidak ada di dealer tersebut  ",
            "Meminta Sales People untuk menginformasikan agar memesan HGA & Apparel melalui Order Indent dan kesediaan konsumen untuk Indent "
            , 0))
        nosAudit.add(NosAudit(
            "PIC HGA & Apparel  ",
            "Penjualan HGA & Apparel \n" +
                    "(Order Indent) |  Kondisi : Jika Stock HGA & Apparel tidak ada di dealer tersebut  ",
            "Menncetak nota penjualan melalui DMS "
            , 0))
        nosAudit.add(NosAudit(
            "PIC HGA & Apparel  ",
            "Penjualan HGA & Apparel \n" +
                    "(Order Indent) | Kondisi : Jika Stock HGA & Apparel tidak ada di dealer tersebut   ",
            "Mencatat order konsumen  di Form HGA dan Apparel dan menginformasikan konsumen ketika barang tersedia di jaringan.\n "
            , 0))
        nosAudit.add(NosAudit(
            "PIC HGA & Apparel  ",
            "Konsumen bertemu dengan Service Advisor  ",
            "memastikan ketersediaan ruang tunggu di AHASS. Apabila kondisi ruang tunggu sudah padat , maka SA dapat menyarankan menggunakan booking service / motor ditinggal\n "
            , 0))
        nosAudit.add(NosAudit(
            "PIC HGA & Apparel  ",
            "Konfirmasi terakhir oleh SA  ",
            "- mengarahkan konsumen untuk menggunakan hand sanitizer setelah proses penerimaan\n" +
                    "- mengingatkan konsumen untuk tetap menjaga jarak selama berada di ruang tunggu\n "
            , 0))
        nosAudit.add(NosAudit(
            "PIC HGA & Apparel  ",
            "Konfirmasi terakhir oleh SA  ",
            "- mengarahkan konsumen untuk menggunakan hand sanitizer setelah proses penerimaan\n" +
                    "- mengingatkan konsumen untuk tetap menjaga jarak selama berada di ruang tunggu\n "
            , 0))
        nosAudit.add(NosAudit(
            "PIC HGA & Apparel  ",
            "  ",
            "membersihkan hand grip, jok, spion motor di depan konsumen pada saat proses penyerahan\n "
            , 0))
        nosAudit.add(NosAudit(
            "PIC HGA & Apparel  ",
            "(pembelian langsung tanpa servis maupun permintaan saat servis oleh SA / mekanik)  ",
            "membersihkan kemasan parts dengan menggunakan lap (sudah dibasahi dengan air sabun) dan dikeringkan sebelum menyerahkan kepada konsumen\n "
            , 0))
        nosAudit.add(NosAudit(
            "PIC HGA & Apparel  ",
            "(pembelian langsung tanpa servis maupun permintaan saat servis oleh SA / mekanik)  ",
            "membersihkan kemasan parts dengan menggunakan lap (sudah dibasahi dengan air sabun) dan dikeringkan sebelum menyerahkan kepada konsumen\n "
            , 0))

        nosAudit.add(NosAudit(
            "Kepala Bengkel\n" +
                    "*khusus H23   ",
            "  ",
            "bekerja sama dengan SA untuk menjaga dan mengontrol implementasi protokol kesehatan dealer "
            , 0))

        nosAudit.add(NosAudit(
            "Kepala Bengkel\n" +
                    "*khusus H23   ",
            "  ",
            "mengetahui RS rujukan terdekat dan menginformasikan kepada Service Advisor "
            , 0))












        return nosAudit


    }

        fun generateDataH23Premises(): List<NosAudit> {

            val nosAudit = ArrayList<NosAudit>()

            nosAudit.add(NosAudit("Kebersihan AHASS ",
                "Interior ",
                "Kebersihan Interior :\n" +
                        "-Lantai dan Dinding Bengkel\n" +
                        "      - Bebas dari debu dan minyak pada saat tidak digunakan\n" +
                        "      - Bersih dari sarang binatang \n" +
                        "*Dibersihkan dengan sapu pada saat Jam istirahat mekanik dari debu/ kotoran sisan motor dari motor yang di servis\n" +
                        "*Dibersihkan setiap hari pada saat bengkel selesai beroperasi dengan menggunakan deterjen dan kain pel \n" +
                        "*Menchecklist Form kebersihan dan di dokumentasi (sebagai tools monitoring dan controllong)\n" +
                        "- Piping System \n" +
                        "      - Instalasi pipa harus selalu dalam kondisi bersih  dan  tidak ada sarang binatang\n" +
                        "- Meja Sa dan Front Desk \n" +
                        "    - bersih dan tidak terkelupas \n" +
                        "-Ruang Tunggu \n" +
                        "    -Bebas dari sampah AMDK dan piring bekas Snack konsumen \n" +
                        "*Dibersihkan apabila ada bekas AMDK yang tidak di buang oleh konsumen \n" +
                        "-Toilet\n" +
                        "    -Dalam keadaan bersih dan nyaman digunakan oleh Konsumen \n" +
                        "*Dibersihkan setiap hari dan diulang minimum pada pagi, siang, dan sore hari "
                , 0))



            nosAudit.add(NosAudit("Kebersihan AHASS ", "Checklist Kebersihan Interior ", "1. Terdapat checklist kebersihan untuk area AHASS, tools, ruang tunggu, toilet, dan rak helm\n" +
                    "2. Terdapat kolom checklist kebersihan minimal 3x sehari pada pagi, siang, dan sore hari ", 0))
            nosAudit.add(NosAudit("Booking Service  ", "Promotion Booking Service ", "1. Berupa : Poster/looping image/stiker\n" +
                    "2. Ukuran minimal A4\n" +
                    "3. Template design dan warna minimal mengikuti template design AHM\n" +
                    "4. Rapi, bersih, terbaca oleh konsumen\n" +
                    "5. Terletak di area penerimaan konsumen dan ruang tunggu konsumen\n" +
                    "6. Konten minimal :\n" +
                    "    a. No telpon booking service\n" +
                    "    b. Keterangan minimal booking H-1\n" +
                    "    c. Kondisi hangus (30 menit terlambat)\n" +
                    "7. No telpon booking service = no telpon AHASS dedicated (boleh no HP), bukan extention dari dealer\n" +
                    "8. No telpon booking service bisa dihubungi oleh konsumen ", 0))

            nosAudit.add(NosAudit("Booking service ",
                "Booking service board ", "1. Berupa : softcopy/hardcopy.\n" +
                    "2. Hardcopy ukuran minimal A3\n" +
                    "3. Template design minimal mengikuti template design AHM\n" +
                    "4. Terletak di area penerimaan konsumen\n" +
                    "5.  Konten minimal :\n" +
                    "    a. Hari dan tanggal hari H\n" +
                    "    b. No telpon booking servis\n" +
                    "    c. Nomer pit (jika jumlah pit booking servis > 1)\n" +
                    "    d. Jam booking\n" +
                    "    e. Nama konsumen\n" +
                    "    f. Nomer polisi ", 0))

            nosAudit.add(NosAudit("Booking service ",
                "Data Follow-Up Reminder Servis by SMS / WA (rutinCRM/exclude booking service)\n ",
                "1. Data reminder SMS / WA untuk servis KPB 2 - 4 & servis reguler yang dibagi oleh PIC CRM termasuk konsumen premium dan booking servis\n" +
                        "2. Available berupa apps / file ms. Excel / hard copy\n" +
                        "3. Terdapat di admin CRM H2\n" +
                        "4. Konten minimal berisi : \n" +
                        "     a. nama konsumen \n" +
                        "     b. no hp\n" +
                        "     c. pekerjaan\n" +
                        "     d. tipe & tahun motor sebelumnya\n" +
                        "     f. tanggal follow up SMS / WA \n" +
                        "     g. hasil follow up SMS / WA (misal terkirim/ tidak terkirim / booking servis/ servis kunjung / dll)\n" +
                        "     h. keterangan\n" +
                        "\n" +
                        "Note : proses reminder selalu update, minimal reminder WA / SMS dilakukan 1 hari yang lalu (di hari kerja)  ",
                0))

            nosAudit.add(NosAudit("Booking service ",
                "Data Customer call reminder ",
                "1. Data reminder Call untuk servis KPB 1 - 4 & reguler yang dibagi oleh PIC CRM termasuk konsumen premium dan booking servis\n" +
                        "2. Available berupa apps / file ms. Excel / hard copy\n" +
                        "3. Terdapat di admin CRM H2\n" +
                        "4. Konten minimal berisi : \n" +
                        "     a. nama konsumen \n" +
                        "     b. no hp\n" +
                        "     c. pekerjaan\n" +
                        "     d. tipe & tahun motor sebelumnya\n" +
                        "     f. tanggal follow up call \n" +
                        "     g. hasil follow up call (misal terkirim/ tidak terkirim / booking servis/ servis kunjung / dll)\n" +
                        "     h. keterangan\n" +
                        "\n" +
                        "Note : proses reminder selalu update, minimal reminder call dilakukan 1 hari yang lalu (di hari kerja)  ",
                0))

            nosAudit.add(NosAudit("Parking Area ",
                "Parking in & out signage  ",
                "1. Terbaca oleh konsumen yang akan masuk ke AHASS\n" +
                        "2. Terpisah antara in dan out\n" +
                        "3. Warna jelas dan tidak kusam/luntur\n" +
                        "\n" +
                        "(Ganti gambar) ",
                0))

            nosAudit.add(NosAudit("Parking Area ",
                "Parking lot ",
                "1. Jumlah minimal = 2x jumlah pit fisik\n" +
                        "2. Terletak di luar area pit kerja bengkel atau berada di area halaman bengkel yang tidak mengganggu manuver motor yang akan diservis\n" +
                        "3. Terdapat marka/garis parkir per motor dengan ketentuan sbb :\n" +
                        "   a. Warna marka/garis = putih/kuning\n" +
                        "   b. Ukuran per motor min : 0.75 x 2.0 meter ",
                0))

            nosAudit.add(NosAudit("Parking Area ",
                "Queueing Number (Nomor Antrian) ",
                "1. Jumlah minimal = jumlah pit aktif x 12\n" +
                        "2. Terletak di area satpam/security AHASS\n" +
                        "3. Rapi, dilaminating, tidak sobek\n" +
                        "4. Standar minimal :\n" +
                        "    a. Ukuran min 4 cm x 4 cm\n" +
                        "    b. Warna dasar putih, tinta gelap\n" +
                        "5. Konten minimal :\n" +
                        "    a. Nomor urut yang di print \n" +
                        "6. Jika sudah menggunakan sistem antrian print, maka yang penting tertera nomor urutannya\n" +
                        "7. Dibersihkan setiap sore dan tidak dipakai berulang dalam 1 hari ",
                0))

            nosAudit.add(NosAudit("Parking Area ",
                "Thermal Gun ",
                "1. Spesifikasi: infrared dan digital\n" +
                        "2. Terdapat pada pintu masuk atau di area security / pintu masuk (dedicated untuk showroom)\n" +
                        "3. Untuk dealer H123, thermal gun di AHASS berbeda dengan showroom (mempunyai 2 thermal gun) ",
                0))

            nosAudit.add(NosAudit("Parking Area ",
                "Tempat Cuci Tangan ",
                "1. Tersedia tempat cuci tangan dan sabun pada area pintu masuk \n" +
                        "2. Terdapat sabun cair \n" +
                        "*untuk dealer H123 minimal terdapat 1 tempat cuci tangan pada area pintu masuk dealer\n" +
                        "\n" +
                        "\n" +
                        "Dapat menyesuaikan rekomendasi juklak 164/AHM/MPA/VII/2020 ",
                0))

            nosAudit.add(NosAudit("Parking Area ",
                "Garis Pembatas ",
                "1. Terdapat garis pembatas 1m pada area parkir yang dapat digunakan sebagai titik berdiri konsumen untuk memasuki showroom, jika kondisi sedang ramai ",
                0))

            nosAudit.add(NosAudit("Parking Area ",
                "Poster Reminder  ",
                "Terdapat informasi untuk menggunakan masker dan  hand sanitizer pada pintu masuk showroom\n" +
                        "\n" +
                        "Dapat diletakan bersebelahan dengan hand santizer atau ditempel pada botol hand sanitizer\n ",
                0))

            nosAudit.add(NosAudit("Parking Area ",
                "Hand Sanitizer ",
                "1. Terletak di pintu masuk AHASS, meja ruang tunggu, meja SA, dan front desk\n" +
                        "2 Minimal terisi 1/4 penuh dengan kandungan alkohol minimal 60% ",
                0))

            nosAudit.add(NosAudit("Service Advisor Area ",
                "SA Desk ",
                "1. Sesuai dengan standar AHM, bersih, tidak terkelupas/pudar\n" +
                        "2. berlokasi diarea penerimaan konsumen \n" +
                        "\n" +
                        "Kalau old vinci : nilai 1\n ",
                0))
            nosAudit.add(NosAudit("Service Advisor Area ",
                "Rak Helm ",
                "1. Rapi, bersih, dan digunakan oleh konsumen AHASS\n" +
                        "2. Untuk new vinci : design sesuai standar AHM\n" +
                        "3. Lokasi di area penerimaan konsumen, di dekat SA desk (lokasi sesuai yang di approve bersama) ",
                0))

            nosAudit.add(NosAudit("Service Advisor Area ",
                "Struktur Organisasi ",
                "1. Berupa : softcopy/hardcopy.\n" +
                        "2. Hardcopy yang dibingkai rapi, berukuran minimal A3\n" +
                        "3. Template design minimal mengikuti template design AHM\n" +
                        "4. Terletak di area penerimaan konsumen\n" +
                        "5.  Terbaca oleh konsumen\n" +
                        "6. Terupdate\n" +
                        "7. Konten minimal :\n" +
                        "    a. Foto+ no HP kepala bengkel dan service advisor\n" +
                        "    b. Jabatan lain berupa list nama ",
                0))
            nosAudit.add(NosAudit("Service Advisor Area ",
                "Nomor rangka Market treatment di papan SA ",
                "1. Ditempel data no rangka- no mesin dari unit motor yang masuk kategory market treatment\n" +
                        "2.Terbaca dan dalam kondisi baik \n" +
                        "3. No rangka - No mesin wajib ditempel di setiap papan SA\n ",
                0))
            nosAudit.add(NosAudit("Service Advisor Area ",
                "Workshop Form (Form SA) ",
                "1. Berupa : softcopy/hardcopy\n" +
                        "2. Hardcopy mempunyai min 1 copy, berukuran minimal A4\n" +
                        "3. Konten minimal sesuai dari AHM\n" +
                        "4. Tambahan konten dari form SA lama   \n" +
                        "     a. Data motor    \n" +
                        "     b. Data Pemilik \n" +
                        "    c. Data pembawa\n" +
                        "    d. Data CRM\n" +
                        "    e. Tanda tangan tambahan pekerjaan\n" +
                        "    f. Tanda tangan penyerahan motor\n" +
                        "    g. Km ganti part\n" +
                        "    h. Garansi motor premium\n" +
                        "    i. Informasi waktu sepeda motor dikerjakan\n" +
                        "5. Terletak di area penerimaan konsumen ",
                0))
            nosAudit.add(NosAudit("Service Advisor Area ",
                "Service tag ",
                "1. Rapi dan tidak rusak\n" +
                        "2. Terletak di SA desk atau area penerimaan konsumen\n" +
                        "3. Mengikuti standar minimal yang diberikan oleh AHM sbb :\n" +
                        "    a. Material minimal dari PVC/kertas manila dilaminating\n" +
                        "    b. Ukuran minimal 18 cm x 9 cm\n" +
                        "    c. Ukuran diameter gantungan 5.5 cm\n" +
                        "    d. Tulisan merupakan hasil cetakan\n" +
                        "    e. Tampak 2 sisi\n" +
                        "4. Konten minimal sbb :\n" +
                        "    a. Logo AHASS\n" +
                        "    b. Nomer antrian\n" +
                        "    c. Nama pekerjaan\n" +
                        "5. Ketentuan warna dasar tag sbb :\n" +
                        "   a. Regular = Hijau\n" +
                        "   b. Booking = merah\n" +
                        "   c. Fast track = putih ",
                0))
            nosAudit.add(NosAudit("Service Advisor Area ",
                "Cleaning Kit ",
                "1. berisi campuran air bersih dengan sabun (dilarang menggunakan cairan yang mengandung alkohol)\n" +
                        "2. wiping tools microfiber / plas chamois \n" +
                        "3. Terletak di area meja SA atau parkir motor selesai service ",
                0))

            nosAudit.add(NosAudit("Service Advisor Area ",
                "Informasi Jam Operasional Bengkel  ",
                "1. Terdapat tulisan/informasi jam operasional bengkel\n" +
                        "2. Tulisan terlihat jelas oleh konsumen \n" +
                        "3. Penempatan di area penerimaan konsumen / pintu masuk bengkel ",
                0))

            nosAudit.add(NosAudit("Material Promosi ",
                "Standar Produk Servis\n ",
                "1. Rapi, bersih, dan tidak rusak\n" +
                        "2. Mudah terlihat konsumen di area penerimaan konsumen\n" +
                        "3. Hardcopy/softcopy\n" +
                        "4. Standar Produk Servis sesuai standar AHM yaitu :\n" +
                        "    a. Paket servis lengkap\n" +
                        "    b. Paket servis ringan\n" +
                        "    c. Paket ganti oli plus ",
                0))
            nosAudit.add(NosAudit("Material Promosi ",
                "Paket servis tambahan (Pembersihan CVT, kuras tangki, dll) ",
                "1. Rapi, bersih, dan tidak rusak\n" +
                        "2. Mudah terlihat konsumen di area penerimaan konsumen\n" +
                        "3. Hardcopy/softcopy ",
                0))
            nosAudit.add(NosAudit("Material Promosi ",
                "Produk tambahan AHASS (press body,las, cat,dll)\n" +
                        "nice to have ",
                "1. Rapi, bersih, dan tidak rusak\n" +
                        "2. Mudah terlihat konsumen di area penerimaan konsumen dan atau di ruang tunggu konsumen\n" +
                        "3. Hardcopy/softcopy ",
                0))



            nosAudit.add(NosAudit("Material Promosi ",
                "Edukasi / Promosi Service, Parts & Oil ",
                "1. Rapi, bersih, dan tidak rusak\n" +
                        "2. Mudah terlihat konsumen di area penerimaan konsumen dan atau di area ruang tunggu konsumen\n" +
                        "3. Hardcopy/softcopy\n" +
                        "\n" +
                        "notes: \n" +
                        "- edukasi : ada konten fitur dan manfaat \n" +
                        "- promosi : ada konten benefit nya \n ",
                0))

            nosAudit.add(NosAudit("Front desk area ",
                "Komputer & software operasional setara DMS ",
                "1. Berfungsi dengan baik untuk :\n" +
                        "     a. input form SA menjadi PKB sistem\n" +
                        "     b. rekap laporan bengkel\n ",
                0))
            nosAudit.add(NosAudit("Material Promosi ",
                "Front Desk ",
                "1. Sesuai design AHM\n" +
                        "2. Bersih, rapi, tidak terkelupas\n" +
                        "3. Lokasi sesuai dengan denah yang di approve bersama ",
                0))
            nosAudit.add(NosAudit("Material Promosi ",
                "Front desk dengan bagian belakang langsung ke Gudang Kaca Parts (Old & New VinCi - 4 ruko) ",
                "1. Pada bagian rak parts di Gudang kaca ,  area pinggiran rak paling ujung yang terpasang ram kawat digunakan untuk mendisplay parts dengan mengikuti estetika kerapihan dan kebersihan\n" +
                        "2. Penataan parts pada bagian rak di gudang kaca terlihat rapi dan bersih\n ",
                0))
            nosAudit.add(NosAudit("Material Promosi ",
                "Safety shield\n" +
                        "*Nice to have ",
                "1. Terletak di meja counter \n" +
                        "2. sesuai dengan rekomendasi juklak 154/AHM/MPA/VI/2020 ",
                0))

            nosAudit.add(NosAudit("Material Promosi ",
                "Sparepart display & stock rack (Old VinCi) ",
                "1. Penataan rapi dan sesuai dengan juklak Minimum Item Display :\n" +
                        "Old VinCi : Busi,Gear Set/ Drive belt,battery,tube,brake shoe dan pad,bearing,bulb\n" +
                        "2. Dalam keadaan bersih dan rapi\n ",
                0))


            nosAudit.add(NosAudit("Material Promosi ",
                "Sparepart display & stock rack (New VinCi 2- 3 ruko, tanpa gudang kaca di lantai dasar) ",
                "1. Penataan rapi dan sesuai dengan juklak Minimum Item Display :\n" +
                        "New VinCi : Pad Set, Brakeshoe, Belt, Piston, Busi, Bearing, Tire Tubeless, DCK, Battery, Shock, Element Cleaner, AHM OIL, GMO, HIC, Brakefluid, Coolant\n" +
                        "2. Dalam keadaan bersih dan rapi\n" +
                        "3. Lokasi sesuai dengan denah yang di approve bersama\n" +
                        "4. Tidak digunakan untuk mendisplay Accessories dan Apparel ",
                0))

            nosAudit.add(NosAudit("Ruang tunggu  ",
                "Kursi ruang tunggu  ",
                "1. Jumlah minimum = 2 x jumlah pit fisik (di luar kursi dealing table)\n" +
                        "2. Kursi bersandar\n" +
                        "3. Lokasi kursi sesuai dengan denah yang di approve bersama\n" +
                        "4. Diberi jarak sesuai dengan juklak MPA No 136/AHM/MPA/IV/2020\n" +
                        "5. Apabila space tidak cukup, bisa diberi tanda silang untuk kursi yang tidak boleh diduduki ",
                0))
            nosAudit.add(NosAudit("Ruang tunggu  ",
                "Poster A3 Edukasi Covid ",
                "Terletak di area ruang tunggu ",
                0))
            nosAudit.add(NosAudit("Ruang tunggu  ",
                "TV ",
                "1. Jumlah minimal = 2 TV\n" +
                        "2. Ukuran min 32\" flat\n" +
                        "3. Menampilkan televisi hiburan, promosi+ progres antrian\n" +
                        "4. Diletakkan di hadapan konsumen dengan rapi, dan mudah dilihat oleh customer ketika menunggu  ",
                0))

            nosAudit.add(NosAudit("Ruang tunggu  ",
                "Air minum dalam kemasan gratis ",
                "1. Tersedia cukup di ruang tunggu \n" +
                        "2. Ada tulisan \"GRATIS\" ",
                0))
            nosAudit.add(NosAudit("Ruang tunggu  ",
                "Tempat sampah  ",
                "1. Ada ",
                0))

            nosAudit.add(NosAudit("Ruang tunggu  ",
                "Toilet  ",
                "1. Dalam keadaan bersih, tidak bau\n" +
                        "2. termasuk : ketersediaan air,sabun cuci tangan,tissue / handdryer,keset(doormat)\n" +
                        "3. Terdapat signage yang jelas dari ruang tunggu \n" +
                        "4. Pintu dan kelengkapannya dalam kondisi baik\n" +
                        "5. Penerangan cukup\n" +
                        "6. Terdapat checklist kebersihan minimum 3x sehari pada pagi, siang, dan sore hari ",
                0))

            nosAudit.add(NosAudit("Ruang tunggu  ",
                "Sertifikat Mekanik  ",
                "1. Mudah terbaca oleh konsumen \n" +
                        "2. Tertata rapi dan dalam kondisi yang baik\n" +
                        "3. visual berupa : pajangan sertifikat yang dibingkai (intext lama)  atau dalam bentuk digital , ditampilkan di TV ruang tunggu ( intext baru)\n" +
                        "4. Dapat berupa sampling sesuai keterwakilan tiap level mekanik ",
                0))

            nosAudit.add(NosAudit("Ruang tunggu  ",
                "Wifi gratis\n" +
                        "(Old VinCi) ",
                "1. Terdapat informasi free wifi (termasuk user name dan pasword) , terbaca jelas oleh konsumen (tanpa penghalang)\n" +
                        "2. Berfungsi dengan baik\n" +
                        "\n" +
                        "Apabila new Vinci, mengikuti nilai H1 premise untuk item yang sama\n" +
                        "\n" +
                        "(Gambar akan diganti) ",
                0))

            nosAudit.add(NosAudit("Ruang tunggu  ",
                "Free charging area\n" +
                        "(Old VinCi) ",
                "1. Terdapat informasi free charging area\n" +
                        "2. Kelistrikan berfungsi\n" +
                        "2. Tertata dengan rapi \n" +
                        "3. Bila tidak sesuai juklak maka akan dinilai N/A\n" +
                        "\n" +
                        "Apabila new Vinci, mengikuti nilai H1 premise untuk item yang sama ",
                0))
            nosAudit.add(NosAudit("Ruang tunggu  ",
                "Sirkulasi udara Ruang Tunggu ",
                "1. Memiliki AC atau 1 buah kipas angin langit-langit  (ceiling fan) ukuran minimal 142 cm atau 2 buah kipas angin tembok/berdiri (standing fan) ukuran minimal 35cm \n" +
                        "2. Peletakan diatur sehingga seluruh ruang tunggu terkena sirkulasi udara\n ",
                0))

            nosAudit.add(NosAudit("Ruang tunggu  ",
                "Form monitoring kebersihan ruang tunggu AHASS ",
                "1.memiliki Form yang digunakan untuk memonitoring kebersian ruang tunggu terutama di daerah / fasiliatas yang langsung berhubungan dengan kenyamanan  konsumen saat berada di ruang tunggu  :\n" +
                        "a. Meja Ruang tunggu\n" +
                        "b. Kursi Ruang tunggu\n" +
                        "c. toilet \n" +
                        "2. tercamtum jadwal pengecekan kebersihan dan di checklist sesuai waktu melakuakn pembersihan \n" +
                        "3. dalam keadaan bersih dan digunakan \n ",
                0))


            nosAudit.add(NosAudit("Mechanic Area : mengikuti standard fascia  ",
                "Papan nama mekanik  ",
                "1. Tipe, ukuran, desain sesuai dengan standard PT AHM \n" +
                        "2. Dalam keadaan bersih dan rapi \n" +
                        "3. Tinggi sejajar piping system atau 225 cm dari lantai \n" +
                        "4. Nama mekanik pit sesuai\n" +
                        "5. Penulisan nama mekanik boleh menggunakan media plat/kertas dengan warna senada (new vinci : merah,tulisan abu-abu/silver, old vinci : biru tulisan putih) \n" +
                        "6. Nama mekanik menghadap ke arah ruang tunggu konsumen  ",
                0))

            nosAudit.add(NosAudit(" ",
                "Pit khusus konsumen premium ",
                "1. Jumlah = 2 pit\n" +
                        "2. Berfungsi dengan baik\n" +
                        "3. Ada penanda \"Fast Track Pit\" sesuai ketentuan AHM ",
                0))

            nosAudit.add(NosAudit(" ",
                "Exhaust system \n" +
                        "inc blower untuk seluruh pit aktif ",
                "1. Dalam keadaan bersih dan rapi\n" +
                        "2. Tidak rusak/sobek\n" +
                        "3. Berfungsi  dan digunakan dengan baik \n ",
                0))
            nosAudit.add(NosAudit(" ",
                "5 green poster ",
                "1. Terlihat oleh mekanik \n" +
                        "2. Dibingkai dan diberi cover kaca/plastik dalam keadaan rapi ",
                0))

            nosAudit.add(NosAudit("Mechanic Area : mengikuti standard fascia | Promotional Items  ",
                "5 green poster ",
                "1. Terlihat oleh mekanik \n" +
                        "2. Dibingkai dan diberi cover kaca/plastik dalam keadaan rapi ",
                0))

            nosAudit.add(NosAudit(" ",
                "5 clean poster ",
                "1. Terlihat oleh mekanik \n" +
                        "2. Dibingkai dan diberi cover kaca/plastik dalam keadaan rapi ",
                0))

            nosAudit.add(NosAudit(" ",
                "No smoking signage ",
                "1. Terlihat oleh customer dan mekanik\n" +
                        "2. Dalam keadaan rapi ",
                0))

            nosAudit.add(NosAudit("Mechanic Area : mengikuti standard fascia | Mechanic Tools\n  ",
                "Mechanic Truster ",
                "1. Lengkap dan berfungsi dengan baik\n" +
                        "2. Tertata rapi pada Mechanic Truster Box\n" +
                        "3. 1 pit aktif = 1 mechanic truster\n" +
                        "4. 100% tools asli dari AHM\n" +
                        "5. Tidak ada part bekas\n" +
                        "6. Melakukan checklist tools yang sudah distandarkan oleh AHM (harian)\n" +
                        "7. Tidak ada barang-barang lain yang tidak berkaitan dengan keperluan service ",
                0))

            nosAudit.add(NosAudit("Mechanic Area : mengikuti standard fascia | Mechanic Tools\n  ",
                "Wiping tool ",
                "1. Ada perangkat wiping  \n" +
                        "2. Berbahan microfiber / plas chamois \n" +
                        "3. Ada di tiap pit aktif \n" +
                        "4. Jika sudah ada fasilitas cuci motor, nilai = +1 ",
                0))


            nosAudit.add(NosAudit("Mechanic Area : mengikuti standard fascia | Mechanic Tools\n  ",
                "Air Gun ",
                "1. Berfungsi dengan baik dan digunakan oleh mekanik\n" +
                        "2. Tertata rapi\n" +
                        "3. 1 pit aktif = 1 air gun\n" +
                        "4. Berasal dari pipa yang tersambung ke jalur non Lubricant ",
                0))

            nosAudit.add(NosAudit("Mechanic Area : mengikuti standard fascia | Mechanic Tools\n  ",
                "Impact Wrench ",
                "1. Berfungsi dengan baik dan digunakan oleh mekanik\n" +
                        "2. Tertata rapi\n" +
                        "3. 1 pit aktif = 1 impact wrench\n" +
                        "4. Berasal dari pipa yang tersambung ke jalur Lubricant ",
                0))

            nosAudit.add(NosAudit("Mechanic Area : mengikuti standard fascia | Mechanic Tools\n  ",
                "Bike Lift ",
                "1. Ada dan berfungsi dengan baik (naik maksimum)\n" +
                        "2. Tertata rapi\n" +
                        "3. 1 pit aktif = 1 bike lift\n" +
                        "4. Pelumasan ok\n" +
                        "5. Warna merah untuk new vinci, warna biru (warna kuning di tengah) untuk old vinci\n" +
                        "* Untuk heavy repair tidak wajib memiliki bike lift ",
                0))

            nosAudit.add(NosAudit("Mechanic Area : mengikuti standard fascia | Mechanic Tools\n  ",
                "Special Tools ",
                "1. Ada dan berfungsi dengan baik\n" +
                        "2. Tertata rapi di tempat penyimpanan khusus Special Tools\n" +
                        "3. 1 AHASS memiliki Special Tools dengan jumlah dan jenis yang mengacu pada checklist checklist TSD_Special Tools\n" +
                        "Reguler Dlr = minimum 13 item \n" +
                        "Wing Dlr = minimum 21 item\n" +
                        "Big Wing = minimum 26 item\n" +
                        "4. Ada checklist rutin yang dilakukan pada bulan Januari dan Juli oleh PIC Tools yang ditunjuk di AHASS ",
                0))

            nosAudit.add(NosAudit("Mechanic Area : mengikuti standard fascia | Mechanic Tools\n  ",
                "Torquemeter sudah ditera ",
                "1. Sudah ditera (proses tera diperbolehkan oleh MD) ",
                0))
            nosAudit.add(NosAudit("Mechanic Area : mengikuti standard fascia | Mechanic Tools\n  ",
                "Measurement Tools ",
                "1. Ada dan berfungsi dengan baik\n" +
                        "2. Tertata rapi di tempat penyimpanan khusus Measurement Tools\n" +
                        "3. 1 AHASS memiliki Measurementl Tools dengan jumlah dan jenis yang mengacu pada checklist TSD_Measurement Tools\n" +
                        "4. Ada checklist rutin yang dilakukan pada bulan Januari dan Juli oleh PIC Tools yang ditunjuk di AHASS ",
                0))

            nosAudit.add(NosAudit("Mechanic Area : mengikuti standard fascia | Mechanic Tools\n  ",
                "Fire extinguisher/protection  ",
                "1. Berfungsi dengan baik\n" +
                        "2. Tertata rapi\n" +
                        "3. Rasio : 1 APAR mengcover max 5 pit aktif\n" +
                        "4. 1 APAR = 2.5 kg - 4 kg\n" +
                        "5. Bahan APAR : CO2/Powder/Foam\n" +
                        "6. Jarak antar APAR max 15 meter\n" +
                        "7. Ketinggian APAR dari lantai antara 15 cm sampai dengan 120 cm\n" +
                        "8. APAR bebas halangan\n" +
                        "9. Ada petunjuk lokasi APAR\n" +
                        "10. Jarum penunjuk tekanan berada di area hijau\n" +
                        "11. Sudah diperiksa dinas terkait  dan belum expired ",
                0))

            nosAudit.add(NosAudit("Mechanic Area : mengikuti standard fascia | Mechanic Tools\n  ",
                "MC protector(untuk MC yang menginap) ",
                "1. Berfungsi dengan baik\n" +
                        "2. Tertata rapi\n" +
                        "3. Sejumlah motor yang menginap\n" +
                        "4. 1 motor menginap = 1 cover ",
                0))

            nosAudit.add(NosAudit("Mechanic Area : mengikuti standard fascia | Mechanic Tools\n  ",
                "MC seat cover & hand grip cover ( untuk repair MC) ",
                "1. Sejumlah pit aktif\n" +
                        "2. Rapi, bersih, tidak terkelupas\n" +
                        "3. Berfungsi dan digunakan untuk setiap motor yang diservis\n" +
                        "4. Berwarna merah, ada logo AHASS dan tulisan \"Honda Wing\"\n" +
                        "5. Berwarna merah, ada logo AHASS dan tulisan \"Honda\" -> Untuk H123 dan H23 reguler ",
                0))

            nosAudit.add(NosAudit("Mechanic Area : mengikuti standard fascia | Mechanic Tools\n  ",
                "Tire changer ",
                "1. Berfungsi dan digunakan untuk setiap penggantian ban\n" +
                        "2. Diperbolehkan dengan tire changer non elektrik ",
                0))
            nosAudit.add(NosAudit("Mechanic Area : mengikuti standard fascia | Mechanic Tools\n  ",
                "Tidak menggunakan Bensin untuk Servis ",
                "1. Disediakan sarana pengganti Bensin di tiap pit aktif \n" +
                        "2. tersedia HPC sejumlah seusi Juklak ( 6 buah per AHASS)  ",
                0))


            nosAudit.add(NosAudit("Mechanic Area : mengikuti standard fascia | Mechanic Tools\n  ",
                "Perintah Kerja Bengkel ",
                "1. Ada PKB (Perintah Kerja Bengkel) yang diprint dari komputer front desk officer atau copy form SA dan buku servis\n" +
                        "2. Diletakkan di atas board jepit\n" +
                        "3. Board jepit dijepitkan di plat nomer bagian belakang sepeda motor yang sedang dikerjakan di pit ",
                0))
            nosAudit.add(NosAudit("Mechanic Area : mengikuti standard fascia | Mechanic Tools\n  ",
                "Tempat Penampungan Sentral ",
                "1. Ada 5 wadah (drum/pail plastik) dengan klasifikasi sesuai juklak LB3:\n" +
                        "  -(B104d u/kemasan bekas B3)\n" +
                        "  -(B355-2 u/battery bekas)\n" +
                        "  -(A108d u/Limbah terkontaminasi B3)\n" +
                        "  -(B109d u/filter bekas)\n" +
                        "  -(B110d u/kain majun bekas)\n" +
                        "2. Diberikan pembeda warna sesuai juklak\n" +
                        "\n" +
                        "Jika sudah memiliki TPS di belakang/dalam AHASS, maka central hub langsung Exist, Good ",
                0))

            nosAudit.add(NosAudit("Mechanic Area : mengikuti standard fascia | Mechanic Tools\n  ",
                "Tempat Penampungan PIT ",
                "Penampungan per pit:\n" +
                        "1. Setiap pit aktif mempunyai 1 keranjang merah tidak bercela (41.5 cm x 28.5 cm x 16 cm)\n" +
                        "2. Setiap pit aktif mempunyai 1  jirigen oli min 10 L\n" +
                        "3. Keranjang & jirigen dalam keadaan baik (tidak bocor, tidak patah, tidak getas) ",
                0))


            nosAudit.add(NosAudit("Mechanic Area : mengikuti standard fascia | Final Inspection  ",
                "Mechanic Truster  ",
                "1. Berfungsi dengan baik\n" +
                        "2. Tertata rapi\n" +
                        "3. 1 pit final inspection aktif = 1 mechanic truster (jumlah aktif mengikuti rasio final inspector AHM)\n" +
                        "4. 100% tools asli dari AHM\n" +
                        "5. Tidak ada part bekas\n" +
                        "6. Ada checklist rutin min 1 bulan sekali dari kepala mekanik, sudah berjalan min 3 bulan\n" +
                        "7. Tidak ada barang-barang lain yang tidak berkaitan dengan pekerjaan bengkel dan service) ",
                0))

            nosAudit.add(NosAudit("Mechanic Area : mengikuti standard fascia | Final Inspection  ",
                "RPM Meter ",
                "1. Berfungsi dengan baik\n" +
                        "2. terlihat dari Area Konsumen\n ",
                0))
            nosAudit.add(NosAudit("Mechanic Area : mengikuti standard fascia | Final Inspection  ",
                "Exhaust system  ",
                "1. Dalam keadaan bersih dan rapi\n" +
                        "2. Berfungsi dan digunakan dengan baik  ",
                0))

            nosAudit.add(NosAudit("Mechanic Area : mengikuti standard fascia | Final Inspection  ",
                "Helm\n ",
                "1. Minimal helm half face SNI, dalam keadaan bersih dan rapi\n" +
                        "2. Ada tulisan \"Helm Final Inspection\"\n" +
                        "3. Digunakan pada saat final inspection mencoba tes jalan motor\n" +
                        "4. Jumlah minimal 1 ",
                0))
            nosAudit.add(NosAudit("Final Confirmation  ",
                "Sticker reminder ",
                "1. Sesuai dengan ketentuan dari PT AHM\n" +
                        "    Minimum konten :\n" +
                        "    a. logo Wing Honda Merah\n" +
                        "    b. logo AHASS \n" +
                        "    c. Nama AHASS\n" +
                        "    d. No telpon AHASS\n" +
                        "    e. Km dan atau waktu servis kembali sesuai maintenance schedule\n" +
                        "    f.  Km dan atau waktu ganti oli kembali sesuai maintenance schedule\n" +
                        "\n ",
                0))
            nosAudit.add(NosAudit(
                "Payment  ",
                "Harga Jasa ",
                "1. Menggunakan design standar dari AHM\n" +
                        "2. Dalam kondisi bersih \n" +
                        "3. Terlihat jelas oleh konsumen di sekitar area pembayaran\n" +
                        "4. Memuat informasi garansi\n" +
                        "5. Uk min A3 jika ditempel di dinding, uk min A4 jika diletakkan di meja\n" +
                        "6. New vinci kasir H2 wajib gabung dengan kasir H1, jika masih terpisah nilai = -1\n" +
                        "7. Wajib terisi dan harga terupdate ",
                0))

            nosAudit.add(NosAudit(
                "Payment  ",
                "Bukti Pembayaran ",
                "1. Tercetak jelas dari komputer (non excel)\n" +
                        "2. Ada nama, alamat, dan nomer telpon AHASS\n" +
                        "3. Berisi :\n" +
                        "- Harga jasa service (sesuai price list)\n" +
                        "- Harga parts yang dibeli maksimal sesuai Harga Eceran Tertinggi\n" +
                        "- Garansi \n" +
                        "- \"Part bekas adalah hak konsumen AHASS\" ",
                0))
            nosAudit.add(NosAudit(
                "Payment  ",
                "Buku Service ",
                "1. terdapat stempel Dealer (Halaman 17-21)\n" +
                        "2. Terisi checklist pekerjaan yang sudah dilakukan mekanik sesuai maintenance schedule + catatan dari mekanik (Hal12-15)\n" +
                        "3. Pengisian stempel dan checklist sesuai \"Km servis\"\n ",
                0))
            nosAudit.add(NosAudit(
                "Follow up call ",
                "List follow up call ",
                "1. Berbentuk hardcopy / sofcopy\n" +
                        "2. Dilakukan setiap hari (H+7 usai perawatan/perbaikan)\n" +
                        "3. Berisi informasi yang jelas : \n" +
                        "- Nama customer\n" +
                        "- Nomor telepon customer\n" +
                        "- Kategori pengerjaan M/C customer (HR/LR/JR/Claim,etc)\n" +
                        "- Premium customer (semua pekerjaan)\n" +
                        "- Respons customer \n" +
                        "- Jadwal JR jika respons customer tidak OK ",
                0))
            nosAudit.add(NosAudit(
                "Hotline Order Facilities ",
                "Signage / poster / board tata cara melakukan Hotline Order ",
                "1. Memiliki signage / poster / HLO board berisi  tata cara melakukan pesanan parts via Hotline Order di counter parts\n" +
                        "2. Signage berisi flow pemesanan Hotline (dealer - MD - AHM), design dapat dilakukan oleh MD ",
                0))

            nosAudit.add(NosAudit(
                "Hotline Order Facilities ",
                "Memiliki Form rekap pemenuhan dan Follow Up Hotline Order\n ",
                "1. Terdapat Form order dan Follow Up Hotline konsumen di Dealer (Parts Counter), Form boleh berupa Hard copy, soft copy atau Portal (sistem)\n" +
                        "2. Form terisi dengan benar sesuai dengan order, data konsumen beserta tanggal follow upnya\n" +
                        "3. Terdapat rekap Form Follow Up Hotline di bulan-bulan sebelumnya (Min 3 bulan ke belakang)\n" +
                        "4. Form Follow Up diupdate sesuai perubahan kedatangan sparepart dan kedatangan konsumen\n" +
                        "5. Form Follow Up ditandatangan oleh PIC Parts dan Kabeng setiap bulannya (hard copy) atau diemailkan (softcopy) kepada PIC Hotline MD setiap akhir bulan. Jika berupa portal harus dapat diakses oleh MD\n ",
                0))
            nosAudit.add(NosAudit(
                "Parts Business Infrastructure ",
                "Buku Harga Eceran Tertinggi (Pricelist) dan update HET terakhir \n ",
                "1. Buku Tersedia, versi cetakan paling akhir\n" +
                        "2. Memiliki harga Parts , Oil , Accessories dan Apparel sesuai dengan update terakhir (mutasi terupdate via softfile, portal atau hard copy)\n ",
                0))

            nosAudit.add(NosAudit(
                "Parts Business Infrastructure ",
                "Computer & software parts \"in- out- stock location- price\"",
                   "1. Aplikasi mampu menampilkan pergerakan dan stok parts (stok parts di komputer harus sama dengan stok parts aktual di gudang parts)\n" +
                           "2. Mampu menampilkan harga terupdate sesuai Harga Eceran Tertinggi dari AHM\n" +
                           "3. Mampu menunjukan lokasi parts di gudang",
                0))

            nosAudit.add(NosAudit(
                "Parts Business Infrastructure ",
                "Computer & software parts \"in- out- stock location- price\"",
                "1. Jumlah komputer minimum, disesuaikan dengan jumlah PIC Part yang ada (misal PIC Part ada 2 maka jumlah komputer yang disediakan = 2)",
                0))

            nosAudit.add(NosAudit(
                "Parts Business Infrastructure ",
                "SIM  (standard item minimum) Parts",
                "Lengkap 95% secara items dan quantity berdasarkan pengecekan stok dealer aktual dengan data SIM Parts terkini",
                0))

            nosAudit.add(NosAudit(
                "Parts Business Infrastructure ",
                "Form HGA dan Apparel (Khusus Regular Dealer)",
                "1. Terdapat Form HGA dan Apparel di Dealer (Bengkel atau tempat Parts Counter / PIC HGA dan Apparel)\n" +
                        "2. Form terisi dengan benar sesuai Order dan Data konsumen\n" +
                        "3. Terdapat rekap Form HGA dan Apparel di bulan-bulan sebelumnya (min. 3 bulan ke belakang)",
                0))

            nosAudit.add(NosAudit(
                "Parts Business Infrastructure ",
                "Parts Catalog",
                "1. Terdapat buku parts catalog lengkap dan  terupdate.  \n" +
                        "2. Buku parts catalog ini berupa hard copy ",
                0))

            nosAudit.add(NosAudit(
                "Parts Warehouse Standard",
                "Memiliki Gudang Khusus Penyimpanan HGP, AHM Oil, HGA dan apparels \n",
                "1. Gudang hanya untuk menyimpan produk HGP, AHM Oil, HGA dan apparels \n" +
                        "2. Khusus ≥ 4 ruko wajib terdapat Glass warehouse lt 1.\n" +
                        "3. Rak Sesuai dengan standard PT AHM\n" +
                        "4. Penerangan yang baik dengan Lokasi lampu penerangan gudang di setiap gang sesuai sumbu gang\n" +
                        "5. Tertata rapi, tidak terdapat produk yang menghalangi gang atau keluar masuk part\n",
                0))

            nosAudit.add(NosAudit(
                "Parts Warehouse Standard",
                "binbox dan bin tag",
                "1. Sesuai dengan standard PT AHM\n" +
                        "2. Isi bin box dengan nama di tag bin box nya harus sesuai \n" +
                        "3. Tertata rapi  (semua bin box harus ada label bin tag, sejajar dan memiliki warna yang seragam)\n" +
                        "4. Binbox gudang kaca/ terlihat konsumen wajib menggunakan impra merah (Binbox gudang lt.2 atau tidak terlihat konsumen boleh menggunakan bahan karton)\n",
                0))

            nosAudit.add(NosAudit(
                "Parts Warehouse Standard",
                "Kode Lokasi dan Sub Lokasi",
                "Terdapat kode lokasi dan sublokasi yang jelas , terstruktur dan sinkron (sama) dengan yang tercatat di sistem stok (wajib dilakukan sampling check)\n",
                0))

            nosAudit.add(NosAudit(
                "Parts Warehouse Standard",
                "Baris Rak Khusus Hotline Order",
                "1. Terdapat 1 baris pada rak sparepart yang dikhususkan untuk meletakan sparepart Hotline Order.\n" +
                        "2. Terdapat sublokasi bertulisakan \"HOTLINE\" pada baris rak terkait\n" +
                        "3. Isi dari rak Hotline adalah sparepart Hotline Konsumen dan dapat dibuktikan melalui data pemesanan/order Hotline Konsumen",
                0))


            nosAudit.add(NosAudit(
                "NOS New Normal",
                "Rekap Harian Suhu Tubuh Karyawan",
                "1. Terletak di Ruang Kepala Cabang\n" +
                        "2. Diupdate setiap hari untuk seluruh karyawan dealer (Showroom + AHASS)\n" +
                        "\n" +
                        "*Untuk dealer H123, penilaian disamakan dengan H1 Premises",
                0))


            nosAudit.add(NosAudit(
                "NOS New Normal",
                "Daftar Suhu Tubuh Harian AHASS People\n" +
                        "Nice to have",
                "Terletak pada sekitar area pintu masuk AHASS dan terlihat oleh konsumen",
                0))

            return nosAudit
        }

//    fun generateDataH23Premises(): List<NosAudit> {
//
//        val nosAudit = ArrayList<NosAudit>()
//
//        nosAudit.add(NosAudit("Booking Service  ",
//            "Booking service board  ",
//            "Menambahkan logo aplikasi di booking service board tersebut\n" +
//                    "\n" +
//                    " Dalam konten minimal terdapat informasi Sumber booking\n" +
//                    "\n" +
//                    "Kabeng juga menuliskan data booking servis via aplikasi disini\n" +
//                    "\n" +
//                    "*Nilai EXIST NOT GOOD apabila sudah mempunyai booking service board, namun tidak ada logo aplikasi atau informasi sumber booking\n" +
//                    "\n" +
//                    "*selected MD only", 0))
//
//        nosAudit.add(NosAudit("Front desk area ",
//            "Poster Apps  ",
//            "Hanya perlu ada di dealer 3 in one (H123)\n" +
//                    "\n" +
//                    "1. Poster dicetak sesuai dengan ukuran (A2) menggunakan bahan \"Artcarton270\"\n" +
//                    "2. Poster diletakkan di papan pengumuman bengkel atau area Front Desk", 0))
//
//        nosAudit.add(NosAudit("Ruang tunggu  ",
//            "Roll / X  Banner 60cm x 160cm ",
//            "Roll atau X-Banner Apps terpasang dengan baik di ruang tunggu bengkel\n" +
//                    "\n" +
//                    "*kondisi di dalam ruangan, tidak rusak, bersih, dan terlihat oleh konsumen\n" +
//                    "*penilaian ditekankan lebih ke tujuan item ini untuk meningkatkan awareness customer\n" +
//                    "\n" +
//                    "*selected MD only", 0))
//
//        return nosAudit
//
//    }
//
//
//    fun generateDataH23Process(): List<NosAudit> {
//
//        val nosAudit = ArrayList<NosAudit>()
//
//             nosAudit.add(NosAudit("Front Desk Officer | Layanan Booking Servis (by call)",
//            "Data Booking Servis",
//            "- Menawarkan booking service selanjutnya melalui Aplikasi MotorkuX (HSO)/ Daya Auto (DAM)/ Brompit (MPM)/ Wanda (WMS)\n" +
//                    "\n" +
//                    "*selected MD only", 0))
//
//        nosAudit.add(NosAudit("Front Desk Officer | Layanan Booking Servis (by call)",
//            "Data Booking Servis",
//            "- Mencatat data konsumen booking via Aplikasi MotorkuX (HSO)/ Daya Auto (DAM)/ Brompit (MPM)/ Wanda (WMS) ke form booking service\n", 0))
//
//        nosAudit.add(NosAudit("Front Desk Officer | Penerimaan Motor",
//            "Check-In ",
//            "- melakukan check-in di web console / DMS\n" +
//                    "- menukarkan KPB Digital atau voucher\n" +
//                    "- memberikan catatan biaya potongan discount voucher Aplikasi di form PKB\n" +
//                    "\n" +
//                    "*selected MD only", 0))
//
//        nosAudit.add(NosAudit("Front Desk Officer | Pekerjaan selesai ",
//            "Check-Out ",
//            "- melakukan check-out di web console / DMS\n" +
//                    "- mengingatkan konsumen untuk booking untuk servis selanjutnya dengan aplikasi\n" +
//                    "- Mendorong konsumen untuk mengumpulkan poin Hepigo yang dapat ditukar dengan bermacam-macam voucher\n" +
//                    "\n" +
//                    "*selected MD only", 0))
//
//        nosAudit.add(NosAudit("Admin CRM H23 | Konsumen menerima service reminder ",
//            "Reminder Servis ",
//            "- SMS / WA Blast H-30 sebelum servis dan menginfokan ke konsumen untuk booking dan klaim KPB via Aplikasi MotorkuX (HSO)/ Daya Auto (DAM)/ Brompit (MPM)/ Wanda (WMS)\n" +
//                    "\n" +
//                    "*selected MD only", 0))
//
//        nosAudit.add(NosAudit("Admin CRM H23 | Konsumen menerima service reminder ",
//            "Reminder Servis ",
//            "- SMS / WA Blast H-14 sebelum servis dan menginfokan ke konsumen untuk booking dan klaim KPB via Aplikasi MotorkuX (HSO)/ Daya Auto (DAM)/ Brompit (MPM)/ Wanda (WMS)\n" +
//                    "\n" +
//                    "*selected MD only", 0))
//
//        nosAudit.add(NosAudit("Admin CRM H23 | Konsumen menerima follow up setelah servis ",
//            "List konsumen yang di-follow up",
//            "- Mengingatkan konsumen untuk booking dengan Aplikasi MotorkuX (HSO)/ Daya Auto (DAM)/ Brompit (MPM)/ Wanda (WMS) untuk servis selanjutnya\n" +
//                    "- Mendorong konsumen untuk mengumpulkan poin Hepigo yang dapat ditukar dengan bermacam-macam voucher\n" +
//                    "\n" +
//                    "*selected MD only", 0))
//
//        nosAudit.add(NosAudit("Servis Advisor | Konsumen bertemu dengan Servis Advisor ",
//            "Penerimaan booking servis ",
//            "BOOKING APLIKASI\n" +
//                    "- Meminta konsumen memperlihatkan bukti booking melalui aplikasi.\n" +
//                    "- Memastikan nama, tanggal, jam, dan lokasi servis sesuai dengan yang ada di webconsole. Jika sudah sesuai, maka klik check - in di webconsole.\n" +
//                    "- Meminta konsumen untuk konfirmasi check-in di aplikasi \n" +
//                    "\n" +
//                    "- Pastikan di aplikasi konsumen, apakah konsumen memiliki KPB digital/ voucher promo (servis atau parts)\n" +
//                    "- (Jika punya voucher) Swipe e-voucher pada aplikasi dan masukkan kode ahass. Dan tulis pada form PKB jika ada penggunaan voucher Rp 20.000.\n" +
//                    "- Bila memiliki KPB Digital, masukan KM motor konsumen dan ambil foto speedometer. \n" +
//                    "\n" +
//                    "*Selected MD only\n" +
//                    "\n", 0))
//
//        nosAudit.add(NosAudit("Servis Advisor  ",
//            "Konfirmasi terakhir oleh SA ",
//            "- Melakukan check-out di web console / DMS\n" +
//                    "- Meminta konsumen untuk memberikan rating di aplikasi \n" +
//                    "- Mengingatkan fitur-fitur Aplikasi MotorkuX (HSO)/ Daya Auto (DAM)/ Brompit (MPM)/ Wanda (WMS) lainnya dan value yang bisa didapatkan konsumen\n" +
//                    "- Mengingatkan konsumen untuk mengecek voucher di notifikasi\n" +
//                    "- Memberi tahu konsumen bahwa setelah servis dapat 15 poin Hepigo\n" +
//                    "- Mendorong konsumen untuk mengumpulkan poin Hepigo yang dapat ditukar dengan bermacam-macam voucher\n" +
//                    "\n" +
//                    "*selected MD only\n", 0))
//
//        nosAudit.add(NosAudit("Servis Advisor  ",
//            "Konfirmasi terakhir oleh SA ",
//            "- Reminder kepada konsumen untuk download, registrasi, dan booking service melalui Aplikasi MotorkuX (HSO)/ Daya Auto (DAM)/ Brompit (MPM)/ Wanda (WMS)\n" +
//                    "\n" +
//                    "*selected MD only", 0))
//
//        nosAudit.add(NosAudit("Cashier | Apabila exin baru, kasir H2 tidak digabung dengan kasir H1, semua nilai proses kasir = 0 ",
//            "Detail pembayaran ",
//            "BOOKING LEWAT APLIKASI\n" +
//                    "Menjelaskan detail pembayaran (Biaya Jasa Bengkel, Biaya Spare Part, dan potongan biaya atas penggunaan voucher dari aplikasi)\n" +
//                    "\n" +
//                    "*selected MD only", 0))
//
//        nosAudit.add(NosAudit("Cashier | Apabila exin baru, kasir H2 tidak digabung dengan kasir H1, semua nilai proses kasir = 0 ",
//            "Selesai pembayaran ",
//            "- Jika konsumen Servis lewat aplikasi tawarkan untuk melakukan booking selanjutnya dan tawarkan promo aplikasi yang sedang berjalan (jika ada)\n" +
//                    "\n" +
//                    "- Mengenalkan Asisten Sales Digital (Dealer Activation) kepada konsumen\n" +
//                    "\n" +
//                    "*Selected MD only\n", 0))
//
//        nosAudit.add(NosAudit("PIC HGA & Apparel | Pembelian langsung tanpa servis",
//            "Menanyakan/menggali kebutuhan konsumen  ",
//            "- Tawarkan konsumen untuk mendownload Aplikasi MotorkuX (HSO)/ Daya Auto (DAM)/ Brompit (MPM)/ Wanda (WMS) untuk melihat update-an produk HGP, AHM OIL, DAN HGA atau apparel yang tersedia\n" +
//                    "\n" +
//                    "- Mengecek leads H3 yang terdaftar di Web Console \n" +
//                    "- Follow up konsumen dari leads H3 di Web Console\n" +
//                    "\n" +
//                    "- Jika barang yang diorder lewat Aplikasi tidak tersedia, maka PIC Parts menawarkan kepada konsumen apakah tetap diorder melalui hotline order atau batal\n" +
//                    "- Bila mau diteruskan, maka PIC Parts melakukan proses hotline order seperti biasa, dan menginformasikan kepada konsumen cara tracking melalui aplikasi\n" +
//                    "- Nomor yang dapat digunakan untuk tracking adalah nomor mesin/nomor PO\n" +
//                    "\n" +
//                    "*Selected MD only\n" +
//                    "\n", 0))
//
//
//        return nosAudit
//
//    }

}