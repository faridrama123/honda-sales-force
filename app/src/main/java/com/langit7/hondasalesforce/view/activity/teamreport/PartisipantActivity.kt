package com.langit7.hondasalesforce.view.activity.teamreport

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import com.glide.slider.library.SliderTypes.DefaultSliderView
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.article
import com.langit7.hondasalesforce.model.baseresponse
import com.langit7.hondasalesforce.model.teamreport.frequent
import com.langit7.hondasalesforce.model.teamreport.quizawal
import com.langit7.hondasalesforce.model.teamreport.quizawal_detail

import com.langit7.hondasalesforce.presenter.logic.MainPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.activity.BaseActivity
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.teamreport_partisipant_activity.*
import java.text.SimpleDateFormat
import java.util.*

class PartisipantActivity : BaseActivity() {
    lateinit var presenter: MainPresenter
    var listData = ArrayList<quizawal>()

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teamreport_partisipant_activity)
        tactionbartitle.text = getString(R.string.mTPartisipant)
        imgback.setOnClickListener {
            onBackPressed()
        }


        val txtBulan1 = calendarData(0)
        val txtBulan2 = calendarData(-1)
        val txtBulan3 = calendarData(-2)

        llPKBulan1.setOnClickListener {
            val monthyear = txtBulan1.split(" ").toTypedArray()
            val month = monthyear[0].substring(0,3)
            val year = monthyear[1]
            goToPeserta("1","1", month, year, "1")
        }
        llPKBulan2.setOnClickListener {
            val monthyear = txtBulan2.split(" ").toTypedArray()
            val month = monthyear[0].substring(0,3)
            val year = monthyear[1]
            goToPeserta("1","1", month, year, "1")
        }
        llPKBulan3.setOnClickListener {
            val monthyear = txtBulan3.split(" ").toTypedArray()
            val month = monthyear[0].substring(0,3)
            val year = monthyear[1]
            goToPeserta("1","1", month, year, "1")
        }

        llNOSBulan1.setOnClickListener {
            val monthyear = txtBulan1.split(" ").toTypedArray()
            val month = monthyear[0].substring(0,3)
            val year = monthyear[1]
            goToPeserta("2","1", month, year, "1")
        }
        llNOSBulan2.setOnClickListener {
            val monthyear = txtBulan2.split(" ").toTypedArray()
            val month = monthyear[0].substring(0,3)
            val year = monthyear[1]
            goToPeserta("2","1", month, year, "1")
        }
        llNOSBulan3.setOnClickListener {
            val monthyear = txtBulan3.split(" ").toTypedArray()
            val month = monthyear[0].substring(0,3)
            val year = monthyear[1]
            goToPeserta("2","1", month, year, "1")
        }
        presenter = MainPresenter(this, APIServices)

    }

    fun getData(){
        showLoadingDialog()

        val usr= function.getUser(this)
        val mainDealer = usr!!.profileUser.dealer.mainDealer

        presenter.getQuizAwal(mainDealer, object :
            ObjectResponseInterface<baseresponse<List<quizawal>>> {
            override fun onSuccess(res: baseresponse<List<quizawal>>) {
                dismisLoadingDialog()
                listData = res.data as ArrayList<quizawal>
                for (i in 0 until listData.size) {
                    val title = listData[i].title;
                    val semester = listData[i].semester;
                    when (i) {
                        0 -> {
                            tvBulan1.text = title
                            val quizs = listData[i].quiz;
                            listPKNOS(quizs, 1)
                        }
                        1 -> {
                            tvBulan2.text = title
                            val quizs = listData[i].quiz;
                            listPKNOS(quizs, 2)
                        }
                        2 -> {
                            tvBulan3.text = title
                            val quizs = listData[i].quiz;
                            listPKNOS(quizs, 3)
                        }
                        3 -> {
                            tvSemesterVerifikasi.text = semester
                        }

                    }
                }
            }
            override fun onFailed(msg: String) {
                dismisLoadingDialog()
                Toast(msg)
            }
        })
    }

    fun listPKNOS(data: List<quizawal_detail>, iHeader: Int){
        for (i in data.indices) {
            val quizName = data.get(i).quizName;
            val desc = data.get(i).description + data.get(i).startDate + " s/d " + data.get(i).endDate
            if(i == 0){
                when (iHeader) {
                    1 -> {
                        tv_titlePK1.text = quizName
                        tvPKTanggalBulan1.text = desc;
                    }
                    2 -> {
                        tv_titlePK2.text = quizName
                        tvPKTanggalBulan2.text = desc;
                    }
                    3 -> {
                        tv_titlePK3.text = quizName
                        tvPKTanggalBulan3.text = desc;
                    }
                }
            }else{
                when (iHeader) {
                    1 -> {
                        tv_titleNOS1.text = quizName
                        tvNOSTanggalBulan1.text = desc;
                    }
                    2 -> {
                        tv_titleNOS2.text = quizName
                        tvNOSTanggalBulan2.text = desc;
                    }
                    3 -> {
                        tv_titleNOS3.text = quizName
                        tvNOSTanggalBulan3.text = desc;
                    }
                }
            }
        }
    }

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    @SuppressLint("SimpleDateFormat")
    fun calendarData(format: Int) : String {
        val cal = Calendar.getInstance()
        cal.set(cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH))
        cal.add(Calendar.MONTH, format)
        return SimpleDateFormat("MMMM yyyy").format(cal.time)
    }

    fun goToPeserta(category: String,
                    category_position: String,
                    month: String,
                    year: String,
                    is_participant: String) {
        var ii= Intent(this, PartisipantDetailActivity::class.java)
        ii.putExtra("category", category)
        ii.putExtra("category_position", category_position)
        ii.putExtra("month", month)
        ii.putExtra("year", year)
        ii.putExtra("title", "Partisipant Kuis")
        ii.putExtra("is_participant", is_participant)
        ii.putExtra("isScore", 0)
        this.startActivity(ii)
    }

    override fun onResume() {
        super.onResume()
        getData();
    }
}