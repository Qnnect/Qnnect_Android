package com.iame.qnnect.android.src.edit_question

import android.content.Intent
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.Observer
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityEditQuestionBinding
import com.iame.qnnect.android.src.question.QuestionCompleteDialog
import com.iame.qnnect.android.src.question.QuestionListActivity
import com.iame.qnnect.android.viewmodel.EditQuestionViewModel
import kotlinx.android.synthetic.main.activity_edit_question.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class EditQuestionActivity : BaseActivity<ActivityEditQuestionBinding, EditQuestionViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_edit_question // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: EditQuestionViewModel by viewModel()

    var check = false

    var cafeQuestionId = 0
    var content = ""

    override fun initStartView() {
        cafeQuestionId = intent.getIntExtra("cafeQuestionId", 0)
        content = intent.getStringExtra("content")!!
    }

    override fun initDataBinding() {
        contents.setText(content)
        viewModel.editquestionResponse.observe(this, Observer {
            dismissLoadingDialog()
            finish()
        })
    }

    override fun initAfterBinding() {
        contents.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int, ) {
                var len = contents.text.toString()
                if(len.length > 0 && contents.text.toString() != content){
                    save_btn.setTextColor(Color.parseColor("#FD774C"))
                    check = true
                }
                else{
                    save_btn.setTextColor(Color.parseColor("#BDBDBD"))
                    check = false
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })


        save_btn.setOnClickListener {
            if(check){
                viewModel.patchEditQuesiton(cafeQuestionId, contents.text.toString())
                showLoadingDialog(this)
            }
        }

        back_btn.setOnClickListener {
            finish()
        }
    }
}