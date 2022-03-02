package com.iame.qnnect.android.src.group.member

import android.content.Context
import android.media.Image
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.src.main.home.model.group_item
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.HomeFragment_case
import com.iame.qnnect.android.src.group.GroupFragment
import com.iame.qnnect.android.src.group.model.group_drink_item
import com.iame.qnnect.android.src.main.MainActivity


class GroupMemberAdapter(
    private val itemList: ArrayList<group_drink_item>
) :
    RecyclerView.Adapter<GroupMemberAdapter.ViewHolder>(){
    var datas = ArrayList<group_drink_item>()
    private var activity: MainActivity? = null


    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        var member_drink: ImageView = itemView.findViewById(R.id.member_drink)
        var member_name: TextView = itemView.findViewById(R.id.member_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.group_member_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        Glide.with(holder.itemView.getContext())
//            .load(itemList.get(position).drink_img)
//            .transform(CenterCrop())
//            .into(holder.member_drink)
        holder.member_drink.setImageResource(itemList.get(position).drink_img)
        holder.member_name.setText(itemList.get(position).member_name)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}