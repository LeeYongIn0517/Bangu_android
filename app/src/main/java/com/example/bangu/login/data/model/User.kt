package com.example.bangu.login.data.model

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class User(
    @SerializedName("id")
    var id :Long,
    @SerializedName("email")
    var email:Char,
    @SerializedName("password")
    var password:Char,
    @SerializedName("gender")
    var gender:Char,
    @SerializedName("nickname")
    var nickname:Char,
    @SerializedName("birth")
    var birth:Char,
    @SerializedName("deleted")
    var deleted:Boolean,
    @SerializedName("image_url")
    var image_url:Char,
    @SerializedName("create_at")
    var create_at: Timestamp,
    @SerializedName("update_at")
    var update_at: Timestamp
)
