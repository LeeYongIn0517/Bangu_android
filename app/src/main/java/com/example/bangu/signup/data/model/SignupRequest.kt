package com.example.bangu.signup.data.model

import com.google.gson.annotations.SerializedName
import retrofit2.http.Field

data class SignupRequest (
    @SerializedName("birth")
    var birth :Long,
    @SerializedName("email")
    var email:String,
    @SerializedName("gender")
    var gender:String,
    @SerializedName("netflix")
    var netflix:Boolean?,
    @SerializedName("nickname")
    var nickname:String,
    @SerializedName("password")
    var password:String,
    @SerializedName("tving")
    var tving:Boolean?,
    @SerializedName("watcha")
    var watcha:Boolean?,
    @SerializedName("wavve")
    var wavve:Boolean?
        )