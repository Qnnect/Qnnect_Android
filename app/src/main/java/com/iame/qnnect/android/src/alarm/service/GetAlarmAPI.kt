package com.iame.qnnect.android.src.alarm.service

import com.iame.qnnect.android.src.alarm.model.GetAlarmListResponse
import com.iame.qnnect.android.src.allow.model.PatchAlarmCheckResponse
import io.reactivex.Single
import retrofit2.http.*

interface GetAlarmAPI {
    @GET("/api/v1/notification")
    fun getAlarm() : Single<List<GetAlarmListResponse>>
}