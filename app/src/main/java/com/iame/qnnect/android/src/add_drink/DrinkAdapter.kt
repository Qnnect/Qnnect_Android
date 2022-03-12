package com.iame.qnnect.android.src.add_drink

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.main.bookmark.model.Cafe

class DrinkAdapter(private val itemList : ArrayList<drink>) :
    RecyclerView.Adapter<DrinkAdapter.ViewHolder>(){
    var datas = ArrayList<drink>()

    var select_index = 0

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

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        var drink_img: ImageView = itemView.findViewById(R.id.drink_img)
        var drink_name: TextView = itemView.findViewById(R.id.drink_name)
        var drink_main: ConstraintLayout = itemView.findViewById(R.id.drink_main)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.drink_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.drink_img.setImageResource(itemList.get(position).img)
        holder.drink_name.setText(itemList.get(position).name)

        holder.itemView.setOnClickListener {

        }

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
            holder.drink_main.setBackgroundResource(R.drawable.store_recipe_select_custom)
            holder.drink_name.setTextColor(Color.parseColor("#FFFFFF"))
        }
        else
        {
            holder.drink_main.setBackgroundResource(R.drawable.store_recipe_custom)
            holder.drink_name.setTextColor(Color.parseColor("#333333"))
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun getItem(position: Int): drink {
        return itemList.get(position)
    }
}