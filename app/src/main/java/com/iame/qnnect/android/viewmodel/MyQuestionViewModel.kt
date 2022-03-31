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
import com.iame.qnnect.android.src.notarrivecafe.model.NotArriveDeleteDataModel
import com.iame.qnnect.android.src.question.model.PostQuestionDataModel
import com.iame.qnnect.android.src.question.model.PostQuestionRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MyQuestionViewModel(private val model: NotArriveDeleteDataModel) : BaseViewModel() {

    private val TAG = "MyQuestionViewModel"

    private val DeleteResponse = MutableLiveData<String>()
    val deleteResponse: LiveData<String>
        get() = DeleteResponse

    fun deleteQuestion(questionId: Int) {
        addDisposable(model.getData(questionId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    DeleteResponse.postValue("200 OK")
                }
            }, {
                DeleteResponse.postValue("204 OK")
            })
        )
    }
}