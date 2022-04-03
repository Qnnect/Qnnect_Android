package com.iame.qnnect.android.src.fcm.service

import com.iame.qnnect.android.src.fcm.model.PostFcmTokenDataModel
import com.iame.qnnect.android.src.splash.model.PostRefreshRequest
import com.iame.qnnect.android.src.splash.model.PostRefreshResponse
import com.iame.qnnect.android.src.splash.model.RefreshDataModel
import io.reactivex.Single

class PostFcmTokenDataImpl(private val service: PostFcmTokenAPI) : PostFcmTokenDataModel {
    override fun postFcmToken(fcmToken: String): Single<Unit> {
        return service.postFcmToken(fcmToken)
    }
}