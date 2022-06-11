package com.iame.qnnect.android.src.drink

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityDrinkBinding
import com.iame.qnnect.android.src.add_drink.AddDrinkBottomSheet
import com.iame.qnnect.android.src.add_drink.service.AddDrinkService
import com.iame.qnnect.android.src.drink.model.CafeUser
import com.iame.qnnect.android.src.drink.model.drink_item
import com.iame.qnnect.android.src.edit_drink.EditDrinkActivity
import com.iame.qnnect.android.src.store.StoreActivity
import com.iame.qnnect.android.util.Getdrink
import com.iame.qnnect.android.util.drinkName
import com.iame.qnnect.android.util.drink_img
import com.iame.qnnect.android.util.drink_imgName
import com.iame.qnnect.android.viewmodel.DrinkViewModel
import kotlinx.android.synthetic.main.activity_drink.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DrinkActivity : BaseActivity<ActivityDrinkBinding, DrinkViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_drink // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: DrinkViewModel by viewModel()

    private val userAdapter: DrinkUserAdapter by inject()

    var drink_list = ArrayList<drink_item>()
    var cafeId = 0
    var userId = 0
    var userDrink = ""
    var item: CafeUser? = null

    override fun initStartView() {
        cafeId = intent.getIntExtra("cafeId", 0)
        userId = intent.getIntExtra("userId", 0)

        binding.memberRecycler.run {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        // user drink response
        viewModel.userDrinkResponse.observe(this, Observer {
            userAdapter.clear()

            var current = it.currentDrinkInfo

            if(item != null){
                binding.text1.text = item!!.nickName+"님의 음료"
            }
            else{
                binding.text1.text = "내 음료"
            }

            if(current.userDrinkName == null){
                binding.drinkImg.setImageResource(R.mipmap.drink_none_foreground)
                binding.completeMain.visibility = View.GONE
                binding.okBtn.visibility = View.GONE
                binding.seekMain.visibility = View.GONE
                binding.lemonShadow.visibility = View.GONE
                binding.completeShadow.visibility = View.GONE
                binding.shadow.visibility = View.VISIBLE

                binding.nullTxt.visibility = View.VISIBLE

                binding.nullTxt.text = item!!.nickName+"님은 아직\n음료를 고르지 않았어요."
            }
            else{
                binding.nullTxt.visibility = View.GONE
                userDrink = current.userDrinkName

                it.cafeUsers.forEach { item ->
                    userAdapter.addItem(item)
                }
                userAdapter.notifyDataSetChanged()

                binding.iceCount.text = current.iceFilled.toString()+"/"+current.ice.toString()
                binding.baseCount.text = current.baseFilled.toString()+"/"+current.base.toString()
                binding.mainCount.text = current.mainFilled.toString()+"/"+current.main.toString()
                binding.toppingCount.text = current.toppingFilled.toString()+"/"+current.topping.toString()

                binding.completeMain.visibility = View.GONE
                binding.okBtn.visibility = View.VISIBLE
                binding.seekMain.visibility = View.VISIBLE

                var item = "빈잔"

                if(it.currentUser){
                    binding.okBtn.visibility = View.VISIBLE
                }
                else{
                    binding.okBtn.visibility = View.GONE
                }

                if(current.iceFilled < current.ice){
                    binding.completeShadow.visibility = View.GONE
                    binding.lemonShadow.visibility = View.GONE
                    binding.shadow.visibility = View.GONE

                    binding.iceTxt.setTextColor(Color.parseColor("#FD774C"))
                    binding.iceCount.setTextColor(Color.parseColor("#FD774C"))
                    binding.baseTxt.setTextColor(Color.parseColor("#000000"))
                    binding.baseCount.setTextColor(Color.parseColor("#000000"))
                    binding.mainTxt.setTextColor(Color.parseColor("#000000"))
                    binding.mainCount.setTextColor(Color.parseColor("#000000"))
                    binding.toppingTxt.setTextColor(Color.parseColor("#000000"))
                    binding.toppingCount.setTextColor(Color.parseColor("#000000"))
                    binding.seekBar.setImageResource(R.drawable.img_drink_progress1)

                    var img = drinkName(userDrink, "빈잔")
                    binding.drinkImg.setImageResource(img)
                }

                if(current.iceFilled == current.ice){
                    binding.completeShadow.visibility = View.GONE
                    binding.lemonShadow.visibility = View.GONE
                    binding.shadow.visibility = View.VISIBLE

                    var img = drinkName(userDrink, "얼음")
                    binding.drinkImg.setImageResource(img)

                    binding.iceTxt.setTextColor(Color.parseColor("#828282"))
                    binding.iceCount.setTextColor(Color.parseColor("#828282"))
                    binding.baseTxt.setTextColor(Color.parseColor("#FD774C"))
                    binding.baseCount.setTextColor(Color.parseColor("#FD774C"))
                    binding.mainTxt.setTextColor(Color.parseColor("#000000"))
                    binding.mainCount.setTextColor(Color.parseColor("#000000"))
                    binding.toppingTxt.setTextColor(Color.parseColor("#000000"))
                    binding.toppingCount.setTextColor(Color.parseColor("#000000"))

                    binding.seekBar.setImageResource(R.drawable.img_drink_progress2)
                }

                if(current.baseFilled == current.base){
                    binding.completeShadow.visibility = View.GONE
                    binding.lemonShadow.visibility = View.GONE
                    binding.shadow.visibility = View.VISIBLE

                    var img = drinkName(userDrink, "베이스")
                    binding.drinkImg.setImageResource(img)
                    binding.seekBar.setImageResource(R.drawable.img_drink_progress3)

                    binding.iceTxt.setTextColor(Color.parseColor("#828282"))
                    binding.iceCount.setTextColor(Color.parseColor("#828282"))
                    binding.baseTxt.setTextColor(Color.parseColor("#828282"))
                    binding.baseCount.setTextColor(Color.parseColor("#828282"))
                    binding.mainTxt.setTextColor(Color.parseColor("#FD774C"))
                    binding.mainCount.setTextColor(Color.parseColor("#FD774C"))
                    binding.toppingTxt.setTextColor(Color.parseColor("#000000"))
                    binding.toppingCount.setTextColor(Color.parseColor("#000000"))
                }

                if(current.mainFilled == current.main){
                    binding.completeShadow.visibility = View.GONE
                    binding.lemonShadow.visibility = View.GONE
                    binding.shadow.visibility = View.VISIBLE

                    var img = drinkName(userDrink, "메인")
                    binding.drinkImg.setImageResource(img)
                    binding.seekBar.setImageResource(R.drawable.img_drink_progress4)

                    binding.iceTxt.setTextColor(Color.parseColor("#828282"))
                    binding.iceCount.setTextColor(Color.parseColor("#828282"))
                    binding.baseTxt.setTextColor(Color.parseColor("#828282"))
                    binding.baseCount.setTextColor(Color.parseColor("#828282"))
                    binding.mainTxt.setTextColor(Color.parseColor("#828282"))
                    binding.mainCount.setTextColor(Color.parseColor("#828282"))
                    binding.toppingTxt.setTextColor(Color.parseColor("#FD774C"))
                    binding.toppingCount.setTextColor(Color.parseColor("#FD774C"))
                }

                if(current.toppingFilled == current.topping){
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

                    var img = drinkName(userDrink, "완성")
                    binding.completeMain.visibility = View.VISIBLE
                    binding.okBtn.visibility = View.GONE
                    binding.seekMain.visibility = View.GONE

                    binding.completeName.text = userDrink+" 완성!"
                    binding.drinkImg.setImageResource(img)
                    binding.seekBar.setImageResource(R.drawable.img_drink_progress4)

                    binding.iceTxt.setTextColor(Color.parseColor("#828282"))
                    binding.iceCount.setTextColor(Color.parseColor("#828282"))
                    binding.baseTxt.setTextColor(Color.parseColor("#828282"))
                    binding.baseCount.setTextColor(Color.parseColor("#828282"))
                    binding.mainTxt.setTextColor(Color.parseColor("#828282"))
                    binding.mainCount.setTextColor(Color.parseColor("#828282"))
                    binding.toppingTxt.setTextColor(Color.parseColor("#828282"))
                    binding.toppingCount.setTextColor(Color.parseColor("#828282"))
                }
            }
            dismissLoadingDialog()
        })

        viewModel.errorResponse.observe(this, Observer {
            binding.drinkImg.setImageResource(R.mipmap.drink_none_foreground)
            binding.completeMain.visibility = View.GONE
            binding.okBtn.visibility = View.GONE
            binding.seekMain.visibility = View.GONE
            binding.lemonShadow.visibility = View.GONE
            binding.completeShadow.visibility = View.GONE
            binding.shadow.visibility = View.VISIBLE

            binding.nullTxt.visibility = View.VISIBLE
            binding.nullTxt.text = item!!.nickName+"님은 아직\n음료를 고르지 않았어요."

//            Toast.makeText(this@DrinkActivity, "네트워크 오류입니다.", Toast.LENGTH_SHORT).show()
            dismissLoadingDialog()
//            finish()
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUserDrink(cafeId, userId)
        showLoadingDialog(this)
    }

    override fun initAfterBinding() {
        binding.backBtn.setOnClickListener {
            finish()
        }
        binding.okBtn.setOnClickListener {
            var intent = Intent(this, EditDrinkActivity::class.java)
            intent.putExtra("cafeId", cafeId)
            startActivity(intent)
        }

        binding.storeBtn.setOnClickListener {
            var intent = Intent(this, StoreActivity::class.java)
            startActivity(intent)
        }

        userAdapter.setOnItemClickListener(object : DrinkUserAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                item = userAdapter.getItem(a_position)
                userAdapter.notifyDataSetChanged()
                viewModel.getUserDrink(cafeId, item!!.cafeUserId)
                showLoadingDialog(this@DrinkActivity)
            }
        })

        // 새음료 추가하기
        binding.newDrinkBtn.setOnClickListener {
            val newaddDrinkBottomSheet = NewAddDrinkBottomSheet{
                when (it) {
                    // 새로 음료 추가
                    0 -> { onResume() }
                }
            }
            newaddDrinkBottomSheet.show(supportFragmentManager, newaddDrinkBottomSheet.tag)
        }
    }
}