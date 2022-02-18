package com.americano.qnnect.kotlin.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.americano.qnnect.kotlin.base.BaseViewModel
import com.americano.qnnect.kotlin.model.DataModel
import com.americano.qnnect.kotlin.model.enum.KakaoSearchSortEnum
import com.americano.qnnect.kotlin.model.response.ImageSearchResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SplashViewModel() : BaseViewModel() {

    private val TAG = "SplashViewModel"
}