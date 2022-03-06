package com.iame.qnnect.android.src.group.model

import com.google.gson.annotations.SerializedName

data class CafeUser(
    @SerializedName("drinkIngredientsFilledResponseList") var drinkIngredientsFilledResponseList: List<DrinkIngredientsFilledResponse>,
    @SerializedName("user") var user: User,
    @SerializedName("userDrinkSelected") var userDrinkSelected: String
)