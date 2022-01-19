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
import com.langit7.hondasalesforce.presenter.viewInterface.ItemClickListener

class QuizListAdapter(val items: List<quiz>, val context: Context, val lst: ItemClickListener<quiz>) : RecyclerView.Adapter<QuizViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        return QuizViewHolder(LayoutInflater.from(context).inflate(R.layout.item_quiz, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val q=items.get(position)
        holder.title?.text=q.title
        holder.date?.text=q.startDate +" - " +q.endDate
        holder.tid?.text="Kuis "+q.id



        holder.main.setOnClickListener(View.OnClickListener {
            lst.onItemClick(items.get(position))
        })



    }


}
class QuizViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val main=view.findViewById<LinearLayout>(R.id.llmain)
    val title=view.findViewById<TextView>(R.id.ttitle)
    val date=view.findViewById<TextView>(R.id.tdate)
    val tid=view.findViewById<TextView>(R.id.tid)
}
