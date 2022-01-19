package com.langit7.hondasalesforce.Util


import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Handler
import android.text.Html
import android.widget.*
import com.bumptech.glide.Glide
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.ads
import com.langit7.hondasalesforce.model.cdb
import com.langit7.hondasalesforce.model.rating
import com.langit7.hondasalesforce.presenter.logic.MainPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.CDBDialogListener
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectListener
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.presenter.viewInterface.YesNoListener
import com.langit7.hondasalesforce.view.activity.BaseActivity
import java.util.*
import android.view.*
import com.langit7.hondasalesforce.view.activity.CustomerRecNotKnowActivity
import com.langit7.hondasalesforce.view.activity.CustomerRecKnowActivity


object DialogUtil {
    fun createCustomerProfileDialog(ctx: Activity) {
        val vg: ViewGroup? = null
        val view = ctx.layoutInflater.inflate(R.layout.dialog_customer_profile, vg)
        val dialog = Dialog(ctx,R.style.CustomerDialogSlideAnim)
        dialog.setCancelable(true)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent);
        dialog.window!!.setGravity(Gravity.BOTTOM);
        dialog.window!!.attributes.width= WindowManager.LayoutParams.MATCH_PARENT;
        dialog.window!!.attributes.horizontalMargin=0f

        var rlmain=view.findViewById<RelativeLayout>(R.id.rlmain)
        var main=view.findViewById<RelativeLayout>(R.id.main_layout)

        var tpria=view.findViewById<TextView>(R.id.tpria)
        var twanita=view.findViewById<TextView>(R.id.twanita)

        var tremaja=view.findViewById<TextView>(R.id.tremaja)
        var tremajamuda=view.findViewById<TextView>(R.id.tremajamuda)
        var tdewasa=view.findViewById<TextView>(R.id.tdewasa)

        var tya=view.findViewById<TextView>(R.id.tya)
        var ttidak=view.findViewById<TextView>(R.id.ttidak)

        var gen=""
        var age=""

        tpria.post {
            tpria.performClick()
        }
        tremajamuda.post {
            tremajamuda.performClick()
        }
        tpria.setOnClickListener {
            gen="1"
            tpria.setBackgroundResource(R.color.reds)
            tpria.setTextColor(ctx.resources.getColor(R.color.white))
            twanita.setBackgroundResource(R.drawable.border_red)
            twanita.setTextColor(ctx.resources.getColor(R.color.black))

        }
        twanita.setOnClickListener {
            gen="2"
            twanita.setBackgroundResource(R.color.reds)
            twanita.setTextColor(ctx.resources.getColor(R.color.white))
            tpria.setBackgroundResource(R.drawable.border_red)
            tpria.setTextColor(ctx.resources.getColor(R.color.black))

        }

        main.setOnClickListener {
            dialog.dismiss()
        }

        tremaja.setOnClickListener {
            age="1"
            tremaja.setBackgroundResource(R.color.reds)
            tremaja.setTextColor(ctx.resources.getColor(R.color.white))
            tremajamuda.setBackgroundResource(R.drawable.border_red)
            tremajamuda.setTextColor(ctx.resources.getColor(R.color.black))
            tdewasa.setBackgroundResource(R.drawable.border_red)
            tdewasa.setTextColor(ctx.resources.getColor(R.color.black))

        }

        tremajamuda.setOnClickListener {
            age="2"

            tremajamuda.setBackgroundResource(R.color.reds)
            tremajamuda.setTextColor(ctx.resources.getColor(R.color.white))
            tremaja.setBackgroundResource(R.drawable.border_red)
            tremaja.setTextColor(ctx.resources.getColor(R.color.black))
            tdewasa.setBackgroundResource(R.drawable.border_red)
            tdewasa.setTextColor(ctx.resources.getColor(R.color.black))

        }
        tdewasa.setOnClickListener {
            age="3"
            tdewasa.setBackgroundResource(R.color.reds)
            tdewasa.setTextColor(ctx.resources.getColor(R.color.white))
            tremaja.setBackgroundResource(R.drawable.border_red)
            tremaja.setTextColor(ctx.resources.getColor(R.color.black))
            tremajamuda.setBackgroundResource(R.drawable.border_red)
            tremajamuda.setTextColor(ctx.resources.getColor(R.color.black))

        }

