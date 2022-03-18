package com.iame.qnnect.android.src.drink.model

import com.google.gson.annotations.SerializedName
import com.iame.qnnect.android.src.edit_drink.model.MyIngredient

data class CafeUser(
    @SerializedName("cafeUserId") var cafeUserId: Int,
    @SerializedName("nickName") var nickName: String,
    @SerializedName("profileImage") var profileImage: String
    )

// {
////      "cafeUserId": 0,
////      "nickName": "string",
////      "profileImage": "string"
////    }