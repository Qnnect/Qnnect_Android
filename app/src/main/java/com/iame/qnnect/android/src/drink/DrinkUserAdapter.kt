package com.iame.qnnect.android.src.drink

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.src.drink.model.CafeUser

class DrinkUserAdapter() :
    RecyclerView.Adapter<UserHolderPage>(){
    var datas = ArrayList<CafeUser>()

    private val itemList = ArrayList<CafeUser>()

    var select_index = -1

    fun setOnItemClickListener(a_listener: OnItemClickEventListener) {
        mItemClickListener = a_listener
    }

    private var mItemClickListener: OnItemClickEventListener? = null
    interface OnItemClickEventListener {
        fun onItemClick(a_view: View?, a_position: Int)
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        var user_img: ImageView = itemView.findViewById(com.iame.qnnect.android.R.id.user_img)
        var user_name: TextView = itemView.findViewById(com.iame.qnnect.android.R.id.user_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(com.iame.qnnect.android.R.layout.drink_group_item, parent, false)

        view.setOnClickListener(View.OnClickListener {

        })
        return UserHolderPage(view, context, mItemClickListener!!)
    }

    override fun onBindViewHolder(holder: UserHolderPage, position: Int) {
        if (holder is UserHolderPage) {
            val viewHolder: UserHolderPage = holder as UserHolderPage
            viewHolder.onBind(itemList[position], this)
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
}