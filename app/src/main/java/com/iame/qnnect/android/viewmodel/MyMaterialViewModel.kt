package com.iame.qnnect.android.viewmodel

import android.graphics.Color
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseViewModel
import com.iame.qnnect.android.src.edit_drink.model.MyIngredient
import com.iame.qnnect.android.src.store.model.GetMyMaterialAllDataModel
import com.iame.qnnect.android.src.store.model.GetMyMaterialDataModel
import com.iame.qnnect.android.util.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MyMaterialViewModel(private val model: GetMyMaterialAllDataModel,
                          private val model2: GetMyMaterialDataModel) : BaseViewModel() {

    private val TAG = "MyMaterialViewModel"

    fun recipe_click(select: TextView, item1: TextView, item2: TextView, item3: TextView){
        select.setBackgroundResource(R.drawable.store_recipe_select_custom)
        select.setTextColor(Color.parseColor("#FFFFFF"))

        item1.setBackgroundResource(R.drawable.store_recipe_custom)
        item1.setTextColor(Color.parseColor("#333333"))
        item2.setBackgroundResource(R.drawable.store_recipe_custom)
        item2.setTextColor(Color.parseColor("#333333"))
        item3.setBackgroundResource(R.drawable.store_recipe_custom)
        item3.setTextColor(Color.parseColor("#333333"))
    }

    private val getMyMaterialAllResponse = MutableLiveData<List<MyIngredient>>()
    val mymaterialAllResponse: LiveData<List<MyIngredient>>
        get() = getMyMaterialAllResponse

    fun getMyMaterialAll() {
        addDisposable(model.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    getMyMaterialAllResponse.postValue(this)
                }
            }, {
                Log.d("error_response : ", it.message.toString())
            })
        )
    }

    private val getMyMaterialResponse = MutableLiveData<List<MyIngredient>>()
    val mymaterialResponse: LiveData<List<MyIngredient>>
        get() = getMyMaterialResponse

    fun getMyMaterial(ingredientType: String) {
        addDisposable(model2.getData(ingredientType)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it.run {
                    getMyMaterialResponse.postValue(this)
                }
            }, {
                Log.d("error_response : ", it.message.toString())
            })
        )
    }

}