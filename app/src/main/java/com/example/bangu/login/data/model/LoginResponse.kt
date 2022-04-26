package com.example.bangu.login.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("grantType")
    var grantType: String,
    @SerializedName("accessToken")
    var accessToken: String,
    @SerializedName("refreshToken")
    var refreshToken: String,
    @SerializedName("accessTokenExpireDate")
    var accessTokenExpireDate: Int
)
