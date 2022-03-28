package com.iame.qnnect.android.src.alarm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.alarm.model.Alarm
import com.iame.qnnect.android.src.reply.model.Replies
import com.iame.qnnect.android.src.reply.ReplyAdapter.OnItemClickEventListener
import com.iame.qnnect.android.src.reply.ReplyHolderPage


class AlarmAdapter() :
    RecyclerView.Adapter<AlarmHolderPage>(){
    var datas = ArrayList<Alarm>()

    private val itemList = ArrayList<Alarm>()

    fun setOnItemClickListener(a_listener: OnItemClickEventListener) {
        mItemClickListener = a_listener
    }
    private var mItemClickListener: OnItemClickEventListener? = null

    interface OnItemClickEventListener {
        fun onItemClick(a_view: View?, a_position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.alarm_item, parent, false)
        return AlarmHolderPage(view, context, mItemClickListener!!)
    }

    override fun onBindViewHolder(holder: AlarmHolderPage, position: Int) {
        if (holder is AlarmHolderPage) {
            val viewHolder: AlarmHolderPage = holder as AlarmHolderPage
            viewHolder.onBind(itemList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(item: Alarm) {
        itemList.add(item)
    }

    fun getItem(position: Int): Alarm {
        return itemList.get(position)
    }

    fun clear() {
        itemList.clear()
        this.notifyDataSetChanged()
    }
}