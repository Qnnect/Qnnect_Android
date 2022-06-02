package com.iame.qnnect.android.src.main.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.diary.DiaryActivity
import com.iame.qnnect.android.src.main.home.home_model.HomeQuestion
import kotlinx.android.synthetic.main.home_question_item.view.*

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
                    var intent = Intent(context, DiaryActivity::class.java)
                    intent.putExtra("cafeQuestionId", item.cafeQuestionId)
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