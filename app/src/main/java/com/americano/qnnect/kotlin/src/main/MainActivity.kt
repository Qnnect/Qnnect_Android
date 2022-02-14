package com.americano.qnnect.kotlin.src.main

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.americano.qnnect.kotlin.R
import com.americano.qnnect.kotlin.config.BaseActivity
import com.americano.qnnect.kotlin.databinding.ActivityMainBinding
import com.americano.qnnect.kotlin.src.main.home.HomeFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commitAllowingStateLoss()

        binding.mainBtmNav.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_main_btm_nav_home -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, HomeFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            })
    }
}