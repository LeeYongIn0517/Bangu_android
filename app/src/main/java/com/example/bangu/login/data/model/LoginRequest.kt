package com.example.bangu.login.data.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("password")
    var password:String,
    @SerializedName("userId")
    var userId:String
)