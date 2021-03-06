package com.iame.qnnect.android.src.search

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.test.core.app.ActivityScenario.launch
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivitySearchBinding
import com.iame.qnnect.android.src.diary.DiaryActivity
import com.iame.qnnect.android.src.main.bookmark.QuestionListAdapter
import com.iame.qnnect.android.src.main.bookmark.model.Bookmark
import com.iame.qnnect.android.src.reply.model.Replies
import com.iame.qnnect.android.src.reply.reply_more.ReplyMoreBottomSheet
import com.iame.qnnect.android.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity<ActivitySearchBinding, SearchViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_search // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: SearchViewModel by viewModel()
    private val questionListAdapter: QuestionListAdapter by inject()

    override fun initStartView() {
        // question list recycler
        binding.questionRecycler.run {
            adapter = questionListAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        viewModel.bookmarkResponse.observe(this, Observer {
            questionListAdapter.clear()

            if(it.isNotEmpty()){
                binding.emptyImg.visibility = View.GONE
                binding.emptyTxt.visibility = View.GONE
                it.forEach { item ->
                    questionListAdapter.addItem(item)
                }
            }
            else{
                binding.emptyImg.visibility = View.VISIBLE
                binding.emptyTxt.visibility = View.VISIBLE
            }
            questionListAdapter.notifyDataSetChanged()
        })
    }

    override fun initAfterBinding() {
        questionListAdapter.setOnItemClickListener { _, a_position ->
            val item: Bookmark = questionListAdapter.getItem(a_position)
            var intent = Intent(this, DiaryActivity::class.java)
            intent.putExtra("cafeQuestionId", item.cafeQuestionId)
            startActivity(intent)
        }



        binding.searchKeyword.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                var search = binding.searchKeyword.text.toString()
                if(search != null){
                    viewModel.getBookamrk(search)
                    return@OnEditorActionListener true
                }
            }
            false
        })

        // coroutineScope search
//        search_keyword.addTextChangedListener(object : TextWatcher {
//            private var searchFor = ""
//            override fun afterTextChanged(s: Editable?) = Unit
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
//
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//                val searchText = s.toString().trim()
//                if (searchText == searchFor)
//                    return
//                searchFor = searchText
//                CoroutineScope(Dispatchers.IO + Job()).launch {
//                    delay(500)  //debounce timeOut
//                    if (searchText != searchFor)
//                        return@launch
//
//                    viewModel.getBookamrk(searchFor)
//                }
//            }
//        })

        binding.searchKeyword.addTextChangedListener(object : TextWatcher {
            private var searchFor = ""
            var job: Job? = null

            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val searchText = s.toString().trim()
                if (searchText == searchFor)
                    return

                job?.cancel()
                searchFor = searchText
                job = lifecycleScope.launch {
                    delay(500)  //debounce timeOut
                    if (searchText != searchFor)
                        return@launch

                    Log.d("lifecycle_scope", searchText.toString())

                    viewModel.getBookamrk(searchFor)
                }
            }
        })

        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}