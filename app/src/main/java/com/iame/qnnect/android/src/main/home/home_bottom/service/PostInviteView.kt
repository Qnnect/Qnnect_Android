package com.iame.qnnect.android.src.main.home.home_bottom.service

import com.iame.qnnect.android.src.group.model.GetGroupResponse
import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupResponse

interface PostInviteView{
    fun onInviteSuccess(response: GetGroupResponse)
    fun onInviteFailure(message: String)
}