package com.iame.qnnect.android.model

import com.iame.qnnect.android.model.enum.KakaoSearchSortEnum
import com.iame.qnnect.android.model.response.ImageSearchResponse
import com.iame.qnnect.android.model.service.KakaoSearchService
import io.reactivex.Single
import what.the.mvvm.MyConstant.Companion.KAKAO_APP_KEY

class DataModelImpl(private val service: KakaoSearchService) : DataModel {

    override fun getData(query: String, sort: KakaoSearchSortEnum, page: Int, size: Int): Single<ImageSearchResponse> {
        return service.searchImage(auth = "KakaoAK $KAKAO_APP_KEY", query = query, sort = sort.sort, page = page, size = size)
    }
}