package com.iame.qnnect.android.src.drink.model

import com.iame.qnnect.android.src.edit_drink.model.GetCurrentUserDrinkResponse
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single

interface GetUserDrinkDataModel {
    fun getData(cafeId: Int, cafeUserId: Int): Single<GetUserDrinkResponse>
}

