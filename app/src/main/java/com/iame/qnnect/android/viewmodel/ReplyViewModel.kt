package com.iame.qnnect.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.src.declare.model.PostDeclareDataModel
import com.iame.qnnect.android.src.reply.model.DeleteAnswerDataModel
import com.iame.qnnect.android.src.reply.model.GetReplyDataModel
import com.iame.qnnect.android.src.reply.model.GetReplyResponse
import com.iame.qnnect.android.src.reply.model.PostReplyDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ReplyViewModel(
    private val model: GetReplyDataModel,
    private val model2: PostReplyDataModel,
    private val model3: DeleteAnswerDataModel,
    private val model4: PostDeclareDataModel
) : BaseViewModel() {

    private val TAG = "ReplyViewModel"

    // delete answer
    private val postDeclareResponse = MutableLiveData<String>()
    val declareResponse: LiveData<String>
        get() = postDeclareResponse

    private val errorDeclareResponse = MutableLiveData<String>()    // mutable 한것을 가리고 immutable 한것을 가져다 쓰려고
    val erdeclareResponse: LiveData<String>
        get() = errorDeclareResponse    // 왜 get() 을 썼는지에 대한 원리 및 -> get()을 쓰면 호출시 마다 초기화시켜주는 목적으로

    // addDisposable 등등 왜 사용하는지
    fun declare(reportId: Int) {
        addDisposable(model4.getData(reportId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    postDeclareResponse.postValue("200 OK")
                }
            }, {
                if(it.message.toString() == "null"){
                    errorDeclareResponse.postValue("본인은 신고할 수 없습니다!")
                }
                else{

                }
                Log.d(TAG, "response error, message : ${it.cause}")
            })
        )
    }

    // delete answer
    private val deleteAnswerResponse = MutableLiveData<String>()
    val answerResponse: LiveData<String>
        get() = deleteAnswerResponse

    fun deleteAnswer(commentId: Int) {
        addDisposable(model3.getData(commentId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    deleteAnswerResponse.postValue("200 OK")
                }
            }, {
                if(it.message.toString() == "null"){
                    deleteAnswerResponse.postValue("200 OK")
                }
                Log.d(TAG, "response error, message : ${it.cause}")
            })
        )
    }

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