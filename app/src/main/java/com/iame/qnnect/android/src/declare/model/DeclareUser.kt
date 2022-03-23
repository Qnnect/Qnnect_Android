package com.iame.qnnect.android.src.declare.model

import com.google.gson.annotations.SerializedName
import com.iame.qnnect.android.src.edit_drink.model.MyIngredient

data class DeclareUser(
    @SerializedName("nickName") val nickName: String,
    @SerializedName("reportId") val reportId: Int
    )

// {
//    "nickName": "string",
//    "reportId": 0
//  }