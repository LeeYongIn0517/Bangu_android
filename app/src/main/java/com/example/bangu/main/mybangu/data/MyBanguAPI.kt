package com.example.bangu.main.mybangu.data

import com.example.bangu.main.mybangu.data.model.MovieSearchResponse
import com.example.bangu.main.mybangu.data.model.RegisterReview
import io.reactivex.Single
import retrofit2.http.*

interface MyBanguAPI {
    @GET("/movies/search") //영화 이름으로 작품 검색
    fun requestMovie(
        @Query("name") name:String,
        @Query("page") page:Int,
        @Query("size") size:Int
    ): Single<MovieSearchResponse>

    @POST("/reviews") //내가 작성한 리뷰 등록
    fun registerMyReview(
        @Header("X-AUTH-TOKEN") accessToken:String,
        @Body review: RegisterReview
    )
}