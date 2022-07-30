package com.example.bangu.main.mybangu.data.model

import com.google.gson.annotations.SerializedName

data class UpdateReview(
    @SerializedName("attention")
    var attention:String,
    @SerializedName("content")
    var content:String,
    @SerializedName("dialogue")
    var dialogue:String,
    @SerializedName("revealed")
    var revealed:String,
    @SerializedName("score")
    var score:Float,
)
