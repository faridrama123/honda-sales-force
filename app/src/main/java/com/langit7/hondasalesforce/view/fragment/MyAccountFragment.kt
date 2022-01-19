package com.langit7.hondasalesforce.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.util.ArrayList
import android.content.Intent
import android.graphics.Color
import android.media.Image
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import android.widget.*
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.CustomTabLayout
import com.langit7.hondasalesforce.Util.DialogUtil
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.leaderboard
import com.langit7.hondasalesforce.model.leaderboardhistory
import com.langit7.hondasalesforce.model.quizresponse
import com.langit7.hondasalesforce.model.redeem
import com.langit7.hondasalesforce.presenter.adapter.WrappingFragmentAdapter
import com.langit7.hondasalesforce.view.activity.ChangeAvatarActivity
import com.langit7.hondasalesforce.view.activity.LeaderboardActivity
import com.langit7.hondasalesforce.view.activity.LoginActivity
import com.langit7.hondasalesforce.view.activity.MainActivity
import xyz.santeri.wvp.WrappingViewPager


class MyAccountFragment : Fragment(), BaseFragmentInterface {

    override var title: String="Akun Saya"

    lateinit var act:MainActivity;

    lateinit var lsFragment:ArrayList<Fragment>

    var lsd=ArrayList<leaderboard>()
    var lsmd=ArrayList<leaderboard>()
    var lsh=ArrayList<leaderboardhistory>()
    var lsHQ=ArrayList<quizresponse>()
    var lsHR=ArrayList<redeem>()
    lateinit var mSectionsPagerAdapter: WrappingFragmentAdapter
    lateinit var view_pager: WrappingViewPager
    lateinit var tabs: CustomTabLayout



    lateinit var tname:TextView
    lateinit var tid:TextView
    lateinit var tposition:TextView
    lateinit var tpoint:TextView
    lateinit var tscore:TextView
    lateinit var tlevel:TextView
    lateinit var llavatar:RelativeLayout
    lateinit var imgavatar:ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_myaccount, container, false)

        act=activity as MainActivity

        llavatar= rootView.findViewById<RelativeLayout>(R.id.llavatar)
        imgavatar= rootView.findViewById<ImageView>(R.id.imgavatar)
        llavatar.setOnClickListener {
            var ii= Intent(context,ChangeAvatarActivity::class.java)
            context!!.startActivity(ii)
        }


        var imgleaderboard= rootView.findViewById<ImageView>(R.id.imgleaderboard)
        imgleaderboard.setOnClickListener {
            var ii= Intent(context,LeaderboardActivity::class.java)
            context!!.startActivity(ii)
        }

        var imglogout=rootView.findViewById<ImageView>(R.id.imglogout)
        imglogout.setOnClickListener {
            function.doLogout(act)
            var ii=Intent(act,LoginActivity::class.java)
            function.savePreverence(context!!, "hasaddialog", false)
            act.startActivity(ii)
            act.finish()
        }
        view_pager=rootView.findViewById(R.id.view_pager)
        tabs=rootView.findViewById(R.id.tabs)

        tname=rootView.findViewById<TextView>(R.id.tname)
        tid=rootView.findViewById<TextView>(R.id.tid)
        tposition=rootView.findViewById<TextView>(R.id.tposition)
        tpoint=rootView.findViewById<TextView>(R.id.tpoint)
        tscore=rootView.findViewById<TextView>(R.id.tscore)
        tlevel=rootView.findViewById<TextView>(R.id.tlevel)


        renderUser()
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

