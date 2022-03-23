package com.iame.qnnect.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.src.allow.model.PatchAlarmCheckResponse
import com.iame.qnnect.android.src.declare.model.DeclareUser
import com.iame.qnnect.android.src.declare.model.DeleteDeclareDataModel
import com.iame.qnnect.android.src.declare.model.GetDeclareListDataModel
import com.iame.qnnect.android.src.main.bookmark.model.*
import com.iame.qnnect.android.src.main.home.model.GetUserResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserDeclareViewModel(private val model: GetDeclareListDataModel,
                           private val model2: DeleteDeclareDataModel) : BaseViewModel() {

    private val TAG = "UserDeclareViewModel"

    private val getDeclareResponse = MutableLiveData<List<DeclareUser>>()
    val declareResponse: LiveData<List<DeclareUser>>
        get() = getDeclareResponse

    fun getDeclareList() {
        addDisposable(model.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    getDeclareResponse.postValue(this)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }

    private val deleteDeclareResponse = MutableLiveData<String>()
    val dedeclareResponse: LiveData<String>
        get() = deleteDeclareResponse

    fun deleteDeclare(reportId: Int) {
        addDisposable(model2.getData(reportId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    deleteDeclareResponse.postValue("200 OK")
                }
            }, {
                deleteDeclareResponse.postValue("204 OK")
            })
        )
    }
}