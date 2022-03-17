package com.iame.qnnect.android.src.edit_drink.service

import com.iame.qnnect.android.src.edit_drink.model.GetCurrentUserDrinkResponse
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface PostEditDrinkAPI {
    @PATCH("/api/v1/drinks/{userDrinkSelectedId}/ingredients/{ingredientsId}")
    fun postEditDrink(@Path("userDrinkSelectedId") userDrinkSelectedId: Int,
                        @Path("ingredientsId") ingredientsId: Int) : Single<Unit>
}