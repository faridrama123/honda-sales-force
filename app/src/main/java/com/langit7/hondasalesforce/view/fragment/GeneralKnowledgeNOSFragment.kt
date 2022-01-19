package com.langit7.hondasalesforce.view.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.text.Html
import android.text.Layout
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.github.aakira.expandablelayout.ExpandableRelativeLayout
import com.github.chrisbanes.photoview.PhotoView
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.DialogUtil
import com.langit7.hondasalesforce.Util.fitxImageView
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.history
import com.langit7.hondasalesforce.model.nos
import com.langit7.hondasalesforce.model.product
import com.langit7.hondasalesforce.model.productfeature
import com.langit7.hondasalesforce.presenter.logic.MainPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.ObjectResponseInterface
import com.langit7.hondasalesforce.view.activity.BaseActivity
import com.langit7.hondasalesforce.view.activity.GeneralKnowledgeActivity
import com.langit7.hondasalesforce.view.activity.ProductDetailActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.player.YouTubePlayerView
import com.pierfrancescosoffritti.androidyoutubeplayer.player.listeners.AbstractYouTubePlayerListener


class GeneralKnowledgeNOSFragment : Fragment(), BaseFragmentInterface {

    override var title: String = ""

    lateinit var act: GeneralKnowledgeActivity

    lateinit var lsprofit: List<nos>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_product_fitur, container, false)

        act = activity as GeneralKnowledgeActivity

        val llc = rootView.findViewById<LinearLayout>(R.id.llfeaturecontainer)
        val img = rootView.findViewById<fitxImageView>(R.id.imgfeature)

        if(lsprofit.size>0) {
            img.visibility=View.GONE

            llc.removeAllViews()
            for (f in lsprofit) {
                var v = inflater.inflate(R.layout.item_expand, null)

                var exlayout = v.findViewById<ExpandableRelativeLayout>(R.id.exlayout)
                var rlheader = v.findViewById<LinearLayout>(R.id.rlheader)
                var imgheader = v.findViewById<ImageView>(R.id.imgheader)
                var ttitle = v.findViewById<TextView>(R.id.ttitle)

                var llspeccontainer = v.findViewById<LinearLayout>(R.id.llspeccontainer)





                rlheader.setOnClickListener {
                    exlayout.toggle()
                    if (exlayout.isExpanded)
                        imgheader.setImageResource(R.drawable.icon_menu_plus)
                    else
                        imgheader.setImageResource(R.drawable.icon_menu_minus)
                }
//


                exlayout.collapse()
                ttitle.setText(f.title)


                llspeccontainer.removeAllViews()


                addText(f.body,f.text_align,f.docs,llspeccontainer)
                addVideo(f.video_embed,llspeccontainer)
//                addVideo("6JYIGclVQdw",llspeccontainer)



                exlayout.isExpanded = false
                llc.addView(v)
            }
        }else{
            var tnodata= TextView(act)
            tnodata.setText("Belum ada data")
            var lp=LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            tnodata.layoutParams=lp
            tnodata.gravity=Gravity.CENTER

            llc.addView(tnodata)

        }

        return rootView
    }


    fun addText(txt:String,alg:String?,url:String?,ll:LinearLayout){

        if (txt.isNotEmpty()) {

            txt.replace("\\r\\n\\t","")

            var tv = TextView(context)
            tv.setText(Html.fromHtml("<u>"+txt+"</u>"))
            tv.setTextColor(resources.getColor(R.color.blue))
            if (alg.isNullOrEmpty() || alg.equals("2"))
                tv.textAlignment = View.TEXT_ALIGNMENT_CENTER
            else
                tv.textAlignment = View.TEXT_ALIGNMENT_TEXT_START

            var lp=LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            lp.setMargins(0,0,0,function.dp2px(act,10))
            tv.layoutParams=lp

            if(!url.isNullOrEmpty()&&url.length>5)
            tv.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(browserIntent)

            }


            ll.addView(tv)
        }

    }
    fun addVideo(url:String?,ll:LinearLayout){

        if(!url.isNullOrEmpty()) {
            var yt=YouTubePlayerView(act)
            var lp=LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            lp.setMargins(0,0,0,function.dp2px(act,10))


            yt.layoutParams=lp



            (context as BaseActivity).lifecycle.addObserver(yt)
            yt.initialize({ initializedYouTubePlayer ->
                initializedYouTubePlayer.addListener(object : AbstractYouTubePlayerListener() {
                    override fun onStateChange(state: PlayerConstants.PlayerState) {
                        super.onStateChange(state)

                    }

                    override fun onReady() {
                        yt.visibility=View.VISIBLE
                        var videoId = "6JYIGclVQdw"
//                    videoId = "https://www.youtube.com/watch?v=LEf27xuYcw4"

                        videoId = url
                            .replace("https://www.youtube.com/embed/", "")
                            .replace("http://www.youtube.com/embed/","")
                            .replace("?feature=player_detailpage","")
//                        Log.e("Videoid",videoId)
                        initializedYouTubePlayer.cueVideo(videoId, 0f)

                    }
                })
            }, true)



            ll.addView(yt)
        }

    }




    companion object {
        fun Instantiate(tit: String, ls: List<nos>): GeneralKnowledgeNOSFragment {
            val sd = GeneralKnowledgeNOSFragment()
            sd.title = tit
            sd.lsprofit = ls
            return sd
        }
    }

}

