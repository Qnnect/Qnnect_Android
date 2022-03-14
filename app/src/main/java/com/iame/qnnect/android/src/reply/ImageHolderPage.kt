package com.iame.qnnect.android.src.reply

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
import com.iame.qnnect.android.src.answer.AnswerActivity
import com.iame.qnnect.android.src.diary.DiaryActivity
import com.iame.qnnect.android.src.diary.model.Comments
import com.iame.qnnect.android.src.group.model.CafeQuestion
import com.iame.qnnect.android.src.group.model.group_question_item
import com.iame.qnnect.android.src.reply.ReplyActivity
import com.iame.qnnect.android.src.reply.model.Replies
import kotlinx.android.synthetic.main.item_main_image.view.*


class ImageHolderPage internal constructor(itemView: View, var context: Context) : RecyclerView.ViewHolder(itemView) {
    private val image :ImageView
    private val delete_btn: ImageView

    var data: String? = null
    fun onBind(data: String) {
        this.data = data

        if(data != "null"){
            try{
                Glide.with(context)
                    .load(data)
                    .transform(CenterCrop(), RoundedCorners(50))
                    .into(image)
            }catch (e: Exception){
                itemView.visibility = View.GONE
            }

        }
        delete_btn.visibility = View.GONE
    }

    init {
        image = itemView.findViewById(R.id.gallery_img)
        delete_btn = itemView.findViewById(R.id.delete_btn)
    }
}
