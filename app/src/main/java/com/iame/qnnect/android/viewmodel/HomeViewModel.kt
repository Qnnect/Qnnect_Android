package com.iame.qnnect.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.main.home.home_model.GetHomeResponse
import com.iame.qnnect.android.src.main.home.home_model.HomeDataModel
import com.iame.qnnect.android.src.main.home.model.GetUserResponse
import com.iame.qnnect.android.src.main.home.model.UserDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val model: HomeDataModel) : BaseViewModel() {

    private val TAG = "HomeViewModel"

    private val getHomeResponse = MutableLiveData<GetHomeResponse>()
    val homeResponse: LiveData<GetHomeResponse>
        get() = getHomeResponse

    fun getHome() {
        addDisposable(model.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    getHomeResponse.postValue(this)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }
}