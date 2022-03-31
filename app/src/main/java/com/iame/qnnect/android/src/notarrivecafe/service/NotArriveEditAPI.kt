package com.iame.qnnect.android.src.notarrivecafe.service

import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.notarrivecafe.model.NotArriveEditRequest
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface NotArriveEditAPI {
    @PATCH("/api/v1/my/question/{questionId}")
    fun patchQuestion(@Path("questionId") questionId: Int, @Body params: NotArriveEditRequest) : Single<Unit>
}