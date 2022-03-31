package com.iame.qnnect.android.src.notarrivecafe.service

import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.notarrivecafe.model.NotArriveEditRequest
import io.reactivex.Single
import retrofit2.http.*

interface NotArriveDeleteAPI {
    @DELETE("/api/v1/my/question/{questionId}")
    fun deleteQuestion(@Path("questionId") questionId: Int) : Single<Unit>
}