package com.iame.qnnect.android.src.reply.model

import androidx.annotation.RequiresPermission
import com.google.gson.annotations.SerializedName
import com.iame.qnnect.android.src.profile.model.PatchProfileResponse
import com.kakao.sdk.user.model.User

data class Replies(
    @SerializedName("content") var content: String,
    @SerializedName("createdAt") var createdAt: String,
    @SerializedName("replyId") var replyId: Int,
    @SerializedName("writer") var writer: Writer
    )

// "replies": [
////    {
////      "content": "string",
////      "createdAt": "2022-03-14",
////      "replyId": 0,
////      "writer": {
////        "nickName": "string",
////        "point": 0,
////        "profileImage": "string"
////      }
////    }
////  ],