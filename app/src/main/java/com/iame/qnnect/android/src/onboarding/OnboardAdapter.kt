package com.iame.qnnect.android.src.onboarding

import android.content.Context
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class OnboardAdapter(fa: FragmentActivity, var mCount: Int) : FragmentStateAdapter(fa!!) {
    @NonNull
    override fun createFragment(position: Int): Fragment {
        val index = getRealPosition(position)
        return if (index == 0) Fragment_1() else if (index == 1) Fragment_2() else if (index == 2) Fragment_3() else Fragment_4()
    }

    override fun getItemCount(): Int {
        return 4
    }

    fun getRealPosition(position: Int): Int {
        return position % mCount
    }
}
