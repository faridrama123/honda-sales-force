package com.langit7.hondasalesforce.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.content.Intent
import android.widget.*
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.apparel
import com.langit7.hondasalesforce.presenter.adapter.ApparelGridAdapter
import com.langit7.hondasalesforce.presenter.viewInterface.ItemClickListener
import com.langit7.hondasalesforce.view.activity.*


class ApparelFragment : Fragment(), BaseFragmentInterface {

    override var title: String=""

    lateinit var act: ApparelActivity;
    lateinit var lsData:List<apparel>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_apparel, container, false)

        act=activity as ApparelActivity

        val rv=rootView.findViewById<GridView>(R.id.gridview)

        var adp= ApparelGridAdapter(act,
            lsData,
            object : ItemClickListener<apparel> {
                override fun onItemClick(res: apparel) {
                    var ii=Intent(act, ApparelDetailActivity::class.java)
                    ii.putExtra("apr",res)
                    startActivity(ii)
                }

            }
        )
        // Access the RecyclerView Adapter and load the data into it
        rv.adapter = adp;

        return rootView
    }


    companion object {
        fun Instantiate(tit:String,lsartc:List<apparel>): ApparelFragment {
            val sd = ApparelFragment()
            sd.title=tit
            sd.lsData=lsartc
            return sd
        }
    }

}

