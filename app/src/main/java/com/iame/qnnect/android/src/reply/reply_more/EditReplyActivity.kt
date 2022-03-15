package com.iame.qnnect.android.src.reply.reply_more

import android.content.Intent
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.Observer
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityEditReplyBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.iame.qnnect.android.viewmodel.EditReplyViewModel
import kotlinx.android.synthetic.main.activity_edit_reply.*

class EditReplyActivity : BaseActivity<ActivityEditReplyBinding, EditReplyViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_edit_reply // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: EditReplyViewModel by viewModel()

    var check = false
    var commentId = 0
    var replyId = 0
    var contents = ""

    override fun initStartView() {
        commentId = intent.getIntExtra("commentId", 0)
        replyId = intent.getIntExtra("replyId", 0)
        contents = intent.getStringExtra("contents")!!

        contents_edit.setText(contents)
        Log.d("edittext_reply", contents)
    }

    override fun initDataBinding() {
        viewModel.editreplyResponse.observe(this, Observer {
            dismissLoadingDialog()
            finish()
        })
    }

    override fun initAfterBinding() {
        back_btn.setOnClickListener {
            finish()
        }

        contents_edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                var len = contents_edit.text.toString()
                check = len.length > 0 && len.length < 50 && contents_edit.text.toString() != contents
                if(check){
                    save_btn.setTextColor(Color.parseColor("#FD774C"))
                }
                else{
                    save_btn.setTextColor(Color.parseColor("#BDBDBD"))
                }
            }
        })

        save_btn.setOnClickListener {
            if(check){
                viewModel.editReply(commentId, replyId, contents_edit.text.toString())
                showLoadingDialog(this)
            }
        }
    }
}