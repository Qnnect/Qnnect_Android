package com.iame.qnnect.android.src.main.home.home_bottom.service

import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupResponse

interface AddGroupView{
    fun onAddGroupSuccess(response: Int)
    fun onAddGroupFailure(message: String)
}