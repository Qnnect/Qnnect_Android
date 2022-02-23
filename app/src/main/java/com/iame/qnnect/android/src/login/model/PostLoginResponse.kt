package com.iame.qnnect.android.src.login.model

import com.google.gson.annotations.SerializedName

data class PostLoginResponse(
    @SerializedName("accessToken") var accessToken: String,
    @SerializedName("isNewMember") var isNewMember: Boolean,
    @SerializedName("refreshToken") var refreshToken: String,
    @SerializedName("userSettingDone") var userSettingDone: Boolean
    )
//{
//    "accessToken": "string",
//    "isNewMember": true,
//    "refreshToken": "string"
//}