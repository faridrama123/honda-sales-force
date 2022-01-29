package com.langit7.hondasalesforce.view.activity.nos

import NosAudit
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.langit7.hondasalesforce.R
import com.langit7.hondasalesforce.databinding.ItemNosBinding

class AdapterListNosAudit : RecyclerView.Adapter<AdapterListNosAudit.ViewHolder>() {

    private val mData = ArrayList<NosAudit>()

    fun setData(items: List<NosAudit>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    fun getData() :ArrayList<NosAudit> {
        return mData
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemNosBinding.bind(itemView)
        fun bind(items: NosAudit, position: Int) {
            with(itemView){
                binding.title    .text = items.title
                binding.subtitle    .text = items.subTitle
                binding.indicatorText    .text = items.data

                binding.question.setOnClickListener {




                    if ( binding.indicator.visibility == View.VISIBLE   ){


                        binding.indicator.visibility = View.GONE
                         binding.sub1.visibility  = View.VISIBLE
                         binding.sub2.visibility  = View.VISIBLE

                         binding.sub3.visibility  = View.VISIBLE
                         binding.sub4.visibility  = View.VISIBLE

                     }else{

                         binding.indicator.visibility = View.VISIBLE

                         binding.sub1.visibility  = View.GONE
                         binding.sub2.visibility  = View.GONE

                         binding.sub3.visibility  = View.GONE
                         binding.sub4.visibility  = View.GONE
                     }
                }

                binding.check1.setOnClickListener {

                    //mengubah data sesuai jawaban yg dipilih
                    mData.set(position, NosAudit(items.title,items.subTitle,items.data, 1))

                    binding.check2.isChecked = false;
                    binding.check3.isChecked = false;
                    binding.check4.isChecked = false;
                }
                binding.check2.setOnClickListener {

                    //mengubah data sesuai jawaban yg dipilih
                    mData.set(position, NosAudit(items.title,items.subTitle,items.data, 2))
                    binding.check1.isChecked = false;
                    binding.check3.isChecked = false;
                    binding.check4.isChecked = false;
                }

                binding.check3.setOnClickListener {
                    //mengubah data sesuai jawaban yg dipilih
                    mData.set(position, NosAudit(items.title,items.subTitle,items.data, 3))
                    binding.check1.isChecked = false;
                    binding.check2.isChecked = false;
                    binding.check4.isChecked = false;
                }

                binding.check4.setOnClickListener {
                    //mengubah data sesuai jawaban yg dipilih
                    mData.set(position, NosAudit(items.title,items.subTitle,items.data, 4))
                    binding.check1.isChecked = false;
                    binding.check2.isChecked = false;
                    binding.check3.isChecked = false;
                }


            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_nos, parent, false)
        return ViewHolder(mView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mData[position],position)
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}