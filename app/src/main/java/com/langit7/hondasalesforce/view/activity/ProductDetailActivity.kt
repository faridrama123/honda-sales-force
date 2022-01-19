package com.langit7.hondasalesforce.view.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.DialogUtil
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.*
import com.langit7.hondasalesforce.presenter.adapter.FragmentAdapter
import com.langit7.hondasalesforce.presenter.logic.ApparelPresenter
import com.langit7.hondasalesforce.presenter.logic.MainPresenter
import com.langit7.hondasalesforce.presenter.logic.ProductPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.fragment.*
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_product_datail.*

class ProductDetailActivity : BaseActivity() {
    lateinit var mSectionsPagerAdapter: FragmentAdapter
    lateinit var lsFragment: ArrayList<BaseFragmentInterface>


    lateinit var mProduct: product

    var lsFeature = ArrayList<productfeature>()
    var lsSalesTalk = ArrayList<salestalk>()
    var lsAcc = ArrayList<productaccessories>()
    var lsCat = ArrayList<apparelcategory>()

    var lsSpecpec = ArrayList<spesification>()
    var lsspeccat = ArrayList<specification_category>()
    var lsspeccon = ArrayList<specification_content>()


    var hassales = false
    var hasfea = false
    var hashis = false
    var hasacc = false
    var hasapr = false
    var hasspc = false

    lateinit var presenter: ProductPresenter
    lateinit var mainpresenter: MainPresenter
    lateinit var presenter_: ApparelPresenter


    var haspoin=false

    var hasfailed=false

    var gen=""
    var age=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_datail)

        mProduct = intent.getSerializableExtra("pr") as product
        if(intent.getStringExtra("gen")!=null)
            gen=intent.getStringExtra("gen")?:""
        if(intent.getStringExtra("age")!=null)
            age=intent.getStringExtra("age")?:""

        if (mProduct == null)
            finish()

        tactionbartitle.setText(getString(R.string.productknowledge))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tactionbartitle.setTextColor(resources.getColor(R.color.white, null))
        } else {
            tactionbartitle.setTextColor(resources.getColor(R.color.white))
        }
        imgback.setImageResource(R.drawable.arrow_back_white)
        imgback.setOnClickListener({
            onBackPressed()
        })


        ttitle.setText(mProduct.title)

        mainpresenter = MainPresenter(this, APIServices)

        presenter = ProductPresenter(this, APIServices)
        showLoadingDialog()
        presenter.getProductFeature(mProduct.id,gen,age, object : DataListInterface<productfeature> {
            override fun onGetDataSuccess(res: List<productfeature>) {
                dismisLoadingDialog()
                lsFeature.clear()
                lsFeature.addAll(res)
                hasfea = true
                init()
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
                hasfea = false
                if(!hasfailed) {
                    hasfailed=true
                    Toast("Koneksi Internet Gagal, Silahkan Coba lagi")
                }
                finish()
            }

        })


        showLoadingDialog()
        presenter.getSalesTalk(mProduct.id, object : DataListInterface<salestalk> {
            override fun onGetDataSuccess(res: List<salestalk>) {
                dismisLoadingDialog()
                lsSalesTalk.clear()
                lsSalesTalk.addAll(res)
                hassales = true
                init()
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
                hassales = false
                if(!hasfailed) {
                    hasfailed=true
                    Toast("Koneksi Internet Gagal, Silahkan Coba lagi")
                }
                finish()
            }


        })

        showLoadingDialog()
        presenter.getProductAccessories(mProduct.id, object : DataListInterface<productaccessories> {
            override fun onGetDataSuccess(res: List<productaccessories>) {
                dismisLoadingDialog()
                lsAcc.clear()
                lsAcc.addAll(res)
                hasacc=true
                init()
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
                hasacc=false
                if(!hasfailed) {
                    hasfailed=true
                    Toast("Koneksi Internet Gagal, Silahkan Coba lagi")
                }
                finish()
            }

        })

        showLoadingDialog()

        presenter.getSpesificationDetail(mProduct.id, object :DataListInterface<spesification>{
            override fun onGetDataSuccess(res: List<spesification>) {
                dismisLoadingDialog()
                lsSpecpec.clear()
                lsSpecpec.addAll(res)
                hasspc=true
                init()
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
                hasspc=false
                if(!hasfailed) {
                    hasfailed=true
                    Toast("Koneksi Internet Gagal, Silahkan Coba lagi")
                }
                finish()
            }
        })

        showLoadingDialog()
        presenter_= ApparelPresenter(this,APIServices)
        presenter_.getApparelCat(object : DataListInterface<apparelcategory> {
            override fun onGetDataSuccess(res: List<apparelcategory>) {
                dismisLoadingDialog()
                lsCat.clear()
                lsCat.addAll(res)
                hasapr = true
                init()
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
                hasapr = false
                if (!hasfailed) {
                    hasfailed = true
                    Toast("Koneksi Internet Gagal, Silahkan Coba lagi")
                }
                finish()
            }
        })

