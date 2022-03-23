package com.iame.qnnect.android.src.declare

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.declare.model.DeclareUser
import com.iame.qnnect.android.src.reply.model.Replies
import com.iame.qnnect.android.src.reply.ReplyAdapter.OnItemClickEventListener
import com.iame.qnnect.android.src.reply.ReplyHolderPage


class DeclareAdapter() :
    RecyclerView.Adapter<DeclareHolderPage>(){

    var datas = ArrayList<DeclareUser>()

    private val itemList = ArrayList<DeclareUser>()

    fun setOnItemClickListener(a_listener: OnItemClickEventListener) {
        mItemClickListener = a_listener
    }
    private var mItemClickListener: OnItemClickEventListener? = null
    interface OnItemClickEventListener {
        fun onItemClick(a_view: View?, a_position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeclareHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.declare_item, parent, false)
        return DeclareHolderPage(view, context, mItemClickListener!!)
    }

    override fun onBindViewHolder(holder: DeclareHolderPage, position: Int) {
        if (holder is DeclareHolderPage) {
            val viewHolder: DeclareHolderPage = holder as DeclareHolderPage
            viewHolder.onBind(itemList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(item: DeclareUser) {
        itemList.add(item)
    }

    fun getItem(position: Int): DeclareUser {
        return itemList.get(position)
    }

    fun clear() {
        itemList.clear()
        this.notifyDataSetChanged()
    }
}