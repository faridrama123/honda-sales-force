package com.langit7.hondasalesforce.presenter.logic

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.langit7.hondasalesforce.API.api
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.article
import com.langit7.hondasalesforce.model.baseresponse
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.activity.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ArticlePresenter{
    private var disposable: Disposable? = null
    var base:BaseActivity
    var api:api

    constructor(b:BaseActivity,ap:api){
        this.base=b
        this.api=ap
    }

    fun getListArticle(cb: DataListInterface<article>){

        var xs="0"

        val listType = object : TypeToken<baseresponse<List<article>>>() {}.getType()
        var sobj= function.getPreverence(base.ctx,"listarticle")
        var obj= Gson().fromJson<baseresponse<List<article>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }



        disposable=api.getListArticle(xs, base.getUser()!!.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->


                    if(obj!=null && res.xs==obj.xs)
                        cb.onGetDataSuccess(obj.data);
                    else {
                        function.savePreverence(base.ctx,"listarticle",Gson().toJson(res))
                        cb.onGetDataSuccess(res.data);
                    }


            },{ error->
                cb.onGetDataFailed(error.message!!)
            })


    }

    fun readArticle(artcid:String,cb: ObjectResponseInterface<article>){


        disposable=api.readArticle( base.getUser()!!.id,artcid,"1")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(res.body()!=null) {
                    cb.onSuccess(res.body()!!);
                }else{
                    cb.onFailed(res.errorBody()!!.string())
                }
            },{ error->
                cb.onFailed(error.message!!)
            })


    }





}