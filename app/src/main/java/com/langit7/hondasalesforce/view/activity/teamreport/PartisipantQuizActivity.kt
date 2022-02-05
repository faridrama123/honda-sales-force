package com.langit7.hondasalesforce.view.activity.teamreport

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.databinding.ActivityListFrequentBinding
import com.langit7.hondasalesforce.databinding.ActivityPartisipantQuizBinding
import com.langit7.hondasalesforce.model.baseresponse
import com.langit7.hondasalesforce.model.teamreport.FrequentUser
import com.langit7.hondasalesforce.model.teamreport.PartisipantQuiz
import com.langit7.hondasalesforce.presenter.adapter.teamreport.AdapterFrequentUsr
import com.langit7.hondasalesforce.presenter.adapter.teamreport.AdapterParticipantKuis
import com.langit7.hondasalesforce.presenter.logic.MainPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.activity.BaseActivity
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.teamreport_kuis_peserta_activity.*

class PartisipantQuizActivity : BaseActivity() {
    lateinit var presenter: MainPresenter
    private lateinit var AdapterParticipantKuis : AdapterParticipantKuis
    private lateinit var binding: ActivityPartisipantQuizBinding

    var category = ""
    var category_position = ""
    var month = ""
    var year = ""
    var title = ""
    var is_participant = ""



    var mainDealer = ""
    var dataTemp = ArrayList<PartisipantQuiz>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPartisipantQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AdapterParticipantKuis = AdapterParticipantKuis()


        category = intent.getStringExtra("category").toString()
        category_position = intent.getStringExtra("category_position").toString()
        month = intent.getStringExtra("month").toString()
        year = intent.getStringExtra("year").toString()
        title = intent.getStringExtra("title").toString()
        is_participant = intent.getStringExtra("is_participant").toString()
        mainDealer = intent.getStringExtra("mainDealer").toString()
        presenter= MainPresenter(this,APIServices)




        imgback.setOnClickListener {
            onBackPressed()
        }

        if(is_participant == "1"){
            tactionbartitle.text = "Mengikuti Kuis"
        }else{
            tactionbartitle.text = "Tidak Mengikuti Kuis"

        }



    }

    fun getData(){
        showLoadingDialog()
        val usr= function.getUser(this)
        val mainDealer = usr!!.profileUser.dealer.mainDealer


        presenter.getPartisipantQuizAndMonitor("1", 1000, 1, mainDealer,category, month, year, category_position,
            is_participant,
            object : ObjectResponseInterface<baseresponse<List<PartisipantQuiz>>> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onSuccess(res: baseresponse<List<PartisipantQuiz>>) {
                    dismisLoadingDialog()
                    dataTemp = res.data as ArrayList<PartisipantQuiz>
                    Log.d(" dataTemp adalah : ", dataTemp.toString())

                    AdapterParticipantKuis.setData(res.data as ArrayList<PartisipantQuiz>)
                    AdapterParticipantKuis.notifyDataSetChanged()

                    if ( dataTemp.isEmpty())  tnodate.visibility = View.VISIBLE;
                }
                override fun onFailed(msg: String) {
                    dismisLoadingDialog()
                    Toast(msg)
                }
            })



        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = AdapterParticipantKuis
        }


    }


    override fun onResume() {
        super.onResume()
        getData()
    }
}