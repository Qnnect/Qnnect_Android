package com.iame.qnnect.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.model.response.ImageSearchResponse
import com.iame.qnnect.android.src.declare.model.PostDeclareDataModel
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
                     private var model5: PostLikeDataModel,
                     private var model6: DeleteQuestionDataModel,
                     private var model7: PostDeclareDataModel) : BaseViewModel() {

    private val TAG = "DiaryViewModel"

    // declare answer
    private val postDeclareResponse = MutableLiveData<String>()
    val declareResponse: LiveData<String>
        get() = postDeclareResponse

    private val errorDeclareResponse = MutableLiveData<String>()
    val erdeclareResponse: LiveData<String>
        get() = errorDeclareResponse

    fun declare(reportId: Int) {
        addDisposable(model7.getData(reportId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    postDeclareResponse.postValue("200 OK")
                }
            }, {
                if(it.message.toString() == "null"){
                    errorDeclareResponse.postValue("본인은 신고할 수 없습니다!")
                }
                else{

                }
                Log.d(TAG, "response error, message : ${it.cause}")
            })
        )
    }

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

    private val deleteQuestionResponse = MutableLiveData<String>()
    val deQuestionResponse: LiveData<String>
        get() = deleteQuestionResponse


    fun deleteQuestion(cafeQuestionId: Int) {
        addDisposable(model6.getData(cafeQuestionId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    var response = "200 OK"
                    postLikeResponse.postValue(response)
                }
            }, {
                var response = "204 OK"
                postLikeResponse.postValue(response)
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
                var response = "204 Delete OK"
                postScrapResponse.postValue(response)
            })
        )
    }

    // get Question
    private val getQuestionResponse = MutableLiveData<GetQuestionResponse>()
    val questionResponse: LiveData<GetQuestionResponse>
        get() = getQuestionResponse

    // getQuestion Error
    private val getErrorResponse = MutableLiveData<String>()
    val questionerrorResponse: LiveData<String>
        get() = getErrorResponse

    fun getQuestion(cafeQuestionId: Int) {
        addDisposable(model3.getData(cafeQuestionId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    getQuestionResponse.postValue(this)
                }
            }, {
                getErrorResponse.postValue("아직 카페에 전달되지 않은 질문입니다.")
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