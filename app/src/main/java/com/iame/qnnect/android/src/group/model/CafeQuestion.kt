package com.iame.qnnect.android.src.group.model

import com.google.gson.annotations.SerializedName

data class CafeQuestion(
    @SerializedName("createdAt") var createdAt: String,
    @SerializedName("daysLeft") var daysLeft: Int,
    @SerializedName("question") var question: String,
    @SerializedName("questioner") var questioner: String
)