package com.iame.qnnect.android.src.alarm

import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityAlarmBinding
import com.iame.qnnect.android.viewmodel.AlarmViewModel
import kotlinx.android.synthetic.main.activity_alarm.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class AlarmActivity : BaseActivity<ActivityAlarmBinding, AlarmViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_alarm // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: AlarmViewModel by viewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
        back_btn.setOnClickListener {
            finish()
        }
    }
}