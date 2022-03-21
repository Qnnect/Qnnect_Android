package com.iame.qnnect.android.src.main.bookmark.model

import com.google.gson.annotations.SerializedName

data class Cafe(
    @SerializedName("cafeId") val cafeId: Int,
    @SerializedName("cafeTitle") val cafeTitle: String
    )

// "cafeId": 12,
//        "cafeTitle": "신사고 4인방"