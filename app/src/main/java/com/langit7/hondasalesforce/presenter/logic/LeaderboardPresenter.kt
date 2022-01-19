package com.langit7.hondasalesforce.presenter.logic

import com.langit7.hondasalesforce.API.api
import com.langit7.hondasalesforce.model.*
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.view.activity.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LeaderboardPresenter{
    private var disposable: Disposable? = null
    var base:BaseActivity
    var api:api

    constructor(b:BaseActivity,ap:api){
        this.base=b
        this.api=ap
    }



    fun getLeaderboardDealer(cb: DataListInterface<leaderboard>){
        try {
            disposable=api.getLeaderboard("0","1","","",base.getUser()!!.profileUser.dealer.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({res->
                    //if(res.data.size>0 ) {
                        cb.onGetDataSuccess(res.data);
                    //}
                },{ error->
                    cb.onGetDataFailed(error.message!!)
                })
        } catch (e: Exception) {
            cb.onGetDataFailed(e.message!!)
        }
    }

    fun getLeaderboardMainDealer(cb: DataListInterface<leaderboard>){
        try {
            disposable=api.getLeaderboard("0","1","",base.getUser()!!.profileUser.dealer.mainDealer,"")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({res->
                    //if(res.data.size>0 ) {
                    cb.onGetDataSuccess(res.data);
                    //}
                },{ error->
                    cb.onGetDataFailed(error.message!!)
                })
        } catch (e: Exception) {
            cb.onGetDataFailed(e.message!!)
        }
    }
    fun getLeaderboardHistory(cb: DataListInterface<leaderboardhistory>){
        disposable=api.getLeaderboardHistory("0","1","12",base.getUser()!!.id)
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
}