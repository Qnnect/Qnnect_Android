package com.iame.qnnect.android.src.profile.model

import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.Part

interface ProfileDataModel {
    fun getData(@Part profilePicture: MultipartBody.Part?, @Part nickName: MultipartBody.Part?): Single<PatchProfileResponse>
}

// data class PatchProfileRequest(
//    @SerializedName("profilePicture") val profilePicture: File,
//    @SerializedName("nickName") val nickName: String
//)
