package com.iame.qnnect.android.src.fcm.model

import com.iame.qnnect.android.model.enum.KakaoSearchSortEnum
import com.iame.qnnect.android.model.response.ImageSearchResponse
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.splash.model.PostRefreshRequest
import com.iame.qnnect.android.src.splash.model.PostRefreshResponse
import io.reactivex.Single

interface PostFcmTokenDataModel {
    fun postFcmToken(fcmToken: String): Single<Unit>
}

