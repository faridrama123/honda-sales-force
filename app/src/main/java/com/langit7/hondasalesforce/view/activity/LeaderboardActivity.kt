package com.langit7.hondasalesforce.view.activity

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.leaderboard
import com.langit7.hondasalesforce.model.leaderboardhistory
import com.langit7.hondasalesforce.presenter.adapter.FragmentAdapter
import com.langit7.hondasalesforce.presenter.logic.LeaderboardPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.view.fragment.BaseFragmentInterface
import com.langit7.hondasalesforce.view.fragment.LeaderboardFragment
import kotlinx.android.synthetic.main.activity_product.*

class LeaderboardActivity : BaseActivity() {

    lateinit var mSectionsPagerAdapter: FragmentAdapter
    lateinit var lsFragment: ArrayList<BaseFragmentInterface>


    lateinit var lsd:ArrayList<leaderboard>
    lateinit var lsmd:ArrayList<leaderboard>
    lateinit var lsh:ArrayList<leaderboardhistory>

    var hasd=false
    var hasmd=false
    var hash=false

    lateinit var presenter:LeaderboardPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leader_board)

        lsd=ArrayList()
        lsmd=ArrayList()
        lsh= ArrayList()


        presenter= LeaderboardPresenter(this,APIServices)


        showLoadingDialog()
        presenter.getLeaderboardDealer(object :DataListInterface<leaderboard>{
            override fun onGetDataSuccess(res: List<leaderboard>) {
                lsd.clear()
                lsd.addAll(res)
                hasd=true
                init()
            }

            override fun onGetDataFailed(msg: String) {
                Toast(msg)
                hasd=true
                init()
            }

        })
        presenter.getLeaderboardMainDealer(object :DataListInterface<leaderboard>{
            override fun onGetDataSuccess(res: List<leaderboard>) {
                lsmd.clear()
                lsmd.addAll(res)
                hasmd=true
                init()
            }

            override fun onGetDataFailed(msg: String) {
                Toast(msg)
                hasmd=true
                init()
            }

        })
        presenter.getLeaderboardHistory(object :DataListInterface<leaderboardhistory>{
            override fun onGetDataSuccess(res: List<leaderboardhistory>) {
                lsh.clear()
                lsh.addAll(res)
                hash=true
                init()
            }

            override fun onGetDataFailed(msg: String) {
                Toast(msg)
                hash=true
                init()
            }

        })

    }

fun init(){
        if(hasd&&hasmd&&hash){
            dismisLoadingDialog()
            setupFragment()
        }
    }

    fun setupFragment() {
//        Log.e("Setup Fragment","A")
        lsFragment = ArrayList()
        lsFragment.add(LeaderboardFragment.Instantiate(lsd,lsmd,lsh))



        mSectionsPagerAdapter = FragmentAdapter(supportFragmentManager, lsFragment)
        view_pager.setAdapter(mSectionsPagerAdapter)




        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        view_pager.setCurrentItem(intent.getIntExtra("tid", 0), true)
        view_pager.offscreenPageLimit=1
    }
}
