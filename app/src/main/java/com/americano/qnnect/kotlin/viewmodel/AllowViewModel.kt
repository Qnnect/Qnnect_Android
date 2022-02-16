package com.americano.qnnect.kotlin.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.CheckBox
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.americano.qnnect.kotlin.base.BaseViewModel
import com.americano.qnnect.kotlin.model.DataModel
import com.americano.qnnect.kotlin.model.enum.KakaoSearchSortEnum
import com.americano.qnnect.kotlin.model.response.ImageSearchResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AllowViewModel() : BaseViewModel() {

    private val TAG = "AllowViewModel"

    var allChecked: Boolean = false
    var onChecked1: Boolean = false
    var onChecked2: Boolean = false
    var onChecked3: Boolean = false


    // checkBox check 여부 확인
    fun onCheckedBoxClicked(): Boolean {
        return onChecked1 || onChecked2 || onChecked3
    }

    fun allChecked(): Boolean{
        return allChecked
    }

//    private val _imageSearchResponseLiveData = MutableLiveData<ImageSearchResponse>()
//
//    val imageSearchResponseLiveData: LiveData<ImageSearchResponse>
//        get() = _imageSearchResponseLiveData

//    fun getImageSearch(query: String, page: Int, size: Int) {
//        addDisposable(model.getData(query, KakaoSearchSortEnum.Accuracy, page, size)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                it.run {
//                    if (documents.size > 0) {
//                        Log.d(TAG, "documents : $documents")
//                        _imageSearchResponseLiveData.postValue(this)
//                    }
//                    Log.d(TAG, "meta : $meta")
//                }
//            }, {
//                Log.d(TAG, "response error, message : ${it.message}")
//            })
//        )
//    }
}