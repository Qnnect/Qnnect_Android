package com.iame.qnnect.android.src.answer.service

import com.iame.qnnect.android.src.answer.model.PatchAnswerDataModel
import com.iame.qnnect.android.src.answer.model.PostAnswerDataModel
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.profile.model.PatchProfileResponse
import com.iame.qnnect.android.src.profile.model.ProfileDataModel
import io.reactivex.Single
import okhttp3.MultipartBody

class PatchAnswerDataImpl(private val service: PatchAnswerAPI) : PatchAnswerDataModel {
    override fun getData(
        image5: MultipartBody.Part?,
        image4: MultipartBody.Part?,
        image3: MultipartBody.Part?,
        image2: MultipartBody.Part?,
        image1: MultipartBody.Part?,
        content: MultipartBody.Part?,
        commentId: Int
    ): Single<Unit> {
        return service.patchAnswer(image5, image4, image3, image2, image1, content, commentId)
    }
}