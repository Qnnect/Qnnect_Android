package com.iame.qnnect.android.src.reply.model

import com.google.gson.annotations.SerializedName
import com.iame.qnnect.android.src.profile.model.PatchProfileResponse
import com.kakao.sdk.user.model.User

data class Writer(
    @SerializedName("nickName") val nickName: String,
    @SerializedName("point") val point: Int,
    @SerializedName("profileImage") val profileImage: String
    )

////      "writer": {
////        "nickName": "string",
////        "point": 0,
////        "profileImage": "string"
////      }