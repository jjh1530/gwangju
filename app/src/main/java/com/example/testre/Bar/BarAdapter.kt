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
//    // list_bar_item ??? ??????
//    // context : ???????????? ????????? ??????????????? ??????????????? ?????????
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_bar_item,parent,false)
//        return CustomViewHolder(view) // -> CustomViewHolder??? itemView??? ?????????
//    }
//    // ??????????????? ?????? ??????????????? ???
//    override fun onBindViewHolder(holder: BarAdapter.CustomViewHolder, position: Int) {
//        holder.barnum.text = barList.get(position).num
//        holder.barPicture.setImageResource(barList.get(position).bar)// barList??? ???????????? ?????????
//        holder.barName.text = barList.get(position).name
//        holder.barInfo.text = barList.get(position).info
//
//        // (1) ????????? ??? ?????? ?????? ??? onClick() ??????
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
//    // ????????? ???
//    override fun getItemCount(): Int {
//        return barList.size
//    }
//    //????????? ?????? ?????? -> Bar class ?????? ??????
//    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val barnum = itemView.findViewById<TextView>(R.id.tv_barnum)
//        val barPicture = itemView.findViewById<ImageView>(R.id.iv_bar) //?????? ??????
//        val barName = itemView.findViewById<TextView>(R.id.tv_barname) // ?????? ??????
//        val barInfo = itemView.findViewById<TextView>(R.id.tv_barinfo) // ?????? ??????
//
//
//
//    }
//
//    // (2) ????????? ???????????????
//    interface OnItemClickListener {
//        fun onClick(v: View, position: Int)
//    }
//    // (3) ???????????? ?????? ??? ????????? ??????
//    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
//        this.itemClickListener = onItemClickListener
//    }
//    // (4) setItemClickListener??? ????????? ?????? ??????
//    private lateinit var itemClickListener : OnItemClickListener
//
//}
//

