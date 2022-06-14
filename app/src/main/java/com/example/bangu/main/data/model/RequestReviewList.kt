package com.example.bangu.main.data.model

import com.google.gson.annotations.SerializedName

data class RequestReviewList(
    @SerializedName("content")
    var content:List<Content>,
    @SerializedName("pageable")
    var pageable:Pageable,
    @SerializedName("totalElements")
    var totalElements:Int,
    @SerializedName("totalPages")
    var totalPages:Int,
    @SerializedName("last")
    var last:Boolean,
    @SerializedName("number")
    var number:Int,
    @SerializedName("sort")
    var sort:Sort,
    @SerializedName("size")
    var size:Int,
    @SerializedName("numberOfElements")
    var numberOfElements:Int,
    @SerializedName("first")
    var first:Boolean,
    @SerializedName("empty")
    var empty:Boolean
)
data class Content(
    @SerializedName("id")
    var id:Int,
    @SerializedName("userProfileData")
    var userProfileData:UserProfileData?,
    @SerializedName("movieResponseData")
    var movieResponseData:MovieResponseData?,
    @SerializedName("followState")
    var followState:Boolean,
    @SerializedName("loginUser")
    var loginUser:Boolean,
    @SerializedName("reviewOttResponseData")
    var reviewOttResponseData:List<ReviewOttResponseData>?,
    @SerializedName("score")
    var score:Float,
    @SerializedName("attention")
    var attention:String,
    @SerializedName("dialogue")
    var dialogue:String,
    @SerializedName("content")
    var content:String,
    @SerializedName("bookmark")
    var bookmark:Boolean
)
data class MovieResponseData(
    @SerializedName("title")
    var title:String,
    @SerializedName("movieOtts")
    var movieOtts:List<MovieOtts>,
    @SerializedName("imageUrl")
    var imageUrl:String,
    @SerializedName("genre")
    var genre:String,
    @SerializedName("director")
    var director:String?,
    @SerializedName("actor")
    var actor:String?,
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
    @SerializedName("id")
    var id:Int,
    @SerializedName("nickname")
    var nickname:String,
    @SerializedName("imageUrl")
    var imageUrl:String,
    @SerializedName("gender")
    var gender:String,
    @SerializedName("birth")
    var birth:Int
)
data class Pageable(
    @SerializedName("sort")
    var sort:Sort,
    @SerializedName("offset")
    var offset:Int,
    @SerializedName("pageNumber")
    var pageNumber:Int,
    @SerializedName("pageSize")
    var pageSize:Int,
    @SerializedName("paged")
    var paged:Boolean,
    @SerializedName("unpaged")
    var unpaged:Boolean,
)
data class Sort(
    @SerializedName("empty")
    var empty: Boolean,
    @SerializedName("sorted")
    var sorted:Boolean,
    @SerializedName("unsorted")
    var unsorted:Boolean
)