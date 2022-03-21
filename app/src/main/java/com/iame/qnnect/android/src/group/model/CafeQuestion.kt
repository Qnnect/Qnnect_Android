package com.iame.qnnect.android.src.group.model

import com.google.gson.annotations.SerializedName

data class CafeQuestion(
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("daysLeft") val daysLeft: Int,
    @SerializedName("cafeQuestionId") val cafeQuestionId: Int,
    @SerializedName("questioner") val questioner: String,
    @SerializedName("question") val question: String,
    @SerializedName("writer") val writer: Boolean
)

// {
//            "createdAt": "2022-03-03",
//            "daysLeft": 0,
//            "cafeQuestionId": 1,
//            "questioner": "넥트",
//            "question": "첫번째 질문입니다.",
//            "writer": false
//        },