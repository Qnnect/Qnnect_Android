package com.iame.qnnect.android.src.main.store

import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseFragment
import com.iame.qnnect.android.databinding.*
import com.iame.qnnect.android.viewmodel.StoreViewModel
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