package com.iame.qnnect.android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.model.response.ImageSearchResponse
import com.iame.qnnect.android.src.login.model.LoginDataModel
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.question.model.PostQuestionRequest
import com.iame.qnnect.android.src.recipe.model.GetRecipeDataModel
import com.iame.qnnect.android.src.recipe.model.GetRecipeResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RecipeViewModel(private val model: GetRecipeDataModel) : BaseViewModel() {

    private val TAG = "RecipeViewModel"

    private val getRecipeResponse = MutableLiveData<GetRecipeResponse>()
    val recipeResponse: LiveData<GetRecipeResponse>
        get() = getRecipeResponse


    fun getReciepe(userDrinkSelectedId: Int, cafeId: Int) {
        addDisposable(model.getData(userDrinkSelectedId, cafeId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    getRecipeResponse.postValue(this)
                }
            }, {
                Log.d(TAG, "response error, message : ${it.message}")
            })
        )
    }
}