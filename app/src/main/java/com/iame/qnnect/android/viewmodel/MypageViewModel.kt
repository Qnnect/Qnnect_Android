package com.iame.qnnect.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.src.main.home.model.GetUserResponse
import com.iame.qnnect.android.src.main.home.model.UserDataModel
import com.iame.qnnect.android.src.profile.model.ProfileDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MypageViewModel(private val model: UserDataModel) : BaseViewModel() {

    private val TAG = "MypageViewModel"

    // get user
    private val getUserResponse = MutableLiveData<GetUserResponse>()
    val userResponse: LiveData<GetUserResponse>
        get() = getUserResponse

    fun getUser() {
        addDisposable(model.getData()
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