package com.iame.qnnect.android.src.group

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.iame.qnnect.android.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.HomeFragment_case
import com.iame.qnnect.android.databinding.FragmentGroupBinding
import com.iame.qnnect.android.src.group.group_bottom.EditDrinkBottomSheet
import com.iame.qnnect.android.src.group.group_bottom.EditGroupBottomSheet
import com.iame.qnnect.android.src.group.group_setting.GroupSettingBottomSheet
import com.iame.qnnect.android.src.group.member.GroupMemberAdapter
import com.iame.qnnect.android.src.group.model.group_drink_item
import com.iame.qnnect.android.src.group.model.group_question_item
import com.iame.qnnect.android.src.group.question.GroupQuestionViewPagerAdapter
import com.iame.qnnect.android.src.invite.InviteActivity
import com.iame.qnnect.android.src.main.MainActivity
import com.iame.qnnect.android.src.main.home.HomeFragment
import com.iame.qnnect.android.src.main.home.home_bottom.AddDrinkBottomSheet
import com.iame.qnnect.android.src.main.home.home_bottom.AddGroupBottomSheet
import com.iame.qnnect.android.viewmodel.GroupViewModel
import kotlinx.android.synthetic.main.fragment_group.*
import kotlinx.android.synthetic.main.fragment_home.dots_indicator
import kotlinx.android.synthetic.main.fragment_home.question_viewPager2


class GroupFragment : BaseFragment<FragmentGroupBinding, GroupViewModel>(R.layout.fragment_group) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_group // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: GroupViewModel by viewModel()

    var question_list = ArrayList<group_question_item>()

    var member_list = ArrayList<group_drink_item>()

    var home_case = HomeFragment_case()
    val fragment_s: Fragment = this
    private var activity: MainActivity? = null

    override fun initStartView() {

        // Member Recycler View
        var group_item1 = group_drink_item(R.mipmap.img_drink_basic_foreground, "두루두루")
        member_list.add(group_item1)
        var group_item2 = group_drink_item(R.mipmap.img_drink_basic_foreground, "제제로제제로")
        member_list.add(group_item2)
        var group_item3 = group_drink_item(R.mipmap.img_drink_basic_foreground, "조이조이")
        member_list.add(group_item3)
        var group_item4 = group_drink_item(R.mipmap.img_drink_basic_foreground, "슈테른슈테른")
        member_list.add(group_item4)
        var group_item5 = group_drink_item(R.mipmap.img_drink_basic_foreground, "모래모래")
        member_list.add(group_item5)

        member_recycler.run {
            adapter = GroupMemberAdapter(member_list)
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            setHasFixedSize(true)
        }

        // Question View Pager2
        var question_itme1 = group_question_item("2022.03.02","D-7", "아아메팀", "오늘은 뭘먹을까요??")
        question_list.add(question_itme1)
        var question_itme2 = group_question_item("2022.03.02","D-30", "틴틴팀", "저희는 죽기전에는 보나요??")
        question_list.add(question_itme2)
        var question_itme3 = group_question_item("2022.03.02","D-90", "Light_One", "자연어 처리가 뭐에요...")
        question_list.add(question_itme3)
        var question_itme4 = group_question_item("2022.03.02","D-7", "아아메", "빨리 끝내고 취업하고 싶어요!!")
        question_list.add(question_itme4)

        question_viewPager2.run {
            adapter = GroupQuestionViewPagerAdapter(question_list)
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

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
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
            val notquestionDialog: NotQuestionDialog = NotQuestionDialog {
                when (it) {
                    // 차단하기
                    1 -> {
                        val addDrinkBottomSheet = AddDrinkBottomSheet()
                        addDrinkBottomSheet.show(requireActivity().supportFragmentManager, addDrinkBottomSheet.tag)
                    }
                }
            }
            notquestionDialog.show(requireActivity().supportFragmentManager, notquestionDialog.tag)
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
                    }
                }
            }

            groupSettingBottomSheet.show(requireActivity().supportFragmentManager, groupSettingBottomSheet.tag)
        }

//        val notanswerDialog = NotAnswerDialog()
//        notanswerDialog.show(requireActivity().supportFragmentManager, notanswerDialog.tag)


    }

}