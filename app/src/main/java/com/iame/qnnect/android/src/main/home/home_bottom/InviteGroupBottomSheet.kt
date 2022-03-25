package com.iame.qnnect.android.src.main.home.home_bottom

import android.app.Dialog
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
import com.iame.qnnect.android.src.group.model.GetGroupResponse
import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupRequest
import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupResponse
import com.iame.qnnect.android.src.main.home.home_bottom.service.AddGroupService
import com.iame.qnnect.android.src.main.home.home_bottom.service.AddGroupView
import com.iame.qnnect.android.src.main.home.home_bottom.service.PostInviteService
import com.iame.qnnect.android.src.main.home.home_bottom.service.PostInviteView
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.nick_name_edit
import kotlinx.android.synthetic.main.fragment_add_group_bottom.*
import kotlinx.android.synthetic.main.fragment_invite_group_bottom.*
import java.util.concurrent.TimeUnit


class InviteGroupBottomSheet(val cafeCode: String?, val itemClick: (Int) -> Unit) :
    BottomSheetDialogFragment(), PostInviteView{
    private lateinit var dlg : BottomSheetDialog

    var check = false
    var home = HomeFragment_case()

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
        return inflater.inflate(R.layout.fragment_invite_group_bottom, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(cafeCode != null){
            PostInviteService(this).tryInvite(cafeCode)
        }

        var close_btn = requireView().findViewById<ImageView>(R.id.close_btn)
        var ok_btn = requireView().findViewById<ConstraintLayout>(R.id.ok_btn)

        var code_txt = requireView().findViewById<EditText>(R.id.code_edit_txt)

        close_btn.setOnClickListener {
            dismiss()
        }

        ok_btn.setOnClickListener {
            if (check) {
                PostInviteService(this).tryInvite(code_txt.text.toString())
            }
        }

        code_edit_txt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                var len = code_edit_txt.text.toString()
                if (len.length > 0) {
                    ok_btn.setBackgroundResource(R.drawable.allow_btn_ok)
                    check = true
                } else {
                    ok_btn.setBackgroundResource(R.drawable.allow_btn_fail)
                    check = false
                }
            }
        })
    }

    override fun onInviteSuccess(response: GetGroupResponse) {
        home.setHome(requireContext(), 1, response.cafeId)
        dismiss()
        itemClick(0)
    }

    override fun onInviteFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        dismiss()
    }
}
