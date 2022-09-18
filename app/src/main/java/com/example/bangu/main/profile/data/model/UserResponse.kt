package com.example.bangu.main.profile.data.model

import com.example.bangu.signup.data.model.userOttResponseData
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id")
    var id:Int,
    @SerializedName("email")
    var email:String,
    @SerializedName("nickname")
    var nickname:String,
    @SerializedName("birth")
    var birth:Int,
    @SerializedName("gender")
    var gender:String,
    @SerializedName("userOttResponseData")
    var userOttResponseData: List<userOttResponseData>,
    @SerializedName("create_at")
    var create_at: String,
    @SerializedName("update_at")
    var update_at: String,
)
