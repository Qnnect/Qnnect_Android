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
import com.iame.qnnect.android.src.add_drink.AddDrinkBottomSheet
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
        get() = R.layout.activity_answer // get() : ????????? ?????????, ????????? ??????

    override val viewModel: AnswerViewModel by viewModel()

    var uriList: ArrayList<Uri> = ArrayList() // ???????????? uri??? ?????? ArrayList ??????
    var pathList: ArrayList<String> = ArrayList()

    var recyclerView // ???????????? ????????? ??????????????????
            : RecyclerView? = null
    var adapter // ????????????????????? ???????????? ?????????
            : MultiImageAdapter? = null

    var home = HomeFragment_case()
    var cafeId = 0

    var cafeQuestionId = 0
    var date = ""
    var dday = ""
    var questioner = ""
    var question = ""
    var dday_num = 0

    var check = false

    override fun initStartView() {
        recyclerView = image_recycler
    }

    override fun initDataBinding() {
        cafeQuestionId = intent.getIntExtra("cafeQuestionId", 0)
        binding.createDate.text = intent.getStringExtra("date")!!
        binding.ddayTxt.text = intent.getStringExtra("dday")!!
        binding.whoQuestion.text = intent.getStringExtra("questioner")!!+"??? ??????"
        binding.questionTxt.text = intent.getStringExtra("question")!!
        dday_num = intent.getIntExtra("dday_num", 0)

        binding.answerEdit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                var len = binding.answerEdit.text.toString()
                check = if(len.length in 10..99){
                    binding.saveBtn.setTextColor(Color.parseColor("#FD774C"))
                    true
                } else{
                    binding.saveBtn.setTextColor(Color.parseColor("#BDBDBD"))
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
                .into(binding.myProfileImg)
            // User Name
            binding.myProfileName.text = it.nickName
            dismissLoadingDialog()
        })
    }

    override fun initAfterBinding() {
        viewModel.getUser()
        showLoadingDialog(this)

        binding.saveBtn.setOnClickListener {
            if(check){
                for(i in 0 until uriList.size){
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
                    if(dday_num == 0){
                        Toast.makeText(baseContext, "????????? ????????? ??? ?????? ????????? ???????????????", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(baseContext, it, Toast.LENGTH_SHORT).show()
                    }
                })
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

    // ???????????? ??????????????? ????????? ??? ???????????? ?????????
    override fun onActivityResult(requestCode: Int, resultCode: Int, @Nullable data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data == null) {   // ?????? ???????????? ???????????? ?????? ??????
            Toast.makeText(applicationContext, "???????????? ???????????? ???????????????.", Toast.LENGTH_LONG).show()
        } else {   // ???????????? ???????????? ????????? ??????
            if (data.clipData == null) {     // ???????????? ????????? ????????? ??????
                Log.e("single choice: ", data.data.toString())
                val imageUri: Uri? = data.data
                uriList.add(imageUri!!)
                adapter = MultiImageAdapter(uriList, applicationContext)
                recyclerView!!.adapter = adapter
                recyclerView!!.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
            } else {      // ???????????? ????????? ????????? ??????
                val clipData = data.clipData
                Log.e("clipData", clipData!!.itemCount.toString())
                if (clipData.itemCount > 5) {   // ????????? ???????????? 6 ????????? ??????
                    Toast.makeText(applicationContext, "????????? 5????????? ?????? ???????????????.", Toast.LENGTH_LONG)
                        .show()
                } else {   // ????????? ???????????? 1??? ?????? 10??? ????????? ??????
                    Log.e(TAG, "multiple choice")
                    for (i in 0 until clipData.itemCount) {
                        val imageUri: Uri = clipData.getItemAt(i).uri // ????????? ??????????????? uri??? ????????????.
                        try {
                            uriList.add(imageUri) //uri??? list??? ?????????.
                        } catch (e: Exception) {
                            Log.e(TAG, "File select error", e)
                        }
                    }
                    adapter = MultiImageAdapter(uriList, applicationContext)
                    recyclerView!!.adapter = adapter // ????????????????????? ????????? ??????
                    recyclerView!!.layoutManager =
                        LinearLayoutManager(this,
                            LinearLayoutManager.HORIZONTAL,
                            true) // ?????????????????? ?????? ????????? ??????
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