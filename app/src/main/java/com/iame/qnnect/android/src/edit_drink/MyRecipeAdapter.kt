package com.iame.qnnect.android.src.edit_drink

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.add_drink.DrinkAdapter
import com.iame.qnnect.android.src.add_drink.drink
import com.iame.qnnect.android.src.edit_drink.model.MyIngredient
import com.iame.qnnect.android.util.recipe


class MyRecipeAdapter() :
    RecyclerView.Adapter<MyRecipeAdapter.ViewHolder>(){
    var datas = ArrayList<MyIngredient>()

    private val itemList = ArrayList<MyIngredient>()

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
        var recipe_main: ConstraintLayout = itemView.findViewById(R.id.recipe_main)
        var recipe_name: TextView = itemView.findViewById(R.id.recipe_name)
        var recipe_img: ImageView = itemView.findViewById(R.id.recipe_img)
        var point: TextView = itemView.findViewById(R.id.point_txt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.material_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var recipe = recipe(itemList.get(position).ingredientId)
        holder.recipe_name.setText(itemList.get(position).name)
        holder.recipe_img.setImageResource(recipe.img)
        holder.point.setText("X"+itemList.get(position).count.toString())

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
        if(select_index==position) {
            holder.recipe_main.setBackgroundResource(R.drawable.store_recipe_select_custom)
        }
        else {
            holder.recipe_main.setBackgroundResource(R.drawable.store_recipe_custom)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(item: MyIngredient) {
        itemList.add(item)
    }

    fun clear() {
        itemList.clear()
    }

    fun getItem(position: Int): recipe {
        return recipe(itemList.get(position).ingredientId)
    }
}