package com.iame.qnnect.android.src.main.home.home_service

import com.iame.qnnect.android.src.group.model.GetGroupResponse
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.main.home.home_model.GetHomeResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface HomeAPI {
    @GET("/api/v1/home")
    fun patchHome() : Single<GetHomeResponse>
}