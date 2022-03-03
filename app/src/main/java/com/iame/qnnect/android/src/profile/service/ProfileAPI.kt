package com.iame.qnnect.android.src.profile.service

import com.iame.qnnect.android.src.profile.model.PatchProfileResponse
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.Part

interface ProfileAPI {
    @Multipart
    @PATCH("/api/v1/user/profile")
    fun patchProfile(@Part profilePicture: MultipartBody.Part?, @Part nickName: MultipartBody.Part?) : Single<PatchProfileResponse>
}

// // data class PatchProfileRequest(
////    @SerializedName("profilePicture") val profilePicture: File,
////    @SerializedName("nickName") val nickName: String
////)