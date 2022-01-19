package com.langit7.hondasalesforce.view.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.apparel
import com.langit7.hondasalesforce.model.apparelcategory
import com.langit7.hondasalesforce.presenter.adapter.FragmentAdapter
import com.langit7.hondasalesforce.presenter.logic.ApparelPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.view.fragment.ApparelFragment
import com.langit7.hondasalesforce.view.fragment.BaseFragmentInterface
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_apparel.*
import java.security.KeyStore

class ApparelActivity : BaseActivity() {
    lateinit var mSectionsPagerAdapter: FragmentAdapter
    lateinit var lsFragment: ArrayList<BaseFragmentInterface>


    lateinit var lsCat:java.util.ArrayList<apparelcategory>

    lateinit var presenter:ApparelPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apparel)



        lsCat= ArrayList()

        tactionbartitle.setText(getString(R.string.apparel))
        imgback.setOnClickListener({
            onBackPressed()
        })

        showLoadingDialog()
        presenter= ApparelPresenter(this,APIServices)

        presenter.getApparelCat(object : DataListInterface<apparelcategory> {
            override fun onGetDataSuccess(res: List<apparelcategory>) {
                lsCat.clear()
                lsCat.addAll(res)

                if(lsCat.size>0) {



                    for (cat in lsCat) {
                        showLoadingDialog()
                        presenter.getApparelbyCat(cat.id.toString(), object : DataListInterface<apparel> {
                            override fun onGetDataSuccess(res: List<apparel>) {
                                dismisLoadingDialog()
                                var lsa = ArrayList<apparel>()
                                lsa.addAll(res)
                                cat.lsApparel = lsa

                                if(que==0){
                                    setupFragment()
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
                Toast(msg)
            }
        })


        imgscan.setOnClickListener {
            var ii= Intent(ctx,ScanActivity::class.java)
            startActivityForResult(ii,1)
        }
    }


    fun setupFragment() {

        lsFragment = ArrayList()
        for(cat in lsCat) {
            lsFragment.add(ApparelFragment.Instantiate(cat.title, cat.lsApparel.toList()))


        }

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
    }

    private fun setuptab() {
        tabs.setTabGravity(TabLayout.GRAVITY_FILL)
        tabs.setTabMode(TabLayout.MODE_FIXED)
        tabs.setSelectedTabIndicatorHeight(function.dp2px(ctx, 1))


        val ll = tabs.getChildAt(0) as LinearLayout

        for (i in 0 until tabs.getTabCount()) {
            ll.setPadding(0, 0, 0, 0)
            val rl = ll.getChildAt(i) as LinearLayout
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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode==12){

            var found=false
            for(c in lsCat){
                for (a in c.lsApparel){
                    if(a.id.equals(data!!.getStringExtra("code"))) {
                        var ii=Intent(ctx,ApparelDetailActivity::class.java)
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