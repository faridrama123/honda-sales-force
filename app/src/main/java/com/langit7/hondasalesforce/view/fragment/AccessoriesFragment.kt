package com.langit7.hondasalesforce.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.*
import com.github.aakira.expandablelayout.ExpandableRelativeLayout
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.model.product
import com.langit7.hondasalesforce.presenter.adapter.ProductGridAdapter
import com.langit7.hondasalesforce.presenter.viewInterface.ItemClickListener
import com.langit7.hondasalesforce.view.activity.*



class AccessoriesFragment : Fragment(), BaseFragmentInterface {

    override var title: String=""

    lateinit var act: ProductActivity;
    lateinit var lsData:List<product>
    lateinit var lsData_:ArrayList<product>


    var search=""
    lateinit var adp:ProductGridAdapter

    lateinit var llfiltercontainer:LinearLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_accessories, container, false)

        act=activity as ProductActivity

        val rv=rootView.findViewById<GridView>(R.id.gridview)
        val imgfilter=rootView.findViewById<ImageView>(R.id.imgfilter)
        val exlayout=rootView.findViewById<ExpandableRelativeLayout>(R.id.exlayout)
        val tsave=rootView.findViewById<TextView>(R.id.tsave)
        val tclear=rootView.findViewById<TextView>(R.id.tclear)
        val etsearch=rootView.findViewById<EditText>(R.id.etsearch)
        llfiltercontainer=rootView.findViewById<LinearLayout>(R.id.llfiltercontainer)


        adp= ProductGridAdapter(act,
            lsData_,
            object : ItemClickListener<product> {
                override fun onItemClick(res: product) {
                    var ii= Intent(act, ProductAccessoriesActivity::class.java)
                    ii.putExtra("pro",res)
                    startActivity(ii)
                }

            }
        )
        // Access the RecyclerView Adapter and load the data into it
        rv.adapter = adp;


        imgfilter.setOnClickListener{
            exlayout.toggle()
        }

        exlayout.collapse()


        renderchoice(inflater)

        tclear.setOnClickListener{
            for(c in act.lsCat){
                function.savePreverence(act,"filter"+c.name,false)
            }
            renderchoice(inflater)
        }

        tsave.setOnClickListener {
            exlayout.collapse()
            filter()
        }

        etsearch.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                search=p0.toString()
                filter()
            }

        })

        filter()
        return rootView
    }

    fun filter(){
        lsData_.clear()
        for(i in lsData){
            for(c in act.lsCat) {
                c.included = !function.getPreverenceBool(act, "filter" + c.name)

                if(c.included && i.category.id.equals(c.id)){

                    if(search.isEmpty()|| (i.description!=null && i.description.toLowerCase().contains(search.toLowerCase())) || i.title.toLowerCase().contains(search.toLowerCase()) )
                    lsData_.add(i)
                }

            }
        }
        adp.notifyDataSetChanged()
    }

    fun renderchoice(inflater:LayoutInflater){

        llfiltercontainer.removeAllViews()
        for(c in act.lsCat){

            c.included=!function.getPreverenceBool(act,"filter"+c.name)


            var v= inflater.inflate(R.layout.item_acc_filter,null)
            var rl=v.findViewById<RelativeLayout>(R.id.rlmain)
            var tt=v.findViewById<TextView>(R.id.ttitle)
            var img=v.findViewById<ImageView>(R.id.img)

            tt.setText(c.name)

            if(c.included)
                img.setImageResource(R.drawable.banner_reddot)
            else
                img.setImageResource(R.drawable.banner_blankdot)


            rl.setOnClickListener{
                if(c.included) {
                    function.savePreverence(act,"filter"+c.name,true)
                    img.setImageResource(R.drawable.banner_blankdot)
                }else {
                    function.savePreverence(act,"filter"+c.name,false)
                    img.setImageResource(R.drawable.banner_reddot)
                }
                c.included=!c.included
            }

            llfiltercontainer.addView(v)
        }
    }

    companion object {
        fun Instantiate(tit:String,lsartc:List<product>): AccessoriesFragment {
            val sd = AccessoriesFragment()
            sd.title=tit
            sd.lsData=lsartc
            sd.lsData_= ArrayList()
            return sd
        }
    }

}

