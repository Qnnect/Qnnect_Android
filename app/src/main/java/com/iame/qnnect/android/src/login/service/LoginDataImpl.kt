package com.iame.qnnect.android.src.login.service

import com.iame.qnnect.android.model.DataModel
import com.iame.qnnect.android.model.enum.KakaoSearchSortEnum
import com.iame.qnnect.android.model.response.ImageSearchResponse
import com.iame.qnnect.android.model.service.KakaoSearchService
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single

class LoginDataImpl(private val service: LoginAPI) : LoginDataModel {
    override fun getData(postLoginRequest: PostLoginRequest): Single<PostLoginResponse> {
        return service.postLogin(postLoginRequest)
    }
}