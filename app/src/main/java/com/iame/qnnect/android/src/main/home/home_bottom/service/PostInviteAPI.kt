package com.iame.qnnect.android.src.main.home.home_bottom.service

import com.iame.qnnect.android.src.group.model.GetGroupResponse
import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupRequest
import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface PostInviteAPI {
    @POST("/api/v1/cafes/join")
    fun postInvite(@Query("cafeCode") cafeCode: String) : Call<GetGroupResponse>
}