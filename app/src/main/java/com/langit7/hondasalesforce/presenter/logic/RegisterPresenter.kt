package com.langit7.hondasalesforce.presenter.logic

import com.langit7.hondasalesforce.API.api
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.position
import com.langit7.hondasalesforce.model.user
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.presenter.viewInterface.RegisterInterface
import com.langit7.hondasalesforce.view.activity.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RegisterPresenter {
    private var disposable: Disposable? = null
    var base: BaseActivity
    var api: api

    constructor(b: BaseActivity, ap: api) {
        this.base = b
        this.api = ap
    }


    fun Register(
        name: String,
        email: String,
        password: String,
        passwordconf: String,
        hid: String,
        phone: String,
        position: String,
        cb: RegisterInterface
    ) {

        if (name.length == 0) {
            cb.onRegisterFailed(base.getString(R.string.namenull))
            return
        }

        if (email.length == 0) {
            cb.onRegisterFailed(base.getString(R.string.emailnull))
            return
        }
        if (password.length == 0) {
            cb.onRegisterFailed(base.getString(R.string.passnull))
            return
        }
        if (hid.length == 0) {
            cb.onRegisterFailed(base.getString(R.string.hidnull))
            return
        }
        if (hid.length == 0) {
            cb.onRegisterFailed(base.getString(R.string.phonenull))
            return
        }
        if (position.length == 0 || position.equals("Pilih Jabatan", true)) {
            cb.onRegisterFailed(base.getString(R.string.jabatannull))
            return
        }

        if (!password.equals(passwordconf)) {
            cb.onRegisterFailed(base.getString(R.string.passwordnotequal))
            return
        }



        disposable = api.register(name, email, password, hid, "08" +phone, position)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->

                if (res.code() == 200 || res.code() == 201) {

                    cb.onRegisterSuccess(res.body()!!);
                } else {
                    var err = res.errorBody()!!.string()
                    cb.onRegisterFailed(err.substring(2, err.length - 2))
                }
            }, { error ->
                error.printStackTrace()
                if (error != null && error.message != null)
                    cb.onRegisterFailed(error.message!!)
                else
                    cb.onRegisterFailed("Something Wrong")

            })


    }

    fun position(cb: DataListInterface<position>) {

        disposable = api.getposition("1", "1")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                //if(res.data.size>0 ) {
                cb.onGetDataSuccess(res.data);
                //}
            }, { error ->
                cb.onGetDataFailed(error.message!!)
            })
    }

    fun getHID(hid:String,cb: ObjectResponseInterface<user>) {

        disposable = api.getHID(hid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                //if(res.data.size>0 ) {
                cb.onSuccess(res);
                //}
            }, { error ->
                cb.onFailed(error.message!!)
            })
    }
}