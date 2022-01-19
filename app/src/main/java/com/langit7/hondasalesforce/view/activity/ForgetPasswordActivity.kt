package com.langit7.hondasalesforce.view.activity

import android.os.Bundle
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.baseresponse
import com.langit7.hondasalesforce.model.user
import com.langit7.hondasalesforce.presenter.logic.LoginPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import kotlinx.android.synthetic.main.activity_forget_password.*

class ForgetPasswordActivity : BaseActivity() {


    lateinit var presenter:LoginPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)


        presenter= LoginPresenter(this,APIServices)




        bsubmit.setOnClickListener{
            showLoadingDialog()
            presenter.forgotpassword(etemail.text.toString(), object :ObjectResponseInterface<baseresponse<user>>{
                override fun onSuccess(res: baseresponse<user>) {
                    dismisLoadingDialog()
                    Toast(res.message)
                    finish()
                }

                override fun onFailed(msg: String) {
                    dismisLoadingDialog()
                    Toast(msg)
                }

            })
        }
    }
}
