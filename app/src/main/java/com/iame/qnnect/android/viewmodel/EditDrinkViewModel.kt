package com.iame.qnnect.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.base.BaseErrorResponse
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.model.response.ImageSearchResponse
import com.iame.qnnect.android.src.edit_drink.model.GetCurrentDrinkDataModel
import com.iame.qnnect.android.src.edit_drink.model.GetCurrentUserDrinkResponse
import com.iame.qnnect.android.src.edit_drink.model.PostEditDrinkDataModel
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EditDrinkViewModel(private val model: GetCurrentDrinkDataModel,
                         private val model2: PostEditDrinkDataModel) : BaseViewModel() {

    private val TAG = "EditDrinkViewModel"

    private val getCurrentUserDrinkResponse = MutableLiveData<GetCurrentUserDrinkResponse>()
    val currentUserDrinkResponse: LiveData<GetCurrentUserDrinkResponse>
        get() = getCurrentUserDrinkResponse

    fun getCurrentDrink(cafeId: Int) {
        addDisposable(model.getData(cafeId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    getCurrentUserDrinkResponse.postValue(this)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }

    private val postEditDrinkResponse = MutableLiveData<String>()
    val editdrinkResponse: LiveData<String>
        get() = postEditDrinkResponse

    private val postEditDrinkError = MutableLiveData<String>()
    val editdrinkError: LiveData<String>
        get() = postEditDrinkError

    fun postEditDrink(userDrinkSelectedId: Int, ingredientsId: Int) {
        addDisposable(model2.getData(userDrinkSelectedId, ingredientsId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    postEditDrinkResponse.postValue("200 OK")
                }
            }, {
                var response = it
                Log.d("error_response", response.localizedMessage.toString())
                if(response.localizedMessage.toString() == "HTTP 406 "){
                    postEditDrinkError.postValue("해당 주재료의 단계가 아닙니다!")
                }
                else{
                    postEditDrinkError.postValue("")
                }
            })
        )
    }
}