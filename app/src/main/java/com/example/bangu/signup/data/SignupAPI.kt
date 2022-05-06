package com.example.bangu.signup.data

import com.example.bangu.signup.data.model.SignupRequest
import com.example.bangu.signup.data.model.SignupResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.*

interface SignupAPI {
    @GET("/users/emailCheck/{userEmail}") //아이디 중복확인
    fun checkUserId(
        @Path("userEmail") userEmail:String
    ): Single<Boolean>

    @GET("/users/nicknameCheck/{nickname}") //닉네임 중복확인
    fun checkNickname(
        @Path("nickname") nickname:String
    ): Single<Boolean>

    @POST("/session/signup") //회원가입
    fun requestSignup(
        @Body signupRequest: SignupRequest
    ):Call<SignupResponse>
}