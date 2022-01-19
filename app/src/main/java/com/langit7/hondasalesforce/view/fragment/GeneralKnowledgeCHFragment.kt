package com.langit7.hondasalesforce.view.fragment

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.text.Html
import android.text.Layout
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.aakira.expandablelayout.ExpandableRelativeLayout
import com.github.chrisbanes.photoview.PhotoView
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.fitxImageView
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.product
import com.langit7.hondasalesforce.model.productfeature
import com.langit7.hondasalesforce.view.activity.GeneralKnowledgeActivity
import com.langit7.hondasalesforce.view.activity.ProductDetailActivity


class GeneralKnowledgeCHFragment : Fragment(), BaseFragmentInterface {

    override var title: String = ""

    lateinit var act: GeneralKnowledgeActivity

    lateinit var lsprofit: List<productfeature>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_product_fitur, container, false)

        act = activity as GeneralKnowledgeActivity

        val llc = rootView.findViewById<LinearLayout>(R.id.llfeaturecontainer)
        val img = rootView.findViewById<fitxImageView>(R.id.imgfeature)

        if(lsprofit.size>0) {
//            Glide.with(act).load(lsprofit.get(0).image).into(img)
//
//            img.setOnClickListener {
//                val mDialogView = LayoutInflater.from(act).inflate(R.layout.dialog_fitur_img, null)
//                //AlertDialogBuilder
//                val mBuilder = AlertDialog.Builder(act)
//                    .setView(mDialogView)
//                val imgf = mDialogView.findViewById<PhotoView>(R.id.imageView)
//                if (lsprofit.size > 0)
//                    Glide.with(act).load(lsprofit.get(0).image).into(imgf)
//                val mAlertDialog = mBuilder.show()
//            }
            img.visibility=View.GONE

            llc.removeAllViews()
            for (f in lsprofit) {
                addImage(f.image,f.image_align,llc)
                addText(f.body,f.text_align,llc)


                if(f.complaint_handling_detail!=null && f.complaint_handling_detail.size>0){
                    for(f1 in f.complaint_handling_detail){
                        addText(f1.body,f1.text_align,llc)
                        addImage(f1.image,f1.image_align,llc)
                    }
                }
                if(f.customer_service_detail!=null && f.customer_service_detail.size>0){
                    for(f1 in f.customer_service_detail){
                        addText(f1.body,f1.text_align,llc)
                        addImage(f1.image,f1.image_align,llc)
                    }
                }



//                exlayout.isExpanded = false
//                llc.addView(v)
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
                    mAlertDialog.dismiss()}
            }
            ll.addView(imgf)
        }

    }




    companion object {
        fun Instantiate(tit: String, ls: List<productfeature>): GeneralKnowledgeCHFragment {
            val sd = GeneralKnowledgeCHFragment()
            sd.title = tit
            sd.lsprofit = ls
            return sd
        }
    }

}

