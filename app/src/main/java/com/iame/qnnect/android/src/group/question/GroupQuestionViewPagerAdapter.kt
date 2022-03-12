package com.iame.qnnect.android.src.group.question

import android.content.Context
import android.content.Intent
import android.net.Uri

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import androidx.core.content.ContextCompat

import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.MainSearchRecyclerViewAdapter
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.diary.DiaryActivity
import com.iame.qnnect.android.src.group.model.CafeQuestion
import com.iame.qnnect.android.src.group.model.group_question_item
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_main_image.view.*


class GroupQuestionViewPagerAdapter internal constructor() :
    RecyclerView.Adapter<GroupViewHolderPage>() {

    private val listData =  ArrayList<CafeQuestion>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupViewHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.group_question_item, parent, false)
        return GroupViewHolderPage(view, context)
    }

    override fun onBindViewHolder(holder: GroupViewHolderPage, position: Int) {
        if (holder is GroupViewHolderPage) {
            val viewHolder: GroupViewHolderPage = holder as GroupViewHolderPage
            viewHolder.onBind(listData[position])
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    fun addItem(item: CafeQuestion) {
        listData.add(item)
    }

    fun clear() {
        listData.clear()
        this.notifyDataSetChanged()
    }
}

