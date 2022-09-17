package com.example.bangu.signup_fin.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with
import com.example.bangu.GlideApp
import com.example.bangu.MyGlideApp
import com.example.bangu.databinding.ItemSignupFinBinding
import com.example.bangu.databinding.ItemSignupFinLoadingBinding
import com.example.bangu.signup_fin.data.model.Content

class SgFinAdapter():RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object{
        private const val TYPE_ITEM = 0
    }
    private val items = ArrayList<Content>()

    //아이템이 게시물인 경우
    inner class ItemViewHolder(private val binding:ItemSignupFinBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(content:Content){
            val imageUrl = content.imageUrl
            GlideApp.with(binding.root).load(imageUrl).override(158,227).into(binding.movieimage) //이미지 바인딩
            binding.moviestar.rating = content.score //별점 바인딩
        }
    }
    //아이템이 로딩뷰인 경우
    inner class LoadingViewHolder(private val binding:ItemSignupFinLoadingBinding): RecyclerView.ViewHolder(binding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RecyclerView.ViewHolder {
        when(viewType){
            TYPE_ITEM -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemSignupFinBinding.inflate(layoutInflater,parent,false)

                return ItemViewHolder(binding)
            }
            //TYPE_LOADING
            else -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemSignupFinLoadingBinding.inflate(layoutInflater,parent,false)

                return LoadingViewHolder(binding)
            }
        }
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ItemViewHolder){
            holder.bind(items[position])
        }
    }
    //전체 아이템 개수 리턴
    override fun getItemCount(): Int {
        return items.size
    }
    fun setList(content:MutableList<Content>){
        items.addAll(content)
        items.add(Content(0, " ", 0F))
    }
}