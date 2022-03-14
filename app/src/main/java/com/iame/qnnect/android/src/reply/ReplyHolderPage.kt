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


class ReplyHolderPage internal constructor(itemView: View, var context: Context) : RecyclerView.ViewHolder(itemView) {
    private val writer_img :ImageView
    private val writer_name: TextView
    private val answer_text: TextView
    private val date_txt: TextView
    private val more_btn: ImageView

    var data: Replies? = null
    fun onBind(data: Replies) {
        this.data = data

        var profile = data.writer

        if(profile.profileImage == null){
            writer_img.setImageResource(R.mipmap.img_profile_dafault_foreground)
        }
        else{
            Glide.with(context)
                .load(profile.profileImage)
                .transform(CenterCrop(), RoundedCorners(200))
                .into(writer_img)
        }
        writer_name.setText(profile.nickName)
        answer_text.setText(data.content)
        date_txt.text = data.createdAt
    }

    init {
        writer_img = itemView.findViewById(R.id.writer_img)
        writer_name = itemView.findViewById(R.id.writer_name)
        answer_text = itemView.findViewById(R.id.answer_text)
        date_txt = itemView.findViewById(R.id.date_txt)
        more_btn = itemView.findViewById(R.id.more_btn)
    }
}
