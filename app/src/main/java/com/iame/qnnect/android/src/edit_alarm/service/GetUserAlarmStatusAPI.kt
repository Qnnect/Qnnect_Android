package com.iame.qnnect.android.src.edit_alarm.service

import com.iame.qnnect.android.src.allow.model.PatchAlarmCheckResponse
import io.reactivex.Single
import retrofit2.http.*

interface GetUserAlarmStatusAPI {
    @GET("/api/v1/user/enablenotification")
    fun getAlarmStatus() : Single<Boolean>
}