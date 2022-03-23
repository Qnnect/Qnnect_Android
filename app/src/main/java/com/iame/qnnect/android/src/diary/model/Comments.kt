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

//"currentUserComment": {
//        "commentId": 94,
//        "createdAt": "2022-03-22"
//        "content": "test",
//        "imageUrl1": "https://dev-qnnect-comment.s3.ap-northeast-2.amazonaws.com/82037cb0-3f56-4589-b683-cdeae6af9b12_image1.jpg",
//        "imageUrl2": "https://dev-qnnect-comment.s3.ap-northeast-2.amazonaws.com/649eb48d-97ab-43ac-bc8e-0e542ba92afd_image2.jpg",
//        "imageUrl3": "https://dev-qnnect-comment.s3.ap-northeast-2.amazonaws.com/93d1662c-ee29-4265-8deb-2d8ae031bd39_image3.jpg",
//        "imageUrl4": "https://dev-qnnect-comment.s3.ap-northeast-2.amazonaws.com/a8559ee9-ae55-43db-8cb8-6932dba37bae_image4.jpg",
//        "imageUrl5": "https://dev-qnnect-comment.s3.ap-northeast-2.amazonaws.com/71b3a573-5da8-4df6-8a98-5a786c41bb6d_image5.jpg",
//        "replyCount": 0
//    }