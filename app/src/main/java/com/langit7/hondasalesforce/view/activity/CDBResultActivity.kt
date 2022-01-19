package com.langit7.hondasalesforce.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.langit7.hondasalesforce.R
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_survey_result.*

class CDBResultActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cdbresult)

        tactionbartitle.setText("Prospect Collection")
        imgback.setOnClickListener({
            var ii= Intent(ctx,CDBListActivity::class.java)
            ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(ii)
            finish()
        })


        tback.setOnClickListener {
            var ii= Intent(ctx,CDBListActivity::class.java)
            ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(ii)
            finish()
        }
    }


    override fun onBackPressed() {
        var ii= Intent(ctx,CDBListActivity::class.java)
        ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(ii)
        finish()
    }
}
