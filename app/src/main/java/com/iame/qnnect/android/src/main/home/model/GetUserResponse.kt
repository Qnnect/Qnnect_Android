package com.iame.qnnect.android.src.main.home.model

import com.google.gson.annotations.SerializedName

data class GetUserResponse(
    @SerializedName("nickName") var nickName: String,
    @SerializedName("point") var point: Int,
    @SerializedName("profileImage") var profileImage: String
    )

// {
//  "nickName": "string",
//  "point": 0,
//  "profileImage": "string"
//}