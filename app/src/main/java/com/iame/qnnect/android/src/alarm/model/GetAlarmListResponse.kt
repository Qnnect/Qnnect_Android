package com.iame.qnnect.android.src.alarm.model

import com.google.gson.annotations.SerializedName

data class GetAlarmListResponse(
    @SerializedName("content") val content: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("groupName") val groupName: String,
    @SerializedName("notificationType") val notificationType: String
    )

// [
//  {
//    "content": "string",
//    "createdAt": "2022-03-28",
//    "groupName": "string",
//    "notificationType": "comment"
//  }
//]