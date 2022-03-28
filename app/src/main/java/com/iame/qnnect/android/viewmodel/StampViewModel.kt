package com.iame.qnnect.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.src.alarm.model.GetAlarmDataModel
import com.iame.qnnect.android.src.alarm.model.GetAlarmListResponse
import com.iame.qnnect.android.src.allow.model.PatchAlarmCheckResponse
import com.iame.qnnect.android.src.main.bookmark.model.*
import com.iame.qnnect.android.src.main.home.model.GetUserResponse
import com.iame.qnnect.android.src.stamp.model.Stamp
import com.iame.qnnect.android.src.stamp.model.StampDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class StampViewModel(private val model: StampDataModel) : BaseViewModel() {

    private val TAG = "StampViewModel"

    private val getStampResponse = MutableLiveData<List<Stamp>>()
    val stampResponse: LiveData<List<Stamp>>
        get() = getStampResponse

    fun getStamp() {
        addDisposable(model.getStamp()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    getStampResponse.postValue(this)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }
}