package com.iame.qnnect.android.src.stamp

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
import com.iame.qnnect.android.src.stamp.model.Stamp
import com.iame.qnnect.android.util.GetStamp
import kotlinx.android.synthetic.main.item_main_image.view.*


class StampHolderPage internal constructor(itemView: View, var context: Context) : RecyclerView.ViewHolder(itemView) {
    private val stamp_img :ImageView
    private val group_name: TextView
    private val drink_name: TextView

    var data: Stamp? = null
    fun onBind(data: Stamp) {
        this.data = data
        if(data.drinkName == "빈잔"){
            stamp_img.setImageResource(R.mipmap.stamp_empty1_foreground)
            group_name.text = ""
            drink_name.text = ""
        }
        else{
            var img = GetStamp(data.drinkName)
            stamp_img.setImageResource(img)
            group_name.text = data.cafeName
            drink_name.text = data.drinkName
        }


    }

    init {
        stamp_img = itemView.findViewById(R.id.stamp_img)
        drink_name = itemView.findViewById(R.id.drink_name)
        group_name = itemView.findViewById(R.id.group_name)
    }
}
