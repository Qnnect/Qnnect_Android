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
import com.iame.qnnect.android.src.question.model.PostQuestionDataModel
import com.iame.qnnect.android.src.question.model.PostQuestionRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EditQuestionViewModel(private  val model: EditQuestionDataModel) : BaseViewModel() {

    private val TAG = "EditQuestionViewModel"

    private val patchEditQuestionResponse = MutableLiveData<String>()
    val editquestionResponse: LiveData<String>
        get() = patchEditQuestionResponse

    fun patchEditQuesiton(cafeQuestionId: Int, content: String) {
        addDisposable(model.getData(cafeQuestionId, content)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    patchEditQuestionResponse.postValue("200 OK")
                }
            }, {
                patchEditQuestionResponse.postValue("204 OK")
            })
        )
    }
}