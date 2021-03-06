package com.iame.qnnect.android.src.main.home

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.iame.qnnect.android.base.BaseFragment
import com.iame.qnnect.android.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.iame.qnnect.android.src.main.MainActivity
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.HomeFragment_case
import com.iame.qnnect.android.databinding.FragmentHomeBinding
import com.iame.qnnect.android.src.alarm.AlarmActivity
import com.iame.qnnect.android.src.main.home.home_bottom.AddGroupBottomSheet
import com.iame.qnnect.android.src.main.home.home_bottom.InviteGroupBottomSheet
import com.iame.qnnect.android.src.profile.ProfileActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.dots_indicator
import kotlinx.android.synthetic.main.fragment_home.point_txt
import kotlinx.android.synthetic.main.fragment_home.question_viewPager2
import kotlinx.android.synthetic.main.fragment_home.user_diary_name
import kotlinx.android.synthetic.main.fragment_home.user_profile_img
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_home // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: HomeViewModel by viewModel()

    var fragment = this

    private val questionRecyclerViewAdapter: QuestionRecyclerViewAdapter by inject()
    private val groupAdapter: GroupAdapter by inject()

    var home_case = HomeFragment_case()

    val fragment_s: Fragment = this
    private var activity: MainActivity? = null

    override fun initStartView() {
//        var link = baseToken.getLink(requireContext())

        var alarm = baseToken.getAlarm(requireContext())
        if(alarm){
            var intent = Intent(context, AlarmActivity::class.java)
            startActivity(intent)
        }

        // dynamick link
//        if(link){
//            baseToken.setLink(requireContext(), false)
//            var intent = Intent(context, AlarmActivity::class.java)
//            startActivity(intent)
//        }

        // group recycler
        group_recycler.run {
            adapter = groupAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            setHasFixedSize(true)
        }

        // question viewpager
        question_viewPager2.run {
            adapter = questionRecyclerViewAdapter
        }
        //인디케이터 타입1
        val dotsIndicator = dots_indicator
        dotsIndicator.setViewPager2(question_viewPager2)
    }

    override fun initDataBinding() {
        var cafeCode = baseToken.getCafeCode(requireContext())
        if(cafeCode != null){
            val invitgroupBottomSheet: InviteGroupBottomSheet = InviteGroupBottomSheet(cafeCode) {
                when (it) {
                    // 그룹페이지로 이동
                    0 -> {
                        baseToken.setCafeCode(requireContext(), null)
                        activity = fragment_s.activity as MainActivity?
                        //change_for_adapter는 mainactivity에 구현
                        activity?.fragmentChange_for_adapter()
                        dismissLoadingDialog()
                    }
                }
            }
            invitgroupBottomSheet.show(requireActivity().supportFragmentManager, invitgroupBottomSheet.tag)
            baseToken.setCafeCode(requireContext(), null)
        }

        viewModel.homeResponse.observe(this, Observer {
//            dismissLoadingDialog()
            var image = it.user.profileImage

            // Profile Url
            Glide.with(this)
                .load(image)
                .transform(CenterCrop(), RoundedCorners(200))
                .apply(RequestOptions().placeholder(R.mipmap.profile_default_foreground)
                    .error(R.mipmap.profile_default_foreground))
                .into(user_profile_img)

            if(it.user.nickName == null){
                var intent = Intent(context, ProfileActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP //액티비티 스택제거
                startActivity(intent)
            }

            // User Name
            user_diary_name.text = it.user.nickName+"님의 다이어리"
            // User Point
            point_txt.text = it.user.point.toString()+"P"

            // 확인안 한 alarm이 있는지
            if(it.hasUnreadNotification){
                find_alarm.visibility = View.VISIBLE
            }
            else{
                find_alarm.visibility = View.GONE
            }

            if(it.questionList.size == 1){
                dots_indicator.visibility = View.INVISIBLE
            }

            if(it.questionList.size == 0){
                empty_question.visibility = View.VISIBLE
                dots_indicator.visibility = View.GONE
                question_viewPager2.visibility = View.GONE
            }
            else{
                empty_question.visibility = View.GONE
                dots_indicator.visibility = View.VISIBLE
                question_viewPager2.visibility = View.VISIBLE

                it.questionList.forEach { item ->
                    questionRecyclerViewAdapter.addItem(item)
                }
            }

            if(it.groupList.size == 0){
                empty_main.visibility = View.VISIBLE
                not_empty_main.visibility = View.GONE
            }
            else{
                not_empty_main.visibility = View.VISIBLE
                empty_main.visibility = View.GONE
                it.groupList.forEach { item ->
                    groupAdapter.addItem(item)
                }
            }

            questionRecyclerViewAdapter.notifyDataSetChanged()
            groupAdapter.notifyDataSetChanged()
            dismissLoadingDialog()
        })
    }

    override fun initAfterBinding() {
        add_group_btn.setOnClickListener {
            val maingroupBottomSheet: AddGroupBottomSheet = AddGroupBottomSheet {
                when (it) {
                    // 그룹페이지로 이동
                    0 -> {
                        activity = fragment_s.activity as MainActivity?
                        //change_for_adapter는 mainactivity에 구현
                        activity?.fragmentChange_for_adapter()
                    }
                }
            }
            maingroupBottomSheet.show(requireActivity().supportFragmentManager, maingroupBottomSheet.tag)
        }

        invite_group_btn.setOnClickListener {
            val invitgroupBottomSheet: InviteGroupBottomSheet = InviteGroupBottomSheet(null) {
                when (it) {
                    // 그룹페이지로 이동
                    0 -> {
                        // 코드를 좀 더 변환해보기
                        activity = fragment_s.activity as MainActivity?
                        //change_for_adapter는 mainactivity에 구현
                        activity?.fragmentChange_for_adapter()
                    }
                }
            }
            invitgroupBottomSheet.show(requireActivity().supportFragmentManager, invitgroupBottomSheet.tag)
        }

        empty_add_group_btn.setOnClickListener {
            val maingroupBottomSheet: AddGroupBottomSheet = AddGroupBottomSheet {
                when (it) {
                    // 그룹페이지로 이동
                    0 -> {
                        activity = fragment_s.activity as MainActivity?
                        //change_for_adapter는 mainactivity에 구현
                        activity?.fragmentChange_for_adapter()
                    }
                }
            }
            maingroupBottomSheet.show(requireActivity().supportFragmentManager, maingroupBottomSheet.tag)
        }

        empty_invite_group_btn.setOnClickListener {
            val invitgroupBottomSheet: InviteGroupBottomSheet = InviteGroupBottomSheet(null) {
                when (it) {
                    // 그룹페이지로 이동
                    0 -> {
                        activity = fragment_s.activity as MainActivity?
                        //change_for_adapter는 mainactivity에 구현
                        activity?.fragmentChange_for_adapter()
                    }
                }
            }
            invitgroupBottomSheet.show(requireActivity().supportFragmentManager, invitgroupBottomSheet.tag)
        }

        alarm_btn.setOnClickListener {
            var intent = Intent(context, AlarmActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onResume() {
        super.onResume()

        groupAdapter.clear()
        questionRecyclerViewAdapter.clear()

        viewModel.getHome()
        showLoadingDialog(requireContext())
    }
}