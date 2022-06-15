package com.iame.qnnect.android.src.empty

import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityAlarmBinding
import com.iame.qnnect.android.databinding.ActivityEmptyBinding
import com.iame.qnnect.android.viewmodel.AlarmViewModel
import com.iame.qnnect.android.viewmodel.EmptyViewModel
import kotlinx.android.synthetic.main.activity_empty.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EmptyActivity : BaseActivity<ActivityEmptyBinding, EmptyViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_empty // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: EmptyViewModel by viewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}