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
import com.iame.qnnect.android.src.main.bookmark.model.bookmark_question
import com.iame.qnnect.android.src.main.home.model.group_item


class QuestionListAdapter(private val itemList : ArrayList<bookmark_question>) :
    RecyclerView.Adapter<QuestionListAdapter.ViewHolder>(){
    var datas = ArrayList<bookmark_question>()

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
        holder.question_num.setText(itemList.get(position).question_num)
        holder.question_contents.setText(itemList.get(position).question_contents)
        holder.date.setText(itemList.get(position).date)


//        holder.itemView.setOnClickListener {
//            var intent = Intent(holder.itemView?.context, StreamingActivity::class.java)
//            intent.putExtra("what_streaming", "challenge")
////            var challengeId = intent.getIntExtra("challengeId", 0)
//            intent.putExtra("challengeId", itemList.get(position).challengeId)
//            ContextCompat.startActivity(holder.itemView.context, intent, null)
////            val name = itemList.get(position).category_name
////            intent.putExtra("category_name", name)
////            ContextCompat.startActivity(holder.itemView.context, intent, null)
//        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}