package com.iame.qnnect.android.src.diary

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.src.main.home.model.group_item
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.HomeFragment_case
import com.iame.qnnect.android.src.answer.AnswerActivity
import com.iame.qnnect.android.src.diary.model.answer_item
import com.iame.qnnect.android.src.group.GroupFragment
import com.iame.qnnect.android.src.main.MainActivity


class AnswerAdapter(
    private val itemList: ArrayList<answer_item>,
    val context: Context
) :
    RecyclerView.Adapter<AnswerAdapter.ViewHolder>(){
    var datas = ArrayList<answer_item>()

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        var user_img: ImageView = itemView.findViewById(R.id.user_img)
        var user_name: TextView = itemView.findViewById(R.id.user_name)
        var contents: TextView = itemView.findViewById(R.id.answer_contents)
        var answer_img: ImageView = itemView.findViewById(R.id.answer_img)
        var answer_btn: ImageView = itemView.findViewById(R.id.answer_btn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.diary_answer_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.user_img.

        holder.user_name.setText(itemList.get(position).user_name)
        holder.contents.setText(itemList.get(position).contents)

        if(itemList.get(position).images == null){
            holder.answer_img.visibility = View.GONE
        }

        holder.answer_btn.setOnClickListener {
            var intent = Intent(context, AnswerActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}