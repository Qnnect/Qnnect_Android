package com.iame.qnnect.android.src.group.model

data class CafeUserResponse(
    val drinkIngredientsFilledResponseList: List<DrinkIngredientsFilledResponse>,
    val user: User,
    val userDrinkSelected: String
)