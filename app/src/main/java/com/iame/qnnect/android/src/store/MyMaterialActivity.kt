package com.iame.qnnect.android.src.store

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.*
import com.iame.qnnect.android.src.main.store.RecipeAdapter
import com.iame.qnnect.android.src.main.store.RecipeDialog
import com.iame.qnnect.android.util.*
import com.iame.qnnect.android.viewmodel.MyMaterialViewModel
import kotlinx.android.synthetic.main.activity_mymaterial.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyMaterialActivity : BaseActivity<ActivityMymaterialBinding, MyMaterialViewModel>(){

    override val layoutResourceId: Int
        get() = R.layout.activity_mymaterial // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: MyMaterialViewModel by viewModel()

    private val recipeAdapter: RecipeAdapter by inject()

    var recipe_list = ArrayList<recipe>()

    override fun initStartView() {
        // member recycler
        recipe_recycler.run {
            adapter = recipeAdapter
            layoutManager = GridLayoutManager(context, 2).apply {
                orientation = GridLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        recipe_list = allrecipe()
        recipeInit(recipe_list)
    }

    override fun initAfterBinding() {
        back_btn.setOnClickListener {
            finish()
        }

        all_btn.setOnClickListener {
            viewModel.recipe_click(all_btn, base_btn, main_btn, topping_btn)

            var items = allrecipe()
            recipeInit(items)
        }

        base_btn.setOnClickListener {
            viewModel.recipe_click(base_btn, all_btn, main_btn, topping_btn)

            var items = baserecipe()
            recipeInit(items)
        }

        main_btn.setOnClickListener {
            viewModel.recipe_click(main_btn, base_btn, all_btn, topping_btn)

            var items = mainrecipe()
            recipeInit(items)
        }

        topping_btn.setOnClickListener {
            viewModel.recipe_click(topping_btn, base_btn, main_btn, all_btn)

            var items = toppingrecipe()
            recipeInit(items)
        }

        recipeAdapter.setOnItemClickListener(object : RecipeAdapter.OnItemClickListener {
            override fun onItemClick(v: View?, position: Int) {
                var request = recipeAdapter.getItem(position)
                recipeAdapter.notifyDataSetChanged()

                val recipeDialog = RecipeDialog(request) {
                    when (it) {
                        1 -> {

                        }
                    }
                }
                recipeDialog.show(supportFragmentManager, recipeDialog.tag)
            }
        })
    }

    fun recipeInit(items: ArrayList<recipe>){
        recipeAdapter.clear()

        for(i in 0..items.size-1){
            recipeAdapter.addItem(items.get(i))
        }
        recipeAdapter.notifyDataSetChanged()
    }
}