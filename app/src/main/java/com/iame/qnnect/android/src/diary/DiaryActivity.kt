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
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DiaryActivity : BaseActivity<ActivityDiaryBinding, DiaryViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_diary // get() : 커스텀 접근자, 코틀린 문법

    lateinit var answerAdapter: AnswerAdapter
    lateinit var answerRecyclerView: RecyclerView
    var answer_list = ArrayList<answer_item>()
    var image_list = ArrayList<String>()

    var liked = false
    var writer = false
    var scraped = false
    var cafeQuestionId = 0

    override val viewModel: DiaryViewModel by viewModel()

    override fun initStartView() {
        liked = intent.getBooleanExtra("liked", false)
        writer = intent.getBooleanExtra("writer", false)
        scraped = intent.getBooleanExtra("scraped", false)
        cafeQuestionId = intent.getIntExtra("cafeQuestionId", 0)
        Log.d("diary_response", cafeQuestionId.toString())
        Log.d("diary_response", cafeQuestionId.toString())

        if(writer){
            edit_btn.visibility = View.VISIBLE
            delete_btn.visibility = View.VISIBLE
        }
        if(scraped){
            bookmark_btn.setImageResource(R.mipmap.ic_bookmark_select_foreground)
        }

//        question_txt
//        who_question
//        create_date
//        dday_txt
//        bookmark_btn
    }

    override fun onResume() {
        super.onResume()
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
        image_list.add("test")
        var item1 = answer_item("1", "아아메", "95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내", null)
        answer_list.add(item1)
        var item2 = answer_item("1", "아아메", "95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내", image_list)
        answer_list.add(item2)
        var item3 = answer_item("1", "아아메", "95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내", image_list)
        answer_list.add(item3)
        var item4 = answer_item("1", "아아메", "95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내95자이내", null)
        answer_list.add(item4)
        answer_list.add(item1)
        answer_list.add(item4)
        answer_list.add(item2)
        answer_list.add(item4)

        answer_recycler.run {
            adapter = AnswerAdapter(answer_list, context)
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }

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
    }
}