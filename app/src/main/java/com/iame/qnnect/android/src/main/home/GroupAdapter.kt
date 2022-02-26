package com.iame.qnnect.android.src.main.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.src.main.home.model.group_item
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.group.GroupFragment
import com.iame.qnnect.android.src.main.MainActivity


class GroupAdapter(
    val context: Context,
    private val itemList: ArrayList<group_item>,
    val fragment_s: Fragment
) :
    RecyclerView.Adapter<GroupAdapter.ViewHolder>(){
    var datas = ArrayList<group_item>()
    private var activity: MainActivity? = null

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        var group_name: TextView = itemView.findViewById(com.iame.qnnect.android.R.id.group_name)
        var date: TextView = itemView.findViewById(com.iame.qnnect.android.R.id.date_txt)
        var people_count: TextView = itemView.findViewById(com.iame.qnnect.android.R.id.count_txt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(com.iame.qnnect.android.R.layout.home_group_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.group_name.setText(itemList.get(position).group_name)
        holder.date.setText(itemList.get(position).date)
        holder.people_count.setText(itemList.get(position).people_count)


        holder.itemView.setOnClickListener {
            var fragment: Fragment = GroupFragment()
            var bundle: Bundle = Bundle()
//            bundle.putString("user_id",holder?.userID.text.toString())
//            bundle.putString("goal_id",holder?.goalID.text.toString())
//            bundle.putString("content",holder?.content_txt.text.toString())
//            bundle.putString("date",holder?.date_txt.text.toString())

            fragment.arguments=bundle
            // 나는 fragment안에 fragment가 있기 때문에 이런식으로 bundle을 붙여줘야했다.
            /*그런게 아니라면
            fragment_s.fragmentManager!!.beginTransaction().replace(R.id.content).commit()
            해주면 된다.*/

            activity = fragment_s.activity as MainActivity?
            //change_for_adapter는 mainactivity에 구현
            activity?.fragmentChange_for_adapter(fragment)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}