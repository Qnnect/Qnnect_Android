package com.iame.qnnect.android.src.question.service

import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupRequest
import com.iame.qnnect.android.src.profile.model.PatchProfileResponse
import com.iame.qnnect.android.src.question.model.PostQuestionRequest
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.*

interface PostQuestionAPI {
    @POST("/api/v1/cafes/{cafeId}/question/")
    fun postQuestion(@Path("cafeId") cafeId: Int, @Body content: String) : Single<Unit>
}