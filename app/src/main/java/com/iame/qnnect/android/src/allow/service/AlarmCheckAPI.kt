package com.iame.qnnect.android.src.allow.service

import com.iame.qnnect.android.src.allow.model.PatchAlarmCheckResponse
import io.reactivex.Single
import retrofit2.http.*

interface AlarmCheckAPI {
    @PATCH("/api/v1/user/enablenotification")
    fun patchAlarmCheck(
        @Header("Authorization") auth: String,
        @Query("enableNotification") enableNotification: Boolean,
    ) : Single<PatchAlarmCheckResponse>
}