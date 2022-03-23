package com.iame.qnnect.android.src.reply

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.reply.model.Replies
import com.iame.qnnect.android.src.reply.ReplyAdapter.OnItemClickEventListener


class ReplyAdapter() :
    RecyclerView.Adapter<ReplyHolderPage>(){
    var datas = ArrayList<Replies>()

    private val itemList = ArrayList<Replies>()

    fun setOnItemClickListener(a_listener: OnItemClickEventListener) {
        mItemClickListener = a_listener
    }

    fun setOnItemClickListener2(d_listener: OnItemClickEventListener) {
        dItemClickListener = d_listener
    }

    private var mItemClickListener: OnItemClickEventListener? = null
    private var dItemClickListener: OnItemClickEventListener? = null
    interface OnItemClickEventListener {
        fun onItemClick(a_view: View?, a_position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReplyHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.reply_item, parent, false)
        return ReplyHolderPage(view, context, mItemClickListener!!, dItemClickListener!!)
    }

    override fun onBindViewHolder(holder: ReplyHolderPage, position: Int) {
        if (holder is ReplyHolderPage) {
            val viewHolder: ReplyHolderPage = holder as ReplyHolderPage
            viewHolder.onBind(itemList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(item: Replies) {
        itemList.add(item)
    }

    fun getItem(position: Int): Replies {
        return itemList.get(position)
    }

    fun clear() {
        itemList.clear()
        this.notifyDataSetChanged()
    }
}