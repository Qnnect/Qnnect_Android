package com.iame.qnnect.android.src.allow

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.CheckBox
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.base.BaseToken
import com.iame.qnnect.android.databinding.ActivityAllowBinding
import com.iame.qnnect.android.src.allow.model.PatchAlarmCheckResponse
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.login.model.PostLoginResponse
import com.iame.qnnect.android.src.main.MainActivity
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
        viewModel.alarmCheckResponse.observe(this, Observer {
            if(it.response == null){
                var intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
            var intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        })
    }

    override fun initAfterBinding() {
        allow_radio_all.setOnClickListener {
            radioSelectAll(allow_radio_all, allow_radio1, allow_radio2, allow_radio3, ok_btn)
        }

        allow_radio1.setOnClickListener {
            radio_select(allow_radio1, allow_radio2, ok_btn)
            allow_radio_all.isChecked = allow_radio1.isChecked && allow_radio2.isChecked && allow_radio3.isChecked
        }

        allow_radio2.setOnClickListener {
            radio_select(allow_radio2, allow_radio1, ok_btn)
            allow_radio_all.isChecked = allow_radio1.isChecked && allow_radio2.isChecked && allow_radio3.isChecked
        }

        allow_radio3.setOnClickListener {
            allow_radio_all.isChecked = allow_radio1.isChecked && allow_radio2.isChecked && allow_radio3.isChecked
        }

        // 개인정보 처리
        txt2.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://windy-laundry-812.notion.site/5a8dd6542fcf4ed9bd90e9f69d7a2e90"))
            startActivity(intent)
        }
        more_btn1.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://windy-laundry-812.notion.site/5a8dd6542fcf4ed9bd90e9f69d7a2e90"))
            startActivity(intent)
        }

        // 서비스 이용
        txt3.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://windy-laundry-812.notion.site/c5609c94300b42caae2610c3f3dc0d4b"))
            startActivity(intent)
        }
        more_btn2.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://windy-laundry-812.notion.site/c5609c94300b42caae2610c3f3dc0d4b"))
            startActivity(intent)
        }

        ok_btn.setOnClickListener {
            if(checkNext(allow_radio1, allow_radio2)){
                if(allow_radio3.isChecked){
                    viewModel.patchAlarmCheck(allow_radio3.isChecked)
                }
                else{
                    var intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    fun radioSelectAll(select_item: CheckBox,
                       item1: CheckBox, item2: CheckBox, item3: CheckBox
                       , nextBtn: ConstraintLayout){
        var check = select_item.isChecked
        if(check){
            nextBtn.setBackgroundResource(R.drawable.allow_btn_ok)
        }
        else{
            nextBtn.setBackgroundResource(R.drawable.allow_btn_fail)
        }
        item1.isChecked = check
        item2.isChecked = check
        item3.isChecked = check
    }

    fun radio_select(select_item: CheckBox, item: CheckBox, nextBtn: ConstraintLayout){
        if(select_item.isChecked && item.isChecked){
            nextBtn.setBackgroundResource(R.drawable.allow_btn_ok)
        }
        else{
            nextBtn.setBackgroundResource(R.drawable.allow_btn_fail)
        }
    }

    fun checkNext(item1: CheckBox, item2: CheckBox): Boolean{
        return item1.isChecked && item2.isChecked
    }
}