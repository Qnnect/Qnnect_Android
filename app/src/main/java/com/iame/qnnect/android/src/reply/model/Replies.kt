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

//{
//            "replyId": 122,
//            "writerInfo": {
//                "profileImage": "https://dev-qnnect-profile.s3.ap-northeast-2.amazonaws.com/703ecd40-04e4-4a23-b78f-5433674befcd_photo.jpg",
//                "nickName": "두루",
//                "point": 10000,
//                "reportId": 8
//            },
//            "content": "declare_test",
//            "writer": true,
//            "createdAt": "2022-03-23"
//        }