package com.langit7.hondasalesforce.presenter.adapter

import android.content.Context
import android.graphics.Color
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
import com.langit7.hondasalesforce.model.quizhistorydetail
import com.langit7.hondasalesforce.presenter.viewInterface.ItemClickListener

class QuizHistoryDetailListAdapter(val items: List<quizhistorydetail>, val context: Context) : RecyclerView.Adapter<QuizHistoryDetailViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizHistoryDetailViewHolder {
        return QuizHistoryDetailViewHolder(LayoutInflater.from(context).inflate(R.layout.item_quiz_history_detail, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: QuizHistoryDetailViewHolder, position: Int) {
        val art=items.get(position)


        if(art.isTrue.equals("1")) {
            holder.title?.text = "Benar"
            holder.title.setTextColor(Color.GREEN)
        }else{
            holder.title?.text = "Salah"
            holder.title.setTextColor(Color.RED)
        }

        holder.desc?.text=art.question.body

        if(art.answer!=null)
        holder.answer?.text= art.answer.body



    }


}
class QuizHistoryDetailViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val main=view.findViewById<RelativeLayout>(R.id.main)
    val title=view.findViewById<TextView>(R.id.ttitle)
    val desc=view.findViewById<TextView>(R.id.tdesc)
    val answer=view.findViewById<TextView>(R.id.tanswer)
}
