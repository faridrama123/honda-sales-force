package com.langit7.hondasalesforce.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import java.util.ArrayList
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.widget.*
import com.bumptech.glide.request.RequestOptions
import com.glide.slider.library.SliderLayout
import com.glide.slider.library.SliderTypes.TextSliderView
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.*
import com.langit7.hondasalesforce.presenter.adapter.*
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.presenter.viewInterface.ItemClickListener
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.activity.BaseActivity
import com.langit7.hondasalesforce.view.activity.LeaderboardActivity
import com.langit7.hondasalesforce.view.activity.MainActivity
import kotlinx.android.synthetic.main.textslider.*


class LeaderboardHistoryListFragment : Fragment(), BaseFragmentInterface {


    override var title: String=""

    lateinit var act: BaseActivity;

    var lsL=ArrayList<leaderboardhistory>()
    lateinit var adp: LeaderboardHistoryListAdapter
    lateinit var llc: LinearLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_leaderboard_history, container, false)

        act=activity as BaseActivity
        llc=rootView.findViewById<LinearLayout>(R.id.llcontainer)

//        adp= LeaderboardHistoryListAdapter(lsL,act)
//        val rv=rootView.findViewById<RecyclerView>(R.id.rv)
//        rv.layoutManager = LinearLayoutManager(act)
//        rv.adapter = adp;
//        adp.notifyDataSetChanged()
        renderdata(lsL)


        return rootView
    }

    fun renderdata(lsL:List<leaderboardhistory>){

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

            tname?.text=q.created_date
            tpoint?.text=q.total_point
            tlevel?.text=function.getMedalName(q.medal_type)
            tno?.visibility=View.GONE
            tposition?.visibility=View.GONE

            img.setImageResource(function.getMedalImageLarge(q.medal_type))

            llc.addView(v)
        }
    }

    companion object {

        fun Instantiate(ls:ArrayList<leaderboardhistory>,tit:String): LeaderboardHistoryListFragment {
            val sd = LeaderboardHistoryListFragment()
            sd.lsL=ls
            sd.title=tit
            return sd
        }
    }

}

