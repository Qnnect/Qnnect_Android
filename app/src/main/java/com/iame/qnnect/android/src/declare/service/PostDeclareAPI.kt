package com.iame.qnnect.android.src.declare.service

import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single
import retrofit2.http.*

interface PostDeclareAPI {
    @POST("/api/v1/user/report")
    fun postDeclare(@Query("reportId") reportId: Int) : Single<Unit>
}