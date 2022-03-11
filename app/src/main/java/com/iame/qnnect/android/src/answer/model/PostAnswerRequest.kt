package com.iame.qnnect.android.src.answer.model

import com.google.gson.annotations.SerializedName
import java.io.File

data class PostAnswerRequest(
    @SerializedName("image5") val image5: File,
    @SerializedName("image4") val image4: File,
    @SerializedName("image3") val image3: File,
    @SerializedName("image2") val image2: File,
    @SerializedName("image1") val image1: File,
    @SerializedName("content") val content: String
)

// @SerializedName("cafeId") val cafeId: Int,
//    @SerializedName("cafeQuestionId") val cafeQuestionId: Int
