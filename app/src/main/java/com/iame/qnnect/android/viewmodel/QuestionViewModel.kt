package com.iame.qnnect.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.model.response.ImageSearchResponse
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.question.model.PostQuestionDataModel
import com.iame.qnnect.android.src.question.model.PostQuestionRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class QuestionViewModel(private val model: PostQuestionDataModel) : BaseViewModel() {

    private val TAG = "QuestionViewModel"

    private val postQuestionResponse = MutableLiveData<String>()    // view 에서 보여줄 만한 변수명으로
    val postquestionResponse: LiveData<String>
        get() = postQuestionResponse

    fun postQuestion(cafeId: Int, contents: String) {
        addDisposable(model.getData(cafeId, contents)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    postQuestionResponse.postValue("200 OK")
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }
}