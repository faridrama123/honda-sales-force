package com.langit7.hondasalesforce.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.*
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.DialogUtil
import com.langit7.hondasalesforce.model.cdb
import com.langit7.hondasalesforce.presenter.adapter.CDBListAdapter
import com.langit7.hondasalesforce.presenter.viewInterface.CDBClickListener
import com.langit7.hondasalesforce.presenter.viewInterface.CDBDialogListener
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.activity.CDBListActivity
import java.util.*
import kotlin.collections.ArrayList


class CDBFragment : Fragment(), BaseFragmentInterface {

    override var title: String=""

    lateinit var act: CDBListActivity;
    lateinit var lsCDB:List<cdb>
    lateinit var lsCDB_:MutableList<cdb>

    lateinit var adp:CDBListAdapter

    var clickable=false



    lateinit var spstatus : Spinner
    lateinit var spdate : Spinner
    lateinit var etsearch : EditText
    lateinit var tclear : TextView
    lateinit var tsave : TextView
    lateinit var rgsort : RadioGroup
    lateinit var optbyname : RadioButton
    lateinit var optbystatus : RadioButton
    lateinit var optbytime : RadioButton

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_cdb, container, false)

        act=activity as CDBListActivity

        val rv=rootView.findViewById<RecyclerView>(R.id.rv)

        spstatus=rootView.findViewById(R.id.spstatus)
        spdate=rootView.findViewById(R.id.spdate)
        etsearch=rootView.findViewById(R.id.etsearch)
        tclear=rootView.findViewById(R.id.tclear)
        tsave=rootView.findViewById(R.id.tsave)
        rgsort=rootView.findViewById(R.id.rgsort)
        optbyname=rootView.findViewById(R.id.optbyname)
        optbystatus=rootView.findViewById(R.id.optbystatus)
        optbytime=rootView.findViewById(R.id.optbytime)


        var statusfilter= mutableListOf<String>("Semua Status","COLD","HOT","WARM")
        var statusadp=ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_item,statusfilter)
        spstatus.adapter=statusadp

        var datefilter= mutableListOf<String>("Semua waktu","Follow Up bulan ini","Waktu input bulan ini")
        var dateadp=ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_item,datefilter)
        spdate.adapter=dateadp

        tclear.setOnClickListener{
            spstatus.setSelection(0)
            spdate.setSelection(0)
            etsearch.setText("")
            filter()
        }

        tsave.setOnClickListener {
            filter()
        }

        rgsort.setOnCheckedChangeListener(object :RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
                sort()
            }

        })


        rv.layoutManager = LinearLayoutManager(act)


        adp= CDBListAdapter(
            lsCDB_,
            act,
            object:CDBClickListener{
                override fun onItemClick(res: cdb) {
                    if(clickable){
                        DialogUtil.createCDBDialog(act,res,object :CDBDialogListener{
                            override fun onUpdate(date: String,time:String, status: String) {
                                act.presenter.updateCDB(res.id!!,status,date,time,object : ObjectResponseInterface<String>{
                                    override fun onSuccess(re: String) {
                                        var st=""
                                        if(status.equals("0"))
                                            st="COLD"
                                        else if(status.equals("1"))
                                            st="HOT"
                                        else
                                            st="WARM"

                                        DialogUtil.createCDBResultDialog(act,res,"Tanggal follow up baru:<br>"+date +" "+ time+"<br>Status:<br>"+st).setOnDismissListener {

                                            val ii=Intent(act,CDBListActivity::class.java)
                                            ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                            act.startActivity(ii)
                                            act.finish()

                                        }
                                    }

                                    override fun onFailed(msg: String) {
                                    }

                                })
                            }

                            override fun ondeal(id: String) {
                                act.presenter.updateCDBDeal(id,"1",object :ObjectResponseInterface<String>{
                                    override fun onSuccess(re: String) {
                                        var st="DEAL"

                                        DialogUtil.createCDBResultDialog(act,res,"<br>Status:<br>"+st).setOnDismissListener {

                                            val ii=Intent(act,CDBListActivity::class.java)
                                            ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                            act.startActivity(ii)
                                            act.finish()

                                        }
                                    }

                                    override fun onFailed(msg: String) {
                                    }

                                })
                            }

                            override fun onnodeal(id: String) {
                                act.presenter.updateCDBDeal(id,"2",object :ObjectResponseInterface<String>{
                                    override fun onSuccess(re: String) {
                                        var st="NO DEAL"

                                        DialogUtil.createCDBResultDialog(act,res,"<br>Status:<br>"+st).setOnDismissListener {

                                            val ii=Intent(act,CDBListActivity::class.java)
                                            ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                            act.startActivity(ii)
                                            act.finish()

                                        }
                                    }

                                    override fun onFailed(msg: String) {
                                    }

                                })
                            }

                        })



                    }
            }


                override fun onCallClick(res: cdb) {
                    act.phone=res.telp!!
                    act.checkPermission()
                }

            }
        )
        // Access the RecyclerView Adapter and load the data into it
        rv.adapter = adp;

        optbystatus.isChecked=true
        filter()




        return rootView
    }

    fun filter(){
        lsCDB_.clear()
        lsCDB_.addAll(lsCDB)
        filterstatus(lsCDB_)
        filterdate(lsCDB_)
        filtertext(lsCDB_)

        sort()
    }

    fun sort(){

        if(optbyname.isChecked)
            lsCDB_.sortBy { it.nama }
        else if (optbytime.isChecked)
            lsCDB_.sortBy { it.datefollowup }
        else if (optbystatus.isChecked)
            lsCDB_.sortWith(compareBy ({ it.statusindex },{it.datefollowup}))

        adp.notifyDataSetChanged()
    }

    fun filterstatus(lsCDB:List<cdb>){
        var lsCDB_= ArrayList<cdb>()
        if(spstatus.selectedItemPosition==0){//all
            lsCDB_.addAll(lsCDB)
        }
        if(spstatus.selectedItemPosition==1){//cold
            for(a in lsCDB){
                if(a.status!!.equals("0"))
                    lsCDB_.add(a)
            }
        }
        if(spstatus.selectedItemPosition==2){//hot
            for(a in lsCDB){
                if(a.status!!.equals("1"))
                    lsCDB_.add(a)
            }
        }
        if(spstatus.selectedItemPosition==3){//warm
            for(a in lsCDB){
//                Log.e("st",a.status!!)
                if(a.status!!.equals("2"))
                    lsCDB_.add(a)
            }
        }

        this.lsCDB_.clear()
        this.lsCDB_.addAll(lsCDB_)

    }
    fun filterdate(lsCDB:List<cdb>){
        var lsCDB_= ArrayList<cdb>()
        var thismonth=Calendar.getInstance()
        thismonth.set(Calendar.DAY_OF_MONTH,4)

        if(spdate.selectedItemPosition==0){
            lsCDB_.addAll(lsCDB)
        }
        if(spdate.selectedItemPosition==1){
            for(a in lsCDB){
                if(a.datefollowup!!.after(Date(thismonth.timeInMillis)))
                    lsCDB_.add(a)
            }
        }
        if(spdate.selectedItemPosition==2){
            for(a in lsCDB){
                if(a.createddate!!.after(Date(thismonth.timeInMillis)))
                    lsCDB_.add(a)
            }
        }

        this.lsCDB_.clear()
        this.lsCDB_.addAll(lsCDB_)
    }
    fun filtertext(lsCDB:List<cdb>){
        var lsCDB_= ArrayList<cdb>()
        var s=etsearch.text.toString()
        if(s.isNotEmpty()) {
            for (a in lsCDB) {
                if (a.nama!!.toLowerCase().contains(s.toLowerCase()) || a.producttype!!.toLowerCase().contains(s.toLowerCase()) || a.alamat!!.toLowerCase().contains(s.toLowerCase()) || a.telp!!.toLowerCase().contains(s.toLowerCase()))
                    lsCDB_.add(a)
            }
        }else
            lsCDB_.addAll(lsCDB)

        this.lsCDB_.clear()
        this.lsCDB_.addAll(lsCDB_)
    }

    companion object {
        fun Instantiate(tit:String,lsartc:List<cdb>,clickable:Boolean): CDBFragment {
            val sd = CDBFragment()
            sd.title=tit
            sd.lsCDB=lsartc
            sd.clickable=clickable
            sd.lsCDB_=ArrayList<cdb>()
            return sd
        }
    }

}

