package com.example.bangu.main.profile.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.bangu.R
import com.example.bangu.databinding.ItemFollowingBinding
import com.example.bangu.main.profile.data.model.FollowContent

class FollowingAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = ArrayList<FollowContent>()

    inner class ItemViewHolder(private val binding: ItemFollowingBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(content:FollowContent){
            /**유저닉네임, 이미지 바인딩*/
            binding.apply {
                nickname.text = content.nickname
                when(content.imageUrl){
                    "" -> Glide.with(binding.root).load(R.drawable.review_icon).override(Target.SIZE_ORIGINAL).into(binding.image)
                    else -> Glide.with(binding.root).load(content.imageUrl).override(Target.SIZE_ORIGINAL).into(binding.image)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFollowingBinding.inflate(layoutInflater, parent, false)

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ItemViewHolder) holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setList(content:MutableList<FollowContent>){
        items.addAll(content) //서버에서 받아온 FollowContent 리스트 삽입
    }
}