package com.iame.qnnect.android.src.question.service

import com.iame.qnnect.android.src.group.model.GetGroupRequest
import com.iame.qnnect.android.src.group.model.GetGroupResponse
import com.iame.qnnect.android.src.group.model.GroupDataModel
import com.iame.qnnect.android.src.main.bookmark.model.*
import com.iame.qnnect.android.src.main.bookmark.service.BookmarkListAPI
import com.iame.qnnect.android.src.main.home.home_model.GetHomeResponse
import com.iame.qnnect.android.src.main.home.home_model.HomeDataModel
import com.iame.qnnect.android.src.question.model.GetQuestionResponse
import com.iame.qnnect.android.src.question.model.SearchQuestionDataModel
import com.iame.qnnect.android.src.search.model.SearchDataModel
import io.reactivex.Single

class SearchQuestionDataImpl(private val service: SearchQuestionAPI) : SearchQuestionDataModel {
    override fun getData(cafeId: Int, searchWord: String): Single<GetQuestionResponse> {
        return service.getSearch(cafeId, searchWord)
    }
}