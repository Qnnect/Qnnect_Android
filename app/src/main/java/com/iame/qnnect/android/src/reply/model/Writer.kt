package com.iame.qnnect.android.src.reply.model

import com.google.gson.annotations.SerializedName
import com.iame.qnnect.android.src.profile.model.PatchProfileResponse
import com.kakao.sdk.user.model.User

data class Writer(
    @SerializedName("nickName") val nickName: String,
    @SerializedName("point") val point: Int,
    @SerializedName("profileImage") val profileImage: String,
    @SerializedName("reportId") val reportId: Int
    )

//{
//        "profileImage": "https://dev-qnnect-profile.s3.ap-northeast-2.amazonaws.com/profileDefault.png",
//        "nickName": "제제로짱",
//        "point": 8770,
//        "reportId": 7
//    }