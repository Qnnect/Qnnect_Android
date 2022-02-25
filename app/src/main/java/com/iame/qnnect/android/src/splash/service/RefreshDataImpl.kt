package com.iame.qnnect.android.src.splash.service

import com.iame.qnnect.android.src.splash.model.PostRefreshRequest
import com.iame.qnnect.android.src.splash.model.PostRefreshResponse
import com.iame.qnnect.android.src.splash.model.RefreshDataModel
import io.reactivex.Single

class RefreshDataImpl(private val service: RefreshAPI) : RefreshDataModel {
    override fun postRefresh(postRefreshRequest: PostRefreshRequest): Single<PostRefreshResponse> {
        return service.postRefresh(postRefreshRequest)
    }
}