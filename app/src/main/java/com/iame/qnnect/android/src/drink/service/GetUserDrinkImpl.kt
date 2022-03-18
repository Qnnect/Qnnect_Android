package com.iame.qnnect.android.src.drink.service

import com.iame.qnnect.android.src.drink.model.GetUserDrinkDataModel
import com.iame.qnnect.android.src.drink.model.GetUserDrinkResponse
import com.iame.qnnect.android.src.edit_drink.model.GetCurrentDrinkDataModel
import com.iame.qnnect.android.src.edit_drink.model.GetCurrentUserDrinkResponse
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single

class GetUserDrinkImpl(private val service: GetUserDrinkAPI) : GetUserDrinkDataModel {
    override fun getData(cafeId: Int, cafeUserId: Int): Single<GetUserDrinkResponse> {
        return service.getUserDrink(cafeId, cafeUserId)
    }
}