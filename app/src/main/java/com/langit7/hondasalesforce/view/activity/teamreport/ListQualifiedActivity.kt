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
import com.langit7.hondasalesforce.databinding.ActivityListQualifiedBinding
import com.langit7.hondasalesforce.model.baseresponse
import com.langit7.hondasalesforce.model.teamreport.FrequentUser
import com.langit7.hondasalesforce.model.teamreport.ListParticipantQualified
import com.langit7.hondasalesforce.model.teamreport.PartisipantQuiz
import com.langit7.hondasalesforce.presenter.adapter.teamreport.AdapterFrequentUsr
import com.langit7.hondasalesforce.presenter.adapter.teamreport.AdapterQualifiedUsr
import com.langit7.hondasalesforce.presenter.logic.MainPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.activity.BaseActivity
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.teamreport_kuis_peserta_activity.*

class ListQualifiedActivity : BaseActivity() {
    lateinit var presenter: MainPresenter
   private lateinit var AdapterQualifiedUsr : AdapterQualifiedUsr
    private lateinit var binding: ActivityListQualifiedBinding



    var category = ""
    var semester = ""
    var year = ""
    var category_position = ""

    var date = ""
    var dataTemp = ArrayList<ListParticipantQualified>()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListQualifiedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AdapterQualifiedUsr = AdapterQualifiedUsr()

        category = intent.getStringExtra("category").toString()
        semester = intent.getStringExtra("semester").toString()
        year = intent.getStringExtra("year").toString()
        category_position = intent.getStringExtra("category_position").toString()


        presenter= MainPresenter(this,APIServices)

        imgback.setOnClickListener {
            onBackPressed()
        }

        if(category == "1"){
            tactionbartitle.text = "Qualified"
        }else{
            tactionbartitle.text = "Non Qualified"

        }


    }

    fun getData(){
       showLoadingDialog()
        val usr= function.getUser(this)
        val mainDealer = usr!!.profileUser.dealer.mainDealer
        presenter.getPartisipantQualified("1", 1000, 1, mainDealer, category, semester, year,category_position,
            object : ObjectResponseInterface<baseresponse<List<ListParticipantQualified>>> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onSuccess(res: baseresponse<List<ListParticipantQualified>>) {
                    dismisLoadingDialog()
                    dataTemp = res.data as ArrayList<ListParticipantQualified>
                    Log.d(" dataTemp adalah : ", dataTemp.toString())

                    AdapterQualifiedUsr.setData(res.data as ArrayList<ListParticipantQualified>)
                    AdapterQualifiedUsr.notifyDataSetChanged()

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
            adapter = AdapterQualifiedUsr
        }


    }

//    @SuppressLint("NotifyDataSetChanged")
//    fun sortBy(){
//        lsFrequentUser.clear()
//        lsFrequentUser = dataTemp;
//        if(isDesc == 1){
//            val  sortedByPrice = dataTemp.sortedByDescending{it.score}
//            lsPartisipant.addAll(sortedByPrice)
//            isDesc = 0
//        }else{
//            val sortedByPrice = dataTemp.sortedBy{it.score}
//            lsPartisipant.addAll(sortedByPrice)
//            isDesc = 1
//        }
//        if(dataTemp.indices.count() > 0){
//            tnodate.visibility = View.GONE;
//        }else{
//            tnodate.visibility = View.VISIBLE;
//        }
//           adp.notifyDataSetChanged()
//    }

    override fun onResume() {
        super.onResume()
        getData()
    }
}