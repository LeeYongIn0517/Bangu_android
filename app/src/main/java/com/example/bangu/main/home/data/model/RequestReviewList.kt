package com.example.bangu.main.home.data.model

import com.google.gson.annotations.SerializedName

data class RequestReviewList(
    @SerializedName("content")
    var content:List<Content>,
    @SerializedName("empty")
    var empty:Boolean,
    @SerializedName("first")
    var first:Boolean,
    @SerializedName("last")
    var last:Boolean,
    @SerializedName("number")
    var number:Int,
    @SerializedName("numberOfElements")
    var numberOfElements:Int,
    @SerializedName("pageable")
    var pageable:Pageable,
    @SerializedName("size")
    var size:Int,
    @SerializedName("sort")
    var sort:Sort,
    @SerializedName("totalElements")
    var totalElements:Int,
    @SerializedName("totalPages")
    var totalPages:Int
)
data class Content(
    @SerializedName("attention")
    var attention:String,
    @SerializedName("content")
    var content:String,
    @SerializedName("dialogue")
    var dialogue:String,
    @SerializedName("followState")
    var followState:Boolean,
    @SerializedName("id")
    var id:Int,
    @SerializedName("loginUser")
    var loginUser:Boolean,
    @SerializedName("movieResponseData")
    var movieResponseData:MovieResponseData,
    @SerializedName("reviewOttResponseData")
    var reviewOttResponseData:List<ReviewOttResponseData>,
    @SerializedName("score")
    var score:Float,
    @SerializedName("userProfileData")
    var userProfileData:UserProfileData,
)
data class MovieResponseData(
    @SerializedName("actor")
    var actor:String,
    @SerializedName("birth")
    var birth:Int,
    @SerializedName("deleted")
    var deleted:Boolean,
    @SerializedName("director")
    var director:String,
    @SerializedName("genre")
    var genre:String,
    @SerializedName("id")
    var id:Int,
    @SerializedName("imageUrl")
    var imageUrl:String,
    @SerializedName("movieOtts")
    var movieOtts:List<MovieOtts>,
    @SerializedName("title")
    var title:String,
)
data class MovieOtts(
    @SerializedName("ottId")
    var ottId:Int,
    @SerializedName("ottName")
    var ottName:String
)
data class ReviewOttResponseData(
    @SerializedName("id")
    var id:Int,
    @SerializedName("ottName")
    var ottName:String
)
data class UserProfileData(
    @SerializedName("birth")
    var birth:Int,
    @SerializedName("gender")
    var gender:String,
    @SerializedName("id")
    var id:Int,
    @SerializedName("imageUrl")
    var imageUrl:String,
    @SerializedName("nickname")
    var nickname:String
)
data class Pageable(
    @SerializedName("page")
    var page:Int,
    @SerializedName("size")
    var size:String,
    @SerializedName("sort")
    var sort:List<String>
)
data class Sort(
    @SerializedName("empty")
    var empty: Boolean,
    @SerializedName("sorted")
    var sorted:Boolean,
    @SerializedName("unsorted")
    var unsorted:Boolean
)