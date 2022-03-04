package com.iame.qnnect.android.src.question

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityAnswerBinding
import com.iame.qnnect.android.databinding.ActivityDiaryBinding
import com.iame.qnnect.android.databinding.ActivityLoginBinding
import com.iame.qnnect.android.databinding.ActivityQuestionBinding
import com.iame.qnnect.android.src.allow.AllowActivity
import com.iame.qnnect.android.src.diary.AnswerAdapter
import com.iame.qnnect.android.src.diary.model.answer_item
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.main.MainActivity
import com.iame.qnnect.android.src.main.home.GroupAdapter
import com.iame.qnnect.android.src.main.home.model.group_item
import com.iame.qnnect.android.viewmodel.AnswerViewModel
import com.iame.qnnect.android.viewmodel.DiaryViewModel
import com.iame.qnnect.android.viewmodel.LoginViewModel
import com.iame.qnnect.android.viewmodel.QuestionViewModel
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.android.synthetic.main.activity_answer.*
import kotlinx.android.synthetic.main.activity_diary.*
import kotlinx.android.synthetic.main.activity_diary.back_btn
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class QuestionActivity : BaseActivity<ActivityQuestionBinding, QuestionViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_question // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: QuestionViewModel by viewModel()

    override fun initStartView() {
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
    }
}