package com.example.testre.Cafe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testre.R

class CafeAdapter(val cafeList: ArrayList<Cafe>) : RecyclerView.Adapter<CafeAdapter.CustomViewHolder>() {
    // list_cafeitem 과 연결
    // context : 어뎁터와 연결될 엑티비티의 모든정보를 가져옴
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_cafe_item,parent,false)
        return CustomViewHolder(view) // -> CustomViewHolder의 itemView를 가져옴
    }
    // 만들어지는 뷰를 연결해주는 곳
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.cafenum?.text = cafeList.get(position).num
        holder.cafePicture.setImageResource(cafeList.get(position).cafe)// cafeList의 순서대로 가져옴
        holder.cafeName.text = cafeList.get(position).name
        holder.cafeInfo.text = cafeList.get(position).info
    }
    // 리스트 수
    override fun getItemCount(): Int {
        return cafeList.size
    }
    //뷰홀더 따로 생성 -> cafe class 객체 사용
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cafenum = itemView.findViewById<TextView>(R.id.tv_cafenum)
        val cafePicture = itemView.findViewById<ImageView>(R.id.iv_cafe) //카페 사진
        val cafeName = itemView.findViewById<TextView>(R.id.tv_cafename) // 카페 이름
        val cafeInfo = itemView.findViewById<TextView>(R.id.tv_cafeinfo) // 카페 설명

    }
}
