package com.iame.qnnect.android.src.edit_question.model

import com.iame.qnnect.android.src.drink.model.GetUserDrinkResponse
import com.iame.qnnect.android.src.edit_drink.model.GetCurrentUserDrinkResponse
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single

interface EditQuestionDataModel {
    fun getData(cafeQuestionId: Int, content: String): Single<Unit>
}

