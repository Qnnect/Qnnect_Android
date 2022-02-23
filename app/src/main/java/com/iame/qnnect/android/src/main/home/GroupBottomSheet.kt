package com.iame.qnnect.android.src.main.home

import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import androidx.viewpager2.widget.ViewPager2
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseFragment
import com.iame.qnnect.android.databinding.FragmentGroupBottomBinding
import com.iame.qnnect.android.viewmodel.GroupBottomViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.fragment_group_bottom.*
import kotlinx.android.synthetic.main.fragment_group_bottom.ok_btn
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class GroupBottomSheet(view: MainGroupBottomSheet, viewPager: ViewPager2) : BaseFragment<FragmentGroupBottomBinding, GroupBottomViewModel>(R.layout.fragment_group_bottom) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_group_bottom // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: GroupBottomViewModel by viewModel()
    var viewPager = viewPager
    var check = false

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
        ok_btn.setOnClickListener {
            if(check){
                viewPager.setCurrentItem(1, false)
            }
        }
        // group check
        group_friend_btn.setOnClickListener {
            viewModel.group_select(group_friend_btn, group_family_btn, group_couple_btn)
        }
        group_family_btn.setOnClickListener {
            viewModel.group_select(group_family_btn, group_friend_btn, group_couple_btn)
        }
        group_couple_btn.setOnClickListener {
            viewModel.group_select(group_couple_btn, group_friend_btn, group_family_btn)
        }

        // color check
        color_orange_btn.setOnClickListener {
            viewModel.color_select(color_orange_btn, color_brown_btn, color_pink_btn, color_sky_btn, color_yellow_btn)
        }
        color_brown_btn.setOnClickListener {
            viewModel.color_select(color_brown_btn, color_orange_btn, color_pink_btn, color_sky_btn, color_yellow_btn)
        }
        color_pink_btn.setOnClickListener {
            viewModel.color_select(color_pink_btn, color_brown_btn, color_orange_btn, color_sky_btn, color_yellow_btn)
        }
        color_sky_btn.setOnClickListener {
            viewModel.color_select(color_sky_btn, color_brown_btn, color_pink_btn, color_orange_btn, color_yellow_btn)
        }
        color_yellow_btn.setOnClickListener {
            viewModel.color_select(color_yellow_btn, color_brown_btn, color_pink_btn, color_sky_btn, color_orange_btn)
        }

        // name check
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
                        after: Int
                    ) {
                        emitter?.onNext(s.toString())
                    }

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    }
                })
            })
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())

        observableTextQuery.subscribe(object : Observer<String> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable?) {
            }

            override fun onNext(t: String) {
                var str = name_edit_txt.text.toString()
                if(str.length > 0 && str.length < 11 && str != "null"){
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
    }
}


