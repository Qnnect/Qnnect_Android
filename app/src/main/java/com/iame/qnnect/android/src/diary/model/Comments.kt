package com.iame.qnnect.android.src.diary.model

import com.google.gson.annotations.SerializedName
import com.iame.qnnect.android.src.profile.model.PatchProfileResponse
import com.kakao.sdk.user.model.User

data class Comments(
    @SerializedName("commentId") val commentId: Int,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("profileResponse") val profileResponse: PatchProfileResponse,
    @SerializedName("content") val content: String,
    @SerializedName("imageUrl1") val imageUrl1: String,
    @SerializedName("imageUrl2") val imageUrl2: String,
    @SerializedName("imageUrl3") val imageUrl3: String,
    @SerializedName("imageUrl4") val imageUrl4: String,
    @SerializedName("imageUrl5") val imageUrl5: String,
    @SerializedName("replyCount") val replyCount: Int
    )

// //  {
////            "commentId": 2,
////            "createdAt": "2022-03-09",
////            "profileResponse": {
////                "profileImage": "http://k.kakaocdn.net/dn/bixrOh/btrtYVKqr0n/wEtDnraXxBzeUO42WvBGVk/img_640x640.jpg",
////                "nickName": "슈테른",
////                "point": 10
////            },
////            "content": "가나다라",
////            "imageUrl1": null,
////            "imageUrl2": null,
////            "imageUrl3": "https://dev-qnnect-profile.s3.ap-northeast-2.amazonaws.com/52ace56d-28b1-4fe1-aa4d-958db97916ee_art1132.jpg",
////            "imageUrl4": null,
////            "imageUrl5": "https://dev-qnnect-profile.s3.ap-northeast-2.amazonaws.com/999fd635-ce01-4ed7-9e51-2bb9594d269e_art1131.jpg",
////            "replyCount": 2
////        }