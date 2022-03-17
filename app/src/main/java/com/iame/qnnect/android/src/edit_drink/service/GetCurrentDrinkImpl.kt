package com.iame.qnnect.android.src.edit_drink.service

import com.iame.qnnect.android.src.edit_drink.model.GetCurrentDrinkDataModel
import com.iame.qnnect.android.src.edit_drink.model.GetCurrentUserDrinkResponse
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single

class GetCurrentDrinkImpl(private val service: GetCurrentDrinkAPI) : GetCurrentDrinkDataModel {
    override fun getData(cafeId: Int): Single<GetCurrentUserDrinkResponse> {
        return service.getCurrentDrink(cafeId)
    }
}