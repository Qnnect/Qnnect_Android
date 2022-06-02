package com.iame.qnnect.android.src.group

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.src.main.home.model.group_item


class SubGroupAdapter(
    private val itemList: ArrayList<group_item>
) :
    RecyclerView.Adapter<SubGroupAdapter.ViewHolder>(){
    var datas = ArrayList<group_item>()

//    var group_name: String,
//    var date: String,
//    var people_count: String

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        var group_name: TextView = itemView.findViewById(com.iame.qnnect.android.R.id.group_name)
        var date: TextView = itemView.findViewById(com.iame.qnnect.android.R.id.date_txt)
        var people_count: TextView = itemView.findViewById(com.iame.qnnect.android.R.id.count_txt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(com.iame.qnnect.android.R.layout.home_group_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        Glide.with(holder.itemView.getContext())
//            .load(itemList.get(position).img)
//            .transform(CenterCrop(), RoundedCorners(50))
//            .into(holder.challenge_img)
        holder.group_name.setText(itemList.get(position).group_name)
        holder.date.setText(itemList.get(position).date)
        holder.people_count.setText(itemList.get(position).people_count)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}