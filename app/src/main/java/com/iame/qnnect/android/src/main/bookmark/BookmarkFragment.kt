package com.iame.qnnect.android.src.main.bookmark

import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseFragment
import com.iame.qnnect.android.databinding.FragmentBookmarkBinding
import com.iame.qnnect.android.viewmodel.BookmarkViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarkFragment : BaseFragment<FragmentBookmarkBinding, BookmarkViewModel>(R.layout.fragment_bookmark) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_bookmark // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: BookmarkViewModel by viewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

}