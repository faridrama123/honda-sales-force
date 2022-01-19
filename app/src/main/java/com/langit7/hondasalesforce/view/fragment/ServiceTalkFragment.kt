package com.langit7.hondasalesforce.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.*
import com.github.aakira.expandablelayout.ExpandableRelativeLayout
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.servicetalk
import com.langit7.hondasalesforce.view.activity.*


class ServiceTalkFragment : Fragment(), BaseFragmentInterface {

    override var title: String=""

    lateinit var act: GeneralKnowledgeActivity;
    lateinit var lsData:List<servicetalk>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_service_talk, container, false)

        act=activity as GeneralKnowledgeActivity

        val llc=rootView.findViewById<LinearLayout>(R.id.llcontainer)

        llc.removeAllViews()
        for(com in lsData){

            if(com.body==null)
                com.body=""

            var v=layoutInflater.inflate(R.layout.item_expand,null)
            var exlayout=v.findViewById<ExpandableRelativeLayout>(R.id.exlayout)
            var rlheader=v.findViewById<LinearLayout>(R.id.rlheader)
            var imgheader=v.findViewById<ImageView>(R.id.imgheader)
            var ttitle=v.findViewById<TextView>(R.id.ttitle)
            var tbody=v.findViewById<TextView>(R.id.tbody)
            var lltalk=v.findViewById<LinearLayout>(R.id.llspeccontainer)


            rlheader.setOnClickListener {
                exlayout.toggle()
                if(exlayout.isExpanded)
                    imgheader.setImageResource(R.drawable.icon_menu_plus)
                else
                    imgheader.setImageResource(R.drawable.icon_menu_minus)
            }

            exlayout.collapse()
            ttitle.setText(com.title)

            if( com.body.length>0)
            tbody.setText(Html.fromHtml(com.body))

            exlayout.isExpanded=false


            lltalk.removeAllViews()
            for(sc in com.service_talk_detail) {

                if(sc.body==null)
                    sc.body=""

                var scv= inflater.inflate(R.layout.item_talk,null)
                var exlayout1 = scv.findViewById<ExpandableRelativeLayout>(R.id.exlayout1)
                var rlheader1 = scv.findViewById<LinearLayout>(R.id.rlheader1)
                var imgheader1 = scv.findViewById<ImageView>(R.id.imgheader1)
                var ttitle1 = scv.findViewById<TextView>(R.id.ttitle1)
                var tbody1 = scv.findViewById<TextView>(R.id.tbody1)

                if(sc.body.trim().length>1) {
                    rlheader1.setOnClickListener {
                        exlayout1.toggle()
                        if (exlayout1.isExpanded)
                            imgheader1.setImageResource(R.drawable.icon_menu_plus)
                        else
                            imgheader1.setImageResource(R.drawable.icon_menu_minus)
                    }

//                exlayout.collapse()
                    imgheader1.visibility=View.VISIBLE
                    tbody.visibility = View.GONE
                    ttitle1.setText(sc.title)
                    tbody1.setText(Html.fromHtml(sc.body))
                    exlayout1.isExpanded = false

                }else{
                    imgheader1.visibility=View.GONE
                    ttitle1.setText(sc.title)
                    tbody1.setText(Html.fromHtml(sc.body))
                    ttitle1.setText(sc.title)
                    exlayout1.visibility=View.GONE
                }
                lltalk.addView(scv)
            }


            llc.addView(v)

        }

        return rootView
    }


    companion object {
        fun Instantiate(tit:String,lsartc:List<servicetalk>): ServiceTalkFragment {
            val sd = ServiceTalkFragment()
            sd.title=tit
            sd.lsData=lsartc
            return sd
        }
    }

}

