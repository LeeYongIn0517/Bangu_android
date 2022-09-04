package com.example.bangu.main.mybangu.ui.MyBangu

import android.util.Log
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
import com.example.bangu.databinding.ItemMybanguBinding
import com.example.bangu.main.data.model.Content
import com.example.bangu.main.mybangu.ui.Review.ReviewViewModel

class MyBanguAdapter():RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = ArrayList<Content>()
    private var _rewrite = MutableLiveData<Event<Boolean>>()
    val rewrite :LiveData<Event<Boolean>> = _rewrite
    private var _delete = MutableLiveData<Event<Int>>()
    val delete :LiveData<Event<Int>> = _delete

    /*아이템이 리뷰인 경우*/
    inner class ReviewViewHolder(private val binding: ItemMybanguBinding):RecyclerView.ViewHolder(binding.root){
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
            //OTT 바인딩
            binding.apply{ //ott아이콘 초기화
                netflix.visibility = View.GONE
                tving.visibility = View.GONE
                watcha.visibility = View.GONE
                wavve.visibility =  View.GONE
            }
            val ottSize = content.movieResponseData?.movieOtts?.size
            for(i in 0 until ottSize!!){
                when(content.movieResponseData?.movieOtts!![i].ottName){
                    "NETFLIX" -> binding.netflix.visibility = View.VISIBLE
                    "TVING" -> binding.tving.visibility = View.VISIBLE
                    "WATCHA" -> binding.watcha.visibility = View.VISIBLE
                    "WAVVE" -> binding.wavve.visibility = View.VISIBLE
                }
            }
            //리뷰 수정하기
            binding.reviewRewrite.setOnClickListener {
                Log.d("MyBanguAdapter", "수정대상 리뷰 식별자: "+content.id.toString())
                ReviewViewModel().requestSpecificReview(content.id) //선택된 리뷰의 식별자로 서버에 리뷰요청보내기
                _rewrite.postValue(Event(true))
            }
            //리뷰 삭제하기
            binding.reviewDelete.setOnClickListener {
                _delete.postValue(Event(content.id))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMybanguBinding.inflate(layoutInflater,parent,false)

        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ReviewViewHolder) holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setList(content:MutableList<Content>){
        items.addAll(content) //서버에서 받아온 Content 리스트 삽입
    }
}