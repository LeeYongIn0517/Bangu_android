package com.example.bangu.login.data

import com.example.bangu.login.data.model.AccessToken
import com.example.bangu.login.data.model.LoginRequest
import com.example.bangu.login.data.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
interface LoginAPI {
    @GET("/oauth/kakao/login") //api수정 중
    fun getKakaoToken(): Call<AccessToken>

    @POST("/session/login") //로그인
    fun getLoginToken(
        @Body loginRequest: LoginRequest
    ): Call<LoginResponse>
}