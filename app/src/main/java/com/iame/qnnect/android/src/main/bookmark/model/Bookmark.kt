package com.iame.qnnect.android.src.main.bookmark.model

import com.google.gson.annotations.SerializedName

data class Bookmark(
    @SerializedName("cafeQuestionId") val cafeQuestionId: Int,
    @SerializedName("cafeTitle") val cafeTitle: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("question") val question: String
    )

//  {
//    "cafeQuestionId": 0,
//    "cafeTitle": "string",
//    "createdAt": "2022-03-11",
//    "question": "string"
//  }