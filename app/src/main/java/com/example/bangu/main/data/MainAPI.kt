package com.example.bangu.main.data

import com.example.bangu.main.data.model.Content
import com.example.bangu.main.data.model.RequestReviewList
import com.example.bangu.main.mybangu.data.model.MovieSearchResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MainAPI {
    /**리뷰를 전체 조회*/
    @GET("/reviews/lists")
    fun requestReviewList(
        @Header("X-AUTH-TOKEN") accessToken:String,
        @Query("page") page:Int,
        @Query("size") size:Int,
        @Query("type") type:String,
    ): Observable<RequestReviewList>
    /**영화 이름으로 리뷰 검색*/
    @GET("/reviews")
    fun searchReviews(
        @Header("X-AUTH-TOKEN") accessToken:String,
        @Query("title") title:String,
        @Query("sortType") sortType:Boolean,
    ):Single<List<Content>>

    /**영화 이름으로 작품 검색*/
    @GET("/movies/search")
    fun searchMovies(
        @Query("name") name:String,
        @Query("page") page:Int,
        @Query("size") size:Int
    ): Single<MovieSearchResponse>
}