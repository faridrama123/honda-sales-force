package com.langit7.hondasalesforce.view.activity.teamreport

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.baseresponse
import com.langit7.hondasalesforce.model.teamreport.partisipant
import com.langit7.hondasalesforce.presenter.adapter.teamreport.AdapterPartisipanKuis
import com.langit7.hondasalesforce.presenter.logic.MainPresenter

import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.activity.BaseActivity
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.teamreport_kuis_activity.*
import kotlinx.android.synthetic.main.teamreport_kuis_peserta_activity.*

class KuisPesertaActivity : BaseActivity() {
    var lsPartisipant=ArrayList<partisipant>()

    var dataTemp = ArrayList<partisipant>()
    lateinit var presenter: MainPresenter
    lateinit var adp: AdapterPartisipanKuis

    var category = ""
    var category_position = ""
    var month = ""
    var year = ""
    var is_participant = ""
    var title = ""
    var totalPerPage = 100;
    var page = 1;
    var isScore = 1;
    var isDesc = 1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teamreport_kuis_peserta_activity)
        title = intent.getStringExtra("title").toString()
        category = intent.getStringExtra("category").toString()
        category_position = intent.getStringExtra("category_position").toString()
        month = intent.getStringExtra("month").toString()
        year = intent.getStringExtra("year").toString()
        is_participant = intent.getStringExtra("is_participant").toString()
        isScore = intent.getIntExtra("isScore", 0)

        if(isScore == 1){
            headerScore.visibility = View.VISIBLE
        }else{
            headerScore.visibility = View.GONE
        }

        tnodate.visibility = View.GONE;
        tactionbartitle.text = title
        imgback.setOnClickListener {
            onBackPressed()
        }
        headerScore.setOnClickListener {
            sortBy()
        }

        presenter= MainPresenter(this,APIServices)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun sortBy(){
        lsPartisipant.clear()
        if(isDesc == 1){
            val  sortedByPrice = dataTemp.sortedByDescending{it.score}
            lsPartisipant.addAll(sortedByPrice)
            isDesc = 0
        }else{
            val sortedByPrice = dataTemp.sortedBy{it.score}
            lsPartisipant.addAll(sortedByPrice)
            isDesc = 1
        }
        if(dataTemp.indices.count() > 0){
            tnodate.visibility = View.GONE;
        }else{
            tnodate.visibility = View.VISIBLE;
        }
        adp.notifyDataSetChanged()
    }

    fun getData(){
        showLoadingDialog()
        val usr= function.getUser(this)
        val mainDealer = usr!!.profileUser.dealer.mainDealer

        presenter.getPartisipant("1", totalPerPage, page, mainDealer, category, month, year, category_position, is_participant,
            object : ObjectResponseInterface<baseresponse<List<partisipant>>> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onSuccess(res: baseresponse<List<partisipant>>) {
                dismisLoadingDialog()
                dataTemp = res.data as ArrayList<partisipant>
                sortBy()
            }
            override fun onFailed(msg: String) {
                dismisLoadingDialog()
                Toast(msg)
            }
        })

        adp= AdapterPartisipanKuis(isScore, lsPartisipant,ctx)
        recycler_view.layoutManager=LinearLayoutManager(ctx)
        recycler_view.adapter=adp
    }

    override fun onResume() {
        super.onResume()
        getData()
    }
}
