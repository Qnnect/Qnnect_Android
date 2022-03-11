package com.iame.qnnect.android.src.group.model

import com.google.gson.annotations.SerializedName

data class GetGroupResponse(
    @SerializedName("createdAt") var createdAt: String,
    @SerializedName("title") var title: String,
    @SerializedName("code") var code: String,
    @SerializedName("diaryColor") var diaryColor: String,
    @SerializedName("currentUserResponse") var currentUser: CafeUser,
    @SerializedName("cafeUserResponseList") var cafeUserList: List<CafeUser>,
    @SerializedName("cafeQuestionResponseList") var cafeQuestionList: List<CafeQuestion>
)