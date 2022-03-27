package com.iame.qnnect.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.src.main.bookmark.model.*
import com.iame.qnnect.android.src.main.home.model.GetUserResponse
import com.iame.qnnect.android.src.question.model.GetQuestionResponse
import com.iame.qnnect.android.src.question.model.SearchQuestionDataModel
import com.iame.qnnect.android.src.search.model.SearchDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

class SearchQuestionViewModel(private val model: SearchQuestionDataModel) : BaseViewModel() {

    private val TAG = "SearchQuestionViewModel"

    // get search
    private val getQuestionResponse = MutableLiveData<GetQuestionResponse>()
    val questionResponse: LiveData<GetQuestionResponse>
        get() = getQuestionResponse

    private val getErrorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String>
        get() = getErrorResponse

    fun getBookamrk(cafeId: Int, searchWord: String) {
        addDisposable(model.getData(cafeId, searchWord)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    getQuestionResponse.postValue(this)
                }
            }, {
                getErrorResponse.postValue("Empty Response")
            })
        )
    }
}