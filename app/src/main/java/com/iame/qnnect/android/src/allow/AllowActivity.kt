package com.iame.qnnect.android.src.allow

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.CheckBox
import android.widget.Toast
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
            val fcmtoken = baseToken.getFCM(this)
            if(fcmtoken != null){
                viewModel.postFcmToken(fcmtoken)
            }
            else{
                var intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
        })

        viewModel.fcmtokenResponse.observe(this, Observer {
            var intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        })

        viewModel.errorResponse.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun initAfterBinding() {
        binding.allowRadioAll.setOnClickListener {
            radioSelectAll(binding.allowRadioAll, binding.allowRadio1, binding.allowRadio2, binding.allowRadio3, binding.okBtn)
        }

        binding.allowRadio1.setOnClickListener {
            radio_select(binding.allowRadio1, binding.allowRadio2, binding.okBtn)
            allow_radio_all.isChecked =  binding.allowRadio1.isChecked && binding.allowRadio2.isChecked && binding.allowRadio3.isChecked
        }

        binding.allowRadio2.setOnClickListener {
            radio_select(binding.allowRadio2, binding.allowRadio1, binding.okBtn)
            allow_radio_all.isChecked =  binding.allowRadio1.isChecked && binding.allowRadio2.isChecked && binding.allowRadio3.isChecked
        }

        binding.allowRadio3.setOnClickListener {
            allow_radio_all.isChecked =  binding.allowRadio1.isChecked && binding.allowRadio2.isChecked && binding.allowRadio3.isChecked
        }

        // 개인정보 처리
        binding.txt2.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://windy-laundry-812.notion.site/5a8dd6542fcf4ed9bd90e9f69d7a2e90"))
            startActivity(intent)
        }
        binding.moreBtn1.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://windy-laundry-812.notion.site/5a8dd6542fcf4ed9bd90e9f69d7a2e90"))
            startActivity(intent)
        }

        // 서비스 이용
        binding.txt3.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://windy-laundry-812.notion.site/c5609c94300b42caae2610c3f3dc0d4b"))
            startActivity(intent)
        }
        binding.moreBtn2.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://windy-laundry-812.notion.site/c5609c94300b42caae2610c3f3dc0d4b"))
            startActivity(intent)
        }

        binding.okBtn.setOnClickListener {
            if(checkNext(binding.allowRadio1, binding.allowRadio2)){
                if(binding.allowRadio3.isChecked){
                    viewModel.patchAlarmCheck(binding.allowRadio3.isChecked)
                }
                else{
                    var intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    fun radioSelectAll(select_item: CheckBox,
                       item1: CheckBox, item2: CheckBox, item3: CheckBox,
                       nextBtn: ConstraintLayout){
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