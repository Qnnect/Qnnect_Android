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

    fun patchAlarmCheck(header: String, enableNotification: Boolean) {
        addDisposable(model.patchAlarmCheck(header, enableNotification)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    patchAlarmCheckResponse.postValue(this)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }

    fun radioSelectAll(select_item: CheckBox,
                       item1: CheckBox, item2: CheckBox, item3: CheckBox
                       , nextBtn: ConstraintLayout){
        var check = select_item.isChecked
        if(check){
            nextBtn.setBackgroundResource(R.drawable.allow_btn_ok)
        }
        else{
            nextBtn.setBackgroundResource(R.drawable.allow_btn_fail)
        }
        item1.isChecked = check
        item2.isChecked = check
        item3.isChecked = check
    }

    fun radio_select(select_item: CheckBox, item: CheckBox, nextBtn: ConstraintLayout){
        if(select_item.isChecked && item.isChecked){
            nextBtn.setBackgroundResource(R.drawable.allow_btn_ok)
        }
        else{
            nextBtn.setBackgroundResource(R.drawable.allow_btn_fail)
        }
    }

    fun checkNext(item1: CheckBox, item2: CheckBox): Boolean{
        return item1.isChecked && item2.isChecked
    }
}