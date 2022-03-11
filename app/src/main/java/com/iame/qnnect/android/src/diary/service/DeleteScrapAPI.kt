package com.iame.qnnect.android.src.diary.service

import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Query

interface DeleteScrapAPI {
    @DELETE("/api/v1/users/scrap")
    fun deleteScrap(@Query("cafeQuestionId") cafeQuestionId: Int) : Single<Unit>
}