package com.iame.qnnect.android.viewmodel

import android.graphics.Color
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.src.store.model.PostBuyMaterialDataModel
import com.iame.qnnect.android.util.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class StoreActivityViewModel(private val model: PostBuyMaterialDataModel) : BaseViewModel() {

    private val TAG = "StoreActivityViewModel"

    fun recipe_click(select: TextView, item1: TextView, item2: TextView, item3: TextView){
        select.setBackgroundResource(R.drawable.store_recipe_select_custom)

        item1.setBackgroundResource(R.drawable.store_recipe_custom)
        item1.setTextColor(Color.parseColor("#333333"))
        item2.setBackgroundResource(R.drawable.store_recipe_custom)
        item2.setTextColor(Color.parseColor("#333333"))
        item3.setBackgroundResource(R.drawable.store_recipe_custom)
        item3.setTextColor(Color.parseColor("#333333"))
    }

    private val postbuymaterialResponse = MutableLiveData<String>()
    val buymaterialResponse: LiveData<String>
        get() = postbuymaterialResponse

    private val errorResponse = MutableLiveData<String>()
    val error: LiveData<String>
        get() = errorResponse

    fun postBuyMateial(ingredientsId: Int) {
        addDisposable(model.getData(ingredientsId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    postbuymaterialResponse.postValue("200 OK")
                }
            }, {
                errorResponse.postValue("포인트가 부족합니다!")
            })
        )
    }

}