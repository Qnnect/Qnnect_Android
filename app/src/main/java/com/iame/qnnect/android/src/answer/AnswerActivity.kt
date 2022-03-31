package com.iame.qnnect.android.src.answer

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.base.HomeFragment_case
import com.iame.qnnect.android.databinding.ActivityAnswerBinding
import com.iame.qnnect.android.viewmodel.AnswerViewModel
import kotlinx.android.synthetic.main.activity_answer.*
import kotlinx.android.synthetic.main.activity_answer.create_date
import kotlinx.android.synthetic.main.activity_answer.dday_txt
import kotlinx.android.synthetic.main.activity_answer.my_profile_img
import kotlinx.android.synthetic.main.activity_answer.my_profile_name
import kotlinx.android.synthetic.main.activity_answer.question_txt
import kotlinx.android.synthetic.main.activity_answer.save_btn
import kotlinx.android.synthetic.main.activity_answer.who_question
import kotlinx.android.synthetic.main.activity_diary.*
import kotlinx.android.synthetic.main.activity_diary.back_btn
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_profile.nick_name_edit
import kotlinx.android.synthetic.main.activity_profile.user_img
import kotlinx.android.synthetic.main.activity_question.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.lang.Exception

class AnswerActivity : BaseActivity<ActivityAnswerBinding, AnswerViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_answer // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: AnswerViewModel by viewModel()

    var uriList: ArrayList<Uri> = ArrayList() // 이미지의 uri를 담을 ArrayList 객체
    var pathList: ArrayList<String> = ArrayList()

    var recyclerView // 이미지를 보여줄 리사이클러뷰
            : RecyclerView? = null
    var adapter // 리사이클러뷰에 적용시킬 어댑터
            : MultiImageAdapter? = null

    var home = HomeFragment_case()
    var cafeId = 0

    var cafeQuestionId = 0
    var date = ""
    var dday = ""
    var questioner = ""
    var question = ""

    var check = false

    override fun initStartView() {
        recyclerView = image_recycler
    }

    override fun initDataBinding() {
        cafeQuestionId = intent.getIntExtra("cafeQuestionId", 0)
        create_date.text = intent.getStringExtra("date")!!
        dday_txt.text = intent.getStringExtra("dday")!!
        who_question.text = intent.getStringExtra("questioner")!!+"의 질문"
        question_txt.text = intent.getStringExtra("question")!!

        answer_edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                var len = answer_edit.text.toString()
                check = if(len.length in 10..99){
                    save_btn.setTextColor(Color.parseColor("#FD774C"))
                    true
                } else{
                    save_btn.setTextColor(Color.parseColor("#BDBDBD"))
                    false
                }
            }
        })

        viewModel.userResponse.observe(this, Observer {
            var image = it.profileImage

            // Profile Url
            Glide.with(this)
                .load(image)
                .transform(CenterCrop(), RoundedCorners(200))
                .into(my_profile_img)
            // User Name
            my_profile_name.setText(it.nickName)
            dismissLoadingDialog()
        })
    }

    override fun initAfterBinding() {
        viewModel.getUser()
        showLoadingDialog(this)

        save_btn.setOnClickListener {
            if(check){
                for(i in 0..uriList.size-1){
                    var path = viewModel.getFilePathFromURI(this, uriList.get(i))
                    pathList.add(path)
                }

                val content = answer_edit.text.toString()
                val contentPart: MultipartBody.Part = MultipartBody.Part.createFormData("content", content)

                if(uriList.size == 0){
                    viewModel.postAnswer(null, null, null, null, null,
                        contentPart, cafeQuestionId)
                }
                else if(uriList.size == 1){
                    var request = getPath(pathList.get(0), "image1")
                    viewModel.postAnswer(null, null, null, null, request,
                        contentPart, cafeQuestionId)
                }
                else if(uriList.size == 2){
                    var request1 = getPath(pathList.get(0), "image1")
                    var request2 = getPath(pathList.get(1), "image2")
                    viewModel.postAnswer(null, null, null, request2, request1,
                        contentPart, cafeQuestionId)
                }
                else if(uriList.size == 3){
                    var request1 = getPath(pathList.get(0), "image1")
                    var request2 = getPath(pathList.get(1), "image2")
                    var request3 = getPath(pathList.get(2), "image3")
                    viewModel.postAnswer(null, null, request3, request2, request1,
                        contentPart, cafeQuestionId)
                }
                else if(uriList.size == 4){
                    var request1 = getPath(pathList.get(0), "image1")
                    var request2 = getPath(pathList.get(1), "image2")
                    var request3 = getPath(pathList.get(2), "image3")
                    var request4 = getPath(pathList.get(3), "image4")
                    viewModel.postAnswer(null, request4, request3, request2, request1,
                        contentPart, cafeQuestionId)
                }
                else{
                    var request1 = getPath(pathList.get(0), "image1")
                    var request2 = getPath(pathList.get(1), "image2")
                    var request3 = getPath(pathList.get(2), "image3")
                    var request4 = getPath(pathList.get(3), "image4")
                    var request5 = getPath(pathList.get(3), "image5")
                    viewModel.postAnswer(request5, request4, request3, request2, request1,
                        contentPart, cafeQuestionId)
                }
                showLoadingDialog(this)

                viewModel.answerResponse.observe(this, Observer {
                    dismissLoadingDialog()
                    finish()
                })

                viewModel.badResponse.observe(this, Observer {
                    dismissLoadingDialog()
                    Toast.makeText(baseContext, "질문을 답변할 수 있는 기간이 지났습니다", Toast.LENGTH_SHORT).show()
                })
            }
        }

        back_btn.setOnClickListener {
            finish()
        }

        gallery_btn.setOnClickListener {
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
                uriList.add(imageUri!!)
                adapter = MultiImageAdapter(uriList, applicationContext)
                recyclerView!!.adapter = adapter
                recyclerView!!.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
            } else {      // 이미지를 여러장 선택한 경우
                val clipData = data.clipData
                Log.e("clipData", clipData!!.itemCount.toString())
                if (clipData.itemCount > 5) {   // 선택한 이미지가 6 이상인 경우
                    Toast.makeText(applicationContext, "사진은 5장까지 선택 가능합니다.", Toast.LENGTH_LONG)
                        .show()
                } else {   // 선택한 이미지가 1장 이상 10장 이하인 경우
                    Log.e(TAG, "multiple choice")
                    for (i in 0 until clipData.itemCount) {
                        val imageUri: Uri = clipData.getItemAt(i).uri // 선택한 이미지들의 uri를 가져온다.
                        try {
                            uriList.add(imageUri) //uri를 list에 담는다.
                        } catch (e: Exception) {
                            Log.e(TAG, "File select error", e)
                        }
                    }
                    adapter = MultiImageAdapter(uriList, applicationContext)
                    recyclerView!!.adapter = adapter // 리사이클러뷰에 어댑터 세팅
                    recyclerView!!.layoutManager =
                        LinearLayoutManager(this,
                            LinearLayoutManager.HORIZONTAL,
                            true) // 리사이클러뷰 수평 스크롤 적용
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
}