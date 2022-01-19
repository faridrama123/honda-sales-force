package com.langit7.hondasalesforce.view.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.DialogUtil
import com.langit7.hondasalesforce.model.history
import com.langit7.hondasalesforce.model.quizresponse
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_quiz_result.*

class QuizResultActivity : BaseActivity() {

    lateinit var qr:quizresponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_result)

        qr=intent.getSerializableExtra("res") as quizresponse

        if(qr==null)
            finish()

        try {
            if (qr.total_point.toInt() > 0) {
                DialogUtil.createScoreDialog(ctx as Activity, "+" + qr.total_point)
            }
        }catch (e:Exception){}

        tactionbartitle.setText(qr.title)
        imgback.setOnClickListener({
            onBackPressed()
        })

        tscore.setText(qr.score +"/" + qr.quiz.maximum_score)

        tback.setOnClickListener {
            var ii=Intent(ctx,MainActivity::class.java)
            ii.putExtra("tid",0)
            ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(ii)
            finish()
        }

        tretake.setOnClickListener{
            var ii=Intent(ctx,MainActivity::class.java)
            ii.putExtra("tid",1)
            ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(ii)
            finish()
        }

        if(qr.ispassquiz.equals("1",true)){
            imgresult.setImageResource(R.drawable.img_kuis_done)
            bretake.visibility= View.GONE
            tmsg.setText("Selamat anda lulus, terus tingkatkan pengetahuan produk anda di tipe lainnya")
        }else{
            imgresult.setImageResource(R.drawable.img_kuis_none)
            tscore.setTextColor(resources.getColor(R.color.redcalm))
            bback.setBackgroundResource(R.drawable.round_gray)
            tmsg.setText("Maaf anda belum lulus, tingkatkan kembali pengetahuan produk anda")
        }

    }


    override fun onBackPressed() {
        var ii=Intent(ctx,MainActivity::class.java)
        ii.putExtra("tid",1)
        ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(ii)
        finish()
    }
}
