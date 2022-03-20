package com.iame.qnnect.android.src.main.home.home_bottom.service

import com.iame.qnnect.android.MyApplication
import com.iame.qnnect.android.src.group.model.GetGroupResponse
import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupRequest
import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostInviteService(val view: PostInviteView) {
    fun tryInvite(cafeCode: String){
        val inviteAPI = MyApplication.sRetrofit.create(PostInviteAPI::class.java)
        inviteAPI.postInvite(cafeCode).enqueue(object :
            Callback<GetGroupResponse> {
            override fun onResponse(call: Call<GetGroupResponse>, response: Response<GetGroupResponse>) {
                try{
                    view.onInviteSuccess(response as GetGroupResponse)
                } catch(e: Exception){
                    view.onInviteFailure("해당 코드를 가진 카페를 찾을 수 없습니다.")
                }
            }
            override fun onFailure(call: Call<GetGroupResponse>, t: Throwable) {
                view.onInviteFailure(t.message ?: "통신 오류")
            }
        })
    }
}