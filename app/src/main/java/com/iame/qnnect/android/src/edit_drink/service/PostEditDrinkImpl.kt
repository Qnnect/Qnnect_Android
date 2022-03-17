package com.iame.qnnect.android.src.edit_drink.service

import com.iame.qnnect.android.src.edit_drink.model.GetCurrentDrinkDataModel
import com.iame.qnnect.android.src.edit_drink.model.GetCurrentUserDrinkResponse
import com.iame.qnnect.android.src.edit_drink.model.PostEditDrinkDataModel
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single

class PostEditDrinkImpl(private val service: PostEditDrinkAPI) : PostEditDrinkDataModel {
    override fun getData(userDrinkSelectedId: Int, ingredientsId: Int): Single<Unit> {
        return service.postEditDrink(userDrinkSelectedId, ingredientsId)
    }
}