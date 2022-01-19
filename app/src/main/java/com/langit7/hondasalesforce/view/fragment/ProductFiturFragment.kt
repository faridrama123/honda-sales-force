package com.langit7.hondasalesforce.view.fragment

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.text.Html
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
import com.langit7.hondasalesforce.Util.fitxImageView
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.product
import com.langit7.hondasalesforce.model.productfeature
import com.langit7.hondasalesforce.view.activity.ProductDetailActivity


class ProductFiturFragment : Fragment(), BaseFragmentInterface {

    override var title: String = ""

    lateinit var act: ProductDetailActivity

    lateinit var lsprofit: List<productfeature>

    var pro: product?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_product_fitur, container, false)

        if(pro==null)
            activity?.finish()

        act = activity as ProductDetailActivity

        val llc = rootView.findViewById<LinearLayout>(R.id.llfeaturecontainer)
        val img = rootView.findViewById<fitxImageView>(R.id.imgfeature)

        Glide.with(act).load(pro?.imageFeature).into(img)

        img.setOnClickListener {
            val mDialogView = LayoutInflater.from(act).inflate(R.layout.dialog_fitur_img, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(act)
                .setView(mDialogView)
            val imgf = mDialogView.findViewById<PhotoView>(R.id.imageView)
            Glide.with(act).load(pro?.imageFeature).into(imgf)
            val mAlertDialog=mBuilder.show()
            val tclose=mDialogView.findViewById<TextView>(R.id.tclose)
            tclose.setOnClickListener{
                mAlertDialog.dismiss()}
        }

        try {
            llc.removeAllViews()
            for (f in lsprofit) {
                var v = inflater.inflate(R.layout.item_feature, null)

                var exlayout = v.findViewById<ExpandableRelativeLayout>(R.id.exlayout)
                var rlheader = v.findViewById<LinearLayout>(R.id.rlheader)
                var imgheader = v.findViewById<TextView>(R.id.imgheader)
                var ttitle = v.findViewById<TextView>(R.id.ttitle)
                var tbody = v.findViewById<TextView>(R.id.tdesc)
                var imgf = v.findViewById<ImageView>(R.id.img)

                rlheader.setOnClickListener {
                    exlayout.toggle()
                    if (exlayout.isExpanded) {
                        imgheader.setText("+")
                    } else {
                        imgheader.setText("-")
                    }
                }
    //
                if (f.color != null && f.color.length > 0 ) {
                    rlheader.setBackgroundColor(Color.parseColor(f.color))
                    if (f.labelcolor !=null && f.labelcolor.length > 0){
                        ttitle.setTextColor(Color.parseColor(f.labelcolor.trim()))
                        imgheader.setTextColor(Color.parseColor(f.labelcolor.trim()))
                        rlheader.setOnClickListener {
                            exlayout.toggle()
                            if (exlayout.isExpanded) {
                                imgheader.setText("+")
                                imgheader.setTextColor(Color.parseColor(f.labelcolor))
                            } else {
                                imgheader.setText("-")
                                imgheader.setTextColor(Color.parseColor(f.labelcolor))
                            }
                        }
                    } else {
                        ttitle.setTextColor(Color.parseColor("#000000"))
                        imgheader.setTextColor(Color.parseColor("#000000"))
                    }

                }

                exlayout.collapse()
                ttitle.setText(f.title)
                tbody.setText(Html.fromHtml(f.body))

                if (f.text_align.isNullOrEmpty() || f.text_align.equals("2"))
                    tbody.textAlignment = View.TEXT_ALIGNMENT_CENTER
                else
                    tbody.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                var lp=LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, function.dp2px(act,300))
                lp.setMargins(0,0,0, function.dp2px(act,10))
                if(f.image_align.isNullOrEmpty() || f.image_align.equals("2"))
                    lp.gravity= Gravity.CENTER
                else
                    lp.gravity= Gravity.LEFT
                imgf.layoutParams=lp


                if (f.image.length > 0)
                    Glide.with(act).load(f.image).into(imgf)



                exlayout.isExpanded = false
                llc.addView(v)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return rootView
    }


    companion object {
        fun Instantiate(tit: String, ls: List<productfeature>, pr: product): ProductFiturFragment {
            val sd = ProductFiturFragment()
            sd.title = tit
            sd.lsprofit = ls
            sd.pro = pr
            return sd
        }
    }

}

