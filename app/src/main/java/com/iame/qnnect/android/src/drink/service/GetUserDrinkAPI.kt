package com.iame.qnnect.android.src.drink.service

import com.iame.qnnect.android.src.drink.model.GetUserDrinkResponse
import com.iame.qnnect.android.src.edit_drink.model.GetCurrentUserDrinkResponse
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GetUserDrinkAPI {
    @GET("/api/v1/cafe/{cafeId}/drink/{cafeUserId}")
    fun getUserDrink(@Path("cafeId") cafeId: Int,
                     @Path("cafeUserId") cafeUserId: Int) : Single<GetUserDrinkResponse>
}