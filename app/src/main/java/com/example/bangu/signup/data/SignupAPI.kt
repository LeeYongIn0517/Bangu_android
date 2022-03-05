package com.example.bangu.signup.data

import com.example.bangu.signup.data.model.SignupModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.sql.Timestamp

interface SignupAPI {
    @FormUrlEncoded
    @POST("/session/signup") //Http Method 예시
    fun requestSignup(
        @Field("birth") birth:String,
        @Field("create_at") create_at:Timestamp,
        @Field("email") email:String,
        @Field("gender") gender:Char,
        @Field("netflix") netflix:Boolean,
        @Field("nickname") nickname:String,
        @Field("password") password:String,
        @Field("tving") tving:Boolean,
        @Field("update_at") update_at:Timestamp,
        @Field("watcha") watcha:Boolean,
        @Field("wavve") wavve:Boolean,
    ):Call<SignupModel>
}