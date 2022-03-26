package com.iame.qnnect.android.src.main.mypage.service

import com.iame.qnnect.android.src.diary.model.DeleteQuestionDataModel
import com.iame.qnnect.android.src.diary.model.DeleteScrapDataModel
import com.iame.qnnect.android.src.diary.model.PostScrapDataModel
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.main.mypage.model.DeleteUserDataModel
import com.iame.qnnect.android.src.main.mypage.model.LogoutUserDataModel
import io.reactivex.Single

class LogoutUserDataImpl(private val service: LogoutUserAPI) : LogoutUserDataModel {
    override fun getData(): Single<Unit> {
        return service.logoutUser()
    }
}