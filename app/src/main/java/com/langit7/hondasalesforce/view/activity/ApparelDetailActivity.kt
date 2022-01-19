package com.langit7.hondasalesforce.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.DialogUtil
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.apparel
import com.langit7.hondasalesforce.model.history
import com.langit7.hondasalesforce.presenter.logic.MainPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_apparel_detail.*

class ApparelDetailActivity : BaseActivity() {

    lateinit var apr: apparel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apparel_detail)

        apr= intent.getSerializableExtra("apr") as apparel

        if(apr==null)
            finish()

        tactionbartitle.setText(getString(R.string.apparel))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tactionbartitle.setTextColor(resources.getColor(R.color.white,null))
        }else{
            tactionbartitle.setTextColor(resources.getColor(R.color.white))
        }
        imgback.setImageResource(R.drawable.arrow_back_white)
        imgback.setOnClickListener({
            onBackPressed()
        })



        ttitle.setText(apr.title)

        if(apr.image.length>0)
            Glide.with(ctx).load(apr.image).into(img)

        tharga.setText(function.formatRupiah(apr.price.toString()))
        tukuran.setText(apr.size)
        if(apr.apparelDescription.size>0)
        tmaterial.setText(apr.apparelDescription.get(0).body)


        var warna=""

        if(apr.apparel_detail.size>0){
            for(d in apr.apparel_detail){
                if(!d.color.isNullOrEmpty())
                warna=warna +d.color + "\n"
            }
        }

        if(warna.isEmpty())
            warna="-"

        twarna.setText(warna)


        imgscan.setOnClickListener {
            var ii= Intent(ctx,ScanActivity::class.java)
            startActivityForResult(ii,1)
        }



//        var haspoin=false
//        var i=0
//        var th=Thread{
//            while (actvisible && !haspoin){
//
//                Thread.sleep(1000)
//                i++
//
//                if(i>=40){
//                    runOnUiThread {
//                        var pp= MainPresenter(ctx as BaseActivity,APIServices)
//
//                        var count=function.getPreverence(ctx,"apparelpoint")
//                        if(count.length==0){
//                            count="1"
//                        }
//                        if (count.toInt()<=3)
//                        pp.savePoint("4", function.getToday()+count,object : ObjectResponseInterface<history> {
//                            override fun onSuccess(res: history) {
//                                haspoin=true
//                                function.savePreverence(ctx,"apparelpoint",(count.toInt()+1).toString())
//                                try {
//                                    if (res.total_point.toInt() > 0) {
//                                        DialogUtil.createScoreDialog(ctx as Activity, "+" + res.total_point)
//                                    }
//                                }catch (e:Exception){}
//                            }
//
//                            override fun onFailed(msg: String) {
//                                haspoin=true
//                            }
//
//                        })
//
//                    }
//                }
//            }
//        }
//        th.start()
    }


    override fun onActivityReenter(resultCode: Int, data: Intent?) {
        super.onActivityReenter(resultCode, data)


        if(resultCode==12){
            var ii=Intent()
            ii.putExtra("code",data!!.getStringExtra("code"))
            setResult(12,ii)
            finish()
        }
    }
}
