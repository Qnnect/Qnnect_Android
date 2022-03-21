package com.iame.qnnect.android.src.splash.model

import com.google.gson.annotations.SerializedName

data class PostRefreshResponse(
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("refreshToken") val refreshToken: String
    )
//{
//    "accessToken": "string",
//    "refreshToken": "string"
//}