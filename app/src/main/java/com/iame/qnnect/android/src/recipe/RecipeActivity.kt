package com.iame.qnnect.android.src.recipe

import android.content.Intent
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.*
import com.iame.qnnect.android.src.store.StoreActivity
import com.iame.qnnect.android.viewmodel.*
import kotlinx.android.synthetic.main.activity_recipe.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeActivity : BaseActivity<ActivityRecipeBinding, RecipeViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_recipe // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: RecipeViewModel by viewModel()


    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
        back_btn.setOnClickListener {
            finish()
        }

        store_btn.setOnClickListener {
            var intent = Intent(this, StoreActivity::class.java)
            startActivity(intent)
        }
    }
}