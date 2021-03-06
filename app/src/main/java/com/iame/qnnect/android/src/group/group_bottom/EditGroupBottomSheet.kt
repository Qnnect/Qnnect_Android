package com.iame.qnnect.android.src.group.group_bottom

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.iame.qnnect.android.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.widget.SeekBar
import com.iame.qnnect.android.base.HomeFragment_case
import com.iame.qnnect.android.src.group.group_bottom.service.EditGroupService
import com.iame.qnnect.android.src.group.group_bottom.service.EditGroupView
import com.iame.qnnect.android.src.main.home.home_bottom.model.PostAddGroupRequest
import com.iame.qnnect.android.src.main.home.home_bottom.service.AddGroupService
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_add_group_bottom.*
import kotlinx.android.synthetic.main.fragment_add_group_bottom.color_orange_btn
import kotlinx.android.synthetic.main.fragment_add_group_bottom.color_pink_btn
import kotlinx.android.synthetic.main.fragment_add_group_bottom.color_sky_btn
import kotlinx.android.synthetic.main.fragment_add_group_bottom.group_family_btn
import kotlinx.android.synthetic.main.fragment_add_group_bottom.group_friend_btn
import kotlinx.android.synthetic.main.fragment_add_group_bottom.name_edit_txt
import kotlinx.android.synthetic.main.fragment_edit_group_bottom.*
import java.util.concurrent.TimeUnit


class EditGroupBottomSheet(var groupTitle: String, val itemClick: (Int) -> Unit) :
    BottomSheetDialogFragment(), EditGroupView{
    private lateinit var dlg : BottomSheetDialog
    var cafeId: Int = 0
    var color = "red"
    var groupType = "??????"
    var check = true
    var title: String = ""
    var questionCycle: String = "everyDay"

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // ??? ????????? ???????????? ?????????
        // XML?????? round ????????? ????????? ???????????? ?????????.
        dlg = ( super.onCreateDialog(savedInstanceState).apply {
            // window?.setDimAmount(0.2f) // Set dim amount here
            setOnShowListener {
                val bottomSheet = findViewById<View>(R.id.design_bottom_sheet) as FrameLayout
                bottomSheet.setBackgroundResource(android.R.color.transparent)

//                 ????????? ???????????? Drag??? ??????????????? ??????.
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
        return inflater.inflate(R.layout.fragment_edit_group_bottom, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var close_btn = requireView().findViewById<ImageView>(R.id.close_btn)
        var ok_btn = requireView().findViewById<ConstraintLayout>(R.id.ok_btn)

        name_edit_txt.setText(groupTitle)
        ok_btn.setBackgroundResource(R.drawable.allow_btn_ok)

        name_edit_txt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                var len = name_edit_txt.text.toString()
                check = if(len.length in 2..10){
                    ok_btn.setBackgroundResource(R.drawable.allow_btn_ok)
                    true
                } else{
                    ok_btn.setBackgroundResource(R.drawable.allow_btn_fail)
                    false
                }
            }
        })

        val seekBar = requireView().findViewById<SeekBar>(R.id.seekBar)

        seekBar.max = 3 // ????????? ????????? ??????
        seekBar.progress = 0 // ?????? ????????? ??? ??????

        // OnSeekBarChange ????????? - Seekbar ??? ????????? ??????????????? Listener
        // OnSeekBarChange ????????? - Seekbar ??? ????????? ??????????????? Listener
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // onStopTrackingTouch - SeekBar ??? ?????? ????????? ????????? ?????? ??????
                questionCycle = if(seekBar.progress == 0){
                    "everyDay"
                } else if(seekBar.progress == 1){
                    "threeDay"
                } else if(seekBar.progress == 2){
                    "fiveDay"
                } else{
                    "sevenDay"
                }
            }
        })

        // group check
        var group_friend_btn = requireView().findViewById<TextView>(R.id.group_friend_btn)
        var group_family_btn = requireView().findViewById<TextView>(R.id.group_family_btn)
        var group_couple_btn = requireView().findViewById<TextView>(R.id.group_couple_btn)
        group_friend_btn.setOnClickListener {
            group_select(group_friend_btn, group_family_btn, group_couple_btn)
        }
        group_family_btn.setOnClickListener {
            group_select(group_family_btn, group_friend_btn, group_couple_btn)
        }
        group_couple_btn.setOnClickListener {
            group_select(group_couple_btn, group_friend_btn, group_family_btn)
        }

        // color check
        var color_orange_btn = requireView().findViewById<ImageView>(R.id.color_orange_btn)
        var color_pink_btn = requireView().findViewById<ImageView>(R.id.color_pink_btn)
        var color_sky_btn = requireView().findViewById<ImageView>(R.id.color_sky_btn)
        var color_yellow_btn = requireView().findViewById<ImageView>(R.id.color_yellow_btn)
        color_orange_btn.setOnClickListener {
            color_select(color_orange_btn, color_yellow_btn, color_pink_btn, color_sky_btn)
        }
        color_pink_btn.setOnClickListener {
            color_select(color_pink_btn, color_yellow_btn, color_orange_btn, color_sky_btn)
        }
        color_sky_btn.setOnClickListener {
            color_select(color_sky_btn, color_yellow_btn, color_pink_btn, color_orange_btn)
        }
        color_yellow_btn.setOnClickListener {
            color_select(color_yellow_btn, color_orange_btn, color_pink_btn, color_sky_btn)
        }

        close_btn.setOnClickListener {
            dismiss()
        }
        ok_btn.setOnClickListener {
            var cafeId = HomeFragment_case().getGroupname(requireContext())
            if(check){
                var request = PostAddGroupRequest(color, groupType, questionCycle, name_edit_txt.text.toString())
                EditGroupService(this).tryEditGroup(cafeId!!, request)
            }
            if (cafeId != null) {

            }
        }
    }

    fun group_select(select: TextView, item1:TextView, item2: TextView){
        item1.setBackgroundResource(R.drawable.group_select_fail)
        item1.setTextColor(Color.parseColor("#333333"))
        item2.setBackgroundResource(R.drawable.group_select_fail)
        item2.setTextColor(Color.parseColor("#333333"))

        select.setBackgroundResource(R.drawable.group_select_ok)
        select.setTextColor(Color.parseColor("#FFFFFF"))

        if(select == group_friend_btn){
            groupType = "??????"
        }
        else if(select == group_family_btn){
            groupType = "??????"
        }
        else{
            groupType = "??????"
        }
    }
    fun color_select(select: ImageView, item1: ImageView, item2: ImageView, item3: ImageView){
        select.setBackgroundResource(R.drawable.color_in_custom_select)

        item1.setBackgroundResource(R.drawable.color_in_custom)
        item2.setBackgroundResource(R.drawable.color_in_custom)
        item3.setBackgroundResource(R.drawable.color_in_custom)

        if(select == color_orange_btn){
            color = "red"
        }
        else if(select == color_pink_btn){
            color = "pink"
        }
        else if(select == color_sky_btn){
            color = "blue"
        }
        else{
            color = "yellow"
        }
    }

    override fun onEditGroupSuccess(response: String) {
        itemClick(0)
        dismiss()
//        Log.d("edit_response", response)
    }

    override fun onEditGroupFailure(message: String) {
        dismiss()
    }
}
