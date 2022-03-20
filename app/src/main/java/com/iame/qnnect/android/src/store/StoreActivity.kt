package com.iame.qnnect.android.src.store

import android.content.Intent
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.*
import com.iame.qnnect.android.src.main.store.RecipeAdapter
import com.iame.qnnect.android.src.main.store.RecipeDialog
import com.iame.qnnect.android.util.*
import com.iame.qnnect.android.viewmodel.StoreActivityViewModel
import kotlinx.android.synthetic.main.activity_store.*
import kotlinx.android.synthetic.main.fragment_store.*
import kotlinx.android.synthetic.main.fragment_store.all_btn
import kotlinx.android.synthetic.main.fragment_store.base_btn
import kotlinx.android.synthetic.main.fragment_store.main_btn
import kotlinx.android.synthetic.main.fragment_store.material_btn
import kotlinx.android.synthetic.main.fragment_store.recipe_recycler
import kotlinx.android.synthetic.main.fragment_store.scrollto_btn
import kotlinx.android.synthetic.main.fragment_store.topping_btn
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class StoreActivity : BaseActivity<ActivityStoreBinding, StoreActivityViewModel>(){

    override val layoutResourceId: Int
        get() = R.layout.activity_store // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: StoreActivityViewModel by viewModel()

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

        // 재료 구매 완료
        viewModel.buymaterialResponse.observe(this, Observer {
            Toast.makeText(this, "재료 구매에 성공하셨습니다.", Toast.LENGTH_SHORT).show()
        })

        // 재료 구매 실패
        viewModel.error.observe(this, Observer {
            val notBuyDialog: NotBuyDialog = NotBuyDialog()
            notBuyDialog.show(supportFragmentManager, notBuyDialog.tag)
        })
    }

    override fun initAfterBinding() {
        scrollto_btn.setOnClickListener {
            val smoothScroller: RecyclerView.SmoothScroller by lazy {
                object : LinearSmoothScroller(this) {
                    override fun getVerticalSnapPreference() = SNAP_TO_START
                }
            }
            smoothScroller.targetPosition = 0
            recipe_recycler.layoutManager?.startSmoothScroll(smoothScroller)
            recipe_recycler.scrollToPosition(ScrollView.FOCUS_UP)
        }

        recipe_recycler.setOnScrollListener(object : RecyclerView.OnScrollListener() {
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

//        recipe_recycler.setOnTouchListener(View.OnTouchListener { v, event ->
//            when (event.action) {
//                MotionEvent.ACTION_SCROLL, MotionEvent.ACTION_MOVE -> scrollto_btn.visibility =
//                    View.VISIBLE
//                MotionEvent.ACTION_UP -> {
//                    scrollto_btn.visibility = View.VISIBLE
//                }
//                MotionEvent.ACTION_DOWN -> {
//                    scrollto_btn.visibility = View.VISIBLE
//                }
////                MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> scrollto_btn.visibility =
////                    View.GONE
//            }
//            false
//        })

        back_btn.setOnClickListener {
            finish()
        }

        material_btn.setOnClickListener {
            var intent = Intent(this, MyMaterialActivity::class.java)
            startActivity(intent)
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
                        // 구매
                        1 -> {
                            viewModel.postBuyMateial(request.index)
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