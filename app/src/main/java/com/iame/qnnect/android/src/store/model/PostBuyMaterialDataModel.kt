package com.iame.qnnect.android.src.store.model

import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single

interface PostBuyMaterialDataModel {
    fun getData(ingredientsId: Int): Single<Unit>
}

