package com.example.bangu.login.data

import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginAPI {
    @FormUrlEncoded
    @POST("") //Http Method 예시
    fun requestLogin() //미정. 인증요청 함수
}