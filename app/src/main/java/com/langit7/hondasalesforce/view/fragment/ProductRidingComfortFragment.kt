package com.langit7.hondasalesforce.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.*
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.product
import com.langit7.hondasalesforce.presenter.adapter.ProductGridAdapter
import com.langit7.hondasalesforce.presenter.viewInterface.ItemClickListener
import com.langit7.hondasalesforce.view.activity.*


class ProductRidingComfortFragment : Fragment(), BaseFragmentInterface {

    override var title: String=""

    lateinit var act: ProductDetailActivity;


    lateinit var pro:product

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_product_ridingcomfort, container, false)

        act=activity as ProductDetailActivity


        val td=rootView.findViewById<TextView>(R.id.tdesc)

        if(pro.ridingComfort!=null)
        td.setText(Html.fromHtml(pro.ridingComfort))

        return rootView
    }


    companion object {
        fun Instantiate(tit:String,pr:product): ProductRidingComfortFragment {
            val sd = ProductRidingComfortFragment()
            sd.title=tit
            sd.pro=pr
            return sd
        }
    }

}

