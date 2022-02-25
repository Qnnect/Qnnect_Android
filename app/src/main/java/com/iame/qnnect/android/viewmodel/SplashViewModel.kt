package com.iame.qnnect.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.splash.model.PostRefreshRequest
import com.iame.qnnect.android.src.splash.model.PostRefreshResponse
import com.iame.qnnect.android.src.splash.model.RefreshDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SplashViewModel(private val model: RefreshDataModel) : BaseViewModel() {

    private val TAG = "SplashViewModel"

    private val postRefreshResponse = MutableLiveData<PostRefreshResponse>()
    val refreshResponse: LiveData<PostRefreshResponse>
        get() = postRefreshResponse


    fun postRefresh(postRefreshRequest: PostRefreshRequest) {
        addDisposable(model.postRefresh(postRefreshRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    postRefreshResponse.postValue(this)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }
}

//class LoginViewModel(private val model: LoginDataModel) : BaseViewModel() {
//
//    private val TAG = "LoginViewModel"
//
//    private val postLoginResponse = MutableLiveData<PostLoginResponse>()
//    val loginResponse: LiveData<PostLoginResponse>
//        get() = postLoginResponse
//
//
//    fun postLogin(postLoginRequest: PostLoginRequest) {
//        addDisposable(model.getData(postLoginRequest)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                it.run {
//                    postLoginResponse.postValue(this)
//                }
//            }, {
//                Log.d(TAG, "response error, message : ${it.message}")
//            })
//        )
//    }
//}