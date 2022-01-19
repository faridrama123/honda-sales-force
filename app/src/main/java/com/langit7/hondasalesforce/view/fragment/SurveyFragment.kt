package com.langit7.hondasalesforce.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.bumptech.glide.Glide

import java.util.ArrayList
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.glide.slider.library.SliderLayout
import com.glide.slider.library.SliderTypes.TextSliderView
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.quiz
import com.langit7.hondasalesforce.model.survey
import com.langit7.hondasalesforce.presenter.adapter.QuizListAdapter
import com.langit7.hondasalesforce.presenter.adapter.SurveyListAdapter
import com.langit7.hondasalesforce.presenter.viewInterface.ItemClickListener
import com.langit7.hondasalesforce.view.activity.MainActivity
import com.langit7.hondasalesforce.view.activity.QuizActivity
import com.langit7.hondasalesforce.view.activity.SurveyActivity
import com.langit7.hondasalesforce.view.activity.SurveyNewActivity


class SurveyFragment : Fragment(), BaseFragmentInterface {

    override var title: String="Survey"

    lateinit var act:SurveyNewActivity;

    var lsData=ArrayList<survey>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_survey, container, false)

        act=activity as SurveyNewActivity

        val rv=rootView.findViewById<RecyclerView>(R.id.rv)

        // Creates a vertical Layout Manager
        rv.layoutManager = LinearLayoutManager(act)

        // You can use GridLayoutManager if you want multiple columns. Enter the number of columns as a parameter.
//        rv_animal_list.layoutManager = GridLayoutManager(this, 2)

        var adp= SurveyListAdapter(
            lsData,
            act,
            object : ItemClickListener<survey> {
                override fun onItemClick(s: survey) {
                    var ii= Intent(act, SurveyActivity::class.java)
                    ii.putExtra("s",s)
                    startActivity(ii)

                }
            }
        )
        // Access the RecyclerView Adapter and load the data into it
        rv.adapter = adp;

        return rootView
    }


    companion object {
        fun Instantiate(lsdata:ArrayList<survey>): SurveyFragment {
            val sd = SurveyFragment()
            sd.lsData=lsdata
            return sd
        }
    }

}

