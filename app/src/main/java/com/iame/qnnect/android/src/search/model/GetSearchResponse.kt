package com.iame.qnnect.android.src.search.model

import com.google.gson.annotations.SerializedName
import com.iame.qnnect.android.src.group.model.CafeUser
import com.iame.qnnect.android.src.main.bookmark.model.Bookmark
import com.iame.qnnect.android.src.main.home.model.GetUserResponse

data class GetSearchResponse(
    var bookmarkList: List<Bookmark>
    )