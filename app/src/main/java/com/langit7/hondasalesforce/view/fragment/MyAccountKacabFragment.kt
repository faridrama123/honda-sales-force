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
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.recyclerview.widget.RecyclerView
import android.widget.*
import com.bumptech.glide.request.RequestOptions
import com.glide.slider.library.SliderLayout
import com.glide.slider.library.SliderTypes.TextSliderView
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.CanScrollViewPager
import com.langit7.hondasalesforce.Util.CustomTabLayout
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.leaderboard
import com.langit7.hondasalesforce.model.quizresponse
import com.langit7.hondasalesforce.model.redeem
import com.langit7.hondasalesforce.presenter.adapter.FragmentAdapter
import com.langit7.hondasalesforce.presenter.adapter.WrappingFragmentAdapter
import com.langit7.hondasalesforce.view.activity.LoginActivity
import com.langit7.hondasalesforce.view.activity.MainActivity
import com.langit7.hondasalesforce.view.activity.RedeemActivity
import kotlinx.android.synthetic.main.activity_main.*
import xyz.santeri.wvp.WrappingViewPager


class MyAccountKacabFragment : Fragment(), BaseFragmentInterface {

    override var title: String="Akun Saya"

    lateinit var act:MainActivity;

    lateinit var lsFragment:ArrayList<Fragment>


//    var lsHQ=ArrayList<quizresponse>()
//    var lsHR=ArrayList<redeem>()
    var lsD=ArrayList<leaderboard>()
    var lsMD=ArrayList<leaderboard>()

    lateinit var mSectionsPagerAdapter: WrappingFragmentAdapter
    lateinit var view_pager: WrappingViewPager
    lateinit var tabs: CustomTabLayout


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_myaccount_kacab, container, false)

        act=activity as MainActivity


        var imglogout=rootView.findViewById<ImageView>(R.id.imglogout)
        imglogout.setOnClickListener {
            function.doLogout(act)
            var ii=Intent(act,LoginActivity::class.java)
            function.savePreverence(requireContext(), "hasaddialog", false)
            act.startActivity(ii)
            act.finish()
        }
        view_pager=rootView.findViewById(R.id.view_pager)
        tabs=rootView.findViewById(R.id.tabs)

        var tname=rootView.findViewById<TextView>(R.id.tname)
        var tid=rootView.findViewById<TextView>(R.id.tid)
        var tposition=rootView.findViewById<TextView>(R.id.tposition)
        var tpoint=rootView.findViewById<TextView>(R.id.tpoint)
//        var treadsalestalk=rootView.findViewById<TextView>(R.id.treadsalestalk)
//        var twatchvideo=rootView.findViewById<TextView>(R.id.twatchvideo)
//        var tinputdata=rootView.findViewById<TextView>(R.id.tinputdata)
//        var ttotalassignment=rootView.findViewById<TextView>(R.id.ttotalassignment)
//        var tpass=rootView.findViewById<TextView>(R.id.tpass)
//        var tremed=rootView.findViewById<TextView>(R.id.tremed)



//        var treadproduct=rootView.findViewById<TextView>(R.id.treadproduct)
//        var treadknowledge=rootView.findViewById<TextView>(R.id.treadknowledge)
//        var twatchvideo=rootView.findViewById<TextView>(R.id.twatchvideo)
//        var treadartc=rootView.findViewById<TextView>(R.id.treadartc)
//        var tadddata=rootView.findViewById<TextView>(R.id.tadddata)
//        var ttakequis=rootView.findViewById<TextView>(R.id.ttakequis)
//        var ttakesurvey=rootView.findViewById<TextView>(R.id.ttakesurvey)
//        var tredeem=rootView.findViewById<TextView>(R.id.tredeem)

