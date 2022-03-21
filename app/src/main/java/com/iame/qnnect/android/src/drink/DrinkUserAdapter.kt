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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.drink.model.CafeUser
import com.iame.qnnect.android.src.drink.model.drink_item
import com.iame.qnnect.android.src.group.question.GroupViewHolderPage
import com.iame.qnnect.android.util.recipe
import com.kakao.sdk.user.model.User
import kotlinx.android.synthetic.main.activity_profile.*


class DrinkUserAdapter() :
    RecyclerView.Adapter<UserHolderPage>(){
    var datas = ArrayList<CafeUser>()
    var select_index = 0

    private val itemList = ArrayList<CafeUser>()

    fun setOnItemClickListener(a_listener: OnItemClickEventListener) {
        mItemClickListener = a_listener
    }

    private var mItemClickListener: OnItemClickEventListener? = null
    interface OnItemClickEventListener {
        fun onItemClick(a_view: View?, a_position: Int)
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        var user_img: ImageView = itemView.findViewById(R.id.user_img)
        var user_name: TextView = itemView.findViewById(R.id.user_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.drink_group_item, parent, false)
        return UserHolderPage(view, context, mItemClickListener!!)
    }

    override fun onBindViewHolder(holder: UserHolderPage, position: Int) {
        if (holder is UserHolderPage) {
            val viewHolder: UserHolderPage = holder as UserHolderPage
            viewHolder.onBind(itemList[position], select_index)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(item: CafeUser) {
        itemList.add(item)
    }

    fun clear() {
        itemList.clear()
        this.notifyDataSetChanged()
    }

    fun getItem(position: Int): CafeUser {
        return itemList.get(position)
    }

    fun setBackground(position: Int){
        select_index = position
    }
}