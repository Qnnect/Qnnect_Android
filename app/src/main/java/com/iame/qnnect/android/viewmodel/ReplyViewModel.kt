package com.iame.qnnect.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.src.reply.model.GetReplyDataModel
import com.iame.qnnect.android.src.reply.model.GetReplyResponse
import com.iame.qnnect.android.src.reply.model.PostReplyDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ReplyViewModel(
    private val model: GetReplyDataModel,
    private val model2: PostReplyDataModel,
) : BaseViewModel() {

    private val TAG = "ReplyViewModel"

    // get reply
    private val getReplyResponse = MutableLiveData<GetReplyResponse>()
    val replyResponse: LiveData<GetReplyResponse>
        get() = getReplyResponse

    fun getReply(commentId: Int) {
        addDisposable(model.getData(commentId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    getReplyResponse.postValue(this)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }

    // post reply
    private val postReplyResponse = MutableLiveData<String?>()
    val po_replyResponse: LiveData<String?>
        get() = postReplyResponse

    fun postReply(commentId: Int, content: String) {
        addDisposable(model2.getData(commentId, content)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    postReplyResponse.postValue("200 OK")
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }
}