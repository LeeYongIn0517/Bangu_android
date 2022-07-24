package com.example.bangu.main.mybangu.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.bangu.R
import com.example.bangu.databinding.ReviewMovieItemBinding
import com.example.bangu.main.data.model.MovieResponseData
import com.example.bangu.main.mybangu.ui.myInterface.Communicator
import java.lang.StringBuilder

class SearchPopupAdapter(private val listener:Communicator):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items = ArrayList<MovieResponseData>()
    companion object{
        private const val TYPE_MOVIE = 0
        private const val TYPE_LOADING = 1
    }
    /*아이템이 영화인 경우*/
    inner class ResultViewHolder(private val binding:ReviewMovieItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(movieData:MovieResponseData){
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
                resultGenre.text = movieData.genre //영화 장르
                resultDirector.text = movieData.director //영화 감독
                resultActor.text = movieData.actor //영화 배우
                resultTitle.text = setEnter(movieData.title,ottSize) //영화 제목
            }
            /*영화 작품 선택 시*/
            binding.movieItem.setOnClickListener {
                binding.movieLayout.background = it.context.getDrawable(R.drawable.mybangu_item_selected)
                //리뷰 작성 프레그먼트에 선택된 데이터 전달하기
                val title = binding.resultTitle.text.toString()
                val imageUrl = movieData.imageUrl
                listener.passData(title,imageUrl, movieData.movieOtts!!) //인터페이스로 데이터 전달
                //listener.passWholeData(movieData) //인터페이스로 리뷰등록에 필요한 영화 전체 데이터 전달
            }
        }
    }
    //inner class LoadingViewHolder(private val binding: ReviewItemLoadingBinding):RecyclerView.ViewHolder(binding.root){ }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ReviewMovieItemBinding.inflate(layoutInflater,parent,false)
        return ResultViewHolder(binding)
        /*when(viewType){
            TYPE_MOVIE -> {
                val binding = ReviewMovieItemBinding.inflate(layoutInflater,parent,false)
                return ResultViewHolder(binding)
            }
            else -> {
                val binding = ReviewItemLoadingBinding.inflate(layoutInflater,parent,false)

                return LoadingViewHolder(binding)
            }
        }*/
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ResultViewHolder)
            holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
    /*//뷰의 타입을 정하는 함수
    override fun getItemViewType(position: Int): Int {
        return when(items[position].imageUrl){
            " " -> TYPE_LOADING //setList에서 설정한 로딩뷰의 imageUrl값인 경우
            else -> TYPE_MOVIE
        }
    }
    //로딩이 완료되면 프로그레스바 지우기
    fun deleteLoading(){
        items.removeAt(items.lastIndex)
    }*/
    fun setList(content:MutableList<MovieResponseData>){
        items.addAll(content) //서버에서 받아온 영화작품 데이터 리스트 삽입
        //items.add(MovieResponseData(" ",null," "," "," "," ")) //마지막에 빈 아이템 추가(로딩 뷰)
    }
    /*아이템의 영화제목 길이에 따라 엔터를 삽입하는 함수*/
    fun setEnter(title:String,ottSize:Int):String{
        val item_title = StringBuilder().append(title) //엔터 삽입 후 리턴될 제목
        if(title.length > 12){
            val mString = title.split(" ").toTypedArray() //띄어쓰기로 원소 나누기
            var sum = 0 //엔터 삽입 위치 카운팅
            for(i in 0 until mString.size){
                sum += mString[i].length + 1
                when(ottSize){
                    //ott개수에 따라서 엔터 삽입위치가 달라짐
                    1->if(sum > 16) break
                    2->if(sum > 14) break
                    3->if(sum > 12) break
                    4->if(sum > 10) break
                }
            }
            item_title.insert(sum-1,"\n")
        }

        return item_title.toString()
    }

}
