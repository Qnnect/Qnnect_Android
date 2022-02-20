package com.iame.qnnect.android.src.main.mypage

import android.text.Editable
import android.text.TextWatcher
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityEditProfileBinding
import com.iame.qnnect.android.viewmodel.EditProfileViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class EditprofileActivity : BaseActivity<ActivityEditProfileBinding, EditProfileViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_edit_profile // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: EditProfileViewModel by viewModel()

    var check = false

    override fun initStartView() {
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
        // rx java 사용
        val observableTextQuery = Observable
            .create(ObservableOnSubscribe { emitter: ObservableEmitter<String>? ->
                nick_name_edit.addTextChangedListener(object : TextWatcher {
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
                var str = nick_name_edit.text.toString()
                if(str.length > 0 && str.length < 9 && str != "null"){
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

        ok_btn.setOnClickListener {
            if(check){
                finish()
            }
        }
    }

}