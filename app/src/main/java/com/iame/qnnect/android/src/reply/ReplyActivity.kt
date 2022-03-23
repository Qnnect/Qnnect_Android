package com.iame.qnnect.android.src.reply

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ScrollView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.iame.qnnect.android.BuildConfig
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityReplyBinding
import com.iame.qnnect.android.src.answer.EditAnswerActivity
import com.iame.qnnect.android.src.declare.DeclareBottomSheet
import com.iame.qnnect.android.viewmodel.ReplyViewModel
import kotlinx.android.synthetic.main.activity_reply.*
import kotlinx.android.synthetic.main.activity_reply.image_recycler
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.iame.qnnect.android.src.reply.ReplyAdapter.OnItemClickEventListener
import com.iame.qnnect.android.src.reply.model.Replies
import com.iame.qnnect.android.src.reply.reply_more.ReplyMoreBottomSheet
import kotlinx.android.synthetic.main.activity_answer.*
import kotlinx.android.synthetic.main.activity_reply.back_btn
import kotlinx.android.synthetic.main.activity_reply.my_profile_img
import kotlinx.android.synthetic.main.activity_reply.my_profile_name
import kotlinx.coroutines.*


class ReplyActivity : BaseActivity<ActivityReplyBinding, ReplyViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_reply // get() : 커스텀 접근자, 코틀린 문법

    var commentId = 0
    var check = false

    var cafeQuestionId = 0
    var date = ""
    var dday = ""
    var questioner = ""
    var question = ""
    var content = ""

    override val viewModel: ReplyViewModel by viewModel()

    private val replyAdapter: ReplyAdapter by inject()
    private val imageAdapter: ImageAdapter by inject()

    var reply_check = false

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
        cafeQuestionId = intent.getIntExtra("cafeQuestionId", 0)
        date = intent.getStringExtra("date")!!
        dday = intent.getStringExtra("dday")!!
        questioner = intent.getStringExtra("questioner")!!+"의 질문"
        question = intent.getStringExtra("question")!!
    }

    override fun onResume() {
        super.onResume()
        replyAdapter.clear()
        imageAdapter.clear()
        viewModel.getReply(commentId)
        showLoadingDialog(this)
    }

    override fun initDataBinding() {
        viewModel.replyResponse.observe(this, Observer {
            Glide.with(this)
                .load(it.writerInfo.profileImage)
                .transform(CenterCrop(), RoundedCorners(200))
                .into(my_profile_img)
            my_profile_name.text = it.writerInfo.nickName
            answer_txt.text = it.content
            content = it.content

            date_txt.text = it.createdAt

            if(it.writer){
                more_btn.visibility = View.VISIBLE
            }
            else{
                more_btn.visibility = View.GONE
            }

            it.replies.forEach { item ->
                Log.d("reply_response_count", item.toString())
                replyAdapter.addItem(item)
            }

            imageAdapter.addItem(it.imageUrl1.toString())
            imageAdapter.addItem(it.imageUrl2.toString())
            imageAdapter.addItem(it.imageUrl3.toString())
            imageAdapter.addItem(it.imageUrl4.toString())
            imageAdapter.addItem(it.imageUrl5.toString())

            if(imageAdapter.itemCount == 1){
                img_one.visibility = View.VISIBLE
                Glide.with(this)
                    .load(imageAdapter.getItem(0))
                    .transform(CenterCrop(), RoundedCorners(50))
                    .into(img_one)
                image_recycler.visibility = View.GONE
            }
            else if(imageAdapter.itemCount == 0){
                image_recycler.visibility = View.GONE
                img_one.visibility = View.GONE
            }
            else{
                image_recycler.visibility = View.VISIBLE
                img_one.visibility = View.GONE
            }

            imageAdapter.notifyDataSetChanged()
            replyAdapter.notifyDataSetChanged()
            dismissLoadingDialog()


            if(check){
                // coroutine 써보려고 -> coroutine 안쓰면 제대로 안넘어감
                CoroutineScope(Dispatchers.Main).launch {
                    delay(100)
                    val smoothScroller: RecyclerView.SmoothScroller by lazy {
                        object : LinearSmoothScroller(this@ReplyActivity) {
                            override fun getVerticalSnapPreference() = SNAP_TO_START
                        }
                    }
                    smoothScroller.targetPosition = replyAdapter.itemCount-1
                    reply_recycler.layoutManager?.startSmoothScroll(smoothScroller)
                    group_scroll.fullScroll(ScrollView.FOCUS_DOWN)
                }
                check = false
            }
        })

        viewModel.po_replyResponse.observe(this, Observer {
            reply_edit.text = null
            reply_check = false
            dismissLoadingDialog()
            replyAdapter.clear()
            imageAdapter.clear()
            viewModel.getReply(commentId)
            showLoadingDialog(this)
        })
        viewModel.answerResponse.observe(this, Observer {
            dismissLoadingDialog()
            finish()
        })

        viewModel.declareResponse.observe(this, Observer {
            dismissLoadingDialog()
            onResume()
        })

        viewModel.erdeclareResponse.observe(this, Observer {
            Toast.makeText(this, "본인은 신고할 수 없습니다.", Toast.LENGTH_SHORT).show()
        })
    }

    override fun initAfterBinding() {
        back_btn.setOnClickListener {
            finish()
        }

        more_btn.setOnClickListener {
            val answerMoreBottomSheet: AnswerBottomSheet = AnswerBottomSheet {
                when (it) {
                    // 답변 수정
                    0 -> {
                        var intent = Intent(this, EditAnswerActivity::class.java)
                        intent.putExtra("content", content)
                        intent.putExtra("commentId", commentId)
                        intent.putExtra("cafeQuestionId", cafeQuestionId)
                        intent.putExtra("date", date)
                        intent.putExtra("dday", dday)
                        intent.putExtra("questioner", questioner)
                        intent.putExtra("question", question)
                        startActivity(intent)

                    }
                    // 답변 삭제
                    1 -> {
                        viewModel.deleteAnswer(commentId)
                        showLoadingDialog(this)
                    }
                }
            }
            answerMoreBottomSheet.show(supportFragmentManager, answerMoreBottomSheet.tag)
        }

        reply_edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                var len = reply_edit.text.toString()
                reply_check = len.length > 0 && len.length < 50
            }
        })

        send_btn.setOnClickListener {
            if(reply_check){
                Log.d("edittext_reply", reply_edit.text.toString())
                viewModel.postReply(commentId, reply_edit.text.toString())
                check = true
                showLoadingDialog(this)
            }
        }

        // more
        replyAdapter.setOnItemClickListener(object : OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                val item: Replies = replyAdapter.getItem(a_position)

                val replyMoreBottomSheet: ReplyMoreBottomSheet = ReplyMoreBottomSheet(commentId, item.replyId, item.content) {
                    when (it) {
                        0-> {
                            replyAdapter.clear()
                            imageAdapter.clear()
                            viewModel.getReply(commentId)
                            showLoadingDialog(this@ReplyActivity)
                        }
                    }
                }
                replyMoreBottomSheet.show(supportFragmentManager, replyMoreBottomSheet.tag)
            }
        })

        // declare
        replyAdapter.setOnItemClickListener2(object : OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                val item: Replies = replyAdapter.getItem(a_position)

                val declareBottomSheet: DeclareBottomSheet = DeclareBottomSheet() {
                    when (it) {
                        // 신고하기
                        0 -> {
                            viewModel.declare(item.writerInfo.reportId)
                            val email = Intent(Intent.ACTION_SEND)
                            email.type = "plain/text"
                            val address = arrayOf("qnnect.app@gmail.com")
                            email.putExtra(Intent.EXTRA_EMAIL, address)
                            email.putExtra(Intent.EXTRA_SUBJECT, "큐넥트 글및 유저 신고")
                            email.putExtra(Intent.EXTRA_TEXT, "내용 미리보기 (미리적을 수 있음)")
                            email.putExtra(
                                Intent.EXTRA_TEXT,
                                java.lang.String.format(
                                    "App Version : %s\nAndroid(SDK) : %d(%s)\n 유저 닉네임 : %s\n내용 : ",
                                    BuildConfig.VERSION_NAME,
                                    Build.VERSION.SDK_INT,
                                    Build.VERSION.RELEASE,
                                    item.writerInfo.nickName
                                )
                            )
                            startActivity(email)
                            showLoadingDialog(this@ReplyActivity)
                        }
                        // 차단하기
                        1 ->{
                            viewModel.declare(item.writerInfo.reportId)
                            showLoadingDialog(this@ReplyActivity)
                        }
                    }
                }
                declareBottomSheet.show(supportFragmentManager, declareBottomSheet.tag)
            }
        })
    }
}