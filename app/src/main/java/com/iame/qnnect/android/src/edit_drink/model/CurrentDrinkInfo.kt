package com.iame.qnnect.android.src.edit_drink.model

import com.google.gson.annotations.SerializedName

data class CurrentDrinkInfo(
    @SerializedName("userDrinkSelectedId") val userDrinkSelectedId: Int,
    @SerializedName("userDrinkName") val userDrinkName: String,
    @SerializedName("currentDrinkIngredientsFilled") val currentDrinkIngredientsFilled: List<CurrentDrinkFilled>,
    @SerializedName("ice") val ice: Int,
    @SerializedName("iceFilled") val iceFilled: Int,
    @SerializedName("base") val base: Int,
    @SerializedName("baseFilled") val baseFilled: Int,
    @SerializedName("main") val main: Int,
    @SerializedName("mainFilled") val mainFilled: Int,
    @SerializedName("topping") val topping: Int,
    @SerializedName("toppingFilled") val toppingFilled: Int,
    )

// {
//    "currentDrinkInfo": {
//        "userDrinkSelectedId": 46,
//        "userDrinkName": "딸기라떼",
//        "currentDrinkIngredientsFilled": [],
//        "ice": 2,
//        "iceFilled": 0,
//        "base": 3,
//        "baseFilled": 0,
//        "main": 3,
//        "mainFilled": 0,
//        "topping": 2,
//        "toppingFilled": 0
//    }
//}