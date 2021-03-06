package com.example.bangu.main.data

import com.example.bangu.main.data.model.RequestReviewList
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MainAPI {
    @GET("/reviews/lists") //리뷰를 전체 조회(구독하는 OTT로 필터링)
    fun requestReviewList(
        @Header("X-AUTH-TOKEN") accessToken:String,
        @Query("page") page:Int,
        @Query("size") size:Int,
        @Query("type") type:String,
    ): Observable<RequestReviewList>
}