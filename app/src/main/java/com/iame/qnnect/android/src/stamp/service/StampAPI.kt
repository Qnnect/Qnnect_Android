package com.iame.qnnect.android.src.stamp.service

import com.iame.qnnect.android.src.splash.model.PostRefreshRequest
import com.iame.qnnect.android.src.splash.model.PostRefreshResponse
import com.iame.qnnect.android.src.stamp.model.Stamp
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface StampAPI {
    @GET("/api/v1/user/stamp")
    fun getStamp() : Single<List<Stamp>>
}