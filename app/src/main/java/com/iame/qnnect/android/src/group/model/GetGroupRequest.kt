package com.iame.qnnect.android.src.group.model

import com.google.gson.annotations.SerializedName

data class GetGroupRequest(
    @SerializedName("cafeId") val cafeId: Int
)