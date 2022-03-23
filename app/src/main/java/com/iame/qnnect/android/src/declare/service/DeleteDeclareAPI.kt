package com.iame.qnnect.android.src.declare.service

import com.iame.qnnect.android.src.declare.model.DeclareUser
import com.iame.qnnect.android.src.drink.model.GetUserDrinkResponse
import com.iame.qnnect.android.src.edit_drink.model.GetCurrentUserDrinkResponse
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single
import retrofit2.http.*

interface DeleteDeclareAPI {
    @DELETE("/api/v1/user/report")
    fun getDeleteDeclare(@Query("reportId") reportId: Int) : Single<Unit>
}