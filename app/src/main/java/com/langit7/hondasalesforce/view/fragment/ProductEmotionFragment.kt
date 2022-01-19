package com.langit7.hondasalesforce.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.*
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.product
import com.langit7.hondasalesforce.presenter.adapter.ProductGridAdapter
import com.langit7.hondasalesforce.presenter.viewInterface.ItemClickListener
import com.langit7.hondasalesforce.view.activity.*


class ProductEmotionFragment : Fragment(), BaseFragmentInterface {

    override var title: String=""

    lateinit var act: ProductActivity;



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_product_emotion, container, false)

        act=activity as ProductActivity

        val rv=rootView.findViewById<GridView>(R.id.gridview)



        return rootView
    }


    companion object {
        fun Instantiate(tit:String): ProductEmotionFragment {
            val sd = ProductEmotionFragment()
            sd.title=tit
            return sd
        }
    }

}

