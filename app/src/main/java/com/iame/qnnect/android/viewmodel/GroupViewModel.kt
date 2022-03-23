package com.iame.qnnect.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.src.group.model.GetGroupRequest
import com.iame.qnnect.android.src.group.model.GetGroupResponse
import com.iame.qnnect.android.src.group.model.GroupDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GroupViewModel(var model: GroupDataModel) : BaseViewModel() {

    private val TAG = "GroupViewModel"

    private val getGroupResponse = MutableLiveData<GetGroupResponse>()
    val groupResponse: LiveData<GetGroupResponse>
        get() = getGroupResponse

    private val getErrorResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String>
        get() = getErrorResponse


    fun getGroup(cafeId: Int) {
        addDisposable(model.getData(cafeId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    getGroupResponse.postValue(this)
                }
            }, {
                getErrorResponse.postValue(it.message)
            })
        )
    }

}