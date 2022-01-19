package com.langit7.hondasalesforce.view.activity.teamreport

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.databinding.ActivityListFrequentBinding
import com.langit7.hondasalesforce.model.baseresponse
import com.langit7.hondasalesforce.model.teamreport.FrequentUser

import com.langit7.hondasalesforce.presenter.adapter.teamreport.AdapterFrequentUsr
import com.langit7.hondasalesforce.presenter.logic.MainPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.activity.BaseActivity
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.teamreport_kuis_peserta_activity.*

class ListFrequentActivity : BaseActivity() {
    lateinit var presenter: MainPresenter
    private lateinit var AdapterFrequentUsr : AdapterFrequentUsr
    private lateinit var binding: ActivityListFrequentBinding



    var category = ""
    var mainDealer = ""
    var date = ""
    var dataTemp = ArrayList<FrequentUser>()
    var lsFrequentUser = ArrayList<FrequentUser>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListFrequentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AdapterFrequentUsr = AdapterFrequentUsr()


        category = intent.getStringExtra("category").toString()
        mainDealer = intent.getStringExtra("mainDealer").toString()
        date = intent.getStringExtra("date").toString()
        presenter= MainPresenter(this,APIServices)






    }

    fun getData(){
        showLoadingDialog()
        val usr= function.getUser(this)
        val mainDealer = usr!!.profileUser.dealer.mainDealer



        presenter.getFrequentUser("1", 10, 1, mainDealer, date, category,
            object : ObjectResponseInterface<baseresponse<List<FrequentUser>>> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onSuccess(res: baseresponse<List<FrequentUser>>) {
                    dismisLoadingDialog()
                    dataTemp = res.data as ArrayList<FrequentUser>
                    Log.d(" dataTemp adalah : ", dataTemp.toString())

                    AdapterFrequentUsr.setData(res.data as ArrayList<FrequentUser>)
                    AdapterFrequentUsr.notifyDataSetChanged()

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
            adapter = AdapterFrequentUsr
        }


    }

    @SuppressLint("NotifyDataSetChanged")
    fun sortBy(){
        lsFrequentUser.clear()
        lsFrequentUser = dataTemp;
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
     //   adp.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        getData()
    }
}