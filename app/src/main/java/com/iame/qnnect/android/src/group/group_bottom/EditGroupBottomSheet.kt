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
import java.util.concurrent.TimeUnit


class EditGroupBottomSheet(val itemClick: (Int) -> Unit) :
    BottomSheetDialogFragment(), EditGroupView{
    private lateinit var dlg : BottomSheetDialog
    var cafeId: Int = 0
    var color = "red"
    var groupType = "친구"
    var check = false
    var title: String = ""
    var questionCycle: String = "everyDay"

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
        return inflater.inflate(R.layout.fragment_edit_group_bottom, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var close_btn = view!!.findViewById<ImageView>(R.id.close_btn)
        var ok_btn = view!!.findViewById<ConstraintLayout>(R.id.ok_btn)

        // rx java 사용
        val observableTextQuery = Observable
            .create(ObservableOnSubscribe { emitter: ObservableEmitter<String>? ->
                name_edit_txt.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int,
                    ) {
                        emitter?.onNext(s.toString())
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    }
                })
            })
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())

        observableTextQuery.subscribe(object : io.reactivex.rxjava3.core.Observer<String> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable?) {
            }

            override fun onNext(t: String) {
                var len = name_edit_txt.text.toString()
                if(len.length > 0 && len.length < 11){
                    ok_btn.setBackgroundResource(R.drawable.allow_btn_ok)
                    check = true
                }
                else{
                    ok_btn.setBackgroundResource(R.drawable.allow_btn_fail)
                    check = false
                }
            }
            override fun onError(e: Throwable?) {
            }

        })

        val seekBar = view!!.findViewById<SeekBar>(R.id.seekBar)

        seekBar.max = 3 // 시크바 최대값 설정
        seekBar.progress = 0 // 초기 시크바 값 설정

        // OnSeekBarChange 리스너 - Seekbar 값 변경시 이벤트처리 Listener
        // OnSeekBarChange 리스너 - Seekbar 값 변경시 이벤트처리 Listener
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // onStopTrackingTouch - SeekBar 값 변경 끝나고 드래그 떼면 호출
                if(seekBar.progress == 0){
                    questionCycle = "everyDay"
                }
                else if(seekBar.progress == 1){
                    questionCycle = "threeDay"
                }
                else if(seekBar.progress == 2){
                    questionCycle = "fiveDay"
                }
                else{
                    questionCycle = "sevenDay"
                }
            }
        })

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
        var color_pink_btn = view!!.findViewById<ImageView>(R.id.color_pink_btn)
        var color_sky_btn = view!!.findViewById<ImageView>(R.id.color_sky_btn)
        var color_yellow_btn = view!!.findViewById<ImageView>(R.id.color_yellow_btn)
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
            var cafeId = HomeFragment_case().getGroupname(context!!)
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
            groupType = "친구"
        }
        else if(select == group_family_btn){
            groupType = "가족"
        }
        else{
            groupType = "커플"
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
        TODO("Not yet implemented")
    }
}
