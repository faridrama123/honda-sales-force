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

class GeneralKnowledgePresenter{
    private var disposable: Disposable? = null
    var base:BaseActivity
    var api:api

    constructor(b:BaseActivity,ap:api){
        this.base=b
        this.api=ap
    }





    fun getServiceTalk(cb: DataListInterface<servicetalk>){
        var xs="0"

        val listType = object : TypeToken<baseresponse<List<servicetalk>>>() {}.getType()
        var sobj= function.getPreverence(base.ctx,"servicetalk")
        var obj= Gson().fromJson<baseresponse<List<servicetalk>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }

        disposable=api.getServiceTalk("0")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(obj!=null && res.xs==obj.xs)
                    cb.onGetDataSuccess(obj.data);
                else {
                    function.savePreverence(base.ctx,"servicetalk",Gson().toJson(res))
                    cb.onGetDataSuccess(res.data);
                }
            },{ error->
                cb.onGetDataFailed(error.message!!)
            })


    }
    fun getCommunity(cb: DataListInterface<community>){

        var xs="0"

        val listType = object : TypeToken<baseresponse<List<community>>>() {}.getType()
        var sobj= function.getPreverence(base.ctx,"communitydata")
        var obj= Gson().fromJson<baseresponse<List<community>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }
        disposable=api.getCommunity(xs)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(obj!=null && res.xs==obj.xs)
                    cb.onGetDataSuccess(obj.data);
                else {
                    function.savePreverence(base.ctx,"communitydata",Gson().toJson(res))
                    cb.onGetDataSuccess(res.data);
                }
            },{ error->
                cb.onGetDataFailed(error.message!!)
            })


    }
    fun getBengkelModifikasi(cb: DataListInterface<bengkelmodif>){

        var xs="0"

        val listType = object : TypeToken<baseresponse<List<bengkelmodif>>>() {}.getType()
        var sobj= function.getPreverence(base.ctx,"bengkelmodif")
        var obj= Gson().fromJson<baseresponse<List<bengkelmodif>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }
        disposable=api.getBengkelModifikasi(xs)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(obj!=null && res.xs==obj.xs)
                    cb.onGetDataSuccess(obj.data);
                else {
                    function.savePreverence(base.ctx,"bengkelmodif",Gson().toJson(res))
                    cb.onGetDataSuccess(res.data);
                }
            },{ error->
                cb.onGetDataFailed(error.message!!)
            })


    }

    fun getCity(provid:String,cb: DataListInterface<region>){
        var xs="0"

        val listType = object : TypeToken<baseresponse<List<region>>>() {}.getType()
        var sobj= function.getPreverence(base.ctx,"city"+provid)
        var obj= Gson().fromJson<baseresponse<List<region>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }
        disposable=api.getCity(xs,provid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(obj!=null && res.xs==obj.xs)
                    cb.onGetDataSuccess(obj.data);
                else {
                    function.savePreverence(base.ctx,"city"+provid,Gson().toJson(res))
                    cb.onGetDataSuccess(res.data);
                }
            },{ error->
                cb.onGetDataFailed(error.message!!)
            })


    }

    fun getProvince(cb: DataListInterface<region>){
        var xs="0"

        val listType = object : TypeToken<baseresponse<List<region>>>() {}.getType()
        var sobj= function.getPreverence(base.ctx,"province")
        var obj= Gson().fromJson<baseresponse<List<region>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }
        disposable=api.getProvince(xs)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(obj!=null && res.xs==obj.xs)
                    cb.onGetDataSuccess(obj.data);
                else {
                    function.savePreverence(base.ctx,"province",Gson().toJson(res))
                    cb.onGetDataSuccess(res.data);
                }
            },{ error->
                cb.onGetDataFailed(error.message!!)
            })


    }

    fun getServiceType(cb: DataListInterface<bengkelmodifservice>){
        var xs="0"

        val listType = object : TypeToken<baseresponse<List<bengkelmodifservice>>>() {}.getType()
        var sobj= function.getPreverence(base.ctx,"bengkelmodifservicedata")
        var obj= Gson().fromJson<baseresponse<List<bengkelmodifservice>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }
        disposable=api.getBengkelModifikasiService(xs)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(obj!=null && res.xs==obj.xs)
                    cb.onGetDataSuccess(obj.data);
                else {
                    function.savePreverence(base.ctx,"bengkelmodifservicedata",Gson().toJson(res))
                    cb.onGetDataSuccess(res.data);
                }
            },{ error->
                cb.onGetDataFailed(error.message!!)
            })


    }
    fun getType(cb: DataListInterface<bengkelmodiftype>){
        var xs="0"

        val listType = object : TypeToken<baseresponse<List<bengkelmodiftype>>>() {}.getType()
        var sobj= function.getPreverence(base.ctx,"bengkelmodiftypedata")
        var obj= Gson().fromJson<baseresponse<List<bengkelmodiftype>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }
        disposable=api.getBengkelModifikasiType(xs)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(obj!=null && res.xs==obj.xs)
                    cb.onGetDataSuccess(obj.data);
                else {
                    function.savePreverence(base.ctx,"bengkelmodiftypedata",Gson().toJson(res))
                    cb.onGetDataSuccess(res.data);
                }
            },{ error->
                cb.onGetDataFailed(error.message!!)
            })


    }


    fun getNOS(cb: DataListInterface<nos>){
        var xs="0"

        val listType = object : TypeToken<baseresponse<List<nos>>>() {}.getType()
        var sobj= function.getPreverence(base.ctx,"nosdata")
        var obj= Gson().fromJson<baseresponse<List<nos>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }
        disposable=api.getNOS(xs)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(obj!=null && res.xs==obj.xs)
                    cb.onGetDataSuccess(obj.data);
                else {
                    function.savePreverence(base.ctx,"nosdata",Gson().toJson(res))
                    cb.onGetDataSuccess(res.data);
                }
            },{ error->
                cb.onGetDataFailed(error.message!!)
            })
    }
    fun getGrooming(cb: DataListInterface<grooming>){
        var xs="0"

        val listType = object : TypeToken<baseresponse<List<grooming>>>() {}.getType()
        var sobj= function.getPreverence(base.ctx,"groomingdata")
        var obj= Gson().fromJson<baseresponse<List<grooming>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }
        disposable=api.getGrooming(xs)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(obj!=null && res.xs==obj.xs)
                    cb.onGetDataSuccess(obj.data);
                else {
                    function.savePreverence(base.ctx,"groomingdata",Gson().toJson(res))
                    cb.onGetDataSuccess(res.data);
                }
            },{ error->
                cb.onGetDataFailed(error.message!!)
            })
    }
    fun getSalesmanship(cb: DataListInterface<productfeature>){
        var xs="0"

        val listType = object : TypeToken<baseresponse<List<productfeature>>>() {}.getType()
        var sobj= function.getPreverence(base.ctx,"salesmanshipdata")
        var obj= Gson().fromJson<baseresponse<List<productfeature>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }
        disposable=api.getSalesmanship(xs)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(obj!=null && res.xs==obj.xs)
                    cb.onGetDataSuccess(obj.data);
                else {
                    function.savePreverence(base.ctx,"salesmanshipdata",Gson().toJson(res))
                    cb.onGetDataSuccess(res.data);
                }
            },{ error->
                cb.onGetDataFailed(error.message!!)
            })
    }
    fun getCS(cb: DataListInterface<productfeature>){
        var xs="0"

        val listType = object : TypeToken<baseresponse<List<productfeature>>>() {}.getType()
        var sobj= function.getPreverence(base.ctx,"csdata")
        var obj= Gson().fromJson<baseresponse<List<productfeature>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }
        disposable=api.getCS(xs)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(obj!=null && res.xs==obj.xs)
                    cb.onGetDataSuccess(obj.data);
                else {
                    function.savePreverence(base.ctx,"csdata",Gson().toJson(res))
                    cb.onGetDataSuccess(res.data);
                }
            },{ error->
                cb.onGetDataFailed(error.message!!)
            })
    }
    fun getCSL(cb: DataListInterface<productfeature>){
        var xs="0"

        val listType = object : TypeToken<baseresponse<List<productfeature>>>() {}.getType()
        var sobj= function.getPreverence(base.ctx,"csldata")
        var obj= Gson().fromJson<baseresponse<List<productfeature>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }
        disposable=api.getCSL(xs)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(obj!=null && res.xs==obj.xs)
                    cb.onGetDataSuccess(obj.data);
                else {
                    function.savePreverence(base.ctx,"csldata",Gson().toJson(res))
                    cb.onGetDataSuccess(res.data);
                }
            },{ error->
                cb.onGetDataFailed(error.message!!)
            })
    }
    fun getCH(cb: DataListInterface<productfeature>){
        var xs="0"

        val listType = object : TypeToken<baseresponse<List<productfeature>>>() {}.getType()
        var sobj= function.getPreverence(base.ctx,"chdata")
        var obj= Gson().fromJson<baseresponse<List<productfeature>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }
        disposable=api.getCH(xs)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(obj!=null && res.xs==obj.xs)
                    cb.onGetDataSuccess(obj.data);
                else {
                    function.savePreverence(base.ctx,"chdata",Gson().toJson(res))
                    cb.onGetDataSuccess(res.data);
                }
            },{ error->
                cb.onGetDataFailed(error.message!!)
            })
    }
    fun getCRM(cb: DataListInterface<productfeature>){
        var xs="0"

        val listType = object : TypeToken<baseresponse<List<productfeature>>>() {}.getType()
        var sobj= function.getPreverence(base.ctx,"crmdata")
        var obj= Gson().fromJson<baseresponse<List<productfeature>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }
        disposable=api.getCRM(xs)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(obj!=null && res.xs==obj.xs)
                    cb.onGetDataSuccess(obj.data);
                else {
                    function.savePreverence(base.ctx,"crmdata",Gson().toJson(res))
                    cb.onGetDataSuccess(res.data);
                }
            },{ error->
                cb.onGetDataFailed(error.message!!)
            })
    }

}