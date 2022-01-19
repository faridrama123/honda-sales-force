package com.langit7.hondasalesforce.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.quiz
import com.langit7.hondasalesforce.view.activity.MainActivity
import com.langit7.hondasalesforce.view.activity.teamreport.FrequentActivity
import com.langit7.hondasalesforce.view.activity.teamreport.KuisActivity
import com.langit7.hondasalesforce.view.activity.teamreport.PartisipantActivity
import com.langit7.hondasalesforce.view.activity.teamreport.QualifiedActivity


class TeamReportFragment : Fragment(), BaseFragmentInterface {

    override var title: String="Team Report"
    lateinit var act:MainActivity;
    var lsData=ArrayList<quiz>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_team_report, container, false)
        act=activity as MainActivity
        val imgback=rootView.findViewById<ImageView>(R.id.imgback)
        val ttile=rootView.findViewById<TextView>(R.id.tactionbartitle)
        imgback.visibility=View.INVISIBLE
        ttile.setText(title)

        var rlFrequent = rootView.findViewById<RelativeLayout>(R.id.rlFrequent)
        var rlPartisipant = rootView.findViewById<RelativeLayout>(R.id.rlPartisipant)
        var rlQuiz = rootView.findViewById<RelativeLayout>(R.id.rlQuiz)
        var rlQualified = rootView.findViewById<RelativeLayout>(R.id.rlQualified)


        rlFrequent.setOnClickListener {
            var ii=Intent(act, FrequentActivity::class.java)
            act.startActivity(ii)
        }

        rlPartisipant.setOnClickListener {
            var ii=Intent(act,PartisipantActivity::class.java)
            act.startActivity(ii)
        }

        rlQuiz.setOnClickListener {
            var ii=Intent(act,KuisActivity::class.java)
            act.startActivity(ii)
        }

        rlQualified.setOnClickListener {
            var ii=Intent(act,QualifiedActivity::class.java)
            act.startActivity(ii)
        }

        return rootView
    }

    companion object {
        fun Instantiate(lsdata:List<quiz>): TeamReportFragment {
            val sd = TeamReportFragment()
            sd.lsData.clear()
            sd.lsData.addAll(lsdata)
            return sd
        }
    }

}