        tya.setOnClickListener {


            tya.setBackgroundResource(R.color.reds)
            tya.setTextColor(ctx.resources.getColor(R.color.white))
            ttidak.setBackgroundResource(R.drawable.border_red)
            ttidak.setTextColor(ctx.resources.getColor(R.color.black))
            dialog.dismiss()


            var ii=Intent(ctx, CustomerRecKnowActivity::class.java)
            ii.putExtra("gen",gen)
            ii.putExtra("age",age)
            ctx.startActivity(ii)

        }
        ttidak.setOnClickListener {

            ttidak.setBackgroundResource(R.color.reds)
            ttidak.setTextColor(ctx.resources.getColor(R.color.white))
            tya.setBackgroundResource(R.drawable.border_red)
            tya.setTextColor(ctx.resources.getColor(R.color.black))
            dialog.dismiss()


            var ii=Intent(ctx, CustomerRecNotKnowActivity::class.java)
            ii.putExtra("gen",gen)
            ii.putExtra("age",age)
            ctx.startActivity(ii)

        }


        dialog.setContentView(view)
        dialog.show()
    }

    fun createRatingDialog(ctx: BaseActivity): Dialog {
        val vg: ViewGroup? = null
        val view = ctx.layoutInflater.inflate(R.layout.dialog_rating, vg)
        val dialog = Dialog(ctx)
        dialog.setCancelable(true)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))



        val badd = view.findViewById(R.id.bnext) as Button
        val brating = view.findViewById(R.id.ratingBar) as RatingBar


        badd.text = "Simpan >"

        badd.setOnClickListener {

            ctx.showLoadingDialog()
            var pr=MainPresenter(ctx,ctx.APIServices)
            pr.submitRating(brating.numStars.toString(),object : ObjectResponseInterface<rating>{
                override fun onSuccess(res: rating) {
                    ctx.dismisLoadingDialog()
                }

                override fun onFailed(msg: String) {
                    ctx.dismisLoadingDialog()
                }

            })



            dialog.dismiss()


        }


        dialog.setContentView(view)
        dialog.show()

        return dialog
    }
    fun createUpdateDialog(ctx: Activity): Dialog {
        val vg: ViewGroup? = null
        val view = ctx.layoutInflater.inflate(R.layout.dialog_update, vg)
        val dialog = Dialog(ctx)
        dialog.setCancelable(true)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))



        val badd = view.findViewById(R.id.bnext) as Button


        badd.text = "Unduh >"

        badd.setOnClickListener { dialog.dismiss() }


        dialog.setContentView(view)
        dialog.show()

        return dialog
    }



    fun createScoreDialog(ctx: Activity, msg: String): Dialog {
        val vg: ViewGroup? = null
        val view = ctx.layoutInflater.inflate(R.layout.dialog_score, vg)
        val dialog = Dialog(ctx,android.R.style.Theme_NoTitleBar_Fullscreen)
        dialog.setCancelable(true)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent);
        dialog.window!!.attributes.windowAnimations = R.style.ScoreDialogAnimation

        val tscore = view.findViewById(R.id.tscore) as TextView


        tscore.setText(msg)



        Handler().postDelayed({
            dialog.dismiss()
        },2000)


        dialog.setContentView(view)
        dialog.show()

        return dialog
    }



    fun createCDBResultDialog(ctx: Activity, c: cdb, msg: String): Dialog {
        val vg: ViewGroup? = null
        val view = ctx.layoutInflater.inflate(R.layout.dialog_cdb_result, vg)
        val dialog = Dialog(ctx)
        dialog.setCancelable(true)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)


        val tclose = view.findViewById(R.id.tclose) as TextView
        val tname = view.findViewById(R.id.tname) as TextView
        val tdesc = view.findViewById(R.id.tdesc) as TextView


        tname.setText(c.nama)
        tdesc.setText(Html.fromHtml(msg))


        tclose.setOnClickListener {
            dialog.dismiss()
        }


        dialog.setContentView(view)
        dialog.show()

        return dialog
    }


    fun createCDBDialog(ctx: Activity, c: cdb, lst: CDBDialogListener) {
        val vg: ViewGroup? = null
        val view = ctx.layoutInflater.inflate(R.layout.dialog_cdb, vg)
        val dialog = Dialog(ctx)
        dialog.setCancelable(true)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)


        val ty = view.findViewById(R.id.tyes) as TextView
        val tname = view.findViewById(R.id.tname) as TextView
        val rbupdate = view.findViewById(R.id.rbupdate) as RadioButton
        val rbdeal = view.findViewById(R.id.rbdeal) as RadioButton
        val rbnodeal = view.findViewById(R.id.rbnodeal) as RadioButton

        tname.setText(c.nama)

        ty.setOnClickListener {
            if (rbupdate.isChecked) {
                createCDBUpdateDialog(ctx, c, lst)
            } else if (rbdeal.isChecked) {
                lst.ondeal(c.id!!)
            } else if (rbnodeal.isChecked) {
                lst.onnodeal(c.id!!)
            }
            dialog.dismiss()


        }
        dialog.setContentView(view)
        dialog.show()
    }


    fun createCDBUpdateDialog(ctx: Activity, c: cdb, lst: CDBDialogListener) {
        val vg: ViewGroup? = null
        val view = ctx.layoutInflater.inflate(R.layout.dialog_cdb_update, vg)
        val dialog = Dialog(ctx)
        dialog.setCancelable(true)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)


        val ty = view.findViewById(R.id.tyes) as TextView
        val tname = view.findViewById(R.id.tname) as TextView
        val etdate = view.findViewById(R.id.etdate) as EditText
        val ettime = view.findViewById(R.id.ettime) as EditText
        val imgcalendar = view.findViewById(R.id.imgcalendar) as ImageView
        val imgtime = view.findViewById(R.id.imgtime) as ImageView
        val rbhot = view.findViewById(R.id.rbhot) as RadioButton
        val rbcold = view.findViewById(R.id.rbcold) as RadioButton
        val rbwarm = view.findViewById(R.id.rbwarm) as RadioButton
        val lldate = view.findViewById(R.id.lldate) as LinearLayout



        tname.setText(c.nama)
        setupDate(ctx, etdate, imgcalendar)
        setupTime(ctx, ettime, imgtime)

        rbhot.setOnClickListener{
            if(rbcold.isChecked)
            lldate.visibility=View.GONE
            else
            lldate.visibility=View.VISIBLE
        }
        rbwarm.setOnClickListener{
            if(rbcold.isChecked)
            lldate.visibility=View.GONE
            else
            lldate.visibility=View.VISIBLE
        }
        rbcold.setOnClickListener{
            if(rbcold.isChecked)
            lldate.visibility=View.GONE
            else
            lldate.visibility=View.VISIBLE
        }


        ty.setOnClickListener {
            var stat = ""
            if (rbcold.isChecked)
                stat = "0"
            else if(rbhot.isChecked)
                stat = "1"
            else
                stat = "2"


            var dfu=function.parseDate(etdate.text.toString()+" "+ettime.text.toString(),"dd-MMM-yyyy HH:mm")
            var cn=Calendar.getInstance()
            cn.add(Calendar.HOUR,1)
            var nexthour=cn.timeInMillis
            if((dfu==null||dfu.before(Date(nexthour))) && !stat.equals("0")){
                Toast.makeText(ctx,"Waktu Followup harus lebih besar dari satu jam kedepan dan kurang dari enam bulan kedepan",Toast.LENGTH_LONG).show()
            }else {
                lst.onUpdate(etdate.text.toString(), ettime.text.toString(), stat)
                dialog.dismiss()
            }


        }
        dialog.setContentView(view)
        dialog.show()
    }

    fun setupDate(ctx: Context, tview: EditText, img: View) {
        tview.setText(function.dateToString(Date(), "dd-MMM-yyyy"))
        tview.setOnClickListener {
            var maxdate=Calendar.getInstance()
            maxdate.add(Calendar.MONTH,6)
            var d=DatePickerDialog(
                ctx, object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                        var date =
                            year.toString() + "-" + function.intToStringTime(month + 1) + "-" + function.intToStringTime(
                                dayOfMonth
                            )
                        (it as TextView).text = function.converDateFormat(date, "yyyy-MM-dd", "dd-MMM-yyyy")
                    }

                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            )
            d.datePicker.maxDate=maxdate.timeInMillis
            d.datePicker.minDate=Calendar.getInstance().timeInMillis
            d.show()

        }
        img.setOnClickListener {

            var maxdate=Calendar.getInstance()
            maxdate.add(Calendar.MONTH,6)
            var d=DatePickerDialog(
                ctx, object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                        var date =
                            year.toString() + "-" + function.intToStringTime(month + 1) + "-" + function.intToStringTime(
                                dayOfMonth
                            )
                        tview.setText(function.converDateFormat(date, "yyyy-MM-dd", "dd-MMM-yyyy"))
                    }

                },
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
            )
            d.datePicker.maxDate=maxdate.timeInMillis
            d.datePicker.minDate=Calendar.getInstance().timeInMillis
            d.show()

        }
    }
    fun setupTime(ctx:Context,tview:TextView,img:View){
        tview.setText("00:00")
        tview.setOnClickListener {
            TimePickerDialog(ctx,object : TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    var time=function.intToStringTime(hourOfDay) +":"+function.intToStringTime(minute)
                    tview.text= time
                }
            },Calendar.getInstance().get(Calendar.HOUR_OF_DAY),Calendar.getInstance().get(Calendar.MINUTE),true).show()
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
    fun createRedeemYesNoDialog(ctx: Activity, msg: String, simg: String, lst: YesNoListener) {
        val vg: ViewGroup? = null
        val view = ctx.layoutInflater.inflate(R.layout.dialog_redeem_conf, vg)
        val dialog = Dialog(ctx)
        dialog.setCancelable(true)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)


        val ty = view.findViewById(R.id.tyes) as TextView
        val tn = view.findViewById(R.id.tno) as TextView
        val img = view.findViewById(R.id.img) as ImageView
        val tdesc = view.findViewById(R.id.tdesc) as TextView

        tdesc.setText(msg)


        if (simg.length > 0) {
            Glide.with(ctx).load(simg).into(img)
        }


        ty.setOnClickListener {
            lst.onYes()
            dialog.dismiss()
        }
        tn.setOnClickListener {
            lst.onNo()
            dialog.dismiss()
        }


        dialog.setContentView(view)
        dialog.show()
    }


    fun createRedeemResultDialog(ctx: Activity, msg: String, simg: String) {
        val vg: ViewGroup? = null
        val view = ctx.layoutInflater.inflate(R.layout.dialog_redeem_result, vg)
        val dialog = Dialog(ctx)
        dialog.setCancelable(true)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)


        val ty = view.findViewById(R.id.tyes) as TextView
        val img = view.findViewById(R.id.img) as ImageView
        val tdesc = view.findViewById(R.id.tdesc) as TextView

        tdesc.setText(msg)


        if (simg.length > 0) {
            Glide.with(ctx).load(simg).into(img)
        }


        ty.setOnClickListener {
            dialog.dismiss()
        }


        dialog.setContentView(view)
        dialog.show()
    }

    fun createRedeemDialog(ctx: Activity, lst: ObjectListener<String>) {
        val vg: ViewGroup? = null
        val view = ctx.layoutInflater.inflate(R.layout.dialog_redeem, vg)
        val dialog = Dialog(ctx)
        dialog.setCancelable(true)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)


        val ty = view.findViewById(R.id.tyes) as TextView
        val etcode = view.findViewById(R.id.etcode) as EditText



        ty.setOnClickListener {
            lst.onOK(etcode.text.toString())
            dialog.dismiss()
        }


        dialog.setContentView(view)
        dialog.show()
    }

    fun createAdsDialog(ctx: Activity, ad: ads):Dialog {
        val vg: ViewGroup? = null
        val view = ctx.layoutInflater.inflate(R.layout.dialog_image, vg)
        val dialog = Dialog(ctx, android.R.style.Theme_NoTitleBar_Fullscreen)
        dialog.setCancelable(true)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

//        view.findViewById<RelativeLayout>(R.id.main_layout).setOnClickListener(View.OnClickListener {  })
        view.findViewById<TextView>(R.id.tclose).setOnClickListener(View.OnClickListener { dialog.dismiss() })

        val iv = view.findViewById(R.id.imageView) as fitxImageView


        iv.setOnClickListener {
            if(!ad.redirectUrl.isNullOrEmpty() && ad.redirectUrl.length>5) {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(ad.redirectUrl))
                ctx.startActivity(browserIntent)
            }
            dialog.dismiss()
        }

        Glide.with(ctx).load(ad.image).into(iv)

        dialog.setContentView(view)
        dialog.show()
        return dialog
    }

    fun createBasicKnowledgeDialog(ctx: Activity) {
        val vg: ViewGroup? = null
        val view = ctx.layoutInflater.inflate(R.layout.dialog_basic_knowledge, vg)
        val dialog = Dialog(ctx, android.R.style.Theme_NoTitleBar_Fullscreen)
        dialog.setCancelable(true)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

//        view.findViewById<RelativeLayout>(R.id.main_layout).setOnClickListener(View.OnClickListener {  })
        view.findViewById<TextView>(R.id.tclose).setOnClickListener(View.OnClickListener { dialog.dismiss() })

//        val iv = view.findViewById(R.id.imageView) as ImageView
//
//        Glide.with(ctx).load(url).into(iv)

        dialog.setContentView(view)
        dialog.show()
    }


    fun createLoginFailedDialog(ctx: Activity, msg: String) {
        val vg: ViewGroup? = null
        val view = ctx.layoutInflater.inflate(R.layout.dialog_login_failed, vg)
        val dialog = Dialog(ctx)
        dialog.setCancelable(true)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

//        view.findViewById<RelativeLayout>(R.id.main_layout).setOnClickListener(View.OnClickListener {  })
        view.findViewById<TextView>(R.id.tclose).setOnClickListener(View.OnClickListener { dialog.dismiss() })

        val tm = view.findViewById(R.id.tmsg) as TextView

        tm.setText(msg)

        dialog.setContentView(view)
        dialog.show()
    }

    fun createYesNoDialog(ctx: Activity, msg: String, lst: YesNoListener) {
        val vg: ViewGroup? = null
        val view = ctx.layoutInflater.inflate(R.layout.dialog_yes_no, vg)
        val dialog = Dialog(ctx)
        dialog.setCancelable(true)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

//        view.findViewById<RelativeLayout>(R.id.main_layout).setOnClickListener(View.OnClickListener {  })
//        view.findViewById<TextView>(R.id.tclose).setOnClickListener(View.OnClickListener { dialog.dismiss() })

        val tm = view.findViewById(R.id.tdesc) as TextView
        val ty = view.findViewById(R.id.tyes) as TextView
        val tn = view.findViewById(R.id.tno) as TextView

        tm.setText(msg)

        ty.setOnClickListener {
            lst.onYes()
            dialog.dismiss()
        }
        tn.setOnClickListener {
            lst.onNo()
            dialog.dismiss()
        }

        dialog.setContentView(view)
        dialog.show()
    }

    fun createYesDialog(ctx: Activity, msg: String, yeslabel: String, leftalign:Boolean=false): Dialog {
        val vg: ViewGroup? = null
        val view = ctx.layoutInflater.inflate(R.layout.dialog_yes, vg)
        val dialog = Dialog(ctx)
        dialog.setCancelable(true)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

//        view.findViewById<RelativeLayout>(R.id.main_layout).setOnClickListener(View.OnClickListener {  })
//        view.findViewById<TextView>(R.id.tclose).setOnClickListener(View.OnClickListener { dialog.dismiss() })

        val tm = view.findViewById(R.id.tdesc) as TextView
        val ty = view.findViewById(R.id.tyes) as TextView
//        val tn = view.findViewById(R.id.tno) as TextView

        tm.setText(msg)
        if(leftalign) {
            tm.gravity = Gravity.LEFT
            tm.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
        }
        ty.setText(yeslabel)

        ty.setOnClickListener {
            dialog.dismiss()
        }

        dialog.setContentView(view)
        dialog.show()
        return dialog
    }
}