package com.americano.qnnect.kotlin.model

import com.americano.qnnect.kotlin.model.enum.KakaoSearchSortEnum
import com.americano.qnnect.kotlin.model.response.ImageSearchResponse
import com.americano.qnnect.kotlin.model.service.KakaoSearchService
import io.reactivex.Single
import what.the.mvvm.KAKAO_APP_KEY

class DataModelImpl(private val service: KakaoSearchService) : DataModel {

    override fun getData(query: String, sort: KakaoSearchSortEnum, page: Int, size: Int): Single<ImageSearchResponse> {
        return service.searchImage(auth = "KakaoAK $KAKAO_APP_KEY", query = query, sort = sort.sort, page = page, size = size)
    }
}