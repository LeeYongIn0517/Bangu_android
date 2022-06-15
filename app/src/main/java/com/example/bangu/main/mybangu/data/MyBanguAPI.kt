package com.example.bangu.main.mybangu.data

import com.example.bangu.main.data.model.RequestReviewList
import com.example.bangu.main.mybangu.data.model.MovieSearchResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MyBanguAPI {
    @GET("/movies/search") //영화 이름으로 작품 검색
    fun requestMovie(
        @Query("name") name:String,
        @Query("page") page:Int,
        @Query("size") size:Int
    ):Single<MovieSearchResponse>
}