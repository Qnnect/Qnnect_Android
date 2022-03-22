package com.iame.qnnect.android.src.recipe.service

import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupRequest
import com.iame.qnnect.android.src.profile.model.PatchProfileResponse
import com.iame.qnnect.android.src.question.model.GetQuestionResponse
import com.iame.qnnect.android.src.question.model.PostQuestionRequest
import com.iame.qnnect.android.src.recipe.model.GetRecipeResponse
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.*

interface GetRecipeAPI {
    @GET("/api/v1/drinks/{userDrinkSelectedId}/recipe")
    fun getRecipe(@Path("userDrinkSelectedId") userDrinkSelectedId: Int,
                  @Query("cafeId") cafeId: Int
                    ) : Single<GetRecipeResponse>
}