package com.iame.qnnect.android.src.add_drink.service

import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupRequest
import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface AddDrinkAPI {
    @POST("/api/v1/diaries/{cafeId}/drinks")
    fun addDrink(@Path("cafeId") cafeId: Int, @Query("drinkId") drinkId: Int) : Call<Void>
}