//        tredeem.visibility=View.INVISIBLE
//        tredeem.setOnClickListener {
//
//            DialogUtil.createYesDialog(act,"Untuk sementara redeem belum bisa digunakan","OK")
//
////            var ii= Intent(context,RedeemActivity::class.java)
////            startActivity(ii)
//
//        }





        setupFragment()

        return rootView
    }

    fun renderUser(){
        try {
            var usr= function.getUser(act)
            tname.setText(usr!!.firstName +" " + usr!!.lastName)
            tid.setText(usr.profileUser.hondaid)
            tposition.setText(usr.profileUser.position+ "\n" + usr.profileUser.dealer.mainDealer)
            tpoint.setText(function.formatNumber(usr.profileUser.totalPoint.replace(".0","")))
            tscore.setText(function.formatNumber(usr.profileUser.totalPoint.replace(".0","")))
            tlevel.setText(usr.profileUser.desc_medal_type)

//            treadsalestalk.setText(usr!!.totalReadSalesTalk)
//            tinputdata.setText(usr!!.totalInsertData)
//            ttotalassignment.setText(usr!!.totalQuiz)
//            tpass.setText(usr!!.totalQuizPassed)
//            tremed.setText(usr!!.totalQuizFailed)

//            treadproduct.setText(usr.total_read_product)
//            treadknowledge.setText(usr.total_read_general_knowledge)
//            twatchvideo.setText(usr.totalWatchVideo)
//            treadartc.setText(usr.total_read_article)
//            tadddata.setText(usr.totalInsertData)
//            ttakequis.setText(usr.total_quiz)
//            ttakesurvey.setText(usr.total_survey)
            imgavatar.setImageResource(function.getMedalImageLarge(usr.profileUser.chosen_medal_type))


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    companion object {
        fun Instantiate(lshq:ArrayList<quizresponse>,lshr:ArrayList<redeem>,lsd:ArrayList<leaderboard>,lsmd:ArrayList<leaderboard>,lsh:ArrayList<leaderboardhistory>): MyAccountFragment {
            val sd = MyAccountFragment()
            sd.lsHQ=lshq
            sd.lsHR=lshr
            sd.lsd=lsd
            sd.lsmd=lsmd
            sd.lsh=lsh
            return sd
        }
    }



    fun setupFragment() {


        lsFragment = ArrayList()
        lsFragment.add(MyAccountStatFragment.Instantiate())
        lsFragment.add(LeaderboardListFragment.Instantiate(lsd,"Dealer",""))
        lsFragment.add(LeaderboardListMDFragment.Instantiate(lsmd,"Main Dealer","TOP 10 Sales Force di area Main Dealer"))
        lsFragment.add(QuisHistoryFragment.Instantiate(lsHQ))
        lsFragment.add(RedeemHistoryFragment.Instantiate(lsHR))
        lsFragment.add(LeaderboardHistoryListFragment.Instantiate(lsh,"History Level"))


        mSectionsPagerAdapter = WrappingFragmentAdapter(childFragmentManager, lsFragment)
        view_pager.setAdapter(mSectionsPagerAdapter)



        tabs.setupWithViewPager(view_pager)
        setuptab()
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                permakTabs()

            }

            override fun onPageSelected(position: Int) {
                if(position==4){
                    (context as MainActivity).getredemed()
                }else if(position==5 && lsh.size==0){
                    (context as MainActivity).gethistoryleaderboard()
                }else if(position==3 ){
                    (context as MainActivity).gethistoryquiz()
                }else if(position==1 && lsd.size==0){
                    (context as MainActivity).getLeaderboarddealer()
                }else if(position==2 && lsmd.size==0){
                    (context as MainActivity).getLeaderboardmaindealer()
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        view_pager.setCurrentItem(0)
        view_pager.offscreenPageLimit=6
    }

    private fun setuptab() {
        tabs.setTabGravity(TabLayout.GRAVITY_FILL)
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE)
        tabs.setSelectedTabIndicatorHeight(function.dp2px(context!!, 1))


        val ll = tabs.getChildAt(0) as LinearLayout

//        for (i in 0 until tabs.getTabCount()) {
//            ll.setPadding(0, 0, 0, 0)
//            val rl = ll.getChildAt(i) as LinearLayout
//            if (rl != null) {
//                val lp = LinearLayout.LayoutParams(function.dp2px(context!!, 20), function.dp2px(context!!, 20))
//                lp.setMargins(0, 0, 0, function.dp2px(context!!, -3))
//                val tv = rl.getChildAt(1) as TextView
////                tv.textSize = 8f
//                tv.setPadding(
//                    function.dp2px(context!!, 10),
//                    function.dp2px(context!!, 3),
//                    function.dp2px(context!!, 10),
//                    function.dp2px(context!!, 3)
//                )
//                tv.setBackgroundColor(Color.TRANSPARENT)
//                tv.layoutParams.width = function.dp2px(context!!, 200)
//
//
//            }
//        }
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

