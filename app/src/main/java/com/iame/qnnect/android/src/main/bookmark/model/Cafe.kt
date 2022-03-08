package com.iame.qnnect.android.src.main.bookmark.model

import com.google.gson.annotations.SerializedName

data class Cafe(
    @SerializedName("cafeId") var cafeId: Int,
    @SerializedName("cafeTitle") var cafeTitle: String
    )

// "cafeId": 12,
//        "cafeTitle": "신사고 4인방"