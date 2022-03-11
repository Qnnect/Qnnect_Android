package com.iame.qnnect.android.src.answer.service

import com.iame.qnnect.android.src.profile.model.PatchProfileResponse
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.*

interface PostAnswerAPI {
    @Multipart
    @POST("/api/v1/cafes/{cafeId}/questions/{cafeQuestionId}/comments")
    fun postAnswer(@Part image5: MultipartBody.Part?,
                     @Part image4: MultipartBody.Part?,
                     @Part image3: MultipartBody.Part?,
                     @Part image2: MultipartBody.Part?,
                     @Part image1: MultipartBody.Part?,
                     @Part content: MultipartBody.Part?,
                     @Path("cafeId") cafeId: Int,
                     @Path("cafeQuestionId") cafeQuestionId: Int
    ) : Single<Int>
}

// // data class PatchProfileRequest(
////    @SerializedName("profilePicture") val profilePicture: File,
////    @SerializedName("nickName") val nickName: String
////)