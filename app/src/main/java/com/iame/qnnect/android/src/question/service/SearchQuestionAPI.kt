package com.iame.qnnect.android.src.question.service

import com.iame.qnnect.android.src.group.model.GetGroupResponse
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.main.bookmark.model.Bookmark
import com.iame.qnnect.android.src.main.bookmark.model.Cafe
import com.iame.qnnect.android.src.main.bookmark.model.GetCafeListResponse
import com.iame.qnnect.android.src.main.home.home_model.GetHomeResponse
import com.iame.qnnect.android.src.question.model.GetQuestionResponse
import io.reactivex.Single
import retrofit2.http.*

interface SearchQuestionAPI {
    @GET("/api/v1/question/cafes/{cafeId}/search")
    fun getSearch(@Path("cafeId") cafeId: Int, @Query("searchWord") searchWord: String) : Single<GetQuestionResponse>
}