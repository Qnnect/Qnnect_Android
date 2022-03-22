package com.iame.qnnect.android.src.answer

import com.bumptech.glide.Glide

import android.content.Context
import android.net.Uri
import android.util.Log

import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.widget.ImageView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.iame.qnnect.android.MyConstant.Companion.radius
import com.iame.qnnect.android.R
import com.iame.qnnect.android.src.group.model.CafeUser


class EditImageAdapter internal constructor() :
    RecyclerView.Adapter<EditImageAdapter.ViewHolder>() {
    private var list: ArrayList<Uri?> = ArrayList()
    var select_index = -1

    // item click listener
    interface OnItemClickListener {
        fun onItemClick(v: View?, position: Int)
    }

    // 리스너 객체 참조를 저장하는 변수
    private var mListener: OnItemClickListener? = null

    // OnItemClickListener 리스너 객체 참조를 어댑터에 전달하는 메서드
    fun setOnItemClickListener(listener: OnItemClickListener?) {
        mListener = listener
    }

    // 아이템 뷰를 저장하는 뷰홀더 클래스.
    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var delete_btn: ImageView

        init {
            // 뷰 객체에 대한 참조.
            image = itemView.findViewById(R.id.gallery_img)
            // 뷰 객체에 대한 참조.
            delete_btn = itemView.findViewById(R.id.delete_btn)
        }
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    // LayoutInflater - XML에 정의된 Resource(자원) 들을 View의 형태로 반환.
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val context: Context = parent.context
        val inflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater // context에서 LayoutInflater 객체를 얻는다.
        val view: View = inflater.inflate(R.layout.answer_image_item,
            parent,
            false) // 리사이클러뷰에 들어갈 아이템뷰의 레이아웃을 inflate.
        return ViewHolder(view)
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(list!!.get(position) == null){
            holder.itemView.visibility = View.GONE
        }
        else{
            val image_uri: Uri = list!!.get(position)!!
            Glide.with(holder.itemView.context)
                .load(image_uri)
                .transform(CenterCrop(), RoundedCorners(30))
                .into(holder.image)
        }

        // // item click listener
        holder.delete_btn.setOnClickListener(View.OnClickListener { v ->
            val pos: Int = position
            if (pos != RecyclerView.NO_POSITION) {
                // 리스너 객체의 메서드 호출.
                if (mListener != null) {
                    mListener!!.onItemClick(v, pos)
                }
                select_index=position
            }
        })
    }
    // getItemCount() - 전체 데이터 갯수 리턴.
    fun clear() {
        return list!!.clear()
    }

    fun addItem(item: Uri) {
        list!!.add(item)
    }

    fun addPositionItem(position: Int, item: Uri) {
        list!!.set(position, item)
    }

    fun getItem(item: Uri) {
        list!!.add(item)
    }

    fun getCount(): Int {
        var count = 0
        for(i in 0..list!!.size){
            if(list!!.get(i) != null){count = count+1}
        }
        return count
    }

    fun deleteItem(position: Int) {
        list!!.set(position, null)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
