package com.langit7.hondasalesforce.presenter.adapter.teamreport

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.databinding.ItemFrequenceUserBinding
import com.langit7.hondasalesforce.databinding.ItemPartisipanKuisBinding
import com.langit7.hondasalesforce.model.teamreport.FrequentUser

//class AdapterFrequentUsr {
//}

class AdapterFrequentUsr : RecyclerView.Adapter<AdapterFrequentUsr.ViewHolder>() {
    private val mData = ArrayList<FrequentUser>()

    fun clearData() {
        mData.clear()
        notifyDataSetChanged()
    }

    fun setData(items: ArrayList<FrequentUser>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemFrequenceUserBinding.bind(itemView)
        fun bind(items: FrequentUser, position: Int) {
            with(itemView){

                var lastlogin = items.userId?.profileUser?.lastLoginMobile?.subSequence(0, 10)
                binding.tvNo.text = ( 1 + position).toString()
                binding.tvNama.text = items.userId?.firstName + " " +items.userId?.lastName;
                binding.tvScore.text = lastlogin
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
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_frequence_user, parent, false)
        return ViewHolder(mView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mData[position], position)
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}