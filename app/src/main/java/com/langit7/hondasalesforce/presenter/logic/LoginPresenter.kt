package com.langit7.hondasalesforce.presenter.logic

import com.google.gson.Gson
import com.langit7.hondasalesforce.API.api
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.baseresponse
import com.langit7.hondasalesforce.model.user
import com.langit7.hondasalesforce.presenter.viewInterface.LoginInterface
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.activity.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LoginPresenter{
    private var disposable: Disposable? = null
    var base:BaseActivity
    var api:api

    constructor(b:BaseActivity,ap:api){
        this.base=b
        this.api=ap
    }

    fun login(hid:String, pass:String,cb:LoginInterface){

    if(hid.length==0){
        cb.onLoginFailed(base.getString(R.string.hidnull))
        return
    }

    if(pass.length==0){
        cb.onLoginFailed(base.getString(R.string.passnull))
        return
    }




    disposable=api.login(hid,pass,base.getFCMID())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({res->

                if(res.code()==200) {

                    cb.onLoginSuccess(res.body()!!);
                }else {
                    var err=res.errorBody()!!.string()
//                    cb.onLoginFailed(err.substring(2,err.length-2))

                    if (err.substring(0,1).equals("{")){
                        var obj=Gson().fromJson<baseresponse<String>>(err, baseresponse::class.java)
                        cb.onLoginFailed(obj.message)
                    } else if(err.substring(0,1).equals("[")){
                        cb.onLoginFailed(err.substring(2, err.length-2))
                    }
                }
        },{ error->

            cb.onLoginFailed(error.message!!)


        })


}



    fun forgotpassword(email:String,cb:ObjectResponseInterface<baseresponse<user>>){


        disposable=api.forgotpassword(email)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(res.code()==200) {

                    cb.onSuccess(res.body()!!);
                }else {
                    var err=res.errorBody()!!.string()
                    var jo= Gson().fromJson<baseresponse<user>>(err,baseresponse::class.java)
                    cb.onFailed(jo.message)
                }
            },{ error->
                cb.onFailed(error.message!!)
            })


    }


}