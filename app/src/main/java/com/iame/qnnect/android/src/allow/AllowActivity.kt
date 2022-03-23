package com.iame.qnnect.android.src.allow

import android.content.Intent
import android.util.Log
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
                Log.d("allow_log", "null")
                var intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
            var response = PatchAlarmCheckResponse(it.response)
            Log.d("allow_log", response.toString())
            var intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        })
    }

    override fun initAfterBinding() {
        allow_radio_all.setOnClickListener {
            viewModel.radioSelectAll(allow_radio_all, allow_radio1, allow_radio2, allow_radio3, ok_btn)
        }

        allow_radio1.setOnClickListener {
            viewModel.radio_select(allow_radio1, allow_radio2, ok_btn)
        }

        allow_radio2.setOnClickListener {
            viewModel.radio_select(allow_radio2, allow_radio1, ok_btn)
        }

        ok_btn.setOnClickListener {
            if(viewModel.checkNext(allow_radio1, allow_radio2)){
                if(allow_radio3.isChecked){
                    viewModel.patchAlarmCheck(allow_radio3.isChecked)
//                    var intent = Intent(this, ProfileActivity::class.java)
//                    startActivity(intent)
                }
//                var intent = Intent(this, ProfileActivity::class.java)
//                startActivity(intent)
            }
        }
    }
}