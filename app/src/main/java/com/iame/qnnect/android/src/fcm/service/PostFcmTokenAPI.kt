package com.iame.qnnect.android.src.fcm.service

import com.iame.qnnect.android.src.splash.model.PostRefreshRequest
import com.iame.qnnect.android.src.splash.model.PostRefreshResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface PostFcmTokenAPI {
    @POST("/api/v1/notification/token")
    fun postFcmToken(@Query("fcmToken") fcmToken: String) : Single<Unit>
}