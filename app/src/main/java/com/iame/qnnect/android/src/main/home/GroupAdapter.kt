package com.iame.qnnect.android.src.main.home

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
import com.iame.qnnect.android.src.main.home.model.group_item


class GroupAdapter(private val itemList : ArrayList<group_item>) :
    RecyclerView.Adapter<GroupAdapter.ViewHolder>(){
    var datas = ArrayList<group_item>()

//    var group_name: String,
//    var date: String,
//    var people_count: String

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        var group_name: TextView = itemView.findViewById(R.id.group_name)
        var date: TextView = itemView.findViewById(R.id.date_txt)
        var people_count: TextView = itemView.findViewById(R.id.count_txt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.home_group_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        Glide.with(holder.itemView.getContext())
//            .load(itemList.get(position).img)
//            .transform(CenterCrop(), RoundedCorners(50))
//            .into(holder.challenge_img)
        holder.group_name.setText(itemList.get(position).group_name)
        holder.date.setText(itemList.get(position).date)
        holder.people_count.setText(itemList.get(position).people_count)


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