package com.iame.qnnect.android.src.declare.service

import com.iame.qnnect.android.src.declare.model.DeclareUser
import com.iame.qnnect.android.src.declare.model.GetDeclareListDataModel
import com.iame.qnnect.android.src.declare.model.PostDeclareDataModel
import com.iame.qnnect.android.src.diary.model.DeleteQuestionDataModel
import com.iame.qnnect.android.src.diary.model.DeleteScrapDataModel
import com.iame.qnnect.android.src.diary.model.PostScrapDataModel
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.Single

class GetDeclareListDataImpl(private val service: GetDeclareListDrinkAPI) : GetDeclareListDataModel {
    override fun getData(): Single<List<DeclareUser>> {
        return service.getDeclareList()
    }
}