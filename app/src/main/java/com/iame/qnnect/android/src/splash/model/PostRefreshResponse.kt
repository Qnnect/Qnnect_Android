package com.iame.qnnect.android.src.splash.model

import com.google.gson.annotations.SerializedName

data class PostRefreshResponse(
    @SerializedName("accessToken") var accessToken: String,
    @SerializedName("refreshToken") var refreshToken: String
    )
//{
//    "accessToken": "string",
//    "refreshToken": "string"
//}