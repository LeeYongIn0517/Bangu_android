package com.example.bangu.main.mybangu.ui.MyBangu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.bangu.Event
import com.example.bangu.R
import com.example.bangu.databinding.ReviewItemLoadingBinding
import com.example.bangu.databinding.ReviewMybanguItemBinding
import com.example.bangu.main.data.model.Content
import com.example.bangu.main.mybangu.ui.Review.ReviewViewModel

class MyBanguAdapter():RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object{
        private const val TYPE_MOVIE = 0
        private const val TYPE_REVIEW = 1
        private const val TYPE_LOADING = 2
    }
    private val items = ArrayList<Content>()
    private var _rewrite = MutableLiveData<Event<Boolean>>()
    val rewrite :LiveData<Event<Boolean>> = _rewrite
    private var _delete = MutableLiveData<Event<Int>>()
    val delete :LiveData<Event<Int>> = _delete
    /*아이템이 리뷰인 경우*/
    inner class ReviewViewHolder(private val binding: ReviewMybanguItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(content:Content){
            //영화 정보 및 리뷰 내용 바인딩
            binding.apply {
                myreviewTitle.text = content.movieResponseData?.title
                myreviewGenre.text = content.movieResponseData?.genre
                myreviewStarscore.rating = content.score
                myreviewAttention.text = content.attention
                myreviewDialog.text = content.dialogue
                myreviewContent.text = content.content
            }
            // 영화 이미지 바인딩
            val movie_imageUrl = content.movieResponseData?.imageUrl
            when(movie_imageUrl){
                " " -> Glide.with(binding.root).load(R.drawable.movie01).override(Target.SIZE_ORIGINAL).into(binding.myreviewImage)
                null -> Glide.with(binding.root).load(R.drawable.movie03).override(Target.SIZE_ORIGINAL).into(binding.myreviewImage)
                else -> Glide.with(binding.root).load(movie_imageUrl).override(Target.SIZE_ORIGINAL).into(binding.myreviewImage)
            }
            //ott 바인딩
            binding.apply{ //ott아이콘 초기화
                myreviewNetflix.visibility = View.GONE
                myreviewTving.visibility = View.GONE
                myreviewWatcha.visibility = View.GONE
                myreviewWavve.visibility = View.VISIBLE
            }
            val ottSize = content.movieResponseData?.movieOtts?.size
            for(i in 0 until ottSize!!){
                when(content.movieResponseData?.movieOtts!![i].ottName){
                    "NETFLIX" -> binding.myreviewNetflix.visibility = View.VISIBLE
                    "TVING" -> binding.myreviewTving.visibility = View.VISIBLE
                    "WATCHA" -> binding.myreviewWatcha.visibility = View.VISIBLE
                    "WAVVE" -> binding.myreviewWavve.visibility = View.VISIBLE
                }
            }
            //리뷰 수정하기
            binding.reviewRewrite.setOnClickListener {
                ReviewViewModel().requestSpecificReview(content.id) //선택된 리뷰의 식별자로 서버에 리뷰요청보내기
                _rewrite.postValue(Event(true))
            }
            //리뷰 삭제하기
            binding.reviewDelete.setOnClickListener {
                _delete.postValue(Event(content.id))
            }
        }
    }
    inner class LoadingViewHolder(private val binding: ReviewItemLoadingBinding):RecyclerView.ViewHolder(binding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        when(viewType){
            TYPE_REVIEW -> {
                val binding = ReviewMybanguItemBinding.inflate(layoutInflater,parent,false)

                return ReviewViewHolder(binding)
            }
            else -> {
                val binding = ReviewItemLoadingBinding.inflate(layoutInflater,parent,false)

                return LoadingViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ReviewViewHolder){
            holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(items[position].movieResponseData?.imageUrl){
            " "-> TYPE_LOADING
            else -> TYPE_REVIEW
        }
    }
    //로딩이 완료되면 프로그레스바 지우기
    fun deleteLoading(){
        items.removeAt(items.lastIndex)
    }
    fun setList(content:MutableList<Content>){
        items.addAll(content) //서버에서 받아온 Content 리스트 삽입
        items.add(Content(0,null,null,false,false,null,
            0f," ", " ", " ",false))//마지막에 빈 아이템 추가(로딩 뷰)
    }
}