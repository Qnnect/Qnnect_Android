package com.americano.qnnect.kotlin.src.main.store

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.americano.qnnect.kotlin.MainSearchRecyclerViewAdapter
import com.americano.qnnect.kotlin.R
import com.americano.qnnect.kotlin.base.BaseActivity
import com.americano.qnnect.kotlin.base.BaseFragment
import com.americano.qnnect.kotlin.databinding.*
import com.americano.qnnect.kotlin.viewmodel.BookmarkViewModel
import com.americano.qnnect.kotlin.viewmodel.HomeViewModel
import com.americano.qnnect.kotlin.viewmodel.MainViewModel
import com.americano.qnnect.kotlin.viewmodel.StoreViewModel
import kotlinx.android.synthetic.main.activity_main_two.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class StoreFragment : BaseFragment<FragmentStoreBinding, StoreViewModel>(R.layout.fragment_store) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_store // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: StoreViewModel by viewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

}