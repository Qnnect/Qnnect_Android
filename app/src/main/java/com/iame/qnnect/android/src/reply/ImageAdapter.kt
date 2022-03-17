package com.iame.qnnect.android.src.reply

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
import com.iame.qnnect.android.base.HomeFragment_case
import com.iame.qnnect.android.src.answer.AnswerActivity
import com.iame.qnnect.android.src.diary.model.Comments
import com.iame.qnnect.android.src.diary.model.answer_item
import com.iame.qnnect.android.src.group.GroupFragment
import com.iame.qnnect.android.src.group.member.GroupMemberAdapter
import com.iame.qnnect.android.src.group.model.CafeQuestion
import com.iame.qnnect.android.src.group.model.CafeUser
import com.iame.qnnect.android.src.group.question.GroupViewHolderPage
import com.iame.qnnect.android.src.main.MainActivity
import com.iame.qnnect.android.src.reply.model.Replies
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject


class ImageAdapter() :
    RecyclerView.Adapter<ImageHolderPage>(){
    var datas = ArrayList<String>()

    private val itemList = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.answer_image_item, parent, false)
        return ImageHolderPage(view, context)
    }

    override fun onBindViewHolder(holder: ImageHolderPage, position: Int) {
        if (holder is ImageHolderPage) {
            val viewHolder: ImageHolderPage = holder as ImageHolderPage
            viewHolder.onBind(itemList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun getItem(position: Int): String {
        return itemList.get(position)
    }

    fun addItem(item: String) {
        if(item != "null"){
            itemList.add(item)
        }
    }

    fun clear() {
        itemList.clear()
        this.notifyDataSetChanged()
    }
}