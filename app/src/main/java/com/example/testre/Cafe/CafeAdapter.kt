package com.example.testre.Cafe

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testre.Bar.Bar
import com.example.testre.R
import com.example.testre.databinding.ListCafeItemBinding

class CafeAdapter(val onItemClicked: (Cafe) -> Unit): ListAdapter<Cafe, CafeAdapter.ViewHolder>(diffUtil) {
    inner class ViewHolder(private val binding: ListCafeItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cafe : Cafe) {
            binding.tvCafenum.text = cafe.num
            binding.tvCafename.text = cafe.name
            binding.tvCafeinfo.text = cafe.info
            Glide.with(binding.ivCafe)
                .load(cafe.picture)
                .into(binding.ivCafe)

            binding.root.setOnClickListener {
                onItemClicked(cafe)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListCafeItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Cafe>() {
            override fun areItemsTheSame(oldItem: Cafe, newItem: Cafe): Boolean {
                return oldItem.num == newItem.num
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Cafe, newItem: Cafe): Boolean {
                return oldItem == newItem
            }
        }
    }
}
