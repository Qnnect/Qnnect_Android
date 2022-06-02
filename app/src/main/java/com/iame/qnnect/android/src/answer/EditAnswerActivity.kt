package com.iame.qnnect.android.src.answer

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.AsyncTask
import android.os.Environment
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.test.core.app.ApplicationProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.base.HomeFragment_case
import com.iame.qnnect.android.databinding.ActivityAnswerBinding
import com.iame.qnnect.android.viewmodel.AnswerViewModel
import kotlinx.android.synthetic.main.activity_answer.*
import kotlinx.android.synthetic.main.activity_answer.bookmark_txt
import kotlinx.android.synthetic.main.fragment_store.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import com.google.firebase.messaging.ImageDownload
import java.io.OutputStream


class EditAnswerActivity : BaseActivity<ActivityAnswerBinding, AnswerViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_answer // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: AnswerViewModel by viewModel()
    private val editImageAdapter: EditImageAdapter by inject()

    var uriList: ArrayList<Uri> = ArrayList() // 이미지의 uri를 담을 ArrayList 객체
    var pathList: ArrayList<String> = ArrayList()

    var home = HomeFragment_case()
    var cafeId = 0

    var commentId = 0
    var date = ""
    var dday = ""
    var questioner = ""
    var question = ""

    var check = false

    var delete_list: ArrayList<Int> = ArrayList()

    var before_size = 0

    override fun initStartView() {
        binding.bookmarkTxt.text = "답변 수정"

        commentId = intent.getIntExtra("commentId", 0)

        binding.answerEdit.setText(intent.getStringExtra("content")!!)
        binding.createDate.text = intent.getStringExtra("date")!!
        binding.ddayTxt.text = intent.getStringExtra("dday")!!
        binding.whoQuestion.text = intent.getStringExtra("questioner")!!+"의 질문"
        binding.questionTxt.text = intent.getStringExtra("question")!!

        // image recycler
        binding.imageRecycler.run {
            adapter = editImageAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        viewModel.userResponse.observe(this, Observer {
            var image = it.profileImage

            // Profile Url
            Glide.with(this)
                .load(image)
                .transform(CenterCrop(), RoundedCorners(200))
                .into(binding.myProfileImg)
            // User Name
            my_profile_name.text = it.nickName
            dismissLoadingDialog()
        })

        viewModel.replyResponse.observe(this, Observer {
            binding.answerEdit.setText(it.content)
            if(it.imageUrl1 != null){
//                ImageDownload().execute(it.imageUrl1)
                var uri = downloadImage(it.imageUrl1)
                Log.d("uri_reponse", uri)
//                uriList.add(url)
            }
            if(it.imageUrl2 != null){
//                ImageDownload().execute(it.imageUrl2)
                var uri = downloadImage(it.imageUrl2)
                Log.d("uri_reponse", uri)
//                uriList.add(url)
            }
            if(it.imageUrl3 != null){
//                ImageDownload().execute(it.imageUrl3)
                var uri = downloadImage(it.imageUrl3)
                Log.d("uri_reponse", uri)
//                uriList.add(url)
            }
            if(it.imageUrl4 != null){
//                ImageDownload().execute(it.imageUrl4)
                var uri = downloadImage(it.imageUrl4)
                Log.d("uri_reponse", uri)
//                uriList.add(url)
            }
            if(it.imageUrl5 != null){
//                ImageDownload().execute(it.imageUrl5)
                var uri = downloadImage(it.imageUrl5)
                Log.d("uri_reponse", uri)
//                uriList.add(url)
            }

            editImageAdapter.notifyDataSetChanged()

            viewModel.getUser()
        })

        viewModel.badResponse.observe(this, Observer {
            dismissLoadingDialog()
            Toast.makeText(baseContext, "질문을 답변할 수 있는 기간이 지났습니다", Toast.LENGTH_SHORT).show()
        })

    }

    override fun initAfterBinding() {
        viewModel.getReply(commentId)
        showLoadingDialog(this)

        editImageAdapter!!.setOnItemClickListener(object : EditImageAdapter.OnItemClickListener {
            override fun onItemClick(v: View?, position: Int) {
                if(position < before_size){
                    delete_list.add(position+1)
                    editImageAdapter.deleteItem(position)
                }
            }
        })

        binding.answerEdit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                var len = answer_edit.text.toString()
                check = if(len.length in 10..99){
                    binding.saveBtn.setTextColor(Color.parseColor("#FD774C"))
                    true
                } else{
                    binding.saveBtn.setTextColor(Color.parseColor("#BDBDBD"))
                    false
                }
            }
        })

        binding.saveBtn.setOnClickListener {
            if(check){
                showLoadingDialog(this)
            }
        }

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.galleryBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = MediaStore.Images.Media.CONTENT_TYPE
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            startActivityForResult(intent, 2222)
        }
    }

    // 앨범에서 액티비티로 돌아온 후 실행되는 메서드
    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null) {   // 어떤 이미지도 선택하지 않은 경우
            Toast.makeText(applicationContext, "이미지를 선택하지 않았습니다.", Toast.LENGTH_LONG).show()
        } else {   // 이미지를 하나라도 선택한 경우
            if (data.clipData == null) {     // 이미지를 하나만 선택한 경우
                Log.e("single choice: ", data.data.toString())
                val imageUri: Uri? = data.data
                editImageAdapter.addItem(imageUri!!)
                editImageAdapter.notifyDataSetChanged()
            } else {      // 이미지를 여러장 선택한 경우
                val clipData = data.clipData
                Log.e("clipData", clipData!!.itemCount.toString())
                if (clipData.itemCount > 6 ) {   // 선택한 이미지가 6 이상인 경우
                    Toast.makeText(applicationContext, "사진은 5장까지 선택 가능합니다.", Toast.LENGTH_LONG)
                        .show()
                } else {   // 선택한 이미지가 1장 이상 10장 이하인 경우
                    Log.e(TAG, "multiple choice")
                    for (i in 0 until clipData.itemCount) {
                        val imageUri: Uri = clipData.getItemAt(i).uri // 선택한 이미지들의 uri를 가져온다.
                        try {
                            uriList.add(imageUri) //uri를 list에 담는다.
                            for (i in 0..delete_list.size){
                                var index = delete_list.get(i)-1
                                editImageAdapter.addPositionItem(index, imageUri!!)
                            }

                            editImageAdapter.notifyDataSetChanged()
                        } catch (e: Exception) {
                            Log.e(TAG, "File select error", e)
                        }
                    }
                }
            }
        }
    }

    companion object {
        private const val TAG = "MultiImageActivity"
    }

    fun getPath(path: String, name: String): MultipartBody.Part{
        val file = File(path)
        val requestBody: RequestBody = file.asRequestBody("multipart/form-data".toMediaType())
        val fileToUpload: MultipartBody.Part = MultipartBody.Part.createFormData(name, name+".jpg", requestBody)

        return fileToUpload
    }

    fun downloadImage(imageURL: String): String {
        var uri = ""
        val dirPath =
            Environment.getExternalStorageDirectory().absolutePath + "/" + "Qnnect" + "/"
        val dir = File(dirPath)
        val fileName = imageURL.substring(imageURL.lastIndexOf('/') + 1)
        Glide.with(this)
            .load(imageURL)
            .into(object : CustomTarget<Drawable?>() {
                override fun onResourceReady(
                    @NonNull resource: Drawable,
                    @Nullable transition: Transition<in Drawable?>?,
                ) {
                    val bitmap = (resource as BitmapDrawable).bitmap
                    Toast.makeText(this@EditAnswerActivity, "Saving Image...", Toast.LENGTH_SHORT).show()
                    uri = saveImage(bitmap, dir, fileName)
                }

                override fun onLoadCleared(@Nullable placeholder: Drawable?) {}
                override fun onLoadFailed(@Nullable errorDrawable: Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    Toast.makeText(this@EditAnswerActivity,
                        "Failed to Download Image! Please try again later.",
                        Toast.LENGTH_SHORT).show()
                }
            })
        return uri
    }

    private fun saveImage(image: Bitmap, storageDir: File, imageFileName: String): String {
        var successDirCreated = false
        if (!storageDir.exists()) {
            successDirCreated = storageDir.mkdir()
        }
        if (successDirCreated) {
            val imageFile = File(storageDir, imageFileName)
            val savedImagePath = imageFile.absolutePath
            try {
                val fOut: OutputStream = FileOutputStream(imageFile)
                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut)
                fOut.close()
                Toast.makeText(this@EditAnswerActivity, "Image Saved!", Toast.LENGTH_SHORT).show()
                return savedImagePath
            } catch (e: Exception) {
                Toast.makeText(this@EditAnswerActivity, "Error while saving image!", Toast.LENGTH_SHORT)
                    .show()
                e.printStackTrace()
                return "null"
            }
        } else {
            Toast.makeText(this, "Failed to make folder!", Toast.LENGTH_SHORT).show()
            return "null"
        }
    }
}