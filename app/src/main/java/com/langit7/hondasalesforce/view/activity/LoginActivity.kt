package com.langit7.hondasalesforce.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import com.google.gson.Gson
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.DialogUtil
import com.langit7.hondasalesforce.Util.function.savePreverence
import com.langit7.hondasalesforce.model.user
import com.langit7.hondasalesforce.presenter.logic.LoginPresenter
import com.langit7.hondasalesforce.presenter.logic.RegisterPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.LoginInterface
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.presenter.viewInterface.YesNoListener
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginInterface {
    override fun onLoginSuccess(res: user) {
        dismisLoadingDialog()

        if(res.profileUser.dealer==null){
            Toast("User belum terdaftar pada dealer manapun")
        }else {
            savePreverence(ctx, "user", Gson().toJson(res))
            savePreverence(ctx, "token", res.profileUser.token)
            var ii = Intent(ctx, MainActivity::class.java)
            startActivity(ii)
            finish()
        }
    }

    override fun onLoginFailed(msg: String) {
        dismisLoadingDialog()
        DialogUtil.createLoginFailedDialog(ctx as Activity,msg)
    }

    var passvis=false
    lateinit var presenter:LoginPresenter
    lateinit var registerpresenter:RegisterPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter= LoginPresenter(this,APIServices)
        registerpresenter= RegisterPresenter(this,APIServices)

        bsubmit.setOnClickListener {

            showLoadingDialog()
            registerpresenter.getHID(etuser.text.toString(), object : ObjectResponseInterface<user> {
                override fun onSuccess(res: user) {

                    if(res!=null && res.message.isNullOrEmpty()){
                        if(res.profileUser.is_login.equals("1")) {
                            dismisLoadingDialog()
                            DialogUtil.createYesNoDialog(
                                ctx as Activity,
                                "Akun anda telah login pada device lain, akun anda pada device lain akan otomatis logout jika anda login pada device ini\nLanjutkan?",
                                object : YesNoListener {
                                    override fun onYes() {
                                        showLoadingDialog()
                                        presenter.login(etuser.text.toString(), etpassword.text.toString(), ctx as LoginActivity)
                                    }

                                    override fun onNo() {
                                    }

                                })
                        }else{
                            presenter.login(etuser.text.toString(), etpassword.text.toString(), ctx as LoginActivity)
                        }
                    }else{
                        dismisLoadingDialog()
                        Toast(res.message)
                    }

                }

                override fun onFailed(msg: String) {
                    dismisLoadingDialog()
                    Toast("Koneksi Gagal, Silahkan Coba Lagi")
                }
            })


        }

        tregister.setOnClickListener {
            var ii=Intent(ctx,RegisterActivity::class.java)
            startActivity(ii)

        }

        tforgot.setOnClickListener {
            var ii=Intent(ctx,ForgetPasswordActivity::class.java)
            startActivity(ii)
        }

        etpassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        imgeye.setOnClickListener {
            if(passvis) {
                etpassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                imgeye.setImageResource(R.drawable.icon_visible)
                passvis=false
            }else{
                etpassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_NORMAL
                imgeye.setImageResource(R.drawable.icon_invisible)
                passvis=true
            }
        }
    }
}
