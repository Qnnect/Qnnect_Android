package com.iame.qnnect.android.src.diary.model

import com.google.gson.annotations.SerializedName

data class GetQuestionResponse(
    @SerializedName("questionMainResponse") val questionMainResponse: QuestionMain,
    @SerializedName("currentUserComment") val currentUserComment: Comments,
    @SerializedName("comments") val comments: List<Comments>,
    @SerializedName("scraped") val scraped: Boolean,
    @SerializedName("liked") val liked: Boolean
    )