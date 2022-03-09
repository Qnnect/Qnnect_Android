package com.iame.qnnect.android.src.main.bookmark.service

import com.iame.qnnect.android.src.group.model.GetGroupResponse
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.main.bookmark.model.Bookmark
import com.iame.qnnect.android.src.main.bookmark.model.Cafe
import com.iame.qnnect.android.src.main.bookmark.model.GetCafeListResponse
import com.iame.qnnect.android.src.main.home.home_model.GetHomeResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface BookmarkListAPI {
    @GET("/api/v1/users/scrap/{cafeId}")
    fun getBookmark(@Path("cafeId") cafeId: Int) : Single<List<Bookmark>>
}