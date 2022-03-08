package com.iame.qnnect.android.src.main.home

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.src.main.home.model.group_item
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.iame.qnnect.android.MainSearchRecyclerViewAdapter
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.HomeFragment_case
import com.iame.qnnect.android.src.group.GroupFragment
import com.iame.qnnect.android.src.group.model.CafeUser
import com.iame.qnnect.android.src.main.MainActivity
import com.iame.qnnect.android.src.main.home.home_model.HomeCafes
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_group_item.view.*
import kotlinx.android.synthetic.main.item_main_image.view.*


class GroupAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var datas = ArrayList<group_item>()
    private var activity: MainActivity? = null

    var home_case = HomeFragment_case()

    private val itemList = ArrayList<HomeCafes>()

    class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.home_group_item, parent, false)
    ) {
        var home_case = HomeFragment_case()
        private var activity: MainActivity? = null

        fun onBind(item: HomeCafes) {
            itemView.run {
                if(item.diaryColor == "red"){
                    group_main.setBackgroundResource(R.drawable.home_question_custom_in_red)
                }
                else if(item.diaryColor == "pink"){
                    group_main.setBackgroundResource(R.drawable.home_question_custom_in_pink)
                }
                else if(item.diaryColor == "blue"){
                    group_main.setBackgroundResource(R.drawable.home_question_custom_in_blue)
                }
                else{
                    group_main.setBackgroundResource(R.drawable.home_question_custom_in)
                }
                group_name.setText(item.title)
                date_txt.setText(item.createdAt+"~")
                count_txt.setText(item.cafeUserNum.toString()+"명")
                var id = item.id

                itemView.setOnClickListener {
                    home_case.setHome(context, 1, id)

                    activity = context as MainActivity?
                    //change_for_adapter는 mainactivity에 구현
                    activity?.fragmentChange_for_adapter()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ViewHolder)?.onBind(itemList[position])
    }

    override fun getItemCount() = itemList.size

    fun addItem(item: HomeCafes) {
        itemList.add(item)
    }

    fun clear() {
        itemList.clear()
    }
}