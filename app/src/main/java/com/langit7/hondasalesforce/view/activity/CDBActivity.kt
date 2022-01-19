package com.langit7.hondasalesforce.view.activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.history
import com.langit7.hondasalesforce.presenter.logic.CDBPresenter
import com.langit7.hondasalesforce.presenter.logic.MainPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_cdb.*
import java.util.*

class CDBActivity : BaseActivity() {



    lateinit var presenter:CDBPresenter

    var cold=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cdb)

        tactionbartitle.setText(getString(R.string.prospect))
        imgback.setOnClickListener {
            onBackPressed()
        }


        presenter= CDBPresenter(this)




        btnsend.setOnClickListener {

            var status=""
            if(rbcold.isChecked)
                status="0"
            else if(rbhot.isChecked)
                status="1"
            else
                status="2"



            presenter.addCDB(

                etnma.text.toString(),
                etalmat.text.toString(),
                "08"+etnohp.text.toString(),
                status,
                ettype.text.toString(),
                etemail.text.toString(),
                etdate.text.toString(),
                ettime.text.toString(),
                object : ObjectResponseInterface<String>{
                    override fun onSuccess(res: String) {

                        var pp= MainPresenter(ctx as BaseActivity,APIServices)
                        pp.saveCounter("7", function.getToday(),object : ObjectResponseInterface<history> {
                            override fun onSuccess(res: history) {
                            }

                            override fun onFailed(msg: String) {
                            }

                        })

                        val ii=Intent(ctx,CDBResultActivity::class.java)
                        startActivity(ii)
                        finish()
                    }

                    override fun onFailed(msg: String) {
                        Toast(msg)
                    }


                }


            )




        }


        setupDate(etdate,imgcalendar)
        setupTime(ettime,imgtime)

        rgstatus.setOnCheckedChangeListener { radioGroup, i ->


            if(rbcold.isChecked)
                lldate.visibility=View.GONE
            else
                lldate.visibility=View.VISIBLE

        }
    }


    fun setupDate(tview: EditText, img: View){
        tview.setText(function.dateToString(Date(),"dd-MMM-yyyy"))
        tview.setOnClickListener {

            var maxdate=Calendar.getInstance()
            maxdate.add(Calendar.MONTH,6)


            var d=DatePickerDialog(ctx,object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    var date=year.toString() +"-"+function.intToStringTime(month+1)+"-"+function.intToStringTime(dayOfMonth)
                    (it as TextView).text= function.converDateFormat(date,"yyyy-MM-dd","dd-MMM-yyyy")
                }

            },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
            d.datePicker.maxDate=maxdate.timeInMillis
            d.datePicker.minDate=Calendar.getInstance().timeInMillis
            d.show()
        }
        img.setOnClickListener {
            var maxdate=Calendar.getInstance()
            maxdate.add(Calendar.MONTH,6)
            var d =DatePickerDialog(ctx,object : DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    var date=year.toString() +"-"+function.intToStringTime(month+1)+"-"+function.intToStringTime(dayOfMonth)
                    tview.setText(function.converDateFormat(date,"yyyy-MM-dd","dd-MMM-yyyy"))
                }

            },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
            d.datePicker.maxDate=maxdate.timeInMillis
            d.datePicker.minDate=Calendar.getInstance().timeInMillis
            d.show()
        }
    }

    fun setupTime(tview:TextView,img:View){
        tview.setText("00:00")
        tview.setOnClickListener {

            var t=TimePickerDialog(ctx,object : TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    var time=function.intToStringTime(hourOfDay) +":"+function.intToStringTime(minute)
                    tview.text= time
                }
            },Calendar.getInstance().get(Calendar.HOUR_OF_DAY),Calendar.getInstance().get(Calendar.MINUTE),true)
            t.show()
        }
        img.setOnClickListener {
            TimePickerDialog(ctx,object : TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    var time=function.intToStringTime(hourOfDay) +":"+function.intToStringTime(minute)
                    tview.text= time
                }
            },Calendar.getInstance().get(Calendar.HOUR_OF_DAY),Calendar.getInstance().get(Calendar.MINUTE),true).show()
        }
    }
}

























































