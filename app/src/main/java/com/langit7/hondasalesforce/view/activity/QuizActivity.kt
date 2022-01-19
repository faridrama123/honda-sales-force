package com.langit7.hondasalesforce.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.DialogUtil
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.*
import com.langit7.hondasalesforce.presenter.logic.QuizPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.presenter.viewInterface.YesNoListener
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_quiz.*
import java.util.*
import kotlin.collections.ArrayList

class QuizActivity : BaseActivity() {

    lateinit var mquiz: quiz
    var lsQuestion=ArrayList<question>()
    var lsAnswer=ArrayList<String>()
    var lssQuestion=ArrayList<String>()

    lateinit var th:Thread
    var time=0
    var curq=0
    var choicebuffer=""


    lateinit var presenter :QuizPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)


        mquiz= intent.getSerializableExtra("q") as quiz

        tactionbartitle.setText(mquiz.title)
        imgback.setOnClickListener({
            onBackPressed()
        })



        showLoadingDialog()
        presenter= QuizPresenter(this,APIServices)
        presenter.getQuestion(mquiz.id,object :DataListInterface<question>{
            override fun onGetDataSuccess(res: List<question>) {
                dismisLoadingDialog()
                lsQuestion.clear()
                lssQuestion.clear()
                lsAnswer.clear()
                for(q in res) {
                    lsQuestion.add(q)
                }
                Collections.shuffle(lsQuestion)
                for(q in lsQuestion) {
                    lssQuestion.add(q.id)
                    lsAnswer.add("")
                }
                init()
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
            }


        })

        tback.setOnClickListener {
            onBackPressed()
        }


        tnext.setOnClickListener {
            if(choicebuffer.length>0) {
                lsAnswer.set(curq,choicebuffer)
                choicebuffer=""
            }

            curq++
            if(curq<lsAnswer.size)
            choicebuffer=lsAnswer.get(curq)

            if(curq>=lsQuestion.size)
                DialogUtil.createYesNoDialog(ctx as Activity,"Apakah anda yakin akan menyelesaikan kuis?",object:YesNoListener{
                    override fun onYes() {
                        endQuiz()
                    }

                    override fun onNo() {
                    }
                })

            else
                generateNextQuestion()
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


    }

    fun init(){

        time=mquiz.duration

        tdone.setText(function.intToStringTime(curq))
        ttotal.setText(function.intToStringTime(lsQuestion.size))


        th=Thread(Runnable {
            while(time>0 && actvisible){
                time--
//                Log.e("time",time.toString())
                Thread.sleep(1000)
                runOnUiThread {
                    rendertime()
                    checktime()
                }
            }
        })
        th.start()

        generateNextQuestion()
    }

    fun checktime(){
        if(time==0 && actvisible){
            actvisible=false
            DialogUtil.createYesDialog(ctx as Activity,"Waktu Habis!","Lihat Score >").setOnDismissListener {
                endQuiz()
            }
        }
    }

    fun endQuiz(){
        actvisible=false
        showLoadingDialog()
        presenter.submitQuis(mquiz.id,lssQuestion,lsAnswer,object : ObjectResponseInterface<quizresponse>{
            override fun onSuccess(res: quizresponse) {
                dismisLoadingDialog()

                        var ii= Intent(ctx,QuizResultActivity::class.java)
                        ii.putExtra("res",res)
                        startActivity(ii)
                        finish()

            }

            override fun onFailed(msg: String) {
                dismisLoadingDialog()
                Toast(msg)
                var ii= Intent(ctx,MainActivity::class.java)
                ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                ii.putExtra("tid",1)
                startActivity(ii)
                finish()
            }

        })
    }

    fun rendertime(){
        var s=time %60
        var m=Math.floor((time/60).toDouble())
        tminute.setText(function.intToStringTime(m.toInt()))
        tsecond.setText(function.intToStringTime(s))
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

        llquestioncontainer.addView(v)
    }

    fun renderChoice(llc:LinearLayout,q:question){
        llc.removeAllViews()
        for(c in q.answerQuestion){
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
                renderChoice(llc,q)
            })


            llc.addView(vc)
        }
    }
    fun renderEsay(llc: LinearLayout, q: question){
        llc.removeAllViews()
        var vc =layoutInflater.inflate(R.layout.item_esay,null)

        var rlmain=vc.findViewById<RelativeLayout>(R.id.main)
        var et=vc.findViewById<EditText>(R.id.etesay)

        et.setText(choicebuffer)
        et.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                choicebuffer=et.text.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
        llc.addView(vc)

    }

    override fun onPause() {
        super.onPause()
        actvisible=false
        finish()

    }

    override fun onStop() {
        actvisible=false
        super.onStop()
    }
}
