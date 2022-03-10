package com.iame.qnnect.android.src.search.model

import com.iame.qnnect.android.src.group.model.GetGroupResponse
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.main.bookmark.model.Bookmark
import io.reactivex.Single

interface SearchDataModel {
    fun getData(searchWord: String): Single<List<Bookmark>>
}

