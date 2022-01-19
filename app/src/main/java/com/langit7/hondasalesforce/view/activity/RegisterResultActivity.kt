package com.langit7.hondasalesforce.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.langit7.hondasalesforce.R
import kotlinx.android.synthetic.main.activity_register_result.*

class RegisterResultActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_result)



        imgresult.setImageResource(R.drawable.img_registrasisukses)
        ttitle.setText(getString(R.string.registersuccess))
        tdesc.setText(getString(R.string.registersuccessdesc))


        bback.setOnClickListener({
            var ii=Intent(ctx,LoginActivity::class.java)
            ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(ii)
            finish()



        })
    }
}
