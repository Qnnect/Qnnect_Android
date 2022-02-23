package com.iame.qnnect.android.src.login.model

import com.iame.qnnect.android.model.enum.KakaoSearchSortEnum
import com.iame.qnnect.android.model.response.ImageSearchResponse
import io.reactivex.Single

interface LoginDataModel {
    fun getData(postLoginRequest: PostLoginRequest): Single<PostLoginResponse>
}

