package com.langit7.hondasalesforce.view.activity

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.Gson
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.DialogUtil
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.Util.function.dp2px
import com.langit7.hondasalesforce.Util.function.getPreverenceBool
import com.langit7.hondasalesforce.Util.function.savePreverence
import com.langit7.hondasalesforce.model.*
import com.langit7.hondasalesforce.presenter.adapter.FragmentAdapter
import com.langit7.hondasalesforce.presenter.logic.*
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.presenter.viewInterface.SliderInterface
import com.langit7.hondasalesforce.view.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    val MAINDEALER="main dealer"
    val KACAB="kacab"
    val SUPERVISOR="supervisor"

    var lsSlider = ArrayList<slider>()
    var lsQuiz = ArrayList<quiz>()
    var lsSurvey = ArrayList<survey>()

    var lsTeamReportFragment = ArrayList<quiz>()
    var lsSelfAudit = ArrayList<quiz>()

    var lsNotif = ArrayList<notif>()
    var lsHistoryQuis = ArrayList<quizresponse>()
    var lsHistoryRedeem = ArrayList<redeem>()
    var lsHistoryRedeemKacab = ArrayList<redeem>()
    var lsMD = ArrayList<leaderboard>()
    var lsD = ArrayList<leaderboard>()
    var lsH = ArrayList<leaderboardhistory>()


    lateinit var mSectionsPagerAdapter: FragmentAdapter
    var lsFragment= ArrayList<BaseFragmentInterface>()

    lateinit var presenter: MainPresenter
    lateinit var leaderboadpresenter: LeaderboardPresenter
    lateinit var quizpresenter: QuizPresenter
    lateinit var surveypresenter: SurveyPresenter
    lateinit var redeempresenter:PromoPresenter

    lateinit var lsCat:java.util.ArrayList<apparelcategory>



    var hassl = false
    var hasq = false
    var hass = false
    var hasu = false
    var hasads = false





    var hassetup=false
    var curtab=-1


    var hasadsdialog=false
    var hasrating=false


    var loaddatafailed=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val msg=intent.getStringExtra("msg")

        if(msg!=null && msg.length>0)
            Toast(msg)

        if(intent.getBooleanExtra("logout",false)){
            savePreverence(ctx, "user", "")
            savePreverence(ctx, "token", "")
            var ii = Intent(ctx, SplashActivity::class.java)
            ii.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(ii)
            finish()
            return
        }


        hasadsdialog= getPreverenceBool(ctx,"hasaddialog")
        hasrating= getPreverenceBool(ctx,"hasrating")


        presenter = MainPresenter(this, APIServices)
        quizpresenter = QuizPresenter(this, APIServices)
        surveypresenter = SurveyPresenter(this, APIServices)
        redeempresenter= PromoPresenter(this,APIServices)
        leaderboadpresenter= LeaderboardPresenter(this,APIServices)

        showLoadingDialog()
        (dialog as ProgressDialog).setMessage("Loading...(5%)")
        presenter.getSlider(object : SliderInterface {
            override fun onGetSliderSuccess(res: List<slider>) {
//                dismisLoadingDialog()
                lsSlider.clear()
                lsSlider.addAll(res)
                hassl = true
                init()

//                showLoadingDialog()
                (dialog as ProgressDialog).setMessage("Loading...(20%)")
                presenter.getuser(object : ObjectResponseInterface<baseresponse<user>> {
                    override fun onSuccess(res: baseresponse<user>) {
//                        dismisLoadingDialog()
                        function.savePreverence(ctx, "user", Gson().toJson(res.data))
                        hasu = true
                        init()

//                        showLoadingDialog()
                        (dialog as ProgressDialog).setMessage("Loading...(40%)")
                        quizpresenter.getQuiz(object : DataListInterface<quiz> {
                            override fun onGetDataSuccess(res: List<quiz>) {
//                                dismisLoadingDialog()
                                lsQuiz.clear()

                                for(q in res){
                                    if(q.isActive.equals("1"))
                                        lsQuiz.add(q)
                                }

                                hasq = true
                                init()


//                                showLoadingDialog()
                                (dialog as ProgressDialog).setMessage("Loading...(60%)")
                                surveypresenter.getSurvey(object : DataListInterface<survey> {
                                    override fun onGetDataSuccess(res: List<survey>) {
//                                        dismisLoadingDialog()
                                        lsSurvey.clear()
                                        lsSurvey.addAll(res)
                                        hass = true
                                        init()
//                                        showLoadingDialog()
                                        (dialog as ProgressDialog).setMessage("Loading...(80%)")
                                        presenter.getAds(object : DataListInterface<ads> {
                                            override fun onGetDataSuccess(res: List<ads>) {
                                                dismisLoadingDialog()
                                                hasads = true

                                                if(!hasadsdialog) {
                                                    DialogUtil.createAdsDialog(ctx as Activity, res.get(0)).setOnDismissListener{
                                                        initrating()
                                                    }
                                                    function.savePreverence(ctx,"hasaddialog",true)
                                                }
                                                init()
                                            }

                                            override fun onGetDataFailed(msg: String) {
                                                dismisLoadingDialog()
                                                hasads = true
                                                loaddatafailed=true
                                                init()
                                            }

                                        })
                                    }

                                    override fun onGetDataFailed(msg: String) {
                                        dismisLoadingDialog()
                                        hass = true
                                        loaddatafailed=true
                                        init()
                                    }

                                })

                            }

                            override fun onGetDataFailed(msg: String) {
                                dismisLoadingDialog()
                                hasq = true
                                loaddatafailed=true
                                init()
                            }

                        })
                    }

                    override fun onFailed(msg: String) {
                        dismisLoadingDialog()
                        hasu=true
                        loaddatafailed=true
                        init()
                    }

                })
            }

            override fun onGetSliderFailed(msg: String) {
                dismisLoadingDialog()
                hassl = true
                loaddatafailed=true
                init()

            }
        })



















        fabuser.setOnClickListener {

            DialogUtil.createCustomerProfileDialog(ctx as Activity)

        }

    }

    fun initrating(){

        if(getSettings()!=null &&getSettings().is_survey_rating!=null &&getSettings().is_survey_rating.equals("1")&&getUser()!!.profileUser.is_survey_rating!=null && getUser()!!.profileUser.is_survey_rating.equals("1")){
            DialogUtil.createRatingDialog(ctx as BaseActivity).setOnDismissListener {
                hasrating=true
                function.savePreverence(ctx,"hasrating",true)
            }
        }
    }

    fun init() {
            if(!loaddatafailed) {
                if (hassl && hasq && hass && hasu && hasads  )
                    setupFragment()
                //else
//                    Log.e("has","hassl $hassl, hasq $hasq, hass $hass, hasu $hasu, hasads $hasads")
            }else{
                Toast("Load Data Failed, Please Reloign")
                function.doLogout(ctx)
                var ii=Intent(ctx,LoginActivity::class.java)
                function.savePreverence(ctx, "hasaddialog", false)
                startActivity(ii)
                finish()
            }
    }

    fun setupFragment() {

        lsFragment.clear()
        lsFragment.add(HomeFragment.Instantiate(lsQuiz, lsSurvey))
        lsFragment.add(TeamReportFragment.Instantiate(lsTeamReportFragment))
        lsFragment.add(SelfAuditFragment.Instantiate(lsSelfAudit))
        lsFragment.add(NotificationFragment.Instantiate(lsNotif))

        if(
            getUser()!!.profileUser.parent_position.equals(KACAB,true) ||
            getUser()!!.profileUser.parent_position.equals(SUPERVISOR,true)
        )
            lsFragment.add(MyAccountKacabFragment.Instantiate(lsD,lsMD))
        else if(
            getUser()!!.profileUser.parent_position.equals(MAINDEALER,true)
                )
            lsFragment.add(MyAccountMDFragment.Instantiate(lsMD))
        else
            lsFragment.add(MyAccountFragment.Instantiate(lsHistoryQuis,lsHistoryRedeem,lsD,lsMD,lsH))


        mSectionsPagerAdapter = FragmentAdapter(supportFragmentManager, lsFragment)
        view_pager.setAdapter(mSectionsPagerAdapter)



        tabs.setupWithViewPager(view_pager)
        setuptab()
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                permakTabs()

            }

            override fun onPageSelected(position: Int) {
                curtab=position

                if(position==0)
                    fabuser.visibility=View.VISIBLE
                else
                    fabuser.visibility=View.GONE


                if(curtab==4){
                    if(
                        getUser()!!.profileUser.parent_position.equals(KACAB,true) ||
                        getUser()!!.profileUser.parent_position.equals(SUPERVISOR,true)
                    ) {
                        if(lsD.size==0){
                            getLeaderboarddealer()
                        }
                    }else if(
                        getUser()!!.profileUser.parent_position.equals(MAINDEALER,true)
                    ) {
                        if(lsMD.size==0){
                            getLeaderboardmaindealer()
                        }
                    }
                }

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        if(curtab>-1)
            view_pager.setCurrentItem(curtab)
        else
            view_pager.setCurrentItem(intent.getIntExtra("tid", 0), true)

        view_pager.offscreenPageLimit = 5
    }


    private fun setuptab() {

        if(!hassetup) {
            tabs.setTabGravity(TabLayout.GRAVITY_FILL)
            tabs.setTabMode(TabLayout.MODE_FIXED)
            tabs.setSelectedTabIndicatorHeight(dp2px(ctx, 1))


            val ll = tabs.getChildAt(0) as LinearLayout

            for (i in 0 until tabs.getTabCount()) {
                ll.setPadding(0, 0, 0, 0)
                val rl = ll.getChildAt(i) as LinearLayout
                if (rl != null) {
//                val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
//                lp.setMargins(0, dp2px(ctx, 10), 0, 0)
                    val tv = rl.getChildAt(1) as TextView
                    tv.textSize = 8f
                    tv.setPadding(dp2px(ctx, 10), dp2px(ctx, 3), dp2px(ctx, 10), dp2px(ctx, 3))
                    tv.setBackgroundColor(Color.TRANSPARENT)
                    tv.layoutParams.width = dp2px(ctx, 200)


                    val tv1 = TextView(ctx)
                    val lp1 = LinearLayout.LayoutParams(dp2px(ctx, 15), dp2px(ctx, 15))
                    lp1.topMargin = dp2px(ctx, 5)
                    lp1.bottomMargin = dp2px(ctx, 15)
                    lp1.gravity = Gravity.RIGHT
                    lp1.rightMargin = dp2px(ctx, 10)
                    tv1.setPadding(dp2px(ctx, 3), dp2px(ctx, 3), dp2px(ctx, 3), dp2px(ctx, 3))
                    tv1.setTextSize(8f)
                    tv1.setBackgroundResource(R.drawable.round_orange)
                    tv1.layoutParams = lp1
                    lp1.topMargin = dp2px(ctx, -30)
                    tv1.gravity = Gravity.CENTER
                    tv1.setTextColor(resources.getColor(R.color.white))
                    tv1.setText("0")

                    tv1.visibility = View.INVISIBLE
                    val iv = ImageView(ctx)
                    when (i) {
                        0 -> iv.setImageResource(R.drawable.icon_home_f)
                        1 -> iv.setImageResource(R.drawable.icon_home_f)
                        2 -> iv.setImageResource(R.drawable.icon_survei_f)
                        3 -> {
                            iv.setImageResource(R.drawable.icon_notifikasi_f)
                            if (getnotifcount() > 0) {
                                tv1.setText(getnotifcount().toString())
                                tv1.visibility = View.VISIBLE
                            }
                        }
                        4 -> iv.setImageResource(R.drawable.icon_akunsaya_f)
                    }

                    val lp2 = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )

                    iv.layoutParams = lp2


                    rl.addView(tv1, 0)
                    rl.addView(iv, 0)

                }
            }
            hassetup=true
        }
    }

    fun getnotifcount(): Int {
        var i = 0
        for (n in lsNotif) {
            if (!n.isRead.equals("1"))
                i++
        }

//        Log.e("nc", i.toString())
        return i
    }

    public fun permakTabs() {
        val ll = tabs.getChildAt(0) as LinearLayout
        for (i in 0 until tabs.getTabCount()) {
            val rl = ll.getChildAt(i) as LinearLayout
            val iv = rl.getChildAt(0) as ImageView
            val tv = rl.getChildAt(3) as TextView
            val tv1 = rl.getChildAt(1) as TextView


            Log.w ("tabAc", rl.getChildAt(3).toString());
            //View v1 = rl.getChildAt(2);
            //v1.setVisibility(View.GONE);


            if (i == 3) {
                if (getnotifcount() > 0) {
                    tv1.setText(getnotifcount().toString())
                    tv1.visibility = View.VISIBLE
                } else {
                    tv1.visibility = View.INVISIBLE
                }
            }


            if (tabs.getSelectedTabPosition() == i) {
                //tv.setTextColor(Color.WHITE);
                //tv.getLayoutParams().width=dp2px(ctx,200);
                tv.setTextColor(resources.getColor(R.color.white))
                rl.setBackgroundColor(resources.getColor(R.color.red))

                when (i) {
                    0 -> iv.setImageResource(R.drawable.icon_home_f)
                    1 -> iv.setImageResource(R.drawable.ic_teamreport_f)
                    2 -> iv.setImageResource(R.drawable.ic_self_f)
                    3 -> iv.setImageResource(R.drawable.icon_notifikasi_f)
                    4 -> iv.setImageResource(R.drawable.icon_akunsaya_f)
                }


            } else {
                when (i) {
                    0 -> iv.setImageResource(R.drawable.icon_home_uf)
                    1 -> iv.setImageResource(R.drawable.ic_teamreport)
                    2 -> iv.setImageResource(R.drawable.ic_self)
                    3 -> iv.setImageResource(R.drawable.icon_notifikasi_uf)
                    4 -> iv.setImageResource(R.drawable.icon_akunsaya_uf)
                }
                tv.setTextColor(resources.getColor(R.color.red))
                rl.setBackgroundColor(resources.getColor(R.color.white))
            }
        }

    }

    override fun onResume() {
        super.onResume()

        if(getUser()!=null)
        presenter.getNotif(object : DataListInterface<notif> {
            override fun onGetDataSuccess(res: List<notif>) {

                lsNotif.clear()
                lsNotif.addAll(res)
                try {
                    (lsFragment.get(3) as NotificationFragment).adp.notifyDataSetChanged()
                    permakTabs()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            override fun onGetDataFailed(msg: String) {

            }

        })







        if(lsFragment!=null && lsFragment.size>0){
            (lsFragment.get(0) as HomeFragment).renderUser()
            if(
                getUser()!!.profileUser.parent_position.equals(KACAB,true) ||
                getUser()!!.profileUser.parent_position.equals(SUPERVISOR,true)||
                getUser()!!.profileUser.parent_position.equals(MAINDEALER,true)
            ){
            }else {
                try {
                    (lsFragment.get(4) as MyAccountFragment).renderUser()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

    }


    fun getredemed(){
                showLoadingDialog()
        redeempresenter.getRedeemed(object: DataListInterface<redeem>{
            override fun onGetDataSuccess(res: List<redeem>) {
                dismisLoadingDialog()
                lsHistoryRedeem.clear()
                lsHistoryRedeem.addAll(res)


                if(
                    getUser()!!.profileUser.parent_position.equals(KACAB,true) ||
                    getUser()!!.profileUser.parent_position.equals(SUPERVISOR,true)||
                    getUser()!!.profileUser.parent_position.equals(MAINDEALER,true)
                ){

                }else {
                    try {
                        ((lsFragment.get(4) as MyAccountFragment).lsFragment.get(4) as RedeemHistoryFragment).renderdata(lsHistoryRedeem)
                    } catch (e: Exception) {
                    }
                }
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
            }

        })
    }

    fun gethistoryleaderboard() {
        showLoadingDialog()
        leaderboadpresenter.getLeaderboardHistory(object : DataListInterface<leaderboardhistory> {
            override fun onGetDataSuccess(res: List<leaderboardhistory>) {
                dismisLoadingDialog()
                lsH.clear()
                lsH.addAll(res)

                if(
                    getUser()!!.profileUser.parent_position.equals(KACAB,true) ||
                    getUser()!!.profileUser.parent_position.equals(SUPERVISOR,true)||
                    getUser()!!.profileUser.parent_position.equals(MAINDEALER,true)
                ){

                }else {
                    try {
                        ((lsFragment.get(4) as MyAccountFragment).lsFragment.get(5) as LeaderboardHistoryListFragment).renderdata(lsH)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()

            }

        })
    }

    fun gethistoryquiz(){
        showLoadingDialog()
        quizpresenter.getHistoryQuiz(object : DataListInterface<quizresponse> {
            override fun onGetDataSuccess(res: List<quizresponse>) {
                dismisLoadingDialog()
                lsHistoryQuis.clear()
                lsHistoryQuis.addAll(res)
                if(
                    getUser()!!.profileUser.parent_position.equals(KACAB,true) ||
                    getUser()!!.profileUser.parent_position.equals(SUPERVISOR,true)||
                    getUser()!!.profileUser.parent_position.equals(MAINDEALER,true)
                ){

                }else {
                    try {
                        ((lsFragment.get(4) as MyAccountFragment).lsFragment.get(3) as QuisHistoryFragment).renderdata(lsHistoryQuis)
                    } catch (e: Exception) {
                    }
                }


            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
            }

        })
    }


    fun getLeaderboarddealer(){
        showLoadingDialog()
        leaderboadpresenter.getLeaderboardDealer(object : DataListInterface<leaderboard> {
            override fun onGetDataSuccess(res: List<leaderboard>) {
                dismisLoadingDialog()
                lsD.clear()
                lsD.addAll(res)

                if (
                    getUser()!!.profileUser.parent_position.equals(KACAB, true) ||
                    getUser()!!.profileUser.parent_position.equals(SUPERVISOR, true)
                ) {
                    try {
                        ((lsFragment.get(4) as MyAccountKacabFragment).lsFragment.get(1) as LeaderboardListFragment).renderdata(
                            lsD
                        )
                    } catch (e: Exception) {
                    }
                } else if (getUser()!!.profileUser.parent_position.equals(MAINDEALER, true)) {
                    //do nothing
                } else {
                    try {
                        ((lsFragment.get(4) as MyAccountFragment).lsFragment.get(1) as LeaderboardListFragment).renderdata(
                            lsD
                        )
                    } catch (e: Exception) {
                    }
                }
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
            }

        })
    }

    fun getLeaderboardmaindealer(){
        showLoadingDialog()
        leaderboadpresenter.getLeaderboardMainDealer(object : DataListInterface<leaderboard> {
            override fun onGetDataSuccess(res: List<leaderboard>) {
                dismisLoadingDialog()
                lsMD.clear()
                lsMD.addAll(res)


                if(
                    getUser()!!.profileUser.parent_position.equals(KACAB,true) ||
                    getUser()!!.profileUser.parent_position.equals(SUPERVISOR,true)
                ) {
                    try {
                        ((lsFragment.get(4) as MyAccountKacabFragment).lsFragment.get(2) as LeaderboardListMDFragment).renderLead(lsMD)
                    } catch (e: Exception) {
                    }
                }else if(getUser()!!.profileUser.parent_position.equals(MAINDEALER,true)) {
                    try {
                        ((lsFragment.get(4) as MyAccountMDFragment).lsFragment.get(1) as LeaderboardListMDFragment).renderLead(lsMD)
                    } catch (e: Exception) {
                    }
                }else {
                    try {
                        ((lsFragment.get(4) as MyAccountFragment).lsFragment.get(2) as LeaderboardListMDFragment).renderLead(lsMD)
                    } catch (e: Exception) {
                    }
                }
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
            }

        })
    }

}
