package com.iame.qnnect.android.src.question

import android.content.Intent
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityQuestionlistBinding
import com.iame.qnnect.android.src.diary.DiaryActivity
import com.iame.qnnect.android.src.main.bookmark.QuestionListAdapter
import com.iame.qnnect.android.src.main.bookmark.model.Bookmark
import com.iame.qnnect.android.viewmodel.QuestionListViewModel
import kotlinx.android.synthetic.main.activity_questionlist.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserQuestionActivity : BaseActivity<ActivityQuestionlistBinding, QuestionListViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_questionlist // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: QuestionListViewModel by viewModel()
    private val questionListAdapter: QuestionListAdapter by inject()

    override fun initStartView() {
        // question list recycler
        bookmark_txt.text = "내가 보낸 질문"
        search_btn.visibility = View.GONE

        question_recycler.run {
            adapter = questionListAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        viewModel.userquestionResponse.observe(this, Observer {
            if(it.size == 0){
                empty_img.visibility = View.VISIBLE
                empty_txt.visibility = View.VISIBLE
            }
            else{
                empty_img.visibility = View.GONE
                empty_txt.visibility = View.GONE
                it.forEach { item ->
                    if(item != null){
                        questionListAdapter.addItem(item)
                    }
                }
            }
            questionListAdapter.notifyDataSetChanged()
            dismissLoadingDialog()
        })
    }

    override fun initAfterBinding() {
        questionListAdapter.clear()

        viewModel.getUserQuestion()
        showLoadingDialog(this)

        back_btn.setOnClickListener {
            finish()
        }

        search_btn.setOnClickListener {
            var intent = Intent(this, SearchQuestionActivity::class.java)
            startActivity(intent)
        }

        questionListAdapter.setOnItemClickListener(object :
            QuestionListAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                val item: Bookmark = questionListAdapter.getItem(a_position)
                var intent = Intent(this@UserQuestionActivity, DiaryActivity::class.java)
                intent.putExtra("cafeQuestionId", item.cafeQuestionId)
                startActivity(intent)
            }
        })
    }
}