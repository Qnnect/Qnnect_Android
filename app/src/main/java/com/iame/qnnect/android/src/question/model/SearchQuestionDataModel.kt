package com.iame.qnnect.android.src.question.model

import com.iame.qnnect.android.src.group.model.GetGroupResponse
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.main.bookmark.model.Bookmark
import io.reactivex.Single

interface SearchQuestionDataModel {
    fun getData(cafeId: Int, searchWord: String): Single<GetQuestionResponse>
}

