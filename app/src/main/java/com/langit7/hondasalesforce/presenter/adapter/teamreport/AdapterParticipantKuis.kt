package com.langit7.hondasalesforce.presenter.adapter.teamreport

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.databinding.ItemPartisipantQuizBinding

import com.langit7.hondasalesforce.model.teamreport.PartisipantQuiz

//class AdapterFrequentUsr {
//}

class AdapterParticipantKuis : RecyclerView.Adapter<AdapterParticipantKuis.ViewHolder>() {

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

        private val binding = ItemPartisipantQuizBinding.bind(itemView)
        fun bind(items: PartisipantQuiz, position: Int) {
            with(itemView){

                binding.tvNo.text = ( 1 + position).toString()
                binding.tvNama.text = items.userId?.firstName + " " +items.userId?.lastName;
                binding.tvJabatan.text = items.userId?.profileUser?.position

//                Glide.with(itemView.context)
//                    .load(items.avatarUrl.toString()).centerCrop()
//                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_error))
//                    .into(binding.avatar)

//                itemView.setOnClickListener {
//                    val intent = Intent(itemView.context, DetailActivity::class.java)
//                    intent.putExtra(EXTRA,items)
//                    itemView.context.startActivity(intent)
//                }


            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_partisipant_quiz, parent, false)
        return ViewHolder(mView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mData[position], position)
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}