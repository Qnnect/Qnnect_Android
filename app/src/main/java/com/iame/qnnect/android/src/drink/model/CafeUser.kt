package com.iame.qnnect.android.src.drink.model

import com.google.gson.annotations.SerializedName
import com.iame.qnnect.android.src.edit_drink.model.MyIngredient

data class CafeUser(
    @SerializedName("cafeUserId") val cafeUserId: Int,
    @SerializedName("nickName") val nickName: String,
    @SerializedName("profileImage") val profileImage: String
    )

// {
////      "cafeUserId": 0,
////      "nickName": "string",
////      "profileImage": "string"
////    }