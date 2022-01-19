package com.langit7.hondasalesforce.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.widget.*
import com.bumptech.glide.request.RequestOptions
import com.glide.slider.library.SliderLayout
import com.glide.slider.library.SliderTypes.DefaultSliderView
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.quiz
import com.langit7.hondasalesforce.model.survey
import com.langit7.hondasalesforce.view.activity.*



class HomeFragment : Fragment(), BaseFragmentInterface {

    override var title: String="Home"

    lateinit var act:MainActivity;
    var lsQuizData=ArrayList<quiz>()
    var lsSurveyData=ArrayList<survey>()



    lateinit var tname:TextView
    lateinit var tid:TextView
    lateinit var tposition:TextView
    lateinit var tscore:TextView
    lateinit var tlevel:TextView
    lateinit var llpoint:LinearLayout
    lateinit var imgavatar:ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        act=activity as MainActivity

        var slider=rootView.findViewById<SliderLayout>(R.id.slider)
        slider.setPresetTransformer(SliderLayout.Transformer.Default)
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom)
        slider.setDuration(4000)

        val requestOptions = RequestOptions().centerCrop()
        slider.removeAllSliders()
        for (i in 0 until act.lsSlider.size) {
            val sliderView = DefaultSliderView(act)
            sliderView
                .image(act.lsSlider.get(i).image)
                .setRequestOption(requestOptions)
                .setBackgroundColor(Color.TRANSPARENT)
                .setProgressBarVisible(true)
                .setOnSliderClickListener {
                    val url = act.lsSlider.get(i).first_redirect_url
                    val i = Intent(Intent.ACTION_VIEW)
                    i.data = Uri.parse(url)
                    startActivity(i)
                }

            slider.addSlider(sliderView)

        }


        tname=rootView.findViewById<TextView>(R.id.tname)
        tid=rootView.findViewById<TextView>(R.id.tid)
        tposition=rootView.findViewById<TextView>(R.id.tposition)
        tscore=rootView.findViewById<TextView>(R.id.tscore)
        tlevel=rootView.findViewById<TextView>(R.id.tlevel)
        llpoint=rootView.findViewById<LinearLayout>(R.id.llpoint)
        imgavatar=rootView.findViewById<ImageView>(R.id.imgavatar)




        var rlproductknowledge=rootView.findViewById<RelativeLayout>(R.id.rlproductknowledge)
        var rlbasicknowledge=rootView.findViewById<RelativeLayout>(R.id.rlbasicknowledge)
        var rlvideo=rootView.findViewById<RelativeLayout>(R.id.rlvideo)
        var rlarticle=rootView.findViewById<RelativeLayout>(R.id.rlarticle)
        var rlapparel=rootView.findViewById<RelativeLayout>(R.id.rlapparel)
        var rlprospect=rootView.findViewById<RelativeLayout>(R.id.rlprospect)
        var rlkuis=rootView.findViewById<RelativeLayout>(R.id.rlkuis)
        var rlsurvey=rootView.findViewById<RelativeLayout>(R.id.rlsurvey)

        renderUser()



        rlproductknowledge.setOnClickListener {
            var ii=Intent(act,ProductActivity::class.java)
            act.startActivity(ii)
        }
        rlbasicknowledge.setOnClickListener {
            var ii=Intent(act,GeneralKnowledgeActivity::class.java)
            act.startActivity(ii)
        }
        rlvideo.setOnClickListener {
            var ii=Intent(act,VideoActivity::class.java)
            act.startActivity(ii)
        }
        rlarticle.setOnClickListener {
            var ii=Intent(act,ArticleActivity::class.java)
            act.startActivity(ii)
        }
        rlapparel.setOnClickListener {
            var ii=Intent(act,ScanActivity::class.java)
            act.startActivityForResult(ii, 1)
        }
        rlprospect.setOnClickListener {
//            var ii= Intent(act, CDBListActivity::class.java)
//            act.startActivity(ii)
            act.Toast("Available Soon")
        }
        rlkuis.setOnClickListener {
            var ii=Intent(act,QuizNewActivity::class.java)
            ii.putExtra("lsQuizData", lsQuizData)
            act.startActivity(ii)
        }
        rlsurvey.setOnClickListener {
            var ii=Intent(act,SurveyNewActivity::class.java)
            ii.putExtra("lsSurveyData", lsSurveyData)
            act.startActivity(ii)
        }

        return rootView
    }

    fun renderUser(){
        try {
            var usr= function.getUser(act)
            tname.setText(usr!!.firstName +" " + usr!!.lastName)
            tid.setText(usr!!.profileUser.hondaid)
            tposition.setText(usr!!.profileUser.position + "\n" + usr.profileUser.dealer.mainDealer)
            tscore.setText(function.formatNumber(usr!!.profileUser.totalPoint.replace(".0","")))
            tlevel.setText(usr.profileUser.desc_medal_type)
            imgavatar.setImageResource(function.getMedalImageLarge(usr.profileUser.chosen_medal_type))

            if( usr.profileUser.parent_position.equals(act.KACAB,true) ||
                usr.profileUser.parent_position.equals(act.SUPERVISOR,true)) {
                llpoint.visibility = View.INVISIBLE
                tid.visibility = View.VISIBLE
            }else if(usr.profileUser.parent_position.equals(act.MAINDEALER,true)){
                llpoint.visibility = View.INVISIBLE
                tid.visibility = View.INVISIBLE
            }else{
                llpoint.visibility = View.VISIBLE
                tid.visibility = View.VISIBLE
            }


            if(tid.text.isNullOrEmpty())
                tid.visibility=View.GONE

        } catch (e: Exception) {
        }
    }


    companion object {
        fun Instantiate(quizdata:List<quiz>,surveydata:List<survey>,): HomeFragment {
            val sd = HomeFragment()
            sd.lsQuizData.clear()
            sd.lsQuizData.addAll(quizdata)
            sd.lsSurveyData.clear()
            sd.lsSurveyData.addAll(surveydata)
            return sd
        }
    }

}

