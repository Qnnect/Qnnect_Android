package com.iame.qnnect.android.src.main.home

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.iame.qnnect.android.base.BaseFragment
import com.iame.qnnect.android.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.iame.qnnect.android.src.main.MainActivity
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.HomeFragment_case
import com.iame.qnnect.android.databinding.FragmentHomeBinding
import com.iame.qnnect.android.src.main.home.home_bottom.AddGroupBottomSheet
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
        viewModel.homeResponse.observe(this, Observer {
//            dismissLoadingDialog()
            var image = it.user.profileImage

            // Profile Url
            Glide.with(this)
                .load(image)
                .transform(CenterCrop(), RoundedCorners(200))
                .into(user_profile_img)

            // User Name
            user_diary_name.text = it.user.nickName+"님의 다이어리"
            // User Point
            point_txt.text = it.user.point.toString()+"P"

            it.questionList.forEach { item ->
                questionRecyclerViewAdapter.addItem(item)
            }

            it.groupList.forEach { item ->
                groupAdapter.addItem(item)
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
    }

    override fun onResume() {
        super.onResume()

        groupAdapter.clear()
        questionRecyclerViewAdapter.clear()

        viewModel.getHome()
        showLoadingDialog(context!!)
    }
}