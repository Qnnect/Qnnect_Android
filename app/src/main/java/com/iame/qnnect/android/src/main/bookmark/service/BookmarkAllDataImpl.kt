package com.iame.qnnect.android.src.main.bookmark.service

import com.iame.qnnect.android.src.group.model.GetGroupRequest
import com.iame.qnnect.android.src.group.model.GetGroupResponse
import com.iame.qnnect.android.src.group.model.GroupDataModel
import com.iame.qnnect.android.src.main.bookmark.model.*
import com.iame.qnnect.android.src.main.home.home_model.GetHomeResponse
import com.iame.qnnect.android.src.main.home.home_model.HomeDataModel
import io.reactivex.Single

class BookmarkAllDataImpl(private val service: BookmarkAllAPI) : BookmarkAllDataModel {
    override fun getData(): Single<List<Bookmark>> {
        return service.getBookmark()
    }
}