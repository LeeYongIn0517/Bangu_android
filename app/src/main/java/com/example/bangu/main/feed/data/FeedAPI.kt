package com.example.bangu.main.feed.data

import io.reactivex.Single
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface FeedAPI {
    @POST("/reviews/bookmark/{reviewId}") //리뷰 북마크 및 북마크 해제 하기
    fun adjustBookmark(
        @Header("X-AUTH-TOKEN") accessToken:String,
        @Path("reviewId") reviewId:Int,
    ): Single<Boolean>
}