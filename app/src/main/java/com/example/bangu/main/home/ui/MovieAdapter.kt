package com.example.bangu.main.home.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.bangu.R
import com.example.bangu.databinding.ItemMovieBinding
import com.example.bangu.databinding.ItemMybanguBinding
import com.example.bangu.main.data.model.MovieResponseData
import com.example.bangu.main.mybangu.ui.MyBangu.MyBanguAdapter

class MovieAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = ArrayList<MovieResponseData>()

    inner class ReviewViewHolder(private val binding: ItemMovieBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(movieData:MovieResponseData) {
            //아이템에 서버로 얻은 정보 바인딩
            binding.apply {
                //영화 이미지 바인딩
                var movie_image = movieData.imageUrl
                when(movie_image) {
                    "" -> Glide.with(binding.root).load(R.mipmap.ic_launcher_round).override(Target.SIZE_ORIGINAL)
                        .into(binding.resultImage)
                    else -> Glide.with(binding.root).load(movie_image).override(Target.SIZE_ORIGINAL)
                        .into(binding.resultImage)
                }
                //ott정보 바인딩
                binding.apply{ //ott아이콘 초기화
                    resultNetflix.visibility = View.GONE
                    resultTving.visibility = View.GONE
                    resultWatcha.visibility = View.GONE
                    resultWavve.visibility = View.GONE
                }
                val ottSize = movieData.movieOtts?.size
                for(i in 0 until ottSize!!){
                    when(movieData.movieOtts!!.get(i).ottName){
                        "NETFLIX" -> binding.resultNetflix.visibility = View.VISIBLE
                        "TVING" -> binding.resultTving.visibility = View.VISIBLE
                        "WATCHAPLAY" -> binding.resultWatcha.visibility = View.VISIBLE
                        "WAVVE" -> binding.resultWavve.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(layoutInflater,parent,false)

        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ReviewViewHolder) holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setList(content:MutableList<MovieResponseData>){
        items.addAll(content) //서버에서 받아온 영화작품 데이터 리스트 삽입
    }

    /**모든 항목 삭제*/
    fun clearList(){
        var removed_num = itemCount //삭제되기 전 개수 기록
        items.clear() //모든 항목삭제
        notifyItemRangeRemoved(0, removed_num)
    }
}