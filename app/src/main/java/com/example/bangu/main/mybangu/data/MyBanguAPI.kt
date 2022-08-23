package com.example.bangu.main.mybangu.data

import com.example.bangu.main.data.model.Content
import com.example.bangu.main.mybangu.data.model.MovieSearchResponse
import com.example.bangu.main.mybangu.data.model.RegisterReview
import com.example.bangu.main.mybangu.data.model.RewriteReview
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
    ):Single<RegisterReview>

    @PATCH("/reviews/{id}") //내가 작성한 리뷰 수정
    fun rewriteMyReview(
        @Header("X-AUTH-TOKEN") accessToken:String,
        @Path("id") id:Int,
        @Body review: RewriteReview
    ):Single<RegisterReview>

    @DELETE("/reviews/{id}") //내가 작성한 리뷰 삭제
    fun deleteMyReview(
        @Header("X-AUTH-TOKEN") accessToken:String,
        @Path("id") id:Int,
    ):Single<Any>

    @GET("/reviews/{id}") //식별자 값의 리뷰 상세 조회
    fun requestSpecificReview(
        @Path("id") id:Int,
    ):Single<Content>
}