var i=0
var th=Thread{
    while (actvisible && !haspoin){

        Thread.sleep(1000)
        i++

        if(i>=30){
            runOnUiThread {
                haspoin=true

                var count=function.getPreverence(ctx,function.getUser(ctx)!!.id+"productpoint")
                if(count.length==0){
                    count="1"
                }

                if(!function.getPreverenceBool(ctx, function.getUser(ctx)!!.id+function.getToday() + mProduct.id) ) {
                    function.savePreverence(ctx, function.getUser(ctx)!!.id+function.getToday() + mProduct.id, true)


                    var pp = MainPresenter(ctx as BaseActivity, APIServices)
                    if(count.toInt()<=3) {
                        pp.savePoint(
                            "5",
                            function.getToday() + mProduct.id,
                            object : ObjectResponseInterface<history> {
                                override fun onSuccess(res: history) {
                                    try {

                                        function.savePreverence(
                                            ctx,
                                            function.getUser(ctx)!!.id + "productpoint",
                                            (count.toInt() + 1).toString()
                                        )

                                        if (res.total_point.toInt() > 0) {
                                            DialogUtil.createScoreDialog(ctx as Activity, "+" + res.total_point)
                                        }
                                    } catch (e: Exception) {
                                    }
                                }

                                override fun onFailed(msg: String) {
                                }

                            })
                    }

                    pp.saveCounter(
                        "3",
                        function.getToday() + mProduct.id,
                        object : ObjectResponseInterface<history> {
                            override fun onSuccess(res: history) {
                            }

                            override fun onFailed(msg: String) {
                            }

                        })
                }

            }
        }
    }
}
th.start()

}



