package com.example.bangu.signup.data.model

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

//응답 데이터모델
data class SignupResponse(
    var birth :Long,
    @SerializedName("createAt")
    var createAt: Timestamp,
    @SerializedName("email")
    var email:String,
    @SerializedName("gender")
    var gender:String,
    @SerializedName("id")
    var id:Int,
    @SerializedName("nickname")
    var nickname:String,
    @SerializedName("updateAt")
    var updateAt: Timestamp,
    @SerializedName("userOttRespenseData")
    var userOttRespenseData: List<userOttResponseData>
)
data class userOttResponseData(
    @SerializedName("ottId")
    var ottID:Long,
    @SerializedName("ottName")
    var ottName:String,
    @SerializedName("userId")
    var userId: Long
)