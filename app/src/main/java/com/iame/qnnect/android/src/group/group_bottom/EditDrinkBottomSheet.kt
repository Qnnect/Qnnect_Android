package com.iame.qnnect.android.src.group.group_bottom

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.iame.qnnect.android.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.iame.qnnect.android.base.HomeFragment_case
import com.iame.qnnect.android.src.add_drink.DrinkAdapter
import com.iame.qnnect.android.src.add_drink.drink
import com.iame.qnnect.android.src.add_drink.service.AddDrinkService
import com.iame.qnnect.android.src.add_drink.service.AddDrinkView
import com.iame.qnnect.android.src.group.GroupFragment
import com.iame.qnnect.android.util.Getdrink

class EditDrinkBottomSheet(var frag: GroupFragment) :
    BottomSheetDialogFragment(), AddDrinkView{
    private lateinit var dlg : BottomSheetDialog

    lateinit var drinkAdapter: DrinkAdapter
    lateinit var drinkRecyclerView: RecyclerView
    var drinkList = ArrayList<drink>()

    var home = HomeFragment_case()
    var drinkId = 1

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // 이 코드를 실행하지 않으면
        // XML에서 round 처리를 했어도 적용되지 않는다.
        dlg = ( super.onCreateDialog(savedInstanceState).apply {
            // window?.setDimAmount(0.2f) // Set dim amount here
            setOnShowListener {
                val bottomSheet = findViewById<View>(R.id.design_bottom_sheet) as FrameLayout
                bottomSheet.setBackgroundResource(android.R.color.transparent)

//                 아래와 같이하면 Drag를 불가능하게 한다.
                val behavior = BottomSheetBehavior.from(bottomSheet!!)
                behavior.isDraggable = true
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        } ) as BottomSheetDialog
        return dlg
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_edit_drink_bottom, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var close_btn = requireView().findViewById<ImageView>(R.id.close_btn)
        var ok_btn = requireView().findViewById<ConstraintLayout>(R.id.ok_btn)

        close_btn.setOnClickListener {
            dismiss()
        }
        ok_btn.setOnClickListener {
            var cafeId = home.getGroupname(requireContext())
            AddDrinkService(this).tryAddDrink(cafeId!!, drinkId)
        }

        drinkList.clear()
        for(i in 1..6){
            drinkList.add(Getdrink(i))
        }

        drinkAdapter = DrinkAdapter(drinkList)
        drinkRecyclerView = requireView().findViewById(R.id.drink_recycler)
        drinkRecyclerView.adapter = drinkAdapter
        drinkRecyclerView.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.HORIZONTAL, false)

//        val snapHelper: SnapHelper = PagerSnapHelper()
//        snapHelper.attachToRecyclerView(drinkRecyclerView)

        drinkAdapter.setOnItemClickListener(object : DrinkAdapter.OnItemClickListener {
            override fun onItemClick(v: View?, position: Int) {
                var request = drinkAdapter.getItem(position)

                drinkAdapter.notifyDataSetChanged()

//                viewModel.getBookamrk(request.cafeId)
//                showLoadingDialog(context!!)
            }
        })
    }

    override fun onAddDrinkSuccess(response: String) {
        dismiss()
        frag.onResume()
    }

    override fun onAddDrinkFailure(message: String) {
        TODO("Not yet implemented")
    }
}
