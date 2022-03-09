package com.iame.qnnect.android.src.main.bookmark.model

import com.google.gson.annotations.SerializedName
import com.iame.qnnect.android.src.group.model.CafeUser
import com.iame.qnnect.android.src.main.home.model.GetUserResponse

data class GetBookmarkListResponse(
    var cafeList: List<Bookmark>
    )