package com.iame.qnnect.android.src.group.group_bottom.service

import com.iame.qnnect.android.MyApplication
import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupRequest
import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupResponse
import com.iame.qnnect.android.src.main.home.home_bottom.service.AddGroupAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditGroupService(val view: EditGroupView) {
    fun tryEditGroup(cafeId: Int, request: PostAddGroupRequest){
        val editgroupAPI = MyApplication.sRetrofit.create(EditGroupAPI::class.java)
        editgroupAPI.patchEditGroup(cafeId, request).enqueue(object :
            Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                view.onEditGroupSuccess("200 OK")
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                view.onEditGroupFailure(t.message ?: "통신 오류")
            }
        })
    }
}