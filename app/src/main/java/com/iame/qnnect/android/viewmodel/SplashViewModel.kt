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
import android.content.pm.PackageInfo
import com.google.android.material.internal.ContextUtils.getActivity
import com.iame.qnnect.android.MyApplication
import com.iame.qnnect.android.src.splash.model.VersionCheckDataModel
import java.lang.Exception


class SplashViewModel(private val model: RefreshDataModel,
                      private val model2: VersionCheckDataModel) : BaseViewModel() {

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

    private val getVersionCheckResponse = MutableLiveData<Boolean>()
    val versioncheckResponse: LiveData<Boolean>
        get() = getVersionCheckResponse

    private val errorVersionCheckResponse = MutableLiveData<String>()
    val errorversioncheckResponse: LiveData<String>
        get() = errorVersionCheckResponse


    fun getVersionCheck(currentVersion: String, osType: String) {
        addDisposable(model2.getVersionCheck(currentVersion, osType)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    getVersionCheckResponse.postValue(this)
                }
            }, {
                errorVersionCheckResponse.postValue("네트워크가 원활하지 않습니다.")
            })
        )
    }
}