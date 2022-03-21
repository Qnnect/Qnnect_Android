package com.iame.qnnect.android.src.question.model

import com.iame.qnnect.android.src.main.bookmark.model.GetBookmarkListResponse
import com.iame.qnnect.android.src.profile.model.PatchProfileResponse
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.Part

interface GetQuestionDataModel {
    fun getData(cafeId: Int): Single<GetQuestionResponse>
}
