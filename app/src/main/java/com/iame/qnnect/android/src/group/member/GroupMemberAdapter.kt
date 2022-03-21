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
import com.iame.qnnect.android.util.drink_imgName
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_group.*
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
        if(itemList.get(position).cafeDrinkCommonResponse.userDrinkName == null){
            holder.member_drink.setImageResource(R.mipmap.drink_none_foreground)
//            Glide.with(holder.itemView.getContext())
//                .load(itemList.get(position).drink_img)
//                .transform(CenterCrop())
//                .into(holder.member_drink)
        }
        else{
            if(itemList.get(position).cafeDrinkCommonResponse.currentDrinkIngredientsFilled.size < 2){
                holder.member_drink.setImageResource(R.mipmap.complete_drink_default_foreground)
            }
            else{
                var userDrink = itemList.get(position).cafeDrinkCommonResponse.userDrinkName
                var list = itemList.get(position).cafeDrinkCommonResponse.currentDrinkIngredientsFilled
                var last = itemList.get(position).cafeDrinkCommonResponse.currentDrinkIngredientsFilled.size-1
                var img = drink_imgName(userDrink, list.get(last).ingredientName)

                holder.member_drink.setImageResource(img)
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