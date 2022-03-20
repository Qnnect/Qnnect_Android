package com.iame.qnnect.android.src.finish_drink

import android.graphics.Color
import android.view.WindowManager
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityFinishDrinkBinding
import com.iame.qnnect.android.util.Getdrink
import com.iame.qnnect.android.viewmodel.FinishDrinkViewModel
import kotlinx.android.synthetic.main.activity_finish_drink.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FinishDrinkActivity : BaseActivity<ActivityFinishDrinkBinding, FinishDrinkViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_finish_drink // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: FinishDrinkViewModel by viewModel()

    var drinkId = 0

    override fun initStartView() {
        var window = getWindow()
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.parseColor("#FFD373")
        window.decorView.systemUiVisibility = 0
    }

    override fun initDataBinding() {
        drinkId = intent.getIntExtra("drinkId", 0)
        var drink = Getdrink(drinkId)
        drink_img.setImageResource(drink.img)
        complete_name.text = drink.name+" 완성!"
    }

    override fun initAfterBinding() {
        main.setOnClickListener {
            finish()
        }
    }
}