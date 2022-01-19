package com.langit7.hondasalesforce.view.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.*
import com.langit7.hondasalesforce.presenter.logic.SurveyPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_survey.*

class SurveyActivity : BaseActivity() {

    var SEPA="<|>"
    lateinit var msurvey: survey
    var lsQuestion=ArrayList<surveyquestion>()
    var lsAnswer=ArrayList<String>()
    var lssQuestion=ArrayList<String>()


    var curq=0

    var choicebuffer=""



    lateinit var presenter :SurveyPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)




        msurvey= intent.getSerializableExtra("s") as survey


        if(msurvey==null)
            finish()


        tactionbartitle.setText(msurvey.title)
        imgback.setOnClickListener({
            onBackPressed()
        })

        showLoadingDialog()
        presenter= SurveyPresenter(this,APIServices)
        presenter.getSurveyQuestion(msurvey.id,object : DataListInterface<surveyquestion> {
            override fun onGetDataSuccess(res: List<surveyquestion>) {
                dismisLoadingDialog()
                lsQuestion.clear()

                for(r in res){
                    lsQuestion.add(r)
                    lssQuestion.add(r.id)
                    lsAnswer.add("")
                }
                ttotal.setText(function.intToStringTime(lsQuestion.size))
                generateNextQuestion()
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
            }


        })


        tnext.setOnClickListener {
            if(choicebuffer.length>0) {
                lsAnswer.set(curq,choicebuffer)
                choicebuffer=""


                curq++
                if(curq<lsAnswer.size)
                    choicebuffer=lsAnswer.get(curq)
                if(curq>=lsQuestion.size)
                    endQuiz()
                else
                    generateNextQuestion()
            }else{
                Toast("Isi Survey Terlebih Dahulu")
            }


        }

        tback.setOnClickListener {
            if(choicebuffer.length>0) {
                lsAnswer.set(curq,choicebuffer)
                choicebuffer=""
            }

            curq--
            choicebuffer=lsAnswer.get(curq)
            generateNextQuestion()
        }


        tdone.setText(function.intToStringTime(curq))


    }




    fun endQuiz(){
        showLoadingDialog()
        presenter.submitSurvey(msurvey.id,lssQuestion,lsAnswer,object : ObjectResponseInterface<surveyresponse> {
            override fun onSuccess(res: surveyresponse) {
                dismisLoadingDialog()
                var ii= Intent(ctx,SurveyResultActivity::class.java)
                ii.putExtra("res",res)
                startActivity(ii)
                finish()



            }

            override fun onFailed(msg: String) {
                dismisLoadingDialog()
                Toast(msg)
                var ii= Intent(ctx,MainActivity::class.java)
                ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                ii.putExtra("tid",2)
                startActivity(ii)
                finish()
            }

        })
    }




    fun generateNextQuestion(){

        if(curq==0)
            tback.visibility= View.GONE
        else
            tback.visibility= View.VISIBLE

        if(curq==lsQuestion.size-1)
            tnext.setText("Selesai")
        else
            tnext.setText("Selanjutnya")

        var q=lsQuestion.get(curq)

        tdone.setText(function.intToStringTime(curq+1))

        llquestioncontainer.removeAllViews()
        var v=layoutInflater.inflate(R.layout.item_question,null)
        var tq=v.findViewById<TextView>(R.id.tquestion)
        var llc=v.findViewById<LinearLayout>(R.id.llchoicecontainer)

        tq.setText(q.body)

        if(q.typeQuestion.equals("1",true)) {
            renderChoice(llc,q)
        }else if(q.typeQuestion.equals("2",true)){
            renderEsay(llc,q)
        }
        else if(q.typeQuestion.equals("3",true)){
            renderMultipleSellect(llc,q)
        }else if(q.typeQuestion.equals("4",true)){
            renderMultipleSellect(llc,q)
        }




        if(choicebuffer.length>0)
            tnext.setBackgroundResource(R.drawable.round_red)
        else
            tnext.setBackgroundResource(R.drawable.round_gray)
        llquestioncontainer.addView(v)
    }

    fun renderChoice(llc: LinearLayout, q:surveyquestion){
        llc.removeAllViews()
        for(c in q.surveyAnswerQuestion){
            var vc =layoutInflater.inflate(R.layout.item_choice,null)

            var rlmain=vc.findViewById<RelativeLayout>(R.id.main)
            var tc=vc.findViewById<TextView>(R.id.tdesc)
            var ic=vc.findViewById<ImageView>(R.id.img)

            tc.setText(c.body)
            if(choicebuffer.equals(c.id,true))
                ic.setImageResource(R.drawable.banner_reddot)
            else
                ic.setImageResource(R.drawable.banner_blankdot)

            rlmain.setOnClickListener({
                choicebuffer=c.id
                tnext.setBackgroundResource(R.drawable.round_red)
                renderChoice(llc,q)
            })


            llc.addView(vc)
        }
    }
    fun renderEsay(llc: LinearLayout, q:surveyquestion){
        llc.removeAllViews()
        var vc =layoutInflater.inflate(R.layout.item_esay,null)

        var rlmain=vc.findViewById<RelativeLayout>(R.id.main)
        var et=vc.findViewById<EditText>(R.id.etesay)

        et.setText(choicebuffer)
        et.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                choicebuffer=et.text.toString()
                if(choicebuffer.length>0)
                    tnext.setBackgroundResource(R.drawable.round_red)
                else
                    tnext.setBackgroundResource(R.drawable.round_gray)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })

        llc.addView(vc)

    }

    fun renderMultipleSellect(llc: LinearLayout, q:surveyquestion){
        llc.removeAllViews()
        for(c in q.surveyAnswerQuestion){
            var vc =layoutInflater.inflate(R.layout.item_sellect,null)

            var rlmain=vc.findViewById<RelativeLayout>(R.id.main)
            var tc=vc.findViewById<TextView>(R.id.tdesc)
            var ic=vc.findViewById<CheckBox>(R.id.chk)

            ic.setTag(c.id)
            tc.setText(c.body)
            var cc=choicebuffer.split(SEPA)
            var found=false
            for(ck in cc){
                if(ck.equals(c.id)) {
                    found = true
                    break
                }
            }

            if(found)
                ic.isChecked=true
            else
                ic.isChecked=false

            rlmain.setOnClickListener({
                ic.isChecked=!ic.isChecked

                getsellectedchoice(llc)
            })
            ic.setOnClickListener({
                getsellectedchoice(llc)
            })


            llc.addView(vc)
        }
    }

    fun getsellectedchoice(llc:LinearLayout){
        choicebuffer=""
        for(v in 0..llc.childCount-1){
            var vc=llc.getChildAt(v)
            var ic=vc.findViewById<CheckBox>(R.id.chk)
            if(ic.isChecked){
                choicebuffer=choicebuffer+(ic.tag as String)+SEPA
            }
        }

//        Log.e("cb",choicebuffer)

        if(choicebuffer.length>3)
            choicebuffer=choicebuffer.substring(0,choicebuffer.length-3)

        if(choicebuffer.length>0)
            tnext.setBackgroundResource(R.drawable.round_red)
        else
            tnext.setBackgroundResource(R.drawable.round_gray)
    }



    fun renderMix(llc: LinearLayout, q:surveyquestion){
        llc.removeAllViews()
        for(c in q.surveyAnswerQuestion){
            var vc =layoutInflater.inflate(R.layout.item_sellect,null)

            var rlmain=vc.findViewById<RelativeLayout>(R.id.main)
            var tc=vc.findViewById<TextView>(R.id.tdesc)
            var ic=vc.findViewById<CheckBox>(R.id.chk)

            ic.setTag(c.id)
            tc.setText(c.body)
            var cc=choicebuffer.split(SEPA)
            var found=false
            for(ck in cc){
                if(ck.equals(c.id)) {
                    found = true
                    break
                }
            }

            if(found)
                ic.isChecked=true
            else
                ic.isChecked=false

            rlmain.setOnClickListener({
                ic.isChecked=!ic.isChecked

                getsellectedMiX(llc)
            })




            llc.addView(vc)
        }

        var vc =layoutInflater.inflate(R.layout.item_esay,null)

        var rlmain=vc.findViewById<RelativeLayout>(R.id.main)
        var et=vc.findViewById<EditText>(R.id.etesay)




        var cc=choicebuffer.split(SEPA)

        if(cc.size>0)
        et.setText(cc.get(cc.size-1))

        et.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                getsellectedMiX(llc)

                if(et.text.toString().length>0)
                    tnext.setBackgroundResource(R.drawable.round_red)
                else
                    tnext.setBackgroundResource(R.drawable.round_gray)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        llc.addView(vc)
    }



    fun getsellectedMiX(llc:LinearLayout){
        choicebuffer=""
        for(v in 0..llc.childCount-1){
            var vc=llc.getChildAt(v)
            var ic=vc.findViewById<CheckBox>(R.id.chk)
            if(ic!=null) {
                if (ic.isChecked) {
                    choicebuffer = choicebuffer + (ic.tag as String) + SEPA
                }
            }else{
                var et=vc.findViewById<EditText>(R.id.etesay)
                choicebuffer = choicebuffer + et.text.toString() + SEPA
            }
        }

        if(choicebuffer.length>3)
            choicebuffer=choicebuffer.substring(choicebuffer.length-3,choicebuffer.length-1)
    }
}