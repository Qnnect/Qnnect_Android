package com.iame.qnnect.android.src.login.model

import com.google.gson.annotations.SerializedName

data class PostLoginRequest(
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("loginType") val loginType: String
)