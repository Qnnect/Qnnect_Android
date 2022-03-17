package com.iame.qnnect.android.src.edit_drink.model

import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single

interface GetCurrentDrinkDataModel {
    fun getData(cafeId: Int): Single<GetCurrentUserDrinkResponse>
}

