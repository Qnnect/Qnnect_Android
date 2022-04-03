package com.iame.qnnect.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.src.allow.model.AlarmCheckDataModel
import com.iame.qnnect.android.src.allow.model.PatchAlarmCheckResponse
import com.iame.qnnect.android.src.edit_alarm.model.GetUserAlarmStatusDataModel
import com.iame.qnnect.android.src.main.bookmark.model.*
import com.iame.qnnect.android.src.main.home.model.GetUserResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EditAlarmViewModel(private val model: GetUserAlarmStatusDataModel,
                         private val model2: AlarmCheckDataModel) : BaseViewModel() {

    private val TAG = "EditAlarmViewModel"

    private val getUserAlarmStatusResponse = MutableLiveData<Boolean>()
    val alarmstatusResponse: LiveData<Boolean>
        get() = getUserAlarmStatusResponse

    fun getUserAlarmStatus() {
        addDisposable(model.getAlarmStatus()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    getUserAlarmStatusResponse.postValue(this)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }

    private val patchAlarmCheckResponse = MutableLiveData<PatchAlarmCheckResponse>()
    val alarmCheckResponse: LiveData<PatchAlarmCheckResponse>
        get() = patchAlarmCheckResponse

    fun patchAlarmCheck(enableNotification: Boolean) {
        addDisposable(model2.patchAlarmCheck(enableNotification)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    var result = PatchAlarmCheckResponse("200 OK")
                    patchAlarmCheckResponse.postValue(result)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }
}