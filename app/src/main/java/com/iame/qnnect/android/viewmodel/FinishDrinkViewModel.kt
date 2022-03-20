package com.iame.qnnect.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.model.response.ImageSearchResponse
import com.iame.qnnect.android.src.allow.model.PatchAlarmCheckResponse
import com.iame.qnnect.android.src.drink.model.GetUserDrinkDataModel
import com.iame.qnnect.android.src.drink.model.GetUserDrinkResponse
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FinishDrinkViewModel() : BaseViewModel() {

    private val TAG = "FinishDrinkViewModel"
}