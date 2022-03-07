package com.iame.qnnect.android.src.main.home.home_model

import com.google.gson.annotations.SerializedName

data class HomeCafes(
    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("createdAt") var createdAt: String,
    @SerializedName("cafeUserNum") var cafeUserNum: Int
    )

// {
//            "id": 38,
//            "title": "123",
//            "createdAt": "2022-03-05",
//            "cafeUserNum": 1
//        }