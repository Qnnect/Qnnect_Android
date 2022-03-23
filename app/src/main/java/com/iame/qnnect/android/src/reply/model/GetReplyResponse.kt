package com.iame.qnnect.android.src.reply.model

import com.google.gson.annotations.SerializedName
import com.iame.qnnect.android.src.diary.model.Comments
import com.iame.qnnect.android.src.diary.model.QuestionMain

data class GetReplyResponse(
    @SerializedName("commentId") val commentId: Int,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("writerInfo") val writerInfo: Writer,
    @SerializedName("content") val content: String,
    @SerializedName("imageUrl1") val imageUrl1: String?,
    @SerializedName("imageUrl2") val imageUrl2: String?,
    @SerializedName("imageUrl3") val imageUrl3: String?,
    @SerializedName("imageUrl4") val imageUrl4: String?,
    @SerializedName("imageUrl5") val imageUrl5: String?,
    @SerializedName("replies") val replies: List<Replies>,
    @SerializedName("writer") val writer: Boolean
    )

// {
//    "commentId": 45,
//    "createdAt": "2022-03-20",
//    "writerInfo": {
//        "profileImage": "https://dev-qnnect-profile.s3.ap-northeast-2.amazonaws.com/profileDefault.png",
//        "nickName": "제제로짱",
//        "point": 8770,
//        "reportId": 7
//    },
//    "content": "ㅇ어어어엉어유융유유옹ㄹㅇㄴㄹㅁㅇㄹ",
//    "imageUrl1": "https://dev-qnnect-comment.s3.ap-northeast-2.amazonaws.com/2d1cc408-9670-4a13-8df1-d8e358631531_image1648015542",
//    "imageUrl2": null,
//    "imageUrl3": null,
//    "imageUrl4": null,
//    "imageUrl5": null,
//    "replies": [
//        {
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
//    ],
//    "writer": false
//}