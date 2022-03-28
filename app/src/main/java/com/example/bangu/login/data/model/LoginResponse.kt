package com.example.bangu.login.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("accessToken")
    var accessToken: String,
    @SerializedName("accessTokenExpireDate")
    var accessTokenExpireDate: Int,
    @SerializedName("grantType")
    var grantType: String,
    @SerializedName("refreshToken")
    var refreshToken: String
)
