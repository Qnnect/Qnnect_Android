package com.iame.qnnect.android.src.alarm

import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityAlarmBinding
import com.iame.qnnect.android.src.alarm.model.Alarm
import com.iame.qnnect.android.viewmodel.AlarmViewModel
import kotlinx.android.synthetic.main.activity_alarm.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlarmActivity : BaseActivity<ActivityAlarmBinding, AlarmViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_alarm // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: AlarmViewModel by viewModel()
    private val alarmAdapter: AlarmAdapter by inject()

    override fun initStartView() {
        // alarm recycler
        alarm_recycler.run {
            adapter = alarmAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {

        if(alarmAdapter.itemCount != 0){
            empty_img.visibility = View.VISIBLE
            empty_txt.visibility = View.VISIBLE
        }
        else{
            empty_img.visibility = View.GONE
            empty_txt.visibility = View.GONE
        }

        viewModel.alarmResponse.observe(this, Observer {
            alarmAdapter.clear()
            it.forEach { item ->
                Log.d("reply_response_count", item.toString())
                alarmAdapter.addItem(item)
            }
            alarmAdapter.notifyDataSetChanged()
        })

    }

    override fun onResume() {
        super.onResume()
        viewModel.getAlarm()
    }

    override fun initAfterBinding() {
        back_btn.setOnClickListener {
            finish()
        }

        // more
        alarmAdapter.setOnItemClickListener(object : AlarmAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
//                val item: Alarm = alarmAdapter.getItem(a_position)
            }
        })
    }
}