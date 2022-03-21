package com.iame.qnnect.android.src.reply.model

import androidx.annotation.RequiresPermission
import com.google.gson.annotations.SerializedName
import com.iame.qnnect.android.src.profile.model.PatchProfileResponse
import com.kakao.sdk.user.model.User

data class Replies(
    @SerializedName("replyId") val replyId: Int,
    @SerializedName("writerInfo") val writerInfo: Writer,
    @SerializedName("content") val content: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("writer") val writer: Boolean
    )

//  {
////            "replyId": 6,
////            "writerInfo": {
////                "profileImage": "https://dev-qnnect-profile.s3.ap-northeast-2.amazonaws.com/profileDefault.png",
////                "nickName": "슈테른",
////                "point": 0
////            },
////            "content": "{}나도 모르겠다아",
////            "writer": false,
////            "createdAt": "2022-03-11"
////        },