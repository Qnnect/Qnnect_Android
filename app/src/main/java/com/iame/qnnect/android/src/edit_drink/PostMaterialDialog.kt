package com.iame.qnnect.android.src.edit_drink

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.edit_drink.model.MyIngredient
import com.iame.qnnect.android.util.recipe

class PostMaterialDialog(val recipe: recipe,val itemClick: (Int) -> Unit) : DialogFragment() {

    val item = recipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //false로 설정해 주면 화면밖 혹은 뒤로가기 버튼시 다이얼로그라 dismiss 되지 않는다.
        isCancelable = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.post_add_material_dialog, container,false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var recipe_img = view!!.findViewById<ImageView>(R.id.recipe_img)
        recipe_img.setImageResource(item.img)

        var text = view!!.findViewById<TextView>(R.id.text1)
        text.text = "정말 ‘"+ item.name + "’를 사용하시겠나요?"

        var close_btn = view!!.findViewById<TextView>(R.id.close_btn)
        close_btn.setOnClickListener {
            dismiss()
        }

        var ok_btn = view!!.findViewById<TextView>(R.id.ok_btn)
        ok_btn.setOnClickListener {
            dismiss()
            itemClick(1)
        }
    }
    override fun onResume() {
        super.onResume()
        val windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
        val deviceWidth = size.x
        params?.width = (deviceWidth * 0.8).toInt()
        dialog?.window?.attributes = params as WindowManager.LayoutParams
    }
}