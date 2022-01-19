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
import com.langit7.hondasalesforce.model.salestalk
import com.langit7.hondasalesforce.presenter.adapter.ProductGridAdapter
import com.langit7.hondasalesforce.presenter.viewInterface.ItemClickListener
import com.langit7.hondasalesforce.view.activity.*


class ProductSalesTalkFragment : Fragment(), BaseFragmentInterface {

    override var title: String=""

    lateinit var act: ProductDetailActivity;

    var lsSalestalk=ArrayList<salestalk>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_product_salestalk, container, false)

        act=activity as ProductDetailActivity

        val tt=rootView.findViewById<TextView>(R.id.ttitle)
        val td=rootView.findViewById<TextView>(R.id.tdesc)
        val ttl= rootView.findViewById<TextView>(R.id.title)

        if(lsSalestalk != null && lsSalestalk.size>0){
            tt.setText(lsSalestalk.get(0).title)
            td.setText(Html.fromHtml(lsSalestalk.get(0).body))
        } else {
            ttl.setText("Mohon maaf Sales Talk untuk tipe ini masih on progress")
        }



        return rootView
    }


    companion object {
        fun Instantiate(tit:String,ls:List<salestalk>): ProductSalesTalkFragment {
            val sd = ProductSalesTalkFragment()
            sd.title=tit
            sd.lsSalestalk.clear()
            sd.lsSalestalk.addAll(ls)
            return sd
        }
    }

}

