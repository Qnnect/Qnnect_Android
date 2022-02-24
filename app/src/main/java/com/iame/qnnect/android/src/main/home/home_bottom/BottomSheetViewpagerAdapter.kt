package com.iame.qnnect.android.src.main.home.home_bottom

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class BottomSheetViewpagerAdapter(
    fragmentActivity: FragmentActivity, view: MainGroupBottomSheet,
    viewPager: ViewPager2): FragmentStateAdapter(fragmentActivity) {

    // 1. ViewPager2에 연결할 Fragment 들을 생성
    val fragmentList = listOf<Fragment>(GroupBottomSheet(view,viewPager), GroupBottomNextSheet(view,viewPager))

    // 2. ViesPager2에서 노출시킬 Fragment 의 갯수 설정
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    // 3. ViewPager2의 각 페이지에서 노출할 Fragment 설정
    override fun createFragment(position: Int): Fragment {
        if(position == 0){
            Log.d("viewpager2_fragment : ", "0")
            return fragmentList.get(0)
        }
        else {
            Log.d("viewpager2_fragment : ", position.toString())
            return fragmentList.get(1)
        }
    }
}