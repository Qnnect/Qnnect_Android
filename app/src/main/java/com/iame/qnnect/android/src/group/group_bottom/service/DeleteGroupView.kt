package com.iame.qnnect.android.src.group.group_bottom.service

import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupResponse

interface DeleteGroupView{
    fun onDeleteGroupSuccess(response: String)
    fun onDeleteGroupFailure(message: String)
}