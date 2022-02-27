package com.iame.qnnect.android.src.allow.service

import com.iame.qnnect.android.src.allow.model.AlarmCheckDataModel
import com.iame.qnnect.android.src.allow.model.PatchAlarmCheckResponse
import io.reactivex.Single

class AlarmCheckDataImpl(private val service: AlarmCheckAPI) : AlarmCheckDataModel {
    override fun patchAlarmCheck(enableNotification: Boolean): Single<Unit> {
        return service.patchAlarmCheck(enableNotification)
    }
}