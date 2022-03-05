package com.iame.qnnect.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.src.group.model.GetGroupDataModel
import com.iame.qnnect.android.src.group.model.GetGroupRequest
import com.iame.qnnect.android.src.group.model.GetGroupResponse
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GroupViewModel(private val model: GetGroupDataModel) : BaseViewModel() {

    private val TAG = "GroupViewModel"

    private val getGroupResponse = MutableLiveData<GetGroupResponse>()
    val groupResponse: LiveData<GetGroupResponse>
        get() = getGroupResponse


    fun getGroup(getGroupRequest: GetGroupRequest) {
        addDisposable(model.getData(getGroupRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    getGroupResponse.postValue(this)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }

}