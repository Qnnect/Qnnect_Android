package com.iame.qnnect.android.src.stamp

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.iame.qnnect.android.R
import com.iame.qnnect.android.base.BaseActivity
import com.iame.qnnect.android.databinding.ActivityStampBinding
import com.iame.qnnect.android.src.stamp.model.Stamp
import com.iame.qnnect.android.viewmodel.StampViewModel
import kotlinx.android.synthetic.main.activity_stamp.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class StampActivity : BaseActivity<ActivityStampBinding, StampViewModel>() {

    override val layoutResourceId: Int
        get() = R.layout.activity_stamp // get() : 커스텀 접근자, 코틀린 문법

    override val viewModel: StampViewModel by viewModel()

    private val stampAdapter: StampAdapter by inject()


    override fun initStartView() {
        text1.text = intent.getStringExtra("userName")+"님의\n적립 스탬프"
        stamp_recycler.run {
            adapter = stampAdapter
            layoutManager = GridLayoutManager(context,3).apply {
                orientation = GridLayoutManager.VERTICAL
            }
            setHasFixedSize(true)
        }
    }

    override fun initDataBinding() {
        viewModel.stampResponse.observe(this, Observer {
            var count = it.size/3+1 // 4
            it.forEach { item ->
                stampAdapter.addItem(item)
            }
            if(stampAdapter.itemCount < 9){
                for(i in stampAdapter.itemCount+1..9){
                    var item = Stamp(" ", "빈잔")
                    stampAdapter.addItem(item)
                }
            }
            else{
                for(i in stampAdapter.itemCount+1..3*count){
                    var item = Stamp(" ", "빈잔")
                    stampAdapter.addItem(item)
                }
            }
            stampAdapter.notifyDataSetChanged()
            dismissLoadingDialog()
        })


    }

    override fun onResume() {
        super.onResume()
        viewModel.getStamp()
        showLoadingDialog(this)
    }

    override fun initAfterBinding() {
        back_btn.setOnClickListener {
            finish()
        }
    }
}