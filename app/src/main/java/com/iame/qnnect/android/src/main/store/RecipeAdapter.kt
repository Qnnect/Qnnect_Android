package com.iame.qnnect.android.src.main.store

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.R
import com.iame.qnnect.android.util.recipe


class RecipeAdapter() :
    RecyclerView.Adapter<RecipeAdapter.ViewHolder>(){
    var datas = ArrayList<recipe>()

    private val itemList = ArrayList<recipe>()

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        var recipe_name: TextView = itemView.findViewById(R.id.recipe_name)
        var recipe_img: ImageView = itemView.findViewById(R.id.recipe_img)
        var point: TextView = itemView.findViewById(R.id.point_txt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.recipe_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.recipe_name.setText(itemList.get(position).name)
        holder.recipe_img.setImageResource(itemList.get(position).img)
        holder.point.setText(itemList.get(position).point.toString())
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(item: recipe) {
        itemList.add(item)
    }

    fun clear() {
        itemList.clear()
    }
}