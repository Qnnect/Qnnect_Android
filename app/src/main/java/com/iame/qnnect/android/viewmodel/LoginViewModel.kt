package com.iame.qnnect.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.model.response.ImageSearchResponse
import com.iame.qnnect.android.src.fcm.model.PostFcmTokenDataModel
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel(private val model: LoginDataModel,
                     private val model2: PostFcmTokenDataModel) : BaseViewModel() {

    private val TAG = "LoginViewModel"
    

    private val postLoginResponse = MutableLiveData<PostLoginResponse>()
    val loginResponse: LiveData<PostLoginResponse>
        get() = postLoginResponse


    fun postLogin(postLoginRequest: PostLoginRequest) {
        addDisposable(model.getData(postLoginRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    postLoginResponse.postValue(this)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }

    private val postFcmTokenResponse = MutableLiveData<String>()
    val fcmtokenResponse: LiveData<String>
        get() = postFcmTokenResponse

    private val errorCheckResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String>
        get() = errorCheckResponse

    fun postFcmToken(fcmToken: String) {
        addDisposable(model2.postFcmToken(fcmToken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    postFcmTokenResponse.postValue("200 OK")
                }
            }, {
                errorCheckResponse.postValue("네트워크가 원활하지 않습니다.")
            })
        )
    }
}