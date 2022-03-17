package com.iame.qnnect.android.src.store.service

import com.iame.qnnect.android.src.edit_drink.model.GetCurrentUserDrinkResponse
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface PostBuyMaterialAPI {
    @POST("/api/v1/ingredients/{ingredientsId}")
    fun postBuyMaterial(@Path("ingredientsId") ingredientsId: Int) : Single<Unit>
}