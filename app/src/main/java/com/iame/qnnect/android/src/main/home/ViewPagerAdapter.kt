package com.iame.qnnect.android.src.main.home

import android.content.Context

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.main.home.model.question_item


class ViewPagerAdapter internal constructor(data: ArrayList<question_item>) :
    RecyclerView.Adapter<ViewHolderPage>() {
    private val listData: ArrayList<question_item>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.home_question_item, parent, false)
        return ViewHolderPage(view)
    }

    override fun onBindViewHolder(holder: ViewHolderPage, position: Int) {
        if (holder is ViewHolderPage) {
            val viewHolder: ViewHolderPage = holder as ViewHolderPage
            viewHolder.onBind(listData[position])
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    init {
        listData = data
    }
}
