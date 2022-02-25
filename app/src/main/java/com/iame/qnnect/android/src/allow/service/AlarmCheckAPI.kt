package com.iame.qnnect.android.src.allow.service

import com.iame.qnnect.android.src.allow.model.PatchAlarmCheckResponse
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.splash.model.PostRefreshRequest
import com.iame.qnnect.android.src.splash.model.PostRefreshResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface AlarmCheckAPI {
    @POST("/api/v1/user/enablenotification")
    fun patchAlarmCheck(
        @Header("Authorization") auth: String,
        @Query("enableNotification") enableNotification: Boolean,
    ) : Single<PatchAlarmCheckResponse>
}

// interface LoginAPI {
//    @POST("/api/v1/auth/login")
//    fun postLogin(@Body params: PostLoginRequest) : Single<PostLoginResponse>
//}