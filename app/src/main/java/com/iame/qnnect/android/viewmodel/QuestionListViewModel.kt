package com.iame.qnnect.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.src.main.bookmark.model.*
import com.iame.qnnect.android.src.main.home.model.GetUserResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class QuestionListViewModel(private val model: BookmarkListDataModel) : BaseViewModel() {

    private val TAG = "BookmarkViewModel"


    // get user
    private val getBookmarkResponse = MutableLiveData<List<Bookmark>>()
    val bookmarkResponse: LiveData<List<Bookmark>>
        get() = getBookmarkResponse

    fun getBookamrk(cafeId: Int) {
        addDisposable(model.getData(cafeId)
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