package com.iame.qnnect.android.src.question.model

import com.iame.qnnect.android.src.profile.model.PatchProfileResponse
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.Part

interface PostQuestionDataModel {
    fun getData(cafeId: Int, postQuestionRequest: PostQuestionRequest): Single<Int>
}
