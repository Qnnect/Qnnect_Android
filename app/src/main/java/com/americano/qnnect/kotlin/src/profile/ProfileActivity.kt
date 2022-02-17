package com.americano.qnnect.kotlin.src.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.americano.qnnect.kotlin.R
import com.americano.qnnect.kotlin.base.BaseActivity
import com.americano.qnnect.kotlin.databinding.ActivityProfileBinding
import com.americano.qnnect.kotlin.src.allow.AllowActivity
import com.americano.qnnect.kotlin.src.main.MainActivity
import com.americano.qnnect.kotlin.viewmodel.LoginViewModel
import com.americano.qnnect.kotlin.viewmodel.ProfileViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_profile.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class ProfileActivity : BaseActivity<ActivityProfileBinding, ProfileViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_profile // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: ProfileViewModel by viewModel()

    // image upload vailable
    private val GET_GALLERY_IMAGE = 200
    var path = ""
    var check = false

    override fun initStartView() {
        Glide.with(this)
            .load(R.drawable.img_profile)
            .transform(CenterCrop(), RoundedCorners(200))
            .into(user_img)
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
        profile_img.setOnClickListener {
            viewModel.requestMultiplePermissions(this)
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
            startActivityForResult(intent, GET_GALLERY_IMAGE)

            // 사진 가져오기
            fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
                super.onActivityResult(requestCode, resultCode, data)
                if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.data != null) {
                    val selectedImageUri: Uri? = data.data

                    val uri: Uri = data.getData()!!
                    path = viewModel.getFilePathFromURI(this, uri)

                    Glide.with(this)
                        .load(selectedImageUri)
                        .transform(CenterCrop(), RoundedCorners(200))
                        .into(user_img)
                }
            }
        }

        // rx java 사용
        val observableTextQuery = Observable
            .create(ObservableOnSubscribe { emitter: ObservableEmitter<String>? ->
                nick_name_edit.addTextChangedListener(object : TextWatcher{
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

                if(edit_text_len.text.length != 0){
                    try {
                        edit_text_len.text = str.length.toString()+"/8"
                    }catch (e: Exception){

                    }
                }
            }
            override fun onError(e: Throwable?) {
            }

        })

        ok_btn.setOnClickListener {
            if(check){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}