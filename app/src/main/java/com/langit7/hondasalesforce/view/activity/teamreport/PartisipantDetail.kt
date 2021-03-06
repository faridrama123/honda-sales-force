package com.langit7.hondasalesforce.view.activity.teamreport

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.databinding.ActivityPartisipantDetailBinding
import com.langit7.hondasalesforce.model.baseresponse
import com.langit7.hondasalesforce.model.teamreport.PartisipantDetail
import com.langit7.hondasalesforce.model.teamreport.partisipant
import com.langit7.hondasalesforce.presenter.adapter.teamreport.AdapterPartisipanKuis
import com.langit7.hondasalesforce.presenter.logic.MainPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.activity.BaseActivity
import io.realm.log.RealmLog.info
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_partisipant_detail.*
import kotlinx.android.synthetic.main.activity_partisipant_detail.view.*
import kotlinx.android.synthetic.main.teamreport_frequent_activity.*
import kotlinx.android.synthetic.main.teamreport_kuis_peserta_activity.*

class PartisipantDetailActivity : BaseActivity() {

    var category = ""
    var month = ""
    var year = ""
    var title = ""
    private lateinit var binding: ActivityPartisipantDetailBinding
    lateinit var presenter: MainPresenter
    var kuisVerifikasiSemester = ""




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPartisipantDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter= MainPresenter(this,APIServices)


        title = intent.getStringExtra("title").toString()
        category = intent.getStringExtra("category").toString()
        month = intent.getStringExtra("month").toString()
        year = intent.getStringExtra("year").toString()

        if (category == "3") {
            kuisVerifikasiSemester = intent.getStringExtra("semester").toString()
            month = ""
        }




//        if ( category=="3") {
//            binding.categorySobat.visibility = View.GONE
//            binding.linearSobatIkt    .visibility = View.GONE
//            binding.linearSobatNoikt.visibility = View.GONE
//
//        }

        Log.d(" month adalah : ", month.toString())
        Log.d(" year adalah : ", year.toString())



        tactionbartitle.text = title

    }


    fun intentdetailList () {


        val usr= function.getUser(this)
        val mainDealer = usr!!.profileUser.dealer.mainDealer


        regularIktClick.setOnClickListener {
            goToPeserta(category,"1", month, year, "1")

        }
        regularNoIktClick.setOnClickListener {
            goToPeserta(category,"1", month, year, "2")

        }

        wsp_ikt_click.setOnClickListener {
            goToPeserta(category,"2", month, year, "1")

        }
        wsp_noikt_click.setOnClickListener {
            goToPeserta(category,"2", month, year, "2")

        }

        bwsp_ikt_click.setOnClickListener {
            goToPeserta(category,"3", month, year, "1")

        }
        bwsp_noikt_click.setOnClickListener {
            goToPeserta(category,"3", month, year, "2")

        }

        sobat_ikt_click.setOnClickListener {
            goToPeserta(category,"4", month, year, "1")

        }
        sobat_noikt_click.setOnClickListener {
            goToPeserta(category,"4", month, year, "2")

        }



    }

    fun goToPeserta(category: String,
                    category_position: String,
                    month: String,
                    year: String,
                    is_participant: String) {
        var ii= Intent(this, PartisipantQuizActivity::class.java)
        ii.putExtra("category", category)
        ii.putExtra("category_position", category_position)
        ii.putExtra("month", month)
        ii.putExtra("year", year)
        ii.putExtra("title", "Partisipant Kuis")
        ii.putExtra("is_participant", is_participant)
        ii.putExtra("isScore", 0)
        ii.putExtra("semester", kuisVerifikasiSemester)

        this.startActivity(ii)
    }




    fun getData(){
       showLoadingDialog()
        val usr= function.getUser(this)
        val mainDealer = usr!!.profileUser.dealer.mainDealer

        Log.d("mainDealer", mainDealer);
        Log.d("category", category);
        Log.d("month", month);
        Log.d("year", year);




        presenter.getPartisipantDetail("1", mainDealer, category, month, year, kuisVerifikasiSemester,
            object : ObjectResponseInterface<baseresponse<PartisipantDetail>> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onSuccess(res: baseresponse<PartisipantDetail>) {
                    dismisLoadingDialog()

                    var dataTemp = res.data as PartisipantDetail



                    if ( dataTemp?.descIsShowRegular == "No") {
                            binding.ctitle1.visibility = View.GONE
                            binding.csubtitle1.visibility = View.GONE
                            binding.linearRegularIkt    .visibility = View.GONE
                            binding.linearRegularNoIkt.visibility = View.GONE
                    }

                    if ( dataTemp?.descIsShowWsp.toString() == "No") {
                        binding.ctitle2.visibility = View.GONE
                        binding.linearWspIkt.visibility = View.GONE
                        binding.linearWspNoikt.visibility = View.GONE
                    }

                    if ( dataTemp?.descIsShowBwsp.toString() == "No") {
                        binding.ctitle3.visibility = View.GONE
                        binding.linearBwspIkt    .visibility = View.GONE
                        binding.linearBwspNoikt.visibility = View.GONE
                    }
                    if ( dataTemp?.descIsShowSobat.toString() == "No") {
                        binding.ctitle4.visibility = View.GONE
                        binding.linearSobatIkt    .visibility = View.GONE
                        binding.linearSobatNoikt.visibility = View.GONE
                    }




                    binding.title1.text = dataTemp?.labelRegular.toString()
                    binding.subtitle1.text = dataTemp?.labelRegularSecond  .toString()


                    binding.title2.text = dataTemp?.labelWsp.toString()
                    binding.title3.text = dataTemp?.labelBwsp.toString()
                    binding.title4.text = dataTemp?.labelSobat.toString()





                    binding.startdate.text = dataTemp?.startDate.toString()
                    binding.enddate.text = dataTemp?.endDate.toString()


                    binding.regularIktCount.text = dataTemp?.totalParticipantRegular.toString()
                    binding.regularNoIktCount.text = dataTemp?.totalNonParticipantRegular.toString()


                    binding.bwspIktCount.text = dataTemp?.totalParticipantBwsp.toString()
                    binding.bwspNoiktCount.text = dataTemp?.totalNonParticipantBwsp.toString()

                    binding.wspIktCount.text = dataTemp?.totalParticipantWsp.toString()
                    binding.wspNoiktCount.text = dataTemp?.totalNonParticipantWsp.toString()

                    binding.sobatIktCount.text = dataTemp?.totalParticipantSobat.toString()
                    binding.sobatNoiktCount.text = dataTemp?.totalNonParticipantSobat.toString()



                }
                override fun onFailed(msg: String) {
                    dismisLoadingDialog()
                    Toast(msg)
                }
            })


    }
    override fun onResume() {
        super.onResume()
        getData()
        intentdetailList()
    }


}