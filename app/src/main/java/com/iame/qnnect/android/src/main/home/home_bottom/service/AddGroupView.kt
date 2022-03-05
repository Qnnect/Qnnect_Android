package com.iame.qnnect.android.src.main.home.home_bottom.service

import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupResponse

interface AddGroupView{
    fun onAddGroupSuccess(response: PostAddGroupResponse)
    fun onAddGroupFailure(message: String)
}