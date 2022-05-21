package com.example.bangu.main.mybangu.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bangu.GlideApp
import com.example.bangu.R
import com.example.bangu.databinding.MybanguItemBinding
import com.example.bangu.databinding.ReviewItemBinding
import com.example.bangu.databinding.ReviewItemLoadingBinding
import com.example.bangu.main.data.model.Content
import com.example.bangu.main.home.ui.HomeViewModel

class MyBanguAdapter():RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object{
        private const val TYPE_MOVIE = 0
        private const val TYPE_REVIEW = 1
        private const val TYPE_LOADING = 2
    }
    private val items = ArrayList<Content>()
    private val viewmodel = MyBanguViewModel()

    /*아이템이 리뷰인 경우*/
    inner class ReviewViewHolder(private val binding: MybanguItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(content:Content){
            //영화 정보 및 리뷰 내용 바인딩
            binding.apply {
                myreviewTitle.text = content.movieResponseData?.title
                /*reviewGenre.text = when(content.movieResponseData?.genre){
                    장르는 정보 변동 중. 나중에 구현
                }*/
                myreviewStarscore.rating = content.score
                myreviewAttention.text = content.attention
                myreviewDialog.text = content.dialogue
                myreviewContent.text = content.content
            }
            // 영화 이미지 바인딩
            val movie_imageUrl = content.movieResponseData?.imageUrl
            when(movie_imageUrl){
                " " -> GlideApp.with(binding.root).load(R.drawable.movie01).override(127,100).into(binding.myreviewImage)
                null -> GlideApp.with(binding.root).load(R.drawable.movie03).override(127,100).into(binding.myreviewImage)
                else -> GlideApp.with(binding.root).load(movie_imageUrl).override(127,100).into(binding.myreviewImage)
            }
            //ott 바인딩
            val ottSize = content.reviewOttResponseData?.size
            for(i in 0 until ottSize!!){
                when(content.reviewOttResponseData!![i].ottName){
                    "NETFLIX" -> binding.myreviewNetflix.visibility = View.VISIBLE
                    "TVING" -> binding.myreviewTving.visibility = View.VISIBLE
                    "WATCHA" -> binding.myreviewWatcha.visibility = View.VISIBLE
                    "WAVVE" -> binding.myreviewWavve.visibility = View.VISIBLE
                }
            }
            //리뷰 수정하기
            binding.reviewRewrite.setOnClickListener {

            }
            //리뷰 삭제하기
            binding.reviewDelete.setOnClickListener {

            }
        }
    }
    inner class LoadingViewHolder(private val binding: ReviewItemLoadingBinding):RecyclerView.ViewHolder(binding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
    //로딩이 완료되면 프로그레스바 지우기
    fun deleteLoading(){
        items.removeAt(items.lastIndex)
    }
    fun setList(content:MutableList<Content>){

    }
}