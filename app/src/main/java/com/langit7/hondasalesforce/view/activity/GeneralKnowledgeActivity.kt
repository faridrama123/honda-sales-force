package com.langit7.hondasalesforce.view.activity

import android.app.Activity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import android.widget.LinearLayout
import android.widget.TextView
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.DialogUtil
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.*
import com.langit7.hondasalesforce.presenter.adapter.FragmentAdapter
import com.langit7.hondasalesforce.presenter.logic.GeneralKnowledgePresenter
import com.langit7.hondasalesforce.presenter.logic.MainPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.fragment.*
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_basic_knowledge.*

class GeneralKnowledgeActivity : BaseActivity() {
    lateinit var mSectionsPagerAdapter: FragmentAdapter
    lateinit var lsFragment: ArrayList<BaseFragmentInterface>

    lateinit var lsServiceTalk:ArrayList<servicetalk>
    lateinit var lsCommunity:ArrayList<community>
    lateinit var lsBengkelModif:ArrayList<bengkelmodif>

    lateinit var lsprovince:ArrayList<region>
    lateinit var lscity:ArrayList<region>
    lateinit var lstype:ArrayList<bengkelmodiftype>
    lateinit var lsservce:ArrayList<bengkelmodifservice>

    lateinit var lssprovince:ArrayList<String>
    lateinit var lsscity:ArrayList<String>
    lateinit var lsstype:ArrayList<String>
    lateinit var lssservce:ArrayList<String>



    lateinit var lsNOS:ArrayList<nos>
    lateinit var lsGrooming:ArrayList<grooming>
    lateinit var lsSalesmanship:ArrayList<productfeature>
    lateinit var lsCS:ArrayList<productfeature>
    lateinit var lsCSL:ArrayList<productfeature>
    lateinit var lsCH:ArrayList<productfeature>
    lateinit var lsCRM:ArrayList<productfeature>

    var hasnos=false
    var hasgro=false
    var hassal=false
    var hascs=false
    var hascsl=false
    var hasch=false
    var hascrm=false





    lateinit var presenter: GeneralKnowledgePresenter

    var hass=false
    var hasc=false
    var hasb=false

    var hasf1=false
//    var hasf2=false
    var hasf3=false
    var hasf4=false

    var curtab=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_knowledge)

        lsServiceTalk= ArrayList()
        lsCommunity=ArrayList()
        lsBengkelModif=ArrayList()

        lsprovince=ArrayList()
        lscity=ArrayList()
        lsservce=ArrayList()
        lstype=ArrayList()

        lssprovince=ArrayList()
        lsscity=ArrayList()
        lssservce=ArrayList()
        lsstype=ArrayList()



        lsNOS=ArrayList()
        lsGrooming=ArrayList()
        lsSalesmanship=ArrayList()
        lsCS=ArrayList()
        lsCSL=ArrayList()
        lsCH=ArrayList()
        lsCRM=ArrayList()


        tactionbartitle.setText(getString(R.string.basicknowledge))
        imgback.setOnClickListener({
            onBackPressed()
        })


        presenter=GeneralKnowledgePresenter(this,APIServices)


        showLoadingDialog()
        presenter.getCommunity(object :DataListInterface<community>{
            override fun onGetDataSuccess(res: List<community>) {
                dismisLoadingDialog()
                lsCommunity.clear()

                for(c in res){
                    var hashead=false
                    for(cc in lsCommunity){
                        if(cc.province.equals(c.province))
                            hashead=true
                    }

                    if(hashead)
                        c.isHeader=false
                    else
                        c.isHeader=true

                    lsCommunity.add(c)
                }






                hasc=true
                init()

            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
                hasc=true
            }

        })

        showLoadingDialog()
        presenter.getServiceTalk(object :DataListInterface<servicetalk>{
            override fun onGetDataSuccess(res: List<servicetalk>) {
                dismisLoadingDialog()
                lsServiceTalk.clear()
                lsServiceTalk.addAll(res)
                hass=true
                init()
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
                hass=true
            }

        })

        showLoadingDialog()
        presenter.getBengkelModifikasi(object :DataListInterface<bengkelmodif>{
            override fun onGetDataSuccess(res: List<bengkelmodif>) {
                dismisLoadingDialog()
                lsBengkelModif.clear()
                lsBengkelModif.addAll(res)
                hasb=true
                init()
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
                hasb=true
            }

        })


        showLoadingDialog()
        presenter.getProvince(object :DataListInterface<region>{
            override fun onGetDataSuccess(res: List<region>) {
                dismisLoadingDialog()
                lsprovince.clear()
                lsprovince.addAll(res)

                lssprovince.clear()
                lssprovince.add("Semua Provinsi")
                for(p in lsprovince){
                    lssprovince.add(p.name)
                }



                hasf1=true
                init()
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
                hasf1=true
                init()
            }

        })

