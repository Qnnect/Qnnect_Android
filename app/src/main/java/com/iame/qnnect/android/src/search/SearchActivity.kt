package com.iame.qnnect.android.src.search

import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivitySearchBinding
import com.iame.qnnect.android.src.main.bookmark.QuestionListAdapter
import com.iame.qnnect.android.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity<ActivitySearchBinding, SearchViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_search // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: SearchViewModel by viewModel()
    private val questionListAdapter: QuestionListAdapter by inject()

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
        search_keyword.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                //Enter key Action
                if (event.getAction() === KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    var search = search_keyword.text.toString()

                    questionListAdapter.clear()

                    viewModel.getBookamrk(search)
                    showLoadingDialog(this@SearchActivity)
                    return true
                }
                return false
            }
        })
    }
}