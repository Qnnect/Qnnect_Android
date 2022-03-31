package com.iame.qnnect.android.src.alarm.service

import com.iame.qnnect.android.src.alarm.model.GetAlarmListResponse
import com.iame.qnnect.android.src.allow.model.PatchAlarmCheckResponse
import io.reactivex.Single
import retrofit2.http.*

interface ReadAlarmAPI {
    @PATCH("/api/v1/notification")
    fun readAlarm(@Query("notificationId") notificationId: Int) : Single<Unit>
}