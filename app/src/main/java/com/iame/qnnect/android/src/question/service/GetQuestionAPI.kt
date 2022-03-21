package com.iame.qnnect.android.src.question.service

import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupRequest
import com.iame.qnnect.android.src.profile.model.PatchProfileResponse
import com.iame.qnnect.android.src.question.model.GetQuestionResponse
import com.iame.qnnect.android.src.question.model.PostQuestionRequest
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.*

interface GetQuestionAPI {
    @GET("/api/v1/question/cafes/{cafeId}/all")
    fun getQuestion(@Path("cafeId") cafeId: Int) : Single<GetQuestionResponse>
}