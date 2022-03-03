package com.iame.qnnect.android.src.group.question

import android.content.Context
import android.content.Intent

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.diary.DiaryActivity
import com.iame.qnnect.android.src.group.model.group_question_item
import com.iame.qnnect.android.src.main.home.ViewHolderPage


class GroupQuestionViewPagerAdapter internal constructor(
    data: ArrayList<group_question_item>,
    var context: Context
) :
    RecyclerView.Adapter<GroupViewHolderPage>() {
    private val listData: ArrayList<group_question_item>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.group_question_item, parent, false)
        return GroupViewHolderPage(view)
    }

    override fun onBindViewHolder(holder: GroupViewHolderPage, position: Int) {
        if (holder is GroupViewHolderPage) {
            val viewHolder: GroupViewHolderPage = holder as GroupViewHolderPage
            viewHolder.onBind(listData[position])
        }

        holder.itemView.setOnClickListener {
            var intent = Intent(context, DiaryActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    init {
        listData = data
    }
}
