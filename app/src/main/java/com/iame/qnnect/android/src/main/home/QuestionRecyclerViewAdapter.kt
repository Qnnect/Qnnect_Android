package com.iame.qnnect.android.src.main.home

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.MainSearchRecyclerViewAdapter
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.answer.AnswerActivity
import com.iame.qnnect.android.src.main.home.home_model.HomeQuestion
import com.iame.qnnect.android.src.main.home.model.question_item
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.home_question_item.view.*
import kotlinx.android.synthetic.main.item_main_image.view.*

class QuestionRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class QuestionHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.home_question_item, parent, false)
    ) {
        fun onBind(item: HomeQuestion) {
            itemView.run {
                dday_txt.text = "D-"+item.daysLeft.toString()
                diary_name.text = item.cafeTitle
                question_txt.text = item.content

                itemView.setOnClickListener {
                    var intent = Intent(context, AnswerActivity::class.java)
                    intent.putExtra("questionId", item.cafeQuestionId)
                    context.startActivity(intent)
                }
            }
        }
    }

    private val questionList = ArrayList<HomeQuestion>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = QuestionHolder(parent)

    override fun getItemCount() = questionList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? QuestionHolder)?.onBind(questionList[position])
    }

    fun addItem(item: HomeQuestion) {
        questionList.add(item)
    }

    fun clear() {
        questionList.clear()
    }

}