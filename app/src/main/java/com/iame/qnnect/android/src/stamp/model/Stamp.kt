package com.iame.qnnect.android.src.stamp.model

import com.google.gson.annotations.SerializedName

data class Stamp(
    @SerializedName("cafeName") val cafeName: String,
    @SerializedName("drinkName") val drinkName: String
    )
// [
//  {
//    "cafeName": "string",
//    "drinkName": "string"
//  }
//]