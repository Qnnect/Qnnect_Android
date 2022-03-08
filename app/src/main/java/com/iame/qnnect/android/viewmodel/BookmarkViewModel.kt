package com.iame.qnnect.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.src.main.bookmark.model.Cafe
import com.iame.qnnect.android.src.main.bookmark.model.CafeListDataModel
import com.iame.qnnect.android.src.main.bookmark.model.GetCafeListResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BookmarkViewModel(private val model: CafeListDataModel) : BaseViewModel() {

    private val TAG = "BookmarkViewModel"

    private val getCafesResponse = MutableLiveData<List<Cafe>>()
    val cafesResponse: LiveData<List<Cafe>>
        get() = getCafesResponse

    fun getCafes() {
        addDisposable(model.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    getCafesResponse.postValue(this)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }

}