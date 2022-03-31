package com.iame.qnnect.android.src.alarm.service

import com.iame.qnnect.android.src.alarm.model.GetAlarmDataModel
import com.iame.qnnect.android.src.alarm.model.GetAlarmListResponse
import com.iame.qnnect.android.src.alarm.model.ReadAlarmDataModel
import com.iame.qnnect.android.src.allow.model.AlarmCheckDataModel
import com.iame.qnnect.android.src.allow.model.PatchAlarmCheckResponse
import io.reactivex.Single

class ReadAlarmDataImpl(private val service: ReadAlarmAPI) : ReadAlarmDataModel {
    override fun getAlarm(notificationId: Int): Single<Unit> {
        return service.readAlarm(notificationId)
    }
}