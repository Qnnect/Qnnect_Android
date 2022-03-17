package com.iame.qnnect.android.src.store.service

import com.iame.qnnect.android.src.edit_drink.model.GetCurrentUserDrinkResponse
import com.iame.qnnect.android.src.edit_drink.model.MyIngredient
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GetMyMaterialAllAPI {
    @GET("/api/v1/ingredients/me/all")
    fun getMyMaterialAll() : Single<List<MyIngredient>>
}