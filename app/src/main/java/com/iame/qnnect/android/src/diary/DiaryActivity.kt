package com.iame.qnnect.android.src.diary

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityDiaryBinding
import com.iame.qnnect.android.databinding.ActivityLoginBinding
import com.iame.qnnect.android.src.allow.AllowActivity
import com.iame.qnnect.android.src.answer.AnswerActivity
import com.iame.qnnect.android.src.diary.model.answer_item
import com.iame.qnnect.android.src.login.model.PostLoginRequest
import com.iame.qnnect.android.src.main.MainActivity
import com.iame.qnnect.android.src.main.home.GroupAdapter
import com.iame.qnnect.android.src.main.home.model.group_item
import com.iame.qnnect.android.viewmodel.DiaryViewModel
import com.iame.qnnect.android.viewmodel.LoginViewModel
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.android.synthetic.main.activity_diary.*
import kotlinx.android.synthetic.main.activity_diary.back_btn
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiaryActivity : BaseActivity<ActivityDiaryBinding, DiaryViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_diary // get() : 커스텀 접근자, 코틀린 문법

    var liked = false
    var writer = false
    var scraped = false
    var cafeQuestionId = 0

    override val viewModel: DiaryViewModel by viewModel()

    private val answerAdapter: AnswerAdapter by inject()

    override fun initStartView() {
        cafeQuestionId = intent.getIntExtra("cafeQuestionId", 0)
        Log.d("diary_response", cafeQuestionId.toString())

        answer_recycler.run {
            adapter = answerAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.getQuestion(cafeQuestionId)
        showLoadingDialog(this)
        answerAdapter.clear()

        viewModel.questionResponse.observe(this, Observer {
            var main = it.questionMainResponse
            liked = main.liked
            writer = main.writer
            scraped = main.scraped

            create_date.text = main.createdAt
            dday_txt.text = "D-"+main.daysLeft
            who_question.text = main.questioner+"의 질문"
            question_txt.text = main.question

            it.comments.forEach { item ->
                answerAdapter.addItem(item)
            }

            answerAdapter.notifyDataSetChanged()
            dismissLoadingDialog()
        })

        if(writer){
            edit_btn.visibility = View.VISIBLE
            delete_btn.visibility = View.VISIBLE
        }
        if(scraped){
            bookmark_btn.setImageResource(R.mipmap.ic_bookmark_select_foreground)
        }
        if(liked){
            like_btn.setImageResource(R.mipmap.ic_like_select_btn_foreground)
        }
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
        back_btn.setOnClickListener {
            finish()
        }

        answer_btn.setOnClickListener {
            var intent = Intent(this, AnswerActivity::class.java)
            startActivity(intent)
        }

        bookmark_btn.setOnClickListener {
            if(scraped){
                bookmark_btn.setImageResource(R.mipmap.ic_bookmark_bottom_foreground)
                viewModel.deleteScrap(cafeQuestionId)
                showLoadingDialog(this)

                viewModel.scrapResponse.observe(this, Observer {
                    dismissLoadingDialog()
                    scraped = false
                })

            }
            else{
                bookmark_btn.setImageResource(R.mipmap.ic_bookmark_select_foreground)
                viewModel.postScrap(cafeQuestionId)
                showLoadingDialog(this)

                viewModel.scrapResponse.observe(this, Observer {
                    dismissLoadingDialog()
                    scraped = true
                })
            }
        }

        like_btn.setOnClickListener {
            if(liked){
                like_btn.setImageResource(R.mipmap.ic_like_btn_foreground)
//                viewModel.deleteScrap(cafeQuestionId)
//                showLoadingDialog(this)
//
//                viewModel.scrapResponse.observe(this, Observer {
//                    dismissLoadingDialog()
//                    liked = false
//                })

            }
            else{
                bookmark_btn.setImageResource(R.mipmap.ic_like_select_btn_foreground)
//                viewModel.postScrap(cafeQuestionId)
//                showLoadingDialog(this)
//
//                viewModel.scrapResponse.observe(this, Observer {
//                    dismissLoadingDialog()
//                    liked = true
//                })
            }
        }
    }
}