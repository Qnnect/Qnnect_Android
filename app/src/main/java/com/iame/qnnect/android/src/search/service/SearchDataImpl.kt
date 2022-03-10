package com.iame.qnnect.android.src.search.service

import com.iame.qnnect.android.src.group.model.GetGroupRequest
import com.iame.qnnect.android.src.group.model.GetGroupResponse
import com.iame.qnnect.android.src.group.model.GroupDataModel
import com.iame.qnnect.android.src.main.bookmark.model.*
import com.iame.qnnect.android.src.main.bookmark.service.BookmarkListAPI
import com.iame.qnnect.android.src.main.home.home_model.GetHomeResponse
import com.iame.qnnect.android.src.main.home.home_model.HomeDataModel
import com.iame.qnnect.android.src.search.model.SearchDataModel
import io.reactivex.Single

class SearchDataImpl(private val service: SearchAPI) : SearchDataModel {
    override fun getData(searchWord: String): Single<List<Bookmark>> {
        return service.getSearch(searchWord)
    }
}