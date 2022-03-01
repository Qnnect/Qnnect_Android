package com.iame.qnnect.android.src.main.home.home_bottom

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.TextureView
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
import kotlinx.android.synthetic.main.fragment_group_bottom.*


class GroupBottomNextSheet(val itemClick: (Int) -> Unit) :
    BottomSheetDialogFragment(){
    private lateinit var dlg : BottomSheetDialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // 이 코드를 실행하지 않으면
        // XML에서 round 처리를 했어도 적용되지 않는다.
        dlg = ( super.onCreateDialog(savedInstanceState).apply {
            // window?.setDimAmount(0.2f) // Set dim amount here
            setOnShowListener {
                val bottomSheet = findViewById<View>(R.id.design_bottom_sheet) as FrameLayout
                bottomSheet.setBackgroundResource(android.R.color.transparent)

//                 아래와 같이하면 Drag를 불가능하게 한다.
//                val behavior = BottomSheetBehavior.from(bottomSheet!!)
//                behavior.isDraggable = true
//                behavior.state = BottomSheetBehavior.STATE_EXPANDED
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
        return inflater.inflate(R.layout.fragment_group_bottom_next, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var close_btn = view!!.findViewById<ImageView>(R.id.close_btn)
        var ok_btn = view!!.findViewById<ConstraintLayout>(R.id.ok_btn)

        close_btn.setOnClickListener {
            dismiss()
        }
        ok_btn.setOnClickListener {
            dismiss()
            itemClick(0)
        }

        val seekBar = view!!.findViewById<SeekBar>(R.id.seekBar)
        seekBar.max = 3 // 시크바 최대값 설정
        seekBar.progress = 0 // 초기 시크바 값 설정

        // group check
        var group_friend_btn = view!!.findViewById<TextView>(R.id.group_friend_btn)
        var group_family_btn = view!!.findViewById<TextView>(R.id.group_family_btn)
        var group_couple_btn = view!!.findViewById<TextView>(R.id.group_couple_btn)
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
        var color_orange_btn = view!!.findViewById<ImageView>(R.id.color_orange_btn)
        var color_brown_btn = view!!.findViewById<ImageView>(R.id.color_brown_btn)
        var color_pink_btn = view!!.findViewById<ImageView>(R.id.color_pink_btn)
        var color_sky_btn = view!!.findViewById<ImageView>(R.id.color_sky_btn)
        var color_yellow_btn = view!!.findViewById<ImageView>(R.id.color_yellow_btn)
        color_orange_btn.setOnClickListener {
            color_select(color_orange_btn, color_brown_btn, color_pink_btn, color_sky_btn, color_yellow_btn)
        }
        color_brown_btn.setOnClickListener {
            color_select(color_brown_btn, color_orange_btn, color_pink_btn, color_sky_btn, color_yellow_btn)
        }
        color_pink_btn.setOnClickListener {
            color_select(color_pink_btn, color_brown_btn, color_orange_btn, color_sky_btn, color_yellow_btn)
        }
        color_sky_btn.setOnClickListener {
            color_select(color_sky_btn, color_brown_btn, color_pink_btn, color_orange_btn, color_yellow_btn)
        }
        color_yellow_btn.setOnClickListener {
            color_select(color_yellow_btn, color_brown_btn, color_pink_btn, color_sky_btn, color_orange_btn)
        }
    }

    fun group_select(select: TextView, item1:TextView, item2: TextView){
        item1.setBackgroundResource(R.drawable.group_select_fail)
        item1.setTextColor(Color.parseColor("#333333"))
        item2.setBackgroundResource(R.drawable.group_select_fail)
        item2.setTextColor(Color.parseColor("#333333"))

        select.setBackgroundResource(R.drawable.group_select_ok)
        select.setTextColor(Color.parseColor("#FFFFFF"))
    }
    fun color_select(select: ImageView, item1: ImageView, item2: ImageView, item3: ImageView, item4: ImageView){
        select.setBackgroundResource(R.drawable.color_in_custom_select)

        item1.setBackgroundResource(R.drawable.color_in_custom)
        item2.setBackgroundResource(R.drawable.color_in_custom)
        item3.setBackgroundResource(R.drawable.color_in_custom)
        item4.setBackgroundResource(R.drawable.color_in_custom)
    }
}
