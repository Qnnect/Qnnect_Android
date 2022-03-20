package com.iame.qnnect.android.src.main.store

import android.content.Intent
import android.view.View
import android.widget.ScrollView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseFragment
import com.iame.qnnect.android.databinding.*
import com.iame.qnnect.android.src.add_drink.DrinkAdapter
import com.iame.qnnect.android.src.group.DeleteGroupDialog
import com.iame.qnnect.android.src.group.question.GroupQuestionViewPagerAdapter
import com.iame.qnnect.android.src.main.MainActivity
import com.iame.qnnect.android.src.main.home.GroupAdapter
import com.iame.qnnect.android.src.main.home.model.group_item
import com.iame.qnnect.android.src.store.MyMaterialActivity
import com.iame.qnnect.android.src.store.NotBuyDialog
import com.iame.qnnect.android.util.*
import com.iame.qnnect.android.viewmodel.StoreViewModel
import kotlinx.android.synthetic.main.activity_reply.*
import kotlinx.android.synthetic.main.fragment_group.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_store.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.view.MotionEvent
import android.view.View.OnTouchListener


class StoreFragment : BaseFragment<FragmentStoreBinding, StoreViewModel>(R.layout.fragment_store) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_store // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: StoreViewModel by viewModel()

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
            Toast.makeText(context, "재료 구매에 성공하셨습니다.", Toast.LENGTH_SHORT).show()
        })

        // 재료 구매 실패
        viewModel.error.observe(this, Observer {
            val notBuyDialog: NotBuyDialog = NotBuyDialog()
            notBuyDialog.show(requireActivity().supportFragmentManager, notBuyDialog.tag)
        })
    }

    override fun initAfterBinding() {
        scrollto_btn.setOnClickListener {
            val smoothScroller: RecyclerView.SmoothScroller by lazy {
                object : LinearSmoothScroller(context) {
                    override fun getVerticalSnapPreference() = SNAP_TO_START
                }
            }
            smoothScroller.targetPosition = 0
            recipe_recycler.layoutManager?.startSmoothScroll(smoothScroller)
            recipe_recycler.scrollToPosition(ScrollView.FOCUS_UP)
        }

        recipe_recycler.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_SCROLL, MotionEvent.ACTION_MOVE -> scrollto_btn.visibility = View.VISIBLE
//                MotionEvent.ACTION_DOWN -> {
//                    scrollto_btn.visibility = View.VISIBLE
//                }
                MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> scrollto_btn.visibility = View.GONE
            }
            false
        })


        material_btn.setOnClickListener {
            var intent = Intent(context, MyMaterialActivity::class.java)
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
                        1 -> {
                            viewModel.postBuyMateial(request.index)
                        }
                    }
                }
                recipeDialog.show(requireActivity().supportFragmentManager, recipeDialog.tag)
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