package com.iame.qnnect.android.src.profile.model

import com.google.gson.annotations.SerializedName

data class PatchProfileResponse(
    @SerializedName("nickName") val nickName: String,
    @SerializedName("point") val point: Int,
    @SerializedName("profileImage") val profileImage: String
    )
//{
//    "nickName": "string",
//    "point": 0,
//    "profileImage": "string"
//}