package com.iame.qnnect.android.src.reply

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.reply.model.Replies


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

        Glide.with(context)
            .load(profile.profileImage)
            .transform(CenterCrop(), RoundedCorners(200))
            .apply(RequestOptions().placeholder(R.mipmap.profile_default_foreground)
                .error(R.mipmap.profile_default_foreground))
            .into(writer_img)

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
