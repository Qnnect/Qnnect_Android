package com.iame.qnnect.android.src.profile

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityProfileBinding
import com.iame.qnnect.android.src.main.MainActivity
import com.iame.qnnect.android.viewmodel.ProfileViewModel
import com.kakao.sdk.talk.TalkApiClient
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.nick_name_edit
import kotlinx.android.synthetic.main.activity_profile.ok_btn
import kotlinx.android.synthetic.main.activity_profile.profile_img
import kotlinx.android.synthetic.main.activity_profile.user_img
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.util.concurrent.TimeUnit

class ProfileActivity : BaseActivity<ActivityProfileBinding, ProfileViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_profile // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: ProfileViewModel by viewModel()

    // image upload vailable
    private val GET_GALLERY_IMAGE = 200
    var path = ""
    var check = false
    var default_img_check = true

    var userProfile = ""

    override fun initStartView() {
        Glide.with(this)
            .load(R.mipmap.img_profile_dafault_foreground)
            .transform(CenterCrop(), RoundedCorners(200))
            .into(user_img)
    }

    override fun initDataBinding() {
        viewModel.profileResponse.observe(this, Observer {
            var intent = Intent(this, MainActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP //액티비티 스택제거
            startActivity(intent)
            finish()
            dismissLoadingDialog()
        })
        viewModel.profileDefaultResponse.observe(this, Observer {
            // nickname
            val nickname = nick_name_edit.text.toString()
            val nicknamePart: MultipartBody.Part = MultipartBody.Part.createFormData("nickName", nickname)

            viewModel.patchProfile(null , nicknamePart)
        })
    }

    override fun initAfterBinding() {
        binding.profileImg.setOnClickListener {
            val editImageSheet: EditImageBottomSheet = EditImageBottomSheet {
                when (it) {
                    0 -> {
                        default_img_check = true
                        Glide.with(this)
                            .load(R.mipmap.img_profile_dafault_foreground)
                            .transform(CenterCrop(), RoundedCorners(200))
                            .into(user_img)
                    }
                    1 -> {
                        default_img_check = false
                        viewModel.requestMultiplePermissions(this)
                        val intent = Intent(Intent.ACTION_PICK)
                        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
                        startActivityForResult(intent, GET_GALLERY_IMAGE)
                    }
                }
            }
            editImageSheet.show(supportFragmentManager, editImageSheet.tag)
        }


        binding.nickNameEdit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                check = nicknameCheck(nick_name_edit, ok_btn, check_txt1, edit_text_len)
            }
        })

        binding.okBtn.setOnClickListener {
            if(check){
                // nickname
                val nickname = nick_name_edit.text.toString()
                val nicknamePart: MultipartBody.Part = MultipartBody.Part.createFormData("nickName", nickname)
                // 이미지
                if(path == ""){
                    if(default_img_check){
                        showLoadingDialog(this)
                        viewModel.patchDefaultProfile()
                    }
                }
                else{
                    val file = File(path)
                    val requestBody: RequestBody = file.asRequestBody("multipart/form-data".toMediaType())
                    val fileToUpload: MultipartBody.Part = MultipartBody.Part.createFormData("profilePicture", "photo.jpg", requestBody)

                    showLoadingDialog(this)
                    viewModel.patchProfile(fileToUpload , nicknamePart)
                }
            }
        }
    }

    // 사진 가져오기
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.data != null) {
            val selectedImageUri: Uri? = data.data

            val uri: Uri = data.getData()!!
            path = viewModel.getFilePathFromURI(this, uri)
            Log.d("image_path", uri.toString())
            Log.d("image_path", path.toString())

            Glide.with(this)
                .load(selectedImageUri)
                .transform(CenterCrop(), RoundedCorners(200))
                .into(user_img)
        }
    }


    // nickname check
    fun nicknameCheck(nick_name_edit: EditText, ok_btn: ConstraintLayout, check_txt: TextView, len_check: TextView): Boolean{
        var str = nick_name_edit.text.toString()
        return if(str.length in 2..8 && str != "null"){
            ok_btn.setBackgroundResource(R.drawable.allow_btn_ok)
            nick_name_edit.setBackgroundResource(R.drawable.nickname_edit_ok)
            check_txt.visibility = View.INVISIBLE
            len_check.text = str.length.toString()+"/8"
            true
        } else{
            check_txt.visibility = View.VISIBLE
            nick_name_edit.setBackgroundResource(R.drawable.nickname_edit)
            ok_btn.setBackgroundResource(R.drawable.allow_btn_fail)
            len_check.text = str.length.toString()+"/8"
            false
        }
    }
}