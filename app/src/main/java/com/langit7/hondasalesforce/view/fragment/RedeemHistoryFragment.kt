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
import com.langit7.hondasalesforce.model.*
import com.langit7.hondasalesforce.presenter.adapter.ArticleListAdapter
import com.langit7.hondasalesforce.presenter.adapter.NotifAdapter
import com.langit7.hondasalesforce.presenter.adapter.QuisHistoryListAdapter
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.presenter.viewInterface.ItemClickListener
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.activity.MainActivity
import kotlinx.android.synthetic.main.textslider.*


class RedeemHistoryFragment : Fragment(), BaseFragmentInterface {

    override var title: String="Histori Poin"

    lateinit var act:MainActivity;

    var lsHR=ArrayList<redeem>()
    lateinit var adp:QuisHistoryListAdapter

    lateinit var llc : LinearLayout
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_list, container, false)

        act=activity as MainActivity




//        adp= QuisHistoryListAdapter(lsHQ,act)
//        val rv=rootView.findViewById<RecyclerView>(R.id.rv)
//        rv.layoutManager = LinearLayoutManager(act)
//        rv.adapter = adp;
//        adp.notifyDataSetChanged()

        llc=rootView.findViewById<LinearLayout>(R.id.llcontainer)
        renderdata(lsHR)


        return rootView
    }

    fun renderdata(lsHR:List<redeem>){
        try {
            llc.removeAllViews()
            var i=0
            for(r in lsHR){
                i++
                var v = act.layoutInflater.inflate(R.layout.item_history_redeem,null)
                var tn=v.findViewById<TextView>(R.id.tno)
                var tt=v.findViewById<TextView>(R.id.ttitle)
                var ts=v.findViewById<TextView>(R.id.tscore)

                tn.setText(r.createdDate)
                tt.setText(r.title)
                ts.setText(r.redeemCode)

                llc.addView(v)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        fun Instantiate(ls:ArrayList<redeem>): RedeemHistoryFragment {
            val sd = RedeemHistoryFragment()
            sd.lsHR=ls
            return sd
        }
        fun Instantiate(ls:ArrayList<redeem>,tit:String): RedeemHistoryFragment {
            val sd = RedeemHistoryFragment()
            sd.lsHR=ls
            sd.title=tit
            return sd
        }
    }

}

