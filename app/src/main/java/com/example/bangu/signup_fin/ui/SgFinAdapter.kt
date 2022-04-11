package com.example.bangu.signup_fin.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bangu.databinding.SignupFinItemBinding
import com.example.bangu.databinding.SignupFinItemLoadingBinding
import com.example.bangu.signup_fin.data.model.Content

class SgFinAdapter():RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object{
        private const val TYPE_ITEM = 0
        private const val TYPE_LOADING = 1
    }
    private val items = ArrayList<Content>()

    //아이템이 게시물인 경우
    inner class ItemViewHolder(private val binding:SignupFinItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(content:Content){
            val imageUrl = content.imageUrl
            Glide.with(binding.root).load(imageUrl).into(binding.movieimage) //이미지 바인딩
            binding.moviestar.numStars = content.score.toInt() //별점이...Int타입만 가능함
        }
    }
    //아이템이 로딩뷰인 경우
    inner class LoadingViewHolder(private val binding:SignupFinItemLoadingBinding): RecyclerView.ViewHolder(binding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RecyclerView.ViewHolder {
        when(viewType){
            TYPE_ITEM -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SignupFinItemBinding.inflate(layoutInflater,parent,false)

                return ItemViewHolder(binding)
            }
            //TYPE_LOADING
            else -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SignupFinItemLoadingBinding.inflate(layoutInflater,parent,false)

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
    //뷰의 타입 정하는 함수
    override fun getItemViewType(position: Int): Int {
        //return super.getItemViewType(position)
        return when(items[position].imageUrl){
            " " -> TYPE_LOADING
            else -> TYPE_ITEM
        }
    }
    //로딩이 완료되면 프로그레스바 지우기
    fun deleteLoading(){
        items.removeAt(items.lastIndex)
    }
    fun setList(content:MutableList<Content>){
        items.addAll(content)
        items.add(Content(0, " ", 0F))
    }
}