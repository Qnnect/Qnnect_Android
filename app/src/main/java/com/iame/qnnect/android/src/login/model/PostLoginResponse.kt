package com.iame.qnnect.android.src.login.model

import com.google.gson.annotations.SerializedName

data class PostLoginResponse(
    @SerializedName("accessToken") var accessToken: String,
    @SerializedName("isNewMember") var loginType: Boolean,
    @SerializedName("refreshToken") var refreshToken: String
    )
//{
//    "accessToken": "string",
//    "isNewMember": true,
//    "refreshToken": "string"
//}