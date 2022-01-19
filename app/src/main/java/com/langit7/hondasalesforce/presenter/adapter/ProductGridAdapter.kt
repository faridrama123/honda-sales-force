package com.langit7.hondasalesforce.presenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.apparel
import com.langit7.hondasalesforce.model.product
import com.langit7.hondasalesforce.presenter.viewInterface.ItemClickListener

 class ProductGridAdapter(internal var ctx: Context, internal var lsData:List<product>, internal var listener:ItemClickListener<product>) : ArrayAdapter<product>(ctx,R.layout.item_apparel,lsData){





    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {


        var v: View? = null

        if (v == null) {
            val vi: LayoutInflater
            vi = LayoutInflater.from(ctx)
            v = vi.inflate(R.layout.item_apparel, null)
        }

        val p = getItem(position)

        if (p != null) {

            val t = v!!.findViewById(R.id.tname) as TextView
            val tnew = v!!.findViewById(R.id.tnew) as TextView
            t.setText(p.title)


            val img = v.findViewById(R.id.img) as ImageView
            if(p.variantProduct.size>0)
                Glide.with(ctx).load(p.variantProduct.get(0).image).into(img)
            else
                Glide.with(ctx).load(p.imageFeature).into(img)

            img.setOnClickListener { listener.onItemClick(p) }

            var isnew=p.is_new.equals("1",true)

            if(isnew)
                tnew.visibility=View.VISIBLE
            else
                tnew.visibility=View.GONE


            return v


        }

        return ImageView(ctx)
    }



}