package com.iame.qnnect.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.model.response.ImageSearchResponse
import com.iame.qnnect.android.src.allow.model.PatchAlarmCheckResponse
import com.iame.qnnect.android.src.edit_question.model.EditQuestionDataModel
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.notarrivecafe.model.NotArriveEditDataModel
import com.iame.qnnect.android.src.notarrivecafe.model.NotArriveEditRequest
import com.iame.qnnect.android.src.question.model.PostQuestionDataModel
import com.iame.qnnect.android.src.question.model.PostQuestionRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NotArriveEditViewModel(private val model: NotArriveEditDataModel) : BaseViewModel() {

    private val TAG = "NotArriveEditViewModel"

    private val patchEditResponse = MutableLiveData<String>()
    val editResponse: LiveData<String>
        get() = patchEditResponse

    fun patchEdit(questionId: Int, notArriveEditRequest: NotArriveEditRequest) {
        addDisposable(model.getData(questionId, notArriveEditRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    patchEditResponse.postValue("200 OK")
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }


}