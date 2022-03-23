package com.iame.qnnect.android.src.edit_question.service

import com.iame.qnnect.android.src.drink.model.GetUserDrinkResponse
import com.iame.qnnect.android.src.edit_drink.model.GetCurrentUserDrinkResponse
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single
import retrofit2.http.*

interface EditQuestionAPI {
    @PATCH("/api/v1/question/{cafeQuestionId}")
    fun editQuestion(@Path("cafeQuestionId") cafeQuestionId: Int,
                     @Body content: String) : Single<Unit>
}