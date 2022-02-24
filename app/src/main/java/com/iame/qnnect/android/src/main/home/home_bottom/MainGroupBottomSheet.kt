package com.iame.qnnect.android.src.main.home.home_bottom

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.iame.qnnect.android.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MainGroupBottomSheet() :
    BottomSheetDialogFragment(){
    private lateinit var dlg : BottomSheetDialog

    private lateinit var viewPager: ViewPager2

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // 이 코드를 실행하지 않으면
        // XML에서 round 처리를 했어도 적용되지 않는다.
        dlg = ( super.onCreateDialog(savedInstanceState).apply {
            // window?.setDimAmount(0.2f) // Set dim amount here
            setOnShowListener {
                val bottomSheet = findViewById<View>(R.id.design_bottom_sheet) as FrameLayout
                bottomSheet.setBackgroundResource(android.R.color.transparent)

                // 아래와 같이하면 Drag를 불가능하게 한다.
                //val behavior = BottomSheetBehavior.from(bottomSheet!!)
                //behavior.isDraggable = false
            }
        } ) as BottomSheetDialog
        return dlg
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_main_group_bottom, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = view!!.findViewById(R.id.bottom_sheet_viewPager2)

        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = BottomSheetViewpagerAdapter(requireActivity(), this, viewPager)
        viewPager.adapter = pagerAdapter
        viewPager.isUserInputEnabled = false

        var close_btn = view!!.findViewById<ImageView>(R.id.close_btn)

        close_btn.setOnClickListener {
            dismiss()
        }
    }
}
