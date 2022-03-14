package com.iame.qnnect.android.src.reply.model

import com.google.gson.annotations.SerializedName
import com.iame.qnnect.android.src.profile.model.PatchProfileResponse
import com.kakao.sdk.user.model.User

data class Writer(
    @SerializedName("nickName") var nickName: String,
    @SerializedName("point") var point: Int,
    @SerializedName("profileImage") var profileImage: String
    )

////      "writer": {
////        "nickName": "string",
////        "point": 0,
////        "profileImage": "string"
////      }