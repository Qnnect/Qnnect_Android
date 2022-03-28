package com.iame.qnnect.android.src.stamp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.stamp.model.Stamp


class StampAdapter() :
    RecyclerView.Adapter<StampHolderPage>(){
    var datas = ArrayList<Stamp>()

    private val itemList = ArrayList<Stamp>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StampHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.stamp_item, parent, false)
        return StampHolderPage(view, context)
    }

    override fun onBindViewHolder(holder: StampHolderPage, position: Int) {
        if (holder is StampHolderPage) {
            val viewHolder: StampHolderPage = holder as StampHolderPage
            viewHolder.onBind(itemList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(item: Stamp) {
        itemList.add(item)
    }

    fun getItem(position: Int): Stamp {
        return itemList.get(position)
    }

    fun clear() {
        itemList.clear()
        this.notifyDataSetChanged()
    }
}