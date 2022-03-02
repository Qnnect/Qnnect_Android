package com.iame.qnnect.android.src.main

import androidx.fragment.app.Fragment
import com.iame.qnnect.android.MainSearchRecyclerViewAdapter
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityMainBinding
import com.iame.qnnect.android.src.main.bookmark.BookmarkFragment
import com.iame.qnnect.android.src.main.home.HomeFragment
import com.iame.qnnect.android.src.main.mypage.MypageFragment
import com.iame.qnnect.android.src.main.store.StoreFragment
import com.iame.qnnect.android.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.iame.qnnect.android.base.HomeFragment_case
import com.iame.qnnect.android.src.group.GroupFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_two.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_main // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: MainViewModel by viewModel()

    private val mainSearchRecyclerViewAdapter: MainSearchRecyclerViewAdapter by inject()

    var case = HomeFragment_case()
    var case_num = 0

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

                        if(case_num == 0){
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.main_frm, HomeFragment())
                                .commitAllowingStateLoss()
                            return@OnNavigationItemSelectedListener true
                        }
                        else{
                            var group_name = case.getGroupname(this)
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.main_frm, GroupFragment())
                                .commitAllowingStateLoss()
                            return@OnNavigationItemSelectedListener true
                        }
                    }
                    R.id.menu_main_btm_nav_bookmark -> {
                        item.setIcon(R.mipmap.ic_bookmark_bottom_clicked_foreground)
                        main_btm_nav.menu.findItem(R.id.menu_main_btm_nav_home).setIcon(R.mipmap.ic_home_bottom1_foreground)
                        main_btm_nav.menu.findItem(R.id.menu_main_btm_nav_sotre).setIcon(R.mipmap.ic_store_bottom_foreground)
                        main_btm_nav.menu.findItem(R.id.menu_main_btm_nav_my_page).setIcon(R.mipmap.ic_my_bottom_foreground)

                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, BookmarkFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_main_btm_nav_sotre -> {
                        item.setIcon(R.mipmap.ic_store_bottom_clicked_foreground)
                        main_btm_nav.menu.findItem(R.id.menu_main_btm_nav_home).setIcon(R.mipmap.ic_home_bottom1_foreground)
                        main_btm_nav.menu.findItem(R.id.menu_main_btm_nav_bookmark).setIcon(R.mipmap.ic_bookmark_bottom_foreground)
                        main_btm_nav.menu.findItem(R.id.menu_main_btm_nav_my_page).setIcon(R.mipmap.ic_my_bottom_foreground)

                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, StoreFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_main_btm_nav_my_page -> {
                        item.setIcon(R.mipmap.ic_my_bottom_clicked_foreground)
                        main_btm_nav.menu.findItem(R.id.menu_main_btm_nav_home).setIcon(R.mipmap.ic_home_bottom1_foreground)
                        main_btm_nav.menu.findItem(R.id.menu_main_btm_nav_bookmark).setIcon(R.mipmap.ic_bookmark_bottom_foreground)
                        main_btm_nav.menu.findItem(R.id.menu_main_btm_nav_sotre).setIcon(R.mipmap.ic_store_bottom_foreground)

                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, MypageFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                }
                true
            })
    }

    //이 함수를 통해 다른 fragment로 이동한다.생성자가 아닌 불러오는 형식
    fun fragmentChange_for_adapter(frag: Fragment){
        if(frag == HomeFragment()){
            case_num = 0
        }
        else{
            case_num = 1
        }
        supportFragmentManager.beginTransaction().replace(R.id.main_frm, frag).commit()
    }
}