fun init() {
        if (hasfea && hassales&& hasacc && hasspc && hasapr) {
            setupFragment()
        }
    }

    fun setupFragment() {
//        Log.e("Setup Fragment","A")
        lsFragment = ArrayList()
        lsFragment.add(ProductDesainFragment.Instantiate(mProduct))
        lsFragment.add(ProductSalesTalkFragment.Instantiate("Sales Talk", lsSalesTalk))
        lsFragment.add(ProductFiturFragment.Instantiate("Fitur", lsFeature, mProduct))
//        lsFragment.add(ProductPerformaFragment.Instantiate("Performa"))
        lsFragment.add(ProductRidingComfortFragment.Instantiate("Others", mProduct))
//        lsFragment.add(ProductEmotionFragment.Instantiate("Emotion"))
        lsFragment.add(ProductAccessoriesFragment.Instantiate("Accessories", lsAcc, mProduct))
        lsFragment.add(Aparel_CategoryFragment.Instantiate("Apparel", lsCat))
        lsFragment.add(SpesificationFragment.Instantiate("Spesifikasi", lsSpecpec, mProduct))

//        lsFragment.add(ProductFiturFragment.Instantiate("NOS", lsFeature, mProduct))
//        lsFragment.add(ProductFiturFragment.Instantiate("Grooming", lsFeature, mProduct))
//        lsFragment.add(ProductFiturFragment.Instantiate("Salesmanship", lsFeature, mProduct))
//        lsFragment.add(ProductFiturFragment.Instantiate("Customer Service", lsFeature, mProduct))
//        lsFragment.add(ProductFiturFragment.Instantiate("Customer Service for Leader", lsFeature, mProduct))
//        lsFragment.add(ProductFiturFragment.Instantiate("Complaint Handling", lsFeature, mProduct))
//        lsFragment.add(ProductFiturFragment.Instantiate("CRM (Tips & Trik)", lsFeature, mProduct))




        mSectionsPagerAdapter = FragmentAdapter(supportFragmentManager, lsFragment)
        view_pager.setAdapter(mSectionsPagerAdapter)



        tabs.setupWithViewPager(view_pager)
        setuptab()
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                permakTabs()

            }

            override fun onPageSelected(position: Int) {
//                if (position == 1) {
//                    if (!hashis)
//                        mainpresenter.savePoint("3", mProduct.id, object : ObjectResponseInterface<history> {
//                            override fun onSuccess(res: history) {
//                                hashis = true
//                            }
//
//                            override fun onFailed(msg: String) {
//
//                            }
//
//                        })
//                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        view_pager.setCurrentItem(intent.getIntExtra("tid", 0), true)
        view_pager.offscreenPageLimit = 3
    }

    private fun setuptab() {
        tabs.setTabGravity(TabLayout.GRAVITY_FILL)
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE)
//        tabs.setSelectedTabIndicatorHeight(function.dp2px(ctx, 1))


        val ll = tabs.getChildAt(0) as LinearLayout

        for (i in 0 until tabs.getTabCount()) {
            ll.setPadding(0, 0, 0, 0)
            val rl = ll.getChildAt(i) as LinearLayout
            rl.orientation = LinearLayout.HORIZONTAL
            if (rl != null) {
                val lp = LinearLayout.LayoutParams(function.dp2px(ctx, 20), function.dp2px(ctx, 20))
                lp.setMargins(0, 0, 0, function.dp2px(ctx, -3))
                val tv = rl.getChildAt(1) as TextView
//                tv.textSize = 8f
                tv.setPadding(
                    function.dp2px(ctx, 10),
                    function.dp2px(ctx, 3),
                    function.dp2px(ctx, 10),
                    function.dp2px(ctx, 3)
                )
                tv.setBackgroundColor(Color.TRANSPARENT)
                tv.layoutParams.width = function.dp2px(ctx, 200)

                var iv = ImageView(ctx)
                when (i) {
                    0 -> iv.setImageResource(R.drawable.icon_desain_f)
                    1 -> iv.setImageResource(R.drawable.icon_salestalk_f)
                    2 -> iv.setImageResource(R.drawable.icon_fitur_f)
                    3 -> iv.setImageResource(R.drawable.icon_ridingcomfort_f)
                    4 -> iv.setImageResource(R.drawable.icon_accessories_f)
                    5 -> iv.setImageResource(R.drawable.icon_apparel_f)
                    6 -> iv.setImageResource(R.drawable.icon_spesifikasi_f)
                    7 -> iv.setImageResource(R.drawable.icon_nos_f)
                    8 -> iv.setImageResource(R.drawable.icon_grooming_f)
                    9 -> iv.setImageResource(R.drawable.icon_salesman_f)
                    10 -> iv.setImageResource(R.drawable.icon_customerservice_f)
                    11 -> iv.setImageResource(R.drawable.icon_customerserviceleader_f)
                    12 -> iv.setImageResource(R.drawable.icon_complainhandling_f)
                    13 -> iv.setImageResource(R.drawable.icon_tipstricks_f)
                }

                rl.addView(iv, 0)

            }
        }
    }

    private fun permakTabs() {
        val ll = tabs.getChildAt(0) as LinearLayout
        for (i in 0 until tabs.getTabCount()) {
            val rl = ll.getChildAt(i) as LinearLayout
            val tv = rl.getChildAt(2) as TextView
            //View v1 = rl.getChildAt(1);
            //v1.setVisibility(View.GONE);
            if (tabs.getSelectedTabPosition() == i) {
                //tv.setTextColor(Color.WHITE);
                //tv.getLayoutParams().width=dp2px(ctx,200);
                tv.setTextColor(resources.getColor(R.color.black))
//                rl.setBackgroundColor(resources.getColor(R.color.red))

//                when (i) {
//                    0 -> iv.drawable(R.drawable.icon_home_f)
//                    1 -> iv.setImageResource(R.drawable.icon_kuis_f)
//                    2 -> iv.setImageResource(R.drawable.icon_survei_f)
//                    3 -> iv.setImageResource(R.drawable.icon_notifikasi_f)
//                    4 -> iv.setImageResource(R.drawable.icon_akunsaya_f)
//                }


            } else {

                tv.setTextColor(resources.getColor(R.color.gray))
//                rl.setBackgroundColor(resources.getColor(R.color.white))
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==12){

            var found=false
            for(c in lsCat){
                for (a in c.lsApparel){
                    if(a.id.equals(data!!.getStringExtra("code"))) {
                        var ii= Intent(ctx,ApparelDetailActivity::class.java)
                        ii.putExtra("apr",a)
                        startActivity(ii)
                        found=true
                        break;
                    }
                    if(found)
                        break;
                }
            }

            if(!found)
                Toast("Apparel tidak ditemukan!")

        }
    }


}
