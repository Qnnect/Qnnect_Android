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
import com.iame.qnnect.android.src.drink.model.CafeUser
import com.iame.qnnect.android.src.drink.model.drink_item
import com.iame.qnnect.android.src.edit_drink.EditDrinkActivity
import com.iame.qnnect.android.src.edit_drink.MyRecipeAdapter
import com.iame.qnnect.android.src.reply.ReplyAdapter
import com.iame.qnnect.android.src.reply.model.Replies
import com.iame.qnnect.android.src.reply.reply_more.ReplyMoreBottomSheet
import com.iame.qnnect.android.src.store.StoreActivity
import com.iame.qnnect.android.util.drink_img
import com.iame.qnnect.android.viewmodel.DrinkViewModel
import kotlinx.android.synthetic.main.activity_drink.*
import kotlinx.android.synthetic.main.activity_drink.back_btn
import kotlinx.android.synthetic.main.activity_drink.base_count
import kotlinx.android.synthetic.main.activity_drink.base_txt
import kotlinx.android.synthetic.main.activity_drink.drink_img
import kotlinx.android.synthetic.main.activity_drink.ice_count
import kotlinx.android.synthetic.main.activity_drink.ice_txt
import kotlinx.android.synthetic.main.activity_drink.main_count
import kotlinx.android.synthetic.main.activity_drink.main_txt
import kotlinx.android.synthetic.main.activity_drink.seekBar
import kotlinx.android.synthetic.main.activity_drink.store_btn
import kotlinx.android.synthetic.main.activity_drink.topping_count
import kotlinx.android.synthetic.main.activity_edit_drink.*
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
    var userdrinkId = 0


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

            userdrinkId = current.userDrinkSelectedId

            it.cafeUsers.forEach { item ->
                userAdapter.addItem(item)
            }
            userAdapter.notifyDataSetChanged()

            ice_count.text = current.iceFilled.toString()+"/"+current.ice.toString()
            base_count.text = current.baseFilled.toString()+"/"+current.base.toString()
            main_count.text = current.mainFilled.toString()+"/"+current.main.toString()
            topping_count.text = current.toppingFilled.toString()+"/"+current.topping.toString()

            if(current.iceFilled < current.ice){
                var img = drink_img(userdrinkId, "빈잔")
                drink_img.setImageResource(img)
            }

            if(current.iceFilled == current.ice){
                var img = drink_img(userdrinkId, "얼음")
                drink_img.setImageResource(img)
                seekBar.setImageResource(R.drawable.img_drink_progress1)
            }

            if(current.baseFilled == current.base){
                var img = drink_img(userdrinkId, "베이스")
                drink_img.setImageResource(img)
                seekBar.setImageResource(R.drawable.img_drink_progress2)
                ice_txt.setTextColor(Color.parseColor("#828282"))
                ice_count.setTextColor(Color.parseColor("#828282"))
            }

            if(current.mainFilled == current.main){
                var img = drink_img(userdrinkId, "메인")
                drink_img.setImageResource(img)
                seekBar.setImageResource(R.drawable.img_drink_progress3)
                base_txt.setTextColor(Color.parseColor("#828282"))
                base_count.setTextColor(Color.parseColor("#828282"))
            }

            if(current.toppingFilled == current.topping){
                var img = drink_img(userdrinkId, "토핑")
                drink_img.setImageResource(img)
                seekBar.setImageResource(R.drawable.img_drink_progress4)
                main_txt.setTextColor(Color.parseColor("#828282"))
                main_count.setTextColor(Color.parseColor("#828282"))
            }

            if(it.currentUser){
                ok_btn.visibility = View.VISIBLE
            }
            else{
                ok_btn.visibility = View.GONE
            }
            dismissLoadingDialog()
        })
    }

    override fun initAfterBinding() {
        viewModel.getUserDrink(cafeId, userId)
        showLoadingDialog(this)

        back_btn.setOnClickListener {
            finish()
        }
        ok_btn.setOnClickListener {
            var intent = Intent(this, EditDrinkActivity::class.java)
            startActivity(intent)
        }

        store_btn.setOnClickListener {
            var intent = Intent(this, StoreActivity::class.java)
            startActivity(intent)
        }

        userAdapter.setOnItemClickListener(object : DrinkUserAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                val item: CafeUser = userAdapter.getItem(a_position)
                viewModel.getUserDrink(cafeId, item.cafeUserId)
                showLoadingDialog(this@DrinkActivity)
            }
        })
    }
}