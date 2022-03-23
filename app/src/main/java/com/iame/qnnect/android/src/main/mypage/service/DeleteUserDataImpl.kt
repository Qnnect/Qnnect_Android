package com.iame.qnnect.android.src.main.mypage.service

import com.iame.qnnect.android.src.diary.model.DeleteQuestionDataModel
import com.iame.qnnect.android.src.diary.model.DeleteScrapDataModel
import com.iame.qnnect.android.src.diary.model.PostScrapDataModel
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.main.mypage.model.DeleteUserDataModel
import io.reactivex.Single

class DeleteUserDataImpl(private val service: DeleteUserAPI) : DeleteUserDataModel {
    override fun getData(): Single<Unit> {
        return service.deleteUser()
    }
}