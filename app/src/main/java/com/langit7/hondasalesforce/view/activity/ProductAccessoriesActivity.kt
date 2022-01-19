package com.langit7.hondasalesforce.view.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.github.aakira.expandablelayout.ExpandableRelativeLayout
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.product
import com.langit7.hondasalesforce.model.productaccessories
import com.langit7.hondasalesforce.presenter.logic.ProductPresenter
import com.langit7.hondasalesforce.presenter.viewInterface.DataListInterface
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_product_accessories.*

class ProductAccessoriesActivity : BaseActivity() {


    var lsAcc=ArrayList<productaccessories>()

    lateinit var presenter:ProductPresenter

    lateinit var mProduct:product
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_accessories)


        tactionbartitle.setText(getString(R.string.productknowledge))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tactionbartitle.setTextColor(resources.getColor(R.color.white,null))
        }else{
            tactionbartitle.setTextColor(resources.getColor(R.color.white))
        }
        imgback.setImageResource(R.drawable.arrow_back_white)
        imgback.setOnClickListener{
            onBackPressed()
        }



        mProduct=intent.getSerializableExtra("pro") as product
        if(mProduct==null)
            finish()


        ttitle.setText(mProduct.title)

        if(mProduct.variantProduct.size>0)
            Glide.with(ctx).load(mProduct.variantProduct.get(0).image).into(imgproduct)
        else
            Glide.with(ctx).load(mProduct.imageFeature).into(imgproduct)








        presenter= ProductPresenter(this,APIServices)

        showLoadingDialog()
        presenter.getProductAccessories(mProduct.id,object :DataListInterface<productaccessories>{
            override fun onGetDataSuccess(res: List<productaccessories>) {
                dismisLoadingDialog()
                lsAcc.clear()
                lsAcc.addAll(res)
                init()
            }

            override fun onGetDataFailed(msg: String) {
                dismisLoadingDialog()
            }

        })


    }

    fun init(){

        llacccontainer.removeAllViews()
        for(a in lsAcc){

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
            tprice.setText(a.price)

            if(a.image.length>0)
            Glide.with(ctx).load(a.image).into(img)

            llacccontainer.addView(v)
        }
    }
}
