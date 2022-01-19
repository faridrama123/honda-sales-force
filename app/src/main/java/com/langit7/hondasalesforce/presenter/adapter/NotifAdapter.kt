package com.langit7.hondasalesforce.presenter.adapter


import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.daimajia.swipe.SwipeLayout
import com.daimajia.swipe.adapters.BaseSwipeAdapter
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.notif


import java.util.ArrayList


class NotifAdapter(
    internal var context: Context,
    internal var lsData: List<notif>,
    internal var listener: SwipeActionListener
)//super(context, resource, items);
    : BaseSwipeAdapter() {
    internal var swipeLayout: MutableList<SwipeLayout> = ArrayList()





    override fun getSwipeLayoutResourceId(position: Int): Int {
        return R.id.swipe
    }

    override fun generateView(position: Int, parent: ViewGroup): View {
        val v = LayoutInflater.from(context).inflate(R.layout.item_notif, null)
        swipeLayout.add(v.findViewById(getSwipeLayoutResourceId(position)) as SwipeLayout)

        try {
            swipeLayout[position].close(false)
        } catch (e: Exception) {
        }



        return v
    }

    override fun fillValues(position: Int, convertView: View) {
        var v: View? = convertView

        val swp = v as SwipeLayout?
        if (v == null) {
            val vi: LayoutInflater
            vi = LayoutInflater.from(context)
            v = vi.inflate(R.layout.item_notif, null)
        }

        val p = lsData[position]

        if (p != null) {

            var tt=convertView.findViewById<TextView>(R.id.ttitle)
            var tb=convertView.findViewById<TextView>(R.id.tbody)
            var tti=convertView.findViewById<TextView>(R.id.ttime)
            var rl=convertView.findViewById<RelativeLayout>(R.id.rlmain)

            if(p.isRead.equals("1")) {
                rl.setBackgroundResource(R.color.white)
                tt.setText(Html.fromHtml(p.title))
                tb.setText(Html.fromHtml(p.body))
            }else {
                rl.setBackgroundResource(R.color.grayb)
                tt.setText(Html.fromHtml("<b>" + p.title + "</b>"))
                tb.setText(Html.fromHtml("<b>" + p.body + "</b>"))
            }

            tb.setText(p.body)
            tti.setText(p.createdDate)

        }


        convertView.findViewById<ImageView>(R.id.trash).setOnClickListener(View.OnClickListener {
            listener.onDelete(position)

            try {
                swipeLayout[position].close(true)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })




    }

    override fun getCount(): Int {
        return lsData.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    interface SwipeActionListener {
        fun onDelete(position: Int)
    }




}