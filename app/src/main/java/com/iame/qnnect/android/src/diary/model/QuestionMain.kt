package com.iame.qnnect.android.src.diary.model

import com.google.gson.annotations.SerializedName

data class QuestionMain(
    @SerializedName("createdAt") var createdAt: String,
    @SerializedName("daysLeft") var daysLeft: Int,
    @SerializedName("cafeQuestionId") var cafeQuestionId: Int,
    @SerializedName("questioner") var questioner: String,
    @SerializedName("question") var question: String,
    @SerializedName("writer") var writer: Boolean
    )

// "questionMainResponse": {
//        "createdAt": "2022-03-13",
//        "daysLeft": 7,
//        "cafeQuestionId": 9,
//        "questioner": "넥트",
//        "question": "세번째여",
//        "writer": false
//    }