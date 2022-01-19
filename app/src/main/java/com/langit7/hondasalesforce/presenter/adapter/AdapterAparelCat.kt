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
import com.langit7.hondasalesforce.model.apparelcategory
import com.langit7.hondasalesforce.presenter.viewInterface.ItemClickListener

class AdapterAparelCat(
    internal var ctx: Context, internal var lsApparel: List<apparelcategory>,
    internal var listener: ItemClickListener<apparelcategory>
) : ArrayAdapter<apparelcategory>(
    ctx,
    R.layout.item_apparel, lsApparel
) {


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
            t.setText(p.title)


            val img = v.findViewById(R.id.img) as ImageView
            Glide.with(ctx).load(p.image).into(img)


            img.setOnClickListener { listener.onItemClick(p) }



            return v


        }

        return ImageView(ctx)
    }
}
