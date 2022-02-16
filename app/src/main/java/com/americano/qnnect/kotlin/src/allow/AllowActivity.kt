package com.americano.qnnect.kotlin.src.allow

import android.content.Intent
import android.graphics.Color
import com.americano.qnnect.kotlin.R
import com.americano.qnnect.kotlin.base.BaseActivity
import com.americano.qnnect.kotlin.databinding.ActivityAllowBinding
import com.americano.qnnect.kotlin.src.profile.ProfileActivity
import com.americano.qnnect.kotlin.viewmodel.AllowViewModel
import kotlinx.android.synthetic.main.activity_allow.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllowActivity : BaseActivity<ActivityAllowBinding, AllowViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_allow // get() : 커스텀 접근자, 코틀린 문법


    override val viewModel: AllowViewModel by viewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
        allow_radio_all.setOnClickListener {
            if(viewModel.allChecked){
                allow_radio1.isChecked = true
                allow_radio2.isChecked = true
                allow_radio3.isChecked = true
                ok_btn.setBackgroundResource(R.drawable.allow_btn_ok)
            }
            else{
                allow_radio1.isChecked = false
                allow_radio2.isChecked = false
                allow_radio3.isChecked = false
                ok_btn.setBackgroundResource(R.drawable.allow_btn_fail)
            }
        }

        ok_btn.setOnClickListener {
            if(viewModel.onCheckedBoxClicked()){
                var intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
        }
    }
}