//        tredeem.setOnClickListener {
//            var ii= Intent(context,RedeemActivity::class.java)
//            startActivity(ii)
//        }


        try {
            var usr= function.getUser(act)
            tname.setText(usr!!.firstName +" " + usr!!.lastName)
            tid.setText(usr!!.profileUser.hondaid)
            tposition.setText(usr!!.profileUser.position+ "\n" + usr.profileUser.dealer.mainDealer)
            tpoint.setText(function.formatNumber(usr!!.profileUser.totalPoint.replace(".0","")))

//            treadsalestalk.setText(usr!!.totalReadSalesTalk)
//            tinputdata.setText(usr!!.totalInsertData)
//            ttotalassignment.setText(usr!!.totalQuiz)
//            tpass.setText(usr!!.totalQuizPassed)
//            tremed.setText(usr!!.totalQuizFailed)

//            treadproduct.setText(usr!!.total_read_product)
//            treadknowledge.setText(usr!!.total_read_general_knowledge)
//            twatchvideo.setText(usr!!.totalWatchVideo)
//            treadartc.setText(usr!!.total_read_article)
//            tadddata.setText(usr!!.totalInsertData)
//            ttakequis.setText(usr!!.total_quiz)
//            ttakesurvey.setText(usr!!.total_survey)


        } catch (e: Exception) {
            e.printStackTrace()
        }


        setupFragment()

        return rootView
    }


    companion object {
        fun Instantiate(lsd:ArrayList<leaderboard>,lsmd:ArrayList<leaderboard>): MyAccountKacabFragment {
            val sd = MyAccountKacabFragment()
            sd.lsD=lsd
            sd.lsMD=lsmd
            return sd
        }
    }



    fun setupFragment() {


        lsFragment = ArrayList()
        lsFragment.add(MyAccountStatFragment.Instantiate(true))

//        lsFragment.add(QuisHistoryFragment.Instantiate(lsHQ))
//        lsFragment.add(RedeemKacabHistoryFragment.Instantiate(lsHR,"History Redeem"))
        lsFragment.add(LeaderboardListFragment.Instantiate(lsD,"Dealer",""))
        lsFragment.add(LeaderboardListMDFragment.InstantiateMD(lsMD,"Main Dealer","TOP 10 Sales Force di area Main Dealer"))




        mSectionsPagerAdapter = WrappingFragmentAdapter(childFragmentManager, lsFragment)
        view_pager.setAdapter(mSectionsPagerAdapter)



        tabs.setupWithViewPager(view_pager)
        setuptab()
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                permakTabs()

            }

            override fun onPageSelected(position: Int) {
                if(position==1 && lsD.size==0){
                    (context as MainActivity).getLeaderboarddealer()
                }else if(position==2 && lsMD.size==0){
                    (context as MainActivity).getLeaderboardmaindealer()
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
        view_pager.offscreenPageLimit=3
        view_pager.setCurrentItem(0)
    }

    private fun setuptab() {
        tabs.setTabGravity(TabLayout.GRAVITY_FILL)
        tabs.setTabMode(TabLayout.MODE_FIXED)
        tabs.setSelectedTabIndicatorHeight(function.dp2px(requireContext(), 1))


        val ll = tabs.getChildAt(0) as LinearLayout

        for (i in 0 until tabs.getTabCount()) {
            ll.setPadding(0, 0, 0, 0)
            val rl = ll.getChildAt(i) as LinearLayout
            if (rl != null) {
                val lp = LinearLayout.LayoutParams(function.dp2px(requireContext(), 20), function.dp2px(requireContext(), 20))
                lp.setMargins(0, 0, 0, function.dp2px(requireContext(), -3))
                val tv = rl.getChildAt(1) as TextView
//                tv.textSize = 8f
                tv.setPadding(
                    function.dp2px(requireContext(), 10),
                    function.dp2px(requireContext(), 3),
                    function.dp2px(requireContext(), 10),
                    function.dp2px(requireContext(), 3)
                )
                tv.setBackgroundColor(Color.TRANSPARENT)
                tv.layoutParams.width = function.dp2px(requireContext(), 200)


            }
        }
    }

    private fun permakTabs() {
        val ll = tabs.getChildAt(0) as LinearLayout
        for (i in 0 until tabs.getTabCount()) {
            val rl = ll.getChildAt(i) as LinearLayout
            val tv = rl.getChildAt(1) as TextView
            //View v1 = rl.getChildAt(1);
            //v1.setVisibility(View.GONE);
            if (tabs.getSelectedTabPosition() == i) {
                //tv.setTextColor(Color.WHITE);
                //tv.getLayoutParams().width=dp2px(ctx,200);
                tv.setTextColor(resources.getColor(R.color.white))
//                rl.setBackgroundColor(resources.getColor(R.color.red))



            } else {

                tv.setTextColor(resources.getColor(R.color.gray))
//                rl.setBackgroundColor(resources.getColor(R.color.white))
            }
        }

    }
}

