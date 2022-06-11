package com.iame.qnnect.android.src.edit_alarm

import androidx.lifecycle.Observer
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityEditAlarmBinding
import com.iame.qnnect.android.viewmodel.EditAlarmViewModel
import kotlinx.android.synthetic.main.activity_edit_alarm.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditAlarmActivity : BaseActivity<ActivityEditAlarmBinding, EditAlarmViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_edit_alarm // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: EditAlarmViewModel by viewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {
        viewModel.alarmstatusResponse.observe(this, Observer {
            binding.alarmSwitch.isChecked = it
        })
        viewModel.alarmCheckResponse.observe(this, Observer {
            dismissLoadingDialog()
        })
    }

    override fun initAfterBinding() {
        viewModel.getUserAlarmStatus()

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.alarmSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // 체크시
                viewModel.patchAlarmCheck(true)
                showLoadingDialog(this@EditAlarmActivity)
            } else {
                // 체크 취소
                viewModel.patchAlarmCheck(false)
                showLoadingDialog(this@EditAlarmActivity)
            }
        }
    }
}