package com.iame.qnnect.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.src.main.bookmark.model.*
import com.iame.qnnect.android.src.main.home.model.GetUserResponse
import com.iame.qnnect.android.src.search.model.SearchDataModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

class SearchViewModel(private val model: SearchDataModel) : BaseViewModel() {

    private val TAG = "BookmarkViewModel"

    // get search
    private val getBookmarkResponse = MutableLiveData<List<Bookmark>>()
    val bookmarkResponse: LiveData<List<Bookmark>>
        get() = getBookmarkResponse

    fun getBookamrk(searchWord: String) {
        addDisposable(model.getData(searchWord)
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