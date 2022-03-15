package com.iame.qnnect.android.src.question

import android.content.Intent
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.base.HomeFragment_case
import com.iame.qnnect.android.databinding.ActivityQuestionlistBinding
import com.iame.qnnect.android.databinding.ActivitySearchBinding
import com.iame.qnnect.android.src.main.bookmark.QuestionListAdapter
import com.iame.qnnect.android.src.search.SearchActivity
import com.iame.qnnect.android.viewmodel.QuestionListViewModel
import kotlinx.android.synthetic.main.activity_questionlist.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestionListActivity : BaseActivity<ActivityQuestionlistBinding, QuestionListViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_questionlist // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: QuestionListViewModel by viewModel()
    private val questionListAdapter: QuestionListAdapter by inject()

    var home = HomeFragment_case()

    override fun initStartView() {
        // question list recycler
        question_recycler.run {
            adapter = questionListAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        viewModel.bookmarkResponse.observe(this, Observer {
            it.forEach { item ->
                questionListAdapter.addItem(item)
            }
            questionListAdapter.notifyDataSetChanged()
            dismissLoadingDialog()
        })
    }

    override fun initAfterBinding() {
        questionListAdapter.clear()

        var cafeId = home.getGroupname(this)

        viewModel.getBookamrk(cafeId!!)
        showLoadingDialog(this)

        back_btn.setOnClickListener {
            finish()
        }

        search_btn.setOnClickListener {
            var intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }
}