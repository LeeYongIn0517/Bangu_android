package com.example.bangu.signup_fin.data

import com.example.bangu.signup_fin.data.model.SgFinMovieList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SgFinAPI {
    @GET("/movies/lists") //회원가입 성공시 영화 리스트
    fun requestSgFinMovieList(
        @Query("page") page: Int,
        @Query("size") size:Int,
        @Query("sort") sort:Array<String>? = null
    ): Single<SgFinMovieList>
}