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

class QuizPresenter{
    private var disposable: Disposable? = null
    var base:BaseActivity
    var api:api

    constructor(b:BaseActivity,ap:api){
        this.base=b
        this.api=ap
    }

    fun getQuiz(cb: DataListInterface<quiz>){


        disposable=api.getQuiz("0",base.getUser()!!.id)
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


    fun getQuestion(quizid:String,cb: DataListInterface<question>){


        disposable=api.getQuestion("0",quizid)
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


    fun submitQuis(quizid: String,questionid:ArrayList<String>,answerid:ArrayList<String>,cb:ObjectResponseInterface<quizresponse>){

        //remove empty answer
        for(i in 0..questionid.size-1){
            if(questionid.get(i).length==0){
                questionid.removeAt(i)
                answerid.removeAt(i)
            }
        }



        disposable=api.submitQuiz(quizid,base.getUser()!!.id,questionid,answerid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(res?.body() != null) {
                    cb.onSuccess(res.body()!!);
                }else{
                    var err=res.errorBody()!!.string()

                    if(err.startsWith("{")) {
                        var obj = Gson().fromJson<baseresponse<String>>(err, baseresponse::class.java)
                        if(obj!=null)
                            cb.onFailed(obj.message)
                    }else {
                        cb.onFailed(err)
                    }
                }
            },{ error->
                cb.onFailed(error.message!!)
            })



    }



    fun getHistoryQuiz(cb: DataListInterface<quizresponse>){


        disposable=api.getQuisHistory("0",base.getUser()!!.id)
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

    fun getDetailHistoryQuiz(qid:String,cb: DataListInterface<quizhistorydetail>){


        disposable=api.getQuisHistoryDetail("0","","",qid)
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