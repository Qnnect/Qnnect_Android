package com.iame.qnnect.android.src.recipe.model

import com.iame.qnnect.android.src.main.bookmark.model.GetBookmarkListResponse
import com.iame.qnnect.android.src.profile.model.PatchProfileResponse
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.Part

interface GetRecipeDataModel {
    fun getData(userDrinkSelectedId: Int, cafeId: Int): Single<GetRecipeResponse>
}
