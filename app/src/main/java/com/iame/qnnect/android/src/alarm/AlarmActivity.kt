package com.iame.qnnect.android.src.alarm

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityAlarmBinding
import com.iame.qnnect.android.src.alarm.model.Alarm
import com.iame.qnnect.android.src.alarm.model.GetAlarmListResponse
import com.iame.qnnect.android.src.diary.DiaryActivity
import com.iame.qnnect.android.src.reply.ReplyActivity
import com.iame.qnnect.android.viewmodel.AlarmViewModel
import kotlinx.android.synthetic.main.activity_alarm.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlarmActivity : BaseActivity<ActivityAlarmBinding, AlarmViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_alarm // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: AlarmViewModel by viewModel()
    private val alarmAdapter: AlarmAdapter by inject()

    var contentId = 0
    var notificationType = ""

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

        // comment, question, reply
        viewModel.readResponse.observe(this, Observer {
            dismissLoadingDialog()
            if(notificationType == "question"){
                var intent = Intent(this, DiaryActivity::class.java)
                intent.putExtra("cafeQuestionId", contentId)
                startActivity(intent)
            }
            else{
                var intent = Intent(this, ReplyActivity::class.java)
                intent.putExtra("commentId", contentId)
                startActivity(intent)
            }
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
                val item: GetAlarmListResponse = alarmAdapter.getItem(a_position)
                if(!item.userRead){
                    notificationType = item.notificationType
                    contentId = item.contentId
                    viewModel.readAlarm(item.notificationId)
                    showLoadingDialog(this@AlarmActivity)
                }
                else{
                    if(item.notificationType == "question"){
                        var intent = Intent(this@AlarmActivity, DiaryActivity::class.java)
                        intent.putExtra("cafeQuestionId", item.contentId)
                        startActivity(intent)
                    }
                    else{
                        var intent = Intent(this@AlarmActivity, ReplyActivity::class.java)
                        intent.putExtra("commentId", item.contentId)
                        startActivity(intent)
                    }
                }
            }
        })
    }
}