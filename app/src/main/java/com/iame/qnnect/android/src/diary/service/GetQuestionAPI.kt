package com.iame.qnnect.android.src.diary.service

import com.iame.qnnect.android.src.diary.model.GetQuestionResponse
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single
import retrofit2.http.*

interface GetQuestionAPI {
    @GET("/api/v1/question/{cafeQuestionId}")
    fun getQuestion(@Path("cafeQuestionId") cafeQuestionId: Int) : Single<GetQuestionResponse>
}