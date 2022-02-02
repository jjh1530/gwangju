package com.example.testre.Item

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testre.ChatList
import com.example.testre.databinding.ItemReviewListBinding

class ItemAdapter: ListAdapter<ChatList, ItemAdapter.ViewHolder>(diffUtil) {
    inner class ViewHolder(private val binding: ItemReviewListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chatList: ChatList) {

            binding.reviewTextView.text = chatList.title
            binding.reviewname.text = chatList.userId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemReviewListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ChatList>() {
            override fun areItemsTheSame(oldItem: ChatList, newItem: ChatList): Boolean {
                return oldItem.title == newItem.title
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: ChatList, newItem: ChatList): Boolean {
                return oldItem == newItem
            }
        }
    }
}