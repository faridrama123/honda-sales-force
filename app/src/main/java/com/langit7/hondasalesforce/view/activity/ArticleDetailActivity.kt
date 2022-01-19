package com.langit7.hondasalesforce.view.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.bumptech.glide.Glide
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.DialogUtil
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.article
import com.langit7.hondasalesforce.model.history
import com.langit7.hondasalesforce.presenter.logic.MainPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_article_detail.*

class ArticleDetailActivity : BaseActivity() {


    lateinit var art:article

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)




        art=intent.getSerializableExtra("art") as article

        if(art==null) {
            Toast(getString(R.string.noarticle))
            finish()
        }

        tactionbartitle.setText(art.descCategory)
        imgback.setOnClickListener({
            onBackPressed()
        })


        Glide.with(ctx).load(art.image).into(imgart)
        ttitle.setText(art.title)
        tdate.setText(art.postDate)


        var wv= findViewById<WebView>(R.id.webview)
        wv.getSettings().setJavaScriptEnabled(true); // enable javascript
        wv.getSettings().defaultFontSize=14//dp2px(context!!,5)
        wv.setWebViewClient(MyWebViewClient())
        //wv.loadUrl(URL)
        wv.setBackgroundColor(Color.TRANSPARENT)
        //wv.setInitialScale(resources.getInteger(R.integer.zoomlevel));
        //wv.getSettings().setUseWideViewPort(true);
        wv.loadDataWithBaseURL("", art.body, "text/html", "UTF-8", "");


        var haspoin=false
        var i=0
        var th=Thread{
            while (actvisible && !haspoin){

                Thread.sleep(1000)
                i++

                if(i>=30){
                    runOnUiThread {


                        if(!function.getPreverenceBool(ctx, function.getUser(ctx)!!.id+function.getToday() + art.id)) {
                            function.savePreverence(ctx, function.getUser(ctx)!!.id+function.getToday() + art.id, true)

                            var pp = MainPresenter(ctx as BaseActivity, APIServices)
                            pp.savePoint("1", art.id, object : ObjectResponseInterface<history> {
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

                            pp.saveCounter("1", art.id, object : ObjectResponseInterface<history> {
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

    private inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
//            Log.e("URL",url)

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
            return true
        }
    }
}
