package com.iame.qnnect.android.src.answer

import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityAnswerBinding
import com.iame.qnnect.android.viewmodel.AnswerViewModel
import kotlinx.android.synthetic.main.activity_answer.*
import kotlinx.android.synthetic.main.activity_diary.back_btn
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception

class AnswerActivity : BaseActivity<ActivityAnswerBinding, AnswerViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_answer // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: AnswerViewModel by viewModel()

    var uriList: ArrayList<Uri> = ArrayList() // 이미지의 uri를 담을 ArrayList 객체

    var recyclerView // 이미지를 보여줄 리사이클러뷰
            : RecyclerView? = null
    var adapter // 리사이클러뷰에 적용시킬 어댑터
            : MultiImageAdapter? = null

    override fun initStartView() {
        recyclerView = image_recycler
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
        save_btn.setOnClickListener {
            finish()
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
}