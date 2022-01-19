package com.langit7.hondasalesforce.view.fragment

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.util.ArrayList
import android.graphics.Color
import android.os.Build
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import android.text.Html
import android.widget.*
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.CustomTabLayout
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.leaderboard
import com.langit7.hondasalesforce.model.leaderboardhistory
import com.langit7.hondasalesforce.presenter.adapter.WrappingFragmentAdapter
import com.langit7.hondasalesforce.view.activity.LeaderboardActivity
import xyz.santeri.wvp.WrappingViewPager


class LeaderboardFragment : Fragment(), BaseFragmentInterface {

    override var title: String="sss"

    lateinit var act:LeaderboardActivity;

    lateinit var lsF:ArrayList<Fragment>


    var lsD=ArrayList<leaderboard>()
    var lsMD=ArrayList<leaderboard>()
    var lsH=ArrayList<leaderboardhistory>()
    lateinit var mSectionsPagerAdapter1: WrappingFragmentAdapter
    lateinit var view_pager1: WrappingViewPager
    lateinit var tabs: CustomTabLayout

    lateinit var trank:TextView
    lateinit var tlrank:TextView



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_leaderboard, container, false)

        act=activity as LeaderboardActivity


        view_pager1=rootView.findViewById(R.id.view_pager)
        tabs=rootView.findViewById(R.id.subtabs)

        var tname=rootView.findViewById<TextView>(R.id.tname)
        var tlevel=rootView.findViewById<TextView>(R.id.tlevel)
        var tposition=rootView.findViewById<TextView>(R.id.tposition)
        var tpoint=rootView.findViewById<TextView>(R.id.tpoint)
        var imgmedal=rootView.findViewById<ImageView>(R.id.imglevel)
        var imgback=rootView.findViewById<ImageView>(R.id.imgback)
        trank=rootView.findViewById<TextView>(R.id.trank)
        tlrank=rootView.findViewById<TextView>(R.id.tlrank)



        imgback.setOnClickListener {
            (context as Activity).onBackPressed()
        }




        try {
            var usr= function.getUser(act)
            tname.setText(usr!!.firstName +" " + usr!!.lastName)
            tposition.setText(usr!!.profileUser.position+"\n"+usr.profileUser.dealer.title)
            tpoint.setText(function.formatNumber(usr!!.profileUser.totalPoint.replace(".0","")))
            tlevel.setText(usr!!.profileUser.desc_medal_type)

            imgmedal.setImageResource(function.getMedalImageLarge(usr!!.profileUser.chosen_medal_type))


            tlrank.setText("Ranking anda di Dealer")
            trank.setText("-")
            var rank=1
            for(a in lsD){
                if(a.firstName.equals(usr!!.firstName) && a.lastName.equals(usr!!.lastName)) {
                    trank.setText(Html.fromHtml("<b>"+rank.toString() +"</b> of "+ lsD.size.toString()))
                    break
                }
                rank++
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }


        setupFragment()

        return rootView
    }


    companion object {
        fun Instantiate(lsd:ArrayList<leaderboard>,lsmd:ArrayList<leaderboard>, lsh:ArrayList<leaderboardhistory>): LeaderboardFragment {
            val sd = LeaderboardFragment()
            sd.lsD=lsd
            sd.lsMD=lsmd
            sd.lsH=lsh
            return sd
        }
    }



    fun setupFragment() {


        lsF = ArrayList()
        lsF.add(LeaderboardListFragment.Instantiate(lsD,"Dealer",""))
        lsF.add(LeaderboardListMDFragment.Instantiate(lsMD,"Main Dealer","TOP 10 Sales Force di area Main Dealer"))
        lsF.add(LeaderboardHistoryListFragment.Instantiate(lsH,"History Level"))



        mSectionsPagerAdapter1 = WrappingFragmentAdapter(childFragmentManager, lsF)
        view_pager1.setAdapter(mSectionsPagerAdapter1)



        tabs.setupWithViewPager(view_pager1)
        setuptab()
        view_pager1.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                permakTabs()

            }

            override fun onPageSelected(position: Int) {
                if(position==0){
                    tlrank.setText("Ranking anda di Dealer")
                    trank.setText("-")
                    var usr= function.getUser(act)
                    var rank=1
                    for(a in lsD){
                        if(a.firstName.equals(usr!!.firstName) && a.lastName.equals(usr!!.lastName)) {
                            trank.setText(Html.fromHtml("<b>"+rank.toString() +"</b> of "+ lsD.size.toString()))
                            break
                        }
                        rank++
                    }
                }else if(position==1){
                    tlrank.setText("Ranking anda di Main Dealer")
                    trank.setText("-")
                    var usr= function.getUser(act)
                    var rank=1
                    for(a in lsMD){
                        if(a.firstName.equals(usr!!.firstName) && a.lastName.equals(usr!!.lastName)) {
                            trank.setText(Html.fromHtml("<b>"+rank.toString() +"</b> of "+ lsMD.size.toString()))
                            break
                        }
                        rank++
                    }
                }

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
        view_pager1.offscreenPageLimit=1
        view_pager1.setCurrentItem(0)
    }

    private fun setuptab() {
        tabs.setTabGravity(TabLayout.GRAVITY_FILL)
        tabs.setTabMode(TabLayout.MODE_FIXED)
        tabs.setSelectedTabIndicatorHeight(function.dp2px(context!!, 1))


        val ll = tabs.getChildAt(0) as LinearLayout

        for (i in 0 until tabs.getTabCount()) {
            ll.setPadding(0, 0, 0, 0)
            val rl = ll.getChildAt(i) as LinearLayout
            if (rl != null) {
                val lp = LinearLayout.LayoutParams(function.dp2px(context!!, 20), function.dp2px(context!!, 20))
                lp.setMargins(0, 0, 0, function.dp2px(context!!, -3))
                val tv = rl.getChildAt(1) as TextView
//                tv.textSize = 8f
                tv.setPadding(
                    function.dp2px(context!!, 10),
                    function.dp2px(context!!, 3),
                    function.dp2px(context!!, 10),
                    function.dp2px(context!!, 3)
                )
                tv.setBackgroundColor(Color.TRANSPARENT)
                tv.layoutParams.width = function.dp2px(context!!, 200)


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

