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
                var response = PostRefreshResponse("", "")
                postRefreshResponse.postValue(response)
            })
        )
    }
}