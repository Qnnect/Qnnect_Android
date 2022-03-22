package com.iame.qnnect.android.src.alarm

import android.widget.CompoundButton
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityAlarmBinding
import com.iame.qnnect.android.databinding.ActivityEditAlarmBinding
import com.iame.qnnect.android.viewmodel.AlarmViewModel
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
    }

    override fun initAfterBinding() {
        back_btn.setOnClickListener {
            finish()
        }

        alarm_switch.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
                if(isChecked) {
                    //체크된 상태 취소 시 코드
                } else {
                    //체크된 상태로 만들 시 코드
                }
            }

        })
    }
}