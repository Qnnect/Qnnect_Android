package com.iame.qnnect.android.src.add_drink.service

import com.iame.qnnect.android.MyApplication
import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupResponse
import com.iame.qnnect.android.src.main.home.home_bottom.service.AddGroupAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddDrinkService(val view: AddDrinkView) {
    fun tryAddDrink(cafeId: Int, drinkId: Int){
        val adddrinkAPI = MyApplication.sRetrofit.create(AddDrinkAPI::class.java)
        adddrinkAPI.addDrink(cafeId, drinkId).enqueue(object :
            Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                view.onAddDrinkSuccess("200 OK")
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                view.onAddDrinkFailure(t.message ?: "통신 오류")
            }
        })
    }
}