package com.americano.qnnect.kotlin.src.main.home

import android.os.Bundle
import android.util.Log
import android.view.View
import com.americano.qnnect.kotlin.R
import com.americano.qnnect.kotlin.config.BaseFragment
import com.americano.qnnect.kotlin.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}