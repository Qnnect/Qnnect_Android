package com.iame.qnnect.android.src.main.home

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.main.home.model.question_item


class ViewHolderPage internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val d_day_txt: TextView
    private val group_name: TextView
    private val question_txt: TextView

    var data: question_item? = null
    fun onBind(data: question_item) {
        this.data = data
        d_day_txt.text = data.date
        group_name.text = data.group_name
        question_txt.text = data.question
    }

    init {
        d_day_txt = itemView.findViewById(R.id.dday_txt)
        group_name = itemView.findViewById(R.id.diary_name)
        question_txt = itemView.findViewById(R.id.question_txt)
    }
}
