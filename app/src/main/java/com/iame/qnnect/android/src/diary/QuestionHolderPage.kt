package com.iame.qnnect.android.src.diary

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.answer.AnswerActivity
import com.iame.qnnect.android.src.diary.DiaryActivity
import com.iame.qnnect.android.src.diary.model.Comments
import com.iame.qnnect.android.src.group.model.CafeQuestion
import com.iame.qnnect.android.src.group.model.group_question_item
import kotlinx.android.synthetic.main.item_main_image.view.*


class QuestionHolderPage internal constructor(itemView: View, var context: Context) : RecyclerView.ViewHolder(itemView) {
    private val user_img :ImageView
    private val user_name: TextView
    private val answer_contents: TextView
    private val answer_img: ImageView
    private val answer_btn: ImageView

    var data: Comments? = null
    fun onBind(data: Comments) {
        this.data = data

        var profile = data.profileResponse

        if(profile.profileImage == null){
            user_img.setImageResource(R.mipmap.img_profile_dafault_foreground)
        }
        else{
            Glide.with(context)
                .load(profile.profileImage)
                .transform(CenterCrop(), RoundedCorners(200))
                .into(user_img)
        }
        user_name.setText(profile.nickName)

        answer_contents.setText(data.content)

        var image_check = true
        var images = data
        if(images.imageUrl1 == null && images.imageUrl2 == null && images.imageUrl3 == null &&
            images.imageUrl4 == null && images.imageUrl5 == null){
            image_check = false
        }

        if(!image_check){
            answer_img.visibility = View.GONE
        }
        else{
            Glide.with(context)
                .load(images.imageUrl1)
                .transform(CenterCrop(), RoundedCorners(200))
                .into(answer_img)
        }

        answer_btn.setOnClickListener {
//            var intent = Intent(context, AnswerActivity::class.java)
//            intent.putExtra("commentId", data.commentId)
//            context.startActivity(intent)
        }
    }

    init {
        user_img = itemView.findViewById(R.id.user_img)
        user_name = itemView.findViewById(R.id.user_name)
        answer_contents = itemView.findViewById(R.id.answer_contents)
        answer_img = itemView.findViewById(R.id.answer_img)
        answer_btn = itemView.findViewById(R.id.answer_btn)
    }
}
