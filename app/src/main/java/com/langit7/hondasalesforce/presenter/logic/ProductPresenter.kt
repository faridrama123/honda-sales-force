package com.langit7.hondasalesforce.presenter.logic

import android.util.Log
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

class ProductPresenter{
    private var disposable: Disposable? = null
    var base:BaseActivity
    var api:api

    constructor(b:BaseActivity,ap:api){
        this.base=b
        this.api=ap
    }

    fun getProductbyCat(cat:String,cb: DataListInterface<product>){

        var xs="0"

        val listType = object : TypeToken<baseresponse<List<product>>>() {}.getType()
        var sobj= function.getPreverence(base.ctx,"listproduct"+cat)
        var obj= Gson().fromJson<baseresponse<List<product>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }

        disposable=api.getProductByCategory(cat,xs)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->

                if(obj!=null && res.xs==obj.xs)
                    cb.onGetDataSuccess(obj.data);
                else {
                    function.savePreverence(base.ctx,"listproduct"+cat,Gson().toJson(res))
                    cb.onGetDataSuccess(res.data);
                }




            },{ error->
                cb.onGetDataFailed(error.message!!)
            })


    }


    fun getCat(cb: DataListInterface<productcategory>){

        var xs="0"

        val listType = object : TypeToken<baseresponse<List<productcategory>>>() {}.getType()
        var sobj= function.getPreverence(base.ctx,"productcat")
        var obj= Gson().fromJson<baseresponse<List<productcategory>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }



        disposable=api.getProductCategory(xs)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->

                if(obj!=null && res.xs==obj.xs)
                    cb.onGetDataSuccess(obj.data);
                else {
                    function.savePreverence(base.ctx,"productcat",Gson().toJson(res))
                    cb.onGetDataSuccess(res.data);
                }
            },{ error->
                cb.onGetDataFailed(error.message!!)
            })


    }

    fun getCusPart(cb: DataListInterface<cuspart>){




        disposable=api.getCustomizingPatrs("0")
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
    fun getProductAccessories(productid:String,cb: DataListInterface<productaccessories>){


        var xs="0"

        val listType = object : TypeToken<baseresponse<List<productaccessories>>>() {}.getType()
        var sobj= function.getPreverence(base.ctx,"productacc"+productid)
        var obj= Gson().fromJson<baseresponse<List<productaccessories>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }

        disposable=api.getProductAccessories(productid,xs)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(obj!=null && res.xs==obj.xs)
                    cb.onGetDataSuccess(obj.data);
                else {
                    function.savePreverence(base.ctx,"productacc"+productid,Gson().toJson(res))
                    cb.onGetDataSuccess(res.data);
                }
            },{ error->
                cb.onGetDataFailed(error.message!!)
            })

    }

    fun getProductFeature(productid:String,gen:String,age:String,cb: DataListInterface<productfeature>){

        var ikn=""

        if(age.isNotEmpty() || gen.isNotEmpty())
            ikn="2"

        var xs="0"

        val listType = object : TypeToken<baseresponse<List<productfeature>>>() {}.getType()
        var sobj= function.getPreverence(base.ctx,"productfea"+productid+"_"+gen+"_"+age+"_"+ikn)
        var obj= Gson().fromJson<baseresponse<List<productfeature>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }


        disposable=api.getFeature(productid,xs,gen,age,ikn)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(obj!=null && res.xs==obj.xs)
                    cb.onGetDataSuccess(obj.data);
                else {
                    function.savePreverence(base.ctx,"productfea"+productid+"_"+gen+"_"+age+"_"+ikn,Gson().toJson(res))
                    cb.onGetDataSuccess(res.data);
                }
            },{ error->
                cb.onGetDataFailed(error.message!!)
            })

    }

    fun getSalesTalk(productid:String,cb: DataListInterface<salestalk>){
        var xs="0"

        val listType = object : TypeToken<baseresponse<List<salestalk>>>() {}.getType()
        var sobj= function.getPreverence(base.ctx,"productsalestalk"+productid)
        var obj= Gson().fromJson<baseresponse<List<salestalk>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }
        disposable=api.getSalesTalk(xs,productid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(obj!=null && res.xs==obj.xs)
                    cb.onGetDataSuccess(obj.data);
                else {
                    function.savePreverence(base.ctx,"productsalestalk"+productid,Gson().toJson(res))
                    cb.onGetDataSuccess(res.data);
                }
            },{ error->
                cb.onGetDataFailed(error.message!!)
            })

    }

    fun getSpesificationDetail(productid: String, cb: DataListInterface<spesification>){

        var xs="0"

        val listType = object : TypeToken<baseresponse<List<spesification>>>() {}.getType()
        var sobj= function.getPreverence(base.ctx,"productspesification"+productid)
        var obj= Gson().fromJson<baseresponse<List<spesification>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }

        disposable=api.getSpesificationDetail(productid, xs)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(obj!=null && res.xs==obj.xs)
                    cb.onGetDataSuccess(obj.data);
                else {
                    function.savePreverence(base.ctx,"productspesification"+productid,Gson().toJson(res))
                    cb.onGetDataSuccess(res.data);
                }
            }, {
                error->
                cb.onGetDataFailed(error.message!!)
            })
    }

    fun getSalesProgram( cb: DataListInterface<salesprogram>){

        var xs="0"

        val listType = object : TypeToken<baseresponse<List<salesprogram>>>() {}.getType()
        var sobj= function.getPreverence(base.ctx,"salesprogram")
        var obj= Gson().fromJson<baseresponse<List<salesprogram>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }
        disposable=api.getSalesProgram(xs, "","")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(obj!=null && res.xs==obj.xs)
                    cb.onGetDataSuccess(obj.data);
                else {
                    function.savePreverence(base.ctx,"salesprogram",Gson().toJson(res))
                    cb.onGetDataSuccess(res.data);
                }
            }, {
                    error->
                cb.onGetDataFailed(error.message!!)
            })
    }



    fun getProductRecomendation(gen:String,age:String,ikn:String,cb: DataListInterface<product>){
        var xs="0"

        val listType = object : TypeToken<baseresponse<List<product>>>() {}.getType()
        var sobj= function.getPreverence(base.ctx,"productrecomendation"+"_"+gen+"_"+age)
        var obj= Gson().fromJson<baseresponse<List<product>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }

        disposable=api.getProductRecomendation(xs,"","",gen,age,ikn)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(obj!=null && res.xs==obj.xs) {
                    cb.onGetDataSuccess(obj.data);
                }else {
                    function.savePreverence(base.ctx,"productrecomendation"+"_"+gen+"_"+age,Gson().toJson(res))
                    cb.onGetDataSuccess(res.data);
                }
            },{ error->
                cb.onGetDataFailed(error.message!!)
            })

    }

    fun getImageComparation(cb: DataListInterface<salesprogram>){
        var xs="0"

        val listType = object : TypeToken<baseresponse<List<salesprogram>>>() {}.getType()
        var sobj= function.getPreverence(base.ctx,"imagecomparation")
        var obj= Gson().fromJson<baseresponse<List<salesprogram>>>(sobj,listType)

        if(obj!=null && !obj.xs.isNullOrEmpty()){
            xs=obj.xs
        }
        disposable=api.getListPoster(xs, "","")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res->
                if(obj!=null && res.xs==obj.xs)
                    cb.onGetDataSuccess(obj.data);
                else {
                    function.savePreverence(base.ctx,"imagecomparation",Gson().toJson(res))
                    cb.onGetDataSuccess(res.data);
                }
            }, {
                    error->
                cb.onGetDataFailed(error.message!!)
            })
    }
}