package com.langit7.hondasalesforce.view.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import android.widget.LinearLayout
import android.widget.TextView
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.apparelcategory
import com.langit7.hondasalesforce.model.video
import com.langit7.hondasalesforce.model.videocat
import com.langit7.hondasalesforce.presenter.adapter.FragmentAdapter
import com.langit7.hondasalesforce.presenter.logic.ApparelPresenter
import com.langit7.hondasalesforce.presenter.logic.MainPresenter
import com.langit7.hondasalesforce.presenter.logic.VideoPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.view.fragment.ApparelFragment
import com.langit7.hondasalesforce.view.fragment.BaseFragmentInterface
import com.langit7.hondasalesforce.view.fragment.VideoFragment
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : BaseActivity() {
    lateinit var mSectionsPagerAdapter: FragmentAdapter
    lateinit var lsFragment: ArrayList<BaseFragmentInterface>


    lateinit var lsVideo:java.util.ArrayList<video>
    lateinit var lsVideoCat:java.util.ArrayList<videocat>


    var hascat=false
    var hasvid=false

    lateinit var presenter: VideoPresenter
    lateinit var mainpresenter: MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        lsVideo= ArrayList()
        lsVideoCat= ArrayList()

        tactionbartitle.setText(getString(R.string.video))
        imgback.setOnClickListener({
            onBackPressed()
        })


        presenter= VideoPresenter(this,APIServices)
        mainpresenter= MainPresenter(this,APIServices)

        showLoadingDialog()
        presenter.getVideo(object :DataListInterface<video>{
            override fun onGetDataSuccess(res: List<video>) {
                dismisLoadingDialog()
                lsVideo.clear()
                lsVideo.addAll(res)

                hasvid=true
                init()
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
            }

        })

        showLoadingDialog()
        presenter.getVideocat(object :DataListInterface<videocat>{
            override fun onGetDataSuccess(res: List<videocat>) {
                dismisLoadingDialog()
                lsVideoCat.clear()
                lsVideoCat.addAll(res)
                hascat=true
                init()
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
            }

        })


    }

    fun init(){
        if(hascat&&hasvid)
            setupFragment()
    }

    fun setupFragment() {

        lsFragment = ArrayList()

        for(c in lsVideoCat){
            lsFragment.add(VideoFragment.Instantiate(c.title, presenter.getVideobyCat(lsVideo,c.id)))
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
        view_pager.offscreenPageLimit=4
        view_pager.setCurrentItem(intent.getIntExtra("tid", 0), true)
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
