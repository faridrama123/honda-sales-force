package com.langit7.hondasalesforce.view.fragment

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.text.Html
import android.text.Layout
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.aakira.expandablelayout.ExpandableLinearLayout
import com.github.aakira.expandablelayout.ExpandableRelativeLayout
import com.github.chrisbanes.photoview.PhotoView
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.fitxImageView
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.grooming
import com.langit7.hondasalesforce.model.product
import com.langit7.hondasalesforce.model.productfeature
import com.langit7.hondasalesforce.view.activity.GeneralKnowledgeActivity
import com.langit7.hondasalesforce.view.activity.ProductDetailActivity


class GeneralKnowledgeGroomingFragment : Fragment(), BaseFragmentInterface {

    override var title: String = ""

    lateinit var act: GeneralKnowledgeActivity

    lateinit var lsprofit: List<grooming>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_product_fitur, container, false)

        act = activity as GeneralKnowledgeActivity

        val llc = rootView.findViewById<LinearLayout>(R.id.llfeaturecontainer)
        val img = rootView.findViewById<fitxImageView>(R.id.imgfeature)

        if(lsprofit.size>0) {

            img.visibility-View.GONE

            llc.removeAllViews()
            for (f in lsprofit) {
                var v = inflater.inflate(R.layout.item_expand_linear, null)

                var exlayout = v.findViewById<ExpandableLinearLayout>(R.id.exlayout)
                var rlheader = v.findViewById<LinearLayout>(R.id.rlheader)
                var imgheader = v.findViewById<ImageView>(R.id.imgheader)
                var ttitle = v.findViewById<TextView>(R.id.ttitle)

                var llspeccontainer = v.findViewById<LinearLayout>(R.id.llspeccontainer)


                rlheader.setOnClickListener {
//                    exlayout.initLayout()
//                    exlayout.toggle()
                    if (exlayout.isExpanded) {
                        imgheader.setImageResource(R.drawable.icon_menu_plus)
                        exlayout.collapse(0,LinearInterpolator())
                    }else{
                        imgheader.setImageResource(R.drawable.icon_menu_minus)
                        exlayout.initLayout()
                        exlayout.expand(10,LinearInterpolator())
                    }
                }
                exlayout.collapse()
                ttitle.setText(f.title)


                llspeccontainer.removeAllViews()


//                addText(f.body,f.text_align,llspeccontainer)
//                addImage(f.image,f.image_align,llspeccontainer)

                if(f.groomingSubCategory!=null && f.groomingSubCategory.size>0){
                    for(f1 in f.groomingSubCategory){
                        var v1 = inflater.inflate(R.layout.item_expand_linear, null)

                        var exlayout1 = v1.findViewById<ExpandableLinearLayout>(R.id.exlayout)
                        var rlheader1 = v1.findViewById<LinearLayout>(R.id.rlheader)
                        var imgheader1 = v1.findViewById<ImageView>(R.id.imgheader)
                        var ttitle1 = v1.findViewById<TextView>(R.id.ttitle)

                        var llspeccontainer1 = v1.findViewById<LinearLayout>(R.id.llspeccontainer)


                        rlheader1.setOnClickListener {
                            if (exlayout1.isExpanded) {
                                imgheader1.setImageResource(R.drawable.icon_menu_plus)
                                exlayout1.collapse()
                            }else {
                                imgheader1.setImageResource(R.drawable.icon_menu_minus)
                                exlayout1.initLayout()
                                exlayout1.expand()
                            }
                            var p=exlayout1.parent.parent.parent as ExpandableLinearLayout
                            p.collapse(0,LinearInterpolator())
                            p.initLayout()
                            p.expand(10,LinearInterpolator())

                        }
                        exlayout1.collapse()
                        ttitle1.setText(f1.title)

//                        addText(f1.body,f.text_align,llspeccontainer1)
//                        addImage(f.image,f.image_align,llspeccontainer1)

                        if(f1.groomingSubCategoryGrooming!=null && f1.groomingSubCategoryGrooming.size>0){
                            for(f2 in f1.groomingSubCategoryGrooming){
                                var v2 = inflater.inflate(R.layout.item_expand_linear, null)

                                var exlayout2 = v2.findViewById<ExpandableLinearLayout>(R.id.exlayout)
                                var rlheader2 = v2.findViewById<LinearLayout>(R.id.rlheader)
                                var imgheader2 = v2.findViewById<ImageView>(R.id.imgheader)
                                var ttitle2 = v2.findViewById<TextView>(R.id.ttitle)

                                var llspeccontainer2 = v2.findViewById<LinearLayout>(R.id.llspeccontainer)


                                rlheader2.setOnClickListener {
                                    if (exlayout2.isExpanded) {
                                        imgheader2.setImageResource(R.drawable.icon_menu_plus)
                                        exlayout2.collapse()
                                    }else {
                                        imgheader2.setImageResource(R.drawable.icon_menu_minus)
                                        exlayout2.initLayout()
                                        exlayout2.expand()
                                    }

                                    var p1=exlayout2.parent.parent.parent as ExpandableLinearLayout
                                    p1.collapse(0,LinearInterpolator())
                                    p1.initLayout()
                                    p1.expand(10,LinearInterpolator())

                                    var p=p1.parent.parent.parent as ExpandableLinearLayout
                                    p.collapse(0,LinearInterpolator())
                                    p.initLayout()
                                    p.expand(10,LinearInterpolator())

                                }
                                exlayout2.collapse()
                                ttitle2.setText(f2.title)
                                addText(f2.body,f2.textAlign,llspeccontainer2)
                                addImage(f2.image,f2.imageAlign,llspeccontainer2)


                                if(f2.groomingDetail!=null && f2.groomingDetail.size>0){
                                    for(f3 in f2.groomingDetail){
                                        var v3 = inflater.inflate(R.layout.item_expand_linear, null)

                                        var exlayout3 = v3.findViewById<ExpandableLinearLayout>(R.id.exlayout)
                                        var rlheader3 = v3.findViewById<LinearLayout>(R.id.rlheader)
                                        var imgheader3 = v3.findViewById<ImageView>(R.id.imgheader)
                                        var ttitle3 = v3.findViewById<TextView>(R.id.ttitle)

                                        var llspeccontainer3 = v3.findViewById<LinearLayout>(R.id.llspeccontainer)


                                        rlheader3.setOnClickListener {
                                            if (exlayout3.isExpanded) {
                                                imgheader3.setImageResource(R.drawable.icon_menu_plus)
                                                exlayout3.collapse()
                                            }else {
                                                imgheader3.setImageResource(R.drawable.icon_menu_minus)
                                                exlayout3.initLayout()
                                                exlayout3.expand()
                                            }

                                            var p2=exlayout3.parent.parent.parent as ExpandableLinearLayout
                                            p2.collapse(0,LinearInterpolator())
                                            p2.initLayout()
                                            p2.expand(10,LinearInterpolator())

                                            var p1=p2.parent.parent.parent as ExpandableLinearLayout
                                            p1.collapse(0,LinearInterpolator())
                                            p1.initLayout()
                                            p1.expand(10,LinearInterpolator())

                                            var p=p1.parent.parent.parent as ExpandableLinearLayout
                                            p.collapse(0,LinearInterpolator())
                                            p.initLayout()
                                            p.expand(10,LinearInterpolator())

                                        }
                                        exlayout3.collapse()
                                        ttitle3.setText(Html.fromHtml(f3.body))
//                                        addText(f3.body,f3.textAlign,llspeccontainer3)
                                        addImage(f3.image,f3.imageAlign,llspeccontainer3)

                                        exlayout3.isExpanded = false
                                        llspeccontainer2.addView(v3)
                                    }
                                }

                                exlayout2.isExpanded = false
                                llspeccontainer1.addView(v2)
                            }
                        }

                        exlayout1.isExpanded = false
                        llspeccontainer.addView(v1)
                    }
                }

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


    fun addText(txt:String,alg:String?,ll:LinearLayout){

        if (txt.isNotEmpty()) {

            txt.replace("\\r\\n\\t","")

            var tv = TextView(context)
            tv.setText(Html.fromHtml(txt))
            if (alg.isNullOrEmpty() || alg.equals("2"))
                tv.textAlignment = View.TEXT_ALIGNMENT_CENTER
            else
                tv.textAlignment = View.TEXT_ALIGNMENT_TEXT_START

            var lp=LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT)
            lp.setMargins(0,0,0,function.dp2px(act,10))
            tv.layoutParams=lp

            ll.addView(tv)
        }

    }
    fun addImage(url:String?,alg:String?,ll:LinearLayout){

        if(!url.isNullOrEmpty()) {
            var imgf=ImageView(act)
            imgf.setBackgroundResource(R.color.graylight)
            var lp=LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,function.dp2px(act,300))
            lp.setMargins(0,0,0,function.dp2px(act,10))
            if(alg.isNullOrEmpty() || alg.equals("2"))
                lp.gravity=Gravity.CENTER
            else
                lp.gravity=Gravity.LEFT

            imgf.layoutParams=lp
            imgf.scaleType=ImageView.ScaleType.CENTER_INSIDE
            Glide.with(act).load(url).apply(RequestOptions().optionalFitCenter()).into(imgf)

            imgf.setOnClickListener {
                val mDialogView = LayoutInflater.from(act).inflate(R.layout.dialog_fitur_img, null)
                //AlertDialogBuilder
                val mBuilder = AlertDialog.Builder(act)
                    .setView(mDialogView)
                val imgf1 = mDialogView.findViewById<PhotoView>(R.id.imageView)
                if (lsprofit.size > 0)
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
        fun Instantiate(tit: String, ls: List<grooming>): GeneralKnowledgeGroomingFragment {
            val sd = GeneralKnowledgeGroomingFragment()
            sd.title = tit
            sd.lsprofit = ls
            return sd
        }
    }

}

