package com.iame.qnnect.android.src.question.model

import com.google.gson.annotations.SerializedName
import java.io.File

data class PostQuestionRequest(
    @SerializedName("content") val content: String
)