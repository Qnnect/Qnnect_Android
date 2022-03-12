package com.iame.qnnect.android.src.group.member

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.iame.qnnect.android.MainSearchRecyclerViewAdapter
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.group.model.CafeUser
import com.iame.qnnect.android.src.group.model.group_drink_item
import com.iame.qnnect.android.src.main.MainActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.group_member_item.view.*
import kotlinx.android.synthetic.main.item_main_image.view.*


class GroupMemberAdapter() : RecyclerView.Adapter<GroupMemberAdapter.ViewHolder>(){
    var datas = ArrayList<group_drink_item>()
    private var activity: MainActivity? = null

    private val itemList = ArrayList<CafeUser>()

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
        if(itemList.get(position).userDrinkSelected == null){
            holder.member_drink.setImageResource(R.mipmap.img_drink_default_foreground)
//            Glide.with(holder.itemView.getContext())
//                .load(itemList.get(position).drink_img)
//                .transform(CenterCrop())
//                .into(holder.member_drink)
        }
        else{
            if(itemList.get(position).drinkIngredientsFilledResponseList.size == 0){
                holder.member_drink.setImageResource(R.mipmap.img_drink_basic_foreground)
            }
        }

        holder.member_name.setText(itemList.get(position).user.nickName)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(item: CafeUser) {
        itemList.add(item)
    }

    fun clear() {
        itemList.clear()
        this.notifyDataSetChanged()
    }
}