package com.iame.qnnect.android.src.main.home.home_model

import com.google.gson.annotations.SerializedName
import com.iame.qnnect.android.src.group.model.CafeUser
import com.iame.qnnect.android.src.main.home.model.GetUserResponse

data class GetHomeResponse(
    @SerializedName("user") var user: GetUserResponse,
    @SerializedName("questionTodayList") var questionList: List<HomeQuestion>,
    @SerializedName("cafeMainResponseList") var groupList: List<HomeCafes>
    )