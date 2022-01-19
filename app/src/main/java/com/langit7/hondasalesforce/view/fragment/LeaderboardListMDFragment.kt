package com.langit7.hondasalesforce.view.fragment

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.leaderboard
import com.langit7.hondasalesforce.view.activity.BaseActivity
import com.langit7.hondasalesforce.view.activity.LeaderboardActivity
import java.util.*
import kotlin.collections.ArrayList


class LeaderboardListMDFragment : Fragment(), BaseFragmentInterface {

    override var title: String=""
    var header: String=""
    var isuser=true
    var limit=true

    lateinit var act:BaseActivity;

    var lsL=ArrayList<leaderboard>()
    var lsMaster=ArrayList<leaderboard>()
    lateinit var llc:LinearLayout


    var startfrom=0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_list_leaderboard_md, container, false)

        act=activity as BaseActivity


//        adp= LeaderboardListAdapter(lsL,act)
//        val rv=rootView.findViewById<RecyclerView>(R.id.rv)
//        rv.layoutManager = LinearLayoutManager(act)
//        rv.adapter = adp;
//        adp.notifyDataSetChanged()
        llc=rootView.findViewById<LinearLayout>(R.id.llcontainer)
        var th=rootView.findViewById<TextView>(R.id.theader)
        var trank=rootView.findViewById<TextView>(R.id.tposition)


        if(header.isEmpty())
            th.visibility=View.GONE
        else {
            th.visibility = View.VISIBLE
            th.setText(header)
        }

        var usr= function.getUser(act)

        lsL.clear()
        lsL.addAll(lsMaster)


//        Log.e("LSL",lsL.size.toString())
        var rank=1
        for(a in lsL){
            if(a.firstName.equals(usr!!.firstName) && a.lastName.equals(usr!!.lastName)) {
                trank.setText("Ranking Anda di Main Dealer: "+rank.toString() +" of "+ lsL.size.toString())
                break
            }
            rank++
        }
        if(!isuser)
            trank.visibility=View.GONE


        renderLead(lsL)




        var llsearch=rootView.findViewById<LinearLayout>(R.id.llsearch)
        var etname=rootView.findViewById<EditText>(R.id.etname)
        var etkab=rootView.findViewById<EditText>(R.id.etkab)
        var tclear=rootView.findViewById<TextView>(R.id.tclear)
        var tsearch=rootView.findViewById<TextView>(R.id.tsearch)

        tclear.setOnClickListener {
            etname.setText("")
            etkab.setText("")
            lsL.clear()
            lsL.addAll(lsMaster)
            renderLead(lsL)
        }

        tsearch.setOnClickListener {
            lsL.clear()
            lsL.addAll(lsMaster)
            if(etname.text.isNotEmpty()) {
                var lsl_=ArrayList<leaderboard>()
                for (a in lsL) {
                    if (a.dealer_name.contains(etname.text, true))
                        lsl_.add(a)
                }
                lsL.clear()
                lsL.addAll(lsl_)
            }

            if(etkab.text.isNotEmpty()){
                var lsl_=ArrayList<leaderboard>()
                for(i in lsL){
                    if(i.city_name.contains(etkab.text,true))
                        lsl_.add(i)
                }
                lsL.clear()
                lsL.addAll(lsl_)
            }

            renderLead(lsL)
        }


        if(!isuser)
            llsearch.visibility=View.VISIBLE
        else
            llsearch.visibility=View.GONE


        return rootView
    }

    fun renderLead(lsL:List<leaderboard>){
        var inflater=layoutInflater
        if(startfrom==0)
        llc.removeAllViews()

        var max=9
        if(lsL.size<10 ||!limit)
            max=lsL.size-1

        if(max-startfrom>19){
            max=19
        }else{
            max=max-startfrom
        }

        for(i in startfrom..(startfrom+max)){
            var q=lsL.get(i)
            var v = inflater.inflate(R.layout.item_leaderboard_md,null)
            val tno=v.findViewById<TextView>(R.id.tno)
            val tname=v.findViewById<TextView>(R.id.tname)
            val tposition=v.findViewById<TextView>(R.id.tposition)
            val tdealercode=v.findViewById<TextView>(R.id.tdealercode)
            val tpoint=v.findViewById<TextView>(R.id.tpoint)
            val tlevel=v.findViewById<TextView>(R.id.tlevel)
            val tkab=v.findViewById<TextView>(R.id.tkab)
            val img=v.findViewById<ImageView>(R.id.imglevel)

            tname?.text=q.firstName + " " + q.lastName
            tpoint?.text=q.pointTotal
            tposition?.text=q.dealer_name
            tdealercode?.text=q.code_dealer + " - " + q.userPosition
            tno?.text=(i+1).toString()
            if(!q.city_name.isNullOrEmpty())
                tkab.setText(q.city_name)

            tlevel.setText(function.getMedalName(q.medal_type))


            if(q.user_chosen_medal_type!=null)
                img.setImageResource(function.getMedalImageLarge(q.user_chosen_medal_type))

            llc.addView(v)
        }
        startfrom=startfrom+max+1
    }

    companion object {

        fun Instantiate(ls:ArrayList<leaderboard>,tit:String,header:String,limit:Boolean=true): LeaderboardListMDFragment {
            val sd = LeaderboardListMDFragment()
            sd.lsMaster=ls
            sd.title=tit
            sd.header=header
            sd.limit=limit

            return sd
        }


        fun InstantiateMD(ls:ArrayList<leaderboard>,tit:String,header:String,limit:Boolean=true): LeaderboardListMDFragment {
            val sd = LeaderboardListMDFragment()
            sd.lsMaster=ls
            sd.title=tit
            sd.header=header
            sd.isuser=false
            sd.limit=limit
            return sd
        }
    }

}

