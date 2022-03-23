package com.iame.qnnect.android.src.group

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.iame.qnnect.android.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.HomeFragment_case
import com.iame.qnnect.android.databinding.FragmentGroupBinding
import com.iame.qnnect.android.src.drink.DrinkActivity
import com.iame.qnnect.android.src.group.group_bottom.EditDrinkBottomSheet
import com.iame.qnnect.android.src.group.group_bottom.EditGroupBottomSheet
import com.iame.qnnect.android.src.group.group_setting.GroupSettingBottomSheet
import com.iame.qnnect.android.src.group.member.GroupMemberAdapter
import com.iame.qnnect.android.src.group.question.GroupQuestionViewPagerAdapter
import com.iame.qnnect.android.src.invite.InviteActivity
import com.iame.qnnect.android.src.main.MainActivity
import com.iame.qnnect.android.src.add_drink.AddDrinkBottomSheet
import com.iame.qnnect.android.src.question.QuestionActivity
import com.iame.qnnect.android.src.question.QuestionListActivity
import com.iame.qnnect.android.util.drink_img
import com.iame.qnnect.android.util.drink_imgName
import com.iame.qnnect.android.viewmodel.GroupViewModel
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
    var drink_check = true
    var code = ""
    var title = ""

    var cafeId = 0
    var userId = 0
    var userDrink = ""
    var drink_editCheck = true

    var memberCount = 0

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

    override fun onResume() {
        super.onResume()
        groupMemberAdapter.clear()
        groupQuestionViewPagerAdapter.clear()
        var id = home.getGroupname(requireContext())
        viewModel.getGroup(id!!)
        showLoadingDialog(requireContext())
    }

    override fun initDataBinding() {
        viewModel.groupResponse.observe(this, Observer {
            group_date_txt.text = it.createdAt
            group_name_txt.text = it.title
            group_name_main.text = it.title
            title = it.title

            memberCount = it.cafeUserList.size+1

            code = it.code
            cafeId = it.cafeId
            userId = it.cafeUserId

            if(it.currentUser.cafeDrinkCommonResponse.userDrinkName == null){
                drink_img_default.visibility = View.VISIBLE
                select_text.visibility = View.VISIBLE
                drink_img.visibility = View.GONE
                drink_check = false
                drink_editCheck = false
            }
            else{
                userDrink = it.currentUser.cafeDrinkCommonResponse.userDrinkName
                drink_editCheck = it.currentUser.cafeDrinkCommonResponse.currentDrinkIngredientsFilled == null

                drink_img_default.visibility = View.GONE
                select_text.visibility = View.GONE
                drink_img.visibility = View.VISIBLE

                if(drink_editCheck || it.currentUser.cafeDrinkCommonResponse.currentDrinkIngredientsFilled.size < 2){
                    var img = drink_imgName(userDrink, "빈잔")
                    drink_img.setImageResource(img)
                }
                else{
                    var list = it.currentUser.cafeDrinkCommonResponse.currentDrinkIngredientsFilled
                    var last = list.size-1
                    if(list.get(last).ingredientName == list.get(last-1).ingredientName){
                        var img = drink_imgName(userDrink, list.get(last).ingredientName)
                        drink_img.setImageResource(img)
                    }
                    else{
                        var img = drink_imgName(userDrink, list.get(last-1).ingredientName)
                        drink_img.setImageResource(img)
                    }
                }
                drink_check = true
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
            dismissLoadingDialog()
        })

        viewModel.errorResponse.observe(this, Observer {
            Log.d("error_response", it.toString())
            home_case.setHome(requireContext(), 0, -1)

            activity = fragment_s.activity as MainActivity?
            //change_for_adapter는 mainactivity에 구현
            activity?.fragmentChange_for_adapter()
            Toast.makeText(context, "신고를 받은 방입니다.", Toast.LENGTH_SHORT).show()
            dismissLoadingDialog()
        })
    }

    override fun initAfterBinding() {
        question_list_btn.setOnClickListener {
            var intent = Intent(context, QuestionListActivity::class.java)
            startActivity(intent)
        }

        drink_img_default.setOnClickListener {
            val addDrinkBottomSheet = AddDrinkBottomSheet(this)
            addDrinkBottomSheet.show(requireActivity().supportFragmentManager, addDrinkBottomSheet.tag)
        }
        drink_img.setOnClickListener {
            var intent = Intent(context, DrinkActivity::class.java)
            intent.putExtra("cafeId", cafeId)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }

        select_text.setOnClickListener {
            if(drink_check){
                var intent = Intent(context, DrinkActivity::class.java)
                intent.putExtra("cafeId", cafeId)
                intent.putExtra("userId", userId)
                startActivity(intent)
            }
            else{
                val addDrinkBottomSheet = AddDrinkBottomSheet(this)
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
                            val addDrinkBottomSheet = AddDrinkBottomSheet(this)
                            addDrinkBottomSheet.show(requireActivity().supportFragmentManager, addDrinkBottomSheet.tag)
                        }
                    }
                }
                notquestionDialog.show(requireActivity().supportFragmentManager, notquestionDialog.tag)
            }
        }

        more_btn.setOnClickListener {
            if(drink_check){
                val groupSettingBottomSheet: GroupSettingBottomSheet = GroupSettingBottomSheet {
                    when (it) {
                        // 초대하기
                        0 -> {
                            if(memberCount >= 5){
                                val overGroupDialog: OverGroupDialog = OverGroupDialog()
                                overGroupDialog.show(requireActivity().supportFragmentManager, overGroupDialog.tag)
                            }
                            else{
                                var intent = Intent(context, InviteActivity::class.java)
                                intent.putExtra("code", code)
                                Log.d("intent_response", title)
                                intent.putExtra("title", title)
                                startActivity(intent)
                            }
                        }
                        // 카페 수정
                        1 -> {
                            val editgroupBottomSheet: EditGroupBottomSheet = EditGroupBottomSheet(title) {
                                when (it) {
                                    // 카페 수정
                                    0 -> {
                                        onResume()
                                    }
                                }
                            }
                            editgroupBottomSheet.show(requireActivity().supportFragmentManager, editgroupBottomSheet.tag)
                        }
                        // 음료 수정
                        2 -> {
                            if(drink_editCheck){
                                val editDrinkBottomSheet = EditDrinkBottomSheet(this)
                                editDrinkBottomSheet.show(requireActivity().supportFragmentManager, editDrinkBottomSheet.tag)
                            }
                            else{
                                Toast.makeText(context, "음료가 빈잔일 때만 수정이 가능합니다!", Toast.LENGTH_SHORT).show()}
                        }
                        // 카페 나가기
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
                groupSettingBottomSheet.show(requireActivity().supportFragmentManager, groupSettingBottomSheet.tag)
            }
            else{
                val notquestionDialog: NotQuestionDialog = NotQuestionDialog {
                    when (it) {
                        // 음료추가
                        1 -> {
                            val addDrinkBottomSheet = AddDrinkBottomSheet(this)
                            addDrinkBottomSheet.show(requireActivity().supportFragmentManager, addDrinkBottomSheet.tag)
                        }
                    }
                }
                notquestionDialog.show(requireActivity().supportFragmentManager, notquestionDialog.tag)
            }
        }
    }
}