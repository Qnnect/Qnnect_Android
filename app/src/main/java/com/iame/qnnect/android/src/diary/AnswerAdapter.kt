package com.iame.qnnect.android.src.diary

import android.content.Context
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
import com.iame.qnnect.android.src.diary.model.answer_item
import com.iame.qnnect.android.src.group.GroupFragment
import com.iame.qnnect.android.src.main.MainActivity


class AnswerAdapter(
    private val itemList: ArrayList<answer_item>
) :
    RecyclerView.Adapter<AnswerAdapter.ViewHolder>(){
    var datas = ArrayList<answer_item>()

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        var user_img: ImageView = itemView.findViewById(R.id.user_img)
        var user_name: TextView = itemView.findViewById(R.id.user_name)
        var contents: TextView = itemView.findViewById(R.id.answer_contents)
        var answer_img: ImageView = itemView.findViewById(R.id.answer_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.diary_answer_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.user_img.

        holder.user_name.setText(itemList.get(position).user_name)
        holder.contents.setText(itemList.get(position).contents)

        if(itemList.get(position).images == null){
            holder.answer_img.visibility = View.GONE
        }


//        holder.itemView.setOnClickListener {
//            home_case.setHome(context, 1, "group_name")
//
//            var fragment: Fragment = GroupFragment()
//            var bundle: Bundle = Bundle()
////            bundle.putString("user_id",holder?.userID.text.toString())
////            bundle.putString("goal_id",holder?.goalID.text.toString())
////            bundle.putString("content",holder?.content_txt.text.toString())
////            bundle.putString("date",holder?.date_txt.text.toString())
//
//            fragment.arguments=bundle
//            // 나는 fragment안에 fragment가 있기 때문에 이런식으로 bundle을 붙여줘야했다.
//            /*그런게 아니라면
//            fragment_s.fragmentManager!!.beginTransaction().replace(R.id.content).commit()
//            해주면 된다.*/
//
//            activity = fragment_s.activity as MainActivity?
//            //change_for_adapter는 mainactivity에 구현
//            activity?.fragmentChange_for_adapter(fragment)
//        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}