package com.iame.qnnect.android.src.store

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    private val recipeAdapter: MaterialAdapter by inject()

    var recipe_list = ArrayList<recipe>()

    override fun initStartView() {
        // member recycler
        binding.recipeRecycler.run {
            adapter = recipeAdapter
            layoutManager = GridLayoutManager(context, 2).apply {
                orientation = GridLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        // 전체 나의 재료
        viewModel.mymaterialAllResponse.observe(this, Observer {
            recipeAdapter.clear()
            if(it.isEmpty()){
                binding.emptyImg.visibility = View.VISIBLE
                binding.emptyTxt.visibility = View.VISIBLE
            }
            else{
                binding.emptyImg.visibility = View.GONE
                binding.emptyTxt.visibility = View.GONE
            }
            it.forEach { item ->
                recipeAdapter.addItem(item)
            }
            recipeAdapter.notifyDataSetChanged()
        })

        // 부분별 나의 재료
        viewModel.mymaterialResponse.observe(this, Observer {
            recipeAdapter.clear()

            if(it.isEmpty()){
                binding.emptyImg.visibility = View.VISIBLE
                binding.emptyTxt.visibility = View.VISIBLE
            }
            else{
                binding.emptyImg.visibility = View.GONE
                binding.emptyTxt.visibility = View.GONE
            }
            it.forEach { item ->
                recipeAdapter.addItem(item)
            }
            recipeAdapter.notifyDataSetChanged()
        })

    }

    override fun initAfterBinding() {
        viewModel.getMyMaterialAll()

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.allBtn.setOnClickListener {
            viewModel.recipe_click(binding.allBtn, binding.baseBtn, binding.mainBtn, binding.toppingBtn)
            viewModel.getMyMaterialAll()
        }

        binding.baseBtn.setOnClickListener {
            viewModel.recipe_click(binding.baseBtn, binding.allBtn, binding.mainBtn, binding.toppingBtn)
            viewModel.getMyMaterial("ice_base")
        }

        binding.mainBtn.setOnClickListener {
            viewModel.recipe_click(binding.mainBtn, binding.baseBtn, binding.allBtn, binding.toppingBtn)
            viewModel.getMyMaterial("main")
        }

        binding.toppingBtn.setOnClickListener {
            viewModel.recipe_click(binding.toppingBtn, binding.baseBtn, binding.mainBtn, binding.allBtn)
            viewModel.getMyMaterial("topping")
        }

        binding.recipeRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                // 최하단
                if (!recipe_recycler.canScrollVertically(-1)) {
                    scrollto_btn.visibility = View.GONE
                }
                // 최상단
                else if (!recipe_recycler.canScrollVertically(1)) {
                    scrollto_btn.visibility = View.GONE
                }
                else {
                    scrollto_btn.visibility = View.VISIBLE
                }
            }
        })
    }
}