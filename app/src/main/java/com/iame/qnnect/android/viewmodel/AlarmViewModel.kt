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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AlarmViewModel(private val model: GetAlarmDataModel) : BaseViewModel() {

    private val TAG = "AlarmViewModel"

    private val getAlarmResponse = MutableLiveData<List<GetAlarmListResponse>>()
    val alarmResponse: LiveData<List<GetAlarmListResponse>>
        get() = getAlarmResponse

    fun getAlarm() {
        addDisposable(model.getAlarm()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    getAlarmResponse.postValue(this)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }
}