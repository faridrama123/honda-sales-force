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
import com.langit7.hondasalesforce.model.quiz
import com.langit7.hondasalesforce.presenter.adapter.FragmentAdapter
import com.langit7.hondasalesforce.view.fragment.BaseFragmentInterface
import com.langit7.hondasalesforce.view.fragment.QuizFragment
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_main.*


class QuizNewActivity : BaseActivity() {
    lateinit var mSectionsPagerAdapter: FragmentAdapter
    lateinit var lsFragment: ArrayList<BaseFragmentInterface>
    var lsData=ArrayList<quiz>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_new)
        lsData = intent.getSerializableExtra("lsQuizData") as ArrayList<quiz>
        tactionbartitle.setText(getString(R.string.kuis))
        imgback.setOnClickListener({
            onBackPressed()
        })
        tabs.visibility= View.GONE
        setupFragment()

    }

    fun setupFragment() {
        lsFragment = ArrayList()
        lsFragment.add(QuizFragment.Instantiate(lsData))
        mSectionsPagerAdapter = FragmentAdapter(supportFragmentManager, lsFragment)
        view_pager.setAdapter(mSectionsPagerAdapter)

        tabs.setupWithViewPager(view_pager)
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