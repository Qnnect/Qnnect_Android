package com.iame.qnnect.android.src.store.service

import com.iame.qnnect.android.src.edit_drink.model.GetCurrentDrinkDataModel
import com.iame.qnnect.android.src.edit_drink.model.GetCurrentUserDrinkResponse
import com.iame.qnnect.android.src.edit_drink.model.MyIngredient
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.store.model.GetMyMaterialAllDataModel
import com.iame.qnnect.android.src.store.model.PostBuyMaterialDataModel
import io.reactivex.Single

class GetMyMaterialAllImpl(private val service: GetMyMaterialAllAPI) : GetMyMaterialAllDataModel {
    override fun getData(): Single<List<MyIngredient>> {
        return service.getMyMaterialAll()
    }
}