package com.iame.qnnect.android.src.group

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.iame.qnnect.android.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.HomeFragment_case
import com.iame.qnnect.android.databinding.FragmentGroupBinding
import com.iame.qnnect.android.src.allow.AllowActivity
import com.iame.qnnect.android.src.answer.AnswerActivity
import com.iame.qnnect.android.src.drink.DrinkActivity
import com.iame.qnnect.android.src.group.group_bottom.EditDrinkBottomSheet
import com.iame.qnnect.android.src.group.group_bottom.EditGroupBottomSheet
import com.iame.qnnect.android.src.group.group_setting.GroupSettingBottomSheet
import com.iame.qnnect.android.src.group.member.GroupMemberAdapter
import com.iame.qnnect.android.src.group.model.*
import com.iame.qnnect.android.src.group.question.GroupQuestionViewPagerAdapter
import com.iame.qnnect.android.src.invite.InviteActivity
import com.iame.qnnect.android.src.main.MainActivity
import com.iame.qnnect.android.src.main.home.HomeFragment
import com.iame.qnnect.android.src.main.home.home_bottom.AddDrinkBottomSheet
import com.iame.qnnect.android.src.main.home.home_bottom.AddGroupBottomSheet
import com.iame.qnnect.android.src.question.QuestionActivity
import com.iame.qnnect.android.viewmodel.GroupViewModel
import kotlinx.android.synthetic.main.activity_main_two.*
import kotlinx.android.synthetic.main.fragment_group.*
import kotlinx.android.synthetic.main.fragment_home.dots_indicator
import kotlinx.android.synthetic.main.fragment_home.question_viewPager2


class GroupFragment : BaseFragment<FragmentGroupBinding, GroupViewModel>(R.layout.fragment_group) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_group // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: GroupViewModel by viewModel()

    var question_list = ArrayList<CafeQuestionResponse>()

    var member_list = ArrayList<CafeUserResponse>()

//    data class CafeQuestionResponse(
//        val createdAt: String,
//        val daysLeft: Int,
//        val question: String,
//        val questioner: String
//    )

    var home_case = HomeFragment_case()
    val fragment_s: Fragment = this
    private var activity: MainActivity? = null

    override fun initStartView() {
    }

    override fun initDataBinding() {
        var cafeId = GetGroupRequest(12)
        viewModel.getGroup(cafeId)

        viewModel.groupResponse.observe(this, Observer {
            for(i in 0..it.cafeUserResponseList.size-1){
                var item = it.cafeUserResponseList.get(i)
                member_list.add(item)
            }

            for(i in 0..it.cafeQuestionResponseList.size-1){
                var item = it.cafeQuestionResponseList.get(i)
                question_list.add(item)
            }
        })

        member_recycler.run {
            adapter = GroupMemberAdapter(member_list)
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            setHasFixedSize(true)
        }

        question_viewPager2.run {
            adapter = GroupQuestionViewPagerAdapter(question_list, context)
        }

        //인디케이터 타입1
        val dotsIndicator = dots_indicator
        dotsIndicator.setViewPager2(question_viewPager2)

//        group_recycler.run {
//            adapter = SubGroupAdapter(group_list)
//            layoutManager = LinearLayoutManager(context).apply {
//                orientation = LinearLayoutManager.HORIZONTAL
//            }
//            setHasFixedSize(true)
//        }
    }

    override fun initAfterBinding() {
        drink_img.setOnClickListener {
            var intent = Intent(context, DrinkActivity::class.java)
            startActivity(intent)
        }

        back_btn.setOnClickListener {
            home_case.setHome(requireContext(), 0, "")

            var fragment: Fragment = HomeFragment()
            var bundle: Bundle = Bundle()
            fragment.arguments=bundle

            activity = fragment_s.activity as MainActivity?
            //change_for_adapter는 mainactivity에 구현
            activity?.fragmentChange_for_adapter(fragment)
        }

        question_btn.setOnClickListener {
//            val notquestionDialog: NotQuestionDialog = NotQuestionDialog {
//                when (it) {
//                    // 음료추가
//                    1 -> {
//                        val addDrinkBottomSheet = AddDrinkBottomSheet()
//                        addDrinkBottomSheet.show(requireActivity().supportFragmentManager, addDrinkBottomSheet.tag)
//                    }
//                }
//            }
//            notquestionDialog.show(requireActivity().supportFragmentManager, notquestionDialog.tag)
            var intent = Intent(context, QuestionActivity::class.java)
            startActivity(intent)
        }

        more_btn.setOnClickListener {
            val groupSettingBottomSheet: GroupSettingBottomSheet = GroupSettingBottomSheet {
                when (it) {
                    // 초대하기
                    0 -> {
                        var intent = Intent(context, InviteActivity::class.java)
                        startActivity(intent)
                    }
                    // 카페 수정
                    1 -> {
                        val editgroupBottomSheet: EditGroupBottomSheet = EditGroupBottomSheet {
                            when (it) {
                                // 카페 수정
                                0 -> {
                                }
                            }
                        }
                        editgroupBottomSheet.show(requireActivity().supportFragmentManager, editgroupBottomSheet.tag)
                    }
                    // 음료 수정
                    2 -> {
                        val editDrinkBottomSheet = EditDrinkBottomSheet()
                        editDrinkBottomSheet.show(requireActivity().supportFragmentManager, editDrinkBottomSheet.tag)
                    }
                    // 카페 삭제
                    3 -> {
                        val deleteGroupDialog = DeleteGroupDialog()
                        deleteGroupDialog.show(requireActivity().supportFragmentManager, deleteGroupDialog.tag)

                    }
                }
            }

            groupSettingBottomSheet.show(requireActivity().supportFragmentManager, groupSettingBottomSheet.tag)
        }

//        val notanswerDialog = NotAnswerDialog()
//        notanswerDialog.show(requireActivity().supportFragmentManager, notanswerDialog.tag)


    }

}