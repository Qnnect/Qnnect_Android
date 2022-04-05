package com.iame.qnnect.android.src.question

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.question.model.GetUserQuestionListResponse


class UserQuestionListAdapter() :
    RecyclerView.Adapter<UserQuestionListHolderPage>(){

    private val itemList = ArrayList<GetUserQuestionListResponse>()

    fun setOnItemClickListener(a_listener: OnItemClickEventListener) {
        mItemClickListener = a_listener
    }

    private var mItemClickListener: OnItemClickEventListener? = null

    fun interface OnItemClickEventListener {
        fun onItemClick(a_view: View?, a_position: Int)
    } // <- fun interface (sam) 고차함수에 대한 이해하기 (람다 -> 고차함수를 표현하는 방식 -> 고차함수(함수를 인자로 넘길 수 있음, 자바는 X))


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserQuestionListHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.bookmark_question_item, parent, false)
        return UserQuestionListHolderPage(view, context, mItemClickListener!!)
    }

    override fun onBindViewHolder(holder: UserQuestionListHolderPage, position: Int) {
        if (holder is UserQuestionListHolderPage) {
            val viewHolder: UserQuestionListHolderPage = holder as UserQuestionListHolderPage
            viewHolder.onBind(itemList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(item: GetUserQuestionListResponse) {
        itemList.add(item)
    }

    fun clear() {
        itemList.clear()
        this.notifyDataSetChanged()
    }

    fun getItem(position: Int): GetUserQuestionListResponse {
        return itemList.get(position)
    }
}