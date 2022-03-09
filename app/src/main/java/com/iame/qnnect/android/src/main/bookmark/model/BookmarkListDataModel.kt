package com.iame.qnnect.android.src.main.bookmark.model

import com.iame.qnnect.android.src.group.model.GetGroupResponse
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single

interface BookmarkListDataModel {
    fun getData(cafeId: Int): Single<List<Bookmark>>
}

