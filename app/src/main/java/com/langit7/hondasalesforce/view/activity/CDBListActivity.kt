package com.langit7.hondasalesforce.view.activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.viewpager.widget.ViewPager
import android.widget.LinearLayout
import android.widget.TextView
import com.langit7.hondasalesforce.BuildConfig
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.DialogUtil
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.cdb
import com.langit7.hondasalesforce.model.history
import com.langit7.hondasalesforce.presenter.adapter.FragmentAdapter
import com.langit7.hondasalesforce.presenter.logic.CDBPresenter
import com.langit7.hondasalesforce.presenter.logic.MainPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.fragment.BaseFragmentInterface
import com.langit7.hondasalesforce.view.fragment.CDBFragment
import kotlinx.android.synthetic.main.activity_cdblist.*
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

class CDBListActivity : BaseActivity() {

    lateinit var mSectionsPagerAdapter: FragmentAdapter
    lateinit var lsFragment: ArrayList<BaseFragmentInterface>


     var lsActive=ArrayList<cdb>()
     var lsDeal=ArrayList<cdb>()
     var lsNodeal=ArrayList<cdb>()

    lateinit var presenter: CDBPresenter

    var phone=""

    var hasactive=false
    var hasdeal=false
    var hasnodeal=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cdblist)


        tactionbartitle.setText(getString(R.string.prospect))
        imgback.setOnClickListener({
            onBackPressed()
        })

        presenter= CDBPresenter(this)

        presenter.getCDB("0",object : DataListInterface<cdb>{
            override fun onGetDataSuccess(res: List<cdb>) {
                lsActive.clear()
                lsActive.addAll(res)
                hasactive=true
                init()
            }

            override fun onGetDataFailed(msg: String) {
                hasactive=true
                init()
            }
        })

        presenter.getCDB("1",object : DataListInterface<cdb>{
            override fun onGetDataSuccess(res: List<cdb>) {
                lsDeal.clear()
                lsDeal.addAll(res)
                hasdeal=true
                init()
            }

            override fun onGetDataFailed(msg: String) {
                hasdeal=true
                init()
            }
        })

        presenter.getCDB("2",object : DataListInterface<cdb>{
            override fun onGetDataSuccess(res: List<cdb>) {
                lsNodeal.clear()
                lsNodeal.addAll(res)
                hasnodeal=true
                init()
            }

            override fun onGetDataFailed(msg: String) {
                hasnodeal=true
                init()
            }
        })
        imgadd.setOnClickListener{
            val ii=Intent(ctx,CDBActivity::class.java)
            startActivity(ii)

        }


        imgshare.setOnClickListener {
            share()

            var pp= MainPresenter(ctx as BaseActivity,APIServices)
//            pp.saveCounter("7", function.getToday(),object : ObjectResponseInterface<history> {
//                override fun onSuccess(res: history) {
//                }
//
//                override fun onFailed(msg: String) {
//                }
//
//            })

            pp.savePoint("10", function.getToday(),object : ObjectResponseInterface<history> {
                override fun onSuccess(res: history) {
                    try {
                        if (res.total_point.toInt() > 0) {
                            DialogUtil.createScoreDialog(ctx as Activity, "+" + res.total_point)
                        }
                    }catch (e:Exception){}
                }

                override fun onFailed(msg: String) {
                }

            })



        }

    }


    fun init(){
        if(hasactive && hasdeal && hasnodeal){
            setupFragment()
        }
    }

    fun setupFragment() {

        lsFragment = ArrayList()
        lsFragment.add(CDBFragment.Instantiate("DAFTAR PROSPECT", lsActive,true))
        lsFragment.add(CDBFragment.Instantiate("DEAL", lsDeal,false))
//        lsFragment.add(CDBFragment.Instantiate("NO DEAL", lsNodeal,false))



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

    fun checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CALL_PHONE)
            != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CALL_PHONE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    42)
            }
        } else {
            // Permission has already been granted
            callPhone()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 42) {
            // If request is cancelled, the result arrays are empty.
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                // permission was granted, yay!
                callPhone()
            } else {
                // permission denied, boo! Disable the
                // functionality
            }
            return
        }
    }


    @SuppressLint("MissingPermission")
    fun callPhone(){
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone))
        startActivity(intent)
    }

    fun getBitmapFromView(): Bitmap {
        val view = rlmain
        //imgmap.setVisibility(View.VISIBLE);


        //Define a bitmap with the same size as the view
        val returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888)
        //Bind a canvas to it
        val canvas = Canvas(returnedBitmap)
        //Get the view's background
        val bgDrawable = view.getBackground()
        if (bgDrawable != null)
        //has background drawable, then draw it on the canvas
            bgDrawable!!.draw(canvas)
        else
        //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE)
        // draw the view on the canvas
        view.draw(canvas)
        //return the bitmap

        //imgmap.setVisibility(GONE);
        return returnedBitmap
    }
    fun share(){
        try {
            val icon = getBitmapFromView()
            var file = File(this.getExternalCacheDir(),"tmp.png");
            var fOut = FileOutputStream(file);
            icon.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true, false);
            var share = Intent(android.content.Intent.ACTION_SEND);
            share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            share.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(ctx,
                BuildConfig.APPLICATION_ID + ".shareprovider", file));
            share.putExtra(Intent.EXTRA_TEXT, "Prospect Collections")
            share.setType("image/png");
            startActivity(Intent.createChooser(share, "Share image via"));
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}
