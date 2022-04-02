package com.iame.qnnect.android.src.splash.service

import com.iame.qnnect.android.src.splash.model.PostRefreshRequest
import com.iame.qnnect.android.src.splash.model.PostRefreshResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface VersionCheckAPI {
    @GET("/api/v1/version")
    fun getVersionCheck(@Query("currentVersion") currentVersion: String,
                        @Query("osType") osType: String) : Single<Boolean>
}