package com.iame.qnnect.android.src.store.service

import com.iame.qnnect.android.src.edit_drink.model.GetCurrentDrinkDataModel
import com.iame.qnnect.android.src.edit_drink.model.GetCurrentUserDrinkResponse
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.store.model.PostBuyMaterialDataModel
import io.reactivex.Single

class PostBuyMaterialImpl(private val service: PostBuyMaterialAPI) : PostBuyMaterialDataModel {
    override fun getData(ingredientsId: Int): Single<Unit> {
        return service.postBuyMaterial(ingredientsId)
    }
}