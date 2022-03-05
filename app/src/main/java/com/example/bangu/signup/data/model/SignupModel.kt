package com.example.bangu.signup.data.model

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

//응답 데이터모델
data class SignupModel(
    @SerializedName("birth")
    var birth :Long,
    @SerializedName("create_at")
    var create_at: Timestamp,
    @SerializedName("email")
    var email:String,
    @SerializedName("gender")
    var gender:Char,
    @SerializedName("nickname")
    var nickname:String,
    @SerializedName("update_at")
    var update_at: Timestamp,
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