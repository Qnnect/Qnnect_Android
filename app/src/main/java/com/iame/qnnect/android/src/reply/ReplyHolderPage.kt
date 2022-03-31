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
import com.iame.qnnect.android.src.reply.reply_more.DeleteReplyDialog
import com.iame.qnnect.android.src.reply.reply_more.ReplyMoreBottomSheet
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.item_main_image.view.*
import java.lang.Exception


class ReplyHolderPage internal constructor(itemView: View, var context: Context,
                                           var a_itemClickListener: ReplyAdapter.OnItemClickEventListener,
                                           var d_itemClickListener: ReplyAdapter.OnItemClickEventListener) : RecyclerView.ViewHolder(itemView) {
    private val writer_img :ImageView
    private val writer_name: TextView
    private val answer_text: TextView
    private val date_txt: TextView
    private val more_btn: ImageView

    var data: Replies? = null
    fun onBind(data: Replies) {
        this.data = data

        if(data.writer){
            more_btn.visibility = View.VISIBLE
        }

        var profile = data.writerInfo

        if(profile.profileImage == null){
            writer_img.setImageResource(R.mipmap.img_profile_dafault_foreground)
        }
        else{
            try{
                Glide.with(context)
                    .load(profile.profileImage)
                    .transform(CenterCrop(), RoundedCorners(200))
                    .into(writer_img)
            }catch (e: Exception){
                Glide.with(context)
                    .load(R.mipmap.img_profile_dafault_foreground)
                    .transform(CenterCrop(), RoundedCorners(200))
                    .into(writer_img)
            }

        }
        writer_name.text = profile.nickName
        answer_text.text = data.content
        date_txt.text = data.createdAt

        more_btn.setOnClickListener(View.OnClickListener { a_view ->
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                a_itemClickListener.onItemClick(a_view, position)
            }
        })

        writer_img.setOnClickListener(View.OnClickListener { a_view ->
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                d_itemClickListener.onItemClick(a_view, position)
            }
        })
    }

    init {
        writer_img = itemView.findViewById(R.id.writer_img)
        writer_name = itemView.findViewById(R.id.writer_name)
        answer_text = itemView.findViewById(R.id.answer_text)
        date_txt = itemView.findViewById(R.id.date_txt)
        more_btn = itemView.findViewById(R.id.more_btn)
    }
}
