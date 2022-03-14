package com.iame.qnnect.android.src.reply

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityDiaryBinding
import com.iame.qnnect.android.databinding.ActivityLoginBinding
import com.iame.qnnect.android.databinding.ActivityReplyBinding
import com.iame.qnnect.android.src.allow.AllowActivity
import com.iame.qnnect.android.src.answer.AnswerActivity
import com.iame.qnnect.android.src.diary.AnswerAdapter
import com.iame.qnnect.android.src.diary.model.answer_item
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.main.MainActivity
import com.iame.qnnect.android.src.main.home.GroupAdapter
import com.iame.qnnect.android.src.main.home.model.group_item
import com.iame.qnnect.android.viewmodel.DiaryViewModel
import com.iame.qnnect.android.viewmodel.LoginViewModel
import com.iame.qnnect.android.viewmodel.ReplyViewModel
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.android.synthetic.main.activity_diary.*
import kotlinx.android.synthetic.main.activity_diary.back_btn
import kotlinx.android.synthetic.main.activity_diary.my_profile_img
import kotlinx.android.synthetic.main.activity_diary.my_profile_name
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_reply.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReplyActivity : BaseActivity<ActivityReplyBinding, ReplyViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_reply // get() : 커스텀 접근자, 코틀린 문법

    var commentId = 0

    override val viewModel: ReplyViewModel by viewModel()

    private val replyAdapter: ReplyAdapter by inject()
    private val imageAdapter: ImageAdapter by inject()

    override fun initStartView() {
        reply_recycler.run {
            adapter = replyAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }

        image_recycler.run {
            adapter = imageAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            setHasFixedSize(true)
        }

        commentId = intent.getIntExtra("commentId", 0)
    }

    override fun onResume() {
        super.onResume()
        replyAdapter.clear()
        imageAdapter.clear()
        viewModel.getReply(commentId)
        showLoadingDialog(this)
    }

    override fun initDataBinding() {
        back_btn.setOnClickListener {
            finish()
        }

        viewModel.replyResponse.observe(this, Observer {

            Glide.with(this)
                .load(it.writer.profileImage)
                .transform(CenterCrop(), RoundedCorners(200))
                .into(my_profile_img)
            my_profile_name.text = it.writer.nickName
            answer_txt.text = it.content

            it.replies.forEach { item ->
                replyAdapter.addItem(item)
            }

            imageAdapter.addItem(it.imageUrl1.toString())
            imageAdapter.addItem(it.imageUrl2.toString())
            imageAdapter.addItem(it.imageUrl3.toString())
            imageAdapter.addItem(it.imageUrl4.toString())
            imageAdapter.addItem(it.imageUrl5.toString())

            replyAdapter.notifyDataSetChanged()
            imageAdapter.notifyDataSetChanged()
            dismissLoadingDialog()
        })
    }

    override fun initAfterBinding() {
    }
}