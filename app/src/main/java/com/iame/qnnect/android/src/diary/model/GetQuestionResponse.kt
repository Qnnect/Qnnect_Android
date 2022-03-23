package com.iame.qnnect.android.src.diary.model

import com.google.gson.annotations.SerializedName

data class GetQuestionResponse(
    @SerializedName("questionMainResponse") val questionMainResponse: QuestionMain,
    @SerializedName("currentUserComment") val currentUserComment: Comments,
    @SerializedName("comments") val comments: List<Comments>,
    @SerializedName("scraped") val scraped: Boolean,
    @SerializedName("liked") val liked: Boolean
    )

// {
//    "comments": [
//        {
//            "commentId": 45,
//            "createdAt": "2022-03-20",
//            "profileResponse": {
//                "profileImage": "https://dev-qnnect-profile.s3.ap-northeast-2.amazonaws.com/profileDefault.png",
//                "nickName": "제제로짱",
//                "point": 8770,
//                "reportId": 7
//            },
//            "content": "ㅇ어어어엉어유융유유옹ㄹㅇㄴㄹㅁㅇㄹ",
//            "imageUrl1": "https://dev-qnnect-comment.s3.ap-northeast-2.amazonaws.com/2d1cc408-9670-4a13-8df1-d8e358631531_image1648015542",
//            "imageUrl2": null,
//            "imageUrl3": null,
//            "imageUrl4": null,
//            "imageUrl5": null,
//            "replyCount": 1
//        },
//        {
//            "commentId": 93,
//            "createdAt": "2022-03-22",
//            "profileResponse": {
//                "profileImage": "https://dev-qnnect-profile.s3.ap-northeast-2.amazonaws.com/profileDefault.png",
//                "nickName": "슈테른",
//                "point": 795,
//                "reportId": 3
//            },
//            "content": "abc",
//            "imageUrl1": null,
//            "imageUrl2": null,
//            "imageUrl3": null,
//            "imageUrl4": null,
//            "imageUrl5": null,
//            "replyCount": 0
//        }
//    ],
//    "liked": false,
//    "scraped": true
//}