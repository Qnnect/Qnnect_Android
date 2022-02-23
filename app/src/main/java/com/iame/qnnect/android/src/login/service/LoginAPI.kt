package com.iame.qnnect.android.src.login.service

import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {
    @POST("/api/v1/auth/login")
    fun postLogin(@Body params: PostLoginRequest) : Single<PostLoginResponse>
}