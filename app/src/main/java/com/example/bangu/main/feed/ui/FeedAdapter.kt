package com.example.bangu.main.feed.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bangu.R
import com.example.bangu.databinding.ReviewItemBinding
import com.example.bangu.databinding.ReviewItemLoadingBinding
import com.example.bangu.main.data.model.Content
import com.example.bangu.main.home.ui.HomeAdapter

class FeedAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object{
        private const val TYPE_MOVIE = 0
        private const val TYPE_REVIEW = 1
        private const val TYPE_LOADING = 2
    }
    private val items = ArrayList<Content>()
    private val viewmodel = FeedViewModel()
    inner class ReviewViewHolder(private val binding:ReviewItemBinding):RecyclerView.ViewHolder(binding.root){
        //장르 해쉬태그 (최대 6개)
        private val genre_array = arrayListOf<TextView>(
            binding.defaultGenre0,binding.defaultGenre1,binding.defaultGenre2,
            binding.defaultGenre3,binding.defaultGenre4,binding.defaultGenre5)
        //별점 이미지
        fun bind(content:Content){
            //북마크 추가, 삭제 기능
            var sign:Boolean
            val bookmark = binding.reviewBookmark
            bookmark.apply {
                setOnClickListener {
                    sign = bookmark.isSelected
                    bookmark.isSelected = !sign
                    viewmodel.adjustBookmark(content.id)
                }
            }
            //리뷰를 작성한 계정 프로필 바인딩
            binding.apply {
                userNickname.text = content.userProfileData?.nickname
                userGender.text = if(content.userProfileData?.gender == "M") "남성" else "여성"
                userAgearea.text = when(content.userProfileData?.birth){
                    1 -> "10대"
                    2 -> "20대"
                    3 -> "30대"
                    4 -> "40대"
                    5 -> "50대"
                    else ->  "60대 이상"
                }
            }
            //프로필 이미지 바인딩
            val profile_imageUrl = content.userProfileData?.imageUrl
            when(profile_imageUrl){
                "" -> Glide.with(binding.root).load(R.drawable.review_icon).override(28,28).into(binding.userImage)
                else -> Glide.with(binding.root).load(profile_imageUrl).override(28,28).into(binding.userImage)
            }
            //영화 및 리뷰 내용, 팔로잉, 북마크 바인딩
            binding.apply {
                reviewMovietitle.text = content.movieResponseData?.title
                reviewCore.text = content.dialogue //영화 내용
                reviewBookmark.isSelected = content.bookmark //영화 북마크 여부
                //영화 장르 해쉬 태그
                var genre_token = content.movieResponseData!!.genre.split(' ')
                for(i in 0..genre_token.size){
                    genre_array[i].text = genre_token[i] //장르 문자열 삽입
                    genre_array[i].visibility = View.VISIBLE //가시적이게 전환
                }
            }
            //영화 이미지 바인딩
            val movie_imageUrl = content.movieResponseData?.imageUrl
            when(movie_imageUrl){
                " " -> Glide.with(binding.root).load(R.drawable.movie01).override(127,100).into(binding.reviewMovieimage)
                null -> Glide.with(binding.root).load(R.drawable.movie03).override(127,100).into(binding.reviewMovieimage)
                else -> Glide.with(binding.root).load(movie_imageUrl).override(127,100).into(binding.reviewMovieimage)
            }
            //OTT 바인딩
            val ottSize = content.reviewOttResponseData?.size
            for(i in 0 until ottSize!!){
                when(content.reviewOttResponseData!![i].ottName){
                    "NETFLIX" -> binding.netflix.visibility = View.INVISIBLE
                    "TVING" -> binding.tving.visibility = View.INVISIBLE
                    "WATCHA" -> binding.watcha.visibility = View.INVISIBLE
                    "WAVVE" -> binding.wavve.visibility = View.INVISIBLE
                }
            }
            //피드 페이지의 리뷰이기 때문에 팔로우 버튼 invisible처리
            binding.followingBtn.visibility = View.INVISIBLE
        }
    }
    inner class LoadingViewHolder(private val binding:ReviewItemLoadingBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context);
        when(viewType){
            TYPE_REVIEW -> {
                val binding = ReviewItemBinding.inflate(layoutInflater,parent,false)

                return ReviewViewHolder(binding)
            }
            else -> {
                val binding = ReviewItemLoadingBinding.inflate(layoutInflater,parent,false)

                return LoadingViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is HomeAdapter.ReviewViewHolder){
            holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(items[position].movieResponseData?.imageUrl){
            " " -> FeedAdapter.TYPE_LOADING
            else -> FeedAdapter.TYPE_REVIEW
        }
    }
    fun deleteLoading(){
        items.removeAt(items.lastIndex)
    }
    fun setList(content:MutableList<Content>){
        items.addAll(content) //서버에서 받아온 Content 리스트 삽입
        items.add(Content(0,null,null,false,false,null,
            0f," ", " ", " ",false)) //마지막에 빈 아이템 추가(로딩 뷰)
    }
}