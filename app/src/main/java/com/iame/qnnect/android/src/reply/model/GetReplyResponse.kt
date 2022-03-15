package com.iame.qnnect.android.src.reply.model

import com.google.gson.annotations.SerializedName
import com.iame.qnnect.android.src.diary.model.Comments
import com.iame.qnnect.android.src.diary.model.QuestionMain

data class GetReplyResponse(
    @SerializedName("commentId") var commentId: Int,
    @SerializedName("createdAt") var createdAt: String,
    @SerializedName("writerInfo") var writerInfo: Writer,
    @SerializedName("content") var content: String,
    @SerializedName("imageUrl1") var imageUrl1: String?,
    @SerializedName("imageUrl2") var imageUrl2: String?,
    @SerializedName("imageUrl3") var imageUrl3: String?,
    @SerializedName("imageUrl4") var imageUrl4: String?,
    @SerializedName("imageUrl5") var imageUrl5: String?,
    @SerializedName("replies") var replies: List<Replies>,
    @SerializedName("writer") var writer: Boolean
    )

// {
//    "commentId": 3,
//    "createdAt": "2022-03-09",
//    "writerInfo": {
//        "profileImage": "https://dev-qnnect-profile.s3.ap-northeast-2.amazonaws.com/profileDefault.png",
//        "nickName": "슈테른",
//        "point": 0
//    },
//    "content": "테스트 1",
//    "imageUrl1": null,
//    "imageUrl2": null,
//    "imageUrl3": "https://dev-qnnect-comment.s3.ap-northeast-2.amazonaws.com/cc1166bc-425f-41fb-b77e-d084a010b5bb_art1131.jpg",
//    "imageUrl4": null,
//    "imageUrl5": "https://dev-qnnect-comment.s3.ap-northeast-2.amazonaws.com/cdf664d4-d0d4-4615-9bc8-b9704b0d78cb_art1132.jpg",
//    "replies": [
//        {
//            "replyId": 8,
//            "writerInfo": {
//                "profileImage": "https://dev-qnnect-profile.s3.ap-northeast-2.amazonaws.com/profileDefault.png",
//                "nickName": "슈테른",
//                "point": 0
//            },
//            "content": "가나다",
//            "writer": false,
//            "createdAt": "2022-03-14"
//        },
//        {
//            "replyId": 13,
//            "writerInfo": {
//                "profileImage": "https://dev-qnnect-profile.s3.ap-northeast-2.amazonaws.com/703ecd40-04e4-4a23-b78f-5433674befcd_photo.jpg",
//                "nickName": "두루",
//                "point": 45
//            },
//            "content": "\"test\"",
//            "writer": true,
//            "createdAt": "2022-03-14"
//        }
//    ],
//    "writer": false
//}