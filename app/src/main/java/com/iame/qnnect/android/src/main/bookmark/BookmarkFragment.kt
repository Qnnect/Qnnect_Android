package com.iame.qnnect.android.src.main.bookmark

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseFragment
import com.iame.qnnect.android.databinding.FragmentBookmarkBinding
import com.iame.qnnect.android.src.main.bookmark.model.bookmark_group
import com.iame.qnnect.android.src.main.bookmark.model.bookmark_question
import com.iame.qnnect.android.src.main.home.GroupAdapter
import com.iame.qnnect.android.src.main.home.ViewPagerAdapter
import com.iame.qnnect.android.src.main.home.model.group_item
import com.iame.qnnect.android.src.main.home.model.question_item
import com.iame.qnnect.android.viewmodel.BookmarkViewModel
import kotlinx.android.synthetic.main.fragment_bookmark.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.group_recycler
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarkFragment : BaseFragment<FragmentBookmarkBinding, BookmarkViewModel>(R.layout.fragment_bookmark) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_bookmark // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: BookmarkViewModel by viewModel()

    var group_list = ArrayList<bookmark_group>()
    var question_list = ArrayList<bookmark_question>()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
        var group_item1= bookmark_group("전체")
        group_list.add(group_item1)
        var group_item2= bookmark_group("아아메")
        group_list.add(group_item2)
        var group_item3= bookmark_group("틴틴")
        group_list.add(group_item3)
        var group_item4= bookmark_group("Light One")
        group_list.add(group_item4)
        var group_item5= bookmark_group("어바웃타임 & 토핑")
        group_list.add(group_item5)

        group_recycler.run {
            adapter = GroupnameAdapter(group_list)
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            setHasFixedSize(true)
        }

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

}