package com.iame.qnnect.android.src.diary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.diary.model.Comments
import com.iame.qnnect.android.src.diary.model.answer_item
import com.iame.qnnect.android.src.reply.ReplyAdapter
import com.iame.qnnect.android.src.reply.model.Replies

class AnswerAdapter() :
    RecyclerView.Adapter<QuestionHolderPage>(){
    var datas = ArrayList<answer_item>()

    private val itemList = ArrayList<Comments>()

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.diary_answer_item, parent, false)
        return QuestionHolderPage(view, context, mItemClickListener!!, dItemClickListener!!)
    }

    override fun onBindViewHolder(holder: QuestionHolderPage, position: Int) {
        if (holder is QuestionHolderPage) {
            val viewHolder: QuestionHolderPage = holder as QuestionHolderPage
            viewHolder.onBind(itemList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(item: Comments) {
        itemList.add(item)
    }

    fun getItem(position: Int): Comments {
        return itemList.get(position)
    }

    fun clear() {
        itemList.clear()
        this.notifyDataSetChanged()
    }
}