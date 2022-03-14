package com.example.bangu.signup.data

import com.example.bangu.signup.data.model.Signup
import com.example.bangu.signup.data.model.SignupResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.*
import java.sql.Timestamp

interface SignupAPI {
    @POST("/session/signup") //회원가입
    @Headers("accept: application/json",
        "content-type: application/json")
    fun requestSignup(@Body signup:Signup):Call<SignupResponse>

    @GET("/users/emailCheck/{userEmail}") //아이디 중복확인
    fun checkUserEmail(
        @Path("userEmail") userEmail:String
    ): Single<Boolean>

    @GET("/users/nicknameCheck/{nickname}") //닉네임 중복확인
    fun checkNickname(
        @Path("nickname") nickname:String
    ): Single<Boolean>
}