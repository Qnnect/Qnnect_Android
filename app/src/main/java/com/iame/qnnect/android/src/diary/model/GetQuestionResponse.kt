package com.iame.qnnect.android.src.diary.model

import com.google.gson.annotations.SerializedName

data class GetQuestionResponse(
    @SerializedName("questionMainResponse") var questionMainResponse: QuestionMain,
    @SerializedName("comments") var comments: List<Comments>
    )