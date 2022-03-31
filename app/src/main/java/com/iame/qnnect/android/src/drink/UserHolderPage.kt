package com.iame.qnnect.android.src.drink

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.diary.DiaryActivity
import com.iame.qnnect.android.src.drink.model.CafeUser
import com.iame.qnnect.android.src.group.model.CafeQuestion
import com.iame.qnnect.android.src.group.model.group_question_item
import com.iame.qnnect.android.src.reply.ReplyAdapter
import com.iame.qnnect.android.util.recipe
import kotlinx.android.synthetic.main.item_main_image.view.*
import java.lang.Exception


class UserHolderPage internal constructor(itemView: View, var context: Context, var a_itemClickListener: DrinkUserAdapter.OnItemClickEventListener) : RecyclerView.ViewHolder(itemView) {
    private val user_img :ImageView
    private val user_name: TextView
    private val img_main: ConstraintLayout

    var data: CafeUser? = null
    fun onBind(data: CafeUser, select_index: Int) {
        this.data = data

        try{
            Glide.with(context)
                .load(data.profileImage)
                .transform(CenterCrop(), RoundedCorners(200))
                .into(user_img)
        }catch (e: Exception){
            Glide.with(context)
                .load(R.mipmap.img_profile_dafault_foreground)
                .transform(CenterCrop(), RoundedCorners(200))
                .into(user_img)
        }

        user_name.text = data.nickName

        itemView.setOnClickListener(View.OnClickListener { a_view ->
            val position = adapterPosition
            if(select_index == position){
                img_main.setBackgroundResource(R.drawable.drink_user_select_custom)
                img_main.clipToOutline = true
            }
            else{
                img_main.setBackgroundResource(R.drawable.drink_user_custom)
            }
            if (position != RecyclerView.NO_POSITION) {
                a_itemClickListener.onItemClick(a_view, position)
            }
        })
    }


    init {
        user_img = itemView.findViewById(R.id.user_img)
        user_name = itemView.findViewById(R.id.user_name)
        img_main = itemView.findViewById(R.id.img_main)
    }
}
