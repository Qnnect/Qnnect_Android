package com.iame.qnnect.android.src.allow.service

import com.iame.qnnect.android.src.allow.model.AlarmCheckDataModel
import com.iame.qnnect.android.src.allow.model.PatchAlarmCheckResponse
import io.reactivex.Single

class AlarmCheckDataImpl(private val service: AlarmCheckAPI) : AlarmCheckDataModel {
    override fun patchAlarmCheck(header: String, enableNotification: Boolean): Single<PatchAlarmCheckResponse> {
        return service.patchAlarmCheck(header, enableNotification)
    }
}