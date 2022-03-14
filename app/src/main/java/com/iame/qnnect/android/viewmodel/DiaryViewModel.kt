package com.iame.qnnect.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.model.response.ImageSearchResponse
import com.iame.qnnect.android.src.diary.model.*
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.main.home.model.GetUserResponse
import com.iame.qnnect.android.src.main.home.model.UserDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DiaryViewModel(private var model: PostScrapDataModel,
                     private var model2: DeleteScrapDataModel,
                     private var model3: GetQuestionDataModel,
                     private var model4: UserDataModel,
                     private var model5: PostLikeDataModel) : BaseViewModel() {

    private val TAG = "DiaryViewModel"

    private val postLikeResponse = MutableLiveData<String>()
    val likeResponse: LiveData<String>
        get() = postLikeResponse


    fun postLike(cafeQuestionId: Int, isUserLiked: Boolean) {
        addDisposable(model5.getData(cafeQuestionId, isUserLiked)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    var response = "200 OK"
                    postLikeResponse.postValue(response)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }

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

    private val getQuestionResponse = MutableLiveData<GetQuestionResponse>()
    val questionResponse: LiveData<GetQuestionResponse>
        get() = getQuestionResponse


    fun getQuestion(cafeQuestionId: Int) {
        addDisposable(model3.getData(cafeQuestionId)
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

    // get user
    private val getUserResponse = MutableLiveData<GetUserResponse>()
    val userResponse: LiveData<GetUserResponse>
        get() = getUserResponse

    fun getUser() {
        addDisposable(model4.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    getUserResponse.postValue(this)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }
}