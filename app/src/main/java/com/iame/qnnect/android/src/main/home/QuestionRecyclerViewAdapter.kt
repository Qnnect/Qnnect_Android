package com.iame.qnnect.android.src.main.home

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.MainSearchRecyclerViewAdapter
import com.iame.qnnect.android.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_question_item.view.*
import kotlinx.android.synthetic.main.item_main_image.view.*

class QuestionRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    data class question_item(var date: String, var group_name: String ,var question: String)

    class QuestionHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.home_question_item, parent, false)
    ) {
        fun onBind(item: question_item) {
            itemView.run {
                dday_txt.text = item.date
                diary_name.text = item.group_name
                question_txt.text = item.question

//                item_main_image_view.setOnClickListener {
//                    ContextCompat.startActivity(
//                        context,
//                        Intent(Intent.ACTION_VIEW, Uri.parse(item.documentUrl)),
//                        null
//                    )
//                }
            }
        }
    }

    private val questionList = ArrayList<question_item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = QuestionHolder(parent)

    override fun getItemCount() = questionList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? QuestionHolder)?.onBind(questionList[position])
    }

    fun addImageItem(date: String, group_name: String ,question: String) {
        questionList.add(question_item(date, group_name, question))
    }

    fun clear() {
        questionList.clear()
    }

}