package com.iame.qnnect.android.src.question

import android.content.Intent
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.Observer
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.base.HomeFragment_case
import com.iame.qnnect.android.databinding.ActivityQuestionBinding
import com.iame.qnnect.android.viewmodel.QuestionViewModel
import kotlinx.android.synthetic.main.activity_question.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class QuestionActivity : BaseActivity<ActivityQuestionBinding, QuestionViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_question // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: QuestionViewModel by viewModel()

    var check = false

    override fun initStartView() {
    }

    override fun initDataBinding() {
        viewModel.postquestionResponse.observe(this, Observer {
            Log.d("question test", "질문 성공 "+it.toString()+"번째 질문")
            val questionCompleteDialog = QuestionCompleteDialog {
                when (it) {
                    // 카페로 가기
                    0 -> {
                        finish()
                    }
                    // 카페별 질문 리스트로 이동
                    1 -> {
                        var intent = Intent(this, UserQuestionActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
            questionCompleteDialog.show(supportFragmentManager, questionCompleteDialog.tag)
        })
    }

    override fun initAfterBinding() {
        binding.contents.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int, ) {
                var len = contents.text.toString()
                check = if(len.length in 10..49){
                    binding.saveBtn.setTextColor(Color.parseColor("#FD774C"))
                    true
                } else{
                    binding.saveBtn.setTextColor(Color.parseColor("#BDBDBD"))
                    false
                }
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        binding.saveBtn.setOnClickListener {
            if(check){
                var cafeId = HomeFragment_case().getGroupname(this)
                viewModel.postQuestion(cafeId!!, contents.text.toString())
                Log.d("question test", "질문 성공 "+contents.text.toString())
            }
        }

        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}