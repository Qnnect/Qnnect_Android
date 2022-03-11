package com.iame.qnnect.android.src.main.bookmark

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.main.bookmark.model.Cafe
import com.iame.qnnect.android.src.main.bookmark.model.bookmark_group
import com.iame.qnnect.android.src.main.home.home_model.HomeCafes
import com.iame.qnnect.android.src.main.home.model.group_item
import kotlin.coroutines.coroutineContext


class GroupnameAdapter() :
    RecyclerView.Adapter<GroupnameAdapter.ViewHolder>(){

    private val itemList = ArrayList<Cafe>()
    var select_index = 0

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        var group_name: TextView = itemView.findViewById(R.id.group_name)
    }

    // item click listener
    interface OnItemClickListener {
        fun onItemClick(v: View?, position: Int)
    }

    // 리스너 객체 참조를 저장하는 변수
    private var mListener: OnItemClickListener? = null

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.bookmark_group_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.group_name.setText(itemList.get(position).cafeTitle)

        // // item click listener
        holder.itemView.setOnClickListener(View.OnClickListener { v ->
            val pos: Int = position
            if (pos != RecyclerView.NO_POSITION) {
                // 리스너 객체의 메서드 호출.
                if (mListener != null) {
                    mListener!!.onItemClick(v, pos)
                }
                select_index=position
                notifyDataSetChanged()
            }
        })
        if(select_index==position){
            holder.group_name.setBackgroundResource(R.drawable.group_select_ok)
            holder.group_name.setTextColor(Color.parseColor("#FFFFFF"))
        }
        else
        {
            holder.group_name.setBackgroundResource(R.drawable.group_select_fail)
            holder.group_name.setTextColor(Color.parseColor("#333333"))
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(item: Cafe) {
        itemList.add(item)
    }

    fun clear() {
        itemList.clear()
        this.notifyDataSetChanged()
    }

    fun getItem(position: Int): Cafe{
        return itemList.get(position)
    }
}