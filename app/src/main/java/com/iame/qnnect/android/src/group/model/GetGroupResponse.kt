package com.iame.qnnect.android.src.group.model

import com.google.gson.annotations.SerializedName

data class GetGroupResponse(
    @SerializedName("cafeId") val cafeId: Int,
    @SerializedName("cafeUserId") val cafeUserId: Int,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("title") val title: String,
    @SerializedName("code") val code: String,
    @SerializedName("diaryColor") val diaryColor: String,
    @SerializedName("currentUserResponse") val currentUser: CafeUser,
    @SerializedName("cafeUserResponseList") val cafeUserList: List<CafeUser>,
    @SerializedName("cafeQuestionResponseList") val cafeQuestionList: List<CafeQuestion>
)