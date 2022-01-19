package com.langit7.hondasalesforce.view.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.aakira.expandablelayout.ExpandableLinearLayout
import com.github.aakira.expandablelayout.ExpandableRelativeLayout
import com.github.chrisbanes.photoview.PhotoView
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.fitxImageView
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.salesprogram
import com.langit7.hondasalesforce.view.activity.ProductActivity


class ImageFragment : Fragment(), BaseFragmentInterface {

    override var title: String = ""
    var imgurl: MutableList<salesprogram> = mutableListOf()

    lateinit var act: ProductActivity


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_image, container, false)
        act = activity as ProductActivity
        val llc = rootView.findViewById<LinearLayout>(R.id.img_container)

        llc.removeAllViews()

        imgurl.forEach {
//            var img=PhotoView(context)
//            img.layoutParams=LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
//            Glide.with(act).load(it.image).into(img)
//            img.setOnClickListener{view->
//                val mDialogView = LayoutInflater.from(act).inflate(R.layout.dialog_fitur_img, null)
//                //AlertDialogBuilder
//                val mBuilder = AlertDialog.Builder(act)
//                    .setView(mDialogView)
//                val imgf = mDialogView.findViewById<PhotoView>(R.id.imageView)
//                Glide.with(act).load(it.image).into(imgf)
//                val mAlertDialog=mBuilder.show()
//                val tclose=mDialogView.findViewById<TextView>(R.id.tclose)
//                tclose.setOnClickListener{
//                    mAlertDialog.dismiss()}
//            }
//            llc.addView(img)


            if(it.child!=null && it.child.size>0){
                val v = inflater.inflate(R.layout.item_expand_linear, null)
                val exlayout1 = v.findViewById<ExpandableLinearLayout>(R.id.exlayout)
                val rlheader1 = v.findViewById<LinearLayout>(R.id.rlheader)
                val imgheader1 = v.findViewById<ImageView>(R.id.imgheader)
                val ttitle1 = v.findViewById<TextView>(R.id.ttitle)
                val llspeccontainer1 = v.findViewById<LinearLayout>(R.id.llspeccontainer)

                rlheader1.setOnClickListener {
//                    exlayout1.toggle()
                    if (exlayout1.isExpanded) {
                        imgheader1.setImageResource(R.drawable.icon_menu_plus)
                        exlayout1.collapse(0,LinearInterpolator())
                    }else{
                        imgheader1.setImageResource(R.drawable.icon_menu_minus)
                        exlayout1.initLayout()
                        exlayout1.expand(10,LinearInterpolator())
                    }
                }

                exlayout1.collapse()
                ttitle1.text = it.title

                llspeccontainer1.removeAllViews()


                it.child.forEach {det->
                    val v2 = inflater.inflate(R.layout.item_expand_linear, null)
                    val exlayout2 = v2.findViewById<ExpandableLinearLayout>(R.id.exlayout)
                    val rlheader2 = v2.findViewById<LinearLayout>(R.id.rlheader)
                    val imgheader2 = v2.findViewById<ImageView>(R.id.imgheader)
                    val ttitle2 = v2.findViewById<TextView>(R.id.ttitle)

                    ttitle2.setText(det.title)

                    val llspeccontainer2 = v2.findViewById<LinearLayout>(R.id.llspeccontainer)

                    rlheader2.setOnClickListener {
                        if (exlayout2.isExpanded) {
                            imgheader2.setImageResource(R.drawable.icon_menu_plus)
                            exlayout2.collapse()
                        }else {
                            imgheader2.setImageResource(R.drawable.icon_menu_minus)
                            exlayout2.initLayout()
                            exlayout2.expand()
                        }

                        var p2=exlayout2.parent.parent.parent as ExpandableLinearLayout
                        p2.collapse(0, LinearInterpolator())
                        p2.initLayout()
                        p2.expand(10, LinearInterpolator())

                    }
                    exlayout2.collapse()

                    addImage(det.image,null,llspeccontainer2)
                    exlayout2.isExpanded = false
                    llspeccontainer1.addView(v2)
                }



                exlayout1.isExpanded = false
                llc.addView(v)
            }
        }


        return rootView
    }
    fun addImage(url:String?,alg:String?,ll:LinearLayout){

        if(!url.isNullOrEmpty()) {
            val imgf=ImageView(act)
            imgf.setBackgroundResource(R.color.graylight)
            val lp=LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,function.dp2px(act,300))
            lp.setMargins(0,0,0,function.dp2px(act,10))
            if(alg.isNullOrEmpty() || alg.equals("2"))
                lp.gravity= Gravity.CENTER
            else
                lp.gravity= Gravity.LEFT

            imgf.layoutParams=lp
            imgf.scaleType=ImageView.ScaleType.CENTER_INSIDE
            Glide.with(act).load(url).apply(RequestOptions().optionalFitCenter()).into(imgf)

            imgf.setOnClickListener {
                val mDialogView = LayoutInflater.from(act).inflate(R.layout.dialog_fitur_img, null)
                val mBuilder = AlertDialog.Builder(act)
                    .setView(mDialogView)
                val imgf1 = mDialogView.findViewById<PhotoView>(R.id.imageView)
                    Glide.with(act).load(url).into(imgf1)
                val mAlertDialog = mBuilder.show()

                val tclose=mDialogView.findViewById<TextView>(R.id.tclose)
                tclose.setOnClickListener{
                    mAlertDialog.dismiss()
                }

            }
            ll.addView(imgf)
        }

    }

    companion object {
        fun Instantiate(tit: String, img: MutableList<salesprogram>): ImageFragment {
            val sd = ImageFragment()
            sd.title = tit
            sd.imgurl = img
            return sd
        }
    }

}

