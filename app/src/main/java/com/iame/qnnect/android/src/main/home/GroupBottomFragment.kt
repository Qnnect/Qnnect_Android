package com.iame.qnnect.android.src.main.home

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseFragment
import com.iame.qnnect.android.databinding.FragmentHomeBinding
import com.iame.qnnect.android.viewmodel.HomeViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.viewpager2.widget.ViewPager2
import com.iame.qnnect.android.databinding.FragmentGroupBottomBinding
import com.iame.qnnect.android.src.main.home.model.group_item
import com.iame.qnnect.android.src.main.home.model.question_item
import com.iame.qnnect.android.viewmodel.GroupBottomViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class GroupBottomFragment : BaseFragment<FragmentGroupBottomBinding, GroupBottomViewModel>(R.layout.fragment_group_bottom) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_group_bottom // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: GroupBottomViewModel by viewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

}