package com.iame.qnnect.android.src.drink

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.drink.model.CafeUser
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.drink_group_item.view.*


class UserHolderPage internal constructor(itemView: View, var context: Context,
                                          var a_itemClickListener: DrinkUserAdapter.OnItemClickEventListener) : RecyclerView.ViewHolder(itemView) {
    private val user_img :ImageView
    private val user_name: TextView
    private val img_main: ConstraintLayout

    var data: CafeUser? = null
    fun onBind(data: CafeUser, drinkUserAdapter: DrinkUserAdapter) {
        this.data = data

        Glide.with(context)
            .load(data.profileImage)
            .transform(CenterCrop(), RoundedCorners(200))
            .apply(RequestOptions().placeholder(R.mipmap.profile_default_foreground)
                .error(R.mipmap.profile_default_foreground))
            .into(user_img)

        user_name.text = data.nickName

        itemView.setOnClickListener(View.OnClickListener { a_view ->
            drinkUserAdapter.select_index = adapterPosition
            drinkUserAdapter.notifyDataSetChanged()
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                a_itemClickListener.onItemClick(a_view, position)
            }
        })
        if(drinkUserAdapter.select_index == position){
            img_main.setBackgroundResource(R.drawable.drink_user_select_custom)
            img_main.clipToOutline = true
            user_name.setTextColor(Color.parseColor("#000000"))
        }
        else{
            img_main.setBackgroundResource(R.drawable.drink_user_custom)
            user_name.setTextColor(Color.parseColor("#828282"))
        }
    }

    init {
        user_img = itemView.findViewById(R.id.user_img)
        user_name = itemView.findViewById(R.id.user_name)
        img_main = itemView.findViewById(R.id.img_main)
    }
}
