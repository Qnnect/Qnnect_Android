package com.iame.qnnect.android.src.group.question

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.group.model.group_question_item
import com.iame.qnnect.android.src.main.home.model.question_item


class GroupViewHolderPage internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val create_day :TextView
    private val d_day_txt: TextView
    private val group_name: TextView
    private val question_txt: TextView

    var data: group_question_item? = null
    fun onBind(data: group_question_item) {
        this.data = data
        create_day.text = data.create_day
        d_day_txt.text = data.question_dday
        group_name.text = data.member_name
        question_txt.text = data.question_contents
    }

    init {
        create_day = itemView.findViewById(R.id.create_date)
        d_day_txt = itemView.findViewById(R.id.dday_txt)
        group_name = itemView.findViewById(R.id.diary_name)
        question_txt = itemView.findViewById(R.id.question_txt)
    }
}
