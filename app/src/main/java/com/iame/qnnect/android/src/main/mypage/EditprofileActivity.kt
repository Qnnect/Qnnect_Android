package com.iame.qnnect.android.src.main.mypage

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityEditProfileBinding
import com.iame.qnnect.android.src.profile.EditImageBottomSheet
import com.iame.qnnect.android.viewmodel.EditProfileViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_edit_profile.*
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.util.concurrent.TimeUnit


class EditprofileActivity : BaseActivity<ActivityEditProfileBinding, EditProfileViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_edit_profile // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: EditProfileViewModel by viewModel()

    // image upload vailable
    private val GET_GALLERY_IMAGE = 200
    var path = ""
    var check = false
    var default_img_check = false

    override fun initStartView() {
    }

    override fun initDataBinding() {
        viewModel.getUser()

        viewModel.userResponse.observe(this, Observer {
            var image = it.profileImage

            // Profile Url
            Glide.with(this)
                .load(image)
                .transform(CenterCrop(), RoundedCorners(200))
                .into(user_img)
            // User Name
            nick_name_edit.setText(it.nickName)
        })
    }

    override fun initAfterBinding() {
        profile_img.setOnClickListener {
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

        nick_name_edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                check = viewModel.nickname_check(nick_name_edit, ok_btn, check_txt)
            }
        })

        ok_btn.setOnClickListener {
            if(check){
                // nickname
                val nickname = nick_name_edit.text.toString()
                val nicknamePart: MultipartBody.Part = MultipartBody.Part.createFormData("nickName", nickname)
                // 이미지
                if(path == ""){
                    if(default_img_check){
                        viewModel.patchDefaultProfile()
                    }
                    viewModel.patchProfile(null , nicknamePart)
                }
                else{
                    val file = File(path)
                    val requestBody: RequestBody = file.asRequestBody("multipart/form-data".toMediaType())
                    val fileToUpload: MultipartBody.Part = MultipartBody.Part.createFormData("profilePicture", "photo.jpg", requestBody)

                    viewModel.patchProfile(fileToUpload , nicknamePart)
                }

                viewModel.profileResponse.observe(this, Observer {
                    finish()
                })
            }
        }

        back_btn.setOnClickListener {
            finish()
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
}