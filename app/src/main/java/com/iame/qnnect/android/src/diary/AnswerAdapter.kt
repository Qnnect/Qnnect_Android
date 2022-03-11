package com.iame.qnnect.android.src.diary

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
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject


class AnswerAdapter() :
    RecyclerView.Adapter<QuestionHolderPage>(){
    var datas = ArrayList<answer_item>()

    private val itemList = ArrayList<Comments>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionHolderPage {
        val context: Context = parent.context
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.diary_answer_item, parent, false)
        return QuestionHolderPage(view, context)
    }

    override fun onBindViewHolder(holder: QuestionHolderPage, position: Int) {
        if (holder is QuestionHolderPage) {
            val viewHolder: QuestionHolderPage = holder as QuestionHolderPage
            viewHolder.onBind(itemList[position])
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addItem(item: Comments) {
        itemList.add(item)
    }

    fun clear() {
        itemList.clear()
        this.notifyDataSetChanged()
    }
}