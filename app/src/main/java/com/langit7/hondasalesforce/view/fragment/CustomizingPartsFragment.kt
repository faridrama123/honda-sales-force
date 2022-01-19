package com.langit7.hondasalesforce.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import java.util.ArrayList
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.*
import com.bumptech.glide.request.RequestOptions
import com.glide.slider.library.SliderLayout
import com.glide.slider.library.SliderTypes.TextSliderView
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.apparel
import com.langit7.hondasalesforce.model.article
import com.langit7.hondasalesforce.model.cuspart
import com.langit7.hondasalesforce.model.product
import com.langit7.hondasalesforce.presenter.adapter.*
import com.langit7.hondasalesforce.presenter.viewInterface.ItemClickListener
import com.langit7.hondasalesforce.view.activity.*


class CustomizingPartsFragment : Fragment(), BaseFragmentInterface {

    override var title: String=""

    lateinit var act: ProductActivity;
    lateinit var lsData:List<cuspart>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_apparel, container, false)

        act=activity as ProductActivity

        val rv=rootView.findViewById<GridView>(R.id.gridview)

        var adp= CusPartsGridAdapter(act,
            lsData,
            object : ItemClickListener<cuspart> {
                override fun onItemClick(res: cuspart) {
                    var ii=Intent(act, CustomizingPartsActivity::class.java)
                    ii.putExtra("pr",res)
                    startActivity(ii)
                }

            }
        )
        // Access the RecyclerView Adapter and load the data into it
        rv.adapter = adp;

        return rootView
    }


    companion object {
        fun Instantiate(tit:String,lsdata:List<cuspart>): CustomizingPartsFragment {
            val sd = CustomizingPartsFragment()
            sd.title=tit
            sd.lsData=lsdata
            return sd
        }
    }

}

