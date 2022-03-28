package com.iame.qnnect.android.src.alarm.service

import com.iame.qnnect.android.src.alarm.model.GetAlarmDataModel
import com.iame.qnnect.android.src.alarm.model.GetAlarmListResponse
import com.iame.qnnect.android.src.allow.model.AlarmCheckDataModel
import com.iame.qnnect.android.src.allow.model.PatchAlarmCheckResponse
import io.reactivex.Single

class GetAlarmDataImpl(private val service: GetAlarmAPI) : GetAlarmDataModel {
    override fun getAlarm(): Single<List<GetAlarmListResponse>> {
        return service.getAlarm()
    }
}