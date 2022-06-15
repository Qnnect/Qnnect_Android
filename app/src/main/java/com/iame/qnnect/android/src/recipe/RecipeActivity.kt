package com.iame.qnnect.android.src.recipe

import android.content.Intent
import android.util.Log
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.*
import com.iame.qnnect.android.src.question.QuestionCompleteDialog
import com.iame.qnnect.android.src.question.QuestionListActivity
import com.iame.qnnect.android.src.store.StoreActivity
import com.iame.qnnect.android.util.Getdrink
import com.iame.qnnect.android.util.GetdrinkName
import com.iame.qnnect.android.util.recipe
import com.iame.qnnect.android.viewmodel.*
import kotlinx.android.synthetic.main.activity_recipe.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeActivity : BaseActivity<ActivityRecipeBinding, RecipeViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_recipe // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: RecipeViewModel by viewModel()

    var drinkId = 0
    var cafeId = 0

    override fun initStartView() {
        drinkId = intent.getIntExtra("drinkId", 0)
        cafeId = intent.getIntExtra("cafeId", 0)

//        var drink = Getdrink(drinkId)
//        Glide.with(this)
//                .load(drink.img)
//                .transform(CenterCrop())
//                .into(drink_img)
//        drink_name.text = drink.name
    }

    override fun initDataBinding() {
        viewModel.recipeResponse.observe(this, Observer {
            var name = it.currentDrinkInfo.userDrinkName

            var drink = GetdrinkName(name)
            Glide.with(this)
                .load(drink.img)
                .transform(CenterCrop())
                .into(binding.drinkImg)
            binding.drinkName.text = drink.name

            var recipe = it.drinkRecipeResponses

            var ice = recipe.get(0)
            var img = recipe(ice.ingredientId)
            Glide.with(this)
                .load(img.img)
                .transform(CenterCrop())
                .into(binding.iceImg)
            binding.recipeIceTxt.text = ice.name
            binding.recipeIceCount.text = "X"+ice.count.toString()

            var base = recipe.get(1)
            img = recipe(base.ingredientId)
            Glide.with(this)
                .load(img.img)
                .transform(CenterCrop())
                .into(binding.baseImg)
            binding.recipeBaseTxt.text = base.name
            binding.recipeBaseCount.text = "X"+base.count.toString()

            var main = recipe.get(2)
            img = recipe(main.ingredientId)
            Glide.with(this)
                .load(img.img)
                .transform(CenterCrop())
                .into(binding.mainImg)
            binding.recipeMainTxt.text = main.name
            binding.recipeMainCount.text = "X"+main.count.toString()

            var topping = recipe.get(3)
            img = recipe(topping.ingredientId)
            Glide.with(this)
                .load(img.img)
                .transform(CenterCrop())
                .into(binding.toppingImg)
            if(topping.name == "바닐라 아이스크림"){
                binding.recipeToppingTxt.text = "바닐라\n아이스크림"
            }
            else{
                binding.recipeToppingTxt.text = topping.name
            }
            binding.recipeToppingCount.text = "X"+topping.count.toString()
        })
    }

    override fun initAfterBinding() {
        viewModel.getReciepe(drinkId, cafeId)
        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.storeBtn.setOnClickListener {
            var intent = Intent(this, StoreActivity::class.java)
            startActivity(intent)
        }
    }
}