package com.iame.qnnect.android.src.declare

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.iame.qnnect.android.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.iame.qnnect.android.src.reply.ReplyActivity
import com.iame.qnnect.android.src.reply.reply_more.DeleteReplyDialog
import com.iame.qnnect.android.src.reply.reply_more.EditReplyActivity
import com.iame.qnnect.android.src.reply.reply_more.service.DeleteReplyService
import com.iame.qnnect.android.src.reply.reply_more.service.DeleteReplyView


class DeclareBottomSheet(val itemClick: (Int) -> Unit) :
    BottomSheetDialogFragment(){
    private lateinit var dlg : BottomSheetDialog

    var check = false

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // 이 코드를 실행하지 않으면
        // XML에서 round 처리를 했어도 적용되지 않는다.
        dlg = ( super.onCreateDialog(savedInstanceState).apply {
            // window?.setDimAmount(0.2f) // Set dim amount here
            setOnShowListener {
                val bottomSheet = findViewById<View>(R.id.design_bottom_sheet) as FrameLayout
                bottomSheet.setBackgroundResource(android.R.color.transparent)

//                 아래와 같이하면 Drag를 불가능하게 한다.
                val behavior = BottomSheetBehavior.from(bottomSheet!!)
                behavior.isDraggable = true
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        } ) as BottomSheetDialog
        return dlg
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_declare_bottom, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // 신고하기
        var declare_btn = requireView().findViewById<TextView>(R.id.declare_btn)
        declare_btn.setOnClickListener {
            itemClick(0)
        }

        // 차단하기
        var block_btn = requireView().findViewById<TextView>(R.id.block_btn)
        block_btn.setOnClickListener {
            itemClick(1)
        }
    }
}
