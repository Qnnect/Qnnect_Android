package com.iame.qnnect.android.src.login.model

import com.google.gson.annotations.SerializedName

data class PostLoginResponse(
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("isNewMember") val isNewMember: Boolean,
    @SerializedName("refreshToken") val refreshToken: String,
    @SerializedName("userSettingDone") val userSettingDone: Boolean
    )