package com.iame.qnnect.android.src.group

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.iame.qnnect.android.MainSearchRecyclerViewAdapter
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
import org.koin.android.ext.android.inject


class GroupFragment : BaseFragment<FragmentGroupBinding, GroupViewModel>(R.layout.fragment_group) {

    override val layoutResourceId: Int
        get() = R.layout.fragment_group // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: GroupViewModel by viewModel()

    private val groupMemberAdapter: GroupMemberAdapter by inject()
    private val groupQuestionViewPagerAdapter: GroupQuestionViewPagerAdapter by inject()


    var home_case = HomeFragment_case()
    val fragment_s: Fragment = this
    private var activity: MainActivity? = null
    var drink_check = false
    var check = false

    var home = HomeFragment_case()


    override fun initStartView() {
        // member recycler
        member_recycler.run {
            adapter = groupMemberAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            setHasFixedSize(true)
        }

        // question viewpager
        question_viewPager2.run {
            adapter = groupQuestionViewPagerAdapter
        }
        
        //인디케이터 타입1
        val dotsIndicator = dots_indicator
        dotsIndicator.setViewPager2(question_viewPager2)

    }

    override fun initDataBinding() {
        var id = home.getGroupname(context!!)
        viewModel.getGroup(id!!)

        viewModel.groupResponse.observe(this, Observer {
            group_date_txt.text = it.createdAt
            group_name_txt.text = it.title
            group_name_main.text = it.title
            check = it.organizer

            if(it.currentUser.userDrinkSelected == null){
                drink_img.setImageResource(R.mipmap.img_drink_default_foreground)
                drink_check = false
            }
            else{
                if(it.currentUser.drinkIngredientsFilledResponseList.size == 0){
                    drink_img.setImageResource(R.mipmap.img_drink_basic_foreground)
                    select_text.visibility = View.GONE
                    drink_check = true
                }
            }

            if(it.cafeUserList.size == 0){
                empty_drink.visibility = View.VISIBLE
                member_recycler.visibility = View.GONE
            }
            else{
                member_recycler.visibility = View.VISIBLE
                empty_drink.visibility = View.GONE
                it.cafeUserList.forEach { item ->
                    groupMemberAdapter.addItem(item)
                }
                groupMemberAdapter.notifyDataSetChanged()
            }

            if(it.cafeQuestionList.size == 0){
                item_main.visibility = View.VISIBLE
                question_viewPager2.visibility = View.GONE
                dots_indicator.visibility = View.GONE
            }
            else{
                item_main.visibility = View.GONE
                question_viewPager2.visibility = View.VISIBLE
                dots_indicator.visibility = View.VISIBLE
                it.cafeQuestionList.forEach { item ->
                    groupQuestionViewPagerAdapter.addItem(item)
                }
                groupQuestionViewPagerAdapter.notifyDataSetChanged()
            }

        })
    }

    override fun initAfterBinding() {

        drink_img.setOnClickListener {
            if(drink_check){
                var intent = Intent(context, DrinkActivity::class.java)
                startActivity(intent)
            }
            else{
                val addDrinkBottomSheet = AddDrinkBottomSheet()
                addDrinkBottomSheet.show(requireActivity().supportFragmentManager, addDrinkBottomSheet.tag)
            }
        }

        back_btn.setOnClickListener {
            home_case.setHome(requireContext(), 0, -1)

            activity = fragment_s.activity as MainActivity?
            //change_for_adapter는 mainactivity에 구현
            activity?.fragmentChange_for_adapter()
        }

        question_btn.setOnClickListener {
            if(drink_check){
                var intent = Intent(context, QuestionActivity::class.java)
                startActivity(intent)
            }
            else{
                val notquestionDialog: NotQuestionDialog = NotQuestionDialog {
                    when (it) {
                        // 음료추가
                        1 -> {
                            val addDrinkBottomSheet = AddDrinkBottomSheet()
                            addDrinkBottomSheet.show(requireActivity().supportFragmentManager, addDrinkBottomSheet.tag)
                        }
                    }
                }
                notquestionDialog.show(requireActivity().supportFragmentManager, notquestionDialog.tag)
            }
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
                                    initDataBinding()
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
                        val deleteGroupDialog = DeleteGroupDialog {
                            when (it) {
                                0 -> {
                                    home_case.setHome(requireContext(), 0, -1)

                                    activity = fragment_s.activity as MainActivity?
                                    //change_for_adapter는 mainactivity에 구현
                                    activity?.fragmentChange_for_adapter()
                                }
                            }
                        }
                        deleteGroupDialog.show(requireActivity().supportFragmentManager, deleteGroupDialog.tag)

                    }
                }
            }

            groupSettingBottomSheet.set(check)

            groupSettingBottomSheet.show(requireActivity().supportFragmentManager, groupSettingBottomSheet.tag)
        }
    }
}