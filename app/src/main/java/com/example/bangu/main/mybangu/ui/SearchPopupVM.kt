package com.example.bangu.main.mybangu.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bangu.main.data.model.MovieResponseData
import com.example.bangu.main.mybangu.data.MyBanguRepository
import com.example.bangu.main.mybangu.data.model.MovieSearchResponse

class SearchPopupVM : ViewModel() {
    private val repo = MyBanguRepository
    private var _movieList = MutableLiveData<List<MovieResponseData>>() //리사이클 뷰에 넣을 원소 == 영화 1개의 데이터
    val movieList:LiveData<List<MovieResponseData>> = _movieList
    /*리뷰에 쓸 영화 작품 불러오기*/
    fun requestMovie(name:String, page:Int, size:Int){
        repo.requestMovie(name,page,size,object:MyBanguRepository.GetDataCallback<MovieSearchResponse>{
            override fun onSuccess(data: MovieSearchResponse?) {
                Log.d("SearchPuVM.requestMovie","onSuccess")
                if(data != null){
                    _movieList.value = data.content
                }
            }
            override fun onFailure(throwable: Throwable) {
                Log.d("SearchPuVM.requestMovie","onFailure")
            }
        })
    }
}