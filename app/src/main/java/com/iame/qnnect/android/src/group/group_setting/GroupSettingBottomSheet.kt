package com.iame.qnnect.android.src.group.group_setting

import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.iame.qnnect.android.R

class GroupSettingBottomSheet(val itemClick: (Int) -> Unit) :
    BottomSheetDialogFragment(){
    private lateinit var dlg : BottomSheetDialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // 이 코드를 실행하지 않으면
        // XML에서 round 처리를 했어도 적용되지 않는다.
        dlg = ( super.onCreateDialog(savedInstanceState).apply {
            // window?.setDimAmount(0.2f) // Set dim amount here
            setOnShowListener {
                val bottomSheet = findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
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
        return inflater.inflate(R.layout.fragment_group_setting_bottom, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // 초대하기
        view?.findViewById<ImageView>(R.id.close_btn)?.setOnClickListener {
            dismiss()
        }
        // 초대하기
        view?.findViewById<TextView>(R.id.invite_btn)?.setOnClickListener {
            itemClick(0)
            dismiss()
        }

        // 카페 수정
        view?.findViewById<TextView>(R.id.edit_caffe)?.setOnClickListener {
            itemClick(1)
            dismiss()
        }

        // 음료 수정
        view?.findViewById<TextView>(R.id.edit_drink)?.setOnClickListener {
            itemClick(2)
            dismiss()
        }

        // 카페 삭제
        view?.findViewById<TextView>(R.id.delete_caffe)?.setOnClickListener {
            itemClick(3)
            dismiss()
        }
    }
}