package com.iame.qnnect.android.src.diary.model

import com.google.gson.annotations.SerializedName

data class QuestionMain(
    @SerializedName("createdAt") var createdAt: String,
    @SerializedName("daysLeft") var daysLeft: Int,
    @SerializedName("cafeQuestionId") var cafeQuestionId: Int,
    @SerializedName("questioner") var questioner: String,
    @SerializedName("question") var question: String,
    @SerializedName("liked") var liked: Boolean,
    @SerializedName("writer") var writer: Boolean,
    @SerializedName("scraped") var scraped: Boolean
    )

// "questionMainResponse": {
//        "createdAt": "2022-03-03",
//        "daysLeft": 0,
//        "cafeQuestionId": 1,
//        "questioner": "넥트",
//        "question": "첫번째 질문입니다.",
//        "liked": false,
//        "writer": false,
//        "scraped": false
//    }