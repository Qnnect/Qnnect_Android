package com.iame.qnnect.android.src.search.service

import com.iame.qnnect.android.src.group.model.GetGroupResponse
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.main.bookmark.model.Bookmark
import com.iame.qnnect.android.src.main.bookmark.model.Cafe
import com.iame.qnnect.android.src.main.bookmark.model.GetCafeListResponse
import com.iame.qnnect.android.src.main.home.home_model.GetHomeResponse
import io.reactivex.Single
import retrofit2.http.*

interface SearchAPI {
    @GET("/api/v1/users/scrap")
    fun getSearch(@Query("searchWord") searchWord: String) : Single<List<Bookmark>>
}