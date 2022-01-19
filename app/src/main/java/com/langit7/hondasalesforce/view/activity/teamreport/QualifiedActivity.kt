package com.langit7.hondasalesforce.view.activity.teamreport

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.databinding.ActivityPartisipantDetailBinding
import com.langit7.hondasalesforce.databinding.TeamreportQualifiedActivityBinding
import com.langit7.hondasalesforce.model.baseresponse
import com.langit7.hondasalesforce.model.teamreport.MonitoringQualified
import com.langit7.hondasalesforce.model.teamreport.PartisipantDetail
import com.langit7.hondasalesforce.presenter.logic.MainPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.activity.BaseActivity
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_partisipant_detail.*
import java.text.SimpleDateFormat
import java.util.*
import android.widget.AdapterView

import android.widget.Toast

import android.widget.AdapterView.OnItemSelectedListener

import android.view.View


class QualifiedActivity : BaseActivity() {

    private lateinit var binding: TeamreportQualifiedActivityBinding
    lateinit var presenter: MainPresenter

    lateinit var semester: String
    lateinit var tahun: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = TeamreportQualifiedActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter= MainPresenter(this,APIServices)


        tactionbartitle.text = getString(com.langit7.hondasalesforce.R.string.mTQualified)
        imgback.setOnClickListener {
            onBackPressed()
        }

        val txtBulan1 = calendarData(0)
        val txtBulan2 = calendarData(-1)

        val monthyear1 = txtBulan1.split(" ").toTypedArray()
        val month1 = monthyear1[0].substring(0,3)
        val year1 = monthyear1[1]


        val monthyear2 = txtBulan2.split(" ").toTypedArray()
        val month2 = monthyear2[0].substring(0,3)
        val year2 = monthyear2[1]

        semester = "1";
        tahun = year1;



        val arrayList1 = ArrayList<String>()

        arrayList1.add("Semester 1 $year1")
        arrayList1.add("Semester 2 $year1")
        arrayList1.add("Semester 1 $year2")
        arrayList1.add("Semester 2 $year2")

        val adp = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrayList1)
        val spinner = binding.planetsSpinner as Spinner
        spinner.adapter = adp
        spinner.visibility = View.VISIBLE
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>, view: View?,
                position: Int, arg3: Long
            ) {
                val city = parent.getItemAtPosition(position).toString()
                Toast.makeText(parent.context,  city, Toast.LENGTH_LONG).show()

                if (position ==0) {
                    semester = "1";
                    tahun = year1;
                }
                if (position ==1) {
                    semester = "2";
                    tahun = year1;
                }
                if (position ==2) {
                    semester = "1";
                    tahun = year2;
                }
                if (position ==3) {
                    semester = "2";
                    tahun = year2;
                }
                getData()
            }

            override fun onNothingSelected(arg0: AdapterView<*>?) {
                // TODO Auto-generated method stub
            }
        }




    }

    fun getData(){
        showLoadingDialog()
        val usr= function.getUser(this)
        val mainDealer = usr!!.profileUser.dealer.mainDealer

        presenter.getMonitoringQualified("1", mainDealer, semester, tahun,
            object : ObjectResponseInterface<baseresponse<MonitoringQualified>> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onSuccess(res: baseresponse<MonitoringQualified>) {
                    dismisLoadingDialog()
                    var dataTemp = res.data as MonitoringQualified
                    binding.regularIktCount.text = dataTemp?.totalQualifiedRegular.toString()
                    binding.regularNoIktCount.text = dataTemp?.totalNonQualifiedRegular.toString()
                    binding.bwspIktCount.text = dataTemp?.totalQualifiedBwsp.toString()
                    binding.bwspNoiktCount.text = dataTemp?.totalNonQualifiedBwsp.toString()
                    binding.wspIktCount.text = dataTemp?.totalQualifiedWsp.toString()
                    binding.wspNoiktCount.text = dataTemp?.totalQualifiedWsp.toString()
                    binding.sobatIktCount.text = dataTemp?.totalQualifiedSobat.toString()
                    binding.sobatNoiktCount.text = dataTemp?.totalNonQualifiedSobat.toString()

                }
                override fun onFailed(msg: String) {
                    dismisLoadingDialog()
                    Toast(msg)
                }
            })


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

    fun intentdetailList () {

        val usr= function.getUser(this)
        val mainDealer = usr!!.profileUser.dealer.mainDealer


        regularIktClick.setOnClickListener {
            goToPeserta("1",semester, tahun, "1")

        }
        regularNoIktClick.setOnClickListener {
            goToPeserta("2",semester, tahun, "1")

        }

        wsp_ikt_click.setOnClickListener {
            goToPeserta("1",semester, tahun, "2")

        }
        wsp_noikt_click.setOnClickListener {
            goToPeserta("2",semester, tahun, "2")

        }


        bwsp_ikt_click.setOnClickListener {
            goToPeserta("1",semester, tahun, "3")

        }
        bwsp_noikt_click.setOnClickListener {
            goToPeserta("2",semester, tahun, "3")

        }

        sobat_ikt_click.setOnClickListener {
            goToPeserta("1",semester, tahun, "4")

        }
        sobat_noikt_click.setOnClickListener {
            goToPeserta("2",semester, tahun, "4")

        }

    }

    fun goToPeserta(category: String,
                    semester : String,
                    year : String,
                    category_position: String,
                 ) {
        var ii= Intent(this, ListQualifiedActivity::class.java)
        ii.putExtra("category", category)
        ii.putExtra("semester", semester)
        ii.putExtra("year", year)
        ii.putExtra("category_position", category_position)
        this.startActivity(ii)
    }


    override fun onResume() {
        super.onResume()
        getData()
        intentdetailList()
    }
}