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
import com.iame.qnnect.android.util.drinkName
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
        binding.memberRecycler.run {
            adapter = groupMemberAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            setHasFixedSize(true)
        }

        // question viewpager
        binding.questionViewPager2.run {
            adapter = groupQuestionViewPagerAdapter
        }
        
        //인디케이터 타입1
        val dotsIndicator = binding.dotsIndicator
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
            binding.groupDateTxt.text = it.createdAt
            binding.groupNameTxt.text = it.title
            binding.groupNameMain.text = it.title
            title = it.title

            memberCount = it.cafeUserList.size+1

            code = it.code
            cafeId = it.cafeId
            userId = it.cafeUserId

            if(it.currentUser.cafeDrinkCommonResponse.userDrinkName == null){
                binding.drinkImgDefault.visibility = View.VISIBLE
                binding.selectText.visibility = View.VISIBLE
                binding.drinkImg.visibility = View.GONE
                drink_check = false
                drink_editCheck = false
            }
            else{
                userDrink = it.currentUser.cafeDrinkCommonResponse.userDrinkName
                drink_editCheck = it.currentUser.cafeDrinkCommonResponse.currentDrinkIngredientsFilled == null
                drink_check = true

                binding.drinkImgDefault.visibility = View.GONE
                binding.selectText.visibility = View.GONE
                binding.drinkImg.visibility = View.VISIBLE
                if(it.currentUser.cafeDrinkCommonResponse.currentDrinkIngredientsFilled.isEmpty()){
                    drink_editCheck = true
                }

                var img = drinkName(userDrink, "빈잔")
                if(drink_editCheck || it.currentUser.cafeDrinkCommonResponse.currentDrinkIngredientsFilled.size < 2){
                    img = drinkName(userDrink, "빈잔")
                }
                else{
                    var current = it.currentUser.cafeDrinkCommonResponse
                    if(current.ice == current.iceFilled){
                        img = drinkName(userDrink, "얼음")
                    }
                    if(current.base == current.baseFilled){
                        img = drinkName(userDrink, "베이스")
                    }
                    if(current.main == current.mainFilled){
                        img = drinkName(userDrink, "메인")
                    }
                    if(current.topping == current.toppingFilled){
                        img = drinkName(userDrink, "토핑")
                    }
                }
                binding.drinkImg.setImageResource(img)
            }

            if(it.cafeUserList.isEmpty()){
                binding.emptyDrink.visibility = View.VISIBLE
                binding.memberRecycler.visibility = View.GONE
            }
            else{
                binding.memberRecycler.visibility = View.VISIBLE
                binding.emptyDrink.visibility = View.GONE
                it.cafeUserList.forEach { item ->
                    groupMemberAdapter.addItem(item)
                }
                groupMemberAdapter.notifyDataSetChanged()
            }

            if(it.cafeQuestionList.isEmpty()){
                binding.itemMain.visibility = View.VISIBLE
                binding.questionViewPager2.visibility = View.GONE
                binding.dotsIndicator.visibility = View.GONE
            }
            else{
                binding.itemMain.visibility = View.GONE
                binding.questionViewPager2.visibility = View.VISIBLE
                binding.dotsIndicator.visibility = View.VISIBLE
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
            Toast.makeText(context, "네트워크 상태가 안좋습니다.", Toast.LENGTH_SHORT).show()
            dismissLoadingDialog()
        })
    }

    override fun initAfterBinding() {
        binding.questionListBtn.setOnClickListener {
            var intent = Intent(context, QuestionListActivity::class.java)
            startActivity(intent)
        }

        binding.drinkImgDefault.setOnClickListener {
            val addDrinkBottomSheet = AddDrinkBottomSheet(this)
            addDrinkBottomSheet.show(requireActivity().supportFragmentManager, addDrinkBottomSheet.tag)
        }
        binding.drinkImg.setOnClickListener {
            var intent = Intent(context, DrinkActivity::class.java)
            intent.putExtra("cafeId", cafeId)
            intent.putExtra("userId", userId)
            startActivity(intent)
        }

        binding.selectText.setOnClickListener {
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

        binding.backBtn.setOnClickListener {
            home_case.setHome(requireContext(), 0, -1)

            activity = fragment_s.activity as MainActivity?
            //change_for_adapter는 mainactivity에 구현
            activity?.fragmentChange_for_adapter()
        }

        binding.questionBtn.setOnClickListener {
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

        binding.moreBtn.setOnClickListener {
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
                        val editgroupBottomSheet: EditGroupBottomSheet = EditGroupBottomSheet(title) { it ->
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
                        // 음료 선택시
                        if(drink_check){
                            if(drink_editCheck){
                                val editDrinkBottomSheet = EditDrinkBottomSheet(this)
                                editDrinkBottomSheet.show(requireActivity().supportFragmentManager, editDrinkBottomSheet.tag)
                            }
                            else{
                                Toast.makeText(context, "음료가 빈잔일 때만 수정이 가능합니다!", Toast.LENGTH_SHORT).show()
                            }
                        }
                        // 음료 미선택
                        else{
                            val addDrinkBottomSheet = AddDrinkBottomSheet(this)
                            addDrinkBottomSheet.show(requireActivity().supportFragmentManager, addDrinkBottomSheet.tag)
                        }
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
    }
}