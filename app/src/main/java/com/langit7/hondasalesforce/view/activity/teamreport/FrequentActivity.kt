package com.langit7.hondasalesforce.view.activity.teamreport

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.baseresponse
import com.langit7.hondasalesforce.model.teamreport.frequent
import com.langit7.hondasalesforce.presenter.logic.MainPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.activity.BaseActivity
import io.realm.log.RealmLog.info
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.teamreport_frequent_activity.*
import java.text.SimpleDateFormat
import java.util.*

class FrequentActivity : BaseActivity() {
    lateinit var presenter: MainPresenter
    var date = "";

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.teamreport_frequent_activity)
        tactionbartitle.text = getString(R.string.mTFrequent)
        imgback.setOnClickListener {
            onBackPressed()
        }

        val cal = Calendar.getInstance()
        val dateNow = Calendar.getInstance().time
        date = dateNow.toString("yyyy-MM-dd");
        val dateShow = dateNow.toString("dd/MMMM/yyyy")
        val text48Jam = dateNow.toString("dd MMMM yyyy")

        cal.set(cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH))
        cal.add(Calendar.DATE, -2);
        val minus2Day = SimpleDateFormat("dd").format(cal.time)

        tglSekarang.text = dateShow;
        jam48.text = "$minus2Day - $text48Jam";

        presenter = MainPresenter(this, APIServices)
    }


    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun intentdetailList () {

        val usr= function.getUser(this)
        val mainDealer = usr!!.profileUser.dealer.mainDealer


        l_berhasilLogin.setOnClickListener {
            Log.d("TAG", "l_berhasilLogin.setOnClickListener")
            goTo("1", mainDealer ,date)

        }
        l_tidakberhasilLogin.setOnClickListener {
            Log.d("TAG", "l_tidakberhasilLogin.setOnClickListener")
            goTo("2", mainDealer, date)

        }
        l_fu.setOnClickListener {
            Log.d("TAG", "l_fu.setOnClickListener")
            goTo("3", mainDealer, date)

        }
        l_bukanfu.setOnClickListener {
            Log.d("TAG", "l_bukanfu.setOnClickListener")
            goTo("4", mainDealer, date)
        }
    }


    fun goTo(category: String,
             mainDealer: String,
             dateShow: String,

             ) {
        val ii= Intent(this, ListFrequentActivity::class.java)
        ii.putExtra("category", category)
        ii.putExtra("mainDealer", mainDealer)
        ii.putExtra("date", dateShow)


        this.startActivity(ii)
    }

    private fun getData(){
        showLoadingDialog()
        val date = Calendar.getInstance().time
        val dateInString = date.toString("yyyy-MM-dd")

        val usr= function.getUser(this)
        val mainDealer = usr!!.profileUser.dealer.mainDealer

        presenter.getFrequent(mainDealer, dateInString, object :ObjectResponseInterface<baseresponse<frequent>>{
            override fun onSuccess(res: baseresponse<frequent>) {
                dismisLoadingDialog()
                tvBerhasilLogin.text =  res.data.totalSuccessLogin.toString();
                tvGagalLogin.text =  res.data.totalFailedLogin.toString();
                tvFU.text =  res.data.totalFu.toString();
                tvBukanFU.text =  res.data.totalNfu.toString();
            }
            override fun onFailed(msg: String) {
                dismisLoadingDialog()
                Toast(msg)
            }
        })

    }

    override fun onResume() {
        super.onResume()
        intentdetailList()
        getData();
    }
}