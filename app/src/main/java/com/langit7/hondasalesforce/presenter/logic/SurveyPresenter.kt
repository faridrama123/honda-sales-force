package com.langit7.hondasalesforce.presenter.logic

import com.langit7.hondasalesforce.API.api
import com.langit7.hondasalesforce.model.*
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.activity.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SurveyPresenter{
    private var disposable: Disposable? = null
    var base:BaseActivity
    var api:api

    constructor(b:BaseActivity,ap:api){
        this.base=b
        this.api=ap
    }

    fun getSurvey(cb: DataListInterface<survey>){


        disposable=api.getSurvey("0",base.getUser()!!.id)
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


    fun getSurveyQuestion(surveyid:String,cb: DataListInterface<surveyquestion>){


        disposable=api.getSurveyQuestion("0",surveyid)
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


    fun submitSurvey(surveyid: String,questionid:ArrayList<String>,answerid:ArrayList<String>,cb:ObjectResponseInterface<surveyresponse>){

        disposable=api.submitSurvey(surveyid,base.getUser()!!.id,questionid,answerid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(res.isSuccessful ) {
                cb.onSuccess(res.body()!!);
                }else{
                    cb.onFailed(res.message())
                }
            },{ error->
                cb.onFailed(error.message!!)
            })



    }
}