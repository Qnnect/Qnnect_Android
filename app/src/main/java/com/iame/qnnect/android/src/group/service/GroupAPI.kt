package com.iame.qnnect.android.src.group.service

import com.iame.qnnect.android.src.group.model.GetGroupResponse
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface GroupAPI {
    @GET("/api/v1/cafes/{cafeId}")
    fun getGroup(@Path("cafeId") cafeId: Int) : Single<GetGroupResponse>
}

// interface StreamingCategoryAPI {
//    @GET("/videos/category/{categoryId}")
//    fun getStreamingCategory(@Path("categoryId") categoryId: Int): Call<List<Streaming>>
//}