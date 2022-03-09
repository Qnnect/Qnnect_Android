package com.iame.qnnect.android.src.main.bookmark

import android.content.Intent
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
import com.iame.qnnect.android.src.main.bookmark.model.Bookmark
import com.iame.qnnect.android.src.main.bookmark.model.Cafe
import com.iame.qnnect.android.src.main.bookmark.model.bookmark_question
import com.iame.qnnect.android.src.main.home.model.group_item


class QuestionListAdapter() :
    RecyclerView.Adapter<QuestionListAdapter.ViewHolder>(){

    private val itemList = ArrayList<Bookmark>()

//    var group_name: String,
//    var date: String,
//    var people_count: String

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        var question_num: TextView = itemView.findViewById(R.id.question_num)
        var question_contents: TextView = itemView.findViewById(R.id.question_contents)
        var date: TextView = itemView.findViewById(R.id.question_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.bookmark_question_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.question_num.setText("# "+position.toString())
        holder.question_contents.setText(itemList.get(position).question)
        holder.date.setText(itemList.get(position).createdAt)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(item: Bookmark) {
        itemList.add(item)
    }

    fun clear() {
        itemList.clear()
    }

    fun getItem(position: Int): Bookmark {
        return itemList.get(position)
    }
}