//        showLoadingDialog()
//        presenter.getCity(,object :DataListInterface<region>{
//            override fun onGetDataSuccess(res: List<region>) {
//                dismisLoadingDialog()
//                lscity.clear()
//                lscity.addAll(res)
//
//                lsscity.clear()
//                lsscity.add("Semua Kota")
//                for(c in lscity){
//                    lsscity.add(c.name)
//                }
//
//
//
//                hasf2=true
//                init()
//            }
//
//            override fun onGetDataFailed(msg: String) {
//                dismisLoadingDialog()
//                hasf2=true
//                init()
//            }
//
//        })


        showLoadingDialog()
        presenter.getType(object :DataListInterface<bengkelmodiftype>{
            override fun onGetDataSuccess(res: List<bengkelmodiftype>) {
                dismisLoadingDialog()
                lstype.clear()
                lstype.addAll(res)

                lsstype.clear()
                lsstype.add("Semua Jenis Dealer")
                for(c in lstype){
                    lsstype.add(c.name)
                }

                hasf3=true
                init()
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
                hasf3=true
                init()
            }

        })

        showLoadingDialog()
        presenter.getServiceType(object :DataListInterface<bengkelmodifservice>{
            override fun onGetDataSuccess(res: List<bengkelmodifservice>) {
                dismisLoadingDialog()
                lsservce.clear()
                lsservce.addAll(res)

                lssservce.clear()
                lssservce.add("Semua Jenis Layanan")
                for(c in lsservce){
                    lssservce.add(c.name)
                }

                hasf4=true
                init()
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
                hasf4=true
                init()
            }

        })



        showLoadingDialog()
        presenter.getNOS(object :DataListInterface<nos>{
            override fun onGetDataSuccess(res: List<nos>) {
                dismisLoadingDialog()
                lsNOS.clear()
                lsNOS.addAll(res)
                hasnos=true
                init()
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
                hasnos=true
                init()
            }
        })

        showLoadingDialog()
        presenter.getGrooming(object :DataListInterface<grooming>{
            override fun onGetDataSuccess(res: List<grooming>) {
                dismisLoadingDialog()
                lsGrooming.clear()
                lsGrooming.addAll(res)
                hasgro=true
                init()
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
                hasgro=true
                init()
            }
        })

        showLoadingDialog()
        presenter.getSalesmanship(object :DataListInterface<productfeature>{
            override fun onGetDataSuccess(res: List<productfeature>) {
                dismisLoadingDialog()
                lsSalesmanship.clear()
                lsSalesmanship.addAll(res)
                hassal=true
                init()
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
                hassal=true
                init()
            }
        })

        showLoadingDialog()
        presenter.getCS(object :DataListInterface<productfeature>{
            override fun onGetDataSuccess(res: List<productfeature>) {
                dismisLoadingDialog()
                lsCS.clear()
                lsCS.addAll(res)
                hascs=true
                init()
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
                hascs=true
                init()
            }
        })

        showLoadingDialog()
        presenter.getCSL(object :DataListInterface<productfeature>{
            override fun onGetDataSuccess(res: List<productfeature>) {
                dismisLoadingDialog()
                lsCSL.clear()
                lsCSL.addAll(res)
                hascsl=true
                init()
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
                hascsl=true
                init()
            }
        })

        showLoadingDialog()
        presenter.getCH(object :DataListInterface<productfeature>{
            override fun onGetDataSuccess(res: List<productfeature>) {
                dismisLoadingDialog()
                lsCH.clear()
                lsCH.addAll(res)
                hasch=true
                init()
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
                hasch=true
                init()
            }
        })

        showLoadingDialog()
        presenter.getCRM(object :DataListInterface<productfeature>{
            override fun onGetDataSuccess(res: List<productfeature>) {
                dismisLoadingDialog()
                lsCRM.clear()
                lsCRM.addAll(res)
                hascrm=true
                init()
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
                hascrm=true
                init()
            }
        })

        var haspoin=false
        var i=0
        var th=Thread{
            while (actvisible && !haspoin){

                Thread.sleep(1000)
                i++

                if(i==30){
                    runOnUiThread {
                        if(getUser()!=null){
                            var pp = MainPresenter(ctx as BaseActivity, APIServices)
//                        if(!function.getPreverenceBool(ctx, function.getUser(ctx)!!.id+function.getToday() )) { //loss checking today
                            function.savePreverence(ctx, function.getUser(ctx)!!.id+function.getToday(), true)



                            pp.savePoint("6", function.getToday(), object : ObjectResponseInterface<history> {
                                override fun onSuccess(res: history) {
                                    haspoin = true
                                    try {
                                        if (res.total_point.toInt() > 0) {
                                            DialogUtil.createScoreDialog(ctx as Activity, "+" + res.total_point)
                                        }
                                    } catch (e: Exception) {
                                    }
                                }

                                override fun onFailed(msg: String) {
                                    haspoin = true
                                }

                            })

                            pp.saveCounter("4", function.getToday(), object : ObjectResponseInterface<history> {
                                override fun onSuccess(res: history) {
                                }

                                override fun onFailed(msg: String) {
                                }

                            })
//                        }
                        }

                    }
                }
            }
        }
        th.start()


    }


    fun init(){
        if(hasb&&hasc&&hass&&hasf1&&hasf3&&hasf4&&hasnos&&hasgro&&hassal&&hascs&&hascsl&&hasch&&hascrm){
            setupFragment()
            DialogUtil.createBasicKnowledgeDialog(ctx as Activity)
        }
    }


    fun setupFragment() {

        lsFragment = ArrayList()
        lsFragment.add(GeneralKnowledgeFragment.Instantiate("Script Follow Up", lsCS))
        lsFragment.add(ServiceTalkFragment.Instantiate(getString(R.string.servicetalk), lsServiceTalk))
        lsFragment.add(CommunityFragment.Instantiate(getString(R.string.community), lsCommunity))
        lsFragment.add(BengkelModifFragment.Instantiate("Bengkel \nModifikasi", lsBengkelModif))


        lsFragment.add(GeneralKnowledgeNOSFragment.Instantiate("NOS", lsNOS))
        lsFragment.add(GeneralKnowledgeGroomingFragment.Instantiate("Grooming", lsGrooming))
        lsFragment.add(GeneralKnowledgeFragment.Instantiate("Salesmanship", lsSalesmanship))

//        lsFragment.add(GeneralKnowledgePlainFragment.Instantiate("Customer Service for Leader", lsCSL))
        lsFragment.add(GeneralKnowledgeCHFragment.Instantiate("CS & CH", lsCH))
        lsFragment.add(GeneralKnowledgeFragment.Instantiate("CRM (Tips & Trik)", lsCRM))

        mSectionsPagerAdapter = FragmentAdapter(supportFragmentManager, lsFragment)
        view_pager.setAdapter(mSectionsPagerAdapter)



        tabs.setupWithViewPager(view_pager)
        setuptab()
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                permakTabs()
            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
        if(curtab>-1)
            view_pager.setCurrentItem(curtab)
        else
            view_pager.setCurrentItem(intent.getIntExtra("tid", 0), true)

        view_pager.offscreenPageLimit=3
    }

    private fun setuptab() {
        tabs.setTabGravity(TabLayout.GRAVITY_FILL)
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE)
        tabs.setSelectedTabIndicatorHeight(function.dp2px(ctx, 1))


//        val ll = tabs.getChildAt(0) as LinearLayout
//
//        for (i in 0 until tabs.getTabCount()) {
//            ll.setPadding(0, 0, 0, 0)
//            val rl = ll.getChildAt(i) as LinearLayout
//            if (rl != null) {
//                val lp = LinearLayout.LayoutParams(function.dp2px(ctx, 20), function.dp2px(ctx, 20))
//                lp.setMargins(0, 0, 0, function.dp2px(ctx, -3))
//                val tv = rl.getChildAt(1) as TextView
////                tv.textSize = 8f
//                tv.setPadding(
//                    function.dp2px(ctx, 10),
//                    function.dp2px(ctx, 3),
//                    function.dp2px(ctx, 10),
//                    function.dp2px(ctx, 3)
//                )
//                tv.setBackgroundColor(Color.TRANSPARENT)
//                tv.layoutParams.width = function.dp2px(ctx, 200)
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
