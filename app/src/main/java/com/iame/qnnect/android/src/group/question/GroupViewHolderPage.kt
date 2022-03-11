package com.iame.qnnect.android.src.group.question

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.diary.DiaryActivity
import com.iame.qnnect.android.src.group.model.CafeQuestion
import com.iame.qnnect.android.src.group.model.group_question_item
import kotlinx.android.synthetic.main.item_main_image.view.*


class GroupViewHolderPage internal constructor(itemView: View, var context: Context) : RecyclerView.ViewHolder(itemView) {
    private val create_day :TextView
    private val d_day_txt: TextView
    private val group_name: TextView
    private val question_txt: TextView
    private val main: ConstraintLayout

    var data: CafeQuestion? = null
    fun onBind(data: CafeQuestion) {
        this.data = data
        if(data.questioner == "넥트"){
            main.setBackgroundResource(R.drawable.nnect_question_custom)
        }
        else{
            main.setBackgroundResource(R.drawable.user_question_custom)
        }
        create_day.text = data.createdAt+"~"
        d_day_txt.text = "D-"+data.daysLeft.toString()
        group_name.text = data.questioner+"의 질문"
        question_txt.text = data.question
        itemView.setOnClickListener {
            var intent = Intent(context, DiaryActivity::class.java)
            intent.putExtra("cafeQuestionId", data.cafeQuestionId)
            context.startActivity(intent)
        }
    }

    init {
        create_day = itemView.findViewById(R.id.create_date)
        d_day_txt = itemView.findViewById(R.id.dday_txt)
        group_name = itemView.findViewById(R.id.diary_name)
        question_txt = itemView.findViewById(R.id.question_txt)
        main = itemView.findViewById(R.id.item_main)
    }
}
