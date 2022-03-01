package com.iame.qnnect.android.src.profile.model

import com.google.gson.annotations.SerializedName

data class PatchProfileResponse(
    @SerializedName("nickName") var nickName: String,
    @SerializedName("point") var point: Int,
    @SerializedName("profileImage") var profileImage: String
    )
//{
//    "nickName": "string",
//    "point": 0,
//    "profileImage": "string"
//}