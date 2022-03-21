package com.iame.qnnect.android.src.group.model

import com.google.gson.annotations.SerializedName

data class DrinkIngredientsFilledResponse(
    @SerializedName("ingredientName") val ingredientName: String
)