package com.iame.qnnect.android.src.diary.model

import com.google.gson.annotations.SerializedName

data class QuestionMain(
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("daysLeft") val daysLeft: Int,
    @SerializedName("cafeQuestionId") val cafeQuestionId: Int,
    @SerializedName("questioner") val questioner: String,
    @SerializedName("question") val question: String,
    @SerializedName("writer") val writer: Boolean
    )

// "questionMainResponse": {
//        "createdAt": "2022-03-13",
//        "daysLeft": 7,
//        "cafeQuestionId": 9,
//        "questioner": "넥트",
//        "question": "세번째여",
//        "writer": false
//    }