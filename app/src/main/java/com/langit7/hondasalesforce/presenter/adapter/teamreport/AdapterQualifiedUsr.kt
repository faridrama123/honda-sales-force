package com.langit7.hondasalesforce.presenter.adapter.teamreport

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.langit7.hondasalesforce.R

import com.langit7.hondasalesforce.databinding.ItemQualifiedBinding
import com.langit7.hondasalesforce.model.teamreport.PartisipantQuiz

//class AdapterFrequentUsr {
//}

class AdapterQualifiedUsr : RecyclerView.Adapter<AdapterQualifiedUsr.ViewHolder>() {

    private val mData = ArrayList<PartisipantQuiz>()

    fun clearData() {
        mData.clear()
        notifyDataSetChanged()
    }

    fun setData(items: ArrayList<PartisipantQuiz>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemQualifiedBinding.bind(itemView)
        fun bind(items: PartisipantQuiz, position: Int) {
            with(itemView){


                binding.tvNo.text = ( 1 + position).toString()
                binding.tvNama.text = items.userId?.firstName + " " +items.userId?.lastName;
                binding.tvJabatan.text = items.userId?.profileUser?.position




            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_qualified, parent, false)
        return ViewHolder(mView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mData[position], position)
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}