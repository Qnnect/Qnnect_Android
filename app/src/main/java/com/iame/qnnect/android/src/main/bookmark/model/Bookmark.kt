package com.iame.qnnect.android.src.main.bookmark.model

import com.google.gson.annotations.SerializedName

data class Bookmark(
    @SerializedName("cafeQuestionId") var cafeQuestionId: Int,
    @SerializedName("cafeTitle") var cafeTitle: String,
    @SerializedName("createdAt") var createdAt: String,
    @SerializedName("question") var question: String
    )

//  {
//    "cafeQuestionId": 0,
//    "cafeTitle": "string",
//    "createdAt": "2022-03-11",
//    "question": "string"
//  }