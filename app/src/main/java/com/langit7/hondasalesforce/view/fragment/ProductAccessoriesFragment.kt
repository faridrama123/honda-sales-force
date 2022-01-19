package com.langit7.hondasalesforce.view.fragment

import android.app.AlertDialog
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
import com.langit7.hondasalesforce.Util.DialogUtil
import com.langit7.hondasalesforce.Util.fitxImageView
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.product
import com.langit7.hondasalesforce.model.productaccessories
import com.langit7.hondasalesforce.view.activity.ProductDetailActivity
import kotlinx.android.synthetic.main.activity_product_accessories.*


class ProductAccessoriesFragment : Fragment(), BaseFragmentInterface {
    override var title: String = "Accessories"

    lateinit var act: ProductDetailActivity

    lateinit var mProduct: product
    lateinit var lsAcc : List<productaccessories>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_product_accessories, container, false)

        act=activity as ProductDetailActivity

        val llcontainer=rootView.findViewById<LinearLayout>(R.id.llacccontainer)
        val img=rootView.findViewById<fitxImageView>(R.id.imgproduct)
        val ttl=rootView.findViewById<TextView>(R.id.ttitle)

        if(mProduct.variantProduct.size>0)
            Glide.with(act).load(mProduct.variantProduct.get(0).image).into(img)
        else
            Glide.with(act).load(mProduct.imageFeature).into(img)


        if (lsAcc != null && lsAcc.size>0){
            llcontainer.removeAllViews()
            for (a in lsAcc) {

                var v= layoutInflater.inflate(R.layout.item_acc,null)
                var exlayout=v.findViewById<ExpandableRelativeLayout>(R.id.exlayout)
                var rlheader=v.findViewById<RelativeLayout>(R.id.rlheader)
                var imgheader=v.findViewById<ImageView>(R.id.imgheader)
                var ttitle=v.findViewById<TextView>(R.id.ttitle)
                var tprice=v.findViewById<TextView>(R.id.tprice)
                var img=v.findViewById<ImageView>(R.id.img)

                rlheader.setOnClickListener {
                    exlayout.toggle()
                    if(exlayout.isExpanded)
                        imgheader.setImageResource(R.drawable.icon_menu_plus)
                    else
                        imgheader.setImageResource(R.drawable.icon_menu_minus)
                }

                exlayout.collapse()
                ttitle.setText(a.title)


                if(!a.desc_price.isNullOrEmpty() && !a.body.isNullOrEmpty()) {
                    tprice.setText(a.desc_price + "\n[Tap disini untuk lihat Detail]")
                }else{
                    tprice.visibility=View.GONE
                }

                tprice.setOnClickListener {
                    DialogUtil.createYesDialog(act,a.body,"OK",true)
                }

                if(a.image.length>0)
                    Glide.with(act).load(a.image).into(img)

                img.setOnClickListener {
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
        } else {
            ttl.setText("Mohon maaf belum ada Accessories untuk tipe ini")
        }


        return rootView
    }


    companion object {
        fun Instantiate(tit: String, ls: List<productaccessories>, pr: product): ProductAccessoriesFragment {
            val sd = ProductAccessoriesFragment()
            sd.title = tit
            sd.lsAcc = ls
            sd.mProduct = pr
            return sd
        }

    }
}
