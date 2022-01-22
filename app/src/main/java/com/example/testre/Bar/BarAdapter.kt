package com.example.testre.Bar

import android.content.Context
import android.content.Intent
import android.icu.text.Transliterator
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testre.MainActivity
import com.example.testre.R
import com.example.testre.Test.AllActivityitem
import java.net.Inet4Address
import kotlin.coroutines.coroutineContext

class BarAdapter(val barList: ArrayList<Bar>) : RecyclerView.Adapter<BarAdapter.CustomViewHolder>() {
    // list_bar_item 과 연결
    // context : 어뎁터와 연결될 엑티비티의 모든정보를 가져옴
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_bar_item,parent,false)
        return CustomViewHolder(view) // -> CustomViewHolder의 itemView를 가져옴
    }
    // 만들어지는 뷰를 연결해주는 곳
    override fun onBindViewHolder(holder: BarAdapter.CustomViewHolder, position: Int) {
        holder.barnum.text = barList.get(position).num
        holder.barPicture.setImageResource(barList.get(position).bar)// cafeList의 순서대로 가져옴
        holder.barName.text = barList.get(position).name
        holder.barInfo.text = barList.get(position).info

        // (1) 리스트 내 항목 클릭 시 onClick() 호출
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)

            }
    }

    // 리스트 수
    override fun getItemCount(): Int {
        return barList.size
    }
    //뷰홀더 따로 생성 -> Bar class 객체 사용
    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val barnum = itemView.findViewById<TextView>(R.id.tv_barnum)
        val barPicture = itemView.findViewById<ImageView>(R.id.iv_bar) //술집 사진
        val barName = itemView.findViewById<TextView>(R.id.tv_barname) // 술집 이름
        val barInfo = itemView.findViewById<TextView>(R.id.tv_barinfo) // 술집 설명



    }

    // (2) 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener

}



