package com.iame.qnnect.android.src.allow.model

import com.google.gson.annotations.SerializedName

data class PatchAlarmCheckRequest(
    @SerializedName("enableNotification") val enableNotification: Boolean
)

//{
//    "accessToken": "string",
//    "refreshToken": "string"
//}