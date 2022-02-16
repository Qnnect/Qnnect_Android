package com.americano.qnnect.kotlin.model

import com.americano.qnnect.kotlin.model.enum.KakaoSearchSortEnum
import com.americano.qnnect.kotlin.model.response.ImageSearchResponse
import io.reactivex.Single

interface DataModel {

    fun getData(query:String, sort: KakaoSearchSortEnum, page:Int, size:Int): Single<ImageSearchResponse>

}

