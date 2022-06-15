package com.example.bangu.main.mybangu.data

import android.util.Log
import com.example.bangu.main.data.model.RequestReviewList
import com.example.bangu.main.mybangu.data.model.MovieSearchResponse

object MyBanguRepository {
    private val MyBanguDr = MyBanguDataResource
    /**************************main-mybangu 내가 쓴 리뷰 페이지***************************/
    /*mybangu 페이지의 내가 쓴 리뷰 불러오기*/
    fun requestMyReviews(accessToken: String,page:Int,size:Int,type:String,callback: GetDataCallback<RequestReviewList>){
        Log.d("MyBanguRepository","requestMyReviews")
        MyBanguDr.requestMyReviews(accessToken,page,size,type,callback)
    }
    /***************************main-mybangu 리뷰 작성 페이지****************************/
    /***************************main-mybangu 영화작품 검색 페이지****************************/
    /*리뷰에 쓸 영화 작품 불러오기*/
    fun requestMovie(name: String, page: Int, size: Int,callback: GetDataCallback<MovieSearchResponse>){
        Log.d("MyBanguRepository","requestMovie")
        MyBanguDr.requestMovie(name,page,size,callback)
    }
    interface GetDataCallback<T>{
        fun onSuccess(data:T?)
        fun onFailure(throwable: Throwable)
    }
}