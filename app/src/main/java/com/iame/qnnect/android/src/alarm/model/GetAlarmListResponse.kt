package com.iame.qnnect.android.src.alarm.model

import com.google.gson.annotations.SerializedName

data class GetAlarmListResponse(
    @SerializedName("content") val content: String,
    @SerializedName("contentId") val contentId: Int,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("groupName") val groupName: String,
    @SerializedName("notificationId") val notificationId: Int,
    @SerializedName("notificationType") val notificationType: String,
    @SerializedName("userRead") val userRead: Boolean
)

// [
//  {
//    "content": "string",
//    "createdAt": "2022-03-28",
//    "groupName": "string",
//    "notificationId": 0,
//    "notificationType": "comment",
//    "userRead": true
//  }
//]