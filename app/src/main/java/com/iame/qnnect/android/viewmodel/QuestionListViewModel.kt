package com.iame.qnnect.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.src.main.bookmark.model.*
import com.iame.qnnect.android.src.main.home.model.GetUserResponse
import com.iame.qnnect.android.src.question.model.GetQuestionDataModel
import com.iame.qnnect.android.src.question.model.GetQuestionResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class QuestionListViewModel(private val model: GetQuestionDataModel) : BaseViewModel() {

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

}