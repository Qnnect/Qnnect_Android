package com.americano.qnnect.kotlin.src.main.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.americano.qnnect.kotlin.MainSearchRecyclerViewAdapter
import com.americano.qnnect.kotlin.R
import com.americano.qnnect.kotlin.base.BaseActivity
import com.americano.qnnect.kotlin.base.BaseFragment
import com.americano.qnnect.kotlin.databinding.ActivityMainBinding
import com.americano.qnnect.kotlin.databinding.ActivityMainTwoBinding
import com.americano.qnnect.kotlin.databinding.FragmentHomeBinding
import com.americano.qnnect.kotlin.viewmodel.HomeViewModel
import com.americano.qnnect.kotlin.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main_two.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_home // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: HomeViewModel by viewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
    }

}