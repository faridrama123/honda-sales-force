package com.langit7.hondasalesforce.presenter.adapter.teamreport

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.model.teamreport.partisipant

class AdapterPartisipanKuis(private val isScore: Int, val items: List<partisipant>, val context: Context) : RecyclerView.Adapter<PartisipanKuisViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartisipanKuisViewHolder {
        return PartisipanKuisViewHolder(LayoutInflater.from(context).inflate(R.layout.item_partisipan_kuis, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PartisipanKuisViewHolder, position: Int) {
        val art= items[position]
        holder.no.text = (position + 1).toString()
        holder.nama.text = art.user_id.firstName + " " + art.user_id.lastName
        holder.jabatan.text = art.user_id.profileUser.position
        holder.score.text = art.score.toString()
        if(isScore == 1){
            holder.score.visibility = View.VISIBLE
        }else{
            holder.score.visibility = View.GONE
        }
    }


}
class PartisipanKuisViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val main = view.findViewById<RelativeLayout>(R.id.main)
    val no = view.findViewById<TextView>(R.id.tvNo)
    val nama = view.findViewById<TextView>(R.id.tvNama)
    val jabatan = view.findViewById<TextView>(R.id.tvJabatan)
    val score = view.findViewById<TextView>(R.id.tvScore)

}
