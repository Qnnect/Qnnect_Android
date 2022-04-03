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
import com.iame.qnnect.android.src.fcm.model.PostFcmTokenDataModel
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AllowViewModel(private val model: AlarmCheckDataModel,
                     private val model2: PostFcmTokenDataModel) : BaseViewModel() {

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

    private val postFcmTokenResponse = MutableLiveData<String>()
    val fcmtokenResponse: LiveData<String>
        get() = postFcmTokenResponse

    private val errorCheckResponse = MutableLiveData<String>()
    val errorResponse: LiveData<String>
        get() = errorCheckResponse

    fun postFcmToken(fcmToken: String) {
        addDisposable(model2.postFcmToken(fcmToken)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    postFcmTokenResponse.postValue("200 OK")
                }
            }, {
                errorCheckResponse.postValue("네트워크가 원활하지 않습니다.")
            })
        )
    }
}