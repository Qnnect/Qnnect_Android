package com.iame.qnnect.android.src.edit_alarm.service

import com.iame.qnnect.android.src.allow.model.AlarmCheckDataModel
import com.iame.qnnect.android.src.allow.model.PatchAlarmCheckResponse
import com.iame.qnnect.android.src.edit_alarm.model.GetUserAlarmStatusDataModel
import io.reactivex.Single

class GetUserAlarmStatusDataImpl(private val service: GetUserAlarmStatusAPI) : GetUserAlarmStatusDataModel {
    override fun getAlarmStatus(): Single<Boolean> {
        return service.getAlarmStatus()
    }
}