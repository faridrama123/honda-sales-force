package com.langit7.hondasalesforce.view.activity

import android.graphics.Color
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.*
import com.langit7.hondasalesforce.presenter.adapter.FragmentAdapter
import com.langit7.hondasalesforce.presenter.logic.ProductPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.view.fragment.*
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : BaseActivity() {
    lateinit var mSectionsPagerAdapter: FragmentAdapter
    lateinit var lsFragment: ArrayList<BaseFragmentInterface>


    var lsCat=ArrayList<productcategory>()
    var lsAllproduct=ArrayList<product>()
    var lsCusParts=ArrayList<cuspart>()
    var lsSP=ArrayList<salesprogram>()
    var imgcomparation:MutableList<salesprogram> = mutableListOf()

    var hasprod=false
    var hassp=false
    var hasimg=false

    lateinit var presenter: ProductPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        lsCat= ArrayList()
        lsAllproduct=ArrayList()
        lsCusParts=ArrayList()

        tactionbartitle.setText(getString(R.string.productknowledge))
        imgback.setOnClickListener({
            onBackPressed()
        })


        presenter= ProductPresenter(this,APIServices)

        showLoadingDialog()
        presenter.getCat(object : DataListInterface<productcategory> {
            override fun onGetDataSuccess(res: List<productcategory>) {
                //
                lsCat.clear()
                lsCat.addAll(res.sortedWith(compareBy({ it.getOrder()})))



                lsAllproduct.clear()
                if(lsCat.size>0) {


                    for (cat in lsCat) {
                        showLoadingDialog()
                        presenter.getProductbyCat(cat.id.toString(), object : DataListInterface<product> {
                            override fun onGetDataSuccess(res: List<product>) {
                                dismisLoadingDialog()
                                var lsa = ArrayList<product>()
                                lsa.addAll(res)
                                cat.lsProduct = lsa
                                lsAllproduct.addAll(lsa)
                                if(que==0){
                                    hasprod=true
                                    init()
                                }
                            }

                            override fun onGetDataFailed(msg: String) {
                                dismisLoadingDialog()
                            }


                        })
                    }


                }else{
                    Toast("Belum ada Data!")
                    finish()
                }

                dismisLoadingDialog()
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
            }
        })


//        showLoadingDialog()
//        presenter.getCusPart(object : DataListInterface<cuspart>{
//            override fun onGetDataSuccess(res: List<cuspart>) {
//                dismisLoadingDialog()
//
//                lsCusParts.clear()
//                lsCusParts.addAll(res)
//
//
////                if(que==0){
////                    setupFragment()
////                }
//
//
//            }
//
//            override fun onGetDataFailed(msg: String) {
//                dismisLoadingDialog()
//            }
//
//
//        })


        showLoadingDialog()
        presenter.getSalesProgram(object : DataListInterface<salesprogram>{
            override fun onGetDataSuccess(res: List<salesprogram>) {
                dismisLoadingDialog()

                lsSP.clear()
                lsSP.addAll(res)
                hassp=true
                init()

//                if(que==0){
//                    setupFragment()
//                }


            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
                hassp=true
                init()
            }


        })
        showLoadingDialog()
        presenter.getImageComparation(object : DataListInterface<salesprogram>{
            override fun onGetDataSuccess(res: List<salesprogram>) {
                dismisLoadingDialog()

                if(res.size>0)
                    imgcomparation.addAll(res)

                hasimg=true
                init()


            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
                hasimg=true
                init()
            }


        })


    }


    fun init(){

        if(hasprod && hassp && hasimg)
            setupFragment()

    }

    fun setupFragment() {
//        Log.e("Setup Fragment","A")
        lsFragment = ArrayList()


        lsFragment.add(ProductSalesProgramFragment.Instantiate("Sales Program(National)",lsSP))
        for(cat in lsCat) {
            lsFragment.add(ProductFragment.Instantiate(cat.name, cat.lsProduct.toList()))
        }
//        lsFragment.add(AccessoriesFragment.Instantiate(getString(R.string.acessories), lsAllproduct))
//        lsFragment.add(CustomizingPartsFragment.Instantiate(getString(R.string.customizingparts), lsCusParts))

        if(getUser()?.profileUser?.position?.contains("sobat",true)?.not()?:true)
        lsFragment.add(ImageFragment.Instantiate("Poster Komparasi",imgcomparation))


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

        view_pager.setCurrentItem(intent.getIntExtra("tid", 0), true)
        view_pager.offscreenPageLimit=lsCat.size+2
    }

    private fun setuptab() {
        tabs.setTabGravity(TabLayout.GRAVITY_FILL)
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE)
//        tabs.setSelectedTabIndicatorHeight(function.dp2px(ctx, 1))


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