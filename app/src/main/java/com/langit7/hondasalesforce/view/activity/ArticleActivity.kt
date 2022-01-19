package com.langit7.hondasalesforce.view.activity

import android.graphics.Color
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.article

import com.langit7.hondasalesforce.presenter.adapter.FragmentAdapter
import com.langit7.hondasalesforce.presenter.logic.ArticlePresenter
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.view.fragment.ArticleFragment
import com.langit7.hondasalesforce.view.fragment.BaseFragmentInterface
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_main.*

class ArticleActivity : BaseActivity() {
    lateinit var mSectionsPagerAdapter: FragmentAdapter
    lateinit var lsFragment: ArrayList<BaseFragmentInterface>


    var lsartc1=ArrayList<article>()
//    var lsartc2=ArrayList<article>()


    lateinit var presenter: ArticlePresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)


        tactionbartitle.setText(getString(R.string.article))
        imgback.setOnClickListener({
            onBackPressed()
        })

        showLoadingDialog()
        presenter= ArticlePresenter(this,APIServices)

        tabs.visibility=View.GONE
    }


    fun setupFragment() {


        lsFragment = ArrayList()
        lsFragment.add(ArticleFragment.Instantiate("Comunity Artikel",lsartc1))
//        lsFragment.add(ArticleFragment.Instantiate("AARC",lsartc2))



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

    override fun onResume() {
        super.onResume()
        presenter.getListArticle(object : DataListInterface<article>{
            override fun onGetDataSuccess(res: List<article>) {
                dismisLoadingDialog()
                lsartc1.clear()
                lsartc1.addAll(res)
                setupFragment()
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
                Toast(msg)
            }
        })
    }
}
