package com.langit7.hondasalesforce.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.content.Intent
import android.widget.*
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.product
import com.langit7.hondasalesforce.presenter.adapter.ProductGridAdapter
import com.langit7.hondasalesforce.presenter.viewInterface.ItemClickListener
import com.langit7.hondasalesforce.view.activity.*


class ProductFragment : Fragment(), BaseFragmentInterface {

    override var title: String=""

    lateinit var act: ProductActivity;
     var lsData=ArrayList<product>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_apparel, container, false)

        act=activity as ProductActivity

        val rv=rootView.findViewById<GridView>(R.id.gridview)

        var adp= ProductGridAdapter(act,
            lsData,
            object : ItemClickListener<product> {
                override fun onItemClick(res: product) {
                    var ii=Intent(act, ProductDetailActivity::class.java)
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
        fun Instantiate(tit:String,lsdata:List<product>): ProductFragment {
            val sd = ProductFragment()
            sd.title=tit
            sd.lsData.clear()
            sd.lsData.addAll(lsdata)
            return sd
        }
    }

}

