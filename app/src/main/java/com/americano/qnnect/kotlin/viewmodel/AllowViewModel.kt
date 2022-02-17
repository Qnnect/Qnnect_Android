package com.americano.qnnect.kotlin.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.CheckBox
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.americano.qnnect.kotlin.R
import com.americano.qnnect.kotlin.base.BaseViewModel
import com.americano.qnnect.kotlin.model.DataModel
import com.americano.qnnect.kotlin.model.enum.KakaoSearchSortEnum
import com.americano.qnnect.kotlin.model.response.ImageSearchResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AllowViewModel() : BaseViewModel() {

    private val TAG = "AllowViewModel"
}