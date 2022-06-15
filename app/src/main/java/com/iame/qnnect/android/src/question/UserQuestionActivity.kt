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
import com.iame.qnnect.android.src.notarrivecafe.MyQuestionActivity
import com.iame.qnnect.android.src.question.model.GetUserQuestionListResponse
import com.iame.qnnect.android.viewmodel.QuestionListViewModel
import kotlinx.android.synthetic.main.activity_questionlist.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserQuestionActivity : BaseActivity<ActivityQuestionlistBinding, QuestionListViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_questionlist // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: QuestionListViewModel by viewModel()
    private val questionListAdapter: UserQuestionListAdapter by inject()

    override fun initStartView() {
        // question list recycler
        binding.bookmarkTxt.text = "내가 보낸 질문"
        binding.searchBtn.visibility = View.GONE

        binding.questionRecycler.run {
            adapter = questionListAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        viewModel.userquestionResponse.observe(this, Observer {
            if(it.isEmpty()){
                binding.emptyImg.visibility = View.VISIBLE
                binding.emptyTxt.visibility = View.VISIBLE
            }
            else{
                binding.emptyImg.visibility = View.GONE
                binding.emptyTxt.visibility = View.GONE
                it.forEach { item ->
                    if(item != null){
                        questionListAdapter.addItem(item)
                    }
                }
            }
            questionListAdapter.notifyDataSetChanged()
            dismissLoadingDialog()
        })

        viewModel.errorResponse.observe(this, Observer {
            binding.emptyImg.visibility = View.VISIBLE
            binding.emptyTxt.visibility = View.VISIBLE
            dismissLoadingDialog()
        })
    }

    override fun initAfterBinding() {
        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.searchBtn.setOnClickListener {
            var intent = Intent(this, SearchQuestionActivity::class.java)
            startActivity(intent)
        }

        questionListAdapter.setOnItemClickListener { _, a_position ->
            val item: GetUserQuestionListResponse = questionListAdapter.getItem(a_position)
            if (item.waitingList) {
                var intent = Intent(this@UserQuestionActivity, MyQuestionActivity::class.java)
                intent.putExtra("content", item.question)
                intent.putExtra("questionId", item.cafeQuestionId)
                startActivity(intent)
            } else {
                var intent = Intent(this@UserQuestionActivity, DiaryActivity::class.java)
                intent.putExtra("cafeQuestionId", item.cafeQuestionId)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        questionListAdapter.clear()

        viewModel.getUserQuestion()
        showLoadingDialog(this)
    }
}