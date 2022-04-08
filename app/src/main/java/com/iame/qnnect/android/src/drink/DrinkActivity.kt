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

        member_recycler.run {
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
                text1.text = item!!.nickName+"님의 음료"
            }
            else{
                text1.text = "내 음료"
            }

            if(current.userDrinkName == null){
                drink_img.setImageResource(R.mipmap.drink_none_foreground)
                complete_main.visibility = View.GONE
                ok_btn.visibility = View.GONE
                seek_main.visibility = View.GONE
                lemon_shadow.visibility = View.GONE
                complete_shadow.visibility = View.GONE
                shadow.visibility = View.VISIBLE

                null_txt.visibility = View.VISIBLE

                null_txt.text = item!!.nickName+"님은 아직\n음료를 고르지 않았어요."
            }
            else{
                null_txt.visibility = View.GONE
                userDrink = current.userDrinkName

                it.cafeUsers.forEach { item ->
                    userAdapter.addItem(item)
                }
                userAdapter.notifyDataSetChanged()

                ice_count.text = current.iceFilled.toString()+"/"+current.ice.toString()
                base_count.text = current.baseFilled.toString()+"/"+current.base.toString()
                main_count.text = current.mainFilled.toString()+"/"+current.main.toString()
                topping_count.text = current.toppingFilled.toString()+"/"+current.topping.toString()

                complete_main.visibility = View.GONE
                ok_btn.visibility = View.VISIBLE
                seek_main.visibility = View.VISIBLE

                var item = "빈잔"

                if(it.currentUser){
                    ok_btn.visibility = View.VISIBLE
                }
                else{
                    ok_btn.visibility = View.GONE
                }

                if(current.iceFilled < current.ice){
                    complete_shadow.visibility = View.GONE
                    lemon_shadow.visibility = View.GONE
                    shadow.visibility = View.GONE

                    ice_txt.setTextColor(Color.parseColor("#FD774C"))
                    ice_count.setTextColor(Color.parseColor("#FD774C"))
                    base_txt.setTextColor(Color.parseColor("#000000"))
                    base_count.setTextColor(Color.parseColor("#000000"))
                    main_txt.setTextColor(Color.parseColor("#000000"))
                    main_count.setTextColor(Color.parseColor("#000000"))
                    topping_txt.setTextColor(Color.parseColor("#000000"))
                    topping_count.setTextColor(Color.parseColor("#000000"))
                    seekBar.setImageResource(R.drawable.img_drink_progress1)

                    var img = drinkName(userDrink, "빈잔")
                    drink_img.setImageResource(img)
                }

                if(current.iceFilled == current.ice){
                    complete_shadow.visibility = View.GONE
                    lemon_shadow.visibility = View.GONE
                    shadow.visibility = View.VISIBLE

                    var img = drinkName(userDrink, "얼음")
                    drink_img.setImageResource(img)

                    ice_txt.setTextColor(Color.parseColor("#828282"))
                    ice_count.setTextColor(Color.parseColor("#828282"))
                    base_txt.setTextColor(Color.parseColor("#FD774C"))
                    base_count.setTextColor(Color.parseColor("#FD774C"))
                    main_txt.setTextColor(Color.parseColor("#000000"))
                    main_count.setTextColor(Color.parseColor("#000000"))
                    topping_txt.setTextColor(Color.parseColor("#000000"))
                    topping_count.setTextColor(Color.parseColor("#000000"))

                    seekBar.setImageResource(R.drawable.img_drink_progress2)
                }

                if(current.baseFilled == current.base){
                    complete_shadow.visibility = View.GONE
                    lemon_shadow.visibility = View.GONE
                    shadow.visibility = View.VISIBLE

                    var img = drinkName(userDrink, "베이스")
                    drink_img.setImageResource(img)
                    seekBar.setImageResource(R.drawable.img_drink_progress3)

                    ice_txt.setTextColor(Color.parseColor("#828282"))
                    ice_count.setTextColor(Color.parseColor("#828282"))
                    base_txt.setTextColor(Color.parseColor("#828282"))
                    base_count.setTextColor(Color.parseColor("#828282"))
                    main_txt.setTextColor(Color.parseColor("#FD774C"))
                    main_count.setTextColor(Color.parseColor("#FD774C"))
                    topping_txt.setTextColor(Color.parseColor("#000000"))
                    topping_count.setTextColor(Color.parseColor("#000000"))
                }

                if(current.mainFilled == current.main){
                    complete_shadow.visibility = View.GONE
                    lemon_shadow.visibility = View.GONE
                    shadow.visibility = View.VISIBLE

                    var img = drinkName(userDrink, "메인")
                    drink_img.setImageResource(img)
                    seekBar.setImageResource(R.drawable.img_drink_progress4)

                    ice_txt.setTextColor(Color.parseColor("#828282"))
                    ice_count.setTextColor(Color.parseColor("#828282"))
                    base_txt.setTextColor(Color.parseColor("#828282"))
                    base_count.setTextColor(Color.parseColor("#828282"))
                    main_txt.setTextColor(Color.parseColor("#828282"))
                    main_count.setTextColor(Color.parseColor("#828282"))
                    topping_txt.setTextColor(Color.parseColor("#FD774C"))
                    topping_count.setTextColor(Color.parseColor("#FD774C"))
                }

                if(current.toppingFilled == current.topping){
                    if(userDrink == "레몬에이드"){
                        complete_shadow.visibility = View.GONE
                        lemon_shadow.visibility = View.VISIBLE
                        shadow.visibility = View.GONE
                    }
                    else{
                        complete_shadow.visibility = View.VISIBLE
                        lemon_shadow.visibility = View.GONE
                        shadow.visibility = View.GONE
                    }

                    var img = drinkName(userDrink, "완성")
                    complete_main.visibility = View.VISIBLE
                    ok_btn.visibility = View.GONE
                    seek_main.visibility = View.GONE

                    complete_name.text = userDrink+" 완성!"
                    drink_img.setImageResource(img)
                    seekBar.setImageResource(R.drawable.img_drink_progress4)

                    ice_txt.setTextColor(Color.parseColor("#828282"))
                    ice_count.setTextColor(Color.parseColor("#828282"))
                    base_txt.setTextColor(Color.parseColor("#828282"))
                    base_count.setTextColor(Color.parseColor("#828282"))
                    main_txt.setTextColor(Color.parseColor("#828282"))
                    main_count.setTextColor(Color.parseColor("#828282"))
                    topping_txt.setTextColor(Color.parseColor("#828282"))
                    topping_count.setTextColor(Color.parseColor("#828282"))
                }
            }
            dismissLoadingDialog()
        })

        viewModel.errorResponse.observe(this, Observer {
            drink_img.setImageResource(R.mipmap.drink_none_foreground)
            complete_main.visibility = View.GONE
            ok_btn.visibility = View.GONE
            seek_main.visibility = View.GONE
            lemon_shadow.visibility = View.GONE
            complete_shadow.visibility = View.GONE
            shadow.visibility = View.VISIBLE

            null_txt.visibility = View.VISIBLE
            null_txt.text = item!!.nickName+"님은 아직\n음료를 고르지 않았어요."

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
        back_btn.setOnClickListener {
            finish()
        }
        ok_btn.setOnClickListener {
            var intent = Intent(this, EditDrinkActivity::class.java)
            intent.putExtra("cafeId", cafeId)
            startActivity(intent)
        }

        store_btn.setOnClickListener {
            var intent = Intent(this, StoreActivity::class.java)
            startActivity(intent)
        }

        userAdapter.setOnItemClickListener(object : DrinkUserAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                item = userAdapter.getItem(a_position)
                viewModel.getUserDrink(cafeId, item!!.cafeUserId)
                showLoadingDialog(this@DrinkActivity)
            }
        })

        // 새음료 추가하기
        new_drink_btn.setOnClickListener {
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