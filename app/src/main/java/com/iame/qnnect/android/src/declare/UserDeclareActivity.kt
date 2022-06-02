package com.iame.qnnect.android.src.declare

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityUserDeclareBinding
import com.iame.qnnect.android.src.declare.model.DeclareUser
import com.iame.qnnect.android.src.reply.reply_more.DeleteReplyDialog
import com.iame.qnnect.android.viewmodel.UserDeclareViewModel
import kotlinx.android.synthetic.main.activity_user_declare.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDeclareActivity : BaseActivity<ActivityUserDeclareBinding, UserDeclareViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_user_declare // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: UserDeclareViewModel by viewModel()

    private val declareAdapter: DeclareAdapter by inject()

    override fun initStartView() {
        binding.declareRecycler.run {
            adapter = declareAdapter
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.HORIZONTAL
            }
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        // user drink response
        viewModel.declareResponse.observe(this, Observer {
            declareAdapter.clear()
            if(it.isEmpty()){
                empty_img.visibility = View.VISIBLE
                empty_txt.visibility = View.VISIBLE
            }
            else{
                empty_img.visibility = View.GONE
                empty_txt.visibility = View.GONE
                it.forEach { item ->
                    declareAdapter.addItem(item)
                }
                declareAdapter.notifyDataSetChanged()
            }
            dismissLoadingDialog()
        })

        viewModel.dedeclareResponse.observe(this, Observer {
            dismissLoadingDialog()
            onResume()
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getDeclareList()
        showLoadingDialog(this)
    }

    override fun initAfterBinding() {
        binding.backBtn.setOnClickListener {
            finish()
        }

        declareAdapter.setOnItemClickListener(object : DeclareAdapter.OnItemClickEventListener {
            override fun onItemClick(a_view: View?, a_position: Int) {
                val item: DeclareUser = declareAdapter.getItem(a_position)

                val deleteDialog: DeleteDeclareDialog = DeleteDeclareDialog {
                    when (it) {
                        // 차단 해제
                        1 ->{
                            viewModel.deleteDeclare(item.reportId)
                            showLoadingDialog(this@UserDeclareActivity)
                        }
                    }
                }
                deleteDialog.show(supportFragmentManager, deleteDialog.tag)
            }
        })
    }
}