package com.iame.qnnect.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.model.response.ImageSearchResponse
import com.iame.qnnect.android.src.diary.model.DeleteScrapDataModel
import com.iame.qnnect.android.src.diary.model.PostScrapDataModel
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DiaryViewModel(private var model: PostScrapDataModel,
                     private var model2: DeleteScrapDataModel) : BaseViewModel() {

    private val TAG = "DiaryViewModel"

    private val postScrapResponse = MutableLiveData<String>()
    val scrapResponse: LiveData<String>
        get() = postScrapResponse


    fun postScrap(cafeQuestionId: Int) {
        addDisposable(model.getData(cafeQuestionId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    var response = "200 OK"
                    postScrapResponse.postValue(response)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }

    fun deleteScrap(cafeQuestionId: Int) {
        addDisposable(model2.getData(cafeQuestionId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    var response = "200 OK"
                    postScrapResponse.postValue(response)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }
}