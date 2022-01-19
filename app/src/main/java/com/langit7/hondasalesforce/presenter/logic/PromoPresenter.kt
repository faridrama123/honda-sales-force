package com.langit7.hondasalesforce.presenter.logic

import com.google.gson.Gson
import com.langit7.hondasalesforce.API.api
import com.langit7.hondasalesforce.model.*
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.activity.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class PromoPresenter{
    private var disposable: Disposable? = null
    var base: BaseActivity
    var api:api

    constructor(b:BaseActivity,ap:api){
        this.base=b
        this.api=ap
    }

    fun getPromo(cb: DataListInterface<promo>){


        disposable=api.getPromo("0",base.getUser()!!.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                //if(res.data.size>0 ) {
                    cb.onGetDataSuccess(res.data);
                //}
            },{ error->
                cb.onGetDataFailed(error.message!!)
            })


    }

    fun getRedeemed(cb: DataListInterface<redeem>){


        disposable=api.getRedemmed("0",base.getUser()!!.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                //if(res.data.size>0 ) {
                cb.onGetDataSuccess(res.data);
                //}
            },{ error->
                var msg="Connection Break"
                if(error.message!=null)
                    msg=error.message!!

                cb.onGetDataFailed(msg)
            })


    }


    fun redeemPromo(promoid:String, redeemcode:String,cb: ObjectResponseInterface<redeem>){


        disposable=api.redeemPromo(promoid,base.getUser()!!.id,redeemcode)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(res.code()==200 || res.code()==201) {

                    cb.onSuccess(res.body()!!);
                }else {
                    var err=res.errorBody()!!.string()
//                    cb.onLoginFailed(err.substring(2,err.length-2))

                    if (err.substring(0,1).equals("{")){
                        var obj= Gson().fromJson<baseresponse<String>>(err, baseresponse::class.java)
                        cb.onFailed(obj.message)
                    } else if(err.substring(0,1).equals("[")){
                        cb.onFailed(err.substring(2, err.length-2))
                    }
                }
            },{ error->
                cb.onFailed(error.message!!)
            })


    }
    fun getRedeemHistory(cb: DataListInterface<redeem>){


        disposable=api.getHistoryRedeem("1",base.getUser()!!.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
//                if(res.data!=null) {
                cb.onGetDataSuccess(res.data);
//                }
            },{ error->
                cb.onGetDataFailed(error.message!!)
            })


    }




}