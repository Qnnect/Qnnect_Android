package com.iame.qnnect.android.src.main.home.home_bottom.service

import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupRequest
import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface AddGroupAPI {
    @POST("/api/v1/cafes")
    fun postAddGroup(@Body body: PostAddGroupRequest) : Call<PostAddGroupResponse>
}