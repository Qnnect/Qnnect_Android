package com.iame.qnnect.android.src.main.home.home_bottom.model

import com.google.gson.annotations.SerializedName

data class PostAddGroupRequest(
    @SerializedName("diaryColor") val diaryColor: String,
    @SerializedName("groupType") val groupType: String,
    @SerializedName("questionCycle") val questionCycle: String,
    @SerializedName("title") val title: String
)

// {
//  "diaryColor": "red",
//  "groupType": "친구",
//  "questionCycle": "everyDay",
//  "title": "신사고 4인방"
//}