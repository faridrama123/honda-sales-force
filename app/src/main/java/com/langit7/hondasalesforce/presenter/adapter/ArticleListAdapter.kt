package com.langit7.hondasalesforce.presenter.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.function.fromHtml
import com.langit7.hondasalesforce.model.article
import com.langit7.hondasalesforce.presenter.viewInterface.ItemClickListener

class ArticleListAdapter(val items: List<article>, val context: Context, val lst: ItemClickListener<article>) : RecyclerView.Adapter<ArticleViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(LayoutInflater.from(context).inflate(R.layout.item_article, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val art=items.get(position)
        holder.title?.text=art.title
        holder.desc?.text=fromHtml(art.body)
        holder.date?.text=art.postDate

        if(art.is_read.equals("1",true))
            holder.main.setBackgroundResource(R.color.white)
        else
            holder.main.setBackgroundResource(R.color.graylight)


        Glide.with(context).load(art.image).into(holder.img)
        holder.main.setOnClickListener(View.OnClickListener {
            lst.onItemClick(items.get(position))
        })



    }


}
class ArticleViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val main=view.findViewById<RelativeLayout>(R.id.main)
    val img = view.findViewById<ImageView>(R.id.img)
    val title=view.findViewById<TextView>(R.id.ttitle)
    val date=view.findViewById<TextView>(R.id.tdate)
    val desc=view.findViewById<TextView>(R.id.tdesc)
}
