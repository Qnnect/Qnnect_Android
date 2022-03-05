package com.iame.qnnect.android.src.group.model

data class GetGroupResponse(
    val cafeQuestionResponseList: List<CafeQuestionResponse>,
    val cafeUserResponseList: List<CafeUserResponse>,
    val code: String,
    val createdAt: String,
    val organizer: Organizer,
    val title: String
)