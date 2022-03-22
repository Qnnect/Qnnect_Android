package com.iame.qnnect.android.src.recipe.model

import com.google.gson.annotations.SerializedName
import com.iame.qnnect.android.src.edit_drink.model.CurrentDrinkInfo
import com.iame.qnnect.android.src.edit_drink.model.MyIngredient
import com.iame.qnnect.android.src.group.model.CafeUser
import com.iame.qnnect.android.src.main.bookmark.model.Bookmark
import com.iame.qnnect.android.src.main.home.model.GetUserResponse

data class GetRecipeResponse(
    @SerializedName("currentDrinkInfo") val currentDrinkInfo: CurrentDrinkInfo,
    @SerializedName("drinkRecipeResponses") val drinkRecipeResponses: List<MyIngredient>
    )