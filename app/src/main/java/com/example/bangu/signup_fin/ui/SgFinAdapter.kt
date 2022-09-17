package com.example.bangu.signup_fin.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.bangu.databinding.ItemSignupFinBinding
import com.example.bangu.signup_fin.data.model.Content

class SgFinAdapter():RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = ArrayList<Content>()

    /**아이템이 게시물인 경우*/
    inner class ItemViewHolder(private val binding:ItemSignupFinBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(content:Content){
            val imageUrl = content.imageUrl
            Glide.with(binding.root).load(imageUrl).override(Target.SIZE_ORIGINAL).into(binding.movieimage) //이미지 바인딩
            binding.moviestar.rating = content.score //별점 바인딩
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSignupFinBinding.inflate(layoutInflater,parent,false)

        return ItemViewHolder(binding)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ItemViewHolder){
            holder.bind(items[position])
        }
    }
    /**전체 아이템 개수 리턴*/
    override fun getItemCount(): Int {
        return items.size
    }

    fun setList(content:MutableList<Content>){
        items.addAll(content)
    }
}