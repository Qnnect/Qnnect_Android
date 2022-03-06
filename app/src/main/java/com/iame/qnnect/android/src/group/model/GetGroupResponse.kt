package com.iame.qnnect.android.src.group.model

import com.google.gson.annotations.SerializedName

data class GetGroupResponse(
    @SerializedName("cafeQuestionResponseList") var cafeQuestionList: List<CafeQuestion>,
    @SerializedName("cafeUserResponseList") var cafeUserList: List<CafeUser>,
    @SerializedName("code") var code: String,
    @SerializedName("createdAt") var createdAt: String,
    @SerializedName("organizer") var organizer: Organizer,
    @SerializedName("title") var title: String
)