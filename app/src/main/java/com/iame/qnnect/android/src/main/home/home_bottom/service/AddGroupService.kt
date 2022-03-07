package com.iame.qnnect.android.src.main.home.home_bottom.service

import com.iame.qnnect.android.MyApplication
import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupRequest
import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddGroupService(val view: AddGroupView) {
    fun tryAddGroup(addGroupRequest: PostAddGroupRequest){
        val addgroupAPI = MyApplication.sRetrofit.create(AddGroupAPI::class.java)
        addgroupAPI.postAddGroup(addGroupRequest).enqueue(object :
            Callback<Int> {
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                view.onAddGroupSuccess(response.body() as Int)
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                view.onAddGroupFailure(t.message ?: "통신 오류")
            }
        })
    }
}