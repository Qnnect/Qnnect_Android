package com.iame.qnnect.android.src.question.model

import com.google.gson.annotations.SerializedName

data class GetUserQuestionListResponse(
    @SerializedName("cafeQuestionId") val cafeQuestionId: Int,
    @SerializedName("cafeTitle") val cafeTitle: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("question") val question: String,
    @SerializedName("waitingList") val waitingList: Boolean
    )

// {
//    "cafeQuestionId": 0,
//    "cafeTitle": "string",
//    "createdAt": "2022-03-31",
//    "question": "string",
//    "waitingList": true <- true 이면 기다리는 질문
//  }