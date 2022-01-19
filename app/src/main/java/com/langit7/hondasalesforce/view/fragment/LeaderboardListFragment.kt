package com.langit7.hondasalesforce.view.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.leaderboard
import com.langit7.hondasalesforce.view.activity.BaseActivity
import com.langit7.hondasalesforce.view.activity.LeaderboardActivity
import com.langit7.hondasalesforce.view.activity.MainActivity
import java.util.*


class LeaderboardListFragment : Fragment(), BaseFragmentInterface {

    private lateinit var llc: LinearLayout

    override var title: String=""
    var header: String=""


    lateinit var act: BaseActivity;

    var lsL=ArrayList<leaderboard>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_list_leaderboard, container, false)

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
        var rank=1
        for(a in lsL){
            if(a.firstName.equals(usr!!.firstName) && a.lastName.equals(usr!!.lastName)) {
                    trank.setText("Ranking Anda di Dealer: "+rank.toString() +" of "+ lsL.size.toString())
                break
            }
            rank++
        }


        renderdata(lsL)


        return rootView
    }

    fun renderdata(lsL:List<leaderboard>){
        llc.removeAllViews()
        var i=0
        for(q in lsL){
            i++
            var v = act.layoutInflater.inflate(R.layout.item_leaderboard,null)
            val tno=v.findViewById<TextView>(R.id.tno)
            val tname=v.findViewById<TextView>(R.id.tname)
            val tposition=v.findViewById<TextView>(R.id.tposition)
            val tpoint=v.findViewById<TextView>(R.id.tpoint)
            val tlevel=v.findViewById<TextView>(R.id.tlevel)
            val img=v.findViewById<ImageView>(R.id.imglevel)

            tname?.text=q.firstName + " " + q.lastName
            tpoint?.text=q.pointTotal
            tposition?.text=q.userPosition
            tno?.text=i.toString()
            tlevel.setText(function.getMedalName(q.medal_type))

            if(q.user_chosen_medal_type!=null)
                img.setImageResource(function.getMedalImageLarge(q.user_chosen_medal_type))

            llc.addView(v)
        }
    }

    companion object {

        fun Instantiate(ls:ArrayList<leaderboard>,tit:String,header:String): LeaderboardListFragment {
            val sd = LeaderboardListFragment()
            sd.lsL=ls
            sd.title=tit
            sd.header=header
            return sd
        }
    }

}

