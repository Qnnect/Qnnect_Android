package com.iame.qnnect.android.src.notarrivecafe

import android.content.Intent
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityMyQuestionBinding
import com.iame.qnnect.android.src.reply.reply_more.ReplyMoreBottomSheet
import com.iame.qnnect.android.viewmodel.MyQuestionViewModel
import com.iame.qnnect.android.viewmodel.NotArriveEditViewModel
import kotlinx.android.synthetic.main.activity_my_question.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyQuestionActivity : BaseActivity<ActivityMyQuestionBinding, MyQuestionViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_my_question // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: MyQuestionViewModel by viewModel()

    var check = false
    var content = ""
    var questionId = 0

    override fun initStartView() {
        questionId = intent.getIntExtra("questionId", 0)
        content = intent.getStringExtra("content")!!
    }

    override fun initDataBinding() {
        contents.text = content
    }

    override fun initAfterBinding() {

        more_btn.setOnClickListener {
            val myQuestionEditBottomSheet: MyQuestionEditBottomSheet =
                MyQuestionEditBottomSheet() {
                    when (it) {
                        // 수정 하기
                        0 -> {
                            var intent = Intent(this, NotArriveEditActivity::class.java)
                            intent.putExtra("content", content)
                            intent.putExtra("questionId", questionId)
                            startActivity(intent)
                            finish()
                        }
                        // 삭제 하기
                        1 -> {
                        }
                    }
                }
            myQuestionEditBottomSheet.show(supportFragmentManager, myQuestionEditBottomSheet.tag)
        }

        back_btn.setOnClickListener {
            finish()
        }
    }
}