package com.iame.qnnect.android.src.allow

import android.content.Intent
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityAllowBinding
import com.iame.qnnect.android.src.profile.ProfileActivity
import com.iame.qnnect.android.viewmodel.AllowViewModel
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
            if(allow_radio_all.isChecked){
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

        allow_radio1.setOnClickListener {
            if(allow_radio1.isChecked && allow_radio2.isChecked && allow_radio3.isChecked){
                ok_btn.setBackgroundResource(R.drawable.allow_btn_ok)
            }
            else{
                ok_btn.setBackgroundResource(R.drawable.allow_btn_fail)
            }
        }

        allow_radio2.setOnClickListener {
            if(allow_radio1.isChecked && allow_radio2.isChecked && allow_radio3.isChecked){
                ok_btn.setBackgroundResource(R.drawable.allow_btn_ok)
            }
            else{
                ok_btn.setBackgroundResource(R.drawable.allow_btn_fail)
            }
        }

        allow_radio3.setOnClickListener {
            if(allow_radio1.isChecked && allow_radio2.isChecked && allow_radio3.isChecked){
                ok_btn.setBackgroundResource(R.drawable.allow_btn_ok)
            }
            else{
                ok_btn.setBackgroundResource(R.drawable.allow_btn_fail)
            }
        }

        ok_btn.setOnClickListener {
            if(allow_radio1.isChecked && allow_radio2.isChecked && allow_radio3.isChecked){
                var intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
        }
    }
}