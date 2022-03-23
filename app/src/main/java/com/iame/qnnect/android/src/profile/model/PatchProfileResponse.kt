package com.iame.qnnect.android.src.profile.model

import com.google.gson.annotations.SerializedName

data class PatchProfileResponse(
    @SerializedName("nickName") val nickName: String,
    @SerializedName("point") val point: Int,
    @SerializedName("profileImage") val profileImage: String,
    @SerializedName("reportId") val reportId: Int
    )

//"profileResponse": {
//            "profileImage": "https://dev-qnnect-profile.s3.ap-northeast-2.amazonaws.com/703ecd40-04e4-4a23-b78f-5433674befcd_photo.jpg",
//            "nickName": "두루",
//            "point": 10000,
//            "reportId": 8
//        }