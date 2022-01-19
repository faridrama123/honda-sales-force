package com.langit7.hondasalesforce.presenter.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
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
import com.langit7.hondasalesforce.Util.function.dateToString
import com.langit7.hondasalesforce.Util.function.fromHtml
import com.langit7.hondasalesforce.Util.function.parseDate
import com.langit7.hondasalesforce.model.article
import com.langit7.hondasalesforce.model.cdb
import com.langit7.hondasalesforce.presenter.viewInterface.CDBClickListener

class CDBListAdapter(val items: List<cdb>, val context: Context, val lst: CDBClickListener) : RecyclerView.Adapter<CDBViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CDBViewHolder {
        return CDBViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cdb, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: CDBViewHolder, position: Int) {
        val art=items.get(position)
        holder.tname?.text=art.nama
        holder.taddress?.text= dateToString(art.datefollowup,"dd-MMM-yyyy HH:mm") +"\n"+art.alamat
        holder.temail?.text=art.email
        holder.tproducttype?.text=art.producttype
        holder.tphone?.text=art.telp

        if(art.status.equals("0",true)) {
            holder.tstatus.setText("COLD")
            holder.tstatus.setTextColor(context.resources.getColor(R.color.blue))
            holder.taddress?.text= art.alamat
        }else if(art.status.equals("1",true)){
            holder.tstatus.setText("HOT")
            holder.tstatus.setTextColor(context.resources.getColor(R.color.red))
        }else{
            holder.tstatus.setText("WARM")
            holder.tstatus.setTextColor(context.resources.getColor(R.color.yellowcalm))
        }

        holder.tcall.setOnClickListener {
            lst.onCallClick(items.get(position))
        }

        holder.main.setOnClickListener(View.OnClickListener {
            lst.onItemClick(items.get(position))
        })



    }



}
class CDBViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val main=view.findViewById<RelativeLayout>(R.id.main)
    val tname=view.findViewById<TextView>(R.id.tname)
    val taddress=view.findViewById<TextView>(R.id.taddress)
    val temail=view.findViewById<TextView>(R.id.temail)
    val tproducttype=view.findViewById<TextView>(R.id.tproducttype)
    val tstatus=view.findViewById<TextView>(R.id.tstatus)
    val tphone=view.findViewById<TextView>(R.id.tphone)
    val tcall=view.findViewById<TextView>(R.id.tcall)
}
