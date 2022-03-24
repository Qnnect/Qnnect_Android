package com.iame.qnnect.android.src.question.service

import com.iame.qnnect.android.src.main.bookmark.model.Bookmark
import io.reactivex.Single
import retrofit2.http.*

interface UserQuestionAllAPI {
    @GET("/api/v1/user/question/all")
    fun getQuestion() : Single<List<Bookmark>>
}