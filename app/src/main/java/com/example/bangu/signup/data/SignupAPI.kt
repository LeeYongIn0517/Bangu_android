package com.example.bangu.signup.data

import com.example.bangu.signup.data.model.SignupModel
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.sql.Timestamp

interface SignupAPI {
    @FormUrlEncoded
    @POST("/session/signup") //회원가입
    fun requestSignup(
        @Field("birth") birth:Long,
        @Field("create_At") createAt:String,
        @Field("email") email:String,
        @Field("gender") gender:String,
        @Field("netflix") netflix:Boolean?,
        @Field("nickname") nickname:String,
        @Field("password") password:String,
        @Field("tving") tving:Boolean?,
        @Field("updateAt") updateAt:String,
        @Field("watcha") watcha:Boolean?,
        @Field("wavve") wavve:Boolean?
    ):Call<SignupModel>

    @FormUrlEncoded
    @POST("/users/emailCheck/{userEmail}") //아이디 중복확인
    fun checkUserEmail(
        @Field("userEmail") userEmail:String
    ):Observable<Boolean>
}