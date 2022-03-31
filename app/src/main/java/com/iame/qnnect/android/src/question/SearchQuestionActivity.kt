package com.iame.qnnect.android.src.question

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.base.HomeFragment_case
import com.iame.qnnect.android.databinding.ActivitySearchBinding
import com.iame.qnnect.android.databinding.ActivitySearchQuestionBinding
import com.iame.qnnect.android.src.main.bookmark.QuestionListAdapter
import com.iame.qnnect.android.viewmodel.SearchQuestionViewModel
import com.iame.qnnect.android.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class SearchQuestionActivity : BaseActivity<ActivitySearchQuestionBinding, SearchQuestionViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_search_question // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: SearchQuestionViewModel by viewModel()
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
        viewModel.questionResponse.observe(this, Observer {
            if(it.cafeQuestionList.size != 0 ){
                empty_img.visibility = View.GONE
                empty_txt.visibility = View.GONE

                it.cafeQuestionList.forEach { item ->
                    questionListAdapter.addItem(item)
                }
            }
            else{
                empty_img.visibility = View.VISIBLE
                empty_txt.visibility = View.VISIBLE
            }
            questionListAdapter.notifyDataSetChanged()
            dismissLoadingDialog()
        })

        viewModel.errorResponse.observe(this, Observer {
            empty_img.visibility = View.VISIBLE
            empty_txt.visibility = View.VISIBLE

            questionListAdapter.clear()
            questionListAdapter.notifyDataSetChanged()
            dismissLoadingDialog()
        })
    }

    override fun initAfterBinding() {
        var cafeId = home.getGroupname(this)

        search_keyword.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                //Enter key Action
                if (event.getAction() === KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    var search = search_keyword.text.toString()
                    if(search != null){
                        questionListAdapter.clear()

                        viewModel.getBookamrk(cafeId!!, search)
                        showLoadingDialog(this@SearchQuestionActivity)
                        return true
                    }
                }
                return false
            }
        })

        // coroutine search
        search_keyword.addTextChangedListener(object : TextWatcher {
            private var searchFor = ""
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val searchText = s.toString().trim()
                if (searchText == searchFor)
                    return
                searchFor = searchText
                CoroutineScope(Dispatchers.Main + Job()).launch {
                    delay(500)  //debounce timeOut
                    if (searchText != searchFor)
                        return@launch

                    questionListAdapter.clear()
                    viewModel.getBookamrk(cafeId!!, searchFor)
                    showLoadingDialog(this@SearchQuestionActivity)
                }
            }
        })

        back_btn.setOnClickListener {
            finish()
        }
    }
}