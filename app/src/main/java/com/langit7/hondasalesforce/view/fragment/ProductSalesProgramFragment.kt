package com.langit7.hondasalesforce.view.fragment

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.github.aakira.expandablelayout.ExpandableRelativeLayout
import com.github.chrisbanes.photoview.PhotoView
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.fitxImageView
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.product
import com.langit7.hondasalesforce.model.productaccessories
import com.langit7.hondasalesforce.model.salesprogram
import com.langit7.hondasalesforce.view.activity.ProductActivity
import com.langit7.hondasalesforce.view.activity.ProductDetailActivity
import kotlinx.android.synthetic.main.activity_product_accessories.*


class ProductSalesProgramFragment : Fragment(), BaseFragmentInterface {
    override var title: String = "Sales Program(National)"

    lateinit var act: ProductActivity


     var lsSP = ArrayList<salesprogram>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_product_sales_program, container, false)

        act=activity as ProductActivity

        val llcontainer=rootView.findViewById<LinearLayout>(R.id.llacccontainer)
        val ttl=rootView.findViewById<TextView>(R.id.ttitle)




        if (lsSP != null && lsSP.size>0){
            llcontainer.removeAllViews()
            for (a in lsSP) {

//                var v= layoutInflater.inflate(R.layout.item_acc,null)
//                var exlayout=v.findViewById<ExpandableRelativeLayout>(R.id.exlayout)
//                var rlheader=v.findViewById<RelativeLayout>(R.id.rlheader)
//                var imgheader=v.findViewById<ImageView>(R.id.imgheader)
//                var ttitle=v.findViewById<TextView>(R.id.ttitle)
//                var tprice=v.findViewById<TextView>(R.id.tprice)
//                var img=v.findViewById<ImageView>(R.id.img)
//
//                rlheader.setOnClickListener {
//                    exlayout.toggle()
//                    if(exlayout.isExpanded)
//                        imgheader.setImageResource(R.drawable.icon_menu_plus)
//                    else
//                        imgheader.setImageResource(R.drawable.icon_menu_minus)
//                }
//
//                exlayout.collapse()
//                ttitle.setText(a.title)
//                tprice.visibility=View.GONE
//
//                if(a.image.length>0)
//                    Glide.with(act).load(a.image).into(img)
//
//                img.setOnClickListener {
//                    val mDialogView = LayoutInflater.from(act).inflate(R.layout.dialog_fitur_img, null)
//                    //AlertDialogBuilder
//                    val mBuilder = AlertDialog.Builder(act)
//                        .setView(mDialogView)
//                    val imgf = mDialogView.findViewById<PhotoView>(R.id.imageView)
//                        Glide.with(act).load(a.image).into(imgf)
//                    mBuilder.show()
//                }

                if(a.image.length>0) {
                    var v = fitxImageView(act)

                    var lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    lp.bottomMargin = function.dp2px(act, 10)
                    v.layoutParams = lp

                    Glide.with(act).load(a.image).into(v)

                    v.setOnClickListener {
                        val mDialogView = LayoutInflater.from(act).inflate(R.layout.dialog_fitur_img, null)
                        //AlertDialogBuilder
                        val mBuilder = AlertDialog.Builder(act)
                            .setView(mDialogView)
                        val imgf = mDialogView.findViewById<PhotoView>(R.id.imageView)
                        Glide.with(act).load(a.image).into(imgf)
                        val mAlertDialog=mBuilder.show()
                        val tclose=mDialogView.findViewById<TextView>(R.id.tclose)
                        tclose.setOnClickListener{
                            mAlertDialog.dismiss()}
                    }

                    llcontainer.addView(v)
                }
            }
        } else {
            ttl.setText("Mohon maaf belum ada Sales Program untuk saat ini")
        }


        return rootView
    }


    companion object {
        fun Instantiate(tit: String, ls: ArrayList<salesprogram>): ProductSalesProgramFragment {
            val sd = ProductSalesProgramFragment()
            sd.title = tit
            sd.lsSP = ls
            return sd
        }

    }
}
