package com.iame.qnnect.android.src.add_drink.service

import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupResponse

interface AddDrinkView{
    fun onAddDrinkSuccess(response: String)
    fun onAddDrinkFailure(message: String)
}