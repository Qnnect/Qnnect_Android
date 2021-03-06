package com.iame.qnnect.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.src.main.bookmark.model.*
import com.iame.qnnect.android.src.question.model.GetQuestionDataModel
import com.iame.qnnect.android.src.question.model.GetQuestionResponse
import com.iame.qnnect.android.src.question.model.GetUserQuestionDataModel
import com.iame.qnnect.android.src.question.model.GetUserQuestionListResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class QuestionListViewModel(private val model: GetQuestionDataModel,
                            private val model2: GetUserQuestionDataModel) : BaseViewModel() {

    private val TAG = "QuestionListViewModel"

    // get questionList
    private val getQuestionResponse = MutableLiveData<GetQuestionResponse>()
    val questionResponse: LiveData<GetQuestionResponse>
        get() = getQuestionResponse

    fun getQuestion(cafeId: Int) {
        addDisposable(model.getData(cafeId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    getQuestionResponse.postValue(this)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }

    // get userQuestion all
    private val getUserQuestionResponse = MutableLiveData<List<GetUserQuestionListResponse>>()
    val userquestionResponse: LiveData<List<GetUserQuestionListResponse>>
        get() = getUserQuestionResponse

    private val errorQuestionResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String>
        get() = errorQuestionResponse

    fun getUserQuestion() {
        addDisposable(model2.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    getUserQuestionResponse.postValue(this)
                }
            }, {
                errorQuestionResponse.postValue("error")
            })
        )
    }

}