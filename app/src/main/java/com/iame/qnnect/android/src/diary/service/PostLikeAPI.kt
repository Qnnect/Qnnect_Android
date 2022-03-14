package com.iame.qnnect.android.src.diary.service

import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PostLikeAPI {
    @POST("/api/v1/users/question/{cafeQuestionId}")
    fun postLike(@Path("cafeQuestionId") cafeQuestionId: Int,
                 @Query("isUserLiked") isUserLiked: Boolean) : Single<Unit>
}