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
import com.iame.qnnect.android.src.reply.reply_more.model.EditReplyDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EditReplyViewModel(private val model: EditReplyDataModel) : BaseViewModel() {

    private val TAG = "EditReplyViewModel"

    private val patcheditReplyResponse = MutableLiveData<String?>()
    val editreplyResponse: LiveData<String?>
        get() = patcheditReplyResponse

    fun editReply(commentId: Int, replyId: Int, content: String) {
        addDisposable(model.getData(commentId, replyId, content)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    var result = "200 OK"
                    patcheditReplyResponse.postValue(result)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }
}