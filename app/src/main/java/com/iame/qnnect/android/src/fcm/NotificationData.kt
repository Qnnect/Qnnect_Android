package com.iame.qnnect.android.src.fcm

import com.google.gson.annotations.SerializedName
import com.iame.qnnect.android.src.edit_drink.model.MyIngredient

data class NotificationData(
    @SerializedName("title") val title: String,
    @SerializedName("message") val message: String
    )

// val title: String,
//    val message: String