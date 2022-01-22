package com.example.testre.Resturant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testre.R

class ResturantAdapter(val resturantList: ArrayList<Resturant>) : RecyclerView.Adapter<ResturantAdapter.CustomViewHolder>() {
    // list_resturantitem 과 연결
    // context : 어뎁터와 연결될 엑티비티의 모든정보를 가져옴
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_resturant_item,parent,false)
        return CustomViewHolder(view) // -> CustomViewHolder의 itemView를 가져옴
    }
    // 만들어지는 뷰를 연결해주는 곳
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.resturantnum?.text = resturantList.get(position).num
        holder.resturantPicture.setImageResource(resturantList.get(position).resturant)// resturantList의 순서대로 가져옴
        holder.resturantName.text = resturantList.get(position).name
        holder.resturantInfo.text = resturantList.get(position).info
    }
    // 리스트 수
    override fun getItemCount(): Int {
        return resturantList.size
    }
    //뷰홀더 따로 생성 -> resturant class 객체 사용
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val resturantnum = itemView.findViewById<TextView>(R.id.tv_resturantnum)
        val resturantPicture = itemView.findViewById<ImageView>(R.id.iv_resturant) //술집 사진
        val resturantName = itemView.findViewById<TextView>(R.id.tv_resturantname) // 술집 이름
        val resturantInfo = itemView.findViewById<TextView>(R.id.tv_resturantinfo) // 술집 설명

    }
}
