package com.example.bangu.main.profile.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.bangu.Event
import com.example.bangu.R
import com.example.bangu.databinding.ItemReviewBinding
import com.example.bangu.main.data.model.Content
import com.example.bangu.main.data.model.UserProfileData
import com.example.bangu.main.home.presentation.HomeViewModel
import com.example.bangu.main.profile.presentation.BookmarkViewModel

class BookmarkAdapter():RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = ArrayList<Content>()
    private val viewmodel = BookmarkViewModel()
    private var _unfollow = MutableLiveData<Event<Int>>()
    val  unfollow: LiveData<Event<Int>> = _unfollow
    private var _follow = MutableLiveData<Event<Int>>()
    val  follow: LiveData<Event<Int>> = _follow

    /**아이템이 리뷰인 경우*/
    inner class ReviewViewHolder(private val binding: ItemReviewBinding):RecyclerView.ViewHolder(binding.root) {
        /**장르 해쉬태그 (최대 6개)*/
        private val genre_array = arrayListOf<TextView>(
            binding.defaultGenre0, binding.defaultGenre1, binding.defaultGenre2,
            binding.defaultGenre3, binding.defaultGenre4, binding.defaultGenre5
        )
        fun bind(content: Content) {
            fun bind(content: Content) {
                /**북마크 추가, 삭제 기능*/
                var sign: Boolean
                val bookmark = binding.reviewBookmark
                bookmark.apply {
                    setOnClickListener {
                        sign = bookmark.isSelected //상태 값 할당
                        bookmark.isSelected = !sign
                        viewmodel.adjustBookmark(content.id) //id를 넘겨줌
                    }
                }
                /**북마크, 팔로우&팔로잉 상태 바인딩*/
                if (content.followState) binding.followingBtn.isSelected = true
                if (content.bookmark) binding.reviewBookmark.isSelected = true

                /**팔로우&팔로잉 기능*/
                var follow_sign: Boolean
                binding.followingBtn.apply {
                    setOnClickListener {
                        follow_sign = it.isSelected
                        it.isSelected = !follow_sign
                        when (isSelected) {
                            true -> _unfollow.postValue(Event(content.userProfileData!!.id)) //언팔로우 전달
                            else -> _follow.postValue(Event(content.userProfileData!!.id)) //팔로우 전달
                        }
                    }
                }
                /**리뷰를 작성한 계정 프로필 바인딩*/
                binding.apply {
                    userNickname.text = content.userProfileData?.nickname
                    userGender.text = if (content.userProfileData?.gender == "M") "남성" else "여성"
                    userAgearea.text = when (content.userProfileData?.birth) {
                        1 -> "10대"
                        2 -> "20대"
                        3 -> "30대"
                        4 -> "40대"
                        5 -> "50대"
                        else -> "60대 이상"
                    }

                }
                /**프로필 이미지 바인딩*/
                val profile_imageUrl = content.userProfileData?.imageUrl
                when (profile_imageUrl) {
                    "" -> Glide.with(binding.root).load(R.drawable.review_icon)
                        .override(Target.SIZE_ORIGINAL).into(binding.userImage)
                    else -> Glide.with(binding.root).load(profile_imageUrl)
                        .override(Target.SIZE_ORIGINAL).into(binding.userImage)
                }
                /**영화 및 리뷰 내용, 팔로잉, 북마크, 별점 바인딩*/
                binding.apply {
                    reviewMovietitle.text = content.movieResponseData?.title
                    reviewCore.text = content.content
                    reviewBookmark.isSelected = content.bookmark //영화 북마크 여부
                    followingBtn.isSelected = content.followState
                    reviewStars.rating = content.score
                    /**영화 장르 해쉬 태그*/
                    var genre_token = content.movieResponseData?.genre?.split(' ')
                    if (genre_token != null) {
                        for (i in 0..genre_token.size - 1) {
                            genre_array[i].text = genre_token[i] //장르 문자열 삽입
                            genre_array[i].visibility = View.VISIBLE //가시적이게 전환
                        }
                    }
                }
                /**영화 이미지 바인딩*/
                val movie_imageUrl = content.movieResponseData?.imageUrl
                when (movie_imageUrl) {
                    " " -> Glide.with(binding.root).load(R.drawable.movie01)
                        .override(Target.SIZE_ORIGINAL).into(binding.reviewMovieimage)
                    null -> Glide.with(binding.root).load(R.drawable.movie03)
                        .override(Target.SIZE_ORIGINAL).into(binding.reviewMovieimage)
                    else -> Glide.with(binding.root).load(movie_imageUrl)
                        .override(Target.SIZE_ORIGINAL).into(binding.reviewMovieimage)
                }
                /**OTT 바인딩*/
                /**OTT 아이콘 초기화*/
                binding.apply {
                    netflix.visibility = View.GONE
                    tving.visibility = View.GONE
                    watcha.visibility = View.GONE
                    wavve.visibility = View.GONE
                }
                val ottSize = content.movieResponseData?.movieOtts?.size
                for (i in 0 until ottSize!!) {
                    when (content.movieResponseData?.movieOtts!![i].ottName) {
                        "NETFLIX" -> binding.netflix.visibility = View.VISIBLE
                        "TVING" -> binding.tving.visibility = View.VISIBLE
                        "WATCHAPLAY" -> binding.watcha.visibility = View.VISIBLE
                        "WAVVE" -> binding.wavve.visibility = View.VISIBLE
                    }
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemReviewBinding.inflate(layoutInflater,parent,false)

        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ReviewViewHolder){
            holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setList(content: MutableList<Content>){
        /**서버에서 받아온 Content 리스트 삽입*/
        items.addAll(content)
    }
}