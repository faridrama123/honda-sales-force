package com.langit7.hondasalesforce.presenter.logic

import android.app.Activity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.langit7.hondasalesforce.API.api
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.apparel
import com.langit7.hondasalesforce.model.apparelcategory
import com.langit7.hondasalesforce.model.baseresponse
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ApparelPresenter{
    private var disposable: Disposable? = null
    var base: Activity
    var api:api

    constructor(b:Activity,ap:api){
        this.base=b
        this.api=ap
    }

    fun getApparelbyCat(cat:String,cb: DataListInterface<apparel>){

        var xs="0"

        val listType = object : TypeToken<baseresponse<List<apparel>>>() {}.getType()
        var sobj= function.getPreverence(base,"appareldata"+cat)
        var obj= Gson().fromJson<baseresponse<List<apparel>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }
        disposable=api.getApparelByCategory(cat,xs)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->

                if(obj!=null && res.xs==obj.xs)
                    cb.onGetDataSuccess(obj.data);
                else {
                    function.savePreverence(base,"appareldata"+cat,Gson().toJson(res))
                    cb.onGetDataSuccess(res.data);
                }
            },{ error->
                cb.onGetDataFailed(error.message!!)
            })


    }


    fun getApparelCat(cb: DataListInterface<apparelcategory>){

        var xs="0"

        val listType = object : TypeToken<baseresponse<List<apparelcategory>>>() {}.getType()
        var sobj= function.getPreverence(base,"apparelcategorydata")
        var obj= Gson().fromJson<baseresponse<List<apparelcategory>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }
        disposable=api.getApparelCategory(xs)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->

                if(obj!=null && res.xs==obj.xs)
                    cb.onGetDataSuccess(obj.data);
                else {
                    function.savePreverence(base,"apparelcategorydata",Gson().toJson(res))
                    cb.onGetDataSuccess(res.data);
                }
            },{ error->
                cb.onGetDataFailed(error.message!!)
            })


    }


    fun getApparelbyId(id:String,cb: ObjectResponseInterface<apparel>){


        disposable=api.getApparelbyId(id,"0")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                //if(res.data.size>0 ) {
                cb.onSuccess(res.data);
                //}
            },{ error->
                cb.onFailed(error.message!!)
            })


    }

    fun getApparelbyUrl(url:String,cb: ObjectResponseInterface<apparel>){


        disposable=api.getApparelbyUrl(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                //if(res.data.size>0 ) {
                cb.onSuccess(res.data);
                //}


            },{ error->
                cb.onFailed(error.message!!)
            })


    }
}