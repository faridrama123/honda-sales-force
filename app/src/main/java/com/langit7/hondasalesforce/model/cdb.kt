package com.langit7.hondasalesforce.model

import com.langit7.hondasalesforce.Util.function
import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class cdb : RealmObject() {

    @PrimaryKey
    var id: String? = null
    var uid: String? = null
    var nama: String? = null
    var alamat: String? = null
    var telp: String? = null
    var status: String? = null
    var producttype: String? = null
    var email: String? = null
    var datefollowup: Date? = null
    var fustatus: String? = null
    var createddate: Date? = null
    var statusindex: Int = 0


    companion object {


        fun addCDB(
            uid:String,
            nama: String,
            alamat: String,
            telp: String,
            status: String,
            type: String,
            email: String,
            datefollowup: Date?,
            mRealm: Realm
        ): Boolean {
            mRealm.beginTransaction()

            try {

                var c = mRealm.createObject(cdb::class.java, Calendar.getInstance().timeInMillis.toString())
//                c.id=Calendar.getInstance().timeInMillis.toString()
                c.uid=uid
                c.nama = nama
                c.alamat = alamat
                c.telp = telp
                c.status = status
                c.producttype = type
                c.email = email
                c.datefollowup = datefollowup
                c.fustatus = "0"
                c.createddate= Date()

                if(status.equals("1"))
                    c.statusindex= 0
                else if(status.equals("2"))
                    c.statusindex= 1
                else
                    c.statusindex=3

                mRealm.commitTransaction()
                return true

            } catch (e: Exception) {
                e.printStackTrace()
                mRealm.cancelTransaction()
                return false
            }
        }

        fun getCDB(uid:String,mRealm: Realm, fustatus: String): List<cdb> {
            var res = ArrayList<cdb>()


            mRealm.beginTransaction()

            var n=Calendar.getInstance()
            n.add(Calendar.DAY_OF_MONTH,-1)
            var yesterday=n.timeInMillis
            var q = mRealm.where(cdb::class.java)
                .equalTo("fustatus", fustatus)
                .and()
                .equalTo("uid",uid)
                .and()
                .beginGroup()
                    .notEqualTo("status","0")
                    .or()
                    .beginGroup()
                        .equalTo("status","0")
                        .and()
                        .greaterThan("createddate",Date(yesterday))
                    .endGroup()
                .endGroup()

            var chts = q.findAll()

            if (!chts.isEmpty()) {
                res.addAll(chts)
            }
            mRealm.commitTransaction()

            return res
        }

        fun removeCDB(id: String, mRealm: Realm): Boolean {
            mRealm.beginTransaction()
            try {
                var chts = mRealm.where(cdb::class.java).equalTo("id", id).findAll()
                //var chts = mRealm.where(woModel::class.java).findAll()
                if (!chts.isEmpty()) {
                    for (i in chts.indices.reversed()) {
                        chts.get(i)!!.deleteFromRealm()
                    }
                }
                mRealm.commitTransaction()
                return true
            } catch (e: Exception) {
                mRealm.cancelTransaction()
                return false
            }
        }

        fun updateCDB(id: String, status: String, date: Date?, mRealm: Realm): Boolean {
            mRealm.beginTransaction()
            try {
                var chts = mRealm.where(cdb::class.java).equalTo("id", id).findAll()
                //var chts = mRealm.where(woModel::class.java).findAll()
                if (!chts.isEmpty()) {
                    for (i in chts.indices.reversed()) {
                        chts.get(i)!!.status = status
                        chts.get(i)!!.datefollowup = date
                    }
                }
                mRealm.commitTransaction()
                return true
            } catch (e: Exception) {
                mRealm.cancelTransaction()
                return false
            }
        }

        fun updateCDBFUStatus(id: String, fustatus: String, mRealm: Realm): Boolean {
            mRealm.beginTransaction()
            try {
                var chts = mRealm.where(cdb::class.java).equalTo("id", id).findAll()
                //var chts = mRealm.where(woModel::class.java).findAll()
                if (!chts.isEmpty()) {
                    for (i in chts.indices.reversed()) {
                        chts.get(i)!!.fustatus = fustatus
                    }
                }
                mRealm.commitTransaction()
                return true
            } catch (e: Exception) {
                mRealm.cancelTransaction()
                return false
            }
        }
    }


}