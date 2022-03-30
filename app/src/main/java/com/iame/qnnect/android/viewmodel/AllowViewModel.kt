package com.iame.qnnect.android.viewmodel

import android.util.Log
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.src.allow.model.AlarmCheckDataModel
import com.iame.qnnect.android.src.allow.model.PatchAlarmCheckResponse
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AllowViewModel(private val model: AlarmCheckDataModel) : BaseViewModel() {

    private val TAG = "AllowViewModel"

    private val patchAlarmCheckResponse = MutableLiveData<PatchAlarmCheckResponse>()
    val alarmCheckResponse: LiveData<PatchAlarmCheckResponse>
        get() = patchAlarmCheckResponse

    fun patchAlarmCheck(enableNotification: Boolean) {
        addDisposable(model.patchAlarmCheck(enableNotification)
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