package com.example.bangu.main.profile.data

import com.example.bangu.main.data.model.RequestReviewList
import com.example.bangu.main.profile.data.model.FollowerResponse
import com.example.bangu.main.profile.data.model.FollowingResponse
import io.reactivex.Single
import retrofit2.http.*

interface ProfileApi {

    @PATCH("/users/update/password")
    fun updatePassword(
        @Header("X-AUTH-TOKEN") accessToken:String,
        @Query("nickname") nickname:String,
    ):Single<Any>

    @PATCH("/users/update/nickname")
    fun updateNickname(
        @Header("X-AUTH-TOKEN") accessToken:String,
        @Query("nickname") nickname:String,
    ):Single<Any>

    /**유저 팔로잉 조회*/
    @GET("/users/{id}/following")
    fun requestFollowing(
        @Header("X-AUTH-TOKEN") accessToken:String,
        @Path("id") id:Int,
        @Query("page") page:Int,
        @Query("size") size:Int,
    ):Single<FollowingResponse>

    /**유저 팔로워 조회*/
    @GET("/users/{id}/follower")
    fun requestFollower(
        @Header("X-AUTH-TOKEN") accessToken:String,
        @Path("id") id:Int,
        @Query("page") page:Int,
        @Query("size") size:Int,
    ):Single<FollowerResponse>

    /**유저의 북마크한 리뷰 조회*/
    @GET("/reviews/bookmark")
    fun requestBookmark(
        @Header("X-AUTH-TOKEN") accessToken:String,
        @Query("page") page:Int,
        @Query("size") size:Int,
    ):Single<RequestReviewList>
}