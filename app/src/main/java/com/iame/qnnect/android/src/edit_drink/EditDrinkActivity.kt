package com.iame.qnnect.android.src.edit_drink

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.base.HomeFragment_case
import com.iame.qnnect.android.databinding.ActivityEditDrinkBinding
import com.iame.qnnect.android.src.recipe.RecipeActivity
import com.iame.qnnect.android.src.store.MyMaterialActivity
import com.iame.qnnect.android.src.store.StoreActivity
import com.iame.qnnect.android.util.recipe
import com.iame.qnnect.android.viewmodel.EditDrinkViewModel
import kotlinx.android.synthetic.main.activity_edit_drink.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditDrinkActivity : BaseActivity<ActivityEditDrinkBinding, EditDrinkViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_edit_drink // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: EditDrinkViewModel by viewModel()
    var home = HomeFragment_case()

    private val recipeAdapter: MyRecipeAdapter by inject()
    var userdrinkId = 0

    var cafeId = 0

    override fun initStartView() {
        // member recycler
        material_recycler.run {
            adapter = recipeAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        // user drink response
        viewModel.currentUserDrinkResponse.observe(this, Observer {
            var current = it.currentDrinkInfo
            userdrinkId = current.userDrinkSelectedId

            ice_count.text = current.iceFilled.toString()+"/"+current.ice.toString()
            base_count.text = current.baseFilled.toString()+"/"+current.base.toString()
            main_count.text = current.mainFilled.toString()+"/"+current.main.toString()
            topping_count.text = current.toppingFilled.toString()+"/"+current.topping.toString()


            recipeAdapter.clear()
            if(it.myIngredient.size == 0){
                group_scroll.visibility = View.GONE
                drink_list.visibility = View.VISIBLE
            }
            else{
                drink_list.visibility = View.GONE
                group_scroll.visibility = View.VISIBLE
                it.myIngredient.forEach { item ->
                    recipeAdapter.addItem(item)
                }
                recipeAdapter.notifyDataSetChanged()
            }
            dismissLoadingDialog()
        })

        // 재료 선택
        viewModel.editdrinkResponse.observe(this, Observer {
            viewModel.getCurrentDrink(cafeId!!)
            dismissLoadingDialog()
        })

        viewModel.editdrinkError.observe(this, Observer {
            dismissLoadingDialog()
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        super.onResume()
        cafeId = home.getGroupname(this)!!
        viewModel.getCurrentDrink(cafeId)
        showLoadingDialog(this)
    }

    override fun initAfterBinding() {
        back_btn.setOnClickListener {
            finish()
        }

        store_btn.setOnClickListener {
            var intent = Intent(this, StoreActivity::class.java)
            startActivity(intent)
        }

        material_btn.setOnClickListener {
            var intent = Intent(this, MyMaterialActivity::class.java)
            startActivity(intent)
        }

        recipe_btn.setOnClickListener {
            var intent = Intent(this, RecipeActivity::class.java)
            startActivity(intent)
        }

        recipeAdapter.setOnItemClickListener(object : MyRecipeAdapter.OnItemClickListener {
            override fun onItemClick(v: View?, position: Int) {
                var request = recipeAdapter.getItem(position).index
                recipeAdapter.notifyDataSetChanged()
                viewModel.postEditDrink(userdrinkId, request)
                showLoadingDialog(this@EditDrinkActivity)
            }
        })
    }
}