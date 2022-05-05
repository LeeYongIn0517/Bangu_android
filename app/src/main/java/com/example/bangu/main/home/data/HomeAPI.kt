package com.example.bangu.main.home.data

import com.example.bangu.main.home.data.model.RequestReviewList
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.*

interface HomeAPI {

    @GET("/reviews/lists") //리뷰를 전체 조회(구독하는 OTT로 필터링)
    fun requestReviewList(
        @Header("X-AUTH-TOKEN") accessToken:String,
        @Query("page") page:Int,
        @Query("size") size:Int,
        @Query("type") type:String,
        ): Single<RequestReviewList>

    @POST("/reviews/bookmark/{reviewId}") //리뷰 북마크 및 북마크 해제 하기
    fun adjustBookmark(
        @Header("X-AUTH-TOKEN") accessToken:String,
        @Path("reviewId") reviewId:Int,
    ):Single<Boolean>

    @DELETE("/follow/{toUserId}") //유저 idx를 받아 해당 유저를 언팔로우합니다
    fun requestToUnFollow(
        @Header("X-AUTH-TOKEN") accessToken:String,
        @Path("toUserId") toUser:Int,
    ): Call<Any>

    @POST("/follow/{toUser}") //유저 idx를 받아 해당 유저를 팔로우합니다
    fun requestToFollow(
        @Header("X-AUTH-TOKEN") accessToken:String,
        @Path("toUser") toUser:Int,
    ): Call<Any>
}