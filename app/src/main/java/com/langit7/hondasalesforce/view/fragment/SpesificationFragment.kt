package com.langit7.hondasalesforce.view.fragment

import android.content.Context
import android.net.Uri
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

import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.fitxImageView
import com.langit7.hondasalesforce.model.*
import com.langit7.hondasalesforce.view.activity.ProductDetailActivity


class SpesificationFragment :  Fragment(), BaseFragmentInterface {

    override var title: String=""

    lateinit var act: ProductDetailActivity;

    lateinit var lsspec:List<spesification>
    lateinit var lsspeccat:List<specification_category>
    lateinit var lsspeccontent : List<specification_content>

    lateinit var pro: product

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_spesification, container, false)

        act=activity as ProductDetailActivity

        val llc=rootView.findViewById<LinearLayout>(R.id.llfeaturecontainer)
        val img=rootView.findViewById<fitxImageView>(R.id.imgfeature)

        if(pro.variantProduct.size>0)
            Glide.with(act).load(pro.variantProduct.get(0).image).into(img)
        else
            Glide.with(act).load(pro.imageFeature).into(img)


        llc.removeAllViews()
        for (a in lsspec){
//            for (b in lsspeccat){
//                for (c in lsspeccontent) {

                    var v = layoutInflater.inflate(R.layout.item_spesifikasi, null)
                    var exlayout = v.findViewById<ExpandableRelativeLayout>(R.id.exlayout)
                    var rlheader = v.findViewById<RelativeLayout>(R.id.rlheader)
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

                    exlayout.collapse()
                    ttitle.setText(a.title)

                    llspeccontainer.removeAllViews()
                    for(sc in a.specification_category) {
                        var scv= inflater.inflate(R.layout.item_spec,null)
                        var tdesc = scv.findViewById<TextView>(R.id.tdesc)
                        var tdesc1 = scv.findViewById<TextView>(R.id.tdesc1)
                        tdesc.setText(sc.title)
                        var desc1=""
                        for(scc in sc.specification_content) {
                            desc1=desc1+scc.body + "\n"
                        }
                        tdesc1.setText(desc1)
                        llspeccontainer.addView(scv)
                    }
                    llc.addView(v)
//                }
//            }
        }
        return rootView
    }

    companion object {
        fun Instantiate(tit: String, ls: List<spesification>, pr: product): SpesificationFragment {
            val sd = SpesificationFragment()
            sd.title = tit
            sd.lsspec = ls
//            sd.lsspeccontent = lscon
//            sd.lsspeccat = lscat
            sd.pro = pr
            return sd
        }
//        , lscon: List<specification_content>, lscat: List<specification_category>,
    }


}
