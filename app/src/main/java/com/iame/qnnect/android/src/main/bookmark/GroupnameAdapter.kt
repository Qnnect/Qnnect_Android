package com.iame.qnnect.android.src.main.bookmark

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.main.bookmark.model.Cafe
import com.iame.qnnect.android.src.main.bookmark.model.bookmark_group
import com.iame.qnnect.android.src.main.home.home_model.HomeCafes
import com.iame.qnnect.android.src.main.home.model.group_item


class GroupnameAdapter() :
    RecyclerView.Adapter<GroupnameAdapter.ViewHolder>(){

    private val itemList = ArrayList<Cafe>()

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        var group_name: TextView = itemView.findViewById(R.id.group_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.bookmark_group_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.group_name.setText(itemList.get(position).cafeTitle)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(item: Cafe) {
        itemList.add(item)
    }

    fun clear() {
        itemList.clear()
    }
}