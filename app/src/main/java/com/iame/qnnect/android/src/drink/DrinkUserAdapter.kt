package com.iame.qnnect.android.src.drink

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
import com.iame.qnnect.android.src.drink.model.drink_item
import com.iame.qnnect.android.src.group.GroupFragment
import com.iame.qnnect.android.src.main.MainActivity


class DrinkUserAdapter(
    private val itemList: ArrayList<drink_item>
) :
    RecyclerView.Adapter<DrinkUserAdapter.ViewHolder>(){
    var datas = ArrayList<drink_item>()

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        var user_img: ImageView = itemView.findViewById(R.id.user_img)
        var user_name: TextView = itemView.findViewById(R.id.user_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.drink_group_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.user_img.

        holder.user_name.setText(itemList.get(position).user_name)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}