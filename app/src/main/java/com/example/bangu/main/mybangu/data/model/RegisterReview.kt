package com.example.bangu.main.mybangu.data.model

import com.example.bangu.main.data.model.MovieOtts
import com.google.gson.annotations.SerializedName

data class RegisterReview(
    @SerializedName("actor")
    var actor:String?,
    @SerializedName("attention")
    var attention:String,
    @SerializedName("content")
    var content:String,
    @SerializedName("dialogue")
    var dialogue:String,
    @SerializedName("director")
    var director:String?,
    @SerializedName("genre")
    var genre:String,
    @SerializedName("imageUrl")
    var imageUrl:String,
    @SerializedName("movieOtts")
    var movieOtts:List<MovieOtts>?,
    @SerializedName("revealed")
    var revealed:Boolean,
    @SerializedName("reviewOtt")
    var reviewOtt:ReviewOtt,
    @SerializedName("score")
    var score:Float,
    @SerializedName("title")
    var title:String,
)
data class ReviewOtt(
    @SerializedName("netflix")
    var netflix:Boolean,
    @SerializedName("tving")
    var tving:Boolean,
    @SerializedName("watcha")
    var watcha:Boolean,
    @SerializedName("wavve")
    var wavve:Boolean
)