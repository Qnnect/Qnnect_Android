package com.iame.qnnect.android.src.alarm

import android.view.View
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
        var item1= Alarm(false,1, "신사고 5인방",
            "질문이 도착했습니다: 함께 가장 가고싶은 여행지는 어디인가요?ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ...", "22.12.22")
        var item2= Alarm(false,2, "신사고 5인방",
            "내 질문에 000님이 답글을 남겼습니다: 함께 가장 가고싶은 여행지는 어디인가요?ㅇㅇㅇ...", "22.12.22")
        var item3= Alarm(false,3, "신사고 5인방",
            "내 질문에 000님이 답글을 남겼습니다: 함께 가장 가고싶은 여행지는 어디인가요?ㅇㅇㅇ...", "22.12.22")
        var item4= Alarm(true,1, "신사고 5인방",
            "질문이 도착했습니다: 함께 가장 가고싶은 여행지는 어디인가요?ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ...", "22.12.22")
        var item5= Alarm(true,2, "신사고 5인방",
            "질문이 도착했습니다: 함께 가장 가고싶은 여행지는 어디인가요?ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ...", "22.12.22")
        var item6= Alarm(true,3, "신사고 5인방",
            "내 답변에 000님이 댓글을 달았습니다: 함께 가장 가고싶은 여행지는 어디인가요?ㅇㅇㅇ...", "22.12.22")
        alarmAdapter.addItem(item1)
        alarmAdapter.addItem(item2)
        alarmAdapter.addItem(item3)
        alarmAdapter.addItem(item4)
        alarmAdapter.addItem(item5)
        alarmAdapter.addItem(item6)
        alarmAdapter.addItem(item1)
        alarmAdapter.addItem(item2)
        alarmAdapter.addItem(item3)
        alarmAdapter.addItem(item4)
        alarmAdapter.addItem(item5)
        alarmAdapter.addItem(item6)
        alarmAdapter.notifyDataSetChanged()

        if(alarmAdapter.itemCount != 0){
            empty_img.visibility = View.GONE
            empty_txt.visibility = View.GONE
        }
        else{
            empty_img.visibility = View.VISIBLE
            empty_txt.visibility = View.VISIBLE
        }

//        viewModel.alarmResponse.observe(this, Observer {
//            alarmAdapter.clear()
//            it.forEach { item ->
//                Log.d("reply_response_count", item.toString())
//                alarmAdapter.addItem(item)
//            }
//        })

    }

    override fun onResume() {
        super.onResume()
//        viewModel.getAlarm()
    }

    override fun initAfterBinding() {
        back_btn.setOnClickListener {
            finish()
        }

        // more
        alarmAdapter.setOnItemClickListener(object : AlarmAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                val item: Alarm = alarmAdapter.getItem(a_position)
            }
        })
    }
}