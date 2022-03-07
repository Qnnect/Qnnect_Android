package com.iame.qnnect.android.src.main.home

import android.content.Context
import android.content.Intent
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
                group_name.setText(item.title)
                date_txt.setText(item.createdAt+"~")
                count_txt.setText(item.cafeUserNum.toString()+"명")
                var id = item.id

                itemView.setOnClickListener {
                    home_case.setHome(context, 1, id)

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

                    activity = context as MainActivity?
                    //change_for_adapter는 mainactivity에 구현
                    activity?.fragmentChange_for_adapter(fragment)
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