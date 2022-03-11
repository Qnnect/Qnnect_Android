package com.iame.qnnect.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.src.main.bookmark.model.*
import com.iame.qnnect.android.src.main.home.model.GetUserResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BookmarkViewModel(private val model: CafeListDataModel,
                        private val model2: BookmarkListDataModel,
                        private val model3: BookmarkAllDataModel) : BaseViewModel() {

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

    // get user
    private val getBookmarkResponse = MutableLiveData<List<Bookmark>>()
    val bookmarkResponse: LiveData<List<Bookmark>>
        get() = getBookmarkResponse

    fun getBookamrk(cafeId: Int) {
        addDisposable(model2.getData(cafeId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    getBookmarkResponse.postValue(this)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }

    fun getAllBookamrk() {
        addDisposable(model3.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    getBookmarkResponse.postValue(this)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }

}