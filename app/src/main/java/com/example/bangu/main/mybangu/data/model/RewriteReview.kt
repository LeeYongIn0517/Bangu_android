package com.example.bangu.main.mybangu.data.model

import com.google.gson.annotations.SerializedName

data class RewriteReview(
    @SerializedName("attention")
    var attention:String,
    @SerializedName("content")
    var content:String,
    @SerializedName("dialogue")
    var dialogue:String,
    @SerializedName("revealed")
    var revealed:Boolean,
    @SerializedName("score")
    var score:Float,
)
