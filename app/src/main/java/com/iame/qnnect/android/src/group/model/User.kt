package com.iame.qnnect.android.src.group.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("nickName") val nickName: String,
    @SerializedName("point") val point: Int,
    @SerializedName("profileImage") val profileImage: String,
    @SerializedName("reportId") val reportId: Int
)