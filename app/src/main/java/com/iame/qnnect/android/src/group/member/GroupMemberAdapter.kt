package com.iame.qnnect.android.src.group.member

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.group.model.CafeUser
import com.iame.qnnect.android.src.group.model.group_drink_item
import com.iame.qnnect.android.src.main.MainActivity
import com.iame.qnnect.android.util.drinkName


class GroupMemberAdapter() : RecyclerView.Adapter<GroupMemberAdapter.ViewHolder>(){
    var datas = ArrayList<group_drink_item>()
    private var activity: MainActivity? = null

    private val itemList = ArrayList<CafeUser>()

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        var member_drink: ImageView = itemView.findViewById(R.id.member_drink)
        var member_name: TextView = itemView.findViewById(R.id.member_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.group_member_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(itemList.get(position).cafeDrinkCommonResponse.userDrinkName == null){
            holder.member_drink.setImageResource(R.mipmap.drink_none_foreground)
//            Glide.with(holder.itemView.getContext())
//                .load(itemList.get(position).drink_img)
//                .transform(CenterCrop())
//                .into(holder.member_drink)
        }
        else{
            if(itemList.get(position).cafeDrinkCommonResponse.currentDrinkIngredientsFilled.size < 2){
                holder.member_drink.setImageResource(R.mipmap.complete_drink_default_foreground)
            }
            else{
                var step = itemList[position].cafeDrinkCommonResponse
                var userStep = "빈잔"
                if(step.ice == step.iceFilled){userStep = "얼음"}
                if(step.base == step.baseFilled){userStep = "베이스"}
                if(step.main == step.mainFilled){userStep = "메인"}
                if(step.topping == step.toppingFilled){userStep = "토핑"}

                Log.d("step!!", userStep)
                var userDrink = itemList.get(position).cafeDrinkCommonResponse.userDrinkName
                var img = drinkName(userDrink, userStep)

                holder.member_drink.setImageResource(img)
            }
        }

        holder.member_name.setText(itemList.get(position).user.nickName)
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
}