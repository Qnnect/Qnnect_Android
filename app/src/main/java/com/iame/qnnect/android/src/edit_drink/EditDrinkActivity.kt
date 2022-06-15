package com.iame.qnnect.android.src.edit_drink

import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.base.HomeFragment_case
import com.iame.qnnect.android.databinding.ActivityEditDrinkBinding
import com.iame.qnnect.android.src.finish_drink.FinishDrinkActivity
import com.iame.qnnect.android.src.recipe.RecipeActivity
import com.iame.qnnect.android.src.store.MyMaterialActivity
import com.iame.qnnect.android.src.store.StoreActivity
import com.iame.qnnect.android.util.drinkName
import com.iame.qnnect.android.util.drink_img
import com.iame.qnnect.android.util.drink_imgName
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

    var now = ""
    var next = ""
    var userDrink = ""

    override fun initStartView() {
        cafeId = intent.getIntExtra("cafeId", 0)
        // member recycler
        binding.materialRecycler.run {
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
            userDrink = current.userDrinkName

            binding.iceCount.text = current.iceFilled.toString()+"/"+current.ice.toString()
            binding.baseCount.text = current.baseFilled.toString()+"/"+current.base.toString()
            binding.mainCount.text = current.mainFilled.toString()+"/"+current.main.toString()
            binding.toppingCount.text = current.toppingFilled.toString()+"/"+current.topping.toString()


            if(current.iceFilled < current.ice){
                now = "빈잔"
                next = "얼음"
                var img = drinkName(userDrink, "빈잔")
                binding.drinkImg.setImageResource(img)

                binding.completeShadow.visibility = View.GONE
                binding.lemonShadow.visibility = View.GONE
                binding.shadow.visibility = View.VISIBLE
            }

            if(current.iceFilled == current.ice){
                binding.seekBar.setImageResource(R.drawable.img_drink_progress2)
                binding.iceTxt.setTextColor(Color.parseColor("#828282"))
                binding.iceCount.setTextColor(Color.parseColor("#828282"))
                now = "얼음"
                next = "베이스"
                var img = drinkName(userDrink, "얼음")
                binding.drinkImg.setImageResource(img)

                binding.completeShadow.visibility = View.GONE
                binding.lemonShadow.visibility = View.GONE
                binding.shadow.visibility = View.VISIBLE
            }

            if(current.baseFilled == current.base){
                seekBar.setImageResource(R.drawable.img_drink_progress3)
                base_txt.setTextColor(Color.parseColor("#828282"))
                base_count.setTextColor(Color.parseColor("#828282"))
                now = "베이스"
                next = "주재료"
                var img = drinkName(userDrink, "베이스")
                binding.drinkImg.setImageResource(img)

                binding.completeShadow.visibility = View.GONE
                binding.lemonShadow.visibility = View.GONE
                binding.shadow.visibility = View.VISIBLE
            }

            if(current.mainFilled == current.main){
                seekBar.setImageResource(R.drawable.img_drink_progress4)
                main_txt.setTextColor(Color.parseColor("#828282"))
                main_count.setTextColor(Color.parseColor("#828282"))
                now = "주재료"
                next = "토핑"
                var img = drinkName(userDrink, "메인")
                binding.drinkImg.setImageResource(img)

                if(userdrinkId == 2){
                    binding.completeShadow.visibility = View.GONE
                    binding.lemonShadow.visibility = View.VISIBLE
                    binding.shadow.visibility = View.GONE
                }
                else{
                    binding.completeShadow.visibility = View.GONE
                    binding.lemonShadow.visibility = View.GONE
                    binding.shadow.visibility = View.VISIBLE
                }
            }

            if(current.toppingFilled == current.topping){
                binding.seekBar.setImageResource(R.drawable.img_drink_progress4)
                binding.mainTxt.setTextColor(Color.parseColor("#828282"))
                binding.mainCount.setTextColor(Color.parseColor("#828282"))
                now = "완료"
                next = "완료"
                var img = drinkName(userDrink, "토핑")
                binding.drinkImg.setImageResource(img)

                if(userDrink == "레몬에이드"){
                    binding.completeShadow.visibility = View.GONE
                    binding.lemonShadow.visibility = View.VISIBLE
                    binding.shadow.visibility = View.GONE
                }
                else{
                    binding.completeShadow.visibility = View.VISIBLE
                    binding.lemonShadow.visibility = View.GONE
                    binding.shadow.visibility = View.GONE
                }

                Handler(Looper.getMainLooper()).postDelayed({
                    var intent = Intent(this, FinishDrinkActivity::class.java)
                    intent.putExtra("drinkId", userDrink)
                    startActivity(intent)
                    finish()
                }, 50)
            }


            recipeAdapter.clear()
            if(it.myIngredient.isEmpty()){
                binding.groupScroll.visibility = View.GONE
                binding.drinkList.visibility = View.VISIBLE
            }
            else{
                binding.drinkList.visibility = View.GONE
                binding.groupScroll.visibility = View.VISIBLE
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
        })

        viewModel.editdrinkError.observe(this, Observer {
            if(it == ""){
                viewModel.getCurrentDrink(cafeId!!)
            }
            else{
                dismissLoadingDialog()
                val errorDialog: PostMaterialErrorDialog = PostMaterialErrorDialog(now, next)
                errorDialog.show(supportFragmentManager, errorDialog.tag)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        cafeId = home.getGroupname(this)!!
        viewModel.getCurrentDrink(cafeId)
        showLoadingDialog(this)
    }

    override fun initAfterBinding() {
        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.storeBtn.setOnClickListener {
            var intent = Intent(this, StoreActivity::class.java)
            startActivity(intent)
        }

        binding.materialBtn.setOnClickListener {
            var intent = Intent(this, MyMaterialActivity::class.java)
            startActivity(intent)
        }

        binding.recipeBtn.setOnClickListener {
            var intent = Intent(this, RecipeActivity::class.java)
            intent.putExtra("drinkId", userdrinkId)
            intent.putExtra("cafeId", cafeId)
            startActivity(intent)
        }

        recipeAdapter.setOnItemClickListener(object : MyRecipeAdapter.OnItemClickListener {
            override fun onItemClick(v: View?, position: Int) {
                var request = recipeAdapter.getItem(position).index
                val postMaterialDialog: PostMaterialDialog = PostMaterialDialog(recipeAdapter.getItem(position)) {
                    when (it) {
                        // 음료추가
                        1 -> {
                            viewModel.postEditDrink(userdrinkId, request)
                            showLoadingDialog(this@EditDrinkActivity)
                        }
                    }
                }
                postMaterialDialog.show(supportFragmentManager, postMaterialDialog.tag)
            }
        })
    }
}