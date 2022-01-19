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
import com.langit7.hondasalesforce.Util.function.fromHtml
import com.langit7.hondasalesforce.model.article
import com.langit7.hondasalesforce.model.quiz
import com.langit7.hondasalesforce.model.quizresponse
import com.langit7.hondasalesforce.presenter.viewInterface.ItemClickListener

class QuisHistoryListAdapter(val items: List<quizresponse>, val context: Context) : RecyclerView.Adapter<HistoryViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_history_quis, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val q=items.get(position)
        holder.title?.text=q.title
        holder.score?.text=q.score
        holder.no?.text=(position+1).toString()




    }


}
class HistoryViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val title=view.findViewById<TextView>(R.id.ttitle)
    val no=view.findViewById<TextView>(R.id.tno)
    val score=view.findViewById<TextView>(R.id.tscore)
}
