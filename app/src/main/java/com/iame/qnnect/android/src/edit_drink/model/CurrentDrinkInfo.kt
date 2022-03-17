package com.iame.qnnect.android.src.edit_drink.model

import com.google.gson.annotations.SerializedName

data class CurrentDrinkInfo(
    @SerializedName("userDrinkSelectedId") var userDrinkSelectedId: Int,
    @SerializedName("currentDrinkIngredientsFilled") var currentDrinkIngredientsFilled: List<CurrentDrinkFilled>,
    @SerializedName("ice") var ice: Int,
    @SerializedName("iceFilled") var iceFilled: Int,
    @SerializedName("base") var base: Int,
    @SerializedName("baseFilled") var baseFilled: Int,
    @SerializedName("main") var main: Int,
    @SerializedName("mainFilled") var mainFilled: Int,
    @SerializedName("topping") var topping: Int,
    @SerializedName("toppingFilled") var toppingFilled: Int,
    )

// {
//    "currentDrinkInfo": {
//        "userDrinkSelectedId": 9,
//        "currentDrinkIngredientsFilled": [],
//        "ice": 2,
//        "iceFilled": 0,
//        "base": 3,
//        "baseFilled": 0,
//        "main": 3,
//        "mainFilled": 0,
//        "topping": 2,
//        "toppingFilled": 0
//    },
//    "myIngredient": []
//}