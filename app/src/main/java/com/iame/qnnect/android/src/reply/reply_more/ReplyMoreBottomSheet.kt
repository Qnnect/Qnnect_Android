package com.iame.qnnect.android.src.reply.reply_more

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.iame.qnnect.android.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.widget.SeekBar.OnSeekBarChangeListener
import com.iame.qnnect.android.base.HomeFragment_case
import com.iame.qnnect.android.src.add_drink.AddDrinkBottomSheet
import com.iame.qnnect.android.src.group.NotQuestionDialog
import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupRequest
import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupResponse
import com.iame.qnnect.android.src.main.home.home_bottom.service.AddGroupService
import com.iame.qnnect.android.src.main.home.home_bottom.service.AddGroupView
import com.iame.qnnect.android.src.reply.reply_more.service.DeleteReplyService
import com.iame.qnnect.android.src.reply.reply_more.service.DeleteReplyView
import com.iame.qnnect.android.src.reply.reply_more.service.EditReplyService
import com.iame.qnnect.android.src.reply.reply_more.service.EditReplyView
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.nick_name_edit
import kotlinx.android.synthetic.main.fragment_add_group_bottom.*
import java.util.concurrent.TimeUnit


class ReplyMoreBottomSheet(var commentId: Int, var replyId: Int) :
    BottomSheetDialogFragment(), EditReplyView, DeleteReplyView{
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
        return inflater.inflate(R.layout.fragment_reply_more_bottom, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var edit_btn = view!!.findViewById<TextView>(R.id.edit_reply)
        edit_btn.setOnClickListener {
//            EditReplyService(this).tryEditReply()
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
                        val deleteReplyDialog = DeleteReplyDialog{
                            when (it) {
                                // 삭제
                                1 -> {DeleteReplyService(this).tryEditReply(commentId, replyId)}
                            }
                        }
                    }
                }
            }
            deleteDialog.show(requireActivity().supportFragmentManager, deleteDialog.tag)
        }
    }

    override fun onEditReplySuccess(response: String?) {
    }

    override fun onEditReplyFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onDeleteReplySuccess(response: String?) {
        dismiss()
    }

    override fun onDeleteReplyFailure(message: String) {
    }
}
