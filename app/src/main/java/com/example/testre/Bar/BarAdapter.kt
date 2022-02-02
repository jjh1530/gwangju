package com.example.testre.Bar

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testre.R
import com.example.testre.databinding.ListBarItemBinding


class BarAdapter(val onItemClicked: (Bar) -> Unit): ListAdapter<Bar, BarAdapter.ViewHolder>(diffUtil) {
    inner class ViewHolder(private val binding: ListBarItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(bar : Bar) {
            binding.tvBarnum.text = bar.num
            binding.tvBarname.text = bar.name
            binding.tvBarinfo.text = bar.info
            Glide.with(binding.ivBar)
                .load(bar.picture)
                .into(binding.ivBar)

            binding.root.setOnClickListener {
                onItemClicked(bar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListBarItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(currentList[position])

    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Bar>() {
            override fun areItemsTheSame(oldItem: Bar, newItem: Bar): Boolean {
                return oldItem.num == newItem.num
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Bar, newItem: Bar): Boolean {
                return oldItem == newItem
            }
        }
    }
}

//class BarAdapter(val barList: ArrayList<Bar>) : RecyclerView.Adapter<BarAdapter.CustomViewHolder>() {
//
//    // list_bar_item 과 연결
//    // context : 어뎁터와 연결될 엑티비티의 모든정보를 가져옴
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_bar_item,parent,false)
//        return CustomViewHolder(view) // -> CustomViewHolder의 itemView를 가져옴
//    }
//    // 만들어지는 뷰를 연결해주는 곳
//    override fun onBindViewHolder(holder: BarAdapter.CustomViewHolder, position: Int) {
//        holder.barnum.text = barList.get(position).num
//        holder.barPicture.setImageResource(barList.get(position).bar)// barList의 순서대로 가져옴
//        holder.barName.text = barList.get(position).name
//        holder.barInfo.text = barList.get(position).info
//
//        // (1) 리스트 내 항목 클릭 시 onClick() 호출
//        holder.itemView.setOnClickListener {
//            itemClickListener.onClick(it, position)
//            val barname = barList.get(position).name
//            val barInfo = barList.get(position).info
//            val barpicture = barList.get(position).bar
//            val intent = Intent(holder.itemView?.context, BarAcitivityItem::class.java)
//            intent.putExtra("name",barname)
//            intent.putExtra("info",barInfo)
//            intent.putExtra("picture",barpicture)
//            ContextCompat.startActivity(holder.itemView.context, intent, null)
//        }
//    }
//
//    // 리스트 수
//    override fun getItemCount(): Int {
//        return barList.size
//    }
//    //뷰홀더 따로 생성 -> Bar class 객체 사용
//    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val barnum = itemView.findViewById<TextView>(R.id.tv_barnum)
//        val barPicture = itemView.findViewById<ImageView>(R.id.iv_bar) //술집 사진
//        val barName = itemView.findViewById<TextView>(R.id.tv_barname) // 술집 이름
//        val barInfo = itemView.findViewById<TextView>(R.id.tv_barinfo) // 술집 설명
//
//
//
//    }
//
//    // (2) 리스너 인터페이스
//    interface OnItemClickListener {
//        fun onClick(v: View, position: Int)
//    }
//    // (3) 외부에서 클릭 시 이벤트 설정
//    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
//        this.itemClickListener = onItemClickListener
//    }
//    // (4) setItemClickListener로 설정한 함수 실행
//    private lateinit var itemClickListener : OnItemClickListener
//
//}
//

