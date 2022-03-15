package com.iame.qnnect.android.src.reply.reply_more

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
import com.iame.qnnect.android.src.reply.reply_more.service.DeleteReplyService
import com.iame.qnnect.android.src.reply.reply_more.service.DeleteReplyView


class ReplyMoreBottomSheet(commentId: Int, replyId: Int, content: String) :
    BottomSheetDialogFragment(), DeleteReplyView{
    private lateinit var dlg : BottomSheetDialog

    var check = false

    var commentId = commentId
    var replyId = replyId
    var content = content

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
        return inflater.inflate(R.layout.fragment_reply_more_bottom, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d("response!!!", commentId.toString()+" , "+replyId.toString())
        var edit_btn = view!!.findViewById<TextView>(R.id.edit_reply)
        edit_btn.setOnClickListener {
            var intent = Intent(context, EditReplyActivity::class.java)
            intent.putExtra("commentId", commentId)
            intent.putExtra("replyId", replyId)
            intent.putExtra("contents", content)
            startActivity(intent)
        }

        var delete_btn = view!!.findViewById<TextView>(R.id.delte_reply)
        delete_btn.setOnClickListener {
            val deleteDialog: DeleteReplyDialog = DeleteReplyDialog {
                when (it) {
                    // 취소하기
                    0 -> {
                        dismiss()
                    }
                    // 삭제하기
                    1 -> {
                        DeleteReplyService(this).tryEditReply(commentId, replyId)
                        Log.d("response!!!", commentId.toString()+" , "+replyId.toString())
                    }
                }
            }
            deleteDialog.show(requireActivity().supportFragmentManager, deleteDialog.tag)
        }
    }

    override fun onDeleteReplySuccess(response: String?) {
    }

    override fun onDeleteReplyFailure(message: String) {
        dismiss()
    }
}
