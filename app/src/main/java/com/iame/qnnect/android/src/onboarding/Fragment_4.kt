package com.iame.qnnect.android.src.onboarding

import android.content.Intent
import android.view.ViewGroup
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.login.LoginActivity
import com.iame.qnnect.android.src.main.MainActivity


class Fragment_4 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(
            R.layout.fram_onboard4, container, false) as ViewGroup
    }

    override fun onResume() {
        super.onResume()
        var ok_btn = activity!!.findViewById<ConstraintLayout>(R.id.ok_btn)
        ok_btn.setOnClickListener {
            var intent = Intent(context, LoginActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP //액티비티 스택제거
            startActivity(intent)
        }
    }
}
