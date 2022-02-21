package com.iame.qnnect.android.src.main.home

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.iame.qnnect.android.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.iame.qnnect.android.base.BaseFragment
import com.iame.qnnect.android.databinding.FragmentGroupBottomBinding
import com.iame.qnnect.android.databinding.FragmentGroupBottomNextBinding
import com.iame.qnnect.android.viewmodel.GroupBottomNextViewModel
import com.iame.qnnect.android.viewmodel.GroupBottomViewModel
import kotlinx.android.synthetic.main.fragment_group_bottom.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class GroupBottomNextSheet : BaseFragment<FragmentGroupBottomNextBinding, GroupBottomNextViewModel>(R.layout.fragment_group_bottom_next) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_group_bottom_next // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: GroupBottomNextViewModel by viewModel()

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
        close_btn.setOnClickListener {

        }
    }
}
