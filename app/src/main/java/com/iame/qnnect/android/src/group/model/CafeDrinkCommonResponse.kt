package com.iame.qnnect.android.src.group.model

import com.google.gson.annotations.SerializedName
import com.iame.qnnect.android.src.edit_drink.model.MyIngredient

data class CafeDrinkCommonResponse(
    @SerializedName("userDrinkSelectedId") val userDrinkSelectedId: Int,
    @SerializedName("userDrinkName") val userDrinkName: String,
    @SerializedName("currentDrinkIngredientsFilled") val currentDrinkIngredientsFilled: List<IngredientName>,
    @SerializedName("ice") val ice: Int,
    @SerializedName("iceFilled") val iceFilled: Int,
    @SerializedName("base") val base: Int,
    @SerializedName("baseFilled") val baseFilled: Int,
    @SerializedName("main") val main: Int,
    @SerializedName("mainFilled") val mainFilled: Int,
    @SerializedName("topping") val topping: Int,
    @SerializedName("toppingFilled") val toppingFilled: Int
)