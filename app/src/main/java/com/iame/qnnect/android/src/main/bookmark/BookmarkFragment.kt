package com.iame.qnnect.android.src.main.bookmark

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseFragment
import com.iame.qnnect.android.databinding.FragmentBookmarkBinding
import com.iame.qnnect.android.src.main.bookmark.model.Cafe
import com.iame.qnnect.android.src.main.bookmark.model.GetCafeListResponse
import com.iame.qnnect.android.src.main.bookmark.model.bookmark_group
import com.iame.qnnect.android.src.main.bookmark.model.bookmark_question
import com.iame.qnnect.android.src.main.home.GroupAdapter
import com.iame.qnnect.android.src.main.home.QuestionRecyclerViewAdapter
import com.iame.qnnect.android.src.main.home.ViewPagerAdapter
import com.iame.qnnect.android.src.main.home.model.group_item
import com.iame.qnnect.android.src.main.home.model.question_item
import com.iame.qnnect.android.viewmodel.BookmarkViewModel
import kotlinx.android.synthetic.main.fragment_bookmark.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarkFragment : BaseFragment<FragmentBookmarkBinding, BookmarkViewModel>(R.layout.fragment_bookmark) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_bookmark // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: BookmarkViewModel by viewModel()

    private val groupnameAdapter: GroupnameAdapter by inject()

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
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {

        var question_item1 = bookmark_question("#1", "금요일에 맛있는거 사주세요", "2022.02.25")
        question_list.add(question_item1)
        var question_item2 = bookmark_question("#2", "전 Bhc 뿌링클이요", "2022.02.25")
        question_list.add(question_item2)
        var question_item3 = bookmark_question("#3", "고기 맛집 갑시다", "2022.02.25")
        question_list.add(question_item3)
        var question_item4 = bookmark_question("#4", "전 그냥 아무거나 사주세요", "2022.02.25")
        question_list.add(question_item4)
        var question_item5 = bookmark_question("#5", "맛있는거면 뭐든 상관없어요", "2022.02.25")
        question_list.add(question_item5)
        var question_item6 = bookmark_question("#6", "비싼거요 무조건", "2022.02.25")
        question_list.add(question_item6)
        var question_item7 = bookmark_question("#7", "전 갑자기 집에서 코로나 확진자가...", "2022.02.25")
        question_list.add(question_item7)
        var question_item8 = bookmark_question("#8", "제제로가 먹고 싶은거 먹으러 가요", "2022.02.25")
        question_list.add(question_item8)
        var question_item9 = bookmark_question("#9", "그냥 아무거나 먹어요 배고파요", "2022.02.25")
        question_list.add(question_item9)
        var question_item10 = bookmark_question("#10", "ㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ", "2022.02.25")
        question_list.add(question_item10)
        var question_item11 = bookmark_question("#11", "아무거나", "2022.02.25")
        question_list.add(question_item11)

        question_recycler.run {
            adapter = QuestionListAdapter(question_list)
            layoutManager = LinearLayoutManager(context)
           setHasFixedSize(true)
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.getCafes()

        viewModel.cafesResponse.observe(this, Observer {
            Log.d("login_response", it.toString())
            it.forEach { item ->
                groupnameAdapter.addItem(item)
            }

            groupnameAdapter.notifyDataSetChanged()
        })
    }

}