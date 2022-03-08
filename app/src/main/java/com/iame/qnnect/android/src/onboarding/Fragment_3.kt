package com.iame.qnnect.android.src.onboarding

import android.view.ViewGroup
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import com.iame.qnnect.android.R


class Fragment_3 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return inflater.inflate(
            R.layout.fram_onboard3, container, false) as ViewGroup
    }
}
