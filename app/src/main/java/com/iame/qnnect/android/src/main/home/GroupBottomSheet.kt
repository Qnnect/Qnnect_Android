package com.iame.qnnect.android.src.main.home

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.iame.qnnect.android.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.iame.qnnect.android.base.BaseFragment
import com.iame.qnnect.android.databinding.FragmentGroupBottomBinding
import com.iame.qnnect.android.databinding.FragmentHomeBinding
import com.iame.qnnect.android.src.main.home.model.group_item
import com.iame.qnnect.android.src.main.home.model.question_item
import com.iame.qnnect.android.viewmodel.GroupBottomViewModel
import com.iame.qnnect.android.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_group_bottom.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GroupBottomSheet : BaseFragment<FragmentGroupBottomBinding, GroupBottomViewModel>(R.layout.fragment_group_bottom) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_group_bottom // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: GroupBottomViewModel by viewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
        close_btn.setOnClickListener {

        }
    }
}
