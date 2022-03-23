package com.iame.qnnect.android.src.declare

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
import com.iame.qnnect.android.src.declare.model.DeclareUser
import com.iame.qnnect.android.src.diary.DiaryActivity
import com.iame.qnnect.android.src.diary.model.Comments
import com.iame.qnnect.android.src.group.model.CafeQuestion
import com.iame.qnnect.android.src.group.model.group_question_item
import com.iame.qnnect.android.src.reply.ReplyActivity
import com.iame.qnnect.android.src.reply.ReplyAdapter
import com.iame.qnnect.android.src.reply.model.Replies
import com.iame.qnnect.android.src.reply.reply_more.DeleteReplyDialog
import com.iame.qnnect.android.src.reply.reply_more.ReplyMoreBottomSheet
import kotlinx.android.synthetic.main.item_main_image.view.*


class DeclareHolderPage internal constructor(itemView: View, var context: Context,
                                             var a_itemClickListener: DeclareAdapter.OnItemClickEventListener
) : RecyclerView.ViewHolder(itemView) {

    private val name: TextView
    private val btn: TextView
    private val main: ConstraintLayout

    var data: DeclareUser? = null
    fun onBind(data: DeclareUser) {
        this.data = data

        name.text = data.nickName

        btn.setOnClickListener(View.OnClickListener { a_view ->
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                a_itemClickListener.onItemClick(a_view, position)
            }
        })

        main.setOnClickListener(View.OnClickListener { a_view ->
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                a_itemClickListener.onItemClick(a_view, position)
            }
        })
    }

    init {
        main = itemView.findViewById(R.id.declare_main)
        name = itemView.findViewById(R.id.declare_name)
        btn = itemView.findViewById(R.id.btn)
    }
}
