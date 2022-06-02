package com.iame.qnnect.android.src.diary

import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.iame.qnnect.android.BuildConfig
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityDiaryBinding
import com.iame.qnnect.android.src.answer.AnswerActivity
import com.iame.qnnect.android.src.declare.DeclareBottomSheet
import com.iame.qnnect.android.src.edit_question.EditQuestionActivity
import com.iame.qnnect.android.src.reply.ReplyActivity
import com.iame.qnnect.android.src.reply.reply_more.DeleteReplyDialog
import com.iame.qnnect.android.util.setOnSingleClickListener
import com.iame.qnnect.android.viewmodel.DiaryViewModel
import kotlinx.android.synthetic.main.activity_diary.*
import kotlinx.android.synthetic.main.activity_diary.back_btn
import kotlinx.android.synthetic.main.activity_edit_profile.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.String

class DiaryActivity : BaseActivity<ActivityDiaryBinding, DiaryViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_diary // get() : 커스텀 접근자, 코틀린 문법

    var liked = false
    var writer = false
    var scraped = false
    var cafeQuestionId = 0

    var date = ""
    var dday = ""
    var dday_num = 0
    var questioner = ""
    var question = ""


    override val viewModel: DiaryViewModel by viewModel()

    private val answerAdapter: AnswerAdapter by inject()

    override fun initStartView() {
        cafeQuestionId = intent.getIntExtra("cafeQuestionId", 0)
        Log.d("diary_response", cafeQuestionId.toString())

        binding.answerRecycler.run {
            adapter = answerAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
    }

    override fun onResume() {
        super.onResume()

        answerAdapter.clear()
        viewModel.getQuestion(cafeQuestionId)
        showLoadingDialog(this)
    }

    override fun initDataBinding() {
        viewModel.questionResponse.observe(this, Observer {
            var main = it.questionMainResponse
            liked = it.liked
            writer = main.writer
            scraped = it.scraped

            if(writer){
                binding.editBtn.visibility = View.VISIBLE
                binding.deleteBtn.visibility = View.VISIBLE
            }
            if(scraped){
                binding.bookmarkBtn.setImageResource(R.mipmap.ic_bookmark_select_foreground)
            }
            if(liked){
                binding.likeBtn.setImageResource(R.mipmap.ic_like_select_btn_foreground)
            }

            binding.createDate.text = main.createdAt
            dday_num = main.daysLeft
            binding.ddayTxt.text = "D-"+main.daysLeft
            binding.whoQuestion.text = main.questioner+"의 질문"
            binding.questionTxt.text = main.question

            date = main.createdAt
            dday = "D-"+main.daysLeft.toString()
            questioner = main.questioner
            question = main.question

            // item add
            if(it.currentUserComment != null){
                binding.answerMain.visibility = View.GONE
                binding.myProfileName.visibility = View.GONE
                binding.myProfileImg.visibility = View.GONE
                answerAdapter.addItem(it.currentUserComment)
            }
            else{
                binding.answerMain.visibility = View.VISIBLE
                binding.myProfileName.visibility = View.VISIBLE
                binding.myProfileImg.visibility = View.VISIBLE
            }

            it.comments.forEach { item ->
                answerAdapter.addItem(item)
            }

            answerAdapter.notifyDataSetChanged()
            dismissLoadingDialog()
        })

        viewModel.userResponse.observe(this, Observer {
            var image = it.profileImage

            // Profile Url
            Glide.with(this)
                .load(image)
                .transform(CenterCrop(), RoundedCorners(200))
                .into(binding.myProfileImg)
            // User Name
            binding.myProfileName.text = it.nickName
        })

        viewModel.likeResponse.observe(this, Observer {
            if(liked){
                liked = false
                binding.likeBtn.setImageResource(R.mipmap.ic_like_btn_foreground)
            }
            else{
                liked = true
                binding.likeBtn.setImageResource(R.mipmap.ic_like_select_btn_foreground)
            }
            dismissLoadingDialog()
        })

        viewModel.scrapResponse.observe(this, Observer {
            if(scraped){
                scraped = false
                binding.bookmarkBtn.setImageResource(R.mipmap.ic_bookmark_bottom_foreground)
            }
            else{
                scraped = true
                binding.bookmarkBtn.setImageResource(R.mipmap.ic_bookmark_select_foreground)
            }
            dismissLoadingDialog()
        })

        viewModel.deQuestionResponse.observe(this, Observer {
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

        viewModel.questionerrorResponse.observe(this, Observer {
            dismissLoadingDialog()
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            finish()
        })
    }

    override fun initAfterBinding() {
        viewModel.getUser()

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.answerMain.setOnClickListener {
            var intent = Intent(this, AnswerActivity::class.java)
            intent.putExtra("cafeQuestionId", cafeQuestionId)
            intent.putExtra("date", date)
            intent.putExtra("dday", dday)
            intent.putExtra("questioner", questioner)
            intent.putExtra("question", question)
            intent.putExtra("dday_num", dday_num)
            startActivity(intent)
        }

        // 중복 클릭 방지
        binding.bookmarkBtn.setOnSingleClickListener {
            if(scraped){
                viewModel.deleteScrap(cafeQuestionId)
                showLoadingDialog(this)
            }
            else{
                viewModel.postScrap(cafeQuestionId)
                showLoadingDialog(this)
            }
        }

        answerAdapter.setOnItemClickListener(object : AnswerAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                var item = answerAdapter.getItem(a_position)
                var intent = Intent(this@DiaryActivity, ReplyActivity::class.java)
                intent.putExtra("commentId", item.commentId)
                intent.putExtra("cafeQuestionId", cafeQuestionId)
                intent.putExtra("date", date)
                intent.putExtra("dday", dday)
                intent.putExtra("questioner", questioner)
                intent.putExtra("question", question)
                startActivity(intent)
            }
        })

        answerAdapter.setOnItemClickListener2(object : AnswerAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                var item = answerAdapter.getItem(a_position)

                val declareBottomSheet: DeclareBottomSheet = DeclareBottomSheet() {
                    when (it) {
                        // 신고하기
                        0 -> {
                            viewModel.declare(item.profileResponse.reportId)
                            val email = Intent(Intent.ACTION_SEND)
                            email.type = "plain/text"
                            val address = arrayOf("qnnect.app@gmail.com")
                            email.putExtra(Intent.EXTRA_EMAIL, address)
                            email.putExtra(Intent.EXTRA_SUBJECT, "큐넥트 글및 유저 신고")
                            email.putExtra(Intent.EXTRA_TEXT, "내용 미리보기 (미리적을 수 있음)")
                            email.putExtra(
                                Intent.EXTRA_TEXT,
                                String.format(
                                    "App Version : %s\nAndroid(SDK) : %d(%s)\n 유저 닉네임 : %s\n내용 : ",
                                    BuildConfig.VERSION_NAME,
                                    Build.VERSION.SDK_INT,
                                    Build.VERSION.RELEASE,
                                    item.profileResponse.nickName
                                )
                            )
                            startActivity(email)
                            showLoadingDialog(this@DiaryActivity)
                        }
                        // 차단하기
                        1 ->{
                            viewModel.declare(item.profileResponse.reportId)
                            showLoadingDialog(this@DiaryActivity)
                        }
                    }
                }
                declareBottomSheet.show(supportFragmentManager, declareBottomSheet.tag)
            }
        })

        // 중복 클릭 방지
        binding.likeBtn.setOnSingleClickListener {
            if(liked){
                viewModel.postLike(cafeQuestionId, !liked)
                showLoadingDialog(this)
            }
            else{
                viewModel.postLike(cafeQuestionId, !liked)
                showLoadingDialog(this)
            }
        }

        binding.editBtn.setOnClickListener {
            var intent = Intent(this, EditQuestionActivity::class.java)
            intent.putExtra("cafeQuestionId", cafeQuestionId)
            intent.putExtra("content", question)
            startActivity(intent)
        }

        binding.deleteBtn.setOnClickListener {
            val deleteDialog: DeleteReplyDialog = DeleteReplyDialog {
                when (it) {
                    // 삭제하기
                    1 -> {
                        viewModel.deleteQuestion(cafeQuestionId)
                        showLoadingDialog(this)
                    }
                }
            }
            deleteDialog.show(supportFragmentManager, deleteDialog.tag)
        }
    }
}