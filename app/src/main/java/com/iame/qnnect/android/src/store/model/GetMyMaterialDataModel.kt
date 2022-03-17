package com.iame.qnnect.android.src.store.model

import com.iame.qnnect.android.src.edit_drink.model.MyIngredient
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single

interface GetMyMaterialDataModel {
    fun getData(ingredientType: String): Single<List<MyIngredient>>
}

