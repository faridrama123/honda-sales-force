package com.langit7.hondasalesforce.presenter.logic

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.langit7.hondasalesforce.API.api
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.*
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.view.activity.BaseActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class VideoPresenter{
    private var disposable: Disposable? = null
    var base:BaseActivity
    var api:api

    constructor(b:BaseActivity,ap:api){
        this.base=b
        this.api=ap
    }



    fun getVideo(cb: DataListInterface<video>){
        var xs="0"

        val listType = object : TypeToken<baseresponse<List<video>>>() {}.getType()
        var sobj= function.getPreverence(base.ctx,"videodata")
        var obj= Gson().fromJson<baseresponse<List<video>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }

        disposable=api.getVideo(xs)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(obj!=null && res.xs==obj.xs)
                    cb.onGetDataSuccess(obj.data);
                else {
                    function.savePreverence(base.ctx,"videodata",Gson().toJson(res))
                    cb.onGetDataSuccess(res.data);
                }
            },{ error->
                cb.onGetDataFailed(error.message!!)
            })


    }

    fun getVideocat(cb: DataListInterface<videocat>){
        var xs="0"

        val listType = object : TypeToken<baseresponse<List<videocat>>>() {}.getType()
        var sobj= function.getPreverence(base.ctx,"videocatdata")
        var obj= Gson().fromJson<baseresponse<List<videocat>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }

        disposable=api.getVideoCat(xs)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(obj!=null && res.xs==obj.xs)
                    cb.onGetDataSuccess(obj.data);
                else {
                    function.savePreverence(base.ctx,"videocatdata",Gson().toJson(res))
                    cb.onGetDataSuccess(res.data);
                }
            },{ error->
                cb.onGetDataFailed(error.message!!)
            })


    }



    fun getVideobyCat(lsvideo:List<video>, cat:String):List<video>{
        var  vids= ArrayList<video>()
        for(v in lsvideo){
            if(v.category.equals(cat,true))
                vids.add(v)
        }


        return vids
    }
}