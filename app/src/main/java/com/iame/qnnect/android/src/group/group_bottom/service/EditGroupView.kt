package com.iame.qnnect.android.src.group.group_bottom.service

import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupResponse

interface EditGroupView{
    fun onEditGroupSuccess(response: String)
    fun onEditGroupFailure(message: String)
}