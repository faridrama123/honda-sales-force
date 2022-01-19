package com.langit7.hondasalesforce.presenter.logic

import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.*
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.activity.BaseActivity
import io.realm.Realm
import java.lang.Exception
import java.util.*

class CDBPresenter{
    val DATEFORMAT="dd-MMM-yyyy"
    var base:BaseActivity


    constructor(b:BaseActivity){
        this.base=b
    }



    fun getCDB(fustatus:String,cb: DataListInterface<cdb>){


        try {
            var res=cdb.getCDB(base.getUser()!!.id,Realm.getDefaultInstance(),fustatus)
            cb.onGetDataSuccess(res)

        }catch (e:Exception){
            cb.onGetDataFailed(e.message!!)
        }



    }

    fun addCDB(nama:String, alamat:String, telp:String, status:String, type:String, email:String, datefollowup: String,timefollowup:String, cb: ObjectResponseInterface<String>){

        var dfu=function.parseDate(datefollowup+" "+timefollowup,DATEFORMAT+" HH:mm")
            if(!function.isEmailValid(email)){

                cb.onFailed("Email tidak sesuai")
                return
            }

        var cn=Calendar.getInstance()
            cn.add(Calendar.HOUR,1)
        var nexthour=cn.timeInMillis
        if(!status.equals("0") &&(dfu==null||dfu.before(Date(nexthour)))){

            cb.onFailed("Waktu Followup harus lebih besar dari satu jam kedepan dan kurang dari enam bulan kedepan")
            return
        }


            var res=cdb.addCDB(base.getUser()!!.id,nama,alamat,telp,status,type,email, dfu,Realm.getDefaultInstance())
            if(res) {
                cb.onSuccess("")
            }else{
                cb.onFailed("")
            }


    }


    fun updateCDB(id:String, status:String,date:String,time:String, cb: ObjectResponseInterface<String>){

        var res=cdb.updateCDB(id,status,function.parseDate(date+" "+time,DATEFORMAT+" HH:mm"),Realm.getDefaultInstance())
        if(res) {
            cb.onSuccess("")
        }else{
            cb.onFailed("")
        }


    }
    fun updateCDBDeal(id:String, fustatus:String, cb: ObjectResponseInterface<String>){

        var res=cdb.updateCDBFUStatus(id,fustatus,Realm.getDefaultInstance())
        if(res) {
            cb.onSuccess("")
        }else{
            cb.onFailed("")
        }


    }


    fun removeCDB(id:String,  cb: ObjectResponseInterface<String>){

        var res=cdb.removeCDB(id,Realm.getDefaultInstance())
        if(res) {
            cb.onSuccess("")
        }else{
            cb.onFailed("")
        }


    }
}