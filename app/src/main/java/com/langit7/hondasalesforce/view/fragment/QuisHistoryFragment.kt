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
import com.langit7.hondasalesforce.model.article
import com.langit7.hondasalesforce.model.baseresponse
import com.langit7.hondasalesforce.model.notif
import com.langit7.hondasalesforce.model.quizresponse
import com.langit7.hondasalesforce.presenter.adapter.ArticleListAdapter
import com.langit7.hondasalesforce.presenter.adapter.NotifAdapter
import com.langit7.hondasalesforce.presenter.adapter.QuisHistoryListAdapter
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.presenter.viewInterface.ItemClickListener
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.activity.MainActivity
import com.langit7.hondasalesforce.view.activity.QuizHistoryDetailActivity
import kotlinx.android.synthetic.main.textslider.*


class QuisHistoryFragment : Fragment(), BaseFragmentInterface {

    override var title: String="Histori Kuis"

    lateinit var act:MainActivity;

    var lsHQ=ArrayList<quizresponse>()
    lateinit var adp:QuisHistoryListAdapter

    lateinit var rootView:View
    lateinit var inflater:LayoutInflater

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        this.inflater=inflater
        rootView = inflater.inflate(R.layout.fragment_kuis_history, container, false)

        act=activity as MainActivity




//        adp= QuisHistoryListAdapter(lsHQ,act)
//        val rv=rootView.findViewById<RecyclerView>(R.id.rv)
//        rv.layoutManager = LinearLayoutManager(act)
//        rv.adapter = adp;
//        adp.notifyDataSetChanged()

        renderdata(lsHQ)


        return rootView
    }

    fun renderdata(lsHQ:List<quizresponse>){
        var llc=rootView.findViewById<LinearLayout>(R.id.llcontainer)
        llc.removeAllViews()
        var i=0
        for(q in lsHQ){
            i++
            var v = inflater.inflate(R.layout.item_history_quis,null)
            var tn=v.findViewById<TextView>(R.id.tno)
            var tt=v.findViewById<TextView>(R.id.ttitle)
            var ts=v.findViewById<TextView>(R.id.tscore)

            tn.setText(i.toString())
            tt.setText(q.title)
            ts.setText(q.score)


            v.setOnClickListener {
                val ii= Intent(context,QuizHistoryDetailActivity::class.java)
                ii.putExtra("qid",q)
                startActivity(ii)
            }

            llc.addView(v)
        }
    }

    companion object {
        fun Instantiate(ls:ArrayList<quizresponse>): QuisHistoryFragment {
            val sd = QuisHistoryFragment()
            sd.lsHQ=ls
            return sd
        }
    }

}

