package com.iame.qnnect.android.src.main.home.home_model

import com.google.gson.annotations.SerializedName

data class HomeQuestion(
    @SerializedName("cafeQuestionId") var cafeQuestionId: Int,
    @SerializedName("daysLeft") var daysLeft: Int,
    @SerializedName("content") var content: String,
    @SerializedName("cafeTitle") var cafeTitle: String
    )