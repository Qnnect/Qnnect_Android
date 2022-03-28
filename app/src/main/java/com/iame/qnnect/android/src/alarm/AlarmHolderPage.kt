package com.iame.qnnect.android.src.alarm

import android.content.Context
import android.content.Intent
import android.graphics.Color
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
import com.iame.qnnect.android.src.alarm.model.Alarm
import com.iame.qnnect.android.src.alarm.model.GetAlarmListResponse
import com.iame.qnnect.android.src.answer.AnswerActivity
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


class AlarmHolderPage internal constructor(itemView: View, var context: Context,
                                           var a_itemClickListener: AlarmAdapter.OnItemClickEventListener) : RecyclerView.ViewHolder(itemView) {
    private val alarm_img :ImageView
    private val group_name: TextView
    private val alarm_contents: TextView
    private val alarm_date: TextView
    private val alarm_back: ImageView

    var data: GetAlarmListResponse? = null
    fun onBind(data: GetAlarmListResponse) {
        this.data = data
        if(data.notificationType == "question"){
            alarm_img.setImageResource(R.drawable.question)
        }
        else if(data.notificationType == "reply"){
            alarm_img.setImageResource(R.drawable.reply)
        }
        else{
            alarm_img.setImageResource(R.drawable.answer)
        }

        if(data.userRead){
            alarm_back.setImageResource(R.drawable.alarm_back_checked)
            group_name.setTextColor(Color.parseColor("#828282"))
            alarm_contents.setTextColor(Color.parseColor("#828282"))
        }
        else{
            alarm_back.setImageResource(R.drawable.alarm_background)
            group_name.setTextColor(Color.parseColor("#736055"))
            alarm_contents.setTextColor(Color.parseColor("#121212"))
        }

        group_name.text = data.groupName
        alarm_contents.text = data.content
        alarm_date.text = data.createdAt

        itemView.setOnClickListener(View.OnClickListener { a_view ->
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                a_itemClickListener.onItemClick(a_view, position)
            }
        })
    }

    init {
        alarm_img = itemView.findViewById(R.id.alarm_img)
        alarm_back = itemView.findViewById(R.id.alarm_back)
        group_name = itemView.findViewById(R.id.group_name)
        alarm_contents = itemView.findViewById(R.id.alarm_contents)
        alarm_date = itemView.findViewById(R.id.alarm_date)
    }
}
