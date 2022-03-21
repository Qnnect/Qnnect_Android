package com.iame.qnnect.android.src.question.model

import com.google.gson.annotations.SerializedName
import com.iame.qnnect.android.src.group.model.CafeUser
import com.iame.qnnect.android.src.main.bookmark.model.Bookmark
import com.iame.qnnect.android.src.main.home.model.GetUserResponse

data class GetQuestionResponse(
    @SerializedName("cafeQuestionList") val cafeQuestionList: List<Bookmark>
    )