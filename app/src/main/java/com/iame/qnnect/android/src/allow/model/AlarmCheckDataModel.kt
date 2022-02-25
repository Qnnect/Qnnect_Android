package com.iame.qnnect.android.src.allow.model

import io.reactivex.Single

interface AlarmCheckDataModel {
    fun patchAlarmCheck(header: String, enableNotification: Boolean): Single<PatchAlarmCheckResponse>
}

