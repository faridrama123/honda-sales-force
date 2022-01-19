package com.langit7.hondasalesforce.presenter.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.Util.function
import com.langit7.hondasalesforce.Util.function.fromHtml
import com.langit7.hondasalesforce.model.*
import com.langit7.hondasalesforce.presenter.viewInterface.ItemClickListener

class LeaderboardHistoryListAdapter(val items: List<leaderboardhistory>, val context: Context) : RecyclerView.Adapter<LeaderboardHistoryViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardHistoryViewHolder {
        return LeaderboardHistoryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_leaderboard, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: LeaderboardHistoryViewHolder, position: Int) {
        val q=items.get(position)
        holder.tname?.text=q.created_date
        holder.tpoint?.text=q.total_point
        holder.tposition?.text=q.desc_medal_type
        holder.tno?.visibility=View.GONE

        holder.img.setImageResource(function.getMedalImageLarge(q.medal_type))

    }


}
class LeaderboardHistoryViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tno=view.findViewById<TextView>(R.id.tno)
    val tname=view.findViewById<TextView>(R.id.tname)
    val tposition=view.findViewById<TextView>(R.id.tposition)
    val tpoint=view.findViewById<TextView>(R.id.tpoint)
    val img=view.findViewById<ImageView>(R.id.imglevel)
}
