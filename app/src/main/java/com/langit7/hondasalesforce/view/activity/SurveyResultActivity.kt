package com.langit7.hondasalesforce.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.DialogUtil
import com.langit7.hondasalesforce.model.history
import com.langit7.hondasalesforce.model.surveyresponse
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_survey_result.*

class SurveyResultActivity : BaseActivity() {

    lateinit var sr:surveyresponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_result)

        sr=intent.getSerializableExtra("res") as surveyresponse
//        var poin=intent.getSerializableExtra("poin") as history

        if(sr.total_point!=null ){
            try {
                if (sr.total_point.toInt() > 0) {
                    DialogUtil.createScoreDialog(ctx as Activity, "+" + sr.total_point)
                }
            }catch (e:Exception){}
        }

        if(sr==null)
            finish()


        tactionbartitle.setText(sr.title)
        imgback.setOnClickListener({
            var ii= Intent(ctx,MainActivity::class.java)
            ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            ii.putExtra("tid",2)
            startActivity(ii)
            finish()
        })


        tback.setOnClickListener {
            var ii= Intent(ctx,MainActivity::class.java)
            ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            ii.putExtra("tid",2)
            startActivity(ii)
            finish()
        }
    }


    override fun onBackPressed() {
        var ii= Intent(ctx,MainActivity::class.java)
        ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        ii.putExtra("tid",2)
        startActivity(ii)
        finish()
    }
}
