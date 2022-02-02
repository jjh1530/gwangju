package com.example.testre.Resturant

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
import com.example.testre.Cafe.Cafe
import com.example.testre.R
import com.example.testre.databinding.ListBarItemBinding
import com.example.testre.databinding.ListResturantItemBinding

class ResturantAdapter(val onItemClicked: (Resturant) -> Unit) : ListAdapter<Resturant,ResturantAdapter.ViewHolder>(diffUtil) {
    inner class ViewHolder(private val binding: ListResturantItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(resturant : Resturant) {
            binding.tvResturantnum.text = resturant.num
            binding.tvResturantname.text = resturant.name
            binding.tvResturantinfo.text = resturant.info
            Glide.with(binding.ivResturant)
                .load(resturant.picture)
                .into(binding.ivResturant)

            binding.root.setOnClickListener {
                onItemClicked(resturant)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListResturantItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Resturant>() {
            override fun areItemsTheSame(oldItem: Resturant, newItem: Resturant): Boolean {
                return oldItem.num == newItem.num
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Resturant, newItem: Resturant): Boolean {
                return oldItem == newItem
            }
        }
    }

}
