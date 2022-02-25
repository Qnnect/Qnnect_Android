package com.iame.qnnect.android.src.splash.service

import com.iame.qnnect.android.src.splash.model.PostRefreshRequest
import com.iame.qnnect.android.src.splash.model.PostRefreshResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface RefreshAPI {
    @POST("/api/v1/auth/reissue")
    fun postRefresh(@Body params: PostRefreshRequest) : Single<PostRefreshResponse>
}