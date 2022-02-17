package com.americano.qnnect.kotlin.src.main

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.americano.qnnect.kotlin.MainSearchRecyclerViewAdapter
import com.americano.qnnect.kotlin.R
import com.americano.qnnect.kotlin.base.BaseActivity
import com.americano.qnnect.kotlin.databinding.ActivityMainBinding
import com.americano.qnnect.kotlin.databinding.ActivityMainTwoBinding
import com.americano.qnnect.kotlin.src.main.home.HomeFragment
import com.americano.qnnect.kotlin.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_two.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel




class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutResourceId: Int
        get() = com.americano.qnnect.kotlin.R.layout.activity_main // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: MainViewModel by viewModel()

    private val mainSearchRecyclerViewAdapter: MainSearchRecyclerViewAdapter by inject()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commitAllowingStateLoss()
        main_btm_nav.menu.findItem(R.id.menu_main_btm_nav_home).setIcon(R.mipmap.ic_home_bottom_clicked_foreground)

        main_btm_nav.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_main_btm_nav_home -> {
                        item.setIcon(R.mipmap.ic_home_bottom_clicked_foreground)
                        main_btm_nav.menu.findItem(R.id.menu_main_btm_nav_bookmark).setIcon(R.mipmap.ic_bookmark_bottom_foreground)
                        main_btm_nav.menu.findItem(R.id.menu_main_btm_nav_sotre).setIcon(R.mipmap.ic_store_bottom_foreground)
                        main_btm_nav.menu.findItem(R.id.menu_main_btm_nav_my_page).setIcon(R.mipmap.ic_my_bottom_foreground)

                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, HomeFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_main_btm_nav_bookmark -> {
                        item.setIcon(R.mipmap.ic_bookmark_bottom_clicked_foreground)
                        main_btm_nav.menu.findItem(R.id.menu_main_btm_nav_home).setIcon(R.mipmap.ic_home_bottom1_foreground)
                        main_btm_nav.menu.findItem(R.id.menu_main_btm_nav_sotre).setIcon(R.mipmap.ic_store_bottom_foreground)
                        main_btm_nav.menu.findItem(R.id.menu_main_btm_nav_my_page).setIcon(R.mipmap.ic_my_bottom_foreground)
                    }
                    R.id.menu_main_btm_nav_sotre -> {
                        item.setIcon(R.mipmap.ic_store_bottom_clicked_foreground)
                        main_btm_nav.menu.findItem(R.id.menu_main_btm_nav_home).setIcon(R.mipmap.ic_home_bottom1_foreground)
                        main_btm_nav.menu.findItem(R.id.menu_main_btm_nav_bookmark).setIcon(R.mipmap.ic_bookmark_bottom_foreground)
                        main_btm_nav.menu.findItem(R.id.menu_main_btm_nav_my_page).setIcon(R.mipmap.ic_my_bottom_foreground)
                    }
                    R.id.menu_main_btm_nav_my_page -> {
                        item.setIcon(R.mipmap.ic_my_bottom_clicked_foreground)
                        main_btm_nav.menu.findItem(R.id.menu_main_btm_nav_home).setIcon(R.mipmap.ic_home_bottom1_foreground)
                        main_btm_nav.menu.findItem(R.id.menu_main_btm_nav_bookmark).setIcon(R.mipmap.ic_bookmark_bottom_foreground)
                        main_btm_nav.menu.findItem(R.id.menu_main_btm_nav_sotre).setIcon(R.mipmap.ic_store_bottom_foreground)
                    }
                }
                true
            })

    }

}