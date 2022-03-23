package com.iame.qnnect.android.src.edit_question.service

import com.iame.qnnect.android.src.drink.model.GetUserDrinkDataModel
import com.iame.qnnect.android.src.drink.model.GetUserDrinkResponse
import com.iame.qnnect.android.src.edit_drink.model.GetCurrentDrinkDataModel
import com.iame.qnnect.android.src.edit_drink.model.GetCurrentUserDrinkResponse
import com.iame.qnnect.android.src.edit_question.model.EditQuestionDataModel
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single

class EditQuestionImpl(private val service: EditQuestionAPI) : EditQuestionDataModel {
    override fun getData(cafeQuestionId: Int, content: String): Single<Unit> {
        return service.editQuestion(cafeQuestionId, content)
    }
}