package com.iame.qnnect.android.src.main.bookmark

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseFragment
import com.iame.qnnect.android.databinding.FragmentBookmarkBinding
import com.iame.qnnect.android.src.diary.DiaryActivity
import com.iame.qnnect.android.src.main.bookmark.model.*
import com.iame.qnnect.android.src.main.home.GroupAdapter
import com.iame.qnnect.android.src.main.home.QuestionRecyclerViewAdapter
import com.iame.qnnect.android.src.main.home.ViewPagerAdapter
import com.iame.qnnect.android.src.main.home.model.group_item
import com.iame.qnnect.android.src.main.home.model.question_item
import com.iame.qnnect.android.src.reply.ReplyAdapter
import com.iame.qnnect.android.src.reply.model.Replies
import com.iame.qnnect.android.src.reply.reply_more.ReplyMoreBottomSheet
import com.iame.qnnect.android.src.search.SearchActivity
import com.iame.qnnect.android.viewmodel.BookmarkViewModel
import kotlinx.android.synthetic.main.activity_diary.*
import kotlinx.android.synthetic.main.fragment_bookmark.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarkFragment : BaseFragment<FragmentBookmarkBinding, BookmarkViewModel>(R.layout.fragment_bookmark) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_bookmark // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: BookmarkViewModel by viewModel()

    private val groupnameAdapter: GroupnameAdapter by inject()
    private val questionListAdapter: QuestionListAdapter by inject()

    var question_list = ArrayList<bookmark_question>()

    override fun initStartView() {
        // group list recycler
        boomark_group_recycler.run {
            adapter = groupnameAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            setHasFixedSize(true)
        }

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
        viewModel.cafesResponse.observe(this, Observer {
            var all = Cafe(-1, "전체")
            groupnameAdapter.addItem(all)
            it.forEach { item ->
                groupnameAdapter.addItem(item)
            }
            groupnameAdapter.notifyDataSetChanged()
        })

        viewModel.bookmarkResponse.observe(this, Observer {
            questionListAdapter.clear()

            if(it.size != 0){
                empty_img.visibility = View.GONE
                empty_txt.visibility = View.GONE
                it.forEach { item ->
                    questionListAdapter.addItem(item)
                }
                questionListAdapter.notifyDataSetChanged()
            }
            else{
                empty_img.visibility = View.VISIBLE
                empty_txt.visibility = View.VISIBLE
            }
            dismissLoadingDialog()
        })
    }

    override fun initAfterBinding() {
        // item click listener
        groupnameAdapter.setOnItemClickListener(object : GroupnameAdapter.OnItemClickListener {
            override fun onItemClick(v: View?, position: Int) {
                var request = groupnameAdapter.getItem(position)

                if(position == 0){
                    viewModel.getAllBookamrk()
                    showLoadingDialog(context!!)
                }
                else{
                    viewModel.getBookamrk(request.cafeId)
                    showLoadingDialog(context!!)
                }
            }
        })

        search_btn.setOnClickListener {
            var intent = Intent(context, SearchActivity::class.java)
            startActivity(intent)
        }

        questionListAdapter.setOnItemClickListener { a_view, a_position ->
            val item: Bookmark = questionListAdapter.getItem(a_position)
            var intent = Intent(context, DiaryActivity::class.java)
            intent.putExtra("cafeQuestionId", item.cafeQuestionId)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.getCafes()
        viewModel.getAllBookamrk()
        groupnameAdapter.clear()
        questionListAdapter.clear()

        showLoadingDialog(requireContext())
    }
}