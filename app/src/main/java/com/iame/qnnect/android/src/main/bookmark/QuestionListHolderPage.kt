package com.iame.qnnect.android.src.main.bookmark

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
import com.iame.qnnect.android.src.main.bookmark.model.Bookmark
import com.iame.qnnect.android.src.reply.ReplyActivity
import com.iame.qnnect.android.src.reply.ReplyAdapter
import com.iame.qnnect.android.src.reply.model.Replies
import com.iame.qnnect.android.src.reply.reply_more.DeleteReplyDialog
import com.iame.qnnect.android.src.reply.reply_more.ReplyMoreBottomSheet
import kotlinx.android.synthetic.main.item_main_image.view.*


class QuestionListHolderPage internal constructor(itemView: View, var context: Context,
                                                  var a_itemClickListener: QuestionListAdapter.OnItemClickEventListener) : RecyclerView.ViewHolder(itemView) {
    private val question_group: TextView
    private val question_contents: TextView
    private val date: TextView
    private val bookmark_main: ConstraintLayout

    var data: Bookmark? = null
    fun onBind(data: Bookmark) {
        this.data = data

        question_group.text = data.cafeTitle
        question_contents.text = data.question
        date.text = data.createdAt

        bookmark_main.setOnClickListener (View.OnClickListener { a_view ->
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                a_itemClickListener.onItemClick(a_view, position)
            }
        })
    }

    init {
        question_group = itemView.findViewById(R.id.question_group)
        question_contents = itemView.findViewById(R.id.question_contents)
        date = itemView.findViewById(R.id.question_date)
        bookmark_main = itemView.findViewById(R.id.bookmark_main)
    }
}
