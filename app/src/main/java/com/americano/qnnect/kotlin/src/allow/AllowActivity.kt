package com.americano.qnnect.kotlin.src.allow

import android.content.Intent
import android.os.Bundle
import com.americano.qnnect.kotlin.config.BaseActivity
import com.americano.qnnect.kotlin.databinding.ActivityAllowBinding
import com.americano.qnnect.kotlin.src.profile.ProfileActivity

class AllowActivity : BaseActivity<ActivityAllowBinding>(ActivityAllowBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.allowRadioAll.setOnClickListener {
            binding.allowRadio1.isSelected = true
            binding.allowRadio2.isSelected = true
            binding.allowRadio3.isSelected = true
        }

        binding.okBtn.setOnClickListener { 
